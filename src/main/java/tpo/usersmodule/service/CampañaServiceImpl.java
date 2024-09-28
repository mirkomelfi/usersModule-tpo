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
    @Autowired
    private IUsuarioDAO usuarioDAO;

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
        throw new Error ("Aun no finalizo la campaña");
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

    // hacerme un metodo para traerme las campañas que ya voto y no voto

    public void saveVoto(int idCampaña, int idOpcion, int dni) {

        try {
            Usuario usuario = usuarioDAO.findByDni(dni);
            Campaña campaña = campañaDAO.findById(idCampaña);
            System.out.println(usuario.getCampañasVotadas());
            System.out.println(usuario.getCampañasVotadas().contains(campaña));
            if (usuario.getCampañasVotadas().contains(campaña)) {
                throw new Error("El usuario ya ha votado en esta campaña");
            }

            List<Opcion> opciones=campaña.getOpciones();
            boolean opcionNotCorrect=true;
            for (Opcion op : opciones){
                if (op.getId()==idOpcion){
                    op.setVotos(op.getVotos()+1);
                    opcionNotCorrect=false;
                }
            }

            if (opcionNotCorrect) {
                throw new Error("Error interno: La opcion votada no pertenece a campaña seleccionada");
            }else{

                if (campaña.getEstado()){
                    usuario.getCampañasVotadas().add(campaña);
                    usuarioDAO.save(usuario);
                }else{
                    throw new Error("La campaña ya cerro. No puede votar.");

                }
            }


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
            Opcion ganadora= c.getOpciones().get(0);
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


    public void update(int idCampaña, Campaña campaña) {

        try {
            Campaña newCampaña = campañaDAO.findById(idCampaña);

            List<Opcion> opciones=campaña.getOpciones();
            if (campaña.getDescripcion()!=null){
                newCampaña.setDescripcion(campaña.getDescripcion());
            }
            if (campaña.getTitulo()!=null){
                newCampaña.setTitulo(campaña.getTitulo());
            }
            if (opciones.size()!=0){
                newCampaña.setOpciones(opciones);
            }

            campañaDAO.save(newCampaña);

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



