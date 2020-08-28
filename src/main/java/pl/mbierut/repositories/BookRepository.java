package pl.mbierut.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mbierut.enums.BookStatus;
import pl.mbierut.models.Title;
import pl.mbierut.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByTitleAndStatus(Title title, BookStatus status);
    List<Book> findBooksByTitle(Title title);
    Book getBookById(long id);
}
