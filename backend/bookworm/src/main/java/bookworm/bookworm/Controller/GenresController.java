package bookworm.bookworm.Controller;

import bookworm.bookworm.DTO.genres.GenreRegistrationDTO;
import bookworm.bookworm.model.Book;
import bookworm.bookworm.model.Genres;
import bookworm.bookworm.repository.GenresRepository;
import bookworm.bookworm.service.GenresService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/genres")
public class GenresController {

    @Autowired
    private GenresRepository genresRepository;

    @Autowired
    private GenresService genresService;

    @PostMapping("/new")
    public ResponseEntity<?> newGenre(@RequestBody @Valid GenreRegistrationDTO genreRegistrationDTO){
       return genresService.registerNewGenre(genreRegistrationDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Genres>> listAllGenres(){
        return genresService.listAllGenres();
    }
    @GetMapping("/bookByType")
    public ResponseEntity<List<Book>> findBookByType(@RequestParam String type) {
        return genresService.findBookByType(type);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable Integer id) {
        return genresService.deleteGenre(id);
    }
}
