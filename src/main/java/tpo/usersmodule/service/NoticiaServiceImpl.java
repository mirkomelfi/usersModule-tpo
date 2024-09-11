package tpo.usersmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tpo.usersmodule.config.JwtAuthFilter;
import tpo.usersmodule.model.dao.INoticiaDAO;
import tpo.usersmodule.model.entity.Noticia;

import java.util.List;

@Service
public class NoticiaServiceImpl implements INoticiaService {
    @Autowired
    private INoticiaDAO noticiaDAO;

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
    public void save(Noticia noticia) {

        try {
            noticiaDAO.save(noticia);
            return;
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
                if (data.getFecha() != null)
                    noticia.setFecha(data.getFecha());
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
    
    
}
