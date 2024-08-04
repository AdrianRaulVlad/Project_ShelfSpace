package org.fasttrack.ShelfSpace;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books") // GET http://host:port/books?genre=Fiction
    public List<Book> getAllBooks(@RequestParam(required = false) String genre) {
        if (genre != null) {
            return bookService.getAllBooksByGenre(genre);
        } else {
            return bookService.getAllBooks();
        }
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookService.getBookById(id).orElseThrow(RuntimeException::new);
    }
}
