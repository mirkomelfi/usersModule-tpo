package tpo.usersmodule.model.entity;

public class Inversion {
    String username;
    float monto;

    public Inversion(String username, float monto) {
        this.username = username;
        this.monto = monto;
    }

    public Inversion() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
}
