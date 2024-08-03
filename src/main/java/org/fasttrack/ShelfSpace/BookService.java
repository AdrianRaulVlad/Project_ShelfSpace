package org.fasttrack.ShelfSpace;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
