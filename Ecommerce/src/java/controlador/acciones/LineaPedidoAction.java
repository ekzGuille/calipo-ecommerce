package controlador.acciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.lineaPedido.LineaPedido;
import modelo.lineaPedido.LineaPedidoDAO;

/**
 *
 * @author guill
 */
public class LineaPedidoAction {

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
        webRedirect = "/archivosJSP/todosProductos.jsp";

        String mensajeRespuesta = "";
        LineaPedido lineaPedido = new LineaPedido();
        LineaPedidoDAO lineaPedidoDAO = new LineaPedidoDAO();

        String costeUnitario = request.getParameter("COSTE_UNITARIO");
        String cantidad = request.getParameter("CANTIDAD");
        String subtotal = request.getParameter("SUBTOTAL");
        String idPedido = request.getParameter("ID_PEDIDO");
        String idProducto = request.getParameter("ID_PRODUCTO");

        lineaPedido.setCosteUnitario(Double.parseDouble(costeUnitario));
        lineaPedido.setCantidad(Integer.parseInt(cantidad));
        lineaPedido.setSubtotal(Double.parseDouble(subtotal));
        lineaPedido.setIdPedido(Integer.parseInt(idPedido));
        lineaPedido.setIdProducto(Integer.parseInt(idProducto));

        if (lineaPedidoDAO.add(lineaPedido) == 1) {
            mensajeRespuesta = "Linea de pedido creada";
        } else {
            mensajeRespuesta = "Linea de pedido NO creada";
        }
        request.setAttribute("MENSAJE_LINEA", mensajeRespuesta);

        return webRedirect;
    }

    private String list(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
        webRedirect = "/archivosJSP/todosProductos";

        LineaPedidoDAO lineaPedidoDAO = new LineaPedidoDAO();
        ArrayList<LineaPedido> lstLineaPedido = lineaPedidoDAO.findAll(null);

        String mensajeRespuesta = "";
        if (lstLineaPedido.isEmpty()) {
            mensajeRespuesta = "No hay lineas de pedidos";
            request.setAttribute("MENSAJE_LINEA", mensajeRespuesta);
        } else {
            request.setAttribute("MENSAJE_LINEA", lstLineaPedido);
        }
        request.setAttribute("MENSAJE_LINEA", lstLineaPedido);

        return webRedirect;
    }

    private String mod(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        String webRedirect;
//        webRedirect = "/index.jsp";
        webRedirect = "/Controlador?ACTION=PRODUCTO.PRODACTIVO";

        LineaPedido lineaPedido = new LineaPedido();
        LineaPedidoDAO lineaPedidoDAO = new LineaPedidoDAO();

        String idLineaPedido = request.getParameter("ID_LINEA_PEDIDO");
        String costeUnitario = request.getParameter("COSTE_UNITARIO");
        String cantidad = request.getParameter("CANTIDAD");
        String subtotal = request.getParameter("SUBTOTAL");
        String idPedido = request.getParameter("ID_PEDIDO");
        String idProducto = request.getParameter("ID_PRODUCTO");

        lineaPedido.setIdLineaPedido(Integer.parseInt(idLineaPedido));
        lineaPedido.setCosteUnitario(Double.parseDouble(costeUnitario));
        lineaPedido.setCantidad(Integer.parseInt(cantidad));
        lineaPedido.setSubtotal(Double.parseDouble(subtotal));
        lineaPedido.setIdPedido(Integer.parseInt(idPedido));
        lineaPedido.setIdProducto(Integer.parseInt(idProducto));

        HashMap<Integer, Integer> lstProductoCantidad = (HashMap<Integer, Integer>) session.getAttribute("ARRAY_PEDIDO");

        //Guardar en el la clave la ID de producto
        //Guardar en el valor la cantidad de dicho producto
        lstProductoCantidad.put(Integer.parseInt(idProducto), Integer.parseInt(cantidad));

//        session.setAttribute("ID_LINEA_PEDIDO", idLineaPedido);
        //Sobrescribir el array
        session.setAttribute("ARRAY_PEDIDO", lstProductoCantidad);

        int actualizado = lineaPedidoDAO.update(lineaPedido);

        request.setAttribute("MENSAJE_LINEA", "El producto se ha actualizado.");
        request.setAttribute("ELIMINAR", actualizado);

        return webRedirect;
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) {

        String webRedirect;
//        webRedirect = "/index.jsp";
        webRedirect = "/archivosJSP/todosProductos.jsp";

        String id = request.getParameter("ID_LINEA_PEDIDO");
        LineaPedidoDAO lineaPedidoDAO = new LineaPedidoDAO();

        int filaEliminada = lineaPedidoDAO.delete(Integer.parseInt(id));

        request.setAttribute("MENSAJE_LINEA", "El producto se ha eliminado.");
        request.setAttribute("ELIMINAR", filaEliminada);

        return webRedirect;
    }

    private String addGetID(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String webRedirect;
//        webRedirect = "/index.jsp";
        webRedirect = "/Controlador?ACTION=PRODUCTO.PRODACTIVO";

        String mensajeRespuesta = "";
        LineaPedido lineaPedido = new LineaPedido();
        LineaPedidoDAO lineaPedidoDAO = new LineaPedidoDAO();

        String costeUnitario = request.getParameter("COSTE_UNITARIO");
        String cantidad = request.getParameter("CANTIDAD");
        String subtotal = request.getParameter("SUBTOTAL");
        String idPedido = request.getParameter("ID_PEDIDO");
        String idProducto = request.getParameter("ID_PRODUCTO");

        lineaPedido.setCosteUnitario(Double.parseDouble(costeUnitario));
        lineaPedido.setCantidad(Integer.parseInt(cantidad));
        lineaPedido.setSubtotal(Double.parseDouble(subtotal));
        lineaPedido.setIdPedido(Integer.parseInt(idPedido));
        lineaPedido.setIdProducto(Integer.parseInt(idProducto));

        if (lineaPedidoDAO.add(lineaPedido) == 1) {
            ArrayList<LineaPedido> lstLineaPedido = lineaPedidoDAO.findAll(lineaPedido);

            if (lstLineaPedido.isEmpty()) {
                mensajeRespuesta = "No hay pedidos";
            } else {

                int idLineaPedido = lstLineaPedido.get(0).getIdLineaPedido();
                int cantidadPedido = lstLineaPedido.get(0).getCantidad();
                int idProductoLP = lstLineaPedido.get(0).getIdProducto();

                //Obetener el valor del hashmap de sesión, modificarlo y sobreescribirlo
                HashMap<Integer, Integer> lstProductoCantidad = (HashMap<Integer, Integer>) session.getAttribute("ARRAY_PEDIDO");

                //Guardar en el la clave la ID de producto
                //Guardar en el valor la cantidad de dicho producto
                lstProductoCantidad.put(idProductoLP, cantidadPedido);

                session.setAttribute("ID_LINEA_PEDIDO", idLineaPedido);
//                session.setAttribute("CANTIDAD_LINEA_PEDIDO", cantidadPedido);
                //Sobrescribir el array
                session.setAttribute("ARRAY_PEDIDO", lstProductoCantidad);

            }
            mensajeRespuesta = "Linea de Pedido creada";
        } else {
            mensajeRespuesta = "Linea de Pedido NO creada";
        }
        request.setAttribute("MENSAJE_LINEA", mensajeRespuesta);
//        request.setAttribute("MENSAJE_PROD", null);

        return webRedirect;
    }

}
