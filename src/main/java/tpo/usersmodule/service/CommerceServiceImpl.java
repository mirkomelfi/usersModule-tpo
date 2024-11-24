package tpo.usersmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tpo.usersmodule.model.dao.CarritoDAOImpl;
import tpo.usersmodule.model.dao.ProductoDAOImpl;

import tpo.usersmodule.model.dao.UsuarioDAOImpl;
import tpo.usersmodule.model.dao.VentaDAOImpl;
import tpo.usersmodule.model.entity.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommerceServiceImpl {
    @Autowired
    private ProductoDAOImpl productoDAO;
    @Autowired
    private VentaDAOImpl ventaDAO;
    @Autowired
    private CarritoDAOImpl carritoDAO;
    @Autowired
    private UsuarioDAOImpl usuarioDAO;

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

    public void saveCart(String username,Carrito carrito) {

        try {
            //Carrito newCart= new Carrito();
            //newCart.setUsername(carrito.getUsername());
            //newCart.setProductos(carrito.getProductos());
            Usuario user=usuarioDAO.findByUsername(username);
            user.setCarrito(carrito);
            usuarioDAO.save(user);
            return;
        } catch (Exception e) {
            throw new Error("Error interno en la BD (service saveCart)",e);
        }

    }

    public Carrito getCart(String username) {

        try {
            return carritoDAO.findByUsername(username);
        } catch (Exception e) {
            throw new Error("Error interno en la BD");
        }

    }

    public void deleteCart(String username) {

        try {
            Usuario user=usuarioDAO.findByUsername(username);
            user.setCarrito(new Carrito());
            usuarioDAO.save(user);
            return;
        } catch (Exception e) {
            throw new Error("Error interno en la BD");
        }

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




