package nl.novi.vinylWebshop.controllers;

import nl.novi.vinylWebshop.entities.Genre;
import nl.novi.vinylWebshop.services.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/all")
    private ResponseEntity<List<Genre>> getGenres() {
        return ResponseEntity.ok(genreService.findAllGenres());
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<List<Genre>> createGenre(@RequestBody Genre genre) {
        Genre createdGenre = this.genreService.createGenre(genre);
        return ResponseEntity.ok().body(genreService.findAllGenres());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Genre> findGenreById(@PathVariable Long id) {
        Genre genre = genreService.findGenreById(id);
        return ResponseEntity.ok().body(genre);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Genre> updateUser(@PathVariable Long id, @RequestBody Genre genreInput) {
        Genre genre = genreService.findGenreById(id);
        Genre updatedGenre = genreService.updateGenre(id, genreInput);
        return ResponseEntity.ok().body(genre);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private ResponseEntity<String> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    return ResponseEntity.ok().body("Genre with id " + id + " has been deleted");
    }

}

