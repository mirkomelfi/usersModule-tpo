package tpo.usersmodule.controller.dtos;

import tpo.usersmodule.model.entity.Propuesta;

import java.time.LocalDate;

public class PropuestaDTO {

	private int id;
	String titulo;
	String descripcion;

	LocalDate fechaPublicacion;


	public PropuestaDTO(Propuesta propuesta) {
		this.id = propuesta.getId();
		this.titulo = propuesta.getTitulo();
		this.descripcion = propuesta.getDescripcion();
		this.fechaPublicacion = propuesta.getFechaPublicacion();
	}

	public PropuestaDTO() {

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

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
}
