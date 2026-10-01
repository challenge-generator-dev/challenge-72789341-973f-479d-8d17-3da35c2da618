package com.pragma.loanstatus.repository;

import com.pragma.loanstatus.model.LoanApplication;
import com.pragma.loanstatus.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanStatusRepository extends JpaRepository<LoanApplication, Long> {

    List<LoanApplication> findByStatus(LoanStatus status);

    List<LoanApplication> findByApplicantId(String applicantId);

    Optional<LoanApplication> findByRiskAssessmentId(String riskAssessmentId);

    Optional<LoanApplication> findByCoreBankingId(String coreBankingId);

    List<LoanApplication> findByStatusIn(List<LoanStatus> statuses);

    List<LoanApplication> findByCreatedAtBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);

    long countByStatus(LoanStatus status);
}