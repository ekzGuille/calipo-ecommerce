package controlador.acciones;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.pedidos.Pedido;
import modelo.pedidos.PedidoDAO;
import modelo.productos.Producto;
import modelo.productos.ProductoDAO;

/**
 *
 * @author guill
 */
public class PedidoAction {

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String pagDestino = "";
        String actionReceived = request.getParameter("ACTION");

        String[] actionSplit = actionReceived.split("\\.");

        switch (actionSplit[1]) {
            case "ADD":
                pagDestino = add(request, response);
                break;

            case "LISTAR":
                pagDestino = list(request, response);
                break;

            case "MODIFICAR":
                pagDestino = mod(request, response);
                break;

            case "ELIMINAR":
                pagDestino = delete(request, response);
                break;

            case "ADDGETID":
                pagDestino = addGetID(request, response);
                break;

        }

        return pagDestino;
    }

    private String add(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
//        webRedirect = "/Ecommerce/Controlador?ACTION=PRODUCTO.PRODACTIVO";
        webRedirect = "/archivosJSP/todosProductos.jsp";

        String mensajeRespuesta = "";

        Pedido pedido = new Pedido();
        PedidoDAO pedidoDAO = new PedidoDAO();
        String fechaPedido = request.getParameter("FECHA_PEDIDO");
        String idUsuario = request.getParameter("ID_USUARIO");

        pedido.setFechaPedido(fechaPedido);
        pedido.setIdUsuario(Integer.parseInt(idUsuario));

        if (pedidoDAO.add(pedido) == 1) {

            mensajeRespuesta = "Pedido creado";
        } else {
            mensajeRespuesta = "Pedido NO creado";
        }
        request.setAttribute("MENSAJE_PROD", mensajeRespuesta);

        return webRedirect;
    }

    private String list(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
        webRedirect = "/archivosJSP/adminMenu.jsp";

        PedidoDAO pedidoDAO = new PedidoDAO();
        ArrayList<Pedido> lstPedidos = pedidoDAO.findAll(null);

        String mensajeRespuesta = "";
        if (lstPedidos.isEmpty()) {
            mensajeRespuesta = "No hay pedidos";
            request.setAttribute("MENSAJE_PROD", mensajeRespuesta);
        } else {
            request.setAttribute("MENSAJE_PROD", lstPedidos);
        }
        request.setAttribute("MENSAJE_PROD", lstPedidos);

        return webRedirect;
    }

    private String mod(HttpServletRequest request, HttpServletResponse response) {

        String webRedirect;
//        webRedirect = "/index.jsp";
        webRedirect = "/archivosJSP/adminMenu.jsp";

        Producto producto = new Producto();
        ProductoDAO productoDAO = new ProductoDAO();

        String id = request.getParameter("ID_PRODUCTO");
        String nombre = request.getParameter("NOMBRE_PRODUCTO");
        String precioCompra = request.getParameter("PRECIO_COMPRA");
        String precioVenta = request.getParameter("PRECIO_VENTA");
        String porcentajeOferta = request.getParameter("PORCENTAJE_OFERTA");
        String descripcion = request.getParameter("DESCRIPCION");
        String activo = request.getParameter("PRODUCTO_ACTIVO");
        String foto = "";

        if (!request.getParameter("FOTO_PRODUCTO").equals("") && request.getParameter("NOMBREFOTO") != null) {
            //Si había foto y se pone otra se actualiza
            foto = request.getParameter("FOTO_PRODUCTO");

        } else if (request.getParameter("FOTO_PRODUCTO").equals("") && request.getParameter("NOMBREFOTO") != null) {
            //Si había foto y no se pone otra no se actualiza
            foto = request.getParameter("NOMBREFOTO");

        } else if (!request.getParameter("FOTO_PRODUCTO").equals("") && request.getParameter("NOMBREFOTO") == null) {
            //Si no había foto y se pone una se añade
            foto = request.getParameter("FOTO_PRODUCTO");
        }

        producto.setIdProducto(Integer.parseInt(id));
        producto.setNombre(nombre);
        producto.setPrecioCompra(Double.parseDouble(precioCompra));
        producto.setPrecioVenta(Double.parseDouble(precioVenta));
        producto.setPorcentajeOferta(Double.parseDouble(porcentajeOferta));
        producto.setDescripcion(descripcion);
        producto.setFoto(foto);
        producto.setActivo(activo);

        int actualizado = productoDAO.update(producto);

        request.setAttribute("MENSAJE_PROD", "El producto se ha actualizado.");
        request.setAttribute("ELIMINAR", actualizado);

        return webRedirect;
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) {

        String webRedirect;
//        webRedirect = "/index.jsp";
        webRedirect = "/archivosJSP/adminMenu.jsp";

        String id = request.getParameter("ID_PRODUCTO");
        ProductoDAO productoDAO = new ProductoDAO();

        int filaEliminada = productoDAO.delete(Integer.parseInt(id));

        request.setAttribute("MENSAJE_PROD", "El pedido se ha eliminado.");
        request.setAttribute("ELIMINAR", filaEliminada);

        return webRedirect;
    }

    private String addGetID(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        String webRedirect;
        webRedirect = "/index.jsp";
//        webRedirect = "/Ecommerce/Controlador?ACTION=PRODUCTO.PRODACTIVO";
//        webRedirect = "/archivosJSP/todosProductos.jsp";

        String mensajeRespuesta = "";

        Pedido pedido = new Pedido();
        PedidoDAO pedidoDAO = new PedidoDAO();
        String fechaPedido = request.getParameter("FECHA_PEDIDO");
        String idUsuario = request.getParameter("ID_USUARIO");
        pedido.setFechaPedido(fechaPedido);
        pedido.setIdUsuario(Integer.parseInt(idUsuario));

        if (pedidoDAO.add(pedido) == 1) {
            ArrayList<Pedido> lstPedidos = pedidoDAO.findAll(pedido);

            if (lstPedidos.isEmpty()) {
                mensajeRespuesta = "No hay pedidos";
            } else {
                //Ultimo pedido que concuerde con los datos
                int idPedido = lstPedidos.get(lstPedidos.size() - 1).getIdPedido();
                session.setAttribute("ID_PEDIDO", idPedido);
            }
            mensajeRespuesta = "Pedido creado";
        } else {
            mensajeRespuesta = "Pedido NO creado";
        }
        return webRedirect;
    }
}
