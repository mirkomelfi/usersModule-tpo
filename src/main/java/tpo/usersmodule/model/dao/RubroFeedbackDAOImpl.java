package tpo.usersmodule.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tpo.usersmodule.model.entity.Feedback;
import tpo.usersmodule.model.entity.RubroFeedback;

import java.util.List;

@Repository
public class RubroFeedbackDAOImpl implements IRubroFeedbackDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public RubroFeedback findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        RubroFeedback res = currentSession.get(RubroFeedback.class, id);
        return res;
    }
    @Override
    @Transactional
    public void save(RubroFeedback rubro) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(rubro);
    }


    @Override
    @Transactional
    public List<RubroFeedback> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        String query = "FROM RubroFeedback";
        List<RubroFeedback> list = currentSession.createQuery(query, RubroFeedback.class).getResultList();
        return list;
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        RubroFeedback rubro = currentSession.get(RubroFeedback.class, id);
        currentSession.remove(rubro);
    }

}
