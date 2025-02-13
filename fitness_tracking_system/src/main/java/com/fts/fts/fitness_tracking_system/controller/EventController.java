package com.fts.fts.fitness_tracking_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fts.fts.fitness_tracking_system.utils.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/event")
public class EventController {

    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    @GetMapping("/{userId}")
    public SseEmitter subscribe(@PathVariable Integer userId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.put(Long.valueOf(userId), emitter);
        return emitter;
    }

    public void notify(Integer userId, String message) {
        SseEmitter emitter = emitters.get(Long.valueOf(userId));
        if (emitter != null) {
            try {
                String jsonMessage=objectMapper.writeValueAsString(message);
                emitter.send(jsonMessage, MediaType.TEXT_EVENT_STREAM);
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        }
    }
}
