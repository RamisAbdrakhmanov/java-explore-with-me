package ru.practicum.explore.CONSERREP.controller.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.CONSERREP.service.user.UserRequestService;
import ru.practicum.explore.mapper.ParticipationRequestMapper;
import ru.practicum.explore.model.request.ParticipationRequestDto;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Valid
@RestController
@RequestMapping("/users/{userId}/requests")
@AllArgsConstructor
public class UserRequestController {

    private UserRequestService userRequestService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ParticipationRequestDto> getRequest(@PathVariable long userId){
        log.info("Start: USER : \"getRequest\" : userId={}", userId);
        return userRequestService.getRequest(userId)
                .stream().map(ParticipationRequestMapper::toParticipationRequestDto).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto addRequest(@PathVariable long userId, @RequestParam long eventId){
        log.info("Start: USER : \"addRequest\" : userId={}, eventId={}", userId, eventId);
        return ParticipationRequestMapper
                .toParticipationRequestDto(userRequestService.addRequest(userId,eventId));
    }

    @PatchMapping("/{requestId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public ParticipationRequestDto updateRequest(@PathVariable long userId, @PathVariable long requestId){
        log.info("Start: USER : \"updateRequest\" : userId={}, requestId={}", userId,requestId);
        return ParticipationRequestMapper
                .toParticipationRequestDto(userRequestService.updateRequest(userId,requestId));
    }
}
