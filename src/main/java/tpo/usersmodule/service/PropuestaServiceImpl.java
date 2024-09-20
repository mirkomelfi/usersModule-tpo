package tpo.usersmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpo.usersmodule.model.dao.IImagenDAO;
import tpo.usersmodule.model.dao.IPropuestaDAO;
import tpo.usersmodule.model.dao.IUsuarioDAO;
import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.model.entity.Propuesta;
import tpo.usersmodule.model.entity.Usuario;

import java.util.List;

@Service
public class PropuestaServiceImpl implements IPropuestaService {
    @Autowired
    private IPropuestaDAO propuestaDAO;
    @Autowired
    private IImagenDAO imgDAO;
    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    public Propuesta findById(int id) {
        Propuesta propuesta = propuestaDAO.findById(id);
        if (propuesta != null) {
            return propuesta;
        }
        throw new Error ("La propuesta no existe");
    }


    @Override
    public List<Propuesta> findAll() {
        List<Propuesta> propuestas = propuestaDAO.findAll();
        if (propuestas == null)
            throw new Error("Error al buscar los datos (null)");
        if (propuestas.size() == 0)
            throw new Error("No se encontraron propuestas");
        return propuestas;
    }


    @Override
    public List<Propuesta> findByDni(int dni) {
        List<Propuesta> propuestas = propuestaDAO.findAllByDni(dni);
        if (propuestas == null)
            throw new Error("Error al buscar los datos (null)");
        if (propuestas.size() == 0)
            throw new Error("No se encontraron propuestas");
        return propuestas;
    }

    @Override
    public int save(int dni,Propuesta p) {

        try {
            Usuario u= usuarioDAO.findByDni(dni);
            p.setUsuario(u);
            int id=propuestaDAO.save(p);
            return id;
        } catch (Exception e) {
            throw new Error("Error interno en la BD");
        }

    }

    @Override
    public void deleteById(int id) {
        Propuesta propuesta;
        try {
            propuesta = propuestaDAO.findById(id);
        } catch (Throwable e) {
            throw new Error("Error interno en la BD: " + id);
        }

        if (propuesta == null) {
            throw new Error("La propuesta ingresada no existe");
        }

        propuestaDAO.deleteById(id);

    }


    @Override
    public void saveImagen(Imagen imagen, int propuestaId) {
        Propuesta propuesta = propuestaDAO.findById(propuestaId);
        if (propuesta != null) {
            imagen.setPropuesta(propuesta);
            imgDAO.save(imagen);
            return;
        }
        throw new Error("No se econtro el propuesta");

    }

    @Override
    public Imagen findImagen(int idPropuesta, int numeroImagen) {
        Propuesta not = this.propuestaDAO.findById(idPropuesta);
        if (not == null) throw new Error("No se encotnro el propuesta");
        if (numeroImagen <= 0 || numeroImagen > not.getImagenes().size() || not.getImagenes().isEmpty())
            throw new Error("No se encontró la imagen");
        Imagen img = not.getImagenes().get(numeroImagen - 1);
        return img;

    }

    @Override
    public void deleteImagen(int idPropuesta, int num) {
        Propuesta r = this.propuestaDAO.findById(idPropuesta);
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
    
}
