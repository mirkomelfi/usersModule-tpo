package tpo.usersmodule.service;


import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.model.entity.Propuesta;

import java.util.List;

public interface IPropuestaService {

    public Propuesta findById(int id);
    public List<Propuesta> findAll();
    List<Propuesta> findByDni(int dni);
    public int save(Propuesta propuesta);
    public void deleteById(int id);

    //Manejo de imagenes
    void saveImagen(Imagen imagen, int propuestaID);
    Imagen findImagen(int propuestaID, int numeroImagen);
    public void deleteImagen(int propuestaID, int num);





}
