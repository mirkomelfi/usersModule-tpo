package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "feedbacks")
public class Feedback {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "idFeedback", nullable = false)
	private int id;
	@Column(nullable = false)
	String descripcion;
	@Temporal(TemporalType.DATE)
	LocalDate fechaPublicacion;
	@ManyToOne
	Usuario usuario;
	@ManyToOne
	RubroFeedback rubro;

	public Feedback(int id, String descripcion, LocalDate fechaPublicacion, Usuario usuario, RubroFeedback rubro) {
		this.id = id;
		this.descripcion = descripcion;
		this.fechaPublicacion = LocalDate.now();
		this.usuario=usuario;
		this.rubro=rubro;
	}

	public Feedback() {
		this.fechaPublicacion = LocalDate.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RubroFeedback getRubro() {
		return rubro;
	}

	public void setRubro(RubroFeedback rubro) {
		this.rubro = rubro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
