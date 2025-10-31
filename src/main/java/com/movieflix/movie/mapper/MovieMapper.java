package com.movieflix.movie.mapper;

import com.movieflix.category.controller.dtos.response.CategoryResponse;
import com.movieflix.category.entity.Category;
import com.movieflix.category.mapper.CategoryMapper;
import com.movieflix.movie.controller.dtos.request.MoviesRequest;
import com.movieflix.movie.controller.dtos.response.MovieResponse;
import com.movieflix.movie.entity.Movie;
import com.movieflix.streaming.controller.dtos.response.StreamingResponse;
import com.movieflix.streaming.entity.Streaming;
import com.movieflix.streaming.mapper.StreamingMapper;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper{

    public static Movie toMovie(MoviesRequest moviesRequest){

        List<Category> categories = moviesRequest.categories().stream()
                .map(categoryId-> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streaming = moviesRequest.streaming().stream()
                .map(streamingsId -> Streaming.builder().id(streamingsId).build())
                .toList();

        return Movie.builder()
                .title(moviesRequest.title())
                .description(moviesRequest.description())
                .releaseDate(moviesRequest.releaseDate())
                .rating(moviesRequest.rating())
                .categories(categories)
                .streamings(streaming)
                .build();
    }


    public static MovieResponse toMovieResponse(Movie movie){

        List<CategoryResponse> categories = movie.getCategories().stream()
                .map(movieCategory -> CategoryMapper.toCategoryResponse(movieCategory))
                .toList();

        List<StreamingResponse> streaming = movie.getStreamings().stream()
                .map(movieStreaming -> StreamingMapper.toStreamingResponse(movieStreaming))
                .toList();


        return  MovieResponse.builder()
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .category(categories)
                .streaming(streaming)
                .build();
    }

}
