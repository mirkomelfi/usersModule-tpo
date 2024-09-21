package tpo.usersmodule.controller.dtos;

import tpo.usersmodule.model.entity.RubroFeedback;


public class RubroDTO {

	private int id;
	String descripcion;


	public RubroDTO(RubroFeedback rubro) {
		this.id = rubro.getIdRubro();
		this.descripcion = rubro.getDescripcion();
	}

	public RubroDTO() {

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
	
}
