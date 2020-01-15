package modelo.pedidos;

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
public class PedidoDAO implements DAO<Pedido, Integer> {

    private static final String SQL_INSERT = "INSERT INTO `PEDIDOS`(`FECHA_PEDIDO`, `ID_USUARIO`) VALUES ";
    private static final String SQL_UPDATE = "UPDATE `PEDIDOS` SET ";
    private static final String SQL_DELETE = "DELETE FROM `PEDIDOS` WHERE `ID_PEDIDO`=";
    private static final String SQL_FIND_ALL = "SELECT * FROM `PEDIDOS` WHERE 1=1";

    private MotorPrepareSQL mpSQL;
    private PreparedStatement pst;

    public PedidoDAO() {
        mpSQL = new MotorPrepareSQL();
    }

    @Override
    public int add(Pedido bean) {
        String sql = SQL_INSERT + "(?,?)";
        int resp = 0;

        try {
            pst = this.mpSQL.connect().prepareStatement(sql);
            pst.setString(1, bean.getFechaPedido());
            pst.setString(2, String.valueOf(bean.getIdUsuario()));

            resp = this.mpSQL.execute(pst);

        } catch (Exception e) {
        } finally {
            this.mpSQL.disconnect();
            return resp;
        }
    }

    @Override
    public int delete(Integer id) {
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
    public int update(Pedido bean) {
        HashMap<Integer, Object> lstCondiciones = new HashMap<>();
        String sql = SQL_UPDATE;
        int contarCasos = 0;

        if (bean.getFechaPedido() != null) {
            sql += "`FECHA_PEDIDO`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getFechaPedido());
        }

        if (bean.getIdUsuario() > 0) {
            sql += "`ID_USUARIO`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getIdUsuario());
        }

        //Se añaden siempre comas y, si el último valor es una coma, se quita la última coma.
        if (sql.endsWith(",")) {
            sql = sql.substring(0, sql.length() - 1);
        }

        sql += " WHERE `ID_PEDIDO`=?";
        contarCasos++;
        lstCondiciones.put(contarCasos, bean.getIdPedido());

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
    public ArrayList<Pedido> findAll(Pedido bean) {
        ArrayList<Pedido> lstPedido = null;
        HashMap<Integer, Object> lstCondiciones = new HashMap<>();
        try {
//            this.motorSQL.connect();
            String sqlCabecera = SQL_FIND_ALL;
            String sqlCuerpo = "";
            int contarCasos = 0;

            if (bean != null) {
                if (bean.getIdPedido() > 0) {
                    sqlCuerpo += " AND `ID_PEDIDO`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getIdPedido());
                }
                
                if (bean.getFechaPedido() != null && !bean.getFechaPedido().equals("")) {
                    sqlCuerpo += " AND `FECHA_PEDIDO`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getFechaPedido());
                }
                
                if (bean.getIdUsuario() > 0) {
                    sqlCuerpo += " AND `ID_USUARIO`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getIdUsuario());
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
            lstPedido = new ArrayList<>();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt(1));
                pedido.setFechaPedido(rs.getString(2));
                pedido.setIdUsuario(rs.getInt(3));
                lstPedido.add(pedido);
            }

        } catch (Exception ex) {
        } finally {
            this.mpSQL.disconnect();
            return lstPedido;
        }
    }

}
