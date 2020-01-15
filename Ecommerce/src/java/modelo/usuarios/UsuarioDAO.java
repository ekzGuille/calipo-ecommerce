package modelo.usuarios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import modelo.DAO;
import modelo.MotorPrepareSQL;

/**
 *
 * @author guill
 */
public class UsuarioDAO implements DAO<Usuario, Integer> {

    private static final String SQL_INSERT = "INSERT INTO `USUARIOS`(`NOMBRE`, `APELLIDO_1`, `APELLIDO_2`, `FECHA_NACIMIENTO`, `DIRECCION_ENVIO`, `EMAIL`, `PASSWORD`,`ACTIVO`,`ID_ROL`) VALUES ";
    private static final String SQL_UPDATE = "UPDATE `USUARIOS` SET ";
    private static final String SQL_DELETE = "DELETE FROM `USUARIOS` WHERE `ID_USUARIO` =";
    private static final String SQL_FIND_ALL = "SELECT * FROM `USUARIOS` WHERE 1=1";
    private static final String SQL_GET_ROL = "SELECT `ID_ROL` FROM `USUARIOS` WHERE `ID_USUARIO` =";

    private MotorPrepareSQL mpSQL;
    private PreparedStatement pst;

    public UsuarioDAO() {
        mpSQL = new MotorPrepareSQL();
    }

    @Override
    public int add(Usuario bean) {
//        this.mpSQL.connect();

        String pass = bean.getPassword();

        //Encriptar la contraseña
        //pass = pass
        String sql = SQL_INSERT + "(?,?,?,?,?,?,?,?,?)";
        int resp = 0;

        try {
            pst = this.mpSQL.connect().prepareStatement(sql);
            pst.setString(1, bean.getNombre());
            pst.setString(2, bean.getApellido1());
            pst.setString(3, bean.getApellido2());
            pst.setString(4, bean.getFechaNacimiento());
            pst.setString(5, bean.getDireccionEnvio());
            pst.setString(6, bean.getEmail());
            pst.setString(7, pass);
            pst.setString(8, bean.getActivo());
            pst.setInt(9, bean.getIdRol());

            resp = this.mpSQL.execute(pst);

        } catch (Exception e) {
        } finally {
            this.mpSQL.disconnect();
            return resp;
        }
    }

    @Override
    public int delete(Integer id) {
//        this.mpSQL.connect();
        String sql = SQL_DELETE + "?";

        int resp = 0;
        try {
            pst = this.mpSQL.connect().prepareStatement(sql);
            pst.setInt(1, id);

            resp = this.mpSQL.execute(pst);
        } catch (Exception e) {
        } finally {
            this.mpSQL.disconnect();
            return resp;
        }
    }

    @Override
    public int update(Usuario bean) {
//        this.mpSQL.connect();

        HashMap<Integer, Object> lstCondiciones = new HashMap<>();
        String sql = SQL_UPDATE;
        int contarCasos = 0;

        if (bean.getNombre() != null) {
            sql += "`NOMBRE`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getNombre());
        }

        if (bean.getApellido1() != null) {
            sql += "`APELLIDO_1`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getApellido1());
        }

        if (bean.getApellido2() != null) {
            sql += "`APELLIDO_2`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getApellido2());
        }

        if (bean.getFechaNacimiento() != null) {
            sql += "`FECHA_NACIMIENTO`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getFechaNacimiento());
        }

        if (bean.getDireccionEnvio() != null) {
            sql += "`DIRECCION_ENVIO`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getDireccionEnvio());
        }

        if (bean.getEmail() != null) {
            sql += "`EMAIL`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getEmail());
        }

        if (bean.getPassword() != null) {
            sql += "`PASSWORD`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getPassword());
        }

        if (bean.getActivo() != null) {
            sql += "`ACTIVO`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getActivo());
        }

        if (bean.getIdRol() > 0) {
            sql += "`ID_ROL`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getIdRol());
        }

        //Se añaden siempre comas y, si el último valor es una coma, se quita la última coma.
        if (sql.endsWith(",")) {
            sql = sql.substring(0, sql.length() - 1);
        }

        sql += " WHERE `ID_USUARIO`=?";
        contarCasos++;
        lstCondiciones.put(contarCasos, bean.getIdUsuario());

        int resp = 0;
        try {
            pst = this.mpSQL.connect().prepareStatement(sql);

            for (Map.Entry<Integer, Object> item : lstCondiciones.entrySet()) {
                Integer contar = item.getKey();
                Object valor = item.getValue();

                if (valor instanceof String) {
                    pst.setString(contar, (String) valor);
                }
                if (valor instanceof Integer) {
                    pst.setInt(contar, (Integer) valor);
                }
                if (valor instanceof Double) {
                    pst.setDouble(contar, (Double) valor);
                }
            }

            resp = this.mpSQL.execute(pst);

        } catch (Exception e) {
        } finally {
            mpSQL.disconnect();
            return resp;
        }
    }

