package tpo.usersmodule;

import ar.edu.uade.*;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import tpo.usersmodule.config.WebSocketHandler;
import tpo.usersmodule.controller.Mensaje;
import tpo.usersmodule.model.dao.LogDAOImpl;
import tpo.usersmodule.model.dao.ProductoDAOImpl;
import tpo.usersmodule.model.dao.VentaDAOImpl;
import tpo.usersmodule.model.entity.*;
import tpo.usersmodule.service.CommerceServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

@SpringBootApplication
public class UsersModuleApplication {

    public static void main(String[] args)  throws Exception{

        ApplicationContext a= SpringApplication.run(UsersModuleApplication.class, args);

        LogDAOImpl logDAO=a.getBean(LogDAOImpl.class);
        ProductoDAOImpl productoDAO=a.getBean(ProductoDAOImpl.class);
        VentaDAOImpl ventaDAO=a.getBean(VentaDAOImpl.class);
        CommerceServiceImpl commerceService=a.getBean(CommerceServiceImpl.class);

        WebSocketHandler webSocketHandler=a.getBean(WebSocketHandler.class);


        System.out.println("Pre broker");
        Broker broker = new Broker(
                "3.142.225.39",
                5672,
                "usuario",
                "7@3635@N%8%^%#7%f2!5"

        );

        System.out.println("post broker");
        Connection consumerConnection = broker.startConnection();
        Map<String, WebSocketSession> sessions = webSocketHandler.getSessions();
        //Redefino el callback para los mensajes recibidos.
        Consumer consumer = new Consumer(new CallbackInterface() {
            @Override
            public void callback(String consumerTag, Delivery delivery) {
                System.out.println("callback");

                //Body es la clase que encapsula a los datos enviados desde el módulo de origen.
                Body body = null;
                try {
                    body = Utilities.convertDelivery(delivery);
                    String datos = body.getPayload();

                    if (body.getUseCase().contentEquals("Productos")){
                        if (body.getTarget().contentEquals("Error")){
                            System.out.println("Error en CU: "+body.getUseCase());
                        }else{
                            List<Producto> arrayProductos=new ArrayList<>();
                            if (body.getType().contentEquals(Types.JSON.name())){
                                Producto p = Utilities.convertBody(body, Producto.class);
                                arrayProductos.add(p);
                            }
                            if (body.getType().toLowerCase().contentEquals(Types.ARRAY.name().toLowerCase())){
                                List<String> jsonArray= Utilities.convertString(datos);
                                for (String s : jsonArray) {
                                    Producto p = Utilities.convertElement(s, Producto.class);
                                    arrayProductos.add(p);
                                }
                            }
                            System.out.println(arrayProductos);
                            //commerceService.updateAll(arrayProductos);

                            //Map<String, WebSocketSession> sessions = webSocketHandler.getSessions();

                            // Iteramos sobre todas las sesiones conectadas y enviamos el mensaje
                            sessions.forEach((sessionId, session) -> {
                                try {
                                    // Enviar los productos a cada cliente conectado
                                    webSocketHandler.sendProducts(sessionId, arrayProductos);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });

                            logDAO.save(new Log("Update productos"));
                        }
                    }

                    if (body.getUseCase().contentEquals("Pedidos")){
                        if (body.getTarget().contentEquals("Error")){
                            System.out.println("Error en CU: "+body.getUseCase());
                        }else{
                            String username= body.getTarget();
                            List<Venta> ventasArray=new ArrayList<>();
                            //ventaDAO.deleteAll(body.getTarget());
                            if (body.getType().toLowerCase().contentEquals(Types.ARRAY.name().toLowerCase())){
                                List<String> jsonArray= Utilities.convertString(datos);
                                for (String s : jsonArray) {
                                    System.out.println(s);
                                    Venta v = Utilities.convertElement(s, Venta.class);
                                    ventasArray.add(v);
                                    //ventaDAO.save(v);
                                }
                            }
                            if (body.getType().contentEquals(Types.JSON.name())){
                                Venta v = Utilities.convertBody(body, Venta.class);
                                System.out.println(v);
                                ventasArray.add(v);
                                //ventaDAO.save(v);
                            }

                            webSocketHandler.sendSalesToUser(username, ventasArray);

                            logDAO.save(new Log("Update ventas"));
                        }
                    }



                    if (body.getUseCase().contentEquals("Reclamos")){
                        if (body.getTarget().contentEquals("Error")){
                            System.out.println("Error en CU: "+body.getUseCase());
                        }else{
                            String username= body.getTarget();
                            if (username==null){
                                List<String> tiposReclamoArray=new ArrayList<>();
                                if (body.getType().toLowerCase().contentEquals(Types.ARRAY.name().toLowerCase())){
                                    List<String> jsonArray= Utilities.convertString(datos);
                                    for (String s : jsonArray) {
                                        tiposReclamoArray.add(s);
                                    }
                                }
                                sessions.forEach((sessionId, session) -> {
                                    try {
                                        // Enviar los productos a cada cliente conectado
                                        webSocketHandler.sendTiposReclamo(sessionId, tiposReclamoArray);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }else{

                                List<Reclamo> reclamosArray=new ArrayList<>();
                               // ventaDAO.deleteAll(body.getTarget());
                                if (body.getType().toLowerCase().contentEquals(Types.ARRAY.name().toLowerCase())){
                                    List<String> jsonArray= Utilities.convertString(datos);
                                    for (String s : jsonArray) {
                                        System.out.println(s);
                                        Reclamo r = Utilities.convertElement(s, Reclamo.class);
                                        reclamosArray.add(r);

                                    }
                                }
                                if (body.getType().contentEquals(Types.JSON.name())){
                                    Reclamo r = Utilities.convertBody(body, Reclamo.class);
                                    System.out.println(r);
                                    reclamosArray.add(r);

                                }

                                webSocketHandler.sendReclamosToUser(username, reclamosArray);
                            }
                            logDAO.save(new Log("Update ventas"));
                        }
                    }


                    if (body.getUseCase().contentEquals("Inversiones")){
                        if (body.getTarget().contentEquals("Error")){
                            System.out.println("Error en CU: "+body.getUseCase());
                        }else{
                            String username= body.getTarget();
                            if (username==null){
                                List<Inversion> inversionesArray=new ArrayList<>();
                                if (body.getType().toLowerCase().contentEquals(Types.ARRAY.name().toLowerCase())){
                                    List<String> jsonArray= Utilities.convertString(datos);
                                    for (String s : jsonArray) {
                                        Inversion i = Utilities.convertElement(s, Inversion.class);
                                        inversionesArray.add(i);
                                    }
                                }
                                if (body.getType().contentEquals(Types.JSON.name())){
                                    Inversion i = Utilities.convertBody(body, Inversion.class);
                                    System.out.println(i);
                                    inversionesArray.add(i);
                                }

                                sessions.forEach((sessionId, session) -> {
                                    try {
                                        // Enviar los productos a cada cliente conectado
                                        webSocketHandler.sendInversiones(sessionId, inversionesArray);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }else{

                                List<Inversion> inversionesArray=new ArrayList<>();
                                // ventaDAO.deleteAll(body.getTarget());
                                if (body.getType().toLowerCase().contentEquals(Types.ARRAY.name().toLowerCase())){
                                    List<String> jsonArray= Utilities.convertString(datos);
                                    for (String s : jsonArray) {
                                        System.out.println(s);
                                        Inversion i = Utilities.convertElement(s, Inversion.class);
                                        inversionesArray.add(i);

                                    }
                                }
                                if (body.getType().contentEquals(Types.JSON.name())){
                                    Inversion i = Utilities.convertBody(body, Inversion.class);
                                    System.out.println(i);
                                    inversionesArray.add(i);

                                }

                                webSocketHandler.sendInversionesToUser(username, inversionesArray);
                            }
                            logDAO.save(new Log("Update ventas"));
                        }
                    }

                    if (body.getUseCase().contentEquals("Balance")){
                        if (body.getTarget().contentEquals("Error")){
                            System.out.println("Error en CU: "+body.getUseCase());
                        }else{
                            if (body.getType().contentEquals(Types.JSON.name())){
                                Balance b = Utilities.convertBody(body, Balance.class);
                                System.out.println(b);
                                sessions.forEach((sessionId, session) -> {
                                    try {
                                        // Enviar los productos a cada cliente conectado
                                        webSocketHandler.sendBalance(sessionId, b);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }

                        }
                    }

                } catch (ConverterException e) {
                    System.out.println("RuntimeException");
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Comienza a consumir utilizando un hilo secundario
        consumer.consume(consumerConnection, Modules.USUARIO);
    }




}
