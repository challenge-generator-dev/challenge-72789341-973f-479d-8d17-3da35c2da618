# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de prueba de progreso de estado en un pipeline de integración continua**.

| | |
|---|---|
| Tema | Status Progression Test |
| Nivel | junior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.3 |
| Patron arquitectonico | capas estándar |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.3.0
- org.springframework.boot:spring-boot-starter-data-jpa 3.3.0
- com.h2database:h2 2.2.224
- org.springframework.boot:spring-boot-starter-test 3.3.0
- org.mockito:mockito-core 5.3.1
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.5.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Definición de criterios de aceptación para la prueba**: Documento con los criterios de aceptación detallados para la prueba de progreso de estado.
- **Fase 2 — Implementación de la prueba de progreso de estado**: Prueba implementada y configurada en el pipeline de CI que valida las transiciones de estado.
- **Fase 3 — Validación y optimización de la prueba**: Prueba validada y optimizada que se ejecuta de manera eficiente en el pipeline de CI.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (34)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/loanstatus/service/LoanStatusService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanstatus/config/SwaggerConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanstatus/controller/LoanStatusController.java` — `LoanStatusRepository.findById`
      Se invoca `findById` sobre `LoanStatusRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanstatus/controller/LoanStatusController.java` — `LoanApplication.map`
      Se invoca `map` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanstatus/controller/LoanStatusController.java` — `LoanStatusRepository.save`
      Se invoca `save` sobre `LoanStatusRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanstatus/service/LoanStatusService.java` — `LoanApplication.setCreatedAt`
      Se invoca `setCreatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanstatus/service/LoanStatusService.java` — `LoanApplication.setUpdatedAt`
      Se invoca `setUpdatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanstatus/service/LoanStatusService.java` — `LoanStatusRepository.save`
      Se invoca `save` sobre `LoanStatusRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanstatus/exception/InvalidStateTransitionException.java` — `LoanStatus.name`
      Se invoca `name` sobre `LoanStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanApplication.setCreatedAt`
      Se invoca `setCreatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanApplication.setUpdatedAt`
      Se invoca `setUpdatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanStatusRepository.findById`
      Se invoca `findById` sobre `LoanStatusRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanStatusRepository.save`
      Se invoca `save` sobre `LoanStatusRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanStatusService.actualizarEstado`
      Se invoca `actualizarEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanStatusService.verificarConsistencia`
      Se invoca `verificarConsistencia` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanStatusService.obtenerPorEstado`
      Se invoca `obtenerPorEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java` — `LoanStatusService.contarPorEstado`
      Se invoca `contarPorEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanApplication.setCreatedAt`
      Se invoca `setCreatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanApplication.setUpdatedAt`
      Se invoca `setUpdatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanStatusService.obtenerPorId`
      Se invoca `obtenerPorId` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanStatusService.actualizarEstado`
      Se invoca `actualizarEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanStatusService.obtenerPorEstado`
      Se invoca `obtenerPorEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanStatusService.buscarPorApplicantId`
      Se invoca `buscarPorApplicantId` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanStatusService.crearPrestamo`
      Se invoca `crearPrestamo` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java` — `LoanStatusService.verificarConsistencia`
      Se invoca `verificarConsistencia` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanApplication.setCreatedAt`
      Se invoca `setCreatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanApplication.setUpdatedAt`
      Se invoca `setUpdatedAt` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanStatusService.crearPrestamo`
      Se invoca `crearPrestamo` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanStatusService.actualizarEstado`
      Se invoca `actualizarEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanStatusService.obtenerPorId`
      Se invoca `obtenerPorId` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanStatusService.verificarConsistencia`
      Se invoca `verificarConsistencia` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanStatusService.obtenerPorEstado`
      Se invoca `obtenerPorEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java` — `LoanStatusService.contarPorEstado`
      Se invoca `contarPorEstado` sobre `LoanStatusService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (16)

- `pom.xml`
- `src/main/java/com/pragma/loanstatus/LoanStatusApplication.java`
- `src/main/java/com/pragma/loanstatus/model/LoanApplication.java`
- `src/main/java/com/pragma/loanstatus/model/LoanStatus.java`
- `src/main/java/com/pragma/loanstatus/repository/LoanStatusRepository.java`
- `src/main/resources/application.properties`
- `src/main/java/com/pragma/loanstatus/controller/LoanStatusController.java`
- `src/main/java/com/pragma/loanstatus/service/LoanStatusService.java`
- `src/main/java/com/pragma/loanstatus/exception/InvalidStateTransitionException.java`
- `src/main/java/com/pragma/loanstatus/exception/StateConsistencyException.java`
- `src/main/java/com/pragma/loanstatus/exception/GlobalExceptionHandler.java`
- `src/main/java/com/pragma/loanstatus/config/SwaggerConfig.java`
- `src/test/java/com/pragma/loanstatus/service/LoanStatusServiceTest.java`
- `src/test/java/com/pragma/loanstatus/controller/LoanStatusControllerTest.java`
- `src/test/java/com/pragma/loanstatus/LoanStatusIntegrationTest.java`
- `README.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/loanstatus`
- `src/main/java/com/pragma/loanstatus/controller`
- `src/main/java/com/pragma/loanstatus/service`
- `src/main/java/com/pragma/loanstatus/model`
- `src/main/java/com/pragma/loanstatus/repository`
- `src/main/java/com/pragma/loanstatus/exception`
- `src/main/java/com/pragma/loanstatus/config`
- `src/test/java/com/pragma/loanstatus`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **capas estándar**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Brecha que el reto ataca: Testing complete status lifecycle through pipeline

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
