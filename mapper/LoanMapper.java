package com.dw.ERD.mapper;

import com.dw.ERD.enums.Status;
import com.dw.ERD.model.Loan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoanMapper {
    void insertLoan(Loan loan);
    List<Loan> getLoanList (@Param("memberId") Long memberId, @Param("status") Status status);
    Loan selectLoanById(@Param("loanId") long loanId);
    void updateLoan(Loan loan);
}
