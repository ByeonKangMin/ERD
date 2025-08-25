package com.dw.ERD.service;

import com.dw.ERD.enums.Status;
import com.dw.ERD.mapper.LoanMapper;
import com.dw.ERD.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    LoanMapper loanMapper;

    @Transactional
    public void createLoan (Loan loan) {
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(14));
        loan.setStatus(Status.ACTIVE);
        loan.setFineAmount(0.0);
        loan.setCreatedAt(LocalDateTime.now());

        loanMapper.insertLoan(loan);
        loan.toDto();
    }

    @Transactional(readOnly = true)
    public List<Loan> getLoanList (Long memberId, Status status ){
        return loanMapper.getLoanList(memberId, status);
    }

    @Transactional
    public void returnLoan(long loanId) {
        Loan loan = loanMapper.selectLoanById(loanId);
        if (loan == null) {
            throw new RuntimeException("해당 대출이 존재하지 않습니다.");
        }

        LocalDate today = LocalDate.now();
        loan.setReturnDate(today);

        if (today.isAfter(loan.getDueDate())) {
            loan.setStatus(Status.OVERDUE);
            long overdueDays = ChronoUnit.DAYS.between(loan.getDueDate(), today);
            loan.setFineAmount(overdueDays * 100);
        } else {
            loan.setStatus(Status.RETURNED); // 정상 반납
            loan.setFineAmount(0.0);
        }

        loanMapper.updateLoan(loan);
    }
}
