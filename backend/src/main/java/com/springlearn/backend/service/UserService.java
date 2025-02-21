package com.springlearn.backend.service;


import com.springlearn.backend.dto.UserDto;
import com.springlearn.backend.exception.ResourceNotFoundException;
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

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setTelegramChatId(userDto.getTelegramChatId());
        return userRepository.save(user);
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User getUserByTelegramChatId(Long telegramChatId) {
        return userRepository.findByTelegramChatId(telegramChatId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with Telegram Chat ID: " + telegramChatId));
    }

    public User getUserByUserName(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with name: " + username));
    }
}
