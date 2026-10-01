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