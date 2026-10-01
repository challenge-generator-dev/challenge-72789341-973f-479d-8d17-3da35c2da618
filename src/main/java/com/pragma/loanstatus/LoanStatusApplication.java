package com.pragma.loanstatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LoanStatusApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanStatusApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Inicializa datos de prueba para el flujo de estados.
     * Esta clase interna simula el comportamiento de los actores externos:
     * - Originador de créditos
     * - Motor de evaluación de riesgo
     * - Core bancario
     */
    public static class TestDataInitializer {
        public static void initializeTestData() {
            System.out.println("Inicializando datos de prueba para estados de préstamos...");
            // Simulación de flujo típico:
            // 1. Originador registra una solicitud en estado PENDIENTE
            // 2. Motor de evaluación la pasa a EN_PROCESO
            // 3. Core bancario decide entre APROBADO o RECHAZADO
            System.out.println("Flujo simulado:");
            System.out.println("1. Originador → PENDIENTE");
            System.out.println("2. Motor de riesgo → EN_PROCESO");
            System.out.println("3. Core bancario → APROBADO/RECHAZADO");
        }
    }
}