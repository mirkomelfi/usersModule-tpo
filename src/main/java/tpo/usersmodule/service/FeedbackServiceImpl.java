package tpo.usersmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpo.usersmodule.model.dao.IImagenDAO;
import tpo.usersmodule.model.dao.IFeedbackDAO;
import tpo.usersmodule.model.dao.IUsuarioDAO;
import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.model.entity.Feedback;
import tpo.usersmodule.model.entity.Turno;
import tpo.usersmodule.model.entity.Usuario;

import java.util.List;

@Service
public class FeedbackServiceImpl implements IFeedbackService {
    @Autowired
    private IFeedbackDAO feedbackDAO;
    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    public Feedback findById(int id) {
        Feedback feedback = feedbackDAO.findById(id);
        if (feedback != null) {
            return feedback;
        }
        throw new Error ("La feedback no existe");
    }


    @Override
    public List<Feedback> findAll() {
        List<Feedback> feedbacks = feedbackDAO.findAll();
        if (feedbacks == null)
            throw new Error("Error al buscar los datos (null)");
        if (feedbacks.size() == 0)
            throw new Error("No se encontraron feedbacks");
        return feedbacks;
    }


    @Override
    public List<Feedback> findByDni(int dni) {
        List<Feedback> feedbacks = feedbackDAO.findAllByDni(dni);
        if (feedbacks == null)
            throw new Error("Error al buscar los datos (null)");
        if (feedbacks.size() == 0)
            throw new Error("No se encontraron feedbacks");
        return feedbacks;
    }

    @Override
    public void save(int dni, Feedback f) {

        try {

            Usuario u= usuarioDAO.findByDni(dni);
            f.setUsuario(u);
            feedbackDAO.save(f);

        } catch (Exception e) {
            throw new Error("Error interno en la BD");
        }
    }

    @Override
    public void deleteById(int id) {
        Feedback feedback;
        try {
            feedback = feedbackDAO.findById(id);
        } catch (Throwable e) {
            throw new Error("Error interno en la BD: " + id);
        }

        if (feedback == null) {
            throw new Error("La feedback ingresada no existe");
        }

        feedbackDAO.deleteById(id);

    }

    
}
