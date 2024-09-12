package tpo.usersmodule.controller.dtos;

import tpo.usersmodule.model.entity.Actividad;
import java.util.ArrayList;
import java.util.List;

public class ActividadDTO {

	private int id;
	String nombre;
	String descripcion;
	List<String> dias;
	float valor;
	String profesor;


	public ActividadDTO(Actividad act) {
		this.id = act.getId();
		this.nombre = act.getNombre();
		this.descripcion = act.getDescripcion();
		this.dias = act.getDias();
		this.valor = act.getValor();
		this.profesor = act.getProfesor();
	}

	public ActividadDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<String> getDias() {
		return dias;
	}

	public void setDias(List<String> dias) {
		this.dias = dias;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}


}
