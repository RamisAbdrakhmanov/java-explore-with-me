package ru.practicum.explore.admin.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.explore.admin.repository.AdminUserRepository;
import ru.practicum.explore.common.MyPageRequest;
import ru.practicum.explore.model.exception.CustomNotFoundException;
import ru.practicum.explore.model.user.User;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminUserService {

    private AdminUserRepository adminUserRepository;

    public List<User> getUsers(List<Long> id, int from, int size) {
        Pageable pageable = MyPageRequest.of(from, size);
        return adminUserRepository.findAllByIdIn(id, pageable);
    }

    public User addUser(User user) {
        return adminUserRepository.save(user);
    }

    public void deleteUser(long id) {
        getUserById(id);
        adminUserRepository.deleteById(id);
    }

    public User getUserById(long id) {
        return adminUserRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(String.format("User with id=%d was not found", id)));
    }
}
