package tpo.usersmodule.service;


import tpo.usersmodule.model.entity.Noticia;

import java.util.List;

public interface INoticiaService {

    public Noticia findById(int id);
    public List<Noticia> findAll();
    public void save(Noticia noticia);
    public void update(int id, Noticia noticia);
    public void deleteById(int id);




}
