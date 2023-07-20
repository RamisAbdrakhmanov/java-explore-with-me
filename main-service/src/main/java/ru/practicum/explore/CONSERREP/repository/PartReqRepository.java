package ru.practicum.explore.CONSERREP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.model.request.ParticipationRequest;
import ru.practicum.explore.model.request.Status;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartReqRepository extends JpaRepository<ParticipationRequest, Long> {

    Optional<ParticipationRequest> findByRequesterIdAndEventId(long userId, long eventId);

    Optional<ParticipationRequest> findByIdAndRequesterId(long reqId, long userId);

    List<ParticipationRequest> findAllByRequesterId(long userId);

    List<ParticipationRequest> findAllByEventId(long eventId);

    List<ParticipationRequest> findAllByEventIdAndStatus(long eventId, Status status);
}
