package tpo.usersmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpo.usersmodule.model.entity.Campaña;
import tpo.usersmodule.model.entity.Opcion;
import tpo.usersmodule.service.CampañaServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class CampañaController {
    @Autowired
    private CampañaServiceImpl campañaService;
    
    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")

    @CrossOrigin
    @GetMapping("/campañas/{id}")
    public ResponseEntity<?> getCampañaById(@PathVariable int id) {
        try {
            Campaña campaña = campañaService.findById(id);
            return new ResponseEntity<>(campaña, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }
    @CrossOrigin
    @GetMapping("/campañas")
    public ResponseEntity<?> getCampañas() {
        try {
            List<Campaña> campañas = campañaService.findAll();
            return new ResponseEntity<>(campañas, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin
    @GetMapping("/campañasAbiertas")
    public ResponseEntity<?> getCampañasAbiertas() {
        try {
            List<Campaña> campañas = campañaService.findAbiertas();
            return new ResponseEntity<>(campañas, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin
    @GetMapping("/campañasCerradas")
    public ResponseEntity<?> getCampañasCerradas() {
        try {
            List<Campaña> campañas = campañaService.findCerradas();
            return new ResponseEntity<>(campañas, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin
    @GetMapping("/cerrarCampaña/{id}")
    public ResponseEntity<?> getCerrarCampañaById(@PathVariable int id) {
        try {
            campañaService.cerrarCampaña(id);
            return new ResponseEntity<>(new Mensaje("Cerrada con exito"), HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin
    @GetMapping("/cerrarCampaña/{id}")
    public ResponseEntity<?> getGanadorById(@PathVariable int id) {
        try {
            List<Opcion> opciones = campañaService.findOpcionGanadoraById(id);
            if (opciones.size()==1){
                return new ResponseEntity<>(opciones.getFirst(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(opciones, HttpStatus.OK);
            }

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    
    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @CrossOrigin
    @PostMapping("/campañas")
    public ResponseEntity<?> addCampaña(@RequestBody Campaña campaña) {
        String msj = "";

        try {
            campañaService.save(campaña);
            msj = "Campaña guardada exitosamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }


    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @DeleteMapping("/campañas/{id}")
    public ResponseEntity<?> deleteCampaña(@PathVariable int id) {
        String msj;
        try {
            campañaService.deleteById(id);
            msj = "Campaña eliminada correctamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
}
