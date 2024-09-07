package api.tpo_entrega2.app.controller.dtos;

import api.tpo_entrega2.app.model.entity.Usuario;

import java.util.List;

public class UsuarioDTO {
	private int Dni;
	private String username;
	private String password;
	private String nombre;
	private String apellido;
	private String rol;
	public UsuarioDTO() {
		super();
	}


	public UsuarioDTO(int dni, String username, String password, String nombre, String apellido,
			List<ReclamoDTO> reclamos,String rol) {
		super();
		Dni = dni;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = "ROL_USER";
	}


	public UsuarioDTO(Usuario u) {
		super();
		this.Dni = u.getDni();
		this.username = u.getUsername();
		this.password = u.getPassword();
		this.nombre = u.getNombre();
		this.apellido = u.getApellido();
	}

	public int getDni() {
		return Dni;
	}

	public void setDni(int dni) {
		Dni = dni;
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


	@Override
	public String toString() {
		return "UsuarioDTO [Dni=" + Dni + ", username=" + username + ", password=" + password + ", nombre=" + nombre
				+ ", apellido=" + apellido + "]";
	}


    public Object getRol() {
		return this.rol;
    }
}
