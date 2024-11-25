package tpo.usersmodule.model.entity;

import java.util.Date;




public class Reclamo {
	public Reclamo(Long id, int idPedido,String usuario, String tipoReclamo, String fecha, String premisa, String comentario,
			String imagen, String estado) {
		super();
		this.id = id;
		this.idPedido=idPedido;
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
	private int idPedido;
    private String usuario;

    private String tipoReclamo;

	private String fecha;

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	private String premisa;

	private String comentario;

	private String imagen;

	private String estado;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
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