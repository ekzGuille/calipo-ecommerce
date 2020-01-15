<%-- 
    Document   : signup
    Created on : 17-may-2018, 18:59:03
    Author     : guill
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign up</title>
        <meta charset="UTF-8">
        <link rel="icon" href="../images/favicon/faboat.png">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- JS -->
        <script src="../librerias/jquery-3.3.1.min.js"></script>
        <script src="../librerias/bootstrap-4.0.0/assets/js/vendor/popper.min.js"></script>
        <script src="../librerias/bootstrap-4.0.0/dist/js/bootstrap.min.js"></script>
        <script src="../js/script.js"></script>
        <!-- CSS -->
        <link rel="stylesheet" href="../css/normalize.css"/>
        <link rel="stylesheet" href="../librerias/bootstrap-4.0.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/style.css"/>
    </head>
    <body>
        <%
            if (session.getAttribute("USUARIO_ROL") == null) {
        %>
        <div class="container-fluid">
            <div class="jumbotron">
            </div>
            <nav class="navbar indexNav menuAnexo">
                <h3>Introduzca sus datos para registrarse</h3>
            </nav>
            <form action="../Controlador?ACTION=USUARIO.ADD" method="post" role="form" class="formularioLogin">
                <div class="row">
                    <div class="form-group col-lg-1 col-md-1"></div>
                    <div class="form-group col-lg-4 col-md-4">
                        <label class="labelForm"  for="NOMBRE">Nombre</label>
                        <input type="text" name="NOMBRE" id="NOMBRE" class="form-control formularioInput" placeholder="Nombre" required="required"/>
                    </div>
                    <div class="form-group col-lg-3 col-md-3">
                        <label class="labelForm" for="APELLIDO_1">Primer apellido</label>
                        <input type="text" name="APELLIDO_1" id="APELLIDO_1" class="form-control formularioInput" placeholder="Primer Apellido" required="required"/>
                    </div>
                    <div class="form-group col-lg-3 col-md-3">
                        <label class="labelForm" for="APELLIDO_2">Segundo apellido</label>
                        <input type="text" name="APELLIDO_2" id="APELLIDO_2" class="form-control formularioInput" placeholder="Segundo Apellido" required="required"/>
                    </div>
                    <div class="form-group col-lg-1 col-md-1"></div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-1 col-md-1"></div>
                    <div class="form-group col-lg-3 col-md-3">
                        <label class="labelForm visibleLabel" for="FECHA_NACIMIENTO">Fecha de nacimiento</label>
                        <input type="date" name="FECHA_NACIMIENTO" id="FECHA_NACIMIENTO" class="form-control formularioInput" placeholder="01/01/1900" required="required"/>
                    </div>
                    <div class="form-group col-lg-7 col-md-7">
                        <label class="labelForm visibleLabel" for="DIRECCION_ENVIO">Dirección de envío</label>
                        <input type="text" name="DIRECCION_ENVIO" id="DIRECCION_ENVIO" class="form-control formularioInput" placeholder="C/La Azucena nº 203, portal 23, 3ºA" required="required"/>
                    </div>
                    <div class="form-group col-lg-1 col-md-1"></div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-1 col-md-1"></div>
                    <div class="form-group col-lg-6 col-md-6">
                        <label class="labelForm" for="EMAIL">Correo electrónico</label>
                        <input type="email" name="EMAIL" id="EMAIL" class="form-control formularioInput" placeholder="Email" required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"/>
                    </div>
                    <div class="form-group col-lg-4 col-md-4">
                        <label class="labelForm" for="PASSWORD">Contraseña</label>
                        <input type="password" name="PASSWORD" id="PASSWORD" class="form-control formularioInput" placeholder="Contraseña" required="required"/>
                    </div>
                    <div class="form-group col-lg-1 col-md-1"></div>
                </div>
                <button type="submit" id="btnRegistrar" class="btnAceptar btn btnLogin">Registrarse</button>
                <button type="button" class="btnCancelar btn btnLogin" onclick="document.location = '../index.jsp';" class="btn">Cancelar</button>
            </form>
            <div style="height: 80px"></div> 
            <footer>
                Guillermo Sesé Santos 1SI 2017-2018 &COPY;
            </footer>
        </div>
        <%
            } else {
                response.sendRedirect("../index.jsp");
            }
        %>
    </body>
</html>
