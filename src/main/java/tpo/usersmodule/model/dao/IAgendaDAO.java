package tpo.usersmodule.model.dao;

import tpo.usersmodule.model.entity.Agenda;

import java.util.List;

public interface IAgendaDAO {

    public Agenda findById(int id);
    public List<Agenda> findAll();
    public int save(Agenda agenda);
    public void deleteById(int id) ;


}
