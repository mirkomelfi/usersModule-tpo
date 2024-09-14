package tpo.usersmodule.service;


import tpo.usersmodule.model.entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    public Usuario findByDni(int dni);

    public List<Usuario> findAll();

    public void save(Usuario user);

    public void update(int dni, Usuario usuario);

    public void update(Usuario data);
    public void updateRol(int dni, String rol);
    public void deleteByDni(int dni);

    // NO SE SI HACEN FALTA
    public Usuario findUser(String username, String password);
    public Usuario findByUsername(String username);
    public List<Usuario> findByRol(String rol);
    public Usuario findLogged();



}
