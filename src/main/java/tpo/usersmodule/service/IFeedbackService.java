package tpo.usersmodule.service;


import tpo.usersmodule.model.entity.Feedback;
import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.model.entity.Propuesta;

import java.util.List;

public interface IFeedbackService {

    public Feedback findById(int id);
    public List<Feedback> findAll();
    List<Feedback> findByDni(int dni);
    public void save(int dni,Feedback feedback);
    public void deleteById(int id);

}
