package bookworm.bookworm.service;

import bookworm.bookworm.DTO.genres.GenreRegistrationDTO;
import bookworm.bookworm.model.Book;
import bookworm.bookworm.model.Genres;
import bookworm.bookworm.repository.GenresRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenresService {

    @Autowired
    private GenresRepository genresRepository;

    @Transactional
    public ResponseEntity<String> registerNewGenre(GenreRegistrationDTO genreRegistrationDTO) {
        if (genresRepository.existsByType(genreRegistrationDTO.getType())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Type already in use");
        }

        Genres newGenre = mapDtoToGenre(genreRegistrationDTO);
        genresRepository.save(newGenre);
        return ResponseEntity.status(HttpStatus.CREATED).body("Genre created successfully");
    }

    @Transactional
    public ResponseEntity<List<Genres>> listAllGenres() {
        List<Genres> allGenres = genresRepository.findAll();
        return ResponseEntity.ok(allGenres);
    }

    @Transactional
    public ResponseEntity<Genres> findByType(String type) {
        Optional<Genres> genre = Optional.ofNullable(genresRepository.findByType(type));
        return genre.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Transactional
    public ResponseEntity<List<Book>> findBookByType(String type) {
        List<Book> books = genresRepository.findBooksByGenreType(type);
        return books.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).build() :
                ResponseEntity.ok(books);
    }

    @Transactional
    public ResponseEntity<String> deleteGenre(Integer id) {
        if (genresRepository.existsById(id)) {
            genresRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Genre deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genre not found");
        }
    }


    private Genres mapDtoToGenre(GenreRegistrationDTO genreRegistrationDTO) {
        Genres newGenre = new Genres();
        newGenre.setType(genreRegistrationDTO.getType());
        return newGenre;
    }
}
