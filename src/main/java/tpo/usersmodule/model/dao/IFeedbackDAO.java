package tpo.usersmodule.model.dao;

import tpo.usersmodule.model.entity.Feedback;
import tpo.usersmodule.model.entity.RubroFeedback;

import java.util.List;

public interface IFeedbackDAO {

    public Feedback findById(int id);
    public List<Feedback> findAll();
    public List<Feedback> findAllByDni(int dni, int rubro);
    public int save(Feedback feedback);
    public void deleteById(int id) ;



}
