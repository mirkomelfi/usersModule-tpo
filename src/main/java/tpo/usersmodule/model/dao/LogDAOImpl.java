package tpo.usersmodule.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tpo.usersmodule.model.entity.Log;

import java.util.List;

@Repository
public class LogDAOImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Log findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Log res = currentSession.get(Log.class, id);
        return res;
    }

    @Transactional
    public void save(Log noticia) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(noticia);
    }

    @Transactional
    public List<Log> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        String query = "FROM Log";
        List<Log> list = currentSession.createQuery(query, Log.class).getResultList();
        return list;
    }
    

    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Log noticia = currentSession.get(Log.class, id);
        currentSession.remove(noticia);
    }

}
