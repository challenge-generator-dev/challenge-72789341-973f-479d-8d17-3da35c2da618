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