package com.movieflix.category.controller.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public record CategoryResponse(Long id, String name) {
}
