package pl.mbierut.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mbierut.models.Title;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title, Long> {
    List<Title> findTitlesByTitleContainingAndAuthorContaining(String title, String author);
}
