package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] datosImagen;
    @ManyToOne
    private Noticia noticia;
    @ManyToOne
    private Actividad actividad;

    public Imagen() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Imagen(byte[] datosImagen) {
        super();
        this.datosImagen = datosImagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getDatosImagen() {
        return datosImagen;
    }

    public void setDatosImagen(byte[] datosImagen) {
        this.datosImagen = datosImagen;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    @Override
    public String toString() {
        return "Imagen [id=" + id + ", datosImagen=" + Arrays.toString(datosImagen) + "]";
    }

}
