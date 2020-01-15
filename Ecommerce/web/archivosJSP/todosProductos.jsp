<%-- 
    Document   : signup
    Created on : 17-may-2018, 18:59:03
    Author     : guill
--%>

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
        <title>Explora todos los productos</title>
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
            if (request.getAttribute("MENSAJE_PROD") == null) {
        %>
        <script>
            document.location = '/Ecommerce/Controlador?ACTION=PRODUCTO.PRODACTIVO';
        </script>
        <%
            }
            //Guardar en el la clave la ID de producto
            //Guardar en el valor la cantidad de dicho producto
            HashMap<Integer, Integer> lstProductoCantidad = null;
            if (session.getAttribute("USUARIO_ID") != null) {
                if (session.getAttribute("ARRAY_PEDIDO") != null) {

                    lstProductoCantidad = (HashMap<Integer, Integer>) session.getAttribute("ARRAY_PEDIDO");
                    if (session.getAttribute("ID_PEDIDO") == null) {
        %>
        <script>
            crearPedido(<%=session.getAttribute("USUARIO_ID")%>);
        </script>
        <%

                    }
                }
            }

        %>
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
                        <a class="nav-link"  href="#">Productos</a>
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
            <hr>

            <%
                if (request.getAttribute("ORDENACION") != null) {
                    switch (((Integer) request.getAttribute("ORDENACION"))) {
                        case 0:
            %>
            <style>
                #nombreAsc{
                    /*background-color: #A0A0A0;*/
                    background-color: #7D7D7D;
                }
            </style>
            <%
                    break;
                case 1:
            %>
            <style>
                #nombreDesc{
                    /*background-color: #A0A0A0;*/
                    background-color: #7D7D7D;
                }
            </style>
            <%
                    break;
                case 2:
            %>
            <style>
                #precioAsc{
                    /*background-color: #A0A0A0;*/
                    background-color: #7D7D7D;
                }
            </style>
            <%
                    break;
                case 3:
            %>
            <style>
                #precioDesc{
                    /*background-color: #A0A0A0;*/
                    background-color: #7D7D7D;
                }
            </style>
            <%
                            break;
                case 4:
            %>
            <style>
                #ofertas{
                    /*background-color: #A0A0A0;*/
                    background-color: #7D7D7D;
                }
            </style>
            <%
                            break;
                        default:

                    }
                }

            %>
            <nav class="navbar menuFiltrado" id="filtro">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link textoFiltrado" id="nombreAsc" title="Orden alfabético A-Z" href="/Ecommerce/Controlador?ACTION=PRODUCTO.NOMBREASC">A - Z</a>
                    </li>
                    <div style="width: 2%"></div>
                    <li class="nav-item">
                        <a class="nav-link textoFiltrado" id="nombreDesc" title="Orden alfabético inverso Z-A" href="/Ecommerce/Controlador?ACTION=PRODUCTO.NOMBREDESC">Z - A</a>
                    </li>
                    <div style="width: 2%"></div>
                    <li class="nav-item">
                        <a class="nav-link textoFiltrado" id="precioDesc" title="Más caros" href="/Ecommerce/Controlador?ACTION=PRODUCTO.PRECIODESC">Precio más alto</a>
                    </li>
                    <div style="width: 2%"></div>
                    <li class="nav-item">
                        <a class="nav-link textoFiltrado" id="precioAsc" title="Más baratos" href="/Ecommerce/Controlador?ACTION=PRODUCTO.PRECIOASC">Precio más bajo</a>
                    </li>
                    <div style="width: 2%"></div>
                    <li class="nav-item">
                        <a class="nav-link textoFiltrado" id="ofertas" title="Más baratos" href="/Ecommerce/Controlador?ACTION=PRODUCTO.PRODOFERTA&FROM=PRODUCTOS">Ofertas disponibles</a>
                    </li>
                    <div style="width: 4%"></div>
                    <li class="nav-item">
                        <input type="text" id="busquedaProducto" class="form-control" placeholder="Producto a buscar" >
                    </li>
                    <div style="width: 2%"></div>
                    <li class="nav-item">
                        <input type="button" value="Buscar" class="btn btn-secondary buscarProducto" onclick="buscarProducto()">
                    </li>
                    <div style="width: 4%"></div>
                    <li class="nav-item">
                    </li>
                    <li class="nav-item">
                        <input type="button" value="Restablecer filtro" class="btn btn-dark comprar" onclick="restablecerFiltros()">
                    </li>
                </ul>
            </nav>
            <div id="contenido">
                <%                    if (request.getAttribute("FILTRO") != null) {
                %>
                <div id = "filtradoPor">Productos relacionados con: <span id="nombreFiltro"><%=(String) request.getAttribute("FILTRO")%></span></div>
                    <%
                        }
                    %>
                <div class="container" id="productoList">
                    <%                        if (request.getAttribute("MENSAJE_PROD") != null) {
                            if (request.getAttribute("MENSAJE_PROD") instanceof ArrayList) {

                    %>
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
                        } else if (request.getAttribute("MENSAJE_PROD") instanceof String) {
                        %>
                        <h3 style="margin-top: 3%; margin-left: 2%;"><%=((String) request.getAttribute("MENSAJE_PROD"))%></h3>
                        <div style="height: 280px"></div> 
                        <%
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
            </div>
        </div>
    </body>
</html>
