package bookworm.bookworm.repository;

import bookworm.bookworm.model.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresRepository extends JpaRepository<Genres, Integer> {
}
