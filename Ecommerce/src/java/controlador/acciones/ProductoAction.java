package controlador.acciones;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.productos.Producto;
import modelo.productos.ProductoDAO;

/**
 *
 * @author guill
 */
public class ProductoAction {

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

            case "PRODACTIVO":
                pagDestino = activoS(request, response);
                break;

            case "NOMBREASC":
                pagDestino = nombreAsc(request, response);
                break;

            case "NOMBREDESC":
                pagDestino = nombreDesc(request, response);
                break;

            case "PRECIOASC":
                pagDestino = precioAsc(request, response);
                break;

            case "PRECIODESC":
                pagDestino = precioDesc(request, response);
                break;

            case "MOSTRARNOMBRE":
                pagDestino = filtrarNombre(request, response);
                break;

            case "PRODOFERTA":
                pagDestino = prodOferta(request, response);
                break;

        }

        return pagDestino;
    }

    private String add(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
        webRedirect = "/archivosJSP/adminMenu.jsp";

        String mensajeRespuesta = "";
        String activo = "";
        Producto producto = new Producto();
        ProductoDAO productoDAO = new ProductoDAO();

        String nombre = request.getParameter("NOMBRE_PRODUCTO");
        String precioCompra = request.getParameter("PRECIO_COMPRA");
        String precioVenta = request.getParameter("PRECIO_VENTA");
        String porcentajeOferta = request.getParameter("PORCENTAJE_OFERTA");
        String descripcion = request.getParameter("DESCRIPCION");
        String foto = request.getParameter("FOTO_PRODUCTO");
        if (request.getParameter("PRODUCTO_ACTIVO") == null) {
            activo = "S";
        } else {
            activo = request.getParameter("PRODUCTO_ACTIVO");
        }

        producto.setNombre(nombre);
        producto.setPrecioCompra(Double.parseDouble(precioCompra));
        producto.setPrecioVenta(Double.parseDouble(precioVenta));
        producto.setPorcentajeOferta(Double.parseDouble(porcentajeOferta));
        producto.setDescripcion(descripcion);
        producto.setFoto(foto);
        producto.setActivo(activo);

        if (productoDAO.add(producto) == 1) {
            mensajeRespuesta = "Producto añadido";
        } else {
            mensajeRespuesta = "Producto NO añadido";
        }
        request.setAttribute("MENSAJE_PROD", mensajeRespuesta);

        return webRedirect;
    }

    private String list(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
        webRedirect = "/archivosJSP/adminMenu.jsp";

        ProductoDAO productoDAO = new ProductoDAO();
        ArrayList<Producto> lstProductos = productoDAO.findAll(null);

        String mensajeRespuesta = "";
        if (lstProductos.isEmpty()) {
            mensajeRespuesta = "No hay productos en la tabla";
            request.setAttribute("MENSAJE_PROD", mensajeRespuesta);
        } else {
            request.setAttribute("MENSAJE_PROD", lstProductos);
        }
        request.setAttribute("MENSAJE_PROD", lstProductos);

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

        request.setAttribute("MENSAJE_PROD", "El producto se ha eliminado.");
        request.setAttribute("ELIMINAR", filaEliminada);

        return webRedirect;
    }

    private String activoS(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
//        webRedirect = "/archivosJSP/adminMenu.jsp";
        webRedirect = "/archivosJSP/todosProductos.jsp";

        ProductoDAO productoDAO = new ProductoDAO();
        Producto producto = new Producto();

        producto.setActivo("S");

        ArrayList<Producto> lstProdActivos = productoDAO.findAll(producto);

        String mensajeRespuesta = "";
        if (lstProdActivos.isEmpty()) {
            mensajeRespuesta = "No hay productos activos en la tabla";
            request.setAttribute("MENSAJE_PROD", mensajeRespuesta);
        } else {
            request.setAttribute("MENSAJE_PROD", lstProdActivos);
        }

        return webRedirect;
    }

    private String nombreAsc(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
//        webRedirect = "/archivosJSP/adminMenu.jsp";
        webRedirect = "/archivosJSP/todosProductos.jsp";

        ProductoDAO productoDAO = new ProductoDAO();

        ArrayList<Producto> lstProdActivos = productoDAO.queryNombreAsc();

        String mensajeRespuesta = "";
        if (lstProdActivos.isEmpty()) {
            mensajeRespuesta = "No hay productos activos en la tabla";
            request.setAttribute("MENSAJE_PROD", mensajeRespuesta);
        } else {
            request.setAttribute("MENSAJE_PROD", lstProdActivos);
            request.setAttribute("ORDENACION", 0);
        }

        return webRedirect;
    }

    private String nombreDesc(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
//        webRedirect = "/archivosJSP/adminMenu.jsp";
        webRedirect = "/archivosJSP/todosProductos.jsp";

        ProductoDAO productoDAO = new ProductoDAO();

        ArrayList<Producto> lstProdActivos = productoDAO.queryNombreDesc();

        String mensajeRespuesta = "";
        if (lstProdActivos.isEmpty()) {
            mensajeRespuesta = "No hay productos activos en la tabla";
            request.setAttribute("MENSAJE_PROD", mensajeRespuesta);
        } else {
            request.setAttribute("MENSAJE_PROD", lstProdActivos);
            request.setAttribute("ORDENACION", 1);
        }

        return webRedirect;
    }

    private String precioAsc(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
//        webRedirect = "/archivosJSP/adminMenu.jsp";
        webRedirect = "/archivosJSP/todosProductos.jsp";

        ProductoDAO productoDAO = new ProductoDAO();

        ArrayList<Producto> lstProdActivos = productoDAO.queryPrecioAsc();

        String mensajeRespuesta = "";
        if (lstProdActivos.isEmpty()) {
            mensajeRespuesta = "No hay productos activos en la tabla";
            request.setAttribute("MENSAJE_PROD", mensajeRespuesta);
        } else {
            request.setAttribute("MENSAJE_PROD", lstProdActivos);
            request.setAttribute("ORDENACION", 2);
        }

        return webRedirect;
    }

    private String precioDesc(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
//        webRedirect = "/archivosJSP/adminMenu.jsp";
        webRedirect = "/archivosJSP/todosProductos.jsp";

        ProductoDAO productoDAO = new ProductoDAO();

        ArrayList<Producto> lstProdActivos = productoDAO.queryPrecioDesc();

        String mensajeRespuesta = "";
        if (lstProdActivos.isEmpty()) {
            mensajeRespuesta = "No hay productos activos en la tabla";
            request.setAttribute("MENSAJE_PROD", mensajeRespuesta);
        } else {
            request.setAttribute("MENSAJE_PROD", lstProdActivos);
            request.setAttribute("ORDENACION", 3);
        }

        return webRedirect;
    }

    private String filtrarNombre(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
//        webRedirect = "/archivosJSP/adminMenu.jsp";
        webRedirect = "/archivosJSP/todosProductos.jsp";

        String nombre = request.getParameter("NOMBRE_FILTRO");

        ProductoDAO productoDAO = new ProductoDAO();

        ArrayList<Producto> lstProdActivos = productoDAO.queryFiltrarNombre(nombre);

        String mensajeRespuesta = "";
        if (lstProdActivos.isEmpty()) {
            mensajeRespuesta = "No hay productos relacionados.";
            request.setAttribute("MENSAJE_PROD", mensajeRespuesta);
        } else {
            request.setAttribute("FILTRO", nombre);
            request.setAttribute("MENSAJE_PROD", lstProdActivos);
        }

        return webRedirect;
    }

    private String prodOferta(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect = "";
        if (((String) request.getParameter("FROM")).equals("INDEX")) {
            webRedirect = "/index.jsp";
        } else if (((String) request.getParameter("FROM")).equals("PRODUCTOS")) {
            webRedirect = "/archivosJSP/todosProductos.jsp";
        }
//        webRedirect = "/archivosJSP/adminMenu.jsp";

        ProductoDAO productoDAO = new ProductoDAO();

        ArrayList<Producto> lstProdActivos = productoDAO.queryOferta();

        String mensajeRespuesta = "";
        if (lstProdActivos.isEmpty()) {
            mensajeRespuesta = "No hay productos activos en la tabla";
            request.setAttribute("MENSAJE_PROD", mensajeRespuesta);
        } else {
            request.setAttribute("MENSAJE_PROD", lstProdActivos);
            request.setAttribute("ORDENACION", 4);
        }

        return webRedirect;
    }

}
