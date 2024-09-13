package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "turnos")
public class Turno {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "idTurno", nullable = false)
	private int id;
	private boolean reservado;
	private LocalDateTime fechaHora;
	@ManyToOne
	private Usuario usuarioSolicitante;
	@ManyToOne
	private Usuario usuarioReservado;

	@ManyToOne
	private Agenda agenda;

	public Turno() {
	}

	public Turno(int id, boolean reservado, LocalDateTime fechaHora, Usuario usuarioSolicitante, Usuario usuarioReservado, Agenda agenda) {
		this.id = id;
		this.reservado = reservado;
		this.fechaHora = fechaHora;
		this.usuarioSolicitante = usuarioSolicitante;
		this.usuarioReservado = usuarioReservado;
		this.agenda = agenda;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Usuario getUsuarioSolicitante() {
		return usuarioSolicitante;
	}

	public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
	}

	public Usuario getUsuarioReservado() {
		return usuarioReservado;
	}

	public void setUsuarioReservado(Usuario usuarioReservado) {
		this.usuarioReservado = usuarioReservado;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
}
