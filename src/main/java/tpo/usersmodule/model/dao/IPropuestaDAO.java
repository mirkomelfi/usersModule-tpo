package tpo.usersmodule.model.dao;

import tpo.usersmodule.model.entity.Propuesta;

import java.util.List;

public interface IPropuestaDAO {

    public Propuesta findById(int id);
    public List<Propuesta> findAll();
    public List<Propuesta> findAllByDni(int dni);
    public int save(Propuesta propuesta);
    public void deleteById(int id) ;


}
