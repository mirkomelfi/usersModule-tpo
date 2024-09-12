package tpo.usersmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tpo.usersmodule.controller.dtos.ImagenDTO;
import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.model.entity.Noticia;
import tpo.usersmodule.service.INoticiaService;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("")
public class NoticiaController {
    @Autowired
    private INoticiaService noticiaService;
    
    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
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

    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
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
    
    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
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

    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
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

    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @DeleteMapping("/admin/noticias/{id}")
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


    //Manejo de imagenes

    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @PutMapping("/noticias/{noticiaId}/imagenes")
    public ResponseEntity<?> addImagen(@RequestParam("archivo") MultipartFile archivo, @PathVariable int noticiaId) {
        String msj;
        try {
            Imagen imagen = new Imagen(archivo.getBytes());
            noticiaService.saveImagen(imagen, noticiaId);
            msj = "Imagen subida exitosamente.";
            return ResponseEntity.ok(new Mensaje(msj));
        } catch (IOException e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @GetMapping("/noticias/{idNoticia}/imagenes/{num}")
    public ResponseEntity<?> getImagenes(@PathVariable int num, @PathVariable int idNoticia) {
        String msj;
        try {
            // El numero representa la posicion en el array de imagenes en el noticia
            Imagen imagen = noticiaService.findImagen(idNoticia, num);
            ImagenDTO dto = new ImagenDTO(imagen.getDatosImagen());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @DeleteMapping("/noticias/{idNoticia}/imagenes/{num}")
    public ResponseEntity<?> deleteImagen(@PathVariable int num, @PathVariable int idNoticia) {
        String msj;
        try {
            noticiaService.deleteImagen(idNoticia, num);
            msj = "Imagen eliminada";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }

}
