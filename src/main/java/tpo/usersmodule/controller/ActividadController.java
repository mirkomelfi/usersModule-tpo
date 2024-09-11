package tpo.usersmodule.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tpo.usersmodule.model.entity.Actividad;
import tpo.usersmodule.service.IActividadService;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("")
public class ActividadController {
    @Autowired
    private IActividadService actividadService;

    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @GetMapping("/admin/actividades")
    public ResponseEntity<?> getActividades() {
        try {
            List<Actividad> acts = actividadService.findAll();

            return new ResponseEntity<>(acts, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @GetMapping("/admin/actividades/{id}")
    public ResponseEntity<?> getActividad(@PathVariable int id) {
        try {
            Actividad act = actividadService.findById(id);
            return new ResponseEntity<>(act, HttpStatus.OK);
        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PostMapping("/admin/actividades")
    public ResponseEntity<?> addActividad(@RequestBody Actividad act) {
        String msj = "";

        try {
            actividadService.save(act);
            msj = "Actividad guardada exitosamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PutMapping("/admin/actividads/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody Actividad act) {
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

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @DeleteMapping("/admin/actividads/{userDni}")
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


}
