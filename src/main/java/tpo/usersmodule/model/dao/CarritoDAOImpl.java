package tpo.usersmodule.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tpo.usersmodule.model.entity.Campaña;
import tpo.usersmodule.model.entity.Carrito;
import tpo.usersmodule.model.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarritoDAOImpl{
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public Carrito findByUsername(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Usuario> theQuery = currentSession.createQuery("FROM Usuario WHERE username=:username", Usuario.class);
        theQuery.setParameter("username", username);

        Carrito res= theQuery.getSingleResult().getCarrito();
       // Carrito res = currentSession.get(Carrito.class, username);
        if (res!=null){
            return res;
        }else{
            //Carrito newCart=new Carrito(username,new ArrayList<>());
            Carrito newCart=new Carrito();
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
