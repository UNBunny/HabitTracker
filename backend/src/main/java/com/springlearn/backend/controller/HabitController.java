package com.springlearn.backend.controller;

import com.springlearn.backend.dto.HabitRequestDto;
import com.springlearn.backend.dto.HabitResponseDto;
import com.springlearn.backend.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/habits")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @PostMapping
    public ResponseEntity<HabitResponseDto> addHabit(@RequestBody @Valid HabitRequestDto habitRequestDto) {
        return new ResponseEntity<>(habitService.createHabit(habitRequestDto), HttpStatus.CREATED);
    }

    // Наверное лучше @GetMapping("/{userId}"), но пусть будет так
    @GetMapping()
    public ResponseEntity<List<HabitResponseDto>> getHabitsByUser(@RequestParam("userId") UUID userId) {
        return ResponseEntity.ok(habitService.getHabitsByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitResponseDto> getHabitById(@PathVariable UUID id) {
        return ResponseEntity.ok(habitService.getHabitById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable UUID id) {
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }
}
