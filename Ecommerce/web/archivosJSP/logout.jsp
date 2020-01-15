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
            session.invalidate();
            response.sendRedirect("../index.jsp");
        %>
        <footer>
            Guillermo Sesé Santos 1SI 2017-2018 &COPY;
        </footer>
    </div>
</body>
</html>
