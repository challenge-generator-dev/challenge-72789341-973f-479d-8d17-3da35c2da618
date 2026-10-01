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