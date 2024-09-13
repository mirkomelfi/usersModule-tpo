package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "turnos")
public class Turno {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "idTurno", nullable = false)
	private int id;
	private Timestamp fechaHora;
	@ManyToOne
	private Usuario usuarioSolicitante;
	@ManyToOne
	private Usuario usuarioReservado;

	//@ManyToOne
	//private Agenda agenda;

	public Turno() {
	}

	public Turno(int id, Timestamp fechaHora, Usuario usuarioSolicitante, Usuario usuarioReservado) {
		this.id = id;
		this.fechaHora = fechaHora;
		this.usuarioSolicitante = usuarioSolicitante;
		this.usuarioReservado = usuarioReservado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Timestamp fechaHora) {
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

	@Override
	public String toString() {
		return "Turno{" +
				"id=" + id +
				", fechaHora=" + fechaHora +
				", usuarioSolicitante=" + usuarioSolicitante +
				", usuarioReservado=" + usuarioReservado +
				'}';
	}
}
