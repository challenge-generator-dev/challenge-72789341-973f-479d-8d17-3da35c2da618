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