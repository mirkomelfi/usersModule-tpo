package tpo.usersmodule.model.dao;

import tpo.usersmodule.model.entity.Actividad;

import java.util.List;

public interface IActividadDAO {

    public Actividad findById(int id);
    public List<Actividad> findAll();
    public int save(Actividad actividad);
    public void deleteById(int id) ;


}
