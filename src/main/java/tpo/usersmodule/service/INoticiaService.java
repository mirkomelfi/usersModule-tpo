package tpo.usersmodule.service;


import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.model.entity.Noticia;

import java.util.List;

public interface INoticiaService {

    public Noticia findById(int id);
    public List<Noticia> findAll();
    public void save(Noticia noticia);
    public void update(int id, Noticia noticia);
    public void deleteById(int id);

    //Manejo de imagenes
    void saveImagen(Imagen imagen, int reclamoId);
    Imagen findImagen(int noticiaID, int numeroImagen);
    public void deleteImagen(int noticiaID, int num);





}
