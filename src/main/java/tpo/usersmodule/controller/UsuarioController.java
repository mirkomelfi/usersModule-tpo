package tpo.usersmodule.controller;

import api.tpo_entrega2.app.controller.dtos.AreaComunDTO;
import api.tpo_entrega2.app.controller.dtos.UnidadDTO;
import api.tpo_entrega2.app.controller.dtos.UsuarioDTO;
import api.tpo_entrega2.app.model.entity.AreaComun;
import api.tpo_entrega2.app.model.entity.Unidad;
import api.tpo_entrega2.app.model.entity.Usuario;
import api.tpo_entrega2.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    // RUTAS DE ADMIN
    @PreAuthorize("hasAuthority('ROL_ADMIN')")
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
    @PreAuthorize("hasAuthority('ROL_ADMIN')")
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

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
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

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
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
    @GetMapping("/misUnidades")
    public ResponseEntity<?> getUnidades() {
        try {
            List<Unidad> unidades = usuarioService.getUnidades();
            List<UnidadDTO> dtos = convertirUnidadesADTO(unidades);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @GetMapping("/misAreas")
    public ResponseEntity<?> getAreas() {
        try {
            List<AreaComun> areas = this.usuarioService.getAreas();
            List<AreaComunDTO> dtos = this.convertirAreasADTO(areas);
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }
    }

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

    private List<UnidadDTO> convertirUnidadesADTO(List<Unidad> unidades) {
        List<UnidadDTO> dtos = new ArrayList<UnidadDTO>();
        if (unidades != null) {
            for (Unidad u : unidades) {
                dtos.add(new UnidadDTO(u));
            }
        }
        return dtos;
    }

    private List<AreaComunDTO> convertirAreasADTO(List<AreaComun> areas) {
        List<AreaComunDTO> dtos = new ArrayList<AreaComunDTO>();
        if (areas != null) {
            for (AreaComun u : areas) {
                dtos.add(new AreaComunDTO(u));
            }
        }
        return dtos;
    }

}
