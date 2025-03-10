package com.springlearn.backend.service;


import com.springlearn.backend.dto.UserDto;
import com.springlearn.backend.exception.ResourceNotFoundException;
import com.springlearn.backend.mapper.UserMapper;
import com.springlearn.backend.model.User;
import com.springlearn.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setUserName(user.getUserName());
//        userDto.setTelegramChatId(user.getTelegramChatId());

        return userMapper.toDto(user);
    }

    public UserDto getUserByTelegramChatId(Long telegramChatId) {
        User user = userRepository.findByTelegramChatId(telegramChatId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with Telegram Chat ID: " + telegramChatId));

//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setUserName(user.getUserName());
//        userDto.setTelegramChatId(user.getTelegramChatId());

        return userMapper.toDto(user);
    }

    public UserDto getUserByUserName(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with name: " + username));

//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setUserName(user.getUserName());
//        userDto.setTelegramChatId(user.getTelegramChatId());

        return userMapper.toDto(user);
    }
}
