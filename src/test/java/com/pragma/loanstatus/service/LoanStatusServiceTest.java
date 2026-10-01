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