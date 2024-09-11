package tpo.usersmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tpo.usersmodule.model.entity.Noticia;
import tpo.usersmodule.service.INoticiaService;

import javax.crypto.SecretKey;
import java.util.List;

@RestController
@RequestMapping("")
public class NoticiaController {
    @Autowired
    private INoticiaService noticiaService;
    
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @GetMapping("/admin/noticias")
    public ResponseEntity<?> getNoticias() {
        try {
            List<Noticia> nots = noticiaService.findAll();

            return new ResponseEntity<>(nots, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @GetMapping("/admin/noticias/{id}")
    public ResponseEntity<?> getNoticia(@PathVariable int id) {
        try {
            Noticia not = noticiaService.findById(id);
            return new ResponseEntity<>(not, HttpStatus.OK);
        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }
    
    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PostMapping("/admin/noticias")
    public ResponseEntity<?> addNoticia(@RequestBody Noticia not) {
        String msj = "";

        try {
            noticiaService.save(not);
            msj = "Noticia guardada exitosamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PutMapping("/admin/noticias/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody Noticia not) {
        String msj;
        try {
            noticiaService.update(id, not);
            msj = "Noticia actualizada correctamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @DeleteMapping("/admin/noticias/{userDni}")
    public ResponseEntity<?> deleteNoticia(@PathVariable int id) {
        String msj;
        try {
            noticiaService.deleteById(id);
            msj = "Noticia eliminada correctamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }
    }
    

}
