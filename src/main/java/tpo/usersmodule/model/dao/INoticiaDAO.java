package tpo.usersmodule.model.dao;

import tpo.usersmodule.model.entity.Noticia;

import java.util.List;

public interface INoticiaDAO {

    public Noticia findById(int id);
    public List<Noticia> findAll();
    public int save(Noticia noticia);
    public void deleteById(int id) ;


}
