package ru.practicum.explore.CONSERREP.service.all;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.explore.CONSERREP.repository.CommentRepository;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.common.MyPageRequest;
import ru.practicum.explore.model.comment.Comment;

import java.util.List;

@Service
@AllArgsConstructor
public class AllCommentService {

    private CommentRepository commentRepository;
    private EntityFinder entityFinder;

    public List<Comment> getComments(long eventId, int from, int size) {
        entityFinder.getEventById(eventId);
        Pageable pageable = MyPageRequest.of(from, size);
        return commentRepository.findAllByEventIdOrderByCreatedDesc(eventId, pageable);
    }
}
