package tpo.usersmodule.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todas las rutas
<<<<<<< HEAD
                .allowedOrigins("https://main.d220bqspbr415z.amplifyapp.com/") // Cambia por tu dominio
=======
                .allowedOrigins("https://main.d220bqspbr415z.amplifyapp.com") // Cambia por tu dominio
>>>>>>> 33357469bebc29546d6ca07f01939633929fd97c
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Permite todos los encabezados
                .allowCredentials(true); // Si necesitas permitir credenciales
    }
}
