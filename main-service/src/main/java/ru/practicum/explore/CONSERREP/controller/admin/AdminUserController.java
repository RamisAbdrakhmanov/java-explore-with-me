package ru.practicum.explore.CONSERREP.controller.admin;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.CONSERREP.service.admin.AdminUserService;
import ru.practicum.explore.mapper.UserMapper;
import ru.practicum.explore.model.user.dto.NewUserDto;
import ru.practicum.explore.model.user.dto.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.stream.Collectors;

import static ru.practicum.explore.mapper.UserMapper.toUser;
import static ru.practicum.explore.mapper.UserMapper.toUserDto;

@Slf4j
@Valid
@RestController
@RequestMapping("/admin/users")
@AllArgsConstructor
public class AdminUserController {

    private AdminUserService adminUserService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers(@RequestParam List<Long> id,
                                  @RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                  @RequestParam(defaultValue = "10") @Positive int size) {
        log.info("Start: ADMIN : \"getUser\" : id={}", id);
        return adminUserService.getUsers(id, from, size)
                .stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody NewUserDto userDto) {
        log.info("Start: ADMIN : \"addUser\" : NewUserDto={}", userDto);
        return toUserDto(adminUserService.addUser(toUser(userDto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable @Positive long id) {
        log.info("Start: ADMIN : \"deleteUser\" id={}", id);
        adminUserService.deleteUser(id);
    }
}
