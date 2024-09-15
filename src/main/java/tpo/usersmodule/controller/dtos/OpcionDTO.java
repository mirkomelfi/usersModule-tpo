package tpo.usersmodule.controller.dtos;

import tpo.usersmodule.model.entity.Opcion;


public class OpcionDTO {

	private int id;
	String titulo;
	int votos;
	


	public OpcionDTO(Opcion opcion) {
		this.id = opcion.getId();
		this.titulo = opcion.getTitulo();
		this.votos = opcion.getVotos();
	}

	public OpcionDTO() {

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

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

}
