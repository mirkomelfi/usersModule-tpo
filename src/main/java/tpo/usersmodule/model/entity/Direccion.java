package tpo.usersmodule.model.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Direccion {
    String ciudad;
    String calle;
    int numero;
    int codPostal;

    public Direccion() {
        super();
    }

    public Direccion(String ciudad, String calle, int numero, int codPostal) {
        super();
        this.ciudad = ciudad;
        this.calle = calle;
        this.numero = numero;
        this.codPostal = codPostal;
    }

    public Direccion(Direccion direc) {
        super();
        this.ciudad = direc.ciudad;
        this.calle = direc.calle;
        this.numero = direc.numero;
        this.codPostal = direc.codPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    @Override
    public String toString() {
        return "Direccion [ciudad=" + ciudad + ", calle=" + calle + ", numero=" + numero + ", codPostal=" + codPostal + "]";
    }

}
