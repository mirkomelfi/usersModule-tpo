package tpo.usersmodule.controller;

public class Mensaje {

    String msj;
    int id;

    public Mensaje(String msj) {
        super();
        this.msj = msj;
    }
    public Mensaje(String msj, int id) {
        super();
        this.msj = msj;
        this.id = id;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
