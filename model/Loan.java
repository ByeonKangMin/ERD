package com.dw.ERD.model;

import com.dw.ERD.dto.LoanDto;
import com.dw.ERD.enums.Status;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Loan {
    private long loanId;            //대출Id
    private Member memberId;        //회원Id
    private Book bookId;            //도서Id
    private LocalDate loanDate;     //대출일
    private LocalDate dueDate;      //반납예정일
    private LocalDate returnDate;   //실제반납일
    private Status status;          //상태(대여, 반납, 연체)
    private double fineAmount;      //연체료
    private LocalDateTime createdAt;    //대출신청일시

    public LoanDto toDto() {
        return new LoanDto(
                this.loanId,
                this.memberId,
                this.bookId,
                this.loanDate,
                this.dueDate

        );
    }

}
