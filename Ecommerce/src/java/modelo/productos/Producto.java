package modelo.productos;

/**
 *
 * @author guill
 */
public class Producto {
    
    //Atributos de la tabla
    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precioCompra;
    private double precioVenta;
    private double porcentajeOferta;
    private String foto;
    private String activo;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, String descripcion, double precioCompra, double precioVenta, double porcentajeOferta, String foto, String activo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.porcentajeOferta = porcentajeOferta;
        this.foto = foto;
        this.activo = activo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPorcentajeOferta() {
        return porcentajeOferta;
    }

    public void setPorcentajeOferta(double porcentajeOferta) {
        this.porcentajeOferta = porcentajeOferta;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + ", porcentajeOferta=" + porcentajeOferta + ", foto=" + foto + ", activo=" + activo + '}';
    }

}
