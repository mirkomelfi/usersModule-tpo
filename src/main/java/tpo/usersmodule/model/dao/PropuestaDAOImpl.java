package tpo.usersmodule.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.model.entity.Propuesta;

import java.util.List;

@Repository
public class PropuestaDAOImpl implements IPropuestaDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Propuesta findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Propuesta res = currentSession.get(Propuesta.class, id);
        return res;
    }

    @Override
    @Transactional
    public int save(Propuesta propuesta) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(propuesta);
        return propuesta.getId();
    }


    @Override
    @Transactional
    public List<Propuesta> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        String query = "FROM Propuesta";
        List<Propuesta> list = currentSession.createQuery(query, Propuesta.class).getResultList();
        return list;
    }

    @Override
    @Transactional
    public List<Propuesta> findAllByDni(int dni) {

        List<Propuesta> result;
        Session currentSession = entityManager.unwrap(Session.class);
        result= currentSession.createQuery("FROM Propuesta WHERE usuario.dni = :dni").setParameter("dni",dni).getResultList();
        System.out.println(dni);
        System.out.println(result);
        return result;

    }
    

    @Override
    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Propuesta propuesta = currentSession.get(Propuesta.class, id);
        currentSession.remove(propuesta);
    }

}
