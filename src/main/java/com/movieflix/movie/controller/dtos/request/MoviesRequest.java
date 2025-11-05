package com.movieflix.movie.controller.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MoviesRequest(String title,
                            String description,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
                            LocalDate releaseDate,
                            Double rating,
                            List<Long> categories,
                            List<Long> streaming){
}
