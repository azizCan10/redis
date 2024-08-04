package com.test.redis.service;

import com.test.redis.dto.CreateUserRequest;
import com.test.redis.dto.UpdateUserRequest;
import com.test.redis.model.User;
import com.test.redis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @CacheEvict(value = {"users"}, allEntries = true)
    public User createUser(CreateUserRequest request) {
        return userRepository.save(modelMapper.map(request, User.class));
    }

    @Cacheable(value = "users", key = "#root.methodName", unless = "#result == null")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Cacheable(cacheNames = "user_id", key = "#root.methodName + #id", unless = "#result == null")
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    @CachePut(cacheNames = "user_id", key = "'getUserById' + #request.id", unless = "#result == null")
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

    @CacheEvict(value = {"users", "user_id"}, allEntries = true)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
