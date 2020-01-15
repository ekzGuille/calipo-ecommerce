package modelo.carro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import modelo.DAO;
import modelo.MotorPrepareSQL;

/**
 *
 * @author guill
 */
public class CarroDAO implements DAO<Carro, Integer> {

    private static final String SQL_FIND_LINEAPEDIDO = "SELECT `LINEA_PEDIDOS`.`ID_PRODUCTO`,`FOTO`,`NOMBRE`,`DESCRIPCION`,`PORCENTAJE_OFERTA`,`COSTE_UNITARIO`,`CANTIDAD`,`SUBTOTAL` "
            + "FROM `LINEA_PEDIDOS`,`PRODUCTOS` "
            + "WHERE 1=1 AND `LINEA_PEDIDOS`.`ID_PRODUCTO`=`PRODUCTOS`.`ID_PRODUCTO` AND `LINEA_PEDIDOS`.`ID_PEDIDO`=";

    private MotorPrepareSQL mpSQL;
    private PreparedStatement pst;

    public CarroDAO() {
        mpSQL = new MotorPrepareSQL();
    }

    @Override
    public int add(Carro bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Carro bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Carro> findAll(Carro bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Carro> findLineaPedido(Integer idPedido) {
        ArrayList<Carro> lstCarro = null;
        try {

            String sqlCabecera = SQL_FIND_LINEAPEDIDO;
            String sqlCuerpo = "?";

            String sqlFinal = sqlCabecera + sqlCuerpo;
            
            pst = this.mpSQL.connect().prepareStatement(sqlFinal);
            pst.setInt(1, idPedido);
            
            //El .execute está programado. Llama al .executeUpdate (devuelve el nº de filas alteradas)
            //El .executeQuery devuelve un Resultset
            ResultSet rs = this.mpSQL.executeQuery(pst);
            lstCarro = new ArrayList<>();

            while (rs.next()) {
                Carro carro = new Carro();
                carro.setIdProducto(rs.getInt(1));
                carro.setFotoProducto(rs.getString(2));
                carro.setNombreProducto(rs.getString(3));
                carro.setDescripcionProducto(rs.getString(4));
                carro.setOfertaProducto(rs.getDouble(5));
                carro.setCosteUnitario(rs.getDouble(6));
                carro.setCantidadProducto(rs.getInt(7));
                carro.setSubtotalProducto(rs.getDouble(8));
                lstCarro.add(carro);
            }
        } catch (Exception ex) {

        } finally {
            this.mpSQL.disconnect();
        }
        return lstCarro;
    }

}
