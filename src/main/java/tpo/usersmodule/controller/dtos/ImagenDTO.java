package tpo.usersmodule.controller.dtos;

public class ImagenDTO {
    private byte[] datosImagen;

    public ImagenDTO() {
        super();
    }

    public ImagenDTO(byte[] datosImagen) {
        super();
        this.datosImagen = datosImagen;
    }

    public byte[] getDatosImagen() {
        return datosImagen;
    }

    public void setDatosImagen(byte[] datosImagen) {
        this.datosImagen = datosImagen;
    }

}
