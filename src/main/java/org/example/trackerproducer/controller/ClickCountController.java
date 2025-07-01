package org.example.trackerproducer.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/clicks")
public class ClickCountController {

    private final Map<String, Long> clickStore = new ConcurrentHashMap<>();

    @KafkaListener(topics = "click-counts", groupId = "click-consumer")
    public void listen(ConsumerRecord<String, String> record) {
        clickStore.put(record.key(), Long.parseLong(record.value()));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> totalClicks() {
        long total = clickStore.values().stream().mapToLong(Long::longValue).sum();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/count/{userId}")
    public ResponseEntity<Long> userClicks(@PathVariable String userId) {
        return ResponseEntity.ok(clickStore.getOrDefault(userId, 0L));
    }
}
