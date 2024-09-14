package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "campañas")
public class Campaña {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "idCampaña", nullable = false)
	private int id;
	@Column(nullable = false)
	String titulo;
	@Column(nullable = false)
	String descripcion;
    @OneToMany(mappedBy = "campaña", cascade = CascadeType.ALL)
	List<Opcion> opciones;
	boolean estado;

    public Campaña() {
        this.estado = true;
    }

    public Campaña(int id, String titulo, String descripcion, List<Opcion> opciones, boolean estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.opciones = opciones;
        this.estado = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
