package tpo.usersmodule.controller.dtos;

import jakarta.persistence.*;
import tpo.usersmodule.model.entity.Actividad;
import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.model.entity.Noticia;

import java.time.LocalDate;
import java.util.List;

public class NoticiaDTO {

	private int id;
	String titulo;
	String descripcion;

	LocalDate fechaPublicacion;


	public NoticiaDTO(Noticia noticia) {
		this.id = noticia.getId();
		this.titulo = noticia.getTitulo();
		this.descripcion = noticia.getDescripcion();
		this.fechaPublicacion = noticia.getFechaPublicacion();
	}

	public NoticiaDTO() {

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
