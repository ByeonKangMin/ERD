package com.dw.ERD.service;

import com.dw.ERD.dto.BookAdminDto;
import com.dw.ERD.dto.BookDto;
import com.dw.ERD.mapper.BookMapper;
import com.dw.ERD.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    BookMapper bookMapper;

    @Transactional
    public BookDto registerBookByAdmin (BookAdminDto bookAdminDto) {
        if (bookMapper.countByIsbn(bookAdminDto.getIsbn()) >0) {
            throw new IllegalArgumentException("이미 등록된 도서입니다");
        }
        Book book = new Book();
        book.setIsbn(bookAdminDto.getIsbn());
        book.setTitle(bookAdminDto.getTitle());
        book.setAuthor(bookAdminDto.getAuthor());
        book.setPublisher(bookAdminDto.getPublisher());
        book.setPublicationYear(bookAdminDto.getPublicationYear());
        book.setCategory(bookAdminDto.getCategory());
        book.setTotalQuantity(bookAdminDto.getTotalQuantity());
        book.setAvailableQuantity(bookAdminDto.getTotalQuantity());
        book.setLocation(bookAdminDto.getLocation());
        book.setCreatedAt(LocalDateTime.now());

        bookMapper.registerBookByAdmin(book);

        BookDto dto = new BookDto();
        dto.setBookId(book.getBookId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPublisher(book.getPublisher());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setCategory(book.getCategory());
        dto.setAvailableQuantity(book.getAvailableQuantity());
        dto.setLocation(book.getLocation());

        return dto;
    }

    @Transactional(readOnly = true)
    public List<BookDto> getBooks(int page, int size) {
        int offset = (page - 1) * size; // page 1 기준
        List<Book> books = bookMapper.getBooks(offset, size);

        return books.stream()
                .map(Book::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookDto getBookById(long bookId) {
        Book book = bookMapper.getBookById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("존재하지 않는 도서입니다: " + bookId);
        }
        return book.toDto();
    }

    @Transactional
    public BookAdminDto modifyBookById(long bookId, BookAdminDto bookAdminDto) {
        Book book = bookMapper.getBookById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("해당 도서가 없습니다" + bookId);
        }

        book.setIsbn(bookAdminDto.getIsbn());
        book.setTitle(bookAdminDto.getTitle());
        book.setAuthor(bookAdminDto.getAuthor());
        book.setPublisher(bookAdminDto.getPublisher());
        book.setPublicationYear(bookAdminDto.getPublicationYear());
        book.setCategory(bookAdminDto.getCategory());
        book.setTotalQuantity(bookAdminDto.getTotalQuantity());
        book.setAvailableQuantity(bookAdminDto.getTotalQuantity());
        book.setLocation(bookAdminDto.getLocation());

        bookMapper.modifyBookById(book);
        return book.toDTO();
    }

    @Transactional
    public void deleteBookById(long bookId) {
        Book book = bookMapper.getBookById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("존재하지 않는 도서입니다: " + bookId);
        }
        bookMapper.deleteBookById(bookId);
    }
}
