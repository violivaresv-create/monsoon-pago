package cl.monsoon.pago.configweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    
@Bean
    public OpenAPI apiInfo(){
        return new OpenAPI()
                        .info(new Info()
                                .title("Monsoon: Microservicio de Pago")
                                .version("0.0.2")
                                .description("microservicio de pago de videojuegos Monsoon, permite realizar pagos y consultar el estado de los mismos")
                    );
    }

}
