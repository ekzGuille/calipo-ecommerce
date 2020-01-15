package modelo.lineaPedido;

/**
 *
 * @author guill
 */
public class LineaPedido {
    
    private int idLineaPedido;
    private double costeUnitario;
    private int cantidad;
    private double subtotal;
    private int idProducto;
    private int idPedido;

    public LineaPedido() {
    }

    public LineaPedido(int idLineaPedido, double costeUnitario, int cantidad, double subtotal, int idProducto, int idPedido) {
        this.idLineaPedido = idLineaPedido;
        this.costeUnitario = costeUnitario;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.idProducto = idProducto;
        this.idPedido = idPedido;
    }

    public int getIdLineaPedido() {
        return idLineaPedido;
    }

    public void setIdLineaPedido(int idLineaPedido) {
        this.idLineaPedido = idLineaPedido;
    }

    public double getCosteUnitario() {
        return costeUnitario;
    }

    public void setCosteUnitario(double costeUnitario) {
        this.costeUnitario = costeUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public String toString() {
        return "LineaPedido{" + "idLineaPedido=" + idLineaPedido + ", costeUnitario=" + costeUnitario + ", cantidad=" + cantidad + ", subtotal=" + subtotal + ", idProducto=" + idProducto + ", idPedido=" + idPedido + '}';
    }
    
    
}
