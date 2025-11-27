package com.movieflix.movie.controller.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MoviesRequest(@Schema(type = "string", description = "Name do filme")
                            @NotEmpty (message = "the Title field cannot be empty") String title,

                            @Schema(type = "string", description = "Breve descrição sobre o filme")
                            String description,

                            @Schema(type = "date", description = "Data de lançamento. Ex: dd/mm/yyyy")
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
                            LocalDate releaseDate,

                            @Schema(type = "double", description = "Nota de avaliação do filme")
                            Double rating,

                            @Schema(type = "array", description = "Lista de IDs de categorias")
                            List<Long> categories,

                            @Schema(type = "array", description = "Lista de IDs de streamings")
                            List<Long> streaming){
}
