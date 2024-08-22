package org.fasttrack.ShelfSpace;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.fasttrack.ShelfSpace.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookReader bookReader;
    private final List<Book> books = new ArrayList<>();

    @PostConstruct
    public void init() {
        System.out.println("Post constructor on Book Service");
        books.addAll(bookReader.readBooks());
        System.out.println("Service initialized with " + books);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<String> getAllBookTitles() {
        return books.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    public List<Book> getBooksByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooksByGenre(String genre) {
        return books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .toList();
    }

    public List<Book> getAllEntriesByGenre(String genre) {
        return books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .toList();
    }

    public long countBooksByGenre(String genre) {
        return books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .count();
    }

    public List<String> listAllGenres() {
        return books.stream()
                .map(Book::getGenre)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public long countBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .count();
    }

    public List<String> listAllAuthors() {
        return books.stream()
                .map(Book::getAuthor)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByState(String state) {
        return books.stream()
                .filter(book -> book.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    public Optional<Book> getBookById(long id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
    }

    public List<Book> getBooksByPartialTitle(String partialTitle) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(partialTitle.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Book deleteBookEntry(long id) {
        Book bookToBeDeleted = getBookById(id).orElseThrow(() -> new EntityNotFoundException("Can't delete a missing book", id));
        books.remove(bookToBeDeleted);
        return bookToBeDeleted;
    }

    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    public Book updateBook(Book book) {
        deleteBookEntry(book.getId());
        addBook(book);
        return book;
    }

}
