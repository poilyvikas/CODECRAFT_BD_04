package com.internship.secureuserapi.service;

import com.internship.secureuserapi.model.User;
import com.internship.secureuserapi.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    @Cacheable(value = "users")
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public Optional<User> getUserById(UUID id) {
        return repo.findById(id);
    }

    @CacheEvict(value = "users", allEntries = true)
    public User createUser(User user) {
        return repo.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public User updateUser(UUID id, User updated) {
        updated.setId(id);
        return repo.save(updated);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void deleteUser(UUID id) {
        repo.deleteById(id);
    }
}
