package com.movieflix.movie.service;

import com.movieflix.category.entity.Category;
import com.movieflix.category.service.CategoryService;
import com.movieflix.movie.controller.dtos.response.MovieResponse;
import com.movieflix.movie.entity.Movie;
import com.movieflix.movie.repository.MovieRepository;
import com.movieflix.streaming.entity.Streaming;
import com.movieflix.streaming.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;
    
    public Movie save(Movie movie){
       movie.setCategories(this.findCategories(movie.getCategories()));
       movie.setStreamings(this.findStreamings(movie.getStreamings()));
       return movieRepository.save(movie);
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }
    
    public Optional<Movie> findById (Long id){
        return movieRepository.findById(id);
    } 

    private List<Category> findCategories(List<Category> categories){
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach( category ->
                categoryService.categoryID(
                        category.getId()).ifPresent(categoriesFound::add));
        return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings){
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming ->
                streamingService.findById(streaming.getId()).
                        ifPresent(streaming1 -> streamingsFound.add(streaming1)));
        return streamingsFound;
    }

    public Optional<Movie> update(Long movieId, Movie updateMovie){
        Optional<Movie> optMovie = movieRepository.findById(movieId);

        if(optMovie.isPresent()){

            List<Category> optcategories = this.findCategories(updateMovie.getCategories());
            List<Streaming> optStreaming = this.findStreamings((updateMovie.getStreamings()));

            Movie movie = optMovie.get();
            movie.setTitle(updateMovie.getTitle());
            movie.setDescription(updateMovie.getDescription());
            movie.setReleaseDate(updateMovie.getReleaseDate());
            movie.setRating(updateMovie.getRating());

            movie.getCategories().clear();
            movie.setCategories(optcategories);
            movie.getStreamings().clear();
            movie.setStreamings(optStreaming);

            movieRepository.save(movie);
            return  Optional.of(movie);
        }
        return Optional.empty();
    }


    public List<Movie> findByCategory(Long categoryId){
        return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public List<Movie> findByStreaming(Long streamingId){
        return movieRepository.findMovieByStreamings(List.of(Streaming.builder().id(streamingId).build()));
    }

    public void deleteById(Long id){
        movieRepository.deleteById(id);
    }

}
