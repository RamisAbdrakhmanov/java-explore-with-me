package ru.practicum.explore.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explore.model.category.Category;
import ru.practicum.explore.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "annotation")
    private String annotation;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "confirmed_requests")
    private long confirmedRequests;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "description")
    private String description;
    @Column(name = "event_date")
    private LocalDateTime eventDate;
    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User user;
    @Column(name = "lat")
    private float lat;
    @Column(name = "lon")
    private float lon;
    @Column(name = "paid")
    private Boolean paid;
    @Column(name = "participant_limit")
    private int participantLimit;
    @Column(name = "published_on")
    private LocalDateTime publishedOn;
    @Column(name = "request_moderation")
    private Boolean requestModeration;
    @Column(name = "state")
    private State state;
    @Column(name = "title")
    private String title;
    @Column(name = "views")
    private Integer views;

    public Event(User user, Category category, String annotation, String description,
                 LocalDateTime eventDate, float lat, float lon, Boolean paid,
                 int participantLimit, Boolean requestModeration, String title) {
        this.annotation = annotation;
        this.category = category;
        this.description = description;
        this.eventDate = eventDate;
        this.user = user;
        this.lat = lat;
        this.lon = lon;
        this.paid = paid;
        this.participantLimit = participantLimit;
        this.requestModeration = requestModeration;
        this.title = title;


        this.createdOn = LocalDateTime.now();
        if (paid == null) {
            this.paid = false;
        }
        if (state == null) {
            this.state = State.PENDING;
        }
        if (requestModeration == null) {
            this.requestModeration = true;
        }
    }
}
