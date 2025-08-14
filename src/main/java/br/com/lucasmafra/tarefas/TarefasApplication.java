package br.com.lucasmafra.tarefas;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TarefasApplication {
	public static void main(String[] args) {
		SpringApplication.run(TarefasApplication.class, args);
	}
    @Bean
    public OpenAPI customOpenAPI (@Value("${app.description}") String appDescription, @Value("${app.version}") String appVersion){
        return new OpenAPI().info(new Info()
                .title("Tarefa API")
                .version(appVersion)
                .description(appDescription)
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
