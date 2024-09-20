package tpo.usersmodule.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tpo.usersmodule.model.entity.Actividad;

import java.util.List;

@Repository
public class ActividadDAOImpl implements IActividadDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Actividad findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Actividad res = currentSession.get(Actividad.class, id);
        return res;
    }

    @Override
    @Transactional
    public int save(Actividad actividad) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(actividad);
        return actividad.getId();
    }


    @Override
    @Transactional
    public List<Actividad> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        String query = "FROM Actividad";
        List<Actividad> list = currentSession.createQuery(query, Actividad.class).getResultList();
        return list;
    }
    

    @Override
    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Actividad actividad = currentSession.get(Actividad.class, id);
        currentSession.remove(actividad);
    }

}
