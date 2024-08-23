package org.fasttrack.ShelfSpace.service.book;

import org.fasttrack.ShelfSpace.model.book.Book;
import org.fasttrack.ShelfSpace.model.person.Person;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository
public class BookReader {

    public List<Book> readBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/books.txt"));
            long id = 1;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                books.add(stringToBook(line, id++));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    private Book stringToBook(String text, long id) {
        String[] tokens = text.split("\\|");
        Book.BookBuilder bookBuilder = Book.builder()
                .id(id)
                .title(tokens[0])
                .author(new Person(tokens[1]))
                .genre(tokens[2])
                .releaseDate(LocalDate.parse(tokens[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .description(tokens[4])
                .state(tokens[5]);
        return bookBuilder.build();
    }

}
