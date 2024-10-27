package tpo.usersmodule;

import ar.edu.uade.*;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Delivery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tpo.usersmodule.controller.Mensaje;
import tpo.usersmodule.model.entity.Noticia;
import tpo.usersmodule.model.entity.Usuario;


@SpringBootApplication
public class UsersModuleApplication {

    public static void main(String[] args)  throws Exception{

        SpringApplication.run(UsersModuleApplication.class, args);
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

        Connection consumerConnection = broker.startConnection();

        //Redefino el callback para los mensajes recibidos.
        Consumer consumer = new Consumer(new CallbackInterface() {
            @Override
            public void callback(String consumerTag, Delivery delivery) {
                //Body es la clase que encapsula a los datos enviados desde el módulo de origen.
                Body body = null;
                try {
                    body = Utilities.convertDelivery(delivery);
                    System.out.println(body);
                } catch (ConverterException e) {
                    throw new RuntimeException(e);
                }

                //Los datos enviados desde el módulo de origen se encuentran en el atributo payload del body.
                String datos = body.getPayload();
                System.out.println(datos);
                //Los datos enviados desde el módulo de origen pueden convertirse a cualquier clase del modelo, si corresponde.
                try {
                    Mensaje n = Utilities.convertBody(body, Mensaje.class);
                    System.out.println(n);
                } catch (ConverterException e) {

                }
            }
        });

        //Comienza a consumir utilizando un hilo secundario
        consumer.consume(consumerConnection, Modules.USUARIO);

        Connection publisherConnection = broker.startConnection();

        //Crea la instancia para poder publicar un mensaje
        Publisher publisher = new Publisher(Modules.USUARIO);

        // Admin crea una noticia
        Mensaje m = new Mensaje("Messi a San Lorenzo");


        //Convierto un objeto a un String de formato JSON.
        String mensaje = Utilities.convertClass(m);

        //El token es el JWT que entrega Gestion_Interna
        //Types presenta 3 variables, String, JSON o Array, utilizado para un mejor manejo del mensaje.
        publisher.publish(publisherConnection, mensaje, Modules.USUARIO, "Prueba", "token", Types.JSON);

        broker.endConnection(publisherConnection);

    }

}
