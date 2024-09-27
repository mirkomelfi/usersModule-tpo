package tpo.usersmodule.service;


import tpo.usersmodule.model.entity.Feedback;
import tpo.usersmodule.model.entity.RubroFeedback;

import java.util.List;

public interface IFeedbackService {

    public Feedback findById(int id);
    public List<Feedback> findAll(int rubro);
    List<Feedback> findByDni(int dni, int rubro);
    public void save(int dni,Feedback feedback, int idRubro);
    public void deleteById(int id);


    public void saveRubro(RubroFeedback r) ;
    public List<RubroFeedback> findRubros() ;
    public void deleteRubroById(int id) ;

}
