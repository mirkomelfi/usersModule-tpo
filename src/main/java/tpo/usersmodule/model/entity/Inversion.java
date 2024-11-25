package tpo.usersmodule.model.entity;

import java.time.LocalDateTime;

public class Inversion {
    String usuario;
    float amount;
    String note;
    String date;

    public Inversion() {
    }

    public Inversion(String usuario, float amount, String note, String date) {
        this.usuario = usuario;
        this.amount = amount;
        this.note = note;
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
