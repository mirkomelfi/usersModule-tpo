package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @Column(name = "product_id")
    private long idProducto;
    private String nombre;
    private String descripcion;
    private Double precioVenta;
    private Integer stockActual;
    private Float descuentoEfectivo;
    private Float descuentoSocios;
    private Float descuentoNoSocios;
    private String categoria;
    @ElementCollection
    private Set<String> caracteristicas;
    @ElementCollection
    private Set<String>talles;

    public Producto() {
    }

    public Producto(long idProducto, String nombre, String descripcion, Double precioVenta, Integer stockActual, Float descuentoEfectivo, Float descuentoSocios, Float descuentoNoSocios, String categoria, Set<String> caracteristicas, Set<String> talles) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.stockActual = stockActual;
        this.descuentoEfectivo = descuentoEfectivo;
        this.descuentoSocios = descuentoSocios;
        this.descuentoNoSocios = descuentoNoSocios;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
        this.talles = talles;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
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

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Float getDescuentoEfectivo() {
        return descuentoEfectivo;
    }

    public void setDescuentoEfectivo(Float descuentoEfectivo) {
        this.descuentoEfectivo = descuentoEfectivo;
    }

    public Float getDescuentoSocios() {
        return descuentoSocios;
    }

    public void setDescuentoSocios(Float descuentoSocios) {
        this.descuentoSocios = descuentoSocios;
    }

    public Float getDescuentoNoSocios() {
        return descuentoNoSocios;
    }

    public void setDescuentoNoSocios(Float descuentoNoSocios) {
        this.descuentoNoSocios = descuentoNoSocios;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Set<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Set<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Set<String> getTalles() {
        return talles;
    }

    public void setTalles(Set<String> talles) {
        this.talles = talles;
    }
}
