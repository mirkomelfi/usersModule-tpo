package tpo.usersmodule.service;


import tpo.usersmodule.model.entity.Actividad;

import java.util.List;

public interface IActividadService {

    public Actividad findById(int id);
    public List<Actividad> findAll();
    public void save(Actividad actividad);
    public void update(int id, Actividad actividad);
    public void deleteById(int id);




}
