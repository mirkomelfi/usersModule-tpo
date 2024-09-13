package tpo.usersmodule.controller.dtos;

import jakarta.persistence.ManyToOne;
import tpo.usersmodule.model.entity.Noticia;
import tpo.usersmodule.model.entity.Turno;
import tpo.usersmodule.model.entity.Usuario;

import java.sql.Timestamp;
import java.time.LocalDate;

public class TurnoDTO {

	private int id;
	private Timestamp fechaHora;
	private int usuarioSolicitante;
	private int usuarioReservado;


	public TurnoDTO(Turno t) {
		this.id = t.getId();
		this.usuarioSolicitante = t.getUsuarioSolicitante().getDni();
		this.usuarioReservado = t.getUsuarioReservado().getDni();
		this.fechaHora = t.getFechaHora();
	}

	public TurnoDTO() {

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

	public int getUsuarioSolicitante() {
		return usuarioSolicitante;
	}

	public void setUsuarioSolicitante(int usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
	}

	public int getUsuarioReservado() {
		return usuarioReservado;
	}

	public void setUsuarioReservado(int usuarioReservado) {
		this.usuarioReservado = usuarioReservado;
	}
}
