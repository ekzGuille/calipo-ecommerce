package modelo.pedidos;

import java.util.HashMap;
import modelo.lineaPedido.LineaPedido;

/**
 *
 * @author guill
 */
public class Pedido {

    private int idPedido;
    private String fechaPedido;
    private int idUsuario;
    private HashMap<Integer,LineaPedido> lstLineaPedido;

    public Pedido(int idPedido, String fechaPedido, int idUsuario, HashMap<Integer,LineaPedido> lstLineaPedido) {
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.idUsuario = idUsuario;
        this.lstLineaPedido = lstLineaPedido;
    }

    public Pedido() {
        if (this.lstLineaPedido == null) {
            this.lstLineaPedido = new HashMap<>();
        }
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public HashMap<Integer,LineaPedido> getLstLineaPedido() {
        return lstLineaPedido;
    }

    public void setLstLineaPedido(HashMap<Integer,LineaPedido> lstLineaPedido) {
        this.lstLineaPedido = lstLineaPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" + "idPedido=" + idPedido + ", fechaPedido=" + fechaPedido + ", idUsuario=" + idUsuario + ", lstLineaPedido=" + lstLineaPedido + '}';
    }

}
