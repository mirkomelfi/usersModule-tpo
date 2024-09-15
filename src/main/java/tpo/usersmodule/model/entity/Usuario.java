package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios",uniqueConstraints = { @UniqueConstraint(columnNames = { "username"}) })
public class Usuario {
	@Id
	int dni;
	@Column(nullable = false)
	String username;
	@Column(nullable = false)
	String password;
	String nombre;
	String apellido;
	String rol;
	//String email;
	@Embedded
	Direccion direccion;
	int telefono;
	@Temporal(TemporalType.DATE)
	LocalDate fechaNacimiento;

	@ManyToMany
	@JoinTable(
			name = "registro_campaña",
			joinColumns = @JoinColumn(name = "dni"),
			inverseJoinColumns = @JoinColumn(name = "idCampaña")
	)
	private List<Campaña> campañasVotadas;

	@OneToMany(mappedBy = "usuarioSolicitante")
	private List<Turno> turnosSolicitados;

	@OneToMany(mappedBy = "usuarioReservado")
	private List<Turno> turnosReservados;

	public Usuario() {
		super();
		this.rol = "ROL_USER";
		this.campañasVotadas=new ArrayList<>();
	}

	public Usuario(String username, String password, String nombre, String apellido, String rol, Direccion direccion, int dni, int telefono,LocalDate fechaNacimiento) {
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
		this.direccion = direccion;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono=telefono;
		this.campañasVotadas=new ArrayList<>();
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

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
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

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public List<Turno> getTurnosSolicitados() {
		return turnosSolicitados;
	}

	public void setTurnosSolicitados(List<Turno> turnosSolicitados) {
		this.turnosSolicitados = turnosSolicitados;
	}

	public List<Turno> getTurnosReservados() {
		return turnosReservados;
	}

	public void setTurnosReservados(List<Turno> turnosReservados) {
		this.turnosReservados = turnosReservados;
	}

	public List<Campaña> getCampañasVotadas() {
		return campañasVotadas;
	}

	public void setCampañasVotadas(List<Campaña> campañasVotadas) {
		this.campañasVotadas = campañasVotadas;
	}
}
