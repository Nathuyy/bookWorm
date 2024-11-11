package bookworm.bookworm.repository;

import bookworm.bookworm.model.Book;
import bookworm.bookworm.model.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByGenre(Genres genre);

}

