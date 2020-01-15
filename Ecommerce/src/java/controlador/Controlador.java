/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.acciones.CarroAction;
import controlador.acciones.LineaPedidoAction;
import controlador.acciones.PedidoAction;
import controlador.acciones.ProductoAction;
import controlador.acciones.UsuarioAction;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guill
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String pagDestino = "";
        String actionReceived = request.getParameter("ACTION");

        String[] actionSplit = actionReceived.split("\\.");

        switch (actionSplit[0]) {
            case "USUARIO":
                pagDestino = new UsuarioAction().execute(request, response);
                break;

            case "PRODUCTO":
                pagDestino = new ProductoAction().execute(request, response);
                break;
                
            case "PEDIDO":
                pagDestino = new PedidoAction().execute(request, response);
                break;
                
            case "LINEAPEDIDO":
                pagDestino = new LineaPedidoAction().execute(request, response);
                break;
                
            case "CARRO":
                pagDestino = new CarroAction().execute(request, response);
                break;
        }

        request.getRequestDispatcher(pagDestino).forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
