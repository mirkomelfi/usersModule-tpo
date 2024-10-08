package tpo.usersmodule.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tpo.usersmodule.model.entity.Feedback;

import java.util.List;

@Repository
public class FeedbackDAOImpl implements IFeedbackDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Feedback findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Feedback res = currentSession.get(Feedback.class, id);
        return res;
    }

    @Override
    @Transactional
    public int save(Feedback feedback) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(feedback);
        return feedback.getId();
    }


    @Override
    @Transactional
    public List<Feedback> findAll(int idRubro) {
        Session currentSession = entityManager.unwrap(Session.class);
        List<Feedback> result;
        if (idRubro==0){
            result= currentSession.createQuery("FROM Feedback").getResultList();
        }else{
            result= currentSession.createQuery("FROM Feedback WHERE rubro.id=:rubroId ").setParameter("rubroId",idRubro).getResultList();
        }
        return result;
    }

    @Override
    @Transactional
    public List<Feedback> findAllByDni(int dni, int idRubro) {

        List<Feedback> result;
        Session currentSession = entityManager.unwrap(Session.class);
        System.out.println(idRubro+" idrubro");
        if (idRubro==0){
            result= currentSession.createQuery("FROM Feedback WHERE usuario.dni = :dni").setParameter("dni",dni).getResultList();
        }else{
            result= currentSession.createQuery("FROM Feedback WHERE usuario.dni = :dni AND rubro.id=:rubroId ").setParameter("dni",dni).setParameter("rubroId",idRubro).getResultList();
        }
        return result;

    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Feedback feedback = currentSession.get(Feedback.class, id);
        currentSession.remove(feedback);
    }

}
