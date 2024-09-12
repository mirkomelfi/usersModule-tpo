package tpo.usersmodule.model.dao;

import tpo.usersmodule.model.entity.Imagen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ImagenDAO implements IImagenDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void deleteById(long id) {

        Session currentSession = entityManager.unwrap(Session.class);
        Imagen i = currentSession.get(Imagen.class, id);

        currentSession.remove(i);
    }

    @Override
    @Transactional
    public Imagen findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Imagen res = currentSession.get(Imagen.class, id);
        return res;

    }

    @Override
    @Transactional
    public void save(Imagen imagen) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(imagen);

    }


    // x ahora no se usan
    @Override
    @Transactional
    public List<Imagen> getImagenesPorActividad(int idAct) {
        List<Imagen> result;
        Session currentSession = entityManager.unwrap(Session.class);
        result= currentSession.createQuery("FROM Imagen WHERE id = :idAct").setParameter("idAct",idAct).getResultList();

        return result;
    }

    @Override
    @Transactional
    public List<Imagen> getImagenesPorNoticia(int idNoticia) {
        List<Imagen> result;
        Session currentSession = entityManager.unwrap(Session.class);
        result= currentSession.createQuery("FROM Imagen WHERE id = :idNoticia").setParameter("idNoticia",idNoticia).getResultList();

        return result;
    }

}
