package com.movieflix.movie.repository;

import com.movieflix.category.entity.Category;
import com.movieflix.movie.entity.Movie;
import com.movieflix.streaming.entity.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMovieByCategories(List<Category> categories);
    List<Movie> findMovieByStreamings(List<Streaming> streamings);
}
