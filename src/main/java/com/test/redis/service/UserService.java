package com.test.redis.service;

import com.test.redis.dto.CreateUserRequest;
import com.test.redis.dto.UpdateUserRequest;
import com.test.redis.model.User;
import com.test.redis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public User createUser(CreateUserRequest request) {
        return userRepository.save(modelMapper.map(request, User.class));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    public User updateUser(UpdateUserRequest request) {
        Optional<User> entity = userRepository.findById(request.id());

        if (entity.isPresent()) {
            User user = entity.get();
            user.setPassword(request.password());

            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
