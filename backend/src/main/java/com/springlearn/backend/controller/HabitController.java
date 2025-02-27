package com.springlearn.backend.controller;

import com.springlearn.backend.dto.HabitRequestDto;
import com.springlearn.backend.dto.HabitResponseDto;
import com.springlearn.backend.model.Habit;
import com.springlearn.backend.service.HabitService;
import com.springlearn.backend.service.UserService;
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
    public ResponseEntity<HabitResponseDto> addHabit(@RequestBody HabitRequestDto habitRequestDto) {
        System.out.println("Received HabitRequestDto: " + habitRequestDto);
        HabitResponseDto createdHabit = habitService.createHabit(habitRequestDto);
        return new ResponseEntity<>(createdHabit, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<HabitResponseDto>> getHabitsByUser(@RequestParam("userId") UUID userId) {
        List<HabitResponseDto> habits = habitService.getHabitsByUser(userId);
        return ResponseEntity.ok(habits);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable UUID id) {
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }


}
