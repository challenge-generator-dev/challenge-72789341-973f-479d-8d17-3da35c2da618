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