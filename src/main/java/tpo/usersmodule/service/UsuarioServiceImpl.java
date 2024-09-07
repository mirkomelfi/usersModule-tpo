package api.tpo_entrega2.app.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import api.tpo_entrega2.app.errores.NotFoundError;
import api.tpo_entrega2.app.model.dao.IAreaComunDAO;
import api.tpo_entrega2.app.model.dao.IUsuarioDAO;
import api.tpo_entrega2.app.model.entity.AreaComun;
import api.tpo_entrega2.app.model.entity.Edificio;
import api.tpo_entrega2.app.model.entity.Unidad;
import api.tpo_entrega2.app.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import api.tpo_entrega2.app.config.JwtAuthFilter;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IUsuarioDAO usuarioDAO;
    @Autowired
    private IAreaComunDAO areaDAO;
    @Autowired
    private JwtAuthFilter jwt;

    @Override
    public Usuario findByDni(int dni) {
        Usuario user = usuarioDAO.findByDni(dni);
        if (user != null) {
            return user;
        }
        throw new NotFoundError("El usuario no existe");
    }

    @Override
    public Usuario findUser(String username, String password) {
        Usuario user = null;
        try {
            user = usuarioDAO.findByUsername(username);

        } catch (Throwable e) {
            throw new Error("Ocurrio un problema al buscar el usuario");
        }
        if (user == null)
            throw new NotFoundError("No existe ningun usuario con username:" + username);
        if (checkPassword(password, user.getPassword())) {
            return user;
        }
        throw new Error("Contraseña incorrecta");
    }

    @Override
    public Usuario findByUsername(String username) {
        Usuario user = usuarioDAO.findByUsername(username);
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = usuarioDAO.findAll();
        if (usuarios == null)
            throw new Error("Error al buscar los datos (null)");
        if (usuarios.size() == 0)
            throw new Error("No se encontraron usuarios");
        return usuarios;
    }

    @Override
    public void save(Usuario user) {
        Usuario usr = usuarioDAO.findByDni(user.getDni());
        if (usr != null) {
            throw new Error("Ya existe usuario con este DNI");
        }

        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            usuarioDAO.save(user);
            return;
        } catch (Exception e) {
            throw new Error("Error interno en la BD");
        }

    }

    @Override
    public void deleteByDni(int dni) {
        Usuario user;
        try {
            user = usuarioDAO.findByDni(dni);
        } catch (Throwable e) {
            throw new Error("Error interno en la BD: " + dni);
        }

        if (user == null) {
            throw new Error("El usuario ingresado no existe");
        }
        if (user.getRol().contentEquals("ROL_ADMIN")) {
            throw new Error("No pueden eliminarse usuarios ADMIN");
        }
        usuarioDAO.deleteByDni(dni);

    }

    @Override
    public void update(int dni, Usuario data) {

        try {
            Usuario newUser = usuarioDAO.findByDni(dni);
            if (newUser != null) {
                this.doUpdates(data, newUser);
                usuarioDAO.save(newUser);
            } else {
                throw new Error("El usuario ingresado no existe");
            }
            return;//DataIntegrityViolationException
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new Error("Error en los datos ingresados. Puede que el nombre de usuario ya esté en uso");
        } catch (Throwable e) {
            e.printStackTrace();
            throw new Error("Error al guardar el usuario." + e.getMessage());
        }

    }

    @Override
    public List<Unidad> getUnidades() {
        String username = jwt.getUsername();
        Usuario usr = this.findByUsername(username);
        Set<Unidad> unidades = new HashSet<Unidad>();
        List<Unidad> result = new ArrayList<Unidad>();
        if (usr == null)
            throw new Error("No se encontro el usuario");
        for (Unidad u : usr.getAlquileres()) {

            if (u.getResponsable().getDni() == usr.getDni())
                unidades.add(u);
        }
        for (Unidad u : usr.getPropiedades()) {
            if (u.getResponsable().getDni() == usr.getDni())
                unidades.add(u);
        }
        for (Unidad u : unidades) {
            result.add(u);
        }
        if (unidades.isEmpty())
            throw new Error("No hay unidades para este usuario");
        return result;
    }

    @Override
    public Usuario findLogged() {
        try {
            Usuario u = this.usuarioDAO.findByUsername(jwt.getUsername());
            return u;

        } catch (Throwable e) {
            throw new Error(e.getMessage());
        }

    }

    @Override
    public void update(Usuario data) {
        try {
            Usuario usr = this.findLogged();
            this.doUpdates(data, usr);
            this.usuarioDAO.save(usr);
        } catch (Throwable e) {
            throw new Error(e.getMessage());
        }

    }

    @Override
    public List<AreaComun> getAreas() {
        try {
            Usuario usr = this.findLogged();
            Set<Edificio> edificios = new HashSet<Edificio>();
            List<AreaComun> areas = new ArrayList<AreaComun>();
            for (Unidad u : usr.getAlquileres()) {
                edificios.add(u.getEdificio());
            }
            for (Unidad u : usr.getPropiedades()) {
                edificios.add(u.getEdificio());
            }
            for (Edificio e : edificios) {
                areas.addAll(e.getAreasComunes());
            }
            if (areas.isEmpty())
                throw new Error("No hay areas para este usuario");
            return areas;
        } catch (Throwable e) {
            throw new Error(e.getMessage());
        }
    }

    private boolean checkPassword(String password, String passwordDB) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordMatch = passwordEncoder.matches(password, passwordDB);

        return isPasswordMatch;
    }

    private Usuario doUpdates(Usuario data, Usuario u) {
        if (data.getNombre() != null)
            u.setNombre(data.getNombre());
        if (data.getApellido() != null)
            u.setApellido(data.getApellido());
        if (data.getUsername() != null) {
            if (u.getRol().contentEquals("ROL_ADMIN"))
                throw new Error("No esta permitido cambiar el username de este usuario");
            u.setUsername(data.getUsername());
        }
        if (data.getPassword() != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            u.setPassword(passwordEncoder.encode(data.getPassword()));
        }
        return u;

    }

}
