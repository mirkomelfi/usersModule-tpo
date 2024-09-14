package tpo.usersmodule.service;

//import tpo.usersmodule.config.JwtAuthFilter;
import tpo.usersmodule.model.dao.IUsuarioDAO;
import tpo.usersmodule.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IUsuarioDAO usuarioDAO;
    //@Autowired
    //private JwtAuthFilter jwt;

    @Override
    public Usuario findByDni(int dni) {
        Usuario user = usuarioDAO.findByDni(dni);
        if (user != null) {
            return user;
        }
        throw new Error /*NotFoundError*/("El usuario no existe");
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
            throw new Error /*NotFoundError*/("No existe ningun usuario con username:" + username);
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
    public List<Usuario> findByRol(String rol) {
        List<Usuario> usuarios  = usuarioDAO.findByRol(rol);
        if (usuarios == null)
            throw new Error("Error al buscar los datos (null)");
        if (usuarios.size() == 0)
            throw new Error("No se encontraron usuarios");
        return usuarios;
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
    public Usuario findLogged() {
        try {
           /* Usuario u = this.usuarioDAO.findByUsername(jwt.getUsername());
            return u;
            */
            return null;

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
    public void updateRol(int dni, String rol) {
        try {
            Usuario usr = this.findByDni(dni);
            usr.setRol(rol);
            this.usuarioDAO.save(usr);
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
