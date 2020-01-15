
window.onscroll = cambiarScroll;
//window.onload =cambiarScroll,hideURL;
window.onload = function () {
    cambiarScroll();
    estadoInicial();
    carousel();
};


function cambiarScroll() {
    if (window.scrollY === 0) {
        document.querySelector('footer').style.cssText = "position: absolute; bottom: 0; width: 88.5%; text-align: center;color: #FFFFFF;background-color: #2C3E50;border-bottom-left-radius: 10px;border-bottom-right-radius: 10px;";
    } else {
        document.querySelector('footer').style.cssText = "position: relative; width: 100%;text-align: center;color: #FFFFFF;background-color: #2C3E50;border-bottom-left-radius: 10px;border-bottom-right-radius: 10px;";
    }
}

function hideURL() {
    console.log("entro");
    history.pushState("'" + window.location.href + "'", null, "ecommerce");
}

function mostrarObjeto(idSelector) {

    switch (idSelector) {
        case "usuarioAdd":
            $("#usuarioList").hide();
            $("#productoAdd").hide();
            $("#productoList").hide();

            $("#usuarioAdd").show();
            break;
        case "usuarioList":
            $("#usuarioAdd").hide();
            $("#productoAdd").hide();
            $("#productoList").hide();

            $("#usuarioList").show();
            break;
        case "productoAdd":
            $("#usuarioAdd").hide();
            $("#usuarioList").hide();
            $("#productoList").hide();

            $("#productoAdd").show();
            break;
        case "productoList":
            $("#usuarioAdd").hide();
            $("#usuarioList").hide();
            $("#productoAdd").hide();

            $("#productoList").show();
            break;

        default:
            $("#usuarioAdd").hide();
            $("#usuarioList").hide();
            $("#productoAdd").hide();
            $("#productoList").hide();
            break;
    }
}

function estadoInicial() {

    if (document.getElementById('btnRegistrarse') !== null) {
        var botonU = document.getElementById('btnRegistrarse');
        botonU.innerHTML = "Registrar usuario";
        botonU.removeAttribute("onclick");
    }

    if (document.getElementById('formularioUser') !== null) {
        var formularioU = document.getElementById('formularioUser');
        formularioU.removeAttribute("action");
        formularioU.setAttribute("action", "/Ecommerce/Controlador?ACTION=USUARIO.ADD");
    }

    if (document.getElementById('btnAltaProducto') !== null) {
        var botonP = document.getElementById('btnAltaProducto');
        botonP.innerHTML = "Registrar producto";
        botonP.removeAttribute("onclick");
    }

    if (document.getElementById('formularioProducto') !== null) {
        var formularioP = document.getElementById('formularioProducto');
        formularioP.removeAttribute("action");
        formularioP.setAttribute("action", "/Ecommerce/Controlador?ACTION=PRODUCTO.ADD");
    }
}

function eliminarUsuario(idUser) {
    //Comunicar desde Javascript con el Servidor
    //De esta manera se envía por "GET" y se ve en el URL los datos
    document.location = "Controlador?ACTION=USUARIO.ELIMINAR&ID_USUARIO=" + idUser;
}


function modificarUsuario(idUsuario) {
    var nombre = document.querySelector("#fila" + idUsuario + " .nombre_usr");
    var apellido1 = document.querySelector("#fila" + idUsuario + " .apellido1");
    var apellido2 = document.querySelector("#fila" + idUsuario + " .apellido2");
    var fechaNacimiento = document.querySelector("#fila" + idUsuario + " .fechaNacimiento");
    var direccionEnvio = document.querySelector("#fila" + idUsuario + " .direccionEnvio");
    var email = document.querySelector("#fila" + idUsuario + " .email");
    var password = document.querySelector("#fila" + idUsuario + " .password");
    var activo = document.querySelector("#fila" + idUsuario + " .activo_usr");
    var rol = document.querySelector("#fila" + idUsuario + " .rol");

    document.getElementById('NOMBRE').value = nombre.innerHTML;
    document.getElementById('APELLIDO_1').value = apellido1.innerHTML;
    document.getElementById('APELLIDO_2').value = apellido2.innerHTML;
    document.getElementById('FECHA_NACIMIENTO').value = fechaNacimiento.innerHTML;
    document.getElementById('DIRECCION_ENVIO').value = direccionEnvio.innerHTML;
    document.getElementById('EMAIL').value = email.innerHTML;
    document.getElementById('PASSWORD').value = password.innerHTML;
    document.getElementById('ACTIVO').value = activo.innerHTML;
    document.getElementById('ROL').value = rol.innerHTML;

    var boton = document.getElementById('btnRegistrarse');
    var formulario = document.getElementById('formularioUser');

    boton.innerHTML = "Actualizar usuario";
    boton.setAttribute("onclick", "actualizarUsuario(" + idUsuario + ")");

    formulario.removeAttribute("action");
    formulario.setAttribute("action", "/Ecommerce/Controlador?ACTION=USUARIO.MODIFICAR");

    mostrarObjeto('usuarioAdd');
}


