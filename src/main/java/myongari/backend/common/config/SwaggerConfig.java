package myongari.backend.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Server local = new Server();
        local.setUrl("http://localhost:8080");

        Server prod = new Server();
        prod.setUrl("https://api.myoungari.com");

        return new OpenAPI()
                .info(new Info().title("Myoungari API"))
                .servers(List.of(local, prod));
    }
}
