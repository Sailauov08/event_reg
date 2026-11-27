package com.example.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class RegistrationController {
    private final EventService eventService;

    public RegistrationController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventInfo> getEvents(@RequestParam(name = "title", required = false) String title) {
        return eventService.getEvents(title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventInfo> getEvent(@PathVariable("id") int id) {
        EventInfo event = eventService.getById(id);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventInfo> addEvent(@RequestBody EventInfo input) {
        if (invalid(input)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.addEvent(input));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventInfo> updateEvent(@PathVariable("id") int id,
                                                  @RequestBody EventInfo input) {
        if (eventService.getById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        if (invalid(input)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(eventService.updateEvent(id, input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") int id) {
        if (!eventService.deleteEvent(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private boolean invalid(EventInfo event) {
        return event == null || event.getTitle() == null || event.getTitle().isBlank()
                || event.getLocation() == null || event.getLocation().isBlank();
    }
}