function actualizarUsuario(idUsuario) {

    var id = document.getElementById('ID_USUARIO');
    id.value = idUsuario;

    var nombreMod = document.getElementById('NOMBRE').value;
    var apellido1Mod = document.getElementById('APELLIDO_1').value;
    var apellido2Mod = document.getElementById('APELLIDO_2').value;
    var fechaNacimiento = document.getElementById('FECHA_NACIMIENTO').value;
    var direccionEnvio = document.getElementById('DIRECCION_ENVIO').value;
    var emailMod = document.getElementById('EMAIL').value;
    var passwordMod = document.getElementById('PASSWORD').value;
    var rolMod = document.getElementById('ROL').value;
    var activoMod = document.getElementById('ACTIVO').value;

    document.location = "Controlador?ACTION=USUARIO.MODIFICAR"
            + "&ID_USUARIO=" + idUsuario
            + "&NOMBRE=" + nombreMod
            + "&APELLIDO_1=" + apellido1Mod
            + "&APELLIDO_2=" + apellido2Mod
            + "&FECHA_NACIMIENTO=" + fechaNacimiento
            + "&DIRECCION_ENVIO=" + direccionEnvio
            + "&EMAIL=" + emailMod
            + "&PASSWORD=" + passwordMod
            + "&ROL=" + rolMod
            + "&ACTIVO=" + activoMod;

    mostrarObjeto('usuarioList');
}

function eliminarProducto(idUser) {
    //Comunicar desde Javascript con el Servidor
    //De esta manera se envía por "GET" y se ve en el URL los datos
    document.location = "Controlador?ACTION=PRODUCTO.ELIMINAR&ID_PRODUCTO=" + idUser;
}


function modificarProducto(idProducto) {

    var nombre = document.querySelector("#fila" + idProducto + " .nombre_prod");
    var precioCompra = document.querySelector("#fila" + idProducto + " .precioCompra");
    var precioVenta = document.querySelector("#fila" + idProducto + " .precioVenta");
    var porcentajeOferta = document.querySelector("#fila" + idProducto + " .porcentajeOferta");
    var descripcion = document.querySelector("#fila" + idProducto + " .descripcionAdmin");
    var foto = document.querySelector("#fila" + idProducto + " .foto img");
    var activo = document.querySelector("#fila" + idProducto + " .activo_prod");

    document.getElementById('NOMBRE_PRODUCTO').value = nombre.innerHTML;
    document.getElementById('PRECIO_COMPRA').value = precioCompra.innerHTML;
    document.getElementById('PRECIO_VENTA').value = precioVenta.innerHTML;
    document.getElementById('PORCENTAJE_OFERTA').value = porcentajeOferta.innerHTML;
    document.getElementById('DESCRIPCION').value = descripcion.innerHTML;
    document.getElementById('NOMBREFOTO').value = foto.alt;
    document.getElementById('PRODUCTO_ACTIVO').value = activo.innerHTML;

    var boton = document.getElementById('btnAltaProducto');
    var formulario = document.getElementById('formularioProducto');

    boton.innerHTML = "Actualizar producto";
    boton.setAttribute("onclick", "actualizarProducto(" + idProducto + ")");

    formulario.removeAttribute("action");
    formulario.setAttribute("action", "/Ecommerce/Controlador?ACTION=PRODUCTO.MODIFICAR");

    mostrarObjeto('productoAdd');
}



