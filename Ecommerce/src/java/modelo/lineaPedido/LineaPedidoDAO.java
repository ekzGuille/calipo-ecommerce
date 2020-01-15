package modelo.lineaPedido;

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
public class LineaPedidoDAO implements DAO<LineaPedido, Integer> {

    private static final String SQL_INSERT = "INSERT INTO `LINEA_PEDIDOS`(`COSTE_UNITARIO`, `CANTIDAD`, `SUBTOTAL`, `ID_PEDIDO`, `ID_PRODUCTO`) VALUES ";
    private static final String SQL_UPDATE = "UPDATE `LINEA_PEDIDOS` SET ";
    private static final String SQL_DELETE = "DELETE FROM `LINEA_PEDIDOS` WHERE `ID_LINEA_PEDIDO`=";
    private static final String SQL_FIND_ALL = "SELECT * FROM `LINEA_PEDIDOS` WHERE 1=1";

    private MotorPrepareSQL mpSQL;
    private PreparedStatement pst;

    public LineaPedidoDAO() {
        mpSQL = new MotorPrepareSQL();
    }

    @Override
    public int add(LineaPedido bean) {
        String sql = SQL_INSERT + "(?,?,?,?,?)";
        int resp = 0;

        try {
            pst = this.mpSQL.connect().prepareStatement(sql);
            pst.setDouble(1, bean.getCosteUnitario());
            pst.setInt(2, bean.getCantidad());
            pst.setDouble(3, bean.getSubtotal());
            pst.setInt(4, bean.getIdPedido());
            pst.setInt(5, bean.getIdProducto());

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
    public int update(LineaPedido bean) {
        this.mpSQL.connect();

        HashMap<Integer, Object> lstCondiciones = new HashMap<>();
        String sql = SQL_UPDATE;
        int contarCasos = 0;

        if (bean.getCosteUnitario() > 0) {
            sql += "`COSTE_UNITARIO`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getCosteUnitario());
        }

        if (bean.getCantidad() > 0) {
            sql += "`CANTIDAD`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getCantidad());
        }

        if (bean.getSubtotal() > 0) {
            sql += "`SUBTOTAL`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getSubtotal());
        }

        if (bean.getIdPedido() > 0) {
            sql += "`ID_PEDIDO`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getIdPedido());
        }

        if (bean.getIdProducto() > 0) {
            sql += "`ID_PRODUCTO`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getIdProducto());
        }

        //Se añaden siempre comas y, si el último valor es una coma, se quita la última coma.
        if (sql.endsWith(",")) {
            sql = sql.substring(0, sql.length() - 1);
        }

        sql += " WHERE `ID_LINEA_PEDIDO`=?";
        contarCasos++;
        lstCondiciones.put(contarCasos, bean.getIdLineaPedido());

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
    public ArrayList<LineaPedido> findAll(LineaPedido bean) {
        ArrayList<LineaPedido> lstLineaPedido = null;
        HashMap<Integer, Object> lstCondiciones = new HashMap<>();
        try {

            String sqlCabecera = SQL_FIND_ALL;
            String sqlCuerpo = "";
            int contarCasos = 0;

            if (bean != null) {
                if (bean.getIdLineaPedido() > 0) {
                    sqlCuerpo += " AND `ID_LINEA_PEDIDO`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getIdLineaPedido());
                }
                if (bean.getCosteUnitario() > 0) {
                    sqlCuerpo += " AND `COSTE_UNITARIO`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getCosteUnitario());
                }
                if (bean.getCantidad() > 0) {
                    sqlCuerpo += " AND `CANTIDAD`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getCantidad());
                }
                if (bean.getSubtotal() > 0) {
                    sqlCuerpo += " AND `SUBTOTAL`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getSubtotal());
                }
                if (bean.getIdPedido() > 0) {
                    sqlCuerpo += " AND `ID_PEDIDO`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getIdPedido());
                }
                if (bean.getIdProducto() > 0) {
                    sqlCuerpo += " AND `ID_PRODUCTO`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getIdProducto());
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
            lstLineaPedido = new ArrayList<>();

            while (rs.next()) {
                LineaPedido lineaPedido = new LineaPedido();
                lineaPedido.setIdLineaPedido(rs.getInt(1));
                lineaPedido.setCosteUnitario(rs.getDouble(2));
                lineaPedido.setCantidad(rs.getInt(3));
                lineaPedido.setSubtotal(rs.getDouble(4));
                lineaPedido.setIdPedido(rs.getInt(5));
                lineaPedido.setIdProducto(rs.getInt(6));
                lstLineaPedido.add(lineaPedido);
            }

        } catch (Exception ex) {

        } finally {
            this.mpSQL.disconnect();
        }
        return lstLineaPedido;
    }
        
}
