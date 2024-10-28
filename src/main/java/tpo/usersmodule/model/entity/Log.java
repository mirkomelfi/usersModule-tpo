package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "logs")
public class Log {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    String msj;
    LocalDate fechaPublicacion;

    public Log(String msj) {
        this.msj = msj;
        this.fechaPublicacion = LocalDate.now();
    }

    public Log() {

    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }
}
