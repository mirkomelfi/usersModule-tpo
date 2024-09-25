package tpo.usersmodule.controller.dtos;

import tpo.usersmodule.model.entity.Usuario;

public class CredencialesDTO {
	private int dni;
	private String username;
	private String password;
	private String nombre;
	private String apellido;
	private String rol;

	public CredencialesDTO() {
		super();
	}

	public CredencialesDTO(Usuario user) {
		super();
		this.dni = user.getDni();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.nombre = user.getNombre();
		this.apellido = user.getApellido();
		this.rol = user.getRol();
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}
