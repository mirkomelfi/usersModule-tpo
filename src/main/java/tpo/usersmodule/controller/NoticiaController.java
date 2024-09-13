package tpo.usersmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tpo.usersmodule.controller.dtos.ActividadDTO;
import tpo.usersmodule.controller.dtos.NoticiaDTO;
import tpo.usersmodule.controller.dtos.ImagenDTO;
import tpo.usersmodule.model.entity.Noticia;
import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.model.entity.Noticia;
import tpo.usersmodule.service.INoticiaService;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class NoticiaController {
    @Autowired
    private INoticiaService noticiaService;
    
    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @GetMapping("/noticias")
    public ResponseEntity<?> getNoticias() {
        try {
            List<Noticia> nots = noticiaService.findAll();
            List<NoticiaDTO> dtos = convertirNoticiasADTO(nots);
            return new ResponseEntity<>(dtos, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @GetMapping("/noticias/{id}")
    public ResponseEntity<?> getNoticia(@PathVariable int id) {
        try {
            Noticia not = noticiaService.findById(id);
            NoticiaDTO dto = new NoticiaDTO(not);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }
    
    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @CrossOrigin
    @PostMapping("/admin/noticias")
    public ResponseEntity<?> addNoticia(@RequestBody Noticia not) {
        String msj = "";

        try {
            int id=noticiaService.save(not);
            msj = "Noticia guardada exitosamente";
            return new ResponseEntity<>(new Mensaje(msj,id), HttpStatus.OK);
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
            msj = "Noticia noticiaualizada correctamente";
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
    @CrossOrigin
    @PutMapping("/admin/noticias/{noticiaId}/imagenes")
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
    @CrossOrigin
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
    @DeleteMapping("/admin/noticias/{idNoticia}/imagenes/{num}")
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

    private List<NoticiaDTO> convertirNoticiasADTO(List<Noticia> noticias) {
        List<NoticiaDTO> dtos = new ArrayList<NoticiaDTO>();
        if (noticias != null) {
            for (Noticia noticia: noticias) {
                dtos.add(new NoticiaDTO(noticia));
            }
        }
        return dtos;
    }
    
}
