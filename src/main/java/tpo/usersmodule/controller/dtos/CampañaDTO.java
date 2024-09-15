package tpo.usersmodule.controller.dtos;

import tpo.usersmodule.model.entity.Campaña;
import tpo.usersmodule.model.entity.Noticia;
import tpo.usersmodule.model.entity.Opcion;

import java.util.ArrayList;
import java.util.List;


public class CampañaDTO {

	private int id;
	String titulo;
	String descripcion;
	List<OpcionDTO> opciones;



	public CampañaDTO(Campaña campaña) {
		this.id = campaña.getId();
		this.titulo = campaña.getTitulo();
		this.descripcion = campaña.getDescripcion();
		this.opciones=convertirOpcionesADTO(campaña.getOpciones());
	}

	public CampañaDTO() {

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

	public List<OpcionDTO> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<OpcionDTO> opciones) {
		this.opciones = opciones;
	}

	private List<OpcionDTO> convertirOpcionesADTO(List<Opcion> opciones) {
		List<OpcionDTO> dtos = new ArrayList<OpcionDTO>();
		if (opciones != null) {
			for (Opcion opcion: opciones) {
				dtos.add(new OpcionDTO(opcion));
			}
		}
		return dtos;
	}

}
