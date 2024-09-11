package tpo.usersmodule.model.dao;

import org.springframework.transaction.annotation.Transactional;
import tpo.usersmodule.model.entity.Usuario;

import java.util.List;

public interface IUsuarioDAO {

    public Usuario findByDni(int dni); // no se si va aca o directo en service
    // public Usuario findById(int id);

    public List<Usuario> findAll();

    public List<Usuario> findByRol(String rol);

    public void save(Usuario user);

    // public void deleteById(int id) ;
    public void deleteByDni(int dni); // no se si va aca o directo en service

    public Usuario findByUsername(String username);

}
