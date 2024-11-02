package tpo.usersmodule.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tpo.usersmodule.model.entity.Producto;
import tpo.usersmodule.model.entity.RubroFeedback;

import java.util.List;

@Repository
public class ProductoDAOImpl{
    @Autowired
    IProductoDAOBase daoBase;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveNew(Producto p) {
        try {
            System.out.println(p);
            daoBase.save(p);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    @Transactional
    public Producto findById(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        String query = "FROM Producto where idProducto=:id";
        Producto producto = currentSession.createQuery(query, Producto.class).setParameter("id",id).getSingleResult();
        return producto;
    }

    @Transactional
    public Producto getById(long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Producto producto =currentSession.get(Producto.class, id);
        return producto;
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
