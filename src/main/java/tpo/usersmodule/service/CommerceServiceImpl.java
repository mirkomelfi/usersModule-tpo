package tpo.usersmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tpo.usersmodule.model.dao.ProductoDAOImpl;

import tpo.usersmodule.model.dao.VentaDAOImpl;
import tpo.usersmodule.model.entity.Producto;
import tpo.usersmodule.model.entity.Imagen;
import tpo.usersmodule.model.entity.Usuario;
import tpo.usersmodule.model.entity.Venta;

import java.util.List;

@Service
public class CommerceServiceImpl {
    @Autowired
    private ProductoDAOImpl productoDAO;
    @Autowired
    private VentaDAOImpl ventaDAO;

    public Producto findProductoById(int id) {
        Producto producto = productoDAO.findById(id);
        if (producto != null) {
            return producto;
        }
        throw new Error ("La producto no existe");
    }

    public List<Producto> findAllProductos() {
        List<Producto> productos = productoDAO.findAll();
        if (productos == null)
            throw new Error("Error al buscar los datos (null)");
        if (productos.size() == 0)
            throw new Error("No se encontraron productos");
        return productos;
    }

    public Venta findVentaById(int id) {
        Venta venta = ventaDAO.findById(id);
        if (venta != null) {
            return venta;
        }
        throw new Error ("La venta no existe");
    }

    public List<Venta> findAllVentas() {
        List<Venta> ventas = ventaDAO.findAll();
        if (ventas == null)
            throw new Error("Error al buscar los datos (null)");
        if (ventas.size() == 0)
            throw new Error("No se encontraron ventas");
        return ventas;
    }


    
    
}
