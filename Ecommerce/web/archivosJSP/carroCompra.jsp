<%-- 
    Document   : signup
    Created on : 17-may-2018, 18:59:03
    Author     : guill
--%>

<%@page import="modelo.carro.Carro"%>
<%@page import="java.util.HashMap"%>
<%@page import="modelo.pedidos.Pedido"%>
<%@page import="modelo.lineaPedido.LineaPedido"%>
<%@page import="modelo.productos.Producto"%>
<%@page import="modelo.usuarios.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Su carro de la compra</title>
        <meta charset="UTF-8">
        <link rel="icon" href="../images/favicon/faboat.png">

        <link rel="icon" href="images/favicon/faboat.png">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- JS -->
        <script src="../librerias/jquery-3.3.1.min.js"></script>
        <script src="../librerias/bootstrap-4.0.0/assets/js/vendor/popper.min.js"></script>
        <script src="../librerias/bootstrap-4.0.0/dist/js/bootstrap.min.js"></script>
        <script src="../js/script.js"></script>

        <script src="librerias/jquery-3.3.1.min.js"></script>
        <script src="librerias/bootstrap-4.0.0/assets/js/vendor/popper.min.js"></script>
        <script src="librerias/bootstrap-4.0.0/dist/js/bootstrap.min.js"></script>
        <script src="js/script.js"></script>
        <!-- CSS -->
        <link rel="stylesheet" href="../css/normalize.css"/>
        <link rel="stylesheet" href="../librerias/bootstrap-4.0.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/style.css"/>

        <link rel="stylesheet" href="css/normalize.css"/>
        <link rel="stylesheet" href="librerias/bootstrap-4.0.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <%
            if (session.getAttribute("USUARIO_ROL") != null) {
                double costeTotal = 0;
                HashMap<Integer, Integer> lstProductoCantidad = null;
                if (session.getAttribute("ARRAY_PEDIDO") != null) {
                    lstProductoCantidad = (HashMap<Integer, Integer>) session.getAttribute("ARRAY_PEDIDO");
                }

        %>
        <!--Al efectuarse la compra se vacía de la sesión el pedido y el arra de lista pedidos-->

        <div class="container-fluid">
            <div class="jumbotron">
            </div>
            <!--Menu de navegacion--> 
            <nav class="navbar indexNav">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link"  href="index.jsp">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/Ecommerce/Controlador?ACTION=PRODUCTO.PRODACTIVO">Productos</a>
                    </li>
                    <!--                    <li class="nav-item">
                                            <a class="nav-link"  href="#">Sobre Nosotros</a>
                                        </li>-->
                    <%                        if (session.getAttribute("USUARIO_ROL") != null) {
                            int rol = (Integer) session.getAttribute("USUARIO_ROL");
                            if (rol == 2) {
                                //Registrado
                            } else if (rol == 1) {
                                //Admin
                    %>
                    <li class="nav-item">
                        <a class="nav-link"  href="archivosJSP/adminMenu.jsp">MENÚ ADMINISTRADOR</a>
                    </li>
                    <%
                            }
                        }
                    %>
                </ul>
                <%
                    if (session.getAttribute("USUARIO_LOG") != null) {
                %>
                <ul class="nav">
                    <!--                    <li class="nav-item">
                                            <a class="nav-link"  href="#">Mi Cuenta</a>
                                        </li>-->
                    <li class="nav-item">
                        <a class="nav-link carroCompra" href="#">
                            Carro de la compra
                        </a>
                    </li>
                    <li class="nav-item contadorProducto">
                        <span class="nav-link cantidadProducto">
                            <%
                                if (session.getAttribute("ARRAY_PEDIDO") != null) {

                                    out.println(lstProductoCantidad.size());
                                }
                            %>
                        </span>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="nombreUsuario"  href="#">
                            <%out.println(session.getAttribute("USUARIO_LOG"));
                                }%>
                        </a> 
                    </li>
                </ul>

                <%
                    if (session.getAttribute("USUARIO_LOG") != null) {
                %>
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="archivosJSP/logout.jsp">Log out</a>
                    </li>
                </ul>            
                <%
                } else {

                %>
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link"  href="archivosJSP/signup.jsp">Sign Up</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"  href="archivosJSP/login.jsp">Login</a>
                    </li>
                </ul>
                <%                                }
                %>
            </nav>
            <div id="contenido">
                <div class="container-fluid" id="productoList">
                    <%                        if (request.getAttribute("MENSAJE_LINEA") != null) {
                            if (request.getAttribute("MENSAJE_LINEA") instanceof ArrayList) {
                                if (!((ArrayList) request.getAttribute("MENSAJE_LINEA")).isEmpty()) {
                    %>
                    <!--<h5 id="tituloLstProducto">Listado total de Productos</h5>-->
                    <div id="tablaProducto">
                        <table class="table tablaCarrito">
                            <th></th>
                            <th></th>
                            <th>Nombre</th>
                            <th>Descripcion</th>
                            <th>Precio original</th>
                            <th>Coste unitario</th>
                            <th>Cantidad</th>
                            <th>Subtotal</th>

                            <%                                ArrayList<Carro> lstCarro = (ArrayList) request.getAttribute("MENSAJE_LINEA");
                            %>
                            <div class="row muestrarioProductos">
                                <%                            for (Carro carro : lstCarro) {
                                        costeTotal += carro.getSubtotalProducto();

                                %>

                                <tr id="fila<%=carro.getIdProducto()%>">
                                    <td class="idProducto"><%out.print(carro.getIdProducto());%></td>
                                    <td class="fotoProducto"><img src="../Ecommerce/images/productos/<%out.print(carro.getFotoProducto());%>" style="max-width: 50px !important;" alt="<%out.print(carro.getFotoProducto());%>" class="img-thumbnail"></td>
                                    <td class="nombreProducto"><%out.print(carro.getNombreProducto());%></td>
                                    <td class="descripcionProducto"><%out.print(carro.getDescripcionProducto());%></td>
                                    <%
                                        if (carro.getOfertaProducto() != 1) {
                                    %>
                                    <td class="costeProductoNoOferta"><%out.print(((int) (carro.getCosteUnitario() / (carro.getOfertaProducto()) * 100)) * 0.01);%> €</td>
                                    <%
                                    } else {
                                    %>
                                    <td></td>
                                    <%
                                        }
                                    %>
                                    <td class="costeProducto"><%out.print(carro.getCosteUnitario());%> €</td>
                                    <td class="cantidad"><%out.print(carro.getCantidadProducto());%> uds.</td>
                                    <td class="subtotalProducto"><%out.print(carro.getSubtotalProducto());%> €</td>
                                </tr>
                                <%
                                    }
                                %>
                            </div>
                        </table>

                        <div class="costeTotal"> 
                            TOTAL:
                            <span id="dineroTotal"><%out.println(costeTotal);%> €</span>
                        </div>
                        <div class="botonCompra">
                            <input type="button" value="Realizar Pedido" class="btn btn-dark comprar" onclick="terminarPedido()">
                        </div>
                    </div>
                    <%
                    } else {
                    %>
                    <h2 id="carroVacio">Carro vacío</h2>
                    <%
                                }
                            }
                        }
                    %>
                </div>
            </div>
            <div style="height: 50px"></div> 
            <footer>
                <div class="footer">
                    Guillermo Sesé Santos 1SI 2017-2018 &COPY;
                </div>
            </footer>
            <%            } else {
                    response.sendRedirect("../index.jsp");
                }
            %>
        </div>
    </body>
</html>
