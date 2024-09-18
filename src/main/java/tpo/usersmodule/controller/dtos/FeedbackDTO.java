package tpo.usersmodule.controller.dtos;

import tpo.usersmodule.model.entity.Feedback;

import java.time.LocalDate;

public class FeedbackDTO {

	private int id;
	String descripcion;
	LocalDate fechaPublicacion;


	public FeedbackDTO(Feedback feedback) {
		this.id = feedback.getId();
		this.descripcion = feedback.getDescripcion();
		this.fechaPublicacion = feedback.getFechaPublicacion();
	}

	public FeedbackDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
