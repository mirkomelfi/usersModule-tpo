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
	private UsuarioDTO usuarioSolicitante;
	private UsuarioDTO usuarioReservado;


	public TurnoDTO(Turno t) {
		this.id = t.getId();
		this.usuarioSolicitante = new UsuarioDTO(t.getUsuarioSolicitante());
		this.usuarioReservado = new UsuarioDTO(t.getUsuarioReservado());
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

	public UsuarioDTO getUsuarioSolicitante() {
		return usuarioSolicitante;
	}

	public void setUsuarioSolicitante(UsuarioDTO usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
	}

	public UsuarioDTO getUsuarioReservado() {
		return usuarioReservado;
	}

	public void setUsuarioReservado(UsuarioDTO usuarioReservado) {
		this.usuarioReservado = usuarioReservado;
	}
}
