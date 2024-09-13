package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agendas")
public class Agenda {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "idAgenda", nullable = false)
	private int id;
	@OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
	List<Turno> turnos;
	@ElementCollection
	private List<DayOfWeek> diasDisponibles;

	@ElementCollection
	private List<LocalTime> horasDisponibles;

	public Agenda() {

		this.turnos = new ArrayList<Turno>();
		diasDisponibles.add(DayOfWeek.MONDAY);
		diasDisponibles.add(DayOfWeek.WEDNESDAY);
		horasDisponibles.add(LocalTime.of(9, 0));
		horasDisponibles.add(LocalTime.of(10, 0));
		horasDisponibles.add(LocalTime.of(11, 0));
		horasDisponibles.add(LocalTime.of(12, 0));

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public List<DayOfWeek> getDiasDisponibles() {
		return diasDisponibles;
	}

	public void setDiasDisponibles(List<DayOfWeek> diasDisponibles) {
		this.diasDisponibles = diasDisponibles;
	}

	public List<LocalTime> getHorasDisponibles() {
		return horasDisponibles;
	}

	public void setHorasDisponibles(List<LocalTime> horasDisponibles) {
		this.horasDisponibles = horasDisponibles;
	}
}
