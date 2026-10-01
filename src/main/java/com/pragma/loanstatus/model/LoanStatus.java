package com.pragma.loanstatus.model;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public enum LoanStatus {
    PENDING {
        @Override
        public Set<LoanStatus> getAllowedTransitions() {
            return new HashSet<>(Arrays.asList(PROCESSING, REJECTED));
        }
    },
    PROCESSING {
        @Override
        public Set<LoanStatus> getAllowedTransitions() {
            return new HashSet<>(Arrays.asList(APPROVED, REJECTED));
        }
    },
    APPROVED {
        @Override
        public Set<LoanStatus> getAllowedTransitions() {
            return new HashSet<>();
        }
    },
    REJECTED {
        @Override
        public Set<LoanStatus> getAllowedTransitions() {
            return new HashSet<>();
        }
    };

    public abstract Set<LoanStatus> getAllowedTransitions();

    public boolean isFinalState() {
        return this == APPROVED || this == REJECTED;
    }

    public static boolean isValidTransition(LoanStatus currentStatus, LoanStatus newStatus) {
        if (currentStatus == null || newStatus == null) {
            return false;
        }
        return currentStatus.getAllowedTransitions().contains(newStatus);
    }
}
"