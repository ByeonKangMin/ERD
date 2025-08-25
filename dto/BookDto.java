package com.dw.ERD.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class BookDto {
    private long bookId;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String category;
    private int availableQuantity;
    private String location;

}
