package pl.mbierut.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mbierut.models.Title;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title, Long> {
    List<Title> findTitlesByTitleAndAndAuthor(String title, String author);
    List<Title> findTitlesByTitleContaining(String title);
    List<Title> findTitlesByAuthorContaining(String author);
}
