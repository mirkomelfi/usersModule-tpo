package tpo.usersmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tpo.usersmodule.controller.dtos.TurnoDTO;
import tpo.usersmodule.controller.dtos.TurnoDTO;
import tpo.usersmodule.model.entity.Turno;
import tpo.usersmodule.model.entity.Turno;
import tpo.usersmodule.service.TurnoServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class TurnoController {
    @Autowired
    private TurnoServiceImpl turnoService;
    
    //@PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_USER')")
    @CrossOrigin
    @GetMapping("/turnos/{dni}")
    public ResponseEntity<?> getTurnos(@PathVariable int dni) {
        try {
            List<Turno> turnos = turnoService.findAll(dni);
            List<TurnoDTO> dtos = convertirTurnosADTO(turnos);
            return new ResponseEntity<>(dtos, HttpStatus.OK);

        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    
    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @CrossOrigin
    @PostMapping("/turnos/{dniSolicitante}/{dniSolicitado}")
    public ResponseEntity<?> addTurno(@PathVariable int dniSolicitante, @PathVariable int dniSolicitado,@RequestBody Turno turno) {
        String msj = "";

        try {
            turnoService.save(dniSolicitante,dniSolicitado,turno);
            msj = "Turno guardado exitosamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }


    //@PreAuthorize("hasAuthority('ROL_ADMIN')")
    @DeleteMapping("/turnos/{id}")
    public ResponseEntity<?> deleteTurno(@PathVariable int id) {
        String msj;
        try {
            turnoService.deleteById(id);
            msj = "Turno eliminada correctamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }
    }
    private List<TurnoDTO> convertirTurnosADTO(List<Turno> turnos) {
        List<TurnoDTO> dtos = new ArrayList<TurnoDTO>();
        if (turnos != null) {
            for (Turno turno: turnos) {
                dtos.add(new TurnoDTO(turno));
            }
        }
        return dtos;
    }
    
}
