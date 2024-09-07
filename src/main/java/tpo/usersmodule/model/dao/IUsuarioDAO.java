package tpo.usersmodule.model.dao;

import api.tpo_entrega2.app.model.entity.Usuario;

import java.util.List;

public interface IUserDAO {

    public Usuario findByDni(int dni); // no se si va aca o directo en service
    // public Usuario findById(int id);

    public List<Usuario> findAll();

    public void save(Usuario user);

    // public void deleteById(int id) ;
    public void deleteByDni(int dni); // no se si va aca o directo en service

    public Usuario findByUsername(String username);

}
