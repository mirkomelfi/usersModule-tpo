package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "noticias")
public class Noticia {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "idNoticia", nullable = false)
	private int id;
	@Column(nullable = false)
	String titulo;
	@Column(nullable = false)
	String descripcion;
	//String fotos;
	@Temporal(TemporalType.DATE)
	LocalDate fecha;

	public Noticia(int id, String titulo, String descripcion, LocalDate fecha) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fecha = fecha;
	}

	public Noticia() {

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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}
