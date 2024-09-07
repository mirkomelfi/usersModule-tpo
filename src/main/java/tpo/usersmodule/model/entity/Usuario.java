package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios",uniqueConstraints = { @UniqueConstraint(columnNames = { "username"}) })
public class User {
	@Column(nullable = false)
	String username;
	String password;
	String nombre;
	String apellido;
	String rol;

	@OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
	List<Reclamo> reclamos;
	@OneToMany(mappedBy = "propietario")
	List<Unidad> propiedades;
	@OneToMany(mappedBy = "inquilino")
	List<Unidad> alquileres;
	@Id
	int dni;
	public User() {
		super();
		this.rol = "ROL_USER";
	}

	public User(String username, String password, String nombre, String apellido, String rol, List<Reclamo> reclamos, List<Unidad> propiedades, List<Unidad> alquileres, int dni) {
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
		this.reclamos = reclamos;
		this.propiedades = propiedades;
		this.alquileres = alquileres;
		this.dni = dni;
	}

	public User(Usuario u) {
		super();
		this.dni = u.getDni();
		this.username = u.getUsername();
		this.password = u.getPassword();
		this.rol = u.getRol();
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

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}


	public List<Reclamo> getReclamos() {
		return reclamos;
	}

	public void setReclamos(List<Reclamo> reclamos) {
		this.reclamos = reclamos;
	}


	public List<Unidad> getPropiedades() {
		return propiedades;
	}

	public void setPropiedades(List<Unidad> propiedades) {
		this.propiedades = propiedades;
	}

	public List<Unidad> getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(List<Unidad> alquileres) {
		this.alquileres = alquileres;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [username=" + username + ", password=" + password + ", nombre=" + nombre + ", apellido="
				+ apellido + ", dni=" + dni + "]";
	}

}
