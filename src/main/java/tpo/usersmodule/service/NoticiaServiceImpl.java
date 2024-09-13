package tpo.usersmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tpo.usersmodule.config.JwtAuthFilter;
import tpo.usersmodule.model.dao.IImagenDAO;
import tpo.usersmodule.model.dao.INoticiaDAO;
import tpo.usersmodule.model.entity.Noticia;
import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.model.entity.Noticia;

import java.util.List;

@Service
public class NoticiaServiceImpl implements INoticiaService {
    @Autowired
    private INoticiaDAO noticiaDAO;
    @Autowired
    private IImagenDAO imgDAO;

    @Override
    public Noticia findById(int id) {
        Noticia noticia = noticiaDAO.findById(id);
        if (noticia != null) {
            return noticia;
        }
        throw new Error ("La noticia no existe");
    }


    @Override
    public List<Noticia> findAll() {
        List<Noticia> noticias = noticiaDAO.findAll();
        if (noticias == null)
            throw new Error("Error al buscar los datos (null)");
        if (noticias.size() == 0)
            throw new Error("No se encontraron noticias");
        return noticias;
    }

    @Override
    public int save(Noticia noticia) {

        try {
            int id=noticiaDAO.save(noticia);
            System.out.print(id+" : id noticia");
            return id;
        } catch (Exception e) {
            throw new Error("Error interno en la BD");
        }

    }

    @Override
    public void deleteById(int id) {
        Noticia noticia;
        try {
            noticia = noticiaDAO.findById(id);
        } catch (Throwable e) {
            throw new Error("Error interno en la BD: " + id);
        }

        if (noticia == null) {
            throw new Error("La noticia ingresada no existe");
        }

        noticiaDAO.deleteById(id);

    }

    @Override
    public void update(int id, Noticia data) {

        try {
            Noticia noticia = noticiaDAO.findById(id);
            if (noticia != null) {
                if (data.getDescripcion() != null)
                    noticia.setDescripcion(data.getDescripcion());
                if (data.getFechaPublicacion() != null)
                    noticia.setFechaPublicacion(data.getFechaPublicacion());
                if (data.getTitulo() != null)
                    noticia.setTitulo(data.getTitulo());
                noticiaDAO.save(noticia);
            } else {
                throw new Error("La noticia ingresada no existe");
            }

        } catch (Throwable e) {
            e.printStackTrace();
            throw new Error("Error al guardar el noticia." + e.getMessage());
        }

    }


    @Override
    public void saveImagen(Imagen imagen, int noticiaId) {
        Noticia noticia = noticiaDAO.findById(noticiaId);
        if (noticia != null) {
            imagen.setNoticia(noticia);
            imgDAO.save(imagen);
            return;
        }
        throw new Error("No se econtro el noticia");

    }

    @Override
    public Imagen findImagen(int idNoticia, int numeroImagen) {
        Noticia not = this.noticiaDAO.findById(idNoticia);
        if (not == null) throw new Error("No se encotnro el noticia");
        if (numeroImagen <= 0 || numeroImagen > not.getImagenes().size() || not.getImagenes().isEmpty())
            throw new Error("No se encontró la imagen");
        Imagen img = not.getImagenes().get(numeroImagen - 1);
        return img;

    }

    @Override
    public void deleteImagen(int idNoticia, int num) {
        Noticia r = this.noticiaDAO.findById(idNoticia);
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
