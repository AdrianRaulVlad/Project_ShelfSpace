package org.fasttrack.ShelfSpace.service.book;

import org.fasttrack.ShelfSpace.model.book.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByGenre(String book);
}
