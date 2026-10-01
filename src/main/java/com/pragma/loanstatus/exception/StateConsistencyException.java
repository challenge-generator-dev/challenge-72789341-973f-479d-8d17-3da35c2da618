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