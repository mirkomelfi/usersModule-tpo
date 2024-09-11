package tpo.usersmodule.model.dao;

import tpo.usersmodule.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UsuarioDAOImpl implements IUsuarioDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Usuario findByDni(int dni) {
        Session currentSession = entityManager.unwrap(Session.class);
        Usuario res = currentSession.get(Usuario.class, dni);
        return res;
    }

    @Override
    @Transactional
    public List<Usuario> findByRol(String rol) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Usuario> theQuery = currentSession.createQuery("FROM Usuario WHERE rol=:rol", Usuario.class);
        theQuery.setParameter("rol", rol);

        List<Usuario> list = theQuery.getResultList();

        return list;

    }


    @Override
    @Transactional
    public Usuario findByUsername(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Usuario> theQuery = currentSession.createQuery("FROM Usuario WHERE username=:username", Usuario.class);
        theQuery.setParameter("username", username);

        Usuario user = theQuery.uniqueResult();

        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    private boolean checkPassword(String password, String passwordDB) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Password: " + password);
        System.out.println("passwordDB: " + passwordDB);

        boolean isPasswordMatch = passwordEncoder.matches(password, passwordDB);

        return isPasswordMatch;
    }

    @Override
    @Transactional
    public void save(Usuario user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(user);
    }


    @Override
    @Transactional
    public List<Usuario> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        String query = "FROM Usuario";
        List<Usuario> list = currentSession.createQuery(query, Usuario.class).getResultList();
        return list;
    }


    @Override
    @Transactional
    public void deleteByDni(int dni) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query updateInqulinos = currentSession.createQuery("update Unidad set inquilino = null where inquilino.dni=:dniUser");
        updateInqulinos.setParameter("dniUser", dni);
        updateInqulinos.executeUpdate();
        Query updatePropietarios = currentSession.createQuery("update Unidad set propietario = null where propietario.dni=:dniUser");
        updatePropietarios.setParameter("dniUser", dni);
        updatePropietarios.executeUpdate();
        Usuario usr = this.findByDni(dni);
        currentSession.remove(usr);
    }

}
