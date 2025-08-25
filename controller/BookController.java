package com.dw.ERD.controller;

import com.dw.ERD.dto.BookAdminDto;
import com.dw.ERD.dto.BookDto;
import com.dw.ERD.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/post/books")
    public ResponseEntity<BookDto> registerBookByAdmin (@RequestBody BookAdminDto bookAdminDto) {
        BookDto bookDto = bookService.registerBookByAdmin(bookAdminDto);
        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
    }

    @GetMapping("/get/books")
    public ResponseEntity<List<BookDto>> getBooks(@RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        List<BookDto> list = bookService.getBooks(page, size);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/books/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable long bookId) {
        BookDto bookDto = bookService.getBookById(bookId);
        return ResponseEntity.ok(bookDto);
    }

    @PutMapping("/put/books/{bookId}")
    public ResponseEntity<BookAdminDto> modifyBookById(@PathVariable long bookId,
                                                        @RequestBody BookAdminDto bookAdminDto) {
        BookAdminDto bookDto = bookService.modifyBookById(bookId, bookAdminDto);
        return ResponseEntity.ok(bookDto);
    }

    @DeleteMapping("delete/books/{bookId}")
    public ResponseEntity<Void> deleteBookById(@PathVariable long bookId) {
        bookService.deleteBookById(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
