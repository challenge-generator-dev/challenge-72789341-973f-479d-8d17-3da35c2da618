# Implementación de prueba de progreso de estado en un pipeline de integración continua

En el contexto de una plataforma de banca digital, se requiere asegurar que el estado de una solicitud de préstamo se actualice correctamente a lo largo del pipeline de integración continua (CI). Los estados clave son 'pendiente', 'en proceso', 'aprobado' y'rechazado'. La prueba debe validar la transición de estados desde la recepción de la solicitud hasta su resolución final, asegurando que cada estado intermedio se maneje adecuadamente y que no haya estados inconsistentes. Los actores involucrados son el 'originador de créditos', el'motor de evaluación de riesgo' y el 'core bancario'. Las propiedades operativas incluyen la consistencia del estado entre el 'originador de créditos' y el 'core bancario', con un umbral de latencia máximo de 5 segundos para la actualización de estados.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Status Progression Test |
| **Nivel** | junior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Definición de criterios de aceptación para la prueba

**Objetivo:** Establecer los criterios de aceptación que la prueba de progreso de estado debe cumplir.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar las transiciones de estado válidas y las condiciones que deben cumplirse en cada transición.
- Definir los casos de prueba para cada transición de estado, incluyendo los escenarios de éxito y fallo.

**Entregable:** Documento con los criterios de aceptación detallados para la prueba de progreso de estado.

<details>
<summary>Pistas de conocimiento</summary>

- Considerar los diferentes actores y sus roles en el proceso de solicitud de préstamo.
- Evaluar los posibles estados inconsistentes y cómo manejarlos.

</details>

### Fase 2: Implementación de la prueba de progreso de estado

**Objetivo:** Desarrollar la prueba que valida las transiciones de estado a lo largo del pipeline de CI.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Crear la infraestructura necesaria para ejecutar la prueba en el pipeline de CI.
- Escribir los casos de prueba que cubren todas las transiciones de estado definidas en la fase anterior.
- Asegurar que la prueba verifica la consistencia del estado entre el 'originador de créditos' y el 'core bancario'.

**Entregable:** Prueba implementada y configurada en el pipeline de CI que valida las transiciones de estado.

<details>
<summary>Pistas de conocimiento</summary>

- Utilizar herramientas de CI para ejecutar y monitorear la prueba.
- Implementar mecanismos para manejar y reportar los estados inconsistentes.

</details>

### Fase 3: Validación y optimización de la prueba

**Objetivo:** Validar el funcionamiento de la prueba y optimizar su rendimiento.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Ejecutar la prueba en diferentes escenarios para asegurar su robustez y cobertura.
- Identificar y corregir cualquier fallo o inconsistencia en la prueba.
- Optimizar la prueba para reducir la latencia y mejorar la eficiencia.

**Entregable:** Prueba validada y optimizada que se ejecuta de manera eficiente en el pipeline de CI.

<details>
<summary>Pistas de conocimiento</summary>

- Realizar pruebas de carga para evaluar el rendimiento de la prueba.
- Aplicar técnicas de optimización para reducir la latencia en la actualización de estados.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es una prueba de progreso de estado y por qué es importante en el contexto de un pipeline de CI?
- **paraQueSirve**: ¿Para qué sirve validar las transiciones de estado en una solicitud de préstamo?
- **comoSeUsa**: ¿Cómo se implementa y configura una prueba de progreso de estado en un pipeline de CI?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar una prueba de progreso de estado y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la implementación de una prueba de progreso de estado en términos de consistencia y latencia?

## Criterios de Evaluacion

- Definición clara de criterios de aceptación para la prueba de progreso de estado.
- Implementación de la prueba que valida las transiciones de estado en el pipeline de CI.
- Validación y optimización de la prueba para asegurar su robustez y eficiencia.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
