package com.movieflix.streaming.controller;

import com.movieflix.streaming.controller.dtos.request.StreamingRequest;
import com.movieflix.streaming.controller.dtos.response.StreamingResponse;
import com.movieflix.streaming.entity.Streaming;
import com.movieflix.streaming.mapper.StreamingMapper;
import com.movieflix.streaming.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAllStreaming(){
        List<StreamingResponse> streamings = streamingService.findAll()
                .stream()
                .map(streaming -> StreamingMapper.toStreamingResponse(streaming))
                .toList();
        return ResponseEntity.ok(streamings);
    }

    @PostMapping("/save")
    public ResponseEntity<StreamingResponse> saveStreaming(@RequestBody StreamingRequest streamingRequest){
        Streaming newStreaming = StreamingMapper.toStreaming(streamingRequest);
        Streaming saveSteaming = streamingService.save(newStreaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(saveSteaming));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getByStreamingId(@PathVariable long id){
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok().body( StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreaming(@PathVariable long id){
        streamingService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

