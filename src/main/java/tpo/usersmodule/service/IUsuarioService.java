package api.tpo_entrega2.app.service;

import java.util.List;

import api.tpo_entrega2.app.model.entity.AreaComun;
import api.tpo_entrega2.app.model.entity.Unidad;
import api.tpo_entrega2.app.model.entity.Usuario;

public interface IUsuarioService {

    public Usuario findByDni(int dni); // no se si va aca o directo en service
    // public Usuario findById(int id);

    public List<Usuario> findAll();

    public void save(Usuario user);

    // public void deleteById(int id) ;
    public void deleteByDni(int dni); // no se si va aca o directo en service

    public void update(int dni, Usuario usuario);

    public Usuario findUser(String username, String password);

    public Usuario findByUsername(String username);

    public List<Unidad> getUnidades();

    public Usuario findLogged();

    public void update(Usuario data);

    public List<AreaComun> getAreas();
}
