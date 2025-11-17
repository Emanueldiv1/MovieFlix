package com.movieflix.streaming.controller.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest( @NotEmpty (message = "the name field cannot be empty ") String name) {
}
