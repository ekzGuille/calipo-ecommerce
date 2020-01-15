<%-- 
    Document   : index
    Created on : 15-may-2018, 9:48:50
    Author     : guill
--%>

<%@page import="modelo.productos.Producto"%>
<%@page import="java.util.HashMap"%>
<%@page import="modelo.lineaPedido.LineaPedido"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Main Page</title>
        <meta charset="UTF-8">
        <link rel="icon" href="images/favicon/faboat.png">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- JS -->
        <script src="librerias/jquery-3.3.1.min.js"></script>
        <script src="librerias/bootstrap-4.0.0/assets/js/vendor/popper.min.js"></script>
        <script src="librerias/bootstrap-4.0.0/dist/js/bootstrap.min.js"></script>
        <script src="js/script.js"></script>
        <!-- CSS -->
        <link rel="stylesheet" href="css/normalize.css"/>
        <link rel="stylesheet" href="librerias/bootstrap-4.0.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body> 
        <%

            if (request.getAttribute("MENSAJE_PROD") == null) {
        %>
        <script>
            document.location = "Controlador?ACTION=PRODUCTO.PRODOFERTA&FROM=INDEX";
        </script>
        <%
            }

            HashMap<Integer, Integer> lstProductoCantidad;
            if (session.getAttribute("ARRAY_PEDIDO") == null) {
                lstProductoCantidad = new HashMap();
                session.setAttribute("ARRAY_PEDIDO", lstProductoCantidad);
            } else {
                lstProductoCantidad = (HashMap<Integer, Integer>) session.getAttribute("ARRAY_PEDIDO");
            }

            if (session.getAttribute("ID_PEDIDO") == null && session.getAttribute("USUARIO_ID") != null) {
        %>
        <script>
            crearPedido(<%=session.getAttribute("USUARIO_ID")%>);
        </script>
        <%

            }
        %>
        <div class="container-fluid">
            <div class="jumbotron">
            </div>
            <!--Menu de navegacion--> 
            <nav class="navbar indexNav">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link"  href="#">Inicio</a>
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
                    <%
                        if (session.getAttribute("ID_PEDIDO") != null) {

                    %>
                    <li class="nav-item">
                        <a class="nav-link carroCompra" href="#" onclick="crearCarro(<%=session.getAttribute("ID_PEDIDO")%>)">
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
                    <%}%>
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

            <!--Contenedor del texto-->
            <div class="container">
                <%                        if (request.getAttribute("MENSAJE_PROD") != null) {
                        if (request.getAttribute("MENSAJE_PROD") instanceof ArrayList) {

                %>
                <h3 style="margin: 2% 0 2% 0"> Disfrute de nuestras ofertas, si quiere ver el catálogo completo haga click <a style="color: grey;" href="/Ecommerce/Controlador?ACTION=PRODUCTO.PRODACTIVO">aquí</a>.</h3>
                </h3>
                <div id="tablaProducto">
                    <%                                ArrayList<Producto> lstProductos = (ArrayList) request.getAttribute("MENSAJE_PROD");
                    %>
                    <div class="row muestrarioProductos">
                        <%                            for (Producto producto : lstProductos) {
                        %>

                        <div class=" col-lg-4 col-md-4 cuadriculaProducto" id="producto<%=producto.getIdProducto()%>">
                            <div class="cuadriculaWrapper">
                                <div class="fotoDescripWrapper" onmouseover="mostrarFotoDesc('fotoProd<%=producto.getIdProducto()%>', 'descProd<%=producto.getIdProducto()%>')" onmouseout="quitarFotoDesc('fotoProd<%=producto.getIdProducto()%>', 'descProd<%=producto.getIdProducto()%>')">
                                    <div class="foto fotoDescrip" id="fotoProd<%=producto.getIdProducto()%>">
                                        <img src="../Ecommerce/images/productos/<%out.print(producto.getFoto());%>" style="max-width: 200px !important;" alt="<%out.print(producto.getFoto());%>" class="img-thumbnail">
                                    </div>
                                    <div class="descripcion" id="descProd<%=producto.getIdProducto()%>">
                                        <%out.print(producto.getDescripcion());%>
                                    </div>
                                </div>
                                <div class="nombre_prod nombre_prodTabla">
                                    <%out.print(producto.getNombre());%>
                                </div>
                                <span>
                                    <div class="precioVenta precioVentaTabla" id="descuento<%=producto.getIdProducto()%>">
                                        <%out.print(producto.getPrecioVenta());%> €
                                    </div>
                                    <%
                                        if (producto.getPorcentajeOferta() != 1) {
                                    %>
                                    <script>
                                        var textoCambiarClase = document.getElementById('descuento<%=producto.getIdProducto()%>');
                                        textoCambiarClase.setAttribute('class', 'precioVenta precioVentaTabla precioViejo');
                                    </script>
                                    <div class="precioVentaRebaja precioVentaTablaRebaja">
                                        <%out.print(((int) ((producto.getPrecioVenta() * producto.getPorcentajeOferta()) * 100)) * 0.01);%> €
                                    </div>
                                    <span class="descDescuento">Descuento del <%out.println((int) (100 - (producto.getPorcentajeOferta() * 100)));%>%</span>

                                    <%
                                        }
                                    %>
                                </span>
                                <%
                                    if (session.getAttribute("USUARIO_LOG") != null) {

                                %>
                                <div class="botonAddCarro">
                                    <input type="button" value="Añadir al Carro" class="btn btn-success" onclick="addLineaPedido('<%=producto.getIdProducto()%>', '<%=((int) ((producto.getPrecioVenta() * producto.getPorcentajeOferta()) * 100)) * 0.01%>', '<%=session.getAttribute("ID_PEDIDO")%>', '<%=session.getAttribute("ID_LINEA_PEDIDO")%>')">
                                    <%
                                        if (lstProductoCantidad.containsKey(producto.getIdProducto())) {
                                    %>
                                    <span class="nav-link cantidadIndividual" id="comprarProducto<%=producto.getIdProducto()%>"><%=lstProductoCantidad.get(producto.getIdProducto())%></span>
                                    <%
                                    } else {
                                    %>
                                    <span class="nav-link cantidadIndividual" id="iniciocomprarProducto<%=producto.getIdProducto()%>">0</span>
                                    <%
                                        }
                                    %>
                                </div>
                                <%
                                } else {
                                %>
                                <div class="botonAddCarro">
                                    <input style="color: #000000;" type="button" value="Añadir al Carro" class="btn btn-success" onclick="(function () {
                                                document.location = 'archivosJSP/login.jsp';
                                            })()">
                                </div>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
                <div class="container">
                    <h3>¿A qué nos dedicamos?</h3>
                    <div class="row">
                        <p>
                            <strong>Calypso Marine Instruments is a spin-off of Prodeo Ingeniería</strong>, an engineering team professionally focused on industrial design services. Prodeo supplies engineering design services (mechanical, electrical, electronics, instrumentation...) for industrial plants.

                            Our newest product, Ultrasonic Portable, is the result of hundreds of engineering hours supported by world class sailing pros. A great challenge and very fulfilling experience!
                        </p>
                    </div>
                    <div class="row">
                        <div class="form-group col-lg-3 col-md-3"></div>
                        <div id="imagesIndex"  class="form-group col-lg-6 col-md-6" style="max-width:500px">
                            <img class="mySlides img-fluid imgIndex" src="images/background/boat1.jpg" style="width:100%">
                            <img class="mySlides img-fluid imgIndex" src="images/background/boat2.jpg" style="width:100%">
                            <img class="mySlides img-fluid imgIndex" src="images/background/boat3.jpg" style="width:100%">
                            <img class="mySlides img-fluid imgIndex" src="images/background/boat4.jpg" style="width:100%">
                            <img class="mySlides img-fluid imgIndex" src="images/background/boat5.jpg" style="width:100%">
                        </div>
                        <div class="form-group col-lg-3 col-md-3"></div>
                    </div>
                    <div class="row">
                        <p>
                            Our new <strong>Ultrasonic Portable Wind Instrument</strong> and data logger strives to be a disruptive innovation, bringing top-notch ultrasonic wind measurement technology to the mobile world. Affordable, portable, with no moving parts, IPX8, easy to install, accurate, and open to third parties apps.

                            The Calypso Ultrasonic Anemometer brings astonishing technology to a wider range of users, from the sailor to the farmer. Get one portable piece of hardware today, tons of posibilites to come!
                        </p>
                    </div>
                </div>
            </div>
            <footer>
                Guillermo Sesé Santos 1SI 2017-2018 &COPY;
            </footer>
        </div>
    </body>
</html>


