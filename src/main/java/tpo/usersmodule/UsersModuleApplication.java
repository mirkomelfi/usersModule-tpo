package tpo.usersmodule;

import ar.edu.uade.*;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import tpo.usersmodule.controller.Mensaje;
import tpo.usersmodule.model.dao.LogDAOImpl;
import tpo.usersmodule.model.dao.ProductoDAOImpl;
import tpo.usersmodule.model.dao.VentaDAOImpl;
import tpo.usersmodule.model.entity.*;

import java.util.Date;
import java.util.List;


@SpringBootApplication
public class UsersModuleApplication {

    public static void main(String[] args)  throws Exception{

        ApplicationContext a= SpringApplication.run(UsersModuleApplication.class, args);

        LogDAOImpl logDAO=a.getBean(LogDAOImpl.class);
        ProductoDAOImpl productoDAO=a.getBean(ProductoDAOImpl.class);
        VentaDAOImpl ventaDAO=a.getBean(VentaDAOImpl.class);

        System.out.println("Pre broker");
        Broker broker = new Broker(
                "3.142.225.39",
                5672,
                "usuario",
                "7@3635@N%8%^%#7%f2!5"

                /*System.getenv("HOST"),
                Integer.parseInt(
                        System.getenv("PORT")
                ),
                System.getenv("USER"),
                System.getenv("PASSWORD")
                 */
        );

        System.out.println("post broker");
        Connection consumerConnection = broker.startConnection();

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
                        if (body.getUseCase().contentEquals("Prueba")){
                            System.out.println(datos);
                        }
                        if (body.getUseCase().contentEquals("Productos")){

                        if (datos!=null) {
                            List<String> jsonArray= Utilities.convertString(datos);

                            productoDAO.deleteAll();


                            for (String s : jsonArray) {
                                System.out.println(s);
                                Producto p = Utilities.convertElement(s, Producto.class);
                                productoDAO.save(p);
                            }

                            logDAO.save(new Log("Update productos"));
                        }

                        }

                    if (body.getUseCase().contentEquals("Pedidos")){

                        if (datos!=null) {
                            List<String> jsonArray= Utilities.convertString(datos);

                            ventaDAO.deleteAll();


                            for (String s : jsonArray) {
                                System.out.println(s);
                                Venta v = Utilities.convertElement(s, Venta.class);
                                ventaDAO.save(v);
                            }

                            logDAO.save(new Log("Update productos"));
                        }

                    }



                } catch (ConverterException e) {
                    System.out.println("RuntimeException");
                    throw new RuntimeException(e);
                }


                //Los datos enviados desde el módulo de origen se encuentran en el atributo payload del body.


            }
        });

        //Comienza a consumir utilizando un hilo secundario
        consumer.consume(consumerConnection, Modules.USUARIO);


    }

}
