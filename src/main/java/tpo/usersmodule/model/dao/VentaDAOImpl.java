package tpo.usersmodule.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tpo.usersmodule.model.entity.Venta;

import java.util.List;

@Repository
public class VentaDAOImpl {
    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public Venta findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Venta res = currentSession.get(Venta.class, id);
        return res;
    }


    @Transactional
    public void save(Venta producto) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(producto);
    }


    @Transactional
    public List<Venta> findAll(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        String query = "FROM Venta where nombreUsuario=:username";
        List<Venta> list = currentSession.createQuery(query, Venta.class).setParameter("username",username).getResultList();
        return list;
    }


    @Transactional
    public void deleteAll(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.createQuery("DELETE FROM Venta where nombreUsuario=:username").setParameter("username",username).executeUpdate();
    }

}
