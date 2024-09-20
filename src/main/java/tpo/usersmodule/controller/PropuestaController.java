package tpo.usersmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tpo.usersmodule.controller.dtos.ImagenDTO;
import tpo.usersmodule.controller.dtos.PropuestaDTO;
import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.model.entity.Propuesta;
import tpo.usersmodule.service.IPropuestaService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class PropuestaController {
    @Autowired
    private IPropuestaService propuestaService;
    
    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @GetMapping("/propuestas")
    public ResponseEntity<?> getPropuestas() {
        try {
            List<Propuesta> nots = propuestaService.findAll();
            List<PropuestaDTO> dtos = convertirPropuestasADTO(nots);
            return new ResponseEntity<>(dtos, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @GetMapping("/propuestas/{id}")
    public ResponseEntity<?> getPropuesta(@PathVariable int id) {
        try {
            Propuesta f = propuestaService.findById(id);
            PropuestaDTO dto = new PropuestaDTO(f);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/propuestas/{dni}")
    public ResponseEntity<?> getPropuestasByUser(@PathVariable int dni) {
        try {
            List<Propuesta> propuestas = propuestaService.findByDni(dni);
            List<PropuestaDTO> dtos = convertirPropuestasADTO(propuestas);
            return new ResponseEntity<>(dtos, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @CrossOrigin
    @PostMapping("/propuestas/{dni}")
    public ResponseEntity<?> addPropuesta(@PathVariable int dni,@RequestBody Propuesta f) {
        String msj = "";

        try {
            int id=propuestaService.save(dni, f);
            msj = "Propuesta guardado exitosamente";
            return new ResponseEntity<>(new Mensaje(msj,id), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }


    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @DeleteMapping("/admin/propuestas/{id}")
    public ResponseEntity<?> deletePropuesta(@PathVariable int id) {
        String msj;
        try {
            propuestaService.deleteById(id);
            msj = "Propuesta eliminado correctamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    //Manejo de imagenes
    @CrossOrigin
    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @PutMapping("/propuestas/{actividadId}/imagenes")
    public ResponseEntity<?> addImagen(@RequestParam("archivo") MultipartFile archivo, @PathVariable int actividadId) {
        String msj;
        try {
            Imagen imagen = new Imagen(archivo.getBytes());
            propuestaService.saveImagen(imagen, actividadId);
            msj = "Imagen subida exitosamente.";
            return ResponseEntity.ok(new Mensaje(msj));
        } catch (IOException e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin
    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @GetMapping("/propuestas/{idActividad}/imagenes/{num}")
    public ResponseEntity<?> getImagenes(@PathVariable int num, @PathVariable int idActividad) {
        String msj;
        try {
            // El numero representa la posicion en el array de imagenes en el actividad
            Imagen imagen = propuestaService.findImagen(idActividad, num);
            ImagenDTO dto = new ImagenDTO(imagen.getDatosImagen());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @DeleteMapping("/admin/propuestas/{idActividad}/imagenes/{num}")
    public ResponseEntity<?> deleteImagen(@PathVariable int num, @PathVariable int idActividad) {
        String msj;
        try {
            propuestaService.deleteImagen(idActividad, num);
            msj = "Imagen eliminada";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    private List<PropuestaDTO> convertirPropuestasADTO(List<Propuesta> propuestas) {
        List<PropuestaDTO> dtos = new ArrayList<PropuestaDTO>();
        if (propuestas != null) {
            for (Propuesta propuesta: propuestas) {
                dtos.add(new PropuestaDTO(propuesta));
            }
        }
        return dtos;
    }
    
    
}
