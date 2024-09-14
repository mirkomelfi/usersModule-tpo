package tpo.usersmodule.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tpo.usersmodule.model.entity.Turno;
import tpo.usersmodule.model.entity.Usuario;

import java.util.List;

@Repository
public class TurnoDAOImpl implements ITurnoDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Turno findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Turno res = currentSession.get(Turno.class, id);
        return res;
    }

    @Override
    @Transactional
    public void save(Turno turno) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(turno);
    }


    @Override
    @Transactional
    public List<Turno> findAll(int dni) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Turno> theQuery = currentSession.createQuery("FROM Turno WHERE usuarioSolicitante.dni=:dni OR usuarioReservado.dni=:dni", Turno.class);
        theQuery.setParameter("dni", dni);

        List<Turno> list = theQuery.getResultList();

        return list;
    }
    

    @Override
    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Turno turno = currentSession.get(Turno.class, id);
        currentSession.remove(turno);
    }

}
