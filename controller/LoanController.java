package com.dw.ERD.controller;

import com.dw.ERD.enums.Status;
import com.dw.ERD.model.Loan;
import com.dw.ERD.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    LoanService loanService;

    @PostMapping("/post/loan")
    public ResponseEntity<String> createLoan(@RequestBody Loan loan) {
        loanService.createLoan(loan);
        return ResponseEntity.ok("도서가 대출되었습니다");
    }

    @GetMapping("/get/loans")
    public ResponseEntity<List<Loan>> getLoanList (@RequestParam(value = "memberId", required = false) Long memberId,
                                                   @RequestParam(value = "status", required = false) Status status) {
        List<Loan> loanList = loanService.getLoanList(memberId, status);
        return ResponseEntity.ok(loanList);
    }

    @PutMapping("/put/loans/{loanId}/return")
    public ResponseEntity<String> returnLoan(@PathVariable("loanId") long loanId) {
        loanService.returnLoan(loanId);
        return ResponseEntity.ok("도서가 반납되었습니다.");
    }
}
