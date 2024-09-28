package tpo.usersmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpo.usersmodule.controller.dtos.CampañaDTO;
import tpo.usersmodule.controller.dtos.OpcionDTO;
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
    @GetMapping("/campanas/{id}")
    public ResponseEntity<?> getCampañaById(@PathVariable int id) {
        try {
            Campaña campaña = campañaService.findById(id);
            CampañaDTO dto= new CampañaDTO(campaña);
            return new ResponseEntity<>(dto, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }
@CrossOrigin
    @GetMapping("/campanas")
    public ResponseEntity<?> getCampañas() {
        try {
            List<Campaña> campañas = campañaService.findAll();
            List<CampañaDTO> dtos = convertirCampañasADTO(campañas);
            return new ResponseEntity<>(dtos, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin
    @GetMapping("/campanasAbiertas")
    public ResponseEntity<?> getCampañasAbiertas() {
        try {
            List<Campaña> campañas = campañaService.findAbiertas();
            List<CampañaDTO> dtos = convertirCampañasADTO(campañas);
            return new ResponseEntity<>(dtos, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin
    @GetMapping("/campanasCerradas")
    public ResponseEntity<?> getCampañasCerradas() {
        try {
            List<Campaña> campañas = campañaService.findCerradas();
            List<CampañaDTO> dtos = convertirCampañasADTO(campañas);
            return new ResponseEntity<>(dtos, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin
    @GetMapping("/campanas/{id}/cerrar")
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
    @GetMapping("/campanas/{id}/ganador")
    public ResponseEntity<?> getGanadorById(@PathVariable int id) {
        try {
            List<Opcion> opciones = campañaService.findOpcionGanadoraById(id);

            List<OpcionDTO> dtos =convertirOpcionesADTO(opciones);
            return new ResponseEntity<>(dtos, HttpStatus.OK);


        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    
    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @CrossOrigin
    @PostMapping("/admin/campanas")
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


    @CrossOrigin
    @PutMapping("/campanas/{idCampaña}/votacion/{idOpcion}/usuario/{dni}")
    public ResponseEntity<?> addVoto(@PathVariable int idCampaña,@PathVariable int idOpcion,@PathVariable int dni) {
        String msj = "";

        try {
            campañaService.saveVoto(idCampaña,idOpcion,dni);
            msj = "Voto registrado";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @CrossOrigin
    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @DeleteMapping("/campanas/{id}")
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

    private List<CampañaDTO> convertirCampañasADTO(List<Campaña> campañas) {
        List<CampañaDTO> dtos = new ArrayList<CampañaDTO>();
        if (campañas != null) {
            for (Campaña campaña: campañas) {
                dtos.add(new CampañaDTO(campaña));
            }
        }
        return dtos;
    }

    private List<OpcionDTO> convertirOpcionesADTO(List<Opcion> opciones) {
        List<OpcionDTO> dtos = new ArrayList<OpcionDTO>();
        if (opciones != null) {
            for (Opcion opcion: opciones) {
                dtos.add(new OpcionDTO(opcion));
            }
        }
        return dtos;
    }


}
