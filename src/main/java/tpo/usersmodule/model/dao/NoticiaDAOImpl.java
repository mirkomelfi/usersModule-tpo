package tpo.usersmodule.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tpo.usersmodule.model.entity.Noticia;

import java.util.List;

@Repository
public class NoticiaDAOImpl implements INoticiaDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Noticia findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Noticia res = currentSession.get(Noticia.class, id);
        return res;
    }

    @Override
    @Transactional
    public int save(Noticia noticia) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(noticia);
        return noticia.getId();
    }


    @Override
    @Transactional
    public List<Noticia> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        String query = "FROM Noticia";
        List<Noticia> list = currentSession.createQuery(query, Noticia.class).getResultList();
        return list;
    }
    

    @Override
    @Transactional
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Noticia noticia = currentSession.get(Noticia.class, id);
        currentSession.remove(noticia);
    }

}
