package tpo.usersmodule.model.dao;

import tpo.usersmodule.model.entity.Campaña;
import tpo.usersmodule.model.entity.Opcion;

import java.util.List;

public interface ICampañaDAO {

    public Campaña findById(int id);
    public List<Opcion> findOpcionGanadora(int id);
    public List<Campaña> findAll();
    public List<Campaña> findAbiertas();
    public List<Campaña> findCerradas();
    public void save(Campaña campaña);
    public void deleteById(int id) ;


}
