package pl.mbierut.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mbierut.enums.BookStatus;
import pl.mbierut.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByStatus(BookStatus status);
    List<Book> findBooksByTitleTitleContainingAndStatus(String title, BookStatus status);
}
