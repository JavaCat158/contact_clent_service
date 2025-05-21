package ru.ibragimov.clientcontactservice.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс для настройки Swagger (OpenAPI 3).
 *
 * Позволяет автоматически сгенерировать документацию REST API,
 * доступную по адресу: http://localhost:8080/swagger-ui.html
 *
 * Swagger помогает разработчикам и тестировщикам взаимодействовать с API
 * и проверять эндпоинты напрямую из браузера.
 */
@Configuration
public class SwaggerConfiguration {

    /**
     * Бин конфигурации OpenAPI, содержащий основную информацию о приложении.
     *
     * @return объект OpenAPI с заголовком, описанием и версией API
     */
    @Bean
    public OpenAPI clientContactOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Client Contact API")
                        .description("Управление клиентами и их контактами")
                        .version("1.0.0"));
    }
}
