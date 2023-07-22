package ru.practicum.explore.model.comment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explore.model.event.Event;
import ru.practicum.explore.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "message")
    private String text;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "commentator_id")
    private User author;
    @Column(name = "created")
    private LocalDateTime created;
}
