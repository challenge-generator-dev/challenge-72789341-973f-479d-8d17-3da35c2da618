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