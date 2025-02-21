package com.springlearn.backend.service;


import com.springlearn.backend.model.User;
import com.springlearn.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByTelegramChatId(Long telegramChatId) {
        return userRepository.findByTelegramChatId(telegramChatId);
    }

    public Optional<User> getUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }
}
