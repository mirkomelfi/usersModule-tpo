package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "opciones")
public class Opcion {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "idOpcion", nullable = false)
	private int id;
	@Column(nullable = false)
	String titulo;
	int votos;
	@ManyToOne
	private Campaña campaña;
	boolean opcionGanadora;

	public Opcion(int id, String titulo, Campaña campaña) {
		this.id = id;
		this.titulo = titulo;
		this.votos = 0;
		this.campaña = campaña;
		this.opcionGanadora = false;
	}

	public Opcion() {
		this.opcionGanadora = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	public boolean isOpcionGanadora() {
		return opcionGanadora;
	}

	public void setOpcionGanadora(boolean opcionGanadora) {
		this.opcionGanadora = opcionGanadora;
	}

	public Campaña getCampaña() {
		return campaña;
	}

	public void setCampaña(Campaña campaña) {
		this.campaña = campaña;
	}
}
