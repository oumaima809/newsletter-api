package com.newsletter.alhadhra.controller;

import com.newsletter.alhadhra.entity.SubscriberEntity;
import com.newsletter.alhadhra.service.SubscriberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriber")
@RequiredArgsConstructor
public class SubscriberController {
    private final SubscriberService subscriberService;

    @PostMapping
    ResponseEntity<SubscriberEntity> saveSubscriber(@Valid @RequestBody SubscriberEntity subscriber) {
        return  ResponseEntity.ok(subscriberService.saveSubscriber(subscriber));
    }

    @GetMapping("/emails")
    ResponseEntity<List<SubscriberEntity>> getSubscribers(){
        return ResponseEntity.ok(subscriberService.getSubscribers());
    }
}
