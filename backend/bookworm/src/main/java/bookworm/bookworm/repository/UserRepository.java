package bookworm.bookworm.repository;

import bookworm.bookworm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    Optional<Object> findByUsername(String username);
}
