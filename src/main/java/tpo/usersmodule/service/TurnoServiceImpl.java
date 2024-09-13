package tpo.usersmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tpo.usersmodule.model.dao.ITurnoDAO;
import tpo.usersmodule.model.dao.IImagenDAO;
import tpo.usersmodule.model.dao.IUsuarioDAO;
import tpo.usersmodule.model.entity.Turno;
import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.model.entity.Usuario;

import java.util.List;

@Service
public class TurnoServiceImpl {
    @Autowired
    private ITurnoDAO turnoDAO;
    @Autowired
    private IUsuarioDAO usuarioDAO;


    public Turno findById(int id) {
        Turno turno = turnoDAO.findById(id);
        if (turno != null) {
            return turno;
        }
        throw new Error ("La turno no existe");
    }



    public List<Turno> findAll(int dni) {
        List<Turno> turnos = turnoDAO.findAll(dni);
        if (turnos == null)
            throw new Error("Error al buscar los datos (null)");
        if (turnos.size() == 0)
            throw new Error("No se encontraron turnos");
        return turnos;
    }


    public void save(int dniSolicitante, int dniSolicitado, Turno turno) {

        try {
            Usuario solicitante= usuarioDAO.findByDni(dniSolicitante);
            Usuario solicitado= usuarioDAO.findByDni(dniSolicitado);
            turno.setUsuarioSolicitante(solicitante);
            turno.setUsuarioReservado(solicitado);
            turnoDAO.save(turno);
            return;
        } catch (Exception e) {
            throw new Error("Error interno en la BD");
        }

    }

    public void deleteById(int id) {
        Turno turno;
        try {
            turno = turnoDAO.findById(id);
        } catch (Throwable e) {
            throw new Error("Error interno en la BD: " + id);
        }

        if (turno == null) {
            throw new Error("La turno ingresada no existe");
        }

        turnoDAO.deleteById(id);

    }



}
