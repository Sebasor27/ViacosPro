import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class cors implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir todas las rutas de la API
                .allowedOrigins("http://localhost:3001") // Aquí pones el puerto de tu frontend (ajústalo si es otro)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite los métodos HTTP que necesites
                .allowedHeaders("*") // Permite todos los headers
                .allowCredentials(true); // Si necesitas enviar cookies o cabeceras de autenticación
    }
}
