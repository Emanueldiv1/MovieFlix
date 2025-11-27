package com.movieflix.movie.controller;

import com.movieflix.movie.controller.dtos.request.MoviesRequest;
import com.movieflix.movie.controller.dtos.response.MovieResponse;
import com.movieflix.movie.entity.Movie;
import com.movieflix.movie.mapper.MovieMapper;
import com.movieflix.movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
@Tag(name = "Movie", description =" Responsavel pelo gerenciamento dos Filmes")
public class MovieController {

   private final MovieService movieService;

   @Operation(summary = "Salvar Filme", description = "Responsavel por salvar um novo filme")
   @ApiResponse(responseCode = "201", description = "Filme salvo com sucesso",
           content = @Content(schema = @Schema(implementation = MovieResponse.class)))
   @PostMapping("/save")
   public ResponseEntity<MovieResponse> save(@Valid @RequestBody MoviesRequest moviesR){
      Movie movieSave =  movieService.save(MovieMapper.toMovie(moviesR));
      return ResponseEntity.ok(MovieMapper.toMovieResponse(movieSave));
   }

   @Operation(summary = "Buscar filme por ID ", description = "Responsavel por um filme pelo ID cadastrado ")
   @ApiResponse(responseCode = "201", description = "Filme encontrado com sucesso",
           content = @Content(schema = @Schema(implementation = MovieResponse.class)))
   @GetMapping()
   public ResponseEntity<List<MovieResponse>> findAll(){
      return ResponseEntity.ok(movieService.findAll()
              .stream()
              .map(movie -> MovieMapper.toMovieResponse(movie))
              .toList());
   }

   @GetMapping("{id}")
   public ResponseEntity<MovieResponse> findById(@PathVariable Long id){
      return movieService.findById(id)
              .map(movie -> ResponseEntity.ok(
                      MovieMapper.toMovieResponse(movie)
              ))
              .orElse(ResponseEntity.notFound().build());
   }

   @PutMapping("/{id}")
   public ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MoviesRequest movieRequest){
     return movieService.update(id, MovieMapper.toMovie(movieRequest))
             .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
             .orElse(ResponseEntity.notFound().build());
   }


   @GetMapping("/search/by-category")
   public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category){
      return ResponseEntity.ok(movieService.findByCategory(category).stream()
              .map(movie -> MovieMapper.toMovieResponse(movie))
              .toList());
   }

   @GetMapping("/search/by-streaming")
   public ResponseEntity<List<MovieResponse>> findByStreaming(@RequestParam Long streaming){
      return ResponseEntity.ok(movieService.findByStreaming(streaming).stream()
              .map(movie -> MovieMapper.toMovieResponse(movie))
              .toList());
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteById(@PathVariable Long id){
      Optional<Movie> optMovie = movieService.findById(id);
      if (optMovie.isPresent()){
         movieService.deleteById(id);
         return ResponseEntity.noContent().build();
      }
      return  ResponseEntity.notFound().build();

   }
}
