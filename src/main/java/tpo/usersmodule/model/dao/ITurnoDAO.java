package tpo.usersmodule.model.dao;

import tpo.usersmodule.model.entity.Turno;

import java.util.List;

public interface ITurnoDAO {

    public Turno findById(int id);
    public List<Turno> findAll(int dni);
    public void save(Turno turno);
    public void deleteById(int id) ;


}
