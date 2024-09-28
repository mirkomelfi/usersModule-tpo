package tpo.usersmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tpo.usersmodule.model.dao.IActividadDAO;
import tpo.usersmodule.model.dao.IImagenDAO;
import tpo.usersmodule.model.dao.IUsuarioDAO;
import tpo.usersmodule.model.entity.Actividad;
import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.model.entity.Usuario;

import java.util.List;

@Service
public class ActividadServiceImpl implements IActividadService {
    @Autowired
    private IActividadDAO actividadDAO;
    @Autowired
    private IImagenDAO imgDAO;
    @Autowired
    private IUsuarioDAO usuarioDAO;

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
    public int save(Actividad actividad) {

        try {
            int id=actividadDAO.save(actividad);

            return id;
        } catch (Exception e) {
            throw new Error("Error interno en la BD");
        }

    }
    @Override
    public void inscribirByDni(int id, int dni) {

        try {
            Actividad act=this.findById(id);
            Usuario u=usuarioDAO.findByDni(dni);
            if (!u.getActividades().contains(act)){
                u.getActividades().add(act);
                usuarioDAO.save(u);
            }
            else{
                throw new Error("Ya se encuentra inscripto");
            }

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
                if (data.getDias().size()!=0)
                    actividad.setDias(data.getDias());
                if (data.getProfesor() != null)
                    actividad.setProfesor(data.getProfesor());
                if (data.getValor()!=0)
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

    @Override
    public void saveImagen(Imagen imagen, int actividadId) {
        Actividad actividad = actividadDAO.findById(actividadId);
        if (actividad != null) {
            imagen.setActividad(actividad);
            imgDAO.save(imagen);
            return;
        }
        throw new Error("No se econtro el actividad");

    }

    @Override
    public Imagen findImagen(int idActividad, int numeroImagen) {
        Actividad act = this.actividadDAO.findById(idActividad);
        if (act == null) throw new Error("No se encotnro el actividad");
        if (numeroImagen <= 0 || numeroImagen > act.getImagenes().size() || act.getImagenes().isEmpty())
            throw new Error("No se encontró la imagen");
        Imagen img = act.getImagenes().get(numeroImagen - 1);
        return img;

    }

    @Override
    public void deleteImagen(int idActividad, int num) {
        Actividad r = this.actividadDAO.findById(idActividad);
        Imagen i;
        Long id;
        if (num <= 0 || num > r.getImagenes().size() || r.getImagenes().isEmpty())
            throw new Error("numero de imagen invalido");
        i = r.getImagenes().get(num - 1);
        id = i.getId();
        r.getImagenes().remove(i);
        imgDAO.deleteById(id);
        return;

    }


    // x ahora no se usa
    @Override
    public List<Imagen> findImagenes(int idAct) {
        List<Imagen> result = null;
        try{
            result = imgDAO.getImagenesPorActividad(idAct);
        }catch (EmptyResultDataAccessException e){
            throw e;
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            throw new Error("Error interno.");
        }
        return result;
    }
    
    
}
