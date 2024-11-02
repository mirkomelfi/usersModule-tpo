package tpo.usersmodule.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpo.usersmodule.model.entity.Producto;

@Repository
public interface IProductoDAOBase extends JpaRepository<Producto,Integer>{


}