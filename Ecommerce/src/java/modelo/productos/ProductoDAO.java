package modelo.productos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import modelo.DAO;
import modelo.MotorPrepareSQL;
import modelo.productos.Producto;

/**
 *
 * @author guill
 */
public class ProductoDAO implements DAO<Producto, Integer> {

    private static final String SQL_INSERT = "INSERT INTO `PRODUCTOS`(`NOMBRE`, `PRECIO_COMPRA`, `PRECIO_VENTA`, `PORCENTAJE_OFERTA`, `DESCRIPCION`, `FOTO`, `ACTIVO`) VALUES ";
    private static final String SQL_UPDATE = "UPDATE `PRODUCTOS` SET ";
    private static final String SQL_DELETE = "DELETE FROM `PRODUCTOS` WHERE `ID` =";
    private static final String SQL_FIND_ALL = "SELECT * FROM `PRODUCTOS` WHERE 1=1";
    private static final String SQL_QUERY = "SELECT * FROM `PRODUCTOS` ";

    private MotorPrepareSQL mpSQL;
    private PreparedStatement pst;

    public ProductoDAO() {
        mpSQL = new MotorPrepareSQL();
    }

    @Override
    public int add(Producto bean) {
//        this.mpSQL.connect();

        String sql = SQL_INSERT + "(?,?,?,?,?,?,?)";
        int resp = 0;

        try {
            pst = this.mpSQL.connect().prepareStatement(sql);
            pst.setString(1, bean.getNombre());
            pst.setDouble(2, bean.getPrecioCompra());
            pst.setDouble(3, bean.getPrecioVenta());
            pst.setDouble(4, bean.getPorcentajeOferta());
            pst.setString(5, bean.getDescripcion());
            pst.setString(6, bean.getFoto());
            pst.setString(7, bean.getActivo());

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
    public int update(Producto bean) {
        this.mpSQL.connect();

        HashMap<Integer, Object> lstCondiciones = new HashMap<>();
        String sql = SQL_UPDATE;
        int contarCasos = 0;

        if (bean.getNombre() != null) {
            sql += "`NOMBRE`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getNombre());
        }

        if (bean.getPrecioCompra() > 0) {
            sql += "`PRECIO_COMPRA`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getPrecioCompra());
        }

        if (bean.getPrecioVenta() > 0) {
            sql += "`PRECIO_VENTA`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getPrecioVenta());
        }

        if (bean.getPorcentajeOferta() > 0) {
            sql += "`PORCENTAJE_OFERTA`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getPorcentajeOferta());
        }

        if (bean.getDescripcion() != null) {
            sql += "`DESCRIPCION`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getDescripcion());
        }

        if (bean.getFoto() != null) {
            sql += "`FOTO`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getFoto());
        }

        if (bean.getActivo() != null) {
            sql += "`ACTIVO`=?,";
            contarCasos++;
            lstCondiciones.put(contarCasos, bean.getActivo());
        }

        //Se añaden siempre comas y, si el último valor es una coma, se quita la última coma.
        if (sql.endsWith(",")) {
            sql = sql.substring(0, sql.length() - 1);
        }

        sql += " WHERE `ID_PRODUCTO`=?";
        contarCasos++;
        lstCondiciones.put(contarCasos, bean.getIdProducto());

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
    public ArrayList<Producto> findAll(Producto bean) {
        ArrayList<Producto> lstProducto = null;
        HashMap<Integer, Object> lstCondiciones = new HashMap<>();
        try {

            String sqlCabecera = SQL_FIND_ALL;
            String sqlCuerpo = "";
            int contarCasos = 0;

            if (bean != null) {
                if (bean.getIdProducto() > 0) {
                    sqlCuerpo += " AND `ID_PRODUCTO`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getIdProducto());

                }
                if (bean.getNombre() != null && !bean.getNombre().equals("")) {
                    sqlCuerpo += " AND `NOMBRE`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getNombre());
                }

                if (bean.getPrecioCompra() > 0) {
                    sqlCuerpo += " AND `PRECIO_COMPRA`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getPrecioCompra());
                }

                if (bean.getPrecioVenta() > 0) {
                    sqlCuerpo += " AND `PRECIO_VENTA`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getPrecioVenta());
                }

                if (bean.getPorcentajeOferta() > 0) {
                    sqlCuerpo += " AND `PORCENTAJE_OFERTA`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getPorcentajeOferta());
                }

                if (bean.getDescripcion() != null && !bean.getDescripcion().equals("")) {
                    sqlCuerpo += " AND `DESCRIPCION`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getDescripcion());
                }

                if (bean.getFoto() != null && !bean.getFoto().equals("")) {
                    sqlCuerpo += " AND `FOTO`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getFoto());
                }

                if (bean.getActivo() != null && !bean.getActivo().equals("")) {
                    sqlCuerpo += " AND `ACTIVO`=?";
                    contarCasos++;
                    lstCondiciones.put(contarCasos, bean.getActivo());
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
            lstProducto = new ArrayList<>();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecioCompra(rs.getDouble(3));
                producto.setPrecioVenta(rs.getDouble(4));
                producto.setPorcentajeOferta(rs.getDouble(5));
                producto.setDescripcion(rs.getString(6));
                producto.setFoto(rs.getString(7));
                producto.setActivo(rs.getString(8));
                lstProducto.add(producto);
            }

        } catch (Exception ex) {

        } finally {
            this.mpSQL.disconnect();
        }
        return lstProducto;
    }

    public ArrayList<Producto> queryNombreAsc() {
        ArrayList<Producto> lstProducto = null;
        try {
            String sqlCabecera = SQL_QUERY;
            String sqlCuerpo = "WHERE `ACTIVO`='S' ORDER BY `NOMBRE` ASC";

            String sqlFinal = sqlCabecera + sqlCuerpo;

            pst = this.mpSQL.connect().prepareStatement(sqlFinal);

            //El .execute está programado. Llama al .executeUpdate (devuelve el nº de filas alteradas)
            //El .executeQuery devuelve un Resultset
            ResultSet rs = this.mpSQL.executeQuery(pst);
            lstProducto = new ArrayList<>();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecioCompra(rs.getDouble(3));
                producto.setPrecioVenta(rs.getDouble(4));
                producto.setPorcentajeOferta(rs.getDouble(5));
                producto.setDescripcion(rs.getString(6));
                producto.setFoto(rs.getString(7));
                producto.setActivo(rs.getString(8));
                lstProducto.add(producto);
            }

        } catch (Exception ex) {

        } finally {
            this.mpSQL.disconnect();
        }
        return lstProducto;
    }

    public ArrayList<Producto> queryNombreDesc() {
        ArrayList<Producto> lstProducto = null;
        try {
            String sqlCabecera = SQL_QUERY;
            String sqlCuerpo = "WHERE `ACTIVO`='S' ORDER BY `NOMBRE` DESC";

            String sqlFinal = sqlCabecera + sqlCuerpo;

            pst = this.mpSQL.connect().prepareStatement(sqlFinal);

            //El .execute está programado. Llama al .executeUpdate (devuelve el nº de filas alteradas)
            //El .executeQuery devuelve un Resultset
            ResultSet rs = this.mpSQL.executeQuery(pst);
            lstProducto = new ArrayList<>();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecioCompra(rs.getDouble(3));
                producto.setPrecioVenta(rs.getDouble(4));
                producto.setPorcentajeOferta(rs.getDouble(5));
                producto.setDescripcion(rs.getString(6));
                producto.setFoto(rs.getString(7));
                producto.setActivo(rs.getString(8));
                lstProducto.add(producto);
            }

        } catch (Exception ex) {

        } finally {
            this.mpSQL.disconnect();
        }
        return lstProducto;
    }

    public ArrayList<Producto> queryPrecioAsc() {
        ArrayList<Producto> lstProducto = null;
        try {
            String sqlCabecera = SQL_QUERY;
            String sqlCuerpo = "WHERE `ACTIVO`='S' ORDER BY `PRECIO_VENTA` ASC";

            String sqlFinal = sqlCabecera + sqlCuerpo;

            pst = this.mpSQL.connect().prepareStatement(sqlFinal);

            //El .execute está programado. Llama al .executeUpdate (devuelve el nº de filas alteradas)
            //El .executeQuery devuelve un Resultset
            ResultSet rs = this.mpSQL.executeQuery(pst);
            lstProducto = new ArrayList<>();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecioCompra(rs.getDouble(3));
                producto.setPrecioVenta(rs.getDouble(4));
                producto.setPorcentajeOferta(rs.getDouble(5));
                producto.setDescripcion(rs.getString(6));
                producto.setFoto(rs.getString(7));
                producto.setActivo(rs.getString(8));
                lstProducto.add(producto);
            }

        } catch (Exception ex) {

        } finally {
            this.mpSQL.disconnect();
        }
        return lstProducto;
    }

    public ArrayList<Producto> queryPrecioDesc() {
        ArrayList<Producto> lstProducto = null;
        try {
            String sqlCabecera = SQL_QUERY;
            String sqlCuerpo = "WHERE `ACTIVO`='S' ORDER BY `PRECIO_VENTA` DESC";

            String sqlFinal = sqlCabecera + sqlCuerpo;

            pst = this.mpSQL.connect().prepareStatement(sqlFinal);

            //El .execute está programado. Llama al .executeUpdate (devuelve el nº de filas alteradas)
            //El .executeQuery devuelve un Resultset
            ResultSet rs = this.mpSQL.executeQuery(pst);
            lstProducto = new ArrayList<>();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecioCompra(rs.getDouble(3));
                producto.setPrecioVenta(rs.getDouble(4));
                producto.setPorcentajeOferta(rs.getDouble(5));
                producto.setDescripcion(rs.getString(6));
                producto.setFoto(rs.getString(7));
                producto.setActivo(rs.getString(8));
                lstProducto.add(producto);
            }

        } catch (Exception ex) {

        } finally {
            this.mpSQL.disconnect();
        }
        return lstProducto;
    }

