package com.movieflix.movie.service;

import com.movieflix.category.entity.Category;
import com.movieflix.category.service.CategoryService;
import com.movieflix.movie.entity.Movie;
import com.movieflix.movie.repository.MovieRepository;
import com.movieflix.streaming.entity.Streaming;
import com.movieflix.streaming.service.StreamingService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

}
