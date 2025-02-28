package com.springlearn.backend.controller;


import com.springlearn.backend.dto.UserDto;
import com.springlearn.backend.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/username/{userName}")
    public ResponseEntity<UserDto> getUserByUserName(@PathVariable String userName) {
        return ResponseEntity.ok(userService.getUserByUserName(userName));
    }

    @GetMapping("/telegram/{telegramChatId}")
    public ResponseEntity<UserDto> getUserByTelegramChatId(@PathVariable Long telegramChatId) {
        return ResponseEntity.ok(userService.getUserByTelegramChatId(telegramChatId));
    }
}
