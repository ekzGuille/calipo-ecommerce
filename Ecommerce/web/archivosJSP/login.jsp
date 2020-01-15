<%-- 
    Document   : login
    Created on : 17-may-2018, 18:59:12
    Author     : guill
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <link rel="icon" href="../images/favicon/faboat.png">

        <link rel="icon" href="images/favicon/faboat.png">
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
            if (session.getAttribute("USUARIO_ROL") == null) {
        %>
        <div class="container-fluid">
            <div class="jumbotron">
            </div>
            <nav class="navbar indexNav menuAnexo">
                <h3>Introduzca sus datos para entar</h3>
            </nav>
            <%
                if (request.getAttribute("MENSAJE_USR") != null) {
            %>
            <form action="Controlador?ACTION=USUARIO.ENTRAR" method="post" role="form" class="formularioLogin">
                <%
                } else {

                %>
                <form action="../Controlador?ACTION=USUARIO.ENTRAR" method="post" role="form" class="formularioLogin">
                    <%    }

                    %>

                    <div class="row">
                        <div class="form-group col-lg-4 col-md-4"></div>
                        <div class="form-group col-lg-4 col-md-4">
                            <label class="labelForm" for="EMAIL">Correo electrónico</label>
                            <input type="text" name="EMAIL" id="EMAIL" class="form-control formularioInput" placeholder="Email" required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"/>
                        </div>
                        <div class="form-group col-lg-4 col-md-4"></div>
                    </div>
                    <div class="row">
                        <div class="form-group col-lg-4 col-md-4"></div>
                        <div class="form-group col-lg-4 col-md-4">
                            <label class="labelForm" for="PASSWORD">Contraseña</label>
                            <input type="password" name="PASSWORD" id="PASSWORD" class="form-control formularioInput" placeholder="Contraseña" required="required"/>
                        </div>
                        <div class="form-group col-lg-4 col-md-4"></div>
                    </div>
                    <h6 style="margin-top: 1%;">¿Aún no tienes cuenta? <a style="color: grey"href="signup.jsp">Créate una</a>.</h6>
                    <button type="submit" id="btnEntrar" class="btnAceptar btn btnLogin">Entrar</button>
                    <button type="button" class="btnCancelar btn btnLogin" onclick="document.location = '/Ecommerce/index.jsp';" class="btn">Cancelar</button>
                </form>

                <%                if (request.getAttribute("MENSAJE_USR") != null) {
                        if (request.getAttribute("MENSAJE_USR") instanceof String) {
                %>
                <div class="container-fluid" id="mensajeLoginError"> 
                    <h5><% out.print((String) request.getAttribute("MENSAJE_USR")); %></h5>
                </div>
                <%
                        }
                    }
                %>
                <div style="height: 200px"></div> 
        </div>
        <footer>
            Guillermo Sesé Santos 1SI 2017-2018 &COPY;
        </footer>
        <%
            } else {
                response.sendRedirect("../index.jsp");
            }
        %>
    </body>
</html>
