package ru.practicum.explore.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.explore.model.user.User;
import ru.practicum.explore.model.user.dto.NewUserDto;
import ru.practicum.explore.model.user.dto.UserDto;
import ru.practicum.explore.model.user.dto.UserShortDto;

@UtilityClass
public class UserMapper {

    public User toUser(NewUserDto userDto) {
        return new User(0, userDto.getName(), userDto.getEmail());
    }

    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    public UserShortDto toUserShortDto(User user){
        return new UserShortDto(user.getId(), user.getName());
    }
}
