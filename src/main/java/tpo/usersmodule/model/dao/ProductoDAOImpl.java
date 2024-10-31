package tpo.usersmodule.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tpo.usersmodule.model.entity.Producto;

import java.util.List;

@Repository
public class ProductoDAOImpl{
    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public Producto findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Producto res = currentSession.get(Producto.class, id);
        return res;
    }


    @Transactional
    public void save(Producto producto) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(producto);
    }


    @Transactional
    public List<Producto> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        String query = "FROM Producto";
        List<Producto> list = currentSession.createQuery(query, Producto.class).getResultList();
        return list;
    }


    @Transactional
    public void deleteAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.createQuery("DELETE FROM Producto where idProducto>0").executeUpdate();
    }

}
