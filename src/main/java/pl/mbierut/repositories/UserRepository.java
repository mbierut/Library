package pl.mbierut.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mbierut.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(long id);
}
