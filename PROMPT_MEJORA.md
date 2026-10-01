# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/loanstatus/service/LoanStatusService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanstatus/config/SwaggerConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanstatus/controller/LoanStatusController.java` — `LoanStatusRepository.findById`: Se invoca `findById` sobre `LoanStatusRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanstatus/controller/LoanStatusController.java` — `LoanApplication.map`: Se invoca `map` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanstatus/controller/LoanStatusController.java` — `LoanStatusRepository.save`: Se invoca `save` sobre `LoanStatusRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanstatus/service/LoanStatusService.java` — `LoanApplication.setCreatedAt`: Se invoca `setCreatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanstatus/service/LoanStatusService.java` — `LoanApplication.setUpdatedAt`: Se invoca `setUpdatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanstatus/service/LoanStatusService.java` — `LoanStatusRepository.save`: Se invoca `save` sobre `LoanStatusRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanstatus/exception/InvalidStateTransitionException.java` — `LoanStatus.name`: Se invoca `name` sobre `LoanStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanApplication.setCreatedAt`: Se invoca `setCreatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanApplication.setUpdatedAt`: Se invoca `setUpdatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanStatusRepository.findById`: Se invoca `findById` sobre `LoanStatusRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanStatusRepository.save`: Se invoca `save` sobre `LoanStatusRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanStatusService.actualizarEstado`: Se invoca `actualizarEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanStatusService.verificarConsistencia`: Se invoca `verificarConsistencia` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanStatusService.obtenerPorEstado`: Se invoca `obtenerPorEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanStatusService.contarPorEstado`: Se invoca `contarPorEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanApplication.setCreatedAt`: Se invoca `setCreatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanApplication.setUpdatedAt`: Se invoca `setUpdatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanStatusService.obtenerPorId`: Se invoca `obtenerPorId` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanStatusService.actualizarEstado`: Se invoca `actualizarEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanStatusService.obtenerPorEstado`: Se invoca `obtenerPorEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanStatusService.buscarPorApplicantId`: Se invoca `buscarPorApplicantId` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanStatusService.crearPrestamo`: Se invoca `crearPrestamo` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanStatusService.verificarConsistencia`: Se invoca `verificarConsistencia` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanApplication.setCreatedAt`: Se invoca `setCreatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanApplication.setUpdatedAt`: Se invoca `setUpdatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanStatusService.crearPrestamo`: Se invoca `crearPrestamo` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanStatusService.actualizarEstado`: Se invoca `actualizarEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanStatusService.obtenerPorId`: Se invoca `obtenerPorId` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanStatusService.verificarConsistencia`: Se invoca `verificarConsistencia` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanStatusService.obtenerPorEstado`: Se invoca `obtenerPorEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanStatusService.contarPorEstado`: Se invoca `contarPorEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
Testing complete status lifecycle through pipeline

### Reto
- Tema: Status Progression Test
- Seniority: junior-l2
- Tipo: practical
- Título: Implementación de prueba de progreso de estado en un pipeline de integración continua
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Definición de criterios de aceptación para la prueba — objetivo: Establecer los criterios de aceptación que la prueba de progreso de estado debe cumplir. — entregable (NO resolver): Documento con los criterios de aceptación detallados para la prueba de progreso de estado.
- Fase 2: Implementación de la prueba de progreso de estado — objetivo: Desarrollar la prueba que valida las transiciones de estado a lo largo del pipeline de CI. — entregable (NO resolver): Prueba implementada y configurada en el pipeline de CI que valida las transiciones de estado.
- Fase 3: Validación y optimización de la prueba — objetivo: Validar el funcionamiento de la prueba y optimizar su rendimiento. — entregable (NO resolver): Prueba validada y optimizada que se ejecuta de manera eficiente en el pipeline de CI.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.0</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>loanstatus</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>loanstatus</name>
    <description>Sistema de gestión de estados de solicitudes de préstamo</description>

    <properties>
        <java.version>21</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.3.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

// === ARCHIVO: src/main/java/com/pragma/loanstatus/LoanStatusApplication.java ===
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

