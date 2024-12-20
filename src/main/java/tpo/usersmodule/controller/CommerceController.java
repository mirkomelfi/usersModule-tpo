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
import tpo.usersmodule.config.ManejadorDeSesiones;
import tpo.usersmodule.config.WebSocketHandler;
import tpo.usersmodule.controller.dtos.ActividadDTO;
import tpo.usersmodule.controller.dtos.UsuarioDTO;
import tpo.usersmodule.model.entity.*;
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
   // private final WebSocketHandler webSocketHandler;

    /*@Autowired
    public CommerceController(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }*/

    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private CommerceServiceImpl commerceService;
    @Autowired
    private ManejadorDeSesiones sesionController;

    String userCore="{user:usuario, password:7@3635@N%8%^%#7%f2!5}";
    //ECOMMERCE
    @CrossOrigin
    @PostMapping("/finalizarCarrito")
    public ResponseEntity<?> crearVenta(@RequestBody Venta venta) {
        String msj = "";

        try {
            Broker broker = new Broker(
                    "3.141.117.124",
                    5672,
                    "usuario",
                    "7@3635@N%8%^%#7%f2!5"

            );
            Connection publisherConnection = broker.startConnection();

            Publisher publisher = new Publisher(Modules.USUARIO);

            venta.setFecha(new Date());
            venta.setCantidadDeProductos(venta.getProductos().size());

            publisher.publish(publisherConnection, Utilities.convertClass(venta), Modules.E_COMMERCE, "Venta", sesionController.getTokenJWTModulo(), Types.JSON,"Venta","600",userCore);

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
                    "3.141.117.124",
                    5672,
                    "usuario",
                    "7@3635@N%8%^%#7%f2!5"

            );
            Connection publisherConnection = broker.startConnection();

            Publisher publisher = new Publisher(Modules.USUARIO);

            publisher.publish(publisherConnection, null, Modules.E_COMMERCE, "Productos",  sesionController.getTokenJWTModulo(), Types.JSON,null,"600",userCore);

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
                    "3.141.117.124",
                    5672,
                    "usuario",
                    "7@3635@N%8%^%#7%f2!5"

            );
            Connection publisherConnection = broker.startConnection();

            Publisher publisher = new Publisher(Modules.USUARIO);

            publisher.publish(publisherConnection, username, Modules.E_COMMERCE, "Pedidos",  sesionController.getTokenJWTModulo(), Types.JSON,null,"600",userCore);

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


    @GetMapping("/carrito")
    public ResponseEntity<?> getCart(@RequestParam String username) {
        try {
            Carrito c = commerceService.getCart(username);

            return new ResponseEntity<>(c, HttpStatus.OK);
        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping("/carrito")
    public ResponseEntity<?> createCart(@RequestParam String username,@RequestBody Carrito carrito) {
        try {

            //System.out.println(carrito.getUsername());
            System.out.println(carrito.getProductos());
            commerceService.saveCart(username,carrito);

            return new ResponseEntity<>(new Mensaje("Creado con exito"), HttpStatus.OK);
        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }


    @DeleteMapping("/carrito")
    public ResponseEntity<?> deleteCart(@RequestParam String username) {
        try {
           commerceService.deleteCart(username);
            return new ResponseEntity<>(new Mensaje("Eliminado correctamente"), HttpStatus.OK);
        } catch (Throwable e) {
            String msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_FOUND);
        }

    }

    // GESTION FINANCIERA


    @GetMapping("/balance")
    public ResponseEntity<?> getBalanceCore() {
        String msj = "";

        try {
            Broker broker = new Broker(
                    "3.141.117.124",
                    5672,
                    "usuario",
                    "7@3635@N%8%^%#7%f2!5"

            );
            Connection publisherConnection = broker.startConnection();

            Publisher publisher = new Publisher(Modules.USUARIO);

            publisher.publish(publisherConnection, null, Modules.GESTION_FINANCIERA, "Balance",  sesionController.getTokenJWTModulo(), Types.JSON,null,"600",userCore);

            broker.endConnection(publisherConnection);

            msj = "Productos solicitados exitosamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @CrossOrigin
    @GetMapping("/inversiones")
    public ResponseEntity<?> getInversionesCore() {
        String msj = "";

        try {
            Broker broker = new Broker(
                    "3.141.117.124",
                    5672,
                    "usuario",
                    "7@3635@N%8%^%#7%f2!5"

            );
            Connection publisherConnection = broker.startConnection();

            Publisher publisher = new Publisher(Modules.USUARIO);

            publisher.publish(publisherConnection, null, Modules.GESTION_FINANCIERA, "Inversiones",  sesionController.getTokenJWTModulo(), Types.JSON,null,"600",userCore);

            broker.endConnection(publisherConnection);

            msj = "Inversiones solicitados exitosamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }


    @CrossOrigin
    @GetMapping("/misInversiones")
    public ResponseEntity<?> getMisInversionesCore(@RequestParam String username) {
        String msj = "";

        try {

            usuarioService.findByUsername(username);

            Broker broker = new Broker(
                    "3.141.117.124",
                    5672,
                    "usuario",
                    "7@3635@N%8%^%#7%f2!5"

            );
            Connection publisherConnection = broker.startConnection();

            Publisher publisher = new Publisher(Modules.USUARIO);

            publisher.publish(publisherConnection, username, Modules.GESTION_FINANCIERA, "Inversiones",  sesionController.getTokenJWTModulo(), Types.JSON,null,"600",userCore);

            broker.endConnection(publisherConnection);

            msj = "Inversiones solicitadas exitosamente para el user "+username;
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }


    @CrossOrigin
    @PostMapping("/finalizarInversion")
    public ResponseEntity<?> crearInversion(@RequestBody Inversion inversion) {
        String msj = "";

        try {
            Broker broker = new Broker(
                    "3.141.117.124",
                    5672,
                    "usuario",
                    "7@3635@N%8%^%#7%f2!5"

            );
            Connection publisherConnection = broker.startConnection();
            System.out.println(inversion.getAmount());
            System.out.println(inversion.getUsuario());
            Publisher publisher = new Publisher(Modules.USUARIO);

            publisher.publish(publisherConnection, Utilities.convertClass(inversion), Modules.GESTION_FINANCIERA, "Inversion",  sesionController.getTokenJWTModulo(), Types.JSON,inversion.getUsuario(),"600",userCore);

            broker.endConnection(publisherConnection);

            msj = "Inversion creada exitosamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    // GESTION INTERNA
    @GetMapping("/misReclamos")
    public ResponseEntity<?> getReclamosCore(@RequestParam String username) {
        String msj = "";

        try {

            usuarioService.findByUsername(username);

            Broker broker = new Broker(
                    "3.141.117.124",
                    5672,
                    "usuario",
                    "7@3635@N%8%^%#7%f2!5"

            );
            Connection publisherConnection = broker.startConnection();

            Publisher publisher = new Publisher(Modules.USUARIO);

            publisher.publish(publisherConnection, username, Modules.GESTION_INTERNA, "Reclamos",  sesionController.getTokenJWTModulo(), Types.JSON,null,"600",userCore);

            broker.endConnection(publisherConnection);

            msj = "Pedidos solicitados exitosamente para el user "+username;
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping("/tiposReclamo")
    public ResponseEntity<?> getTiposReclamoCore() {
        String msj = "";

        try {

            Broker broker = new Broker(
                    "3.141.117.124",
                    5672,
                    "usuario",
                    "7@3635@N%8%^%#7%f2!5"

            );
            Connection publisherConnection = broker.startConnection();

            Publisher publisher = new Publisher(Modules.USUARIO);

            publisher.publish(publisherConnection, null, Modules.GESTION_INTERNA, "Reclamos",  sesionController.getTokenJWTModulo(), Types.JSON,null,"600",userCore);

            broker.endConnection(publisherConnection);

            msj = "Tipos de reclamo solicitados exitosamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PostMapping("/finalizarReclamo")
    public ResponseEntity<?> crearReclamo(@RequestBody Reclamo reclamo) {
        String msj = "";

        try {
            Broker broker = new Broker(
                    "3.141.117.124",
                    5672,
                    "usuario",
                    "7@3635@N%8%^%#7%f2!5"

            );
            Connection publisherConnection = broker.startConnection();

            Publisher publisher = new Publisher(Modules.USUARIO);

            publisher.publish(publisherConnection, Utilities.convertClass(reclamo), Modules.GESTION_INTERNA, "Reclamo",  sesionController.getTokenJWTModulo(), Types.JSON,null,"600",userCore);

            broker.endConnection(publisherConnection);

            msj = "Reclamo creado exitosamente";
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.OK);
        } catch (Throwable e) {
            msj = e.getMessage();
            return new ResponseEntity<>(new Mensaje(msj), HttpStatus.NOT_ACCEPTABLE);
        }

    }


}