    public ArrayList<Producto> queryFiltrarNombre(String nombre) {
        ArrayList<Producto> lstProducto = null;
        try {
            String sqlCabecera = SQL_QUERY;
            String sqlCuerpo = "WHERE `NOMBRE` LIKE  '%"+nombre+"%'";

            String sqlFinal = sqlCabecera + sqlCuerpo;

            pst = this.mpSQL.connect().prepareStatement(sqlFinal);

            //El .execute está programado. Llama al .executeUpdate (devuelve el nº de filas alteradas)
            //El .executeQuery devuelve un Resultset
            ResultSet rs = this.mpSQL.executeQuery(pst);
            lstProducto = new ArrayList<>();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecioCompra(rs.getDouble(3));
                producto.setPrecioVenta(rs.getDouble(4));
                producto.setPorcentajeOferta(rs.getDouble(5));
                producto.setDescripcion(rs.getString(6));
                producto.setFoto(rs.getString(7));
                producto.setActivo(rs.getString(8));
                lstProducto.add(producto);
            }

        } catch (Exception ex) {

        } finally {
            this.mpSQL.disconnect();
        }
        return lstProducto;
    }

    public ArrayList<Producto> queryOferta() {
        ArrayList<Producto> lstProducto = null;
        try {
            String sqlCabecera = SQL_QUERY;
            String sqlCuerpo = "WHERE `PORCENTAJE_OFERTA` > 0 AND `PORCENTAJE_OFERTA` < 1";

            String sqlFinal = sqlCabecera + sqlCuerpo;

            pst = this.mpSQL.connect().prepareStatement(sqlFinal);

            //El .execute está programado. Llama al .executeUpdate (devuelve el nº de filas alteradas)
            //El .executeQuery devuelve un Resultset
            ResultSet rs = this.mpSQL.executeQuery(pst);
            lstProducto = new ArrayList<>();

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPrecioCompra(rs.getDouble(3));
                producto.setPrecioVenta(rs.getDouble(4));
                producto.setPorcentajeOferta(rs.getDouble(5));
                producto.setDescripcion(rs.getString(6));
                producto.setFoto(rs.getString(7));
                producto.setActivo(rs.getString(8));
                lstProducto.add(producto);
            }

        } catch (Exception ex) {

        } finally {
            this.mpSQL.disconnect();
        }
        return lstProducto;
    }

}
