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
import tpo.usersmodule.model.entity.Log;
import tpo.usersmodule.model.entity.Noticia;
import tpo.usersmodule.model.entity.Usuario;
import tpo.usersmodule.model.entity.Venta;

import java.util.Date;
import java.util.List;


@SpringBootApplication
public class UsersModuleApplication {

    public static void main(String[] args)  throws Exception{

        ApplicationContext a= SpringApplication.run(UsersModuleApplication.class, args);
        LogDAOImpl logDAO=a.getBean(LogDAOImpl.class);
        System.out.println("Pre broker");
        Broker broker = new Broker(
                "3.142.225.39",
                5672,
                "usuario",
                "!9*@B3#^447@@65y5@@8"

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
                    if (datos!=null){
                        logDAO.save(new Log(datos));
                    }
                    System.out.println(datos);
                } catch (ConverterException e) {
                    throw new RuntimeException(e);
                }

                try {
                    Venta venta = Utilities.convertBody(body, Venta.class);
                    System.out.println(venta);
                } catch (ConverterException e) {

                }

                //Los datos enviados desde el módulo de origen se encuentran en el atributo payload del body.


            }
        });

        //Comienza a consumir utilizando un hilo secundario
        consumer.consume(consumerConnection, Modules.USUARIO);

        Connection publisherConnection = broker.startConnection();
        System.out.println("publisherConnection");
        //Crea la instancia para poder publicar un mensaje
        Publisher publisher = new Publisher(Modules.USUARIO);

        // Admin crea un mensaje
        //Mensaje m = new Mensaje("Messi a San Lorenzo");
        //String msj="Messi a San Lorenzo";
        Venta v= new Venta(0,"pepe",new Date(),900000,5, List.of(4,5,8,8));

        //Convierto un objeto a un String de formato JSON.
        //String mensaje = Utilities.convertClass(m);


        //El token es el JWT que entrega Gestion_Interna
        //Types presenta 3 variables, String, JSON o Array, utilizado para un mejor manejo del mensaje.
        publisher.publish(publisherConnection, Utilities.convertClass(v), Modules.E_COMMERCE, "Venta", "token", Types.JSON);

        broker.endConnection(publisherConnection);

    }

}
