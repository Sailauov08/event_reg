package com.example.registration;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class EventService {
    private final List<EventInfo> events = new ArrayList<>();
    private int nextId = 4;

    public EventService() {
        events.add(new EventInfo(1, "Web Design Workshop", "Room 101"));
        events.add(new EventInfo(2, "JavaScript Quiz", "Room 102"));
        events.add(new EventInfo(3, "IT Career Talk", "Room 103"));
    }

    public List<EventInfo> getEvents(String title) {
        List<EventInfo> result = new ArrayList<>();
        for (EventInfo event : events) {
            if (title == null || event.getTitle().toLowerCase(Locale.ROOT)
                    .contains(title.toLowerCase(Locale.ROOT))) {
                result.add(event);
            }
        }
        return result;
    }

    public EventInfo getById(int id) {
        for (EventInfo event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    public EventInfo addEvent(EventInfo input) {
        EventInfo event = new EventInfo(nextId++, input.getTitle(), input.getLocation());
        events.add(event);
        return event;
    }

    public EventInfo updateEvent(int id, EventInfo input) {
        EventInfo event = getById(id);
        if (event == null) {
            return null;
        }
        event.setTitle(input.getTitle());
        event.setLocation(input.getLocation());
        return event;
    }

    public boolean deleteEvent(int id) {
        EventInfo event = getById(id);
        return event != null && events.remove(event);
    }
}
