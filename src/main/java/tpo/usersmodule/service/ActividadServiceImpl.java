package tpo.usersmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpo.usersmodule.model.dao.IActividadDAO;
import tpo.usersmodule.model.entity.Actividad;

import java.util.List;

@Service
public class ActividadServiceImpl implements IActividadService {
    @Autowired
    private IActividadDAO actividadDAO;

    @Override
    public Actividad findById(int id) {
        Actividad actividad = actividadDAO.findById(id);
        if (actividad != null) {
            return actividad;
        }
        throw new Error ("La actividad no existe");
    }


    @Override
    public List<Actividad> findAll() {
        List<Actividad> actividads = actividadDAO.findAll();
        if (actividads == null)
            throw new Error("Error al buscar los datos (null)");
        if (actividads.size() == 0)
            throw new Error("No se encontraron actividads");
        return actividads;
    }

    @Override
    public void save(Actividad actividad) {

        try {
            actividadDAO.save(actividad);
            return;
        } catch (Exception e) {
            throw new Error("Error interno en la BD");
        }

    }

    @Override
    public void deleteById(int id) {
        Actividad actividad;
        try {
            actividad = actividadDAO.findById(id);
        } catch (Throwable e) {
            throw new Error("Error interno en la BD: " + id);
        }

        if (actividad == null) {
            throw new Error("La actividad ingresada no existe");
        }

        actividadDAO.deleteById(id);

    }

    @Override
    public void update(int id, Actividad data) {

        try {
            Actividad actividad = actividadDAO.findById(id);
            if (actividad != null) {
                if (data.getDescripcion() != null)
                    actividad.setDescripcion(data.getDescripcion());
                if (data.getNombre() != null)
                    actividad.setNombre(data.getNombre());
                if (data.getDias() != null)
                    actividad.setDias(data.getDias());
                if (data.getProfesor() != null)
                    actividad.setProfesor(data.getProfesor());
                if (!Float.isNaN(data.getValor()))
                    actividad.setValor(data.getValor());
                actividadDAO.save(actividad);
            } else {
                throw new Error("La actividad ingresada no existe");
            }
           
        } catch (Throwable e) {
            e.printStackTrace();
            throw new Error("Error al guardar el actividad." + e.getMessage());
        }

    }
    
    
}
