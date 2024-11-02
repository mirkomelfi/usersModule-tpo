package tpo.usersmodule.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tpo.usersmodule.model.entity.Carrito;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarritoDAOImpl{
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public Carrito findByUsername(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Carrito res = currentSession.get(Carrito.class, username);
        if (res!=null){
            return res;
        }else{
            Carrito newCart=new Carrito(username,new ArrayList<>());
            this.save(newCart);
            return newCart;
        }
    }
    
    @Transactional
    public void save(Carrito carrito) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(carrito);
    }

    
    @Transactional
    public void deleteById(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Carrito carrito = this.findByUsername(username);
        currentSession.remove(carrito);
    }

}
