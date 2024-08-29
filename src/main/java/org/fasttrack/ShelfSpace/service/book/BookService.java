package org.fasttrack.ShelfSpace.service.book;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.fasttrack.ShelfSpace.model.book.Book;
import org.fasttrack.ShelfSpace.model.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookReader bookReader;
    private final BookRepository bookRepository;

    @PostConstruct
    public void init() {
        System.out.println("Post constructor on Book Service");
        bookRepository.saveAll(bookReader.readBooks());
        System.out.println("Service initialized with " + bookRepository.findAll());
    }

    public List<Book> getAllBooks() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).toList();
    }

    public List<Book> getAllBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public Book getBookById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't find the book", id));
    }

    public Book deleteBookEntry(long id) {
        Book bookToBeDeleted = getBookById(id);
        bookRepository.deleteById(id);
        return bookToBeDeleted;
    }

    public Book addBook(Book book) {
        book.setId(null);
        bookRepository.save(book);
        return book;
    }

    public Book updateBook(Book book) {
        bookRepository.save(book);
        return book;
    }

}
