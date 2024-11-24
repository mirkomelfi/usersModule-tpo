package tpo.usersmodule.config;

import ar.edu.uade.Authenticator;
import ar.edu.uade.Broker;
import ar.edu.uade.Modules;
import com.rabbitmq.client.Connection;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ManejadorDeSesiones {
    Broker broker = new Broker(
            "3.141.117.124",
            5672,
            "usuario",
            "7@3635@N%8%^%#7%f2!5"

    );
    String tokenJWTModulo;
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    public  void logingInterno( ){
        System.out.println("---LOGIN: Iniciando login a GI...");
        Broker broker = new Broker(
                "3.141.117.124",
                5672,
                "usuario",
                "7@3635@N%8%^%#7%f2!5"
        );
        String username = "\"usuario\"";
        String password = "\"7@3635@N%8%^%#7%f2!5\"";


        String json = "{\"user\" : "+username+ "," +
                " \"password\" : "+password+"," +
                "  \"case\": \"login\"," +
                " \"origin\": \"usuario\"}";
        String response = "";
        Connection connection ;
        try {
            connection = broker.startConnection();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("---LOGIN: ERROR en el login (CORE)");
            return;
        }
        Authenticator auth = new Authenticator(Modules.USUARIO);
        try{
            response = auth.authenticate(connection,json);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("---LOGIN: ERROR en el login (Autenticacion)");
            return;
        }
        broker.endConnection(connection);
        System.out.println("---LOGIN: Login realizado. Token: "+ response);
        tokenJWTModulo = response;
    }
    public String getTokenJWTModulo() {
        return tokenJWTModulo;
    }
}