package tpo.usersmodule.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tpo.usersmodule.controller.dtos.ActividadDTO;
import tpo.usersmodule.controller.dtos.ImagenDTO;
import tpo.usersmodule.model.entity.Actividad;
import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.service.IActividadService;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("")
public class ActividadController {
    @Autowired
    private IActividadService actividadService;

    @CrossOrigin
    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @GetMapping("/actividades")
    public ResponseEntity<?> getActividades() {
        try {
            List<Actividad> acts = actividadService.findAll();
            List<ActividadDTO> dtos = convertirActsADTO(acts); // Xq sino vienen con el listado de imagenes y se arma un bucle infinito
            return new ResponseEntity<>(dtos, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }
    @CrossOrigin
    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @GetMapping("/actividades/{id}")
    public ResponseEntity<?> getActividad(@PathVariable int id) {
        try {
            Actividad act = actividadService.findById(id);
            ActividadDTO dto = new ActividadDTO(act); // Xq sino vienen con el listado de imagenes y se arma un bucle infinito

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }
    @CrossOrigin
    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PostMapping("/admin/actividades")
    public ResponseEntity<?> addActividad(@RequestBody Actividad act) {
        String msj = "";

        try {
            int id=actividadService.save(act);

            msj = "Actividad guardada exitosamente";
            return new ResponseEntity<>(new Mensaje(msj,id), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @CrossOrigin
    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PostMapping("/actividades/{id}/inscripciones/{dni}")
    public ResponseEntity<?> addActividadToUser(@PathVariable int id,@PathVariable int dni) {
        String msj = "";

        try {
            actividadService.inscribirByDni(id,dni);

            msj = "Inscripcion exitosa";
            return new ResponseEntity<>(new Mensaje(msj,id), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PutMapping("/admin/actividades/{id}")
    public ResponseEntity<?> updateActividad(@PathVariable int id, @RequestBody Actividad act) {
        String msj;
        try {
            actividadService.update(id, act);
            msj = "Actividad actualizada correctamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @DeleteMapping("/admin/actividades/{id}")
    public ResponseEntity<?> deleteActividad(@PathVariable int id) {
        String msj;
        try {
            actividadService.deleteById(id);
            msj = "Actividad eliminada correctamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }
    }


    //Manejo de imagenes
    @CrossOrigin
    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @PutMapping("/admin/actividades/{actividadId}/imagenes")
    public ResponseEntity<?> addImagen(@RequestParam("archivo") MultipartFile archivo, @PathVariable int actividadId) {
        String msj;
        try {
            Imagen imagen = new Imagen(archivo.getBytes());
            actividadService.saveImagen(imagen, actividadId);
            msj = "Imagen subida exitosamente.";
            return ResponseEntity.ok(new Mensaje(msj));
        } catch (IOException e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin
    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @GetMapping("/admin/actividades/{idActividad}/imagenes/{num}")
    public ResponseEntity<?> getImagenes(@PathVariable int num, @PathVariable int idActividad) {
        String msj;
        try {
            // El numero representa la posicion en el array de imagenes en el actividad
            Imagen imagen = actividadService.findImagen(idActividad, num);
            ImagenDTO dto = new ImagenDTO(imagen.getDatosImagen());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @DeleteMapping("/admin/actividades/{idActividad}/imagenes/{num}")
    public ResponseEntity<?> deleteImagen(@PathVariable int num, @PathVariable int idActividad) {
        String msj;
        try {
            actividadService.deleteImagen(idActividad, num);
            msj = "Imagen eliminada";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    // Por ahora este no se usa
    @GetMapping("/admin/actividades/{actividadID}/imagenes")
    public ResponseEntity<?> getArchivos (@PathVariable int actividadID) throws IOException {

        try {
            List<Imagen> result = actividadService.findImagenes(actividadID);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(new Mensaje("No se encontraron imagenes."),HttpStatus.NOT_FOUND);
        }
        catch(Throwable e) {return new ResponseEntity<>(new Mensaje(e.getMessage()),HttpStatus.NOT_ACCEPTABLE);}
    }

    private List<ActividadDTO> convertirActsADTO(List<Actividad> acts) {
        List<ActividadDTO> dtos = new ArrayList<ActividadDTO>();
        if (acts != null) {
            for (Actividad act: acts) {
                dtos.add(new ActividadDTO(act));
            }
        }
        return dtos;
    }

}