    @Override
    public ArrayList<Usuario> findAll(Usuario bean) {
        ArrayList<Usuario> lstUsuario = null;
        HashMap<Integer, Object> lstCondiciones = new HashMap<>();
        try {
//            this.motorSQL.connect();
            String sqlCabecera = SQL_FIND_ALL;
            String sqlCuerpo = "";
            int contarCasos = 0;

            if (bean != null) {
                if (bean.getIdUsuario() > 0) {
                    sqlCuerpo += " AND `ID_USUARIO`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getIdUsuario());

                }
                if (bean.getNombre() != null && !bean.getNombre().equals("")) {
                    sqlCuerpo += " AND `NOMBRE`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getNombre());
                }

                if (bean.getApellido1() != null && !bean.getApellido1().equals("")) {
                    sqlCuerpo += " AND `APELLIDO_1`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getApellido1());
                }

                if (bean.getApellido2() != null && !bean.getApellido2().equals("")) {
                    sqlCuerpo += " AND `APELLIDO_2`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getApellido2());
                }

                if (bean.getFechaNacimiento() != null && !bean.getFechaNacimiento().equals("")) {
                    sqlCuerpo += " AND `FECHA_NACIMIENTO`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getFechaNacimiento());
                }

                if (bean.getDireccionEnvio() != null && !bean.getDireccionEnvio().equals("")) {
                    sqlCuerpo += " AND `DIRECCION_ENVIO`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getDireccionEnvio());
                }

                if (bean.getEmail() != null && !bean.getEmail().equals("")) {
                    sqlCuerpo += " AND `EMAIL`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getEmail());
                }

                if (bean.getPassword() != null && !bean.getPassword().equals("")) {
                    sqlCuerpo += " AND `PASSWORD`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getPassword());
                }

                if (bean.getActivo() != null && !bean.getActivo().equals("")) {
                    sqlCuerpo += " AND `ACTIVO`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getActivo());
                }

                if (bean.getIdRol() > 0) {
                    sqlCuerpo += " AND `ID_ROL`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getIdRol());
                }

            }

            String sqlFinal = sqlCabecera + sqlCuerpo;

            pst = this.mpSQL.connect().prepareStatement(sqlFinal);

            for (Map.Entry<Integer, Object> item : lstCondiciones.entrySet()) {
                Integer contar = item.getKey();
                Object valor = item.getValue();

                if (valor instanceof String) {
                    pst.setString(contar, (String) valor);
                }
                if (valor instanceof Integer) {
                    pst.setInt(contar, (Integer) valor);
                }
                if (valor instanceof Double) {
                    pst.setDouble(contar, (Double) valor);
                }
            }

            //El .execute está programado. Llama al .executeUpdate (devuelve el nº de filas alteradas)
            //El .executeQuery devuelve un Resultset
            ResultSet rs = this.mpSQL.executeQuery(pst);
            lstUsuario = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellido1(rs.getString(3));
                usuario.setApellido2(rs.getString(4));
                usuario.setFechaNacimiento(rs.getString(5));
                usuario.setDireccionEnvio(rs.getString(6));
                usuario.setEmail(rs.getString(7));
                usuario.setPassword(rs.getString(8));
                usuario.setActivo(rs.getString(9));
                usuario.setIdRol(rs.getInt(10));
                lstUsuario.add(usuario);
            }

        } catch (Exception ex) {
        } finally {
            this.mpSQL.disconnect();
            return lstUsuario;
        }
    }

    public int getRol(Usuario bean) {
        int rol = 0;

        String sql = SQL_GET_ROL + "?";

        try {
            pst = this.mpSQL.connect().prepareStatement(sql);
            pst.setInt(1, bean.getIdUsuario());

            ResultSet rs = this.mpSQL.executeQuery(pst);

            while (rs.next()) {
                rol = rs.getInt(1);
            }

        } catch (Exception e) {
        } finally {
            this.mpSQL.disconnect();
            return rol;
        }

    }

}
