package bookworm.bookworm.repository;

import bookworm.bookworm.model.Book;
import bookworm.bookworm.model.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenresRepository extends JpaRepository<Genres, Integer> {


    Genres findByType(String type);

    boolean existsByType(String type);

    @Query("SELECT b FROM Book b JOIN b.genre g WHERE LOWER(g.type) LIKE LOWER(CONCAT('%', :type, '%'))")
    List<Book> findBooksByGenreType(@Param("type") String type);}
