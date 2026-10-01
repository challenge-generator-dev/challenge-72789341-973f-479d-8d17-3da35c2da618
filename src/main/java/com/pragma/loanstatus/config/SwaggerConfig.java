package com.pragma.loanstatus.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración centralizada para la documentación OpenAPI/Swagger del sistema.
 * Expone la especificación OpenAPI 3.0 en /v3/api-docs y la UI en /swagger-ui.html.
 */
@Configuration
public class SwaggerConfig {

    private static final String API_TITLE = "Loan Status Management API";
    private static final String API_VERSION = "1.0.0";
    private static final String API_DESCRIPTION = "
        API REST para la gestión del ciclo de vida de solicitudes de préstamo en la plataforma de banca digital.
        
        ## Funcionalidades principales
        - Consulta de estados de solicitudes de préstamo
        - Actualización de estado con validación de transiciones permitidas
        - Verificación de consistencia entre sistemas (originador, motor de riesgo, core bancario)
        
        ## Estados del préstamo
        - PENDIENTE: Solicitud recibida, en espera de evaluación inicial
        - EN_PROCESO: Bajo evaluación del motor de riesgo
        - APROBADO: Resolución favorable del crédito
        - RECHAZADO: Solicitud denegada por criterios de riesgo
        
        ## Notas de integración
        El sistema valida que las transiciones de estado respeten el flujo del pipeline de CI,
        asegurando consistencia con un umbral de latencia máximo de 5 segundos.
    ";
    private static final String CONTACT_NAME = "Pragma Team";
    private static final String CONTACT_EMAIL = "dev@pragma.com.co";
    private static final String LICENSE_NAME = "Apache 2.0";
    private static final String LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(API_TITLE)
                        .version(API_VERSION)
                        .description(API_DESCRIPTION)
                        .contact(new Contact()
                                .name(CONTACT_NAME)
                                .email(CONTACT_EMAIL))
                        .license(new License()
                                .name(LICENSE_NAME)
                                .url(LICENSE_URL)))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Token de autenticación JWT para acceder a la API")));
    }