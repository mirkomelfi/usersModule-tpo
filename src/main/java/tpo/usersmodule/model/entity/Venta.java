package tpo.usersmodule.model.entity;

import java.util.Date;
import java.util.List;

public class Venta {

    private long idVenta;
    private String nombreUsuario;
    private Date fecha;
    private double montoTotal;
    private Integer cantidadDeProductos;
    private List<Integer> productos;

    public Venta() {
    }

    public Venta(long idVenta, String nombreUsuario, Date fecha, double montoTotal, Integer cantidadDeProductos, List<Integer> productos) {
        this.idVenta = idVenta;
        this.nombreUsuario = nombreUsuario;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.cantidadDeProductos = cantidadDeProductos;
        this.productos = productos;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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