package ru.practicum.explore.CONSERREP.service.admin;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.explore.CONSERREP.repository.UserRepository;
import ru.practicum.explore.common.EntityFinder;
import ru.practicum.explore.common.MyPageRequest;
import ru.practicum.explore.model.user.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AdminUserService {

    private EntityFinder entityFinder;
    private UserRepository userRepository;

    public List<User> getUsers(List<Long> ids, int from, int size) {
        Pageable pageable = MyPageRequest.of(from, size);
        if (ids != null) {
            return userRepository.findAllByIdIn(ids, pageable);
        } else {
            return userRepository.findAll(pageable).toList();
        }
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        entityFinder.getUserById(id);
        userRepository.deleteById(id);
    }

}