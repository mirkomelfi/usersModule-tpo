package tpo.usersmodule.model.entity;

import java.sql.Date;




public class Reclamo {
	public Reclamo(Long id, String usuario, String tipoReclamo, Date fecha, String premisa, String comentario,
			String imagen, String estado) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.tipoReclamo = tipoReclamo;
		this.fecha = fecha;
		this.premisa = premisa;
		this.comentario = comentario;
		this.imagen = imagen;
		this.estado = estado;
	}
	
	public Reclamo() {
		
	}

    private Long id;

    private String usuario;

    private String tipoReclamo;

	private Date fecha;

	private String premisa;

	private String comentario;

	private String imagen;

	private String estado;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getPremisa() {
		return premisa;
	}

	public void setPremisa(String premisa) {
		this.premisa = premisa;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoReclamo() {
		return tipoReclamo;
	}

	public void setTipoReclamo(String tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}