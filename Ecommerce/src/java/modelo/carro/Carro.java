package modelo.carro;

/**
 *
 * @author guill
 */
public class Carro {
    private int idProducto;
    private String fotoProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private double ofertaProducto;
    private double costeUnitario;
    private int cantidadProducto;
    private double subtotalProducto;
    private double totalCompra;

    public Carro() {
    }

    public Carro(int idProducto, String fotoProducto, String nombreProducto, String descripcionProducto, double ofertaProducto, double costeUnitario, int cantidadProducto, double subtotalProducto, double totalCompra) {
        this.idProducto = idProducto;
        this.fotoProducto = fotoProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.ofertaProducto = ofertaProducto;
        this.costeUnitario = costeUnitario;
        this.cantidadProducto = cantidadProducto;
        this.subtotalProducto = subtotalProducto;
        this.totalCompra = totalCompra;
    }

    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(String fotoProducto) {
        this.fotoProducto = fotoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public double getOfertaProducto() {
        return ofertaProducto;
    }

    public void setOfertaProducto(double ofertaProducto) {
        this.ofertaProducto = ofertaProducto;
    }

    public double getCosteUnitario() {
        return costeUnitario;
    }

    public void setCosteUnitario(double costeUnitario) {
        this.costeUnitario = costeUnitario;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public double getSubtotalProducto() {
        return subtotalProducto;
    }

    public void setSubtotalProducto(double subtotalProducto) {
        this.subtotalProducto = subtotalProducto;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    @Override
    public String toString() {
        return "Carro{" + "idProducto=" + idProducto + ", fotoProducto=" + fotoProducto + ", nombreProducto=" + nombreProducto + ", descripcionProducto=" + descripcionProducto + ", ofertaProducto=" + ofertaProducto + ", costeUnitario=" + costeUnitario + ", cantidadProducto=" + cantidadProducto + ", subtotalProducto=" + subtotalProducto + ", totalCompra=" + totalCompra + '}';
    }

    
    
}
