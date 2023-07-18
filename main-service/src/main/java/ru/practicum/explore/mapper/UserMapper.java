package ru.practicum.explore.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explore.model.user.User;
import ru.practicum.explore.model.user.dto.UserDto;

@UtilityClass
public class UserMapper {

    public User toUser(UserDto userDto) {
        return new User(0, userDto.getName(), userDto.getEmail());
    }

    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}
