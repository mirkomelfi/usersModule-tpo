package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "propuestas")
public class Propuesta {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "idPropuesta", nullable = false)
	private int id;
	@Column(nullable = false)
	String titulo;
	@Column(nullable = false)
	String descripcion;
	@OneToMany(mappedBy = "propuesta", cascade = CascadeType.ALL)
	List<Imagen> imagenes;
	@Temporal(TemporalType.DATE)
	LocalDate fechaPublicacion;
	@ManyToOne
	Usuario usuario;

	public Propuesta(int id, String titulo, String descripcion, LocalDate fechaPublicacion, Usuario usuario) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaPublicacion = LocalDate.now();
		this.imagenes = new ArrayList<Imagen>();
	}

	public Propuesta() {
		this.fechaPublicacion = LocalDate.now();
		this.imagenes = new ArrayList<Imagen>();
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

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
