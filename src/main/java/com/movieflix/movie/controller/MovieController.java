package com.movieflix.movie.controller;

import com.movieflix.movie.controller.dtos.request.MoviesRequest;
import com.movieflix.movie.controller.dtos.response.MovieResponse;
import com.movieflix.movie.entity.Movie;
import com.movieflix.movie.mapper.MovieMapper;
import com.movieflix.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

   private final MovieService movieService;

   @PostMapping("/save")
   public ResponseEntity<MovieResponse> save(@RequestBody MoviesRequest moviesR){
      Movie movieSave =  movieService.save(MovieMapper.toMovie(moviesR));
      return ResponseEntity.ok(MovieMapper.toMovieResponse(movieSave));
   }

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
   public ResponseEntity<MovieResponse> update(@PathVariable Long id, @RequestBody MoviesRequest movieRequest){
     return movieService.update(id, MovieMapper.toMovie(movieRequest))
             .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
             .orElse(ResponseEntity.notFound().build());
   }


}
