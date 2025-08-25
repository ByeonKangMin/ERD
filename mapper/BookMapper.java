package com.dw.ERD.mapper;

import com.dw.ERD.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    void registerBookByAdmin (Book book);
    int countByIsbn (String isbn);
    List<Book> getBooks(@Param("offset") int offset, @Param("size") int size);
    Book getBookById(@Param("bookId") long bookId);
    void modifyBookById(Book book);
    void deleteBookById(@Param("bookId") long bookId);
}
