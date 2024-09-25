package tpo.usersmodule.controller.dtos;

import tpo.usersmodule.model.entity.Feedback;

import java.time.LocalDate;

public class FeedbackDTO {

	private int id;
	String descripcion;
	LocalDate fechaPublicacion;
	String rubro;
	String nombreAutor;
	String apellidoAutor;
	int dniAutor;


	public FeedbackDTO(Feedback feedback) {
		RubroDTO rub=new RubroDTO(feedback.getRubro());
		this.id = feedback.getId();
		this.descripcion = feedback.getDescripcion();
		this.fechaPublicacion = feedback.getFechaPublicacion();
		this.dniAutor=feedback.getUsuario().getDni();
		this.nombreAutor=feedback.getUsuario().getNombre();
		this.apellidoAutor=feedback.getUsuario().getApellido();
		this.rubro=new RubroDTO(feedback.getRubro()).getDescripcion();
	}

	public FeedbackDTO() {

	}

	public String getRubro() {
		return rubro;
	}

	public String getNombreAutor() {
		return nombreAutor;
	}

	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public String getApellidoAutor() {
		return apellidoAutor;
	}

	public void setApellidoAutor(String apellidoAutor) {
		this.apellidoAutor = apellidoAutor;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public int getDniAutor() {
		return dniAutor;
	}

	public void setDniAutor(int dniAutor) {
		this.dniAutor = dniAutor;
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
