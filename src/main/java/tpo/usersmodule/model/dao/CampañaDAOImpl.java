package tpo.usersmodule.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tpo.usersmodule.model.entity.Noticia;
import tpo.usersmodule.model.entity.Campaña;
import tpo.usersmodule.model.entity.Opcion;
import tpo.usersmodule.model.entity.Turno;

import java.util.List;

@Repository
public class CampañaDAOImpl implements ICampañaDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Campaña findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Campaña res = currentSession.get(Campaña.class, id);
        return res;
    }

    @Override
    @Transactional
    public void save(Campaña campaña) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(campaña);
    }


    @Override
    @Transactional
    public List<Campaña> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        String query = "FROM Campaña";
        List<Campaña> list = currentSession.createQuery(query, Campaña.class).getResultList();
        return list;

    }
    @Override
    @Transactional
    public List<Campaña> findAbiertas() {
        Session currentSession = entityManager.unwrap(Session.class);
        boolean estado=true;

        Query<Campaña> theQuery = currentSession.createQuery("FROM Campaña WHERE estado=:estado", Campaña.class);
        theQuery.setParameter("estado", estado);

        List<Campaña> list = theQuery.getResultList();
        return list;

    }

    @Override
    @Transactional
    public List<Campaña> findCerradas() {
        Session currentSession = entityManager.unwrap(Session.class);
        boolean estado=false;

        Query<Campaña> theQuery = currentSession.createQuery("FROM Campaña WHERE estado=:estado", Campaña.class);
        theQuery.setParameter("estado", estado);

        List<Campaña> list = theQuery.getResultList();
        return list;

    }

    @Override
    @Transactional
    public List<Opcion> findOpcionGanadora(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        boolean estado=true;

        Query<Opcion> theQuery = currentSession.createQuery("FROM Opcion WHERE campaña=:id AND opcionGanadora=:estado", Opcion.class);
        theQuery.setParameter("estado", estado);
        theQuery.setParameter("id", id);

        List<Opcion> ops = theQuery.getResultList();
        return ops ;

    }
    

    @Override
    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Campaña campaña = currentSession.get(Campaña.class, id);
        currentSession.remove(campaña);
    }

}
