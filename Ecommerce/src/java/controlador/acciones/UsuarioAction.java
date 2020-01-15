package controlador.acciones;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.usuarios.Usuario;
import modelo.usuarios.UsuarioDAO;

/**
 *
 * @author guill
 */
public class UsuarioAction {

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String pagDestino = "";
        String actionReceived = request.getParameter("ACTION");

        String[] actionSplit = actionReceived.split("\\.");

        switch (actionSplit[1]) {
            case "ADD":
                pagDestino = add(request, response);
                break;

            case "LISTAR":
                pagDestino = list(request, response);
                break;

            case "MODIFICAR":
                pagDestino = mod(request, response);
                break;

            case "ELIMINAR":
                pagDestino = delete(request, response);
                break;

            case "ENTRAR":
                pagDestino = login(request, response);
                break;
        }

        return pagDestino;
    }

    private String add(HttpServletRequest request, HttpServletResponse response) {

        String webRedirect;
        webRedirect = "/index.jsp";
//        webRedirect = "/archivosJSP/adminMenu.jsp";

        HttpSession session = request.getSession(true);
        String mensajeRespuesta = "";
        int rol = 0;
        String activo = "";
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        String nombre = request.getParameter("NOMBRE");
        String apellido1 = request.getParameter("APELLIDO_1");
        String apellido2 = request.getParameter("APELLIDO_2");
        String fechaNacimiento = request.getParameter("FECHA_NACIMIENTO");
        String direccionEnvio = request.getParameter("DIRECCION_ENVIO");
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");

        if (request.getParameter("ACTIVO") == null) {
            activo = "S";
        } else {
            activo = request.getParameter("ACTIVO");
        }

        if (request.getParameter("ROL") == null) {
            rol = 2;
        } else {
            rol = Integer.parseInt(request.getParameter("ROL"));
        }

        usuario.setNombre(nombre);
        usuario.setApellido1(apellido1);
        usuario.setApellido2(apellido2);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setDireccionEnvio(direccionEnvio);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setActivo(activo);
        usuario.setIdRol(rol);

        if (usuarioDAO.add(usuario) == 1) {
            mensajeRespuesta = "Usuario añadido";
            session.setAttribute("USUARIO_LOG", usuario.getNombre());
            session.setAttribute("USUARIO_ROL", rol);
        } else {
            mensajeRespuesta = "Usuario NO añadido";
        }
        request.setAttribute("MENSAJE_USR", mensajeRespuesta);

        return webRedirect;
    }

    private String list(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
        webRedirect = "/archivosJSP/adminMenu.jsp";

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ArrayList<Usuario> lstUsuarios = usuarioDAO.findAll(null);

        String mensajeRespuesta = "";
        if (lstUsuarios.isEmpty()) {
            mensajeRespuesta = "No hay usuarios en la tabla";
            request.setAttribute("MENSAJE_USR", mensajeRespuesta);
        } else {
            request.setAttribute("MENSAJE_USR", lstUsuarios);
        }
        request.setAttribute("MENSAJE_USR", lstUsuarios);

        return webRedirect;
    }

    private String mod(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
        webRedirect = "/archivosJSP/adminMenu.jsp";

        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        String id = request.getParameter("ID_USUARIO");
        String nombre = request.getParameter("NOMBRE");
        String apellido1 = request.getParameter("APELLIDO_1");
        String apellido2 = request.getParameter("APELLIDO_2");
        String fechaNacimiento = request.getParameter("FECHA_NACIMIENTO");
        String direccionEnvio = request.getParameter("DIRECCION_ENVIO");
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        String activo = request.getParameter("ACTIVO");
        int rol = Integer.parseInt(request.getParameter("ROL"));

        usuario.setIdUsuario(Integer.parseInt(id));
        usuario.setNombre(nombre);
        usuario.setApellido1(apellido1);
        usuario.setApellido2(apellido2);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setDireccionEnvio(direccionEnvio);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setActivo(activo);
        usuario.setIdRol(rol);

        int actualizado = usuarioDAO.update(usuario);

        request.setAttribute("MENSAJE_USR", "El usuario se ha actualizado.");
        request.setAttribute("ELIMINAR", actualizado);

        return webRedirect;
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) {
        String webRedirect;
//        webRedirect = "/index.jsp";
        webRedirect = "/archivosJSP/adminMenu.jsp";

        String id = request.getParameter("ID_USUARIO");
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        int filaEliminada = usuarioDAO.delete(Integer.parseInt(id));

        request.setAttribute("MENSAJE_USR", "El usuario se ha eliminado.");
        request.setAttribute("ELIMINAR", filaEliminada);

        return webRedirect;
    }

    private String login(HttpServletRequest request, HttpServletResponse response) {
        //Session
        HttpSession session = request.getSession(true);
        //

        String webRedirect;
        webRedirect = "index.jsp";
//        webRedirect = "/archivosJSP/adminMenu.jsp";

        String email = request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario();

        usuario.setEmail(email);
        usuario.setPassword(password);

        ArrayList<Usuario> lstUsuarios = usuarioDAO.findAll(usuario);

        String mensajeRespuesta = "";
        if (lstUsuarios.isEmpty()) {
            mensajeRespuesta = "No se encuentra el usuario";
            request.setAttribute("MENSAJE_USR", mensajeRespuesta);
            webRedirect = "/archivosJSP/login.jsp";

        } else if (lstUsuarios.size() > 1) {
            mensajeRespuesta = "No se encuentra el usuario";
            request.setAttribute("MENSAJE_USR", mensajeRespuesta);
            webRedirect = "/archivosJSP/login.jsp";
        } else {
            String nombreUsuario = lstUsuarios.get(0).getNombre();
            int rol = usuarioDAO.getRol(lstUsuarios.get(0));
            
            //Devolver el nombre del usuario logeado
//            request.setAttribute("USUARIO_LOG", nombreUsuario);
//            request.setAttribute("USUARIO_ROL", rol);
            request.setAttribute("MENSAJE_USR", lstUsuarios);

            //Session atributes
            session.setAttribute("USUARIO_LOG", nombreUsuario);
            session.setAttribute("USUARIO_ROL", rol);
            session.setAttribute("USUARIO_ID", lstUsuarios.get(0).getIdUsuario());
            //
            webRedirect = "/index.jsp";

        }
        return webRedirect;

    }

}
