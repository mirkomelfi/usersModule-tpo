package tpo.usersmodule.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tpo.usersmodule.controller.dtos.UsuarioDTO;
import tpo.usersmodule.model.entity.Usuario;
import tpo.usersmodule.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    private final int EXPIRATION_TIME_IN_MIN = 10; // En 10 mins expira el token


    @Autowired
    private SecretKey secretKey; // Inyecta la clave secreta


    // LOGIN
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO credentials) {
        Usuario user;
        try {
            user = usuarioService.findUser(credentials.getUsername(), credentials.getPassword());

        } catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        // Crear el token JWT
        String token = Jwts.builder().setSubject(credentials.getUsername()).
                claim("rol", user.getRol())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MIN * 60 * 1000))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        Token response = new Token(token);
        return new ResponseEntity<>(response, HttpStatus.OK);


    }



    // RUTAS DE ADMIN
    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @GetMapping("/admin/usuarios")
    public ResponseEntity<?> getUsers() {
        try {
            List<Usuario> users = usuarioService.findAll();
            List<UsuarioDTO> dtos = new ArrayList<UsuarioDTO>();
            for (Usuario u : users) {
                dtos.add(new UsuarioDTO(u));
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }
    @CrossOrigin
    @GetMapping("/usuarios/{rol}")
    public ResponseEntity<?> getUsersByRol(@PathVariable String rol) {
        try {

            List<Usuario> users = usuarioService.findByRol(rol);
            List<UsuarioDTO> dtos = new ArrayList<UsuarioDTO>();
            for (Usuario u : users) {
                dtos.add(new UsuarioDTO(u));
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }


    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @GetMapping("/admin/usuarios/{userDni}")
    public ResponseEntity<?> getUser(@PathVariable int userDni) {
        try {
            Usuario user = usuarioService.findByDni(userDni);
            UsuarioDTO dto = new UsuarioDTO(user);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin
    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PostMapping("/admin/register")
    public ResponseEntity<?> addUser(@RequestBody Usuario user) {
        String msj = "";

        try {
            usuarioService.save(user);
            msj = "Usuario guardado exitosamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PutMapping("/admin/usuarios/{userDni}")
    public ResponseEntity<?> updateUser(@PathVariable int userDni, @RequestBody Usuario user) {
        String msj;
        try {
            usuarioService.update(userDni, user);
            msj = "Usuario actualizado correctamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PutMapping("/admin/usuarios/{userDni}/rol")
    public ResponseEntity<?> updateRol(@PathVariable int userDni, @RequestBody Usuario user) {
        String msj;
        try {
            usuarioService.updateRol(userDni, user.getRol());
            msj = "Rol del Usuario actualizado correctamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }
    }


   // @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @DeleteMapping("/admin/usuarios/{userDni}")
    public ResponseEntity<?> deleteUser(@PathVariable int userDni) {
        String msj;
        try {
            usuarioService.deleteByDni(userDni);
            msj = "Usuario eliminado correctamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }
    }



    // RUTAS PARA USUARIOS NORMALES

    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @GetMapping("/miPerfil")
    public ResponseEntity<?> getLoggedUser() {
        try {
            Usuario user = usuarioService.findLogged();
            UsuarioDTO dto = new UsuarioDTO(user);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @PutMapping("/cambiarPerfil")
    public ResponseEntity<?> updateLoggedUser(@RequestBody UsuarioDTO data) {
        try {
            Usuario user = new Usuario();
            user.setNombre(data.getNombre());
            user.setApellido(data.getApellido());
            user.setUsername(data.getUsername());
            user.setPassword(data.getPassword());
            usuarioService.update(user);
            return new ResponseEntity<>(new Mensaje("Perfil actualizado"), HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.NOT_FOUND);
        }

    }

}
