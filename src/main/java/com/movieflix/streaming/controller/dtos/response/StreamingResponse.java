package com.movieflix.streaming.controller.dtos.response;

import lombok.Builder;

@Builder
public record StreamingResponse(Long id, String name) {
}
