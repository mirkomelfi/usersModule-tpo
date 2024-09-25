package tpo.usersmodule.controller.dtos;

import tpo.usersmodule.model.entity.Direccion;
import tpo.usersmodule.model.entity.Usuario;

import java.time.LocalDate;


public class UsuarioDTO {
	private int dni;
	private String nombre;
	private String apellido;
	private String rol;
	private int telefono;
	LocalDate fechaNacimiento;
	private Direccion direccion;
	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(Usuario user) {
		super();
		this.dni = user.getDni();
		this.telefono = user.getTelefono();
		this.direccion = user.getDireccion();
		this.nombre = user.getNombre();
		this.apellido = user.getApellido();
		this.fechaNacimiento=user.getFechaNacimiento();
		this.rol = user.getRol();
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
}