// === ARCHIVO: src/main/java/com/pragma/loanstatus/model/LoanApplication.java ===
package com.pragma.loanstatus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String applicantId;

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String riskAssessmentId;
    private String coreBankingId;

    public enum LoanStatus {
        PENDIENTE,
        EN_PROCESO,
        APROBADO,
        RECHAZADO
    }

    public LoanApplication() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = LoanStatus.PENDIENTE;
    }

    public LoanApplication(String applicantId, Double amount) {
        this();
        this.applicantId = applicantId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getRiskAssessmentId() {
        return riskAssessmentId;
    }

    public void setRiskAssessmentId(String riskAssessmentId) {
        this.riskAssessmentId = riskAssessmentId;
    }

    public String getCoreBankingId() {
        return coreBankingId;
    }

    public void setCoreBankingId(String coreBankingId) {
        this.coreBankingId = coreBankingId;
    }

    /**
     * Valida que la transición de estado sea válida según el flujo:
     * PENDIENTE -> EN_PROCESO -> APROBADO/RECHAZADO
     * @param newStatus Estado al que se intenta cambiar
     * @return true si la transición es válida
     * @throws IllegalArgumentException si la transición es inválida
     */
    public boolean validateStateTransition(LoanStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo");
        }

        switch (this.status) {
            case PENDIENTE:
                if (newStatus != LoanStatus.EN_PROCESO) {
                    throw new IllegalArgumentException("Transición inválida: de PENDIENTE solo puede pasar a EN_PROCESO");
                }
                break;
            case EN_PROCESO:
                if (newStatus != LoanStatus.APROBADO && newStatus != LoanStatus.RECHAZADO) {
                    throw new IllegalArgumentException("Transición inválida: de EN_PROCESO solo puede pasar a APROBADO o RECHAZADO");
                }
                break;
            case APROBADO:
            case RECHAZADO:
                throw new IllegalArgumentException("Transición inválida: el estado " + this.status + " es final");
            default:
                throw new IllegalArgumentException("Estado desconocido: " + this.status);
        }
        return true;
    }

    /**
     * Verifica consistencia entre los IDs de los actores externos.
     * @throws IllegalStateException si hay inconsistencia
     */
    public void verifyConsistency() {
        if (this.status == LoanStatus.EN_PROCESO && (this.riskAssessmentId == null || this.riskAssessmentId.isEmpty())) {
            throw new IllegalStateException("Estado EN_PROCESO requiere riskAssessmentId");
        }
        if ((this.status == LoanStatus.APROBADO || this.status == LoanStatus.RECHAZADO) && 
            (this.coreBankingId == null || this.coreBankingId.isEmpty())) {
            throw new IllegalStateException("Estado final requiere coreBankingId");
        }
        if (this.status == LoanStatus.PENDIENTE && (this.riskAssessmentId != null || this.coreBankingId != null)) {
            throw new IllegalStateException("Estado PENDIENTE no debe tener IDs de actores externos");
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanstatus/model/LoanStatus.java ===
package com.pragma.loanstatus.model;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public enum LoanStatus {
    PENDING {
        @Override
        public Set<LoanStatus> getAllowedTransitions() {
            return new HashSet<>(Arrays.asList(PROCESSING, REJECTED));
        }
    },
    PROCESSING {
        @Override
        public Set<LoanStatus> getAllowedTransitions() {
            return new HashSet<>(Arrays.asList(APPROVED, REJECTED));
        }
    },
    APPROVED {
        @Override
        public Set<LoanStatus> getAllowedTransitions() {
            return new HashSet<>();
        }
    },
    REJECTED {
        @Override
        public Set<LoanStatus> getAllowedTransitions() {
            return new HashSet<>();
        }
    };

    public abstract Set<LoanStatus> getAllowedTransitions();

    public boolean isFinalState() {
        return this == APPROVED || this == REJECTED;
    }

    public static boolean isValidTransition(LoanStatus currentStatus, LoanStatus newStatus) {
        if (currentStatus == null || newStatus == null) {
            return false;
        }
        return currentStatus.getAllowedTransitions().contains(newStatus);
    }
}
"

// === ARCHIVO: src/main/java/com/pragma/loanstatus/repository/LoanStatusRepository.java ===
package com.pragma.loanstatus.repository;

import com.pragma.loanstatus.model.LoanApplication;
import com.pragma.loanstatus.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanStatusRepository extends JpaRepository<LoanApplication, Long> {

    List<LoanApplication> findByStatus(LoanStatus status);

    List<LoanApplication> findByApplicantId(String applicantId);

    Optional<LoanApplication> findByRiskAssessmentId(String riskAssessmentId);

    Optional<LoanApplication> findByCoreBankingId(String coreBankingId);

    List<LoanApplication> findByStatusIn(List<LoanStatus> statuses);

    List<LoanApplication> findByCreatedAtBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);

    long countByStatus(LoanStatus status);
}

// === ARCHIVO: src/main/resources/application.properties ===
# Configuración de la aplicación Spring Boot
spring.application.name=loanstatus

# Configuración del servidor
server.port=8080

# Configuración de JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect

# Configuración de la base de datos H2 en memoria
spring.datasource.url=jdbc:h2:mem:loanstatusdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Configuración de latencia y consistencia del dominio
# Umbral máximo de latencia para actualización de estados (en milisegundos)
loanstatus.max-latency-ms=5000

# Propiedades de consistencia entre originador de créditos y core bancario
loanstatus.consistency.check-enabled=true
loanstatus.consistency.sync-timeout-ms=5000
loanstatus.consistency.retry-attempts=3
loanstatus.consistency.retry-delay-ms=1000

# Configuración de transiciones de estado
loanstatus.state.transition.timeout-ms=3000
loanstatus.state.transition.retry-enabled=true

# Configuración de logging
logging.level.com.pragma.loanstatus=DEBUG
logging.level.org.springframework.web=INFO
logging.level.org.hibernate.SQL=DEBUG

# Configuración de Swagger/OpenAPI
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true

// === ARCHIVO: src/main/java/com/pragma/loanstatus/controller/LoanStatusController.java ===
package com.pragma.loanstatus.controller;

import com.pragma.loanstatus.model.LoanApplication;
import com.pragma.loanstatus.model.LoanStatus;
import com.pragma.loanstatus.repository.LoanStatusRepository;
import com.pragma.loanstatus.service.LoanStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/loan-status")
public class LoanStatusController {

    private final LoanStatusService loanStatusService;
    private final LoanStatusRepository loanStatusRepository;

    @Autowired
    public LoanStatusController(LoanStatusService loanStatusService, LoanStatusRepository loanStatusRepository) {
        this.loanStatusService = loanStatusService;
        this.loanStatusRepository = loanStatusRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplication> getLoanStatus(@PathVariable Long id) {
        Optional<LoanApplication> loanApplication = loanStatusRepository.findById(id);
        return loanApplication.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<List<LoanApplication>> getLoansByApplicant(@PathVariable String applicantId) {
        List<LoanApplication> loans = loanStatusRepository.findByApplicantId(applicantId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LoanApplication>> getLoansByStatus(@PathVariable LoanStatus status) {
        List<LoanApplication> loans = loanStatusRepository.findByStatus(status);
        return ResponseEntity.ok(loans);
    }

    @PostMapping
    public ResponseEntity<LoanApplication> createLoanApplication(@RequestBody LoanApplication loanApplication) {
        LoanApplication created = loanStatusService.createLoanApplication(
                loanApplication.getApplicantId(),
                loanApplication.getAmount()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<LoanApplication> updateLoanStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> statusUpdate) {
        
        String newStatusStr = statusUpdate.get("status");
        if (newStatusStr == null || newStatusStr.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            LoanStatus newStatus = LoanStatus.valueOf(newStatusStr.toUpperCase());
            Optional<LoanApplication> existingLoan = loanStatusRepository.findById(id);
            
            if (existingLoan.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            LoanApplication loan = existingLoan.get();
            boolean updated = loanStatusService.transitionStatus(loan, newStatus);
            
            if (updated) {
                return ResponseEntity.ok(loanStatusRepository.save(loan));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<LoanApplication> approveLoan(@PathVariable Long id) {
        Optional<LoanApplication> existingLoan = loanStatusRepository.findById(id);
        
        if (existingLoan.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        LoanApplication loan = existingLoan.get();
        boolean approved = loanStatusService.approveLoan(loan);
        
        if (approved) {
            return ResponseEntity.ok(loanStatusRepository.save(loan));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<LoanApplication> rejectLoan(@PathVariable Long id, @RequestBody Map<String, String> rejectionData) {
        Optional<LoanApplication> existingLoan = loanStatusRepository.findById(id);
        
        if (existingLoan.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        LoanApplication loan = existingLoan.get();
        String reason = rejectionData.getOrDefault("reason", "Rechazo no especificado");
        boolean rejected = loanStatusService.rejectLoan(loan, reason);
        
        if (rejected) {
            return ResponseEntity.ok(loanStatusRepository.save(loan));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of("status", "UP", "service", "loan-status"));
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanstatus/service/LoanStatusService.java ===
package com.pragma.loanstatus.service;

import com.pragma.loanstatus.exception.InvalidStateTransitionException;
import com.pragma.loanstatus.exception.StateConsistencyException;
import com.pragma.loanstatus.model.LoanApplication;
import com.pragma.loanstatus.model.LoanStatus;
import com.pragma.loanstatus.repository.LoanStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LoanStatusService {

    private static final Logger logger = LoggerFactory.getLogger(LoanStatusService.class);

    private final LoanStatusRepository loanStatusRepository;

    @Value("${loanstatus.max-latency-ms:5000}")
    private long maxLatencyMs;

    @Value("${loanstatus.consistency.check-enabled:true}")
    private boolean consistencyCheckEnabled;

    @Value("${loanstatus.consistency.sync-timeout-ms:5000}")
    private long consistencyTimeoutMs;

    @Value("${loanstatus.state.transition.timeout-ms:3000}")
    private long transitionTimeoutMs;

    @Autowired
    public LoanStatusService(LoanStatusRepository loanStatusRepository) {
        this.loanStatusRepository = loanStatusRepository;
    }

    public LoanApplication createLoanApplication(String applicantId, Double amount) {
        logger.info("Creating loan application for applicant: {} with amount: {}", applicantId, amount);

        if (applicantId == null || applicantId.isBlank()) {
            throw new IllegalArgumentException("El applicantId no puede ser nulo o vacío");
        }

        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }

        LoanApplication loanApplication = new LoanApplication(applicantId, amount);
        loanApplication.setStatus(LoanStatus.PENDIENTE);
        loanApplication.setCreatedAt(LocalDateTime.now());
        loanApplication.setUpdatedAt(LocalDateTime.now());

        String riskAssessmentId = "RA-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        loanApplication.setRiskAssessmentId(riskAssessmentId);

        LoanApplication saved = loanStatusRepository.save(loanApplication);
        logger.info("Loan application created with ID: {} and status: {}", saved.getId(), saved.getStatus());

        return saved;
    }

    public boolean transitionStatus(LoanApplication loanApplication, LoanStatus newStatus) {
        logger.info("Attempting transition from {} to {} for loan ID: {}", 
                loanApplication.getStatus(), newStatus, loanApplication.getId());

        if (!loanApplication.validateStateTransition(newStatus)) {
            logger.warn("Invalid state transition from {} to {} for loan ID: {}", 
                    loanApplication.getStatus(), newStatus, loanApplication.getId());
            throw new InvalidStateTransitionException(
                    String.format("Transición inválida de %s a %s", loanApplication.getStatus(), newStatus)
            );
        }

        LoanStatus previousStatus = loanApplication.getStatus();
        loanApplication.setStatus(newStatus);
        loanApplication.setUpdatedAt(LocalDateTime.now());

        if (consistencyCheckEnabled) {
            verifyConsistencyWithCoreBanking(loanApplication);
        }

        boolean success = LoanStatus.isValidTransition(previousStatus, newStatus);
        
        if (success) {
            logger.info("State transition successful: {} -> {} for loan ID: {}", 
                    previousStatus, newStatus, loanApplication.getId());
        }

        return success;
    }

    public boolean approveLoan(LoanApplication loanApplication) {
        logger.info("Approving loan application ID: {}", loanApplication.getId());

        try {
            LoanStatus approvedStatus = LoanStatus.APROBADO;
            boolean transitioned = transitionStatus(loanApplication, approvedStatus);

            if (transitioned && loanApplication.getCoreBankingId() == null) {
                String coreBankingId = "CB-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
                loanApplication.setCoreBankingId(coreBankingId);
                logger.info("Core banking ID assigned: {} to loan ID: {}", coreBankingId, loanApplication.getId());
            }

            return transitioned;
        } catch (InvalidStateTransitionException e) {
            logger.error("Cannot approve loan ID {}: {}", loanApplication.getId(), e.getMessage());
            return false;
        }
    }

    public boolean rejectLoan(LoanApplication loanApplication, String reason) {
        logger.info("Rejecting loan application ID: {} with reason: {}", loanApplication.getId(), reason);

        try {
            LoanStatus rejectedStatus = LoanStatus.RECHAZADO;
            return transitionStatus(loanApplication, rejectedStatus);
        } catch (InvalidStateTransitionException e) {
            logger.error("Cannot reject loan ID {}: {}", loanApplication.getId(), e.getMessage());
            return false;
        }
    }

    public LoanApplication processRiskAssessment(LoanApplication loanApplication) {
        logger.info("Processing risk assessment for loan ID: {}", loanApplication.getId());

        if (loanApplication.getRiskAssessmentId() == null) {
            throw new IllegalStateException("El ID de evaluación de riesgo no puede ser nulo");
        }

        LoanStatus currentStatus = loanApplication.getStatus();
        if (currentStatus == LoanStatus.PENDIENTE) {
            loanApplication.setStatus(LoanStatus.EN_PROCESO);
            loanApplication.setUpdatedAt(LocalDateTime.now());
            logger.info("Risk assessment completed for loan ID: {}. Status changed to EN_PROCESO", loanApplication.getId());
        }

        return loanStatusRepository.save(loanApplication);
    }

    public List<LoanApplication> getLoansByStatus(LoanStatus status) {
        logger.debug("Fetching loans with status: {}", status);
        return loanStatusRepository.findByStatus(status);
    }

    public List<LoanApplication> getLoansByApplicant(String applicantId) {
        logger.debug("Fetching loans for applicant: {}", applicantId);
        return loanStatusRepository.findByApplicantId(applicantId);
    }

    private void verifyConsistencyWithCoreBanking(LoanApplication loanApplication) {
        long startTime = System.currentTimeMillis();
        logger.debug("Verifying consistency with core banking for loan ID: {}", loanApplication.getId());

        try {
            loanApplication.verifyConsistency();
            
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime > maxLatencyMs) {
                logger.warn("Consistency check exceeded latency threshold: {}ms > {}ms for loan ID: {}",
                        elapsedTime, maxLatencyMs, loanApplication.getId());
            }

            if (elapsedTime > consistencyTimeoutMs) {
                throw new StateConsistencyException(
                        String.format("Timeout de consistencia excedido: %dms > %dms", 
                                elapsedTime, consistencyTimeoutMs)
                );
            }

            logger.debug("Consistency verification completed in {}ms for loan ID: {}", 
                    elapsedTime, loanApplication.getId());
        } catch (Exception e) {
            logger.error("Consistency check failed for loan ID: {}", loanApplication.getId(), e);
            throw new StateConsistencyException("Error al verificar consistencia con core bancario", e);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanstatus/exception/InvalidStateTransitionException.java ===
package com.pragma.loanstatus.exception;

import com.pragma.loanstatus.model.LoanStatus;

public class InvalidStateTransitionException extends RuntimeException {
    private final LoanStatus currentStatus;
    private final LoanStatus requestedStatus;
    private final String applicantId;

    public InvalidStateTransitionException(LoanStatus currentStatus, LoanStatus requestedStatus) {
        super(String.format("Transición de estado inválida: de %s a %s", 
            currentStatus != null ? currentStatus.name() : "null",
            requestedStatus != null ? requestedStatus.name() : "null"));
        this.currentStatus = currentStatus;
        this.requestedStatus = requestedStatus;
        this.applicantId = null;
    }

    public InvalidStateTransitionException(LoanStatus currentStatus, LoanStatus requestedStatus, String applicantId) {
        super(String.format("Transición de estado inválida para solicitante %s: de %s a %s", 
            applicantId,
            currentStatus != null ? currentStatus.name() : "null",
            requestedStatus != null ? requestedStatus.name() : "null"));
        this.currentStatus = currentStatus;
        this.requestedStatus = requestedStatus;
        this.applicantId = applicantId;
    }

    public InvalidStateTransitionException(String message) {
        super(message);
        this.currentStatus = null;
        this.requestedStatus = null;
        this.applicantId = null;
    }

    public InvalidStateTransitionException(String message, Throwable cause) {
        super(message, cause);
        this.currentStatus = null;
        this.requestedStatus = null;
        this.applicantId = null;
    }

    public LoanStatus getCurrentStatus() {
        return currentStatus;
    }

    public LoanStatus getRequestedStatus() {
        return requestedStatus;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Error: No se puede realizar la transición de estado.\n");
        if (currentStatus != null && requestedStatus != null) {
            sb.append("Estado actual: ").append(currentStatus.name()).append("\n");
            sb.append("Estado solicitado: ").append(requestedStatus.name()).append("\n");
            if (currentStatus.getAllowedTransitions() != null && !currentStatus.getAllowedTransitions().isEmpty()) {
                sb.append("Estados permitidos desde ").append(currentStatus.name()).append(": ");
                currentStatus.getAllowedTransitions().forEach(s -> sb.append(s.name()).append(" "));
            }
        }
        return sb.toString();
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanstatus/exception/StateConsistencyException.java ===
package com.pragma.loanstatus.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class StateConsistencyException extends RuntimeException {
    private final String loanApplicationId;
    private final String originSystem;
    private final String targetSystem;
    private final Map<String, Object> inconsistencyDetails;
    private final LocalDateTime detectedAt;

    public StateConsistencyException(String loanApplicationId, String originSystem, String targetSystem) {
        super(String.format("Inconsistencia de estado detectada para solicitud %s entre %s y %s", 
            loanApplicationId, originSystem, targetSystem));
        this.loanApplicationId = loanApplicationId;
        this.originSystem = originSystem;
        this.targetSystem = targetSystem;
        this.inconsistencyDetails = new HashMap<>();
        this.detectedAt = LocalDateTime.now();
    }

    public StateConsistencyException(String loanApplicationId, String originSystem, String targetSystem, 
                                     String message) {
        super(message);
        this.loanApplicationId = loanApplicationId;
        this.originSystem = originSystem;
        this.targetSystem = targetSystem;
        this.inconsistencyDetails = new HashMap<>();
        this.detectedAt = LocalDateTime.now();
    }

    public StateConsistencyException(String loanApplicationId, String originSystem, String targetSystem,
                                     Map<String, Object> details) {
        super(String.format("Inconsistencia de estado detectada para solicitud %s entre %s y %s", 
            loanApplicationId, originSystem, targetSystem));
        this.loanApplicationId = loanApplicationId;
        this.originSystem = originSystem;
        this.targetSystem = targetSystem;
        this.inconsistencyDetails = details != null ? new HashMap<>(details) : new HashMap<>();
        this.detectedAt = LocalDateTime.now();
    }

    public StateConsistencyException(String message, Throwable cause) {
        super(message, cause);
        this.loanApplicationId = null;
        this.originSystem = null;
        this.targetSystem = null;
        this.inconsistencyDetails = new HashMap<>();
        this.detectedAt = LocalDateTime.now();
    }

    public String getLoanApplicationId() {
        return loanApplicationId;
    }

    public String getOriginSystem() {
        return originSystem;
    }

    public String getTargetSystem() {
        return targetSystem;
    }

    public Map<String, Object> getInconsistencyDetails() {
        return new HashMap<>(inconsistencyDetails);
    }

    public LocalDateTime getDetectedAt() {
        return detectedAt;
    }

    public void addDetail(String key, Object value) {
        this.inconsistencyDetails.put(key, value);
    }

    public boolean isLatencyViolation() {
        return inconsistencyDetails.containsKey("latencyMs") && 
               inconsistencyDetails.get("latencyMs") instanceof Number &&
               ((Number) inconsistencyDetails.get("latencyMs")).longValue() > 5000;
    }

    public String getDetailedMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Error de Consistencia de Estado ===\n");
        sb.append("ID Solicitud: ").append(loanApplicationId).append("\n");
        sb.append("Sistema Origen: ").append(originSystem).append("\n");
        sb.append("Sistema Destino: ").append(targetSystem).append("\n");
        sb.append("Detectado: ").append(detectedAt).append("\n");
        if (!inconsistencyDetails.isEmpty()) {
            sb.append("Detalles:\n");
            inconsistencyDetails.forEach((k, v) -> sb.append("  - ").append(k).append(": ").append(v).append("\n"));
        }
        sb.append("Umbral de latencia: 5000ms\n");
        if (isLatencyViolation()) {
            sb.append("ADVERTENCIA: Se excedió el umbral de latencia máximo\n");
        }
        return sb.toString();
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanstatus/exception/GlobalExceptionHandler.java ===
package com.pragma.loanstatus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidStateTransitionException.class)
    public ResponseEntity<Object> handleInvalidStateTransition(
            InvalidStateTransitionException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Invalid State Transition");
        body.put("message", ex.getMessage());
        
        Map<String, Object> details = new HashMap<>();
        if (ex.getCurrentStatus() != null) {
            details.put("currentStatus", ex.getCurrentStatus().name());
        }
        if (ex.getRequestedStatus() != null) {
            details.put("requestedStatus", ex.getRequestedStatus().name());
        }
        if (ex.getApplicantId() != null) {
            details.put("applicantId", ex.getApplicantId());
        }
        details.put("allowedTransitions", ex.getDetailedMessage());
        body.put("details", details);
        body.put("path", request.getDescription(false).replace("uri=", ""));
        
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StateConsistencyException.class)
    public ResponseEntity<Object> handleStateConsistencyException(
            StateConsistencyException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", "State Consistency Error");
        body.put("message", ex.getMessage());
        
        Map<String, Object> details = new HashMap<>();
        details.put("loanApplicationId", ex.getLoanApplicationId());
        details.put("originSystem", ex.getOriginSystem());
        details.put("targetSystem", ex.getTargetSystem());
        details.put("detectedAt", ex.getDetectedAt().toString());
        details.put("inconsistencyDetails", ex.getInconsistencyDetails());
        details.put("latencyViolation", ex.isLatencyViolation());
        body.put("details", details);
        body.put("path", request.getDescription(false).replace("uri=", ""));
        
        HttpStatus status = ex.isLatencyViolation() ? HttpStatus.SERVICE_UNAVAILABLE : HttpStatus.CONFLICT;
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));
        
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointer(
            NullPointerException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "Se produjo un error interno: " + ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));
        
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(
            Exception ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "Error inesperado: " + ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));
        
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanstatus/config/SwaggerConfig.java ===
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

// === ARCHIVO: src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java ===
package com.pragma.loanstatus.service;

import com.pragma.loanstatus.exception.InvalidStateTransitionException;
import com.pragma.loanstatus.exception.StateConsistencyException;
import com.pragma.loanstatus.model.LoanApplication;
import com.pragma.loanstatus.model.LoanStatus;
import com.pragma.loanstatus.repository.LoanStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para LoanStatusService")
class LoanStatusServiceTest {

    @Mock
    private LoanStatusRepository loanStatusRepository;

    @InjectMocks
    private LoanStatusService loanStatusService;

    private LoanApplication loanApplication;

    @BeforeEach
    void setUp() {
        loanApplication = new LoanApplication("APPLICANT_001", 50000.00);
        loanApplication.setId(1L);
        loanApplication.setStatus(LoanStatus.PENDIENTE);
        loanApplication.setCreatedAt(LocalDateTime.now());
        loanApplication.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    @DisplayName("Debe aprobar la transición de PENDIENTE a EN_PROCESO")
    void debePermitirTransicionPendienteAEnProceso() {
        when(loanStatusRepository.findById(1L)).thenReturn(Optional.of(loanApplication));
        when(loanStatusRepository.save(any(LoanApplication.class))).thenAnswer(inv -> inv.getArgument(0));

        LoanApplication resultado = loanStatusService.actualizarEstado(1L, LoanStatus.EN_PROCESO);

        assertNotNull(resultado);
        assertEquals(LoanStatus.EN_PROCESO, resultado.getStatus());
        verify(loanStatusRepository).save(any(LoanApplication.class));
    }

    @Test
    @DisplayName("Debe aprobar la transición de EN_PROCESO a APROBADO")
    void debePermitirTransicionEnProcesoAAprobado() {
        loanApplication.setStatus(LoanStatus.EN_PROCESO);
        when(loanStatusRepository.findById(1L)).thenReturn(Optional.of(loanApplication));
        when(loanStatusRepository.save(any(LoanApplication.class))).thenAnswer(inv -> inv.getArgument(0));

        LoanApplication resultado = loanStatusService.actualizarEstado(1L, LoanStatus.APROBADO);

        assertNotNull(resultado);
        assertEquals(LoanStatus.APROBADO, resultado.getStatus());
    }

    @Test
    @DisplayName("Debe aprobar la transición de EN_PROCESO a RECHAZADO")
    void debePermitirTransicionEnProcesoARechazado() {
        loanApplication.setStatus(LoanStatus.EN_PROCESO);
        when(loanStatusRepository.findById(1L)).thenReturn(Optional.of(loanApplication));
        when(loanStatusRepository.save(any(LoanApplication.class))).thenAnswer(inv -> inv.getArgument(0));

        LoanApplication resultado = loanStatusService.actualizarEstado(1L, LoanStatus.RECHAZADO);

        assertNotNull(resultado);
        assertEquals(LoanStatus.RECHAZADO, resultado.getStatus());
    }

    @Test
    @DisplayName("Debe rechazar la transición directa de PENDIENTE a APROBADO")
    void debeRechazarTransicionInvalidaPendienteAAprobado() {
        when(loanStatusRepository.findById(1L)).thenReturn(Optional.of(loanApplication));

        assertThrows(InvalidStateTransitionException.class, () -> {
            loanStatusService.actualizarEstado(1L, LoanStatus.APROBADO);
        });

        verify(loanStatusRepository, never()).save(any(LoanApplication.class));
    }

    @Test
    @DisplayName("Debe rechazar la transición de PENDIENTE a RECHAZADO")
    void debeRechazarTransicionInvalidaPendienteARechazado() {
        when(loanStatusRepository.findById(1L)).thenReturn(Optional.of(loanApplication));

        assertThrows(InvalidStateTransitionException.class, () -> {
            loanStatusService.actualizarEstado(1L, LoanStatus.RECHAZADO);
        });

        verify(loanStatusRepository, never()).save(any(LoanApplication.class));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el préstamo no existe")
    void debeLanzarExcepcionCuandoPrestamoNoExiste() {
        when(loanStatusRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            loanStatusService.actualizarEstado(999L, LoanStatus.EN_PROCESO);
        });
    }

    @Test
    @DisplayName("Debe verificar consistencia del estado correctamente")
    void debeVerificarConsistenciaCorrectamente() {
        loanApplication.setRiskAssessmentId("RISK_001");
        loanApplication.setCoreBankingId("CORE_001");
        loanApplication.setStatus(LoanStatus.APROBADO);
        when(loanStatusRepository.findById(1L)).thenReturn(Optional.of(loanApplication));

        assertDoesNotThrow(() -> loanStatusService.verificarConsistencia(1L));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando hay inconsistencia de estado")
    void debeLanzarExcepcionCuandoHayInconsistencia() {
        loanApplication.setRiskAssessmentId("RISK_001");
        loanApplication.setCoreBankingId(null);
        loanApplication.setStatus(LoanStatus.EN_PROCESO);
        when(loanStatusRepository.findById(1L)).thenReturn(Optional.of(loanApplication));

        assertThrows(StateConsistencyException.class, () -> {
            loanStatusService.verificarConsistencia(1L);
        });
    }

    @Test
    @DisplayName("Debe obtener préstamos por estado correctamente")
    void debeObtenerPrestamosPorEstado() {
        when(loanStatusRepository.findByStatus(LoanStatus.PENDIENTE)).thenReturn(java.util.List.of(loanApplication));

        var resultados = loanStatusService.obtenerPorEstado(LoanStatus.PENDIENTE);

        assertNotNull(resultados);
        assertEquals(1, resultados.size());
        verify(loanStatusRepository).findByStatus(LoanStatus.PENDIENTE);
    }

    @Test
    @DisplayName("Debe contar préstamos por estado correctamente")
    void debeContarPrestamosPorEstado() {
        when(loanStatusRepository.countByStatus(LoanStatus.PENDIENTE)).thenReturn(5L);

        long conteo = loanStatusService.contarPorEstado(LoanStatus.PENDIENTE);

        assertEquals(5L, conteo);
        verify(loanStatusRepository).countByStatus(LoanStatus.PENDIENTE);
    }
}

// === ARCHIVO: src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java ===
package com.pragma.loanstatus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.loanstatus.model.LoanApplication;
import com.pragma.loanstatus.model.LoanStatus;
import com.pragma.loanstatus.service.LoanStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Pruebas de integración para LoanStatusController")
class LoanStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoanStatusService loanStatusService;

    private LoanApplication loanApplication;

    @BeforeEach
    void setUp() {
        loanApplication = new LoanApplication("APPLICANT_001", 50000.00);
        loanApplication.setId(1L);
        loanApplication.setStatus(LoanStatus.PENDIENTE);
        loanApplication.setCreatedAt(LocalDateTime.now());
        loanApplication.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    @DisplayName("GET /api/loan-status/1 debe retornar el préstamo con estado 200")
    void debeObtenerPrestamoPorId() throws Exception {
        when(loanStatusService.obtenerPorId(1L)).thenReturn(Optional.of(loanApplication));

        mockMvc.perform(get("/api/loan-status/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.applicantId").value("APPLICANT_001"))
                .andExpect(jsonPath("$.status").value("PENDIENTE"));
    }

    @Test
    @DisplayName("GET /api/loan-status/999 debe retornar 404 cuando no existe")
    void debeRetornar404CuandoNoExiste() throws Exception {
        when(loanStatusService.obtenerPorId(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/loan-status/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /api/loan-status/1/status debe actualizar el estado correctamente")
    void debeActualizarEstado() throws Exception {
        LoanApplication actualizado = new LoanApplication("APPLICANT_001", 50000.00);
        actualizado.setId(1L);
        actualizado.setStatus(LoanStatus.EN_PROCESO);
        actualizado.setCreatedAt(LocalDateTime.now());
        actualizado.setUpdatedAt(LocalDateTime.now());

        when(loanStatusService.actualizarEstado(eq(1L), eq(LoanStatus.EN_PROCESO)))
                .thenReturn(actualizado);

        mockMvc.perform(put("/api/loan-status/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nuevoEstado\": \"EN_PROCESO\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("EN_PROCESO"));
    }

    @Test
    @DisplayName("GET /api/loan-status/status/PENDIENTE debe listar préstamos por estado")
    void debeListarPrestamosPorEstado() throws Exception {
        when(loanStatusService.obtenerPorEstado(LoanStatus.PENDIENTE))
                .thenReturn(List.of(loanApplication));

        mockMvc.perform(get("/api/loan-status/status/PENDIENTE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].status").value("PENDIENTE"));
    }

    @Test
    @DisplayName("GET /api/loan-status/applicant/APPLICANT_001 debe buscar por applicantId")
    void debeBuscarPorApplicantId() throws Exception {
        when(loanStatusService.buscarPorApplicantId("APPLICANT_001"))
                .thenReturn(List.of(loanApplication));

        mockMvc.perform(get("/api/loan-status/applicant/APPLICANT_001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].applicantId").value("APPLICANT_001"));
    }

    @Test
    @DisplayName("POST /api/loan-status debe crear un nuevo préstamo")
    void debeCrearNuevoPrestamo() throws Exception {
        when(loanStatusService.crearPrestamo(any(LoanApplication.class)))
                .thenAnswer(inv -> {
                    LoanApplication nuevo = inv.getArgument(0);
                    nuevo.setId(1L);
                    nuevo.setCreatedAt(LocalDateTime.now());
                    return nuevo;
                });

        String jsonRequest = objectMapper.writeValueAsString(loanApplication);

        mockMvc.perform(post("/api/loan-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    @DisplayName("GET /api/loan-status/1/consistency debe verificar consistencia")
    void debeVerificarConsistencia() throws Exception {
        when(loanStatusService.verificarConsistencia(1L)).thenReturn(loanApplication);

        mockMvc.perform(get("/api/loan-status/1/consistency"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}

// === ARCHIVO: src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java ===
package com.pragma.loanstatus;

import com.pragma.loanstatus.model.LoanApplication;
import com.pragma.loanstatus.model.LoanStatus;
import com.pragma.loanstatus.repository.LoanStatusRepository;
import com.pragma.loanstatus.service.LoanStatusService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@DisplayName("Prueba de integración completa - Pipeline CI")
class LoanStatusIntegrationTest {

    @Autowired
    private LoanStatusService loanStatusService;

    @Autowired
    private LoanStatusRepository loanStatusRepository;

    @Test
    @DisplayName("Debe ejecutar el flujo completo del pipeline de estados correctamente")
    void debeEjecutarFlujoCompletoDelPipeline() {
        LoanApplication solicitud = new LoanApplication("CANDIDATO_001", 100000.00);
        solicitud.setCreatedAt(LocalDateTime.now());
        solicitud.setUpdatedAt(LocalDateTime.now());

        LoanApplication creada = loanStatusService.crearPrestamo(solicitud);
        assertNotNull(creada.getId());
        assertEquals(LoanStatus.PENDIENTE, creada.getStatus());

        LoanApplication.enumStatusTransition(creada, LoanStatus.EN_PROCESO);
        LoanApplication transition1 = loanStatusService.actualizarEstado(creada.getId(), LoanStatus.EN_PROCESO);
        assertEquals(LoanStatus.EN_PROCESO, transition1.getStatus());
        assertNotNull(transition1.getUpdatedAt());

        LoanApplication transition2 = loanStatusService.actualizarEstado(creada.getId(), LoanStatus.APROBADO);
        assertEquals(LoanStatus.APROBADO, transition2.getStatus());
        assertTrue(transition2.getStatus().isFinalState());

        Optional<LoanApplication> consultada = loanStatusService.obtenerPorId(creada.getId());
        assertTrue(consultada.isPresent());
        assertEquals(LoanStatus.APROBADO, consultada.get().getStatus());
    }

    @Test
    @DisplayName("Debe rechazar transiciones de estado inválidas")
    void debeRechazarTransicionesInvalidas() {
        LoanApplication solicitud = new LoanApplication("CANDIDATO_002", 50000.00);
        solicitud.setCreatedAt(LocalDateTime.now());
        solicitud.setUpdatedAt(LocalDateTime.now());

        LoanApplication creada = loanStatusService.crearPrestamo(solicitud);
        assertThrows(Exception.class, () -> {
            loanStatusService.actualizarEstado(creada.getId(), LoanStatus.APROBADO);
        });

        Optional<LoanApplication> consultada = loanStatusService.obtenerPorId(creada.getId());
        assertTrue(consultada.isPresent());
        assertEquals(LoanStatus.PENDIENTE, consultada.get().getStatus());
    }

    @Test
    @DisplayName("Debe mantener consistencia entre originador y core bancario")
    void debeMantenerConsistenciaEntreSistemas() {
        LoanApplication solicitud = new LoanApplication("CANDIDATO_003", 75000.00);
        solicitud.setRiskAssessmentId("RISK_ASSESSMENT_003");
        solicitud.setCoreBankingId("CORE_BANKING_003");
        solicitud.setCreatedAt(LocalDateTime.now());
        solicitud.setUpdatedAt(LocalDateTime.now());

        LoanApplication creada = loanStatusService.crearPrestamo(solicitud);
        loanStatusService.actualizarEstado(creada.getId(), LoanStatus.EN_PROCESO);

        LoanApplication verificada = loanStatusService.verificarConsistencia(creada.getId());
        assertNotNull(verificada.getRiskAssessmentId());
        assertNotNull(verificada.getCoreBankingId());
        assertEquals("RISK_ASSESSMENT_003", verificada.getRiskAssessmentId());
        assertEquals("CORE_BANKING_003", verificada.getCoreBankingId());
    }

    @Test
    @DisplayName("Debe consultar préstamos por diferentes criterios")
    void debeConsultarPorCriterios() {
        LoanApplication solicitud1 = new LoanApplication("CANDIDATO_004", 100000.00);
        solicitud1.setStatus(LoanStatus.PENDIENTE);
        solicitud1.setCreatedAt(LocalDateTime.now());
        solicitud1.setUpdatedAt(LocalDateTime.now());

        LoanApplication solicitud2 = new LoanApplication("CANDIDATO_005", 50000.00);
        solicitud2.setStatus(LoanStatus.EN_PROCESO);
        solicitud2.setCreatedAt(LocalDateTime.now());
        solicitud2.setUpdatedAt(LocalDateTime.now());

        loanStatusService.crearPrestamo(solicitud1);
        loanStatusService.crearPrestamo(solicitud2);

        List<LoanApplication> pendientes = loanStatusService.obtenerPorEstado(LoanStatus.PENDIENTE);
        assertFalse(pendientes.isEmpty());

        List<LoanApplication> enProceso = loanStatusService.obtenerPorEstado(LoanStatus.EN_PROCESO);
        assertFalse(enProceso.isEmpty());

        long conteoPendientes = loanStatusService.contarPorEstado(LoanStatus.PENDIENTE);
        assertTrue(conteoPendientes > 0);
    }

    @Test
    @DisplayName("Debe actualizar estados dentro del umbral de latencia de 5 segundos")
    void debeAtualizarEstadoDentroDelUmbralDeLatencia() {
        LoanApplication solicitud = new LoanApplication("CANDIDATO_006", 25000.00);
        solicitud.setCreatedAt(LocalDateTime.now());
        solicitud.setUpdatedAt(LocalDateTime.now());

        LoanApplication creada = loanStatusService.crearPrestamo(solicitud);

        long tiempoInicio = System.currentTimeMillis();
        loanStatusService.actualizarEstado(creada.getId(), LoanStatus.EN_PROCESO);
        long tiempoFin = System.currentTimeMillis();

        long latencia = tiempoFin - tiempoInicio;
        assertTrue(latencia < 5000, "La latencia excede el umbral de 5 segundos: " + latencia + "ms");
    }

    @Test
    @DisplayName("Debe manejar el flujo completo desde RECHAZADO como estado final")
    void debeManejarRechazoComoEstadoFinal() {
        LoanApplication solicitud = new LoanApplication("CANDIDATO_007", 150000.00);
        solicitud.setCreatedAt(LocalDateTime.now());
        solicitud.setUpdatedAt(LocalDateTime.now());

        LoanApplication creada = loanStatusService.crearPrestamo(solicitud);
        loanStatusService.actualizarEstado(creada.getId(), LoanStatus.EN_PROCESO);
        LoanApplication rechazada = loanStatusService.actualizarEstado(creada.getId(), LoanStatus.RECHAZADO);

        assertEquals(LoanStatus.RECHAZADO, rechazada.getStatus());
        assertTrue(rechazada.getStatus().isFinalState());

        Optional<LoanApplication> consultada = loanStatusService.obtenerPorId(creada.getId());
        assertTrue(consultada.isPresent());
        assertEquals(LoanStatus.RECHAZADO, consultada.get().getStatus());
    }
}

// === ARCHIVO: README.md ===
# Sistema de Gestión de Estados de Solicitudes de Préstamo

## Descripción del Proyecto

Este proyecto implementa un sistema de gestión de estados para solicitudes de préstamo en una plataforma de banca digital. El sistema gestiona las transiciones de estado a través del pipeline de integración continua, asegurando consistencia entre el originador de créditos, el motor de evaluación de riesgo y el core bancario.

### Estados del Préstamo

El sistema soporta los siguientes estados para las solicitudes de préstamo:

- **PENDIENTE**: Estado inicial cuando se recibe una solicitud
- **EN_PROCESO**: La solicitud está siendo evaluada
- **APROBADO**: La solicitud fue aprobada
- **RECHAZADO**: La solicitud fue rechazada

### Reglas de Transición

Las transiciones de estado siguen una máquina de estados finitos:

- PENDIENTE → EN_PROCESO
- EN_PROCESO → APROBADO
- EN_PROCESO → RECHAZADO
- APROBADO y RECHAZADO son estados finales

## Estructura del Proyecto

```
loanstatus/
├── src/
│   ├── main/
│   │   ├── java/com/pragma/loanstatus/
│   │   │   ├── LoanStatusApplication.java       # Punto de entrada
│   │   │   ├── controller/
│   │   │   │   └── LoanStatusController.java     # Endpoints REST
│   │   │   ├── service/
│   │   │   │   └── LoanStatusService.java        # Lógica de negocio
│   │   │   ├── model/
│   │   │   │   ├── LoanApplication.java          # Entidad principal
│   │   │   │   └── LoanStatus.java               # Enum de estados
│   │   │   ├── repository/
│   │   │   │   └── LoanStatusRepository.java     # Capa de acceso a datos
│   │   │   ├── exception/
│   │   │   │   ├── InvalidStateTransitionException.java
│   │   │   │   ├── StateConsistencyException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   └── config/
│   │   │       └── SwaggerConfig.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/pragma/loanstatus/
│           ├── service/LoanStatusServiceTest.java
│           ├── controller/LoanStatusControllerTest.java
│           └── LoanStatusIntegrationTest.java
└── pom.xml
```

## Requisitos Previos

- **Java Development Kit (JDK)**: Versión 21 o superior
- **Apache Maven**: Versión 3.8 o superior
- **IDE recomendado**: IntelliJ IDEA, VS Code con extensión de Java, o Eclipse

## Configuración del Entorno

### Instalación de JDK 21

En sistemas Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install openjdk-21-jdk
```

En macOS con Homebrew:
```bash
brew install openjdk@21
```

En Windows: Descargar desde [Adoptium](https://adoptium.net/)

### Verificar Instalación

```bash
java -version
mvn -version
```

## Ejecución del Proyecto

### Compilación

```bash
mvn clean compile
```

### Ejecución Local

```bash
mvn spring-boot:run
```

La aplicación arrancará en `http://localhost:8080`

### Endpoints Disponibles

Una vez iniciada la aplicación, la documentación Swagger está disponible en:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

#### Endpoints REST

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/loan-applications` | Crear nueva solicitud de préstamo |
| GET | `/api/loan-applications/{id}` | Obtener solicitud por ID |
| GET | `/api/loan-applications` | Listar todas las solicitudes |
| PUT | `/api/loan-applications/{id}/status` | Actualizar estado de solicitud |
| GET | `/api/loan-applications/status/{status}` | Buscar por estado |
| GET | `/api/loan-applications/applicant/{applicantId}` | Buscar por applicant |

## Ejecución de Pruebas

### Ejecutar Todas las Pruebas

```bash
mvn test
```

### Ejecutar Pruebas Unitarias

```bash
mvn test -Dtest=LoanStatusServiceTest
```

### Ejecutar Pruebas de Controlador

```bash
mvn test -Dtest=LoanStatusControllerTest
```

### Ejecutar Pruebas de Integración

```bash
mvn test -Dtest=LoanStatusIntegrationTest
```

### Ver Informe de Cobertura

```bash
mvn test jacoco:report
```

El informe se genera en: `target/site/jacoco/index.html`

### Ejecutar con Verbose

```bash
mvn test -X
```

## Pipeline de Integración Continua

### Configuración de CI

El proyecto está configurado para funcionar con cualquier sistema de CI que soporte Maven. A continuación se muestra la configuración básica.

#### GitHub Actions

Crear archivo `.github/workflows/ci.yml`:

```yaml
name: CI Pipeline

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v4
      
      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'
          cache: 'maven'
      
      - name: Build with Maven
        run: mvn clean compile
      
      - name: Run tests
        run: mvn test
      
      - name: Package
        run: mvn package -DskipTests
      
      - name: Upload artifacts
        uses: actions/upload-artifact@v4
        with:
          name: loanstatus-artifacts
          path: target/*.jar
```

#### GitLab CI

Crear archivo `.gitlab-ci.yml`:

```yaml
stages:
  - build
  - test
  - package

variables:
  MAVEN_OPTS: "-Dmaven.repo.local=.m2/repository"

build:
  stage: build
  image: maven:3.9-eclipse-temurin-21
  script:
    - mvn clean compile
  artifacts:
    paths:
      - target/classes/

test:
  stage: test
  image: maven:3.9-eclipse-temurin-21
  script:
    - mvn test
  artifacts:
    reports:
      junit: target/surefire-reports/*.xml

package:
  stage: package
  image: maven:3.9-eclipse-temurin-21
  script:
    - mvn package -DskipTests
  artifacts:
    paths:
      - target/*.jar
```

#### Jenkinsfile

```groovy
pipeline {
    agent any
    
    tools {
        jdk 'JDK21'
        maven 'Maven3.9'
    }
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
    }
}
```

### Verificación de Calidad de Código

El proyecto incluye configuración para análisis de código estático. Para ejecutar:

```bash
mvn checkstyle:check
```

## Configuración de Base de Datos

El proyecto utiliza H2 en memoria para desarrollo y pruebas. La configuración está en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:loandb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

Para acceder a la consola H2: `http://localhost:8080/h2-console`

## Tecnologías Utilizadas

- **Framework**: Spring Boot 3.3.0
- **Lenguaje**: Java 21
- **Build Tool**: Apache Maven
- **Base de Datos**: H2 (desarrollo) / Compatible con PostgreSQL, MySQL
- **Documentación API**: SpringDoc OpenAPI 2.5.0
- **Testing**: JUnit 5, Mockito
- **Patrón**: Capas estándar (Controller, Service, Repository)

## Licencia

Este proyecto es parte del programa de formación de Pragma.
```
