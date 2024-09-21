package tpo.usersmodule.service;


import tpo.usersmodule.model.entity.Actividad;
import tpo.usersmodule.model.entity.Imagen;

import java.util.List;

public interface IActividadService {

    public Actividad findById(int id);
    public List<Actividad> findAll();
    public int save(Actividad actividad);
    public void inscribirByDni(int id, int dni);
    public void update(int id, Actividad actividad);
    public void deleteById(int id);

    //Manejo de imagenes
    void saveImagen(Imagen imagen, int reclamoId);
    Imagen findImagen(int actividadID, int numeroImagen);
    public void deleteImagen(int actividadID, int num);
    public List<Imagen> findImagenes(int idAct);




}
