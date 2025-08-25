package com.dw.ERD.dto;

import com.dw.ERD.model.Book;
import com.dw.ERD.model.Member;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class LoanDto {
    private long loanId;
    private Member memberId;
    private Book bookId;
    private LocalDate loanDate;
    private LocalDate dueDate;
}
