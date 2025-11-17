package com.movieflix.category.controller.dtos.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty (message = "the name field cannot be empty") String name) {
}
