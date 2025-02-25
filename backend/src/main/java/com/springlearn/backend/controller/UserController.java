package com.springlearn.backend.controller;


import com.springlearn.backend.dto.UserDto;
import com.springlearn.backend.model.User;
import com.springlearn.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
        User createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/username/{userName}")
    public ResponseEntity<UserDto> getUserByUserName(@PathVariable String userName) {
        UserDto user = userService.getUserByUserName(userName);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/telegram/{telegramChatId}")
    public ResponseEntity<UserDto> getUserByTelegramChatId(@PathVariable Long telegramChatId) {
        UserDto user = userService.getUserByTelegramChatId(telegramChatId);
        return ResponseEntity.ok(user);
    }
}
