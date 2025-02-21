package com.springlearn.backend.controller;


import com.springlearn.backend.dto.UserDto;
import com.springlearn.backend.model.User;
import com.springlearn.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
        User user = userService.getUserByUserName(userName);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/telegram/{telegramChatId}")
    public ResponseEntity<User> getUserByTelegramChatId(@PathVariable Long telegramChatId) {
        User user = userService.getUserByTelegramChatId(telegramChatId);
        return ResponseEntity.ok(user);
    }
}
