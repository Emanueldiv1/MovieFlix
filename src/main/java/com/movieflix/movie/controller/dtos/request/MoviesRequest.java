package com.movieflix.movie.controller.dtos.request;

import com.movieflix.category.controller.dtos.response.CategoryResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MoviesRequest(String title,
                            String description,
                            LocalDate releaseDate,
                            Double rating,
                            List<Long> categories,
                            List<Long> streaming){
}
