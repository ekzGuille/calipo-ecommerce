package controlador.acciones;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.carro.Carro;
import modelo.carro.CarroDAO;

/**
 *
 * @author guill
 */
public class CarroAction {

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String pagDestino = "";
        String actionReceived = request.getParameter("ACTION");

        String[] actionSplit = actionReceived.split("\\.");

        switch (actionSplit[1]) {
            case "OBTENERPEDIDO":
                pagDestino = obtenerPedido(request, response);
                break;
        }

        return pagDestino;
    }

    private String obtenerPedido(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
//        webRedirect = "/archivosJSP/todosProductos.jsp";
        webRedirect = "/archivosJSP/carroCompra.jsp";

        CarroDAO carroDAO = new CarroDAO();

        String idPedido = request.getParameter("ID_PEDIDO");

        ArrayList<Carro> lstCarro = carroDAO.findLineaPedido(Integer.parseInt(idPedido));
        String mensajeRespuesta = "";
        if (lstCarro.isEmpty()) {
            mensajeRespuesta = "No hay lineas de pedidos";
            request.setAttribute("MENSAJE_LINEA", mensajeRespuesta);
        } else {
            request.setAttribute("MENSAJE_LINEA", lstCarro);
        }
        request.setAttribute("MENSAJE_LINEA", lstCarro);

        return webRedirect;
    }
}
