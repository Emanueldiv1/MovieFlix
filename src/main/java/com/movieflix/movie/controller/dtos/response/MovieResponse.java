package com.movieflix.movie.controller.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieflix.category.controller.dtos.response.CategoryResponse;
import com.movieflix.category.entity.Category;
import com.movieflix.streaming.controller.dtos.response.StreamingResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MovieResponse(Long id,
                            String title,
                            String description,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
                            LocalDate releaseDate,
                            Double rating,
                            List<CategoryResponse> category,
                            List<StreamingResponse> streaming){
}
