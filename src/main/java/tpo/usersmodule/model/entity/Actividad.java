package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "actividades")
public class Actividad {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "idActividad", nullable = false)
	private int id;
	@Column(nullable = false)
	String nombre;
	@Column(nullable = false)
	String descripcion;
	//String fotos;
	List<String> dias;
	float valor;
	String profesor;


	public Actividad(int id, String nombre, String descripcion, List<String> dias, float valor, String profesor) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.dias = new ArrayList<String>();
		this.valor = valor;
		this.profesor = profesor;
	}

	public Actividad() {

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
