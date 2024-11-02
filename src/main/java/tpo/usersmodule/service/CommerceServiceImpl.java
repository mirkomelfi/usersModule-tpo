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

    public Producto findProductoById(long id) {
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

    public List<Venta> findAllVentas(String username) {
        List<Venta> ventas = ventaDAO.findAll(username);
        if (ventas == null)
            throw new Error("Error al buscar los datos (null)");
        if (ventas.size() == 0)
            throw new Error("No se encontraron ventas");
        return ventas;
    }


    public void updateAll(List<Producto> productos) {
        for (Producto producto : productos){
            System.out.println(producto.toString());
                Producto p=productoDAO.getById(producto.getIdProducto());
                if (p!=null){
                System.out.print(producto.getCaracteristicas());
                if (!producto.getCaracteristicas().isEmpty())p.setCaracteristicas(producto.getCaracteristicas());
                p.setCategoria(producto.getCategoria());
                p.setDescripcion(producto.getDescripcion());
                p.setNombre(producto.getNombre());
                if (!producto.getTalles().isEmpty())p.setTalles(producto.getTalles());
                p.setDescuentoEfectivo(producto.getDescuentoEfectivo());
                p.setDescuentoSocios(producto.getDescuentoSocios());
                p.setDescuentoNoSocios(producto.getDescuentoNoSocios());
                p.setPrecioVenta(producto.getPrecioVenta());
                p.setStockActual(producto.getStockActual());
                productoDAO.saveNew(p);
                }else{
                    productoDAO.saveNew(producto);
                }
            }
        }
    }




