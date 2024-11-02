package tpo.usersmodule.controller;

import ar.edu.uade.Modules;
import ar.edu.uade.Publisher;
import ar.edu.uade.Types;
import ar.edu.uade.Utilities;
import com.rabbitmq.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpo.usersmodule.controller.dtos.ActividadDTO;
import tpo.usersmodule.controller.dtos.UsuarioDTO;
import tpo.usersmodule.model.entity.Actividad;
import tpo.usersmodule.model.entity.Producto;
import tpo.usersmodule.model.entity.Usuario;
import tpo.usersmodule.model.entity.Venta;
import ar.edu.uade.*;
import tpo.usersmodule.service.CommerceServiceImpl;
import tpo.usersmodule.service.IActividadService;
import tpo.usersmodule.service.IUsuarioService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("")
public class CommerceController {
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private CommerceServiceImpl commerceService;
    @CrossOrigin
    @PostMapping("/finalizarCarrito")
    public ResponseEntity<?> crearVenta(@RequestBody Venta venta) {
        String msj = "";

        try {
            Broker broker = new Broker(
                    "3.142.225.39",
                    5672,
                    "usuario",
                    "7@3635@N%8%^%#7%f2!5"

            );
            Connection publisherConnection = broker.startConnection();

            Publisher publisher = new Publisher(Modules.USUARIO);

            venta.setFecha(new Date());
            venta.setCantidadDeProductos(venta.getProductos().size());

            publisher.publish(publisherConnection, Utilities.convertClass(venta), Modules.E_COMMERCE, "Venta", "token", Types.JSON,"Venta","600");

            broker.endConnection(publisherConnection);

            msj = "Carrito comprado exitosamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @CrossOrigin
    @GetMapping("/productosUpdate")
    public ResponseEntity<?> getProductosCore() {
        String msj = "";

        try {
            Broker broker = new Broker(
                    "3.142.225.39",
                    5672,
                    "usuario",
                    "7@3635@N%8%^%#7%f2!5"

            );
            Connection publisherConnection = broker.startConnection();

            Publisher publisher = new Publisher(Modules.USUARIO);

            publisher.publish(publisherConnection, null, Modules.E_COMMERCE, "Productos", "token", Types.JSON,null,"600");

            broker.endConnection(publisherConnection);

            msj = "Productos solicitados exitosamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @CrossOrigin
    @GetMapping("/productos/{id}")
    public ResponseEntity<?> getProducto(@PathVariable int id) {
        try {
            Producto producto = commerceService.findProductoById(id);
            System.out.print(producto);
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }
    @CrossOrigin
    @GetMapping("/misPedidos")
    public ResponseEntity<?> getPedidosCore(@RequestParam String username) {
        String msj = "";

        try {

            usuarioService.findByUsername(username);

            Broker broker = new Broker(
                    "3.142.225.39",
                    5672,
                    "usuario",
                    "7@3635@N%8%^%#7%f2!5"

            );
            Connection publisherConnection = broker.startConnection();

            Publisher publisher = new Publisher(Modules.USUARIO);

            publisher.publish(publisherConnection, username, Modules.E_COMMERCE, "Pedidos", "token", Types.JSON,null,"600");

            broker.endConnection(publisherConnection);

            msj = "Pedidos solicitados exitosamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }


    @CrossOrigin
    @GetMapping("/productos")
    public ResponseEntity<?> getProductos() {
        String msj = "";

        try {
            List<Producto> productos = commerceService.findAllProductos();
            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @CrossOrigin
    @GetMapping("/ventas")
    public ResponseEntity<?> getVentas(@RequestParam String username) {
        String msj = "";

        try {
            List<Venta> ventas = commerceService.findAllVentas(username);
            return new ResponseEntity<>(ventas, HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }


}
