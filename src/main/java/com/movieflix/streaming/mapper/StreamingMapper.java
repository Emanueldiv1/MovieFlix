package com.movieflix.streaming.mapper;

import com.movieflix.streaming.controller.dtos.request.StreamingRequest;
import com.movieflix.streaming.controller.dtos.response.StreamingResponse;
import com.movieflix.streaming.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest streamingRequest){
        return Streaming
                .builder()
                .name(streamingRequest.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
