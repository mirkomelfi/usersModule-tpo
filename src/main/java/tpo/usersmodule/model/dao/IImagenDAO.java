package tpo.usersmodule.model.dao;

import tpo.usersmodule.model.entity.Imagen;

import java.util.List;

public interface IImagenDAO {
	public void deleteById(long id);
	public Imagen findById(int id);
	public void save(Imagen imagen);
	public List<Imagen> getImagenesPorActividad(int idAct);
}
