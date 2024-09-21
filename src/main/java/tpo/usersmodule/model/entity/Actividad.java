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
	@OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL)
	List<Imagen> imagenes;
	List<String> dias;
	// List horarios
	// float duracion
	float valor;
	String profesor;

	@ManyToMany(mappedBy = "actividades")
	private List<Usuario> usuarios;


	public Actividad(int id, String nombre, String descripcion, List<String> dias, float valor, String profesor, Usuario usuario) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.dias = new ArrayList<String>();
		this.valor = valor;
		this.profesor = profesor;
		this.imagenes = new ArrayList<Imagen>();
		this.usuarios=new ArrayList<Usuario>();
	}

	public Actividad() {
		this.imagenes = new ArrayList<Imagen>();
		this.dias = new ArrayList<String>();
		this.usuarios=new ArrayList<Usuario>();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
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
