<%-- 
    Document   : signup
    Created on : 17-may-2018, 18:59:03
    Author     : guill
--%>

<%@page import="modelo.productos.Producto"%>
<%@page import="modelo.usuarios.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Menu Administrador</title>
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
                if ((Integer) session.getAttribute("USUARIO_ROL") == 1) {
                    //Admin
        %>
        <div class="container-fluid">
            <nav class="navbar">
                <div class="navbar-header">
                    <a class="navbar-brand headerNav" href="#">Administrador: <%out.println(session.getAttribute("USUARIO_LOG"));%></a>
                </div>
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/Ecommerce/index.jsp">Inicio</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">USUARIOS</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" onclick="mostrarObjeto('usuarioAdd'); estadoInicial('formularioUser')" href="#">Alta de nuevo Usuario</a>
                            <a class="dropdown-item" onclick="mostrarObjeto('usuarioList'); estadoInicial('formularioProducto')" href="/Ecommerce/Controlador?ACTION=USUARIO.LISTAR">Mostrar todos los Usuarios</a>
                            <!--<a class="dropdown-item" href="#">Link 3</a>-->
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">PRODUCTOS</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" onclick="mostrarObjeto('productoAdd')" href="#">Alta de nuevo Producto</a>
                            <a class="dropdown-item" onclick="mostrarObjeto('productoList')" href="/Ecommerce/Controlador?ACTION=PRODUCTO.LISTAR">Mostrar todos los Productos</a>
                            <!--<a class="dropdown-item" href="#">Link 3</a>-->
                        </div>
                    </li>
                </ul>
            </nav>
            <div id="contenido">
                <div class="container-fluid" id="usuarioAdd" style="display:none;">
                    <h5 id="tituloAddUsuario" class="tituloAdmin">Añadir usuario</h5>
                    <form action="" method="post" role="form" id="formularioUser">
                        <div class="row">
                            <div class="form-group col-lg-1 col-md-1"></div>
                            <input type="hidden" name="ID_USUARIO" id="ID_USUARIO"/>
                            <div class="form-group col-lg-3 col-md-3">
                                <label for="NOMBRE">Nombre</label>
                                <input type="text" name="NOMBRE" id="NOMBRE" class="form-control" placeholder="Nombre" required="required"/>
                            </div>
                            <div class="form-group col-lg-3 col-md-3">
                                <label for="APELLIDO_1">Primer apellido</label>
                                <input type="text" name="APELLIDO_1" id="APELLIDO_1" class="form-control" placeholder="Primer Apellido" required="required"/>
                            </div>
                            <div class="form-group col-lg-3 col-md-3">
                                <label for="APELLIDO_2">Segundo apellido</label>
                                <input type="text" name="APELLIDO_2" id="APELLIDO_2" class="form-control" placeholder="Segundo Apellido" required="required"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-1 col-md-1"></div>
                            <div class="form-group col-lg-4 col-md-4">
                                <label for="FECHA_NACIMIENTO">Fecha de nacimiento</label>
                                <input type="date" name="FECHA_NACIMIENTO" id="FECHA_NACIMIENTO" class="form-control" placeholder="01/01/1900" required="required"/>
                            </div>
                            <div class="form-group col-lg-6 col-md-6">
                                <label for="DIRECCION_ENVIO">Dirección de envío</label>
                                <input type="text" name="DIRECCION_ENVIO" id="DIRECCION_ENVIO" class="form-control" placeholder="C/La Azucena nº 203, portal 23, 3ºA" required="required"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-1 col-md-1"></div>
                            <div class="form-group col-lg-4 col-md-4">
                                <label for="EMAIL">Correo electrónico</label>
                                <input type="email" name="EMAIL" id="EMAIL" class="form-control" placeholder="Email" required="required"/>
                            </div>
                            <div class="form-group col-lg-3 col-md-3">
                                <label for="PASSWORD">Contraseña</label>
                                <input type="password" name="PASSWORD" id="PASSWORD" class="form-control" placeholder="Contraseña" required="required"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-1 col-md-1"></div>
                            <div class="form-group col-lg-1 col-md-1">
                                <label for="ROL">ROL</label>
                                <input type="number" min="1" max="2" name="ROL" id="ROL" class="form-control" placeholder="2" required="required">
                            </div>
                            <div class="form-group col-lg-1 col-md-1">
                                <label for="ACTIVO">ACTIVO</label>
                                <input type="text" minlength="1" maxlength="1" name="ACTIVO" id="ACTIVO" class="form-control" placeholder="S/N" required="required">
                            </div>
                        </div>
                        <div class="btnMenuAdmin">
                            <button type="submit" id="btnRegistrarse" class="btnAceptar btn btnAdmin"></button>
                            <button type="button" class="btnCancelar btn btnAdmin" onclick="document.location = 'archivosJSP/adminMenu.jsp';" class="btn">Cancelar</button>
                        </div>
                    </form>
                    <div style="height: 200px;"></div> 
                </div>
                <div class="container-fluid" id="usuarioList">
                    <%
                        if (request.getAttribute("MENSAJE_USR") != null) {
                            if (request.getAttribute("MENSAJE_USR") instanceof ArrayList) {

                    %>
                    <h5 id="tituloLstUsuario" class="tituloAdmin">Listado total de Usuarios</h5>
                    <div id="tablaUsuario">
                        <table class="table">
                            <tr>
                                <th>ID</th>
                                <th>NOMBRE</th>
                                <th>APELLIDO1</th>
                                <th>APELLIDO2</th>
                                <th>FECHA NACIMIENTO</th>
                                <th>DIRECCIÓN ENVÍO</th>
                                <th>EMAIL</th>
                                <th>PASSWORD</th>
                                <th>USUARIO ACTIVO</th>
                                <th>ROL</th>
                                <th></th>
                                <th></th>
                            </tr>
                            <%                               ArrayList<Usuario> lstUsuarios = (ArrayList) request.getAttribute("MENSAJE_USR");
                                for (Usuario usuario : lstUsuarios) {
                            %>
                            <tr id="fila<%=usuario.getIdUsuario()%>">
                                <td class="id"><%out.print(usuario.getIdUsuario());%></td>
                                <td class="nombre_usr"><%out.print(usuario.getNombre());%></td>
                                <td class="apellido1"><%out.print(usuario.getApellido1());%></td>
                                <td class="apellido2"><%out.print(usuario.getApellido2());%></td>
                                <td class="fechaNacimiento"><%out.print(usuario.getFechaNacimiento());%></td>
                                <td class="direccionEnvio"><%out.print(usuario.getDireccionEnvio());%></td>
                                <td class="email"><%out.print(usuario.getEmail());%></td>
                                <td class="password"><%out.print(usuario.getPassword());%></td>
                                <td class="activo_usr"><%out.print(usuario.getActivo());%></td>
                                <td class="rol"><%out.print(usuario.getIdRol());%></td>
                                <td><input type="button" value="Eliminar" class="btn btn-danger btnEliminar" onclick="eliminarUsuario('<%=usuario.getIdUsuario()%>')"></td>
                                <td><input type="button" value="Modificar" class="btn btn-info btnInfo" onclick="modificarUsuario('<%=usuario.getIdUsuario()%>')"></td>
                            </tr>
                            <%
                                }

                            } else if (request.getAttribute("MENSAJE_USR") instanceof String) {

                            %>
                            <script>
                                document.location = "Controlador?ACTION=USUARIO.LISTAR"
                            </script>
                            <%          }
                                }
                            %>
                        </table>
                        <!--<div style="height: 200px;"></div>--> 
                    </div>
                </div>
                <div class="container-fluid" id="productoAdd" style="display:none;">
                    <h5 id="tituloAddProducto" class="tituloAdmin">Añadir producto</h5>
                    <form action="" method="post" role="form" id="formularioProducto">
                        <div class="row">
                            <div class="form-group col-lg-1 col-md-1"></div>
                            <input type="hidden" name="ID_PRODUCTO" id="ID_PRODUCTO"/>
                            <div class="form-group col-lg-4 col-md-4">
                                <label for="NOMBRE_PRODUCTO">Nombre del producto</label>
                                <input type="text" maxlength="40" name="NOMBRE_PRODUCTO" id="NOMBRE_PRODUCTO" class="form-control" placeholder="Nombre" required="required"/>
                            </div>
                            <div class="form-group col-lg-2 col-md-2">
                                <label for="PRECIO_COMPRA">Precio de compra</label>
                                <input type="number" min="0" step=".01" name="PRECIO_COMPRA" id="PRECIO_COMPRA" class="form-control" placeholder="€" required="required"/>
                            </div>
                            <div class="form-group col-lg-2 col-md-2">
                                <label for="PRECIO_VENTA">Precio de venta</label>
                                <input type="number" min="0" step=".01" name="PRECIO_VENTA" id="PRECIO_VENTA" class="form-control" placeholder="€" required="required"/>
                            </div>
                            <div class="form-group col-lg-2 col-md-2">
                                <label for="PORCENTAJE_OFERTA">Porcentaje de la oferta</label>
                                <input type="number" min="0" step=".01" name="PORCENTAJE_OFERTA" id="PORCENTAJE_OFERTA" class="form-control" placeholder="%" required="required"/>
                            </div>
                        </div>
                        <div class="row">                            
                            <div class="form-group col-lg-1 col-md-1"></div>
                            <div class="form-group col-lg-10 col-md-10">
                                <label for="DESCRIPCION">Descripción del producto</label>
                                <textarea rows="2" cols="20" maxlength="60" name="DESCRIPCION" id="DESCRIPCION" class="form-control" placeholder="Inserte una descripción" required="required"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-1 col-md-1"></div>

                            <div class="form-group col-lg-7 col-md-7">
                                <label for="FOTO_PRODUCTO">Inserte una foto del producto</label>
                                <input type="file" name="FOTO_PRODUCTO" id="FOTO_PRODUCTO" class="form-control-file" placeholder="Añadir foto" required="required"/>
                                <input type="hidden" name="NOMBREFOTO" id="NOMBREFOTO">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-lg-1 col-md-1"></div>

                            <div class="form-group col-lg-1 col-md-1">
                                <label for="PRODUCTO_ACTIVO">ACTIVO</label>
                                <input type="text" minlength="1" maxlength="1" name="PRODUCTO_ACTIVO" id="PRODUCTO_ACTIVO" class="form-control" placeholder="S/N" required="required">
                            </div>
                        </div>
                        <div class="btnMenuAdmin">

                            <button type="submit" id="btnAltaProducto" class="btnAceptar btn btnAdmin"></button>
                            <button type="button" class="btnCancelar btn btnAdmin" onclick="document.location = 'archivosJSP/adminMenu.jsp';" class="btn btnAdmin">Cancelar</button>
                        </div>
                    </form>
                    <div style="height: 200px;"></div> 
                </div>
                <div class="container-fluid" id="productoList">
                    <%
                        if (request.getAttribute("MENSAJE_PROD") != null) {
                            if (request.getAttribute("MENSAJE_PROD") instanceof ArrayList) {

                    %>
                    <h5 id="tituloLstProducto" class="tituloAdmin">Listado total de Productos</h5>
                    <div id="tablaProducto">
                        <table class="table">
                            <tr>
                                <th>ID</th>
                                <th>NOMBRE</th>
                                <th>PRECIO COMPRA</th>
                                <th>PRECIO VENTA</th>
                                <th>PORCENTAJE OFERTA</th>
                                <th>DESCRIPCIÓN</th>
                                <th>FOTO</th>
                                <th>PRODUCTO ACTIVO</th>
                                <th></th>
                                <th></th>
                            </tr>
                            <%                                ArrayList<Producto> lstProductos = (ArrayList) request.getAttribute("MENSAJE_PROD");
                                for (Producto producto : lstProductos) {
                            %>
                            <tr id="fila<%=producto.getIdProducto()%>">
                                <td class="id"><%out.print(producto.getIdProducto());%></td>
                                <td class="nombre_prod"><%out.print(producto.getNombre());%></td>
                                <td class="precioCompra"><%out.print(producto.getPrecioCompra());%></td>
                                <td class="precioVenta"><%out.print(producto.getPrecioVenta());%></td>
                                <td class="porcentajeOferta"><%out.print(producto.getPorcentajeOferta());%></td>
                                <td class="descripcionAdmin"><%out.print(producto.getDescripcion());%></td>
                                <td class="foto"><img src="../Ecommerce/images/productos/<%out.print(producto.getFoto());%>" style="max-width: 50px !important;" alt="<%out.print(producto.getFoto());%>" class="img-thumbnail"></td>
                                <td class="activo_prod"><%out.print(producto.getActivo());%></td>
                                <td><input type="button" value="Eliminar" class="btn btn-danger btnEliminar" onclick="eliminarProducto('<%=producto.getIdProducto()%>')"></td>
                                <td><input type="button" value="Modificar" class="btn btn-info btnInfo" onclick="modificarProducto('<%=producto.getIdProducto()%>')"></td>
                            </tr>
                            <%
                                }

                            } else if (request.getAttribute("MENSAJE_PROD") instanceof String) {

                            %>
                            <script>
                                document.location = "Controlador?ACTION=PRODUCTO.LISTAR"
                            </script>
                            <%          }
                                }
                            %>
                        </table>
                        <div style="height: 200px;"></div> 
                    </div>
                </div>
                <footer>
                    <div class="footer">
                        Guillermo Sesé Santos 1SI 2017-2018 &COPY;
                    </div>
                </footer>
            </div>
        </div>
        <%
        } else {
        %>
        <div class="container-fluid">
            <h1>
                No tienes permiso para acceder a esta página
            </h1>
        </div>
        <%
                }
            } else {
                response.sendRedirect("../index.jsp");
            }
        %>
    </body>
</html>
