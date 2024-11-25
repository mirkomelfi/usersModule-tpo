package tpo.usersmodule.model.entity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ventas")
public class VentaDTO {
    @Id
    @Column(name ="id_venta")
    private long idVenta;
    private String nombreUsuario;
    private String fecha;
    private double montoTotal;
    private Integer cantidadDeProductos;
    @ElementCollection
    private List<Integer> productos;
    private String estado;


    public VentaDTO() {
    }

    public VentaDTO(long idVenta, String nombreUsuario, String fecha, double montoTotal, String estado, Integer cantidadDeProductos, List<Integer> productos) {
        this.idVenta = idVenta;
        this.nombreUsuario = nombreUsuario;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.cantidadDeProductos = cantidadDeProductos;
        this.productos = productos;
        this.estado=estado;
    }

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getCantidadDeProductos() {
        return cantidadDeProductos;
    }

    public void setCantidadDeProductos(Integer cantidadDeProductos) {
        this.cantidadDeProductos = cantidadDeProductos;
    }

    public List<Integer> getProductos() {
        return productos;
    }

    public void setProductos(List<Integer> productos) {
        this.productos = productos;
    }
}