function actualizarProducto(idProducto) {

    var id = document.getElementById('ID_PRODUCTO');
    id.value = idProducto;

    var nombreMod = document.getElementById('NOMBRE_PRODUCTO').value;
    var precioCompraMod = document.getElementById('PRECIO_COMPRA').value;
    var precioVentaMod = document.getElementById('PRECIO_VENTA').value;
    var porcentajeOfertaMod = document.getElementById('PORCENTAJE_OFERTA').value;
    var descripcionMod = document.getElementById('DESCRIPCION').value;
    var fotoMod = document.getElementById('NOMBREFOTO').value;
    var activoMod = document.getElementById('PRODUCTO_ACTIVO').value;

    document.location = "Controlador?ACTION=PRODUCTO.MODIFICAR"
            + "&ID_PRODUCTO=" + idProducto
            + "&NOMBRE_PRODUCTO=" + nombreMod
            + "&PRECIO_COMPRA=" + precioCompraMod
            + "&PRECIO_VENTA=" + precioVentaMod
            + "&PORCENTAJE_OFERTA=" + porcentajeOfertaMod
            + "&DESCRIPCION=" + descripcionMod
            + "&FOTO_PRODUCTO=" + fotoMod
            + "&PRODUCTO_ACTIVO=" + activoMod;

    mostrarObjeto('productoList');
}
function crearPedido(idUsuario) {

    var fecha = new Date();
    var dia = fecha.getDate();
    var mes = fecha.getMonth() + 1;
    var anio = fecha.getFullYear();
    var fechaCompleta = anio + "/" + mes + "/" + dia;

    document.location = "Controlador?ACTION=PEDIDO.ADDGETID"
            + "&FECHA_PEDIDO=" + fechaCompleta
            + "&ID_USUARIO=" + idUsuario;

}

function addLineaPedido(idProducto, precioVenta, idPedido, idLineaPedido) {
    //Añadir a la linea de Pedido
    //Se require: coste_unitario, cantidad, subtotal (producto), id_pedido, id_producto

    //Span a 0
    if (document.getElementById("iniciocomprarProducto" + idProducto) !== null) {
        var textoInicio = document.getElementById("iniciocomprarProducto" + idProducto).innerHTML;
        var cantidadTextoInicio = parseInt(textoInicio);
    }

    //Span que obtiene los valores de la BD
    if (document.getElementById("comprarProducto" + idProducto) !== null) {
        var texto = document.getElementById("comprarProducto" + idProducto).innerHTML;
        var cantidadTexto = parseInt(texto);
    }

    if (cantidadTextoInicio === 0) {
//    if (idLineaPedido === null) {
        //Comienza una nueva linea de pedido
        cantidadTextoInicio++;
        document.getElementById("iniciocomprarProducto" + idProducto).innerHTML = cantidadTextoInicio;

        document.location = "Controlador?ACTION=LINEAPEDIDO.ADDGETID"
                + "&COSTE_UNITARIO=" + precioVenta
                + "&CANTIDAD=" + cantidadTextoInicio
                + "&SUBTOTAL=" + (cantidadTextoInicio * precioVenta)
                + "&ID_PEDIDO=" + idPedido
                + "&ID_PRODUCTO=" + idProducto
                + "#producto" + idProducto;

    } else if (cantidadTexto !== 0) {
//    } else {
        //Ya se ha creado una linea de producto para dicho producto por lo que 
        //hay que "buscarlo" e incrementar su cantidad (sobrescribirla)
        cantidadTexto++;

        document.getElementById("comprarProducto" + idProducto).innerHTML = cantidadTexto;

        document.location = "Controlador?ACTION=LINEAPEDIDO.MODIFICAR"
                + "&ID_LINEA_PEDIDO=" + idLineaPedido
                + "&COSTE_UNITARIO=" + precioVenta
                + "&CANTIDAD=" + cantidadTexto
                + "&SUBTOTAL=" + (cantidadTexto * precioVenta)
                + "&ID_PEDIDO=" + idPedido
                + "&ID_PRODUCTO=" + idProducto
                + "#producto" + idProducto;

    }
}


function crearCarro(idPedido) {
    document.location = "Controlador?ACTION=CARRO.OBTENERPEDIDO&ID_PEDIDO=" + idPedido;
}

function restablecerFiltros() {
    document.location = "Controlador?ACTION=PRODUCTO.PRODACTIVO";
}

function buscarProducto() {

    if (document.getElementById('busquedaProducto') !== null) {
        var texto = document.getElementById('busquedaProducto').value;
        if (texto !== "") {
            document.location = "Controlador?ACTION=PRODUCTO.MOSTRARNOMBRE&NOMBRE_FILTRO=" + texto;
        }

    }
}

function mostrarFotoDesc(idFoto, idDescripcion) {
    $('#' + idFoto).hide();
    $('#' + idDescripcion).show();
}

function quitarFotoDesc(idFoto, idDescripcion) {
    $('#' + idFoto).show();
    $('#' + idDescripcion).hide();
}

function terminarPedido() {
    alert("Se ha finalizado el pedido, se le redirigirá a la página principal.");
    document.location = "archivosJSP/finPedido.jsp";
}
var myIndex = 0;
function carousel() {
    var i;
    var x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    myIndex++;
    if (myIndex > x.length) {
        myIndex = 1
    }
    x[myIndex - 1].style.display = "block";
    setTimeout(carousel, 3000);
}