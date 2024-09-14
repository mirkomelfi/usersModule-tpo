package tpo.usersmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpo.usersmodule.model.dao.ICampañaDAO;
import tpo.usersmodule.model.dao.IUsuarioDAO;
import tpo.usersmodule.model.entity.Campaña;
import tpo.usersmodule.model.entity.Opcion;
import tpo.usersmodule.model.entity.Usuario;

import java.util.List;

@Service
public class CampañaServiceImpl {
    @Autowired
    private ICampañaDAO campañaDAO;

    public Campaña findById(int id) {
        Campaña campaña = campañaDAO.findById(id);
        if (campaña != null) {
            return campaña;
        }
        throw new Error ("La campaña no existe");
    }
    public List<Opcion> findOpcionGanadoraById(int id) {
        List<Opcion> opciones = campañaDAO.findOpcionGanadora(id);
        if (opciones.size()!=0) {
            return opciones;
        }
        throw new Error ("Las opciones no existen");
    }


    public List<Campaña> findAll() {
        List<Campaña> campañas = campañaDAO.findAll();
        if (campañas == null)
            throw new Error("Error al buscar los datos (null)");
        if (campañas.size() == 0)
            throw new Error("No se encontraron campañas");
        return campañas;
    }

    public List<Campaña> findAbiertas() {
        List<Campaña> campañas = campañaDAO.findAbiertas();
        if (campañas == null)
            throw new Error("Error al buscar los datos (null)");
        if (campañas.size() == 0)
            throw new Error("No se encontraron campañas");
        return campañas;
    }

    public List<Campaña> findCerradas() {
        List<Campaña> campañas = campañaDAO.findCerradas();
        if (campañas == null)
            throw new Error("Error al buscar los datos (null)");
        if (campañas.size() == 0)
            throw new Error("No se encontraron campañas");
        return campañas;
    }


    public void save(Campaña campaña) {

        try {
            campaña.getOpciones().forEach(opcion -> opcion.setCampaña(campaña));
            campañaDAO.save(campaña);
            return;
        } catch (Exception e) {
            throw new Error("Error interno en la BD");
        }

    }

    public void cerrarCampaña(int id) {

        try {
// faltael manejo de si hay empate
            Campaña c=campañaDAO.findById(id);
            //int cont=0;
            // busco cual es la opcion ganadora
            Opcion ganadora= c.getOpciones().getFirst();
            for (Opcion op:c.getOpciones()){
               // if (op.getVotos()== ganadora.getVotos()&&op.getId()!= ganadora.getId()){
                //    cont+=1;
               // }
                if (op.getVotos()> ganadora.getVotos()){
                    ganadora=op;
                }
            }
            // setteo la opcion ganadora
            for (Opcion op:c.getOpciones()){
                if (op.getVotos()== ganadora.getVotos()){
                    op.setOpcionGanadora(true);
                }
            }
            // cierro la campaña
            c.setEstado(false);
            campañaDAO.save(c);
            return;
        } catch (Exception e) {
            throw new Error("Error interno en la BD");
        }

    }


    public void deleteById(int id) {
        Campaña campaña;
        try {
            campaña = campañaDAO.findById(id);
        } catch (Throwable e) {
            throw new Error("Error interno en la BD: " + id);
        }

        if (campaña == null) {
            throw new Error("La campaña ingresada no existe");
        }

        campañaDAO.deleteById(id);

    }


}



