package com.movieflix.movie.controller;

import com.movieflix.movie.controller.dtos.request.MoviesRequest;
import com.movieflix.movie.controller.dtos.response.MovieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Movie", description =" Responsavel pelo gerenciamento dos Filmes")
public interface MovieController{

    @Operation(summary = "Salvar Filme", description = "Responsavel por salvar um novo filme",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Filme salvo com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))

    ResponseEntity<MovieResponse> save(@Valid @RequestBody MoviesRequest moviesR);

    /*---------------------------------------------------------------------------------------------------------*/

    @Operation(summary = "Buscar lista de Filmes  ", description = "Responsavel por buscar uma Lista de filmes ",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filmes encontrados com sucesso",
            content = @Content(array = @ArraySchema( schema = @Schema(implementation = MovieResponse.class))))

    ResponseEntity<List<MovieResponse>> findAll();

    /*---------------------------------------------------------------------------------------------------------*/

    @Operation(summary = "Buscar filme por ID ", description = "Responsavel por um filme pelo ID cadastrado ",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme encontrado com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())

    ResponseEntity<MovieResponse> findById(@PathVariable Long id);

    /*---------------------------------------------------------------------------------------------------------*/

    @Operation(summary = "Alterar filme por ID ", description = "Responsavel por alterar um campo de um filme especifico ",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Filme alterado com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())

    ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MoviesRequest movieRequest);

    /*---------------------------------------------------------------------------------------------------------*/

    @Operation(summary = " Categoria de filmes ", description = "Responsavel por Buscar filmes por categoria especifica",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filmes encontrados ",
            content = @Content(array = @ArraySchema( schema = @Schema(implementation = MovieResponse.class))))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())

    ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category);

    /*---------------------------------------------------------------------------------------------------------*/

    @Operation(summary = " Streaming de filmes ", description = "Responsavel por Buscar filmes por Streaming especifica",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filmes encontrados ",
            content = @Content(array = @ArraySchema( schema = @Schema(implementation = MovieResponse.class))))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())

    ResponseEntity<List<MovieResponse>> findByStreaming(@RequestParam Long streaming);

    /*---------------------------------------------------------------------------------------------------------*/

    @Operation(summary = "Deletar filme por ID", description = "Responsavel por deletar um filme cadastrado ID cadastrado ",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Filme Deletado com sucesso",content = @Content())
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    ResponseEntity<Void> deleteById(@PathVariable Long id);

}
