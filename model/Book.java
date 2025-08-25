package com.dw.ERD.model;

import com.dw.ERD.dto.BookAdminDto;
import com.dw.ERD.dto.BookDto;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Book {
    private long bookId;       //도서Id
    private String isbn;        //도서국재번호
    private String title;       //도서명
    private String author;      //저자
    private String publisher;   //출판사
    private int publicationYear;    //출간년도
    private String category;        //키테고리
    private int totalQuantity;      //총수량
    private int availableQuantity;  //대출가능수량
    private String location;        //소장위치
    private LocalDateTime createdAt;    //등록일시


    public BookDto toDto() {
        return new BookDto(
                this.bookId,
                this.title,
                this.author,
                this.publisher,
                this.publicationYear,
                this.category,
                this.availableQuantity,
                this.location
        );
    }

    public BookAdminDto toDTO(){
        return new BookAdminDto(
                this.bookId,
                this.isbn,
                this.title,
                this.author,
                this.publisher,
                this.publicationYear,
                this.category,
                this.totalQuantity,
                this.location
                );
    }
}