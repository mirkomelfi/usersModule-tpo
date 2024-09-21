package tpo.usersmodule.model.dao;

import tpo.usersmodule.model.entity.RubroFeedback;

import java.util.List;

public interface IRubroFeedbackDAO {
    public RubroFeedback findById(int id);
    public List<RubroFeedback> findAll();
    public void save(RubroFeedback rubro);
    public void deleteById(int id) ;


}
