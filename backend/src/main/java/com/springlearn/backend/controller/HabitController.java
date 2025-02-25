package com.springlearn.backend.controller;

import com.springlearn.backend.dto.HabitDto;
import com.springlearn.backend.model.Habit;
import com.springlearn.backend.service.HabitService;
import com.springlearn.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Habit> addHabit(@RequestBody HabitDto habitDto) {
        Habit createdHabit = habitService.createHabit(habitDto);
        return ResponseEntity.ok(createdHabit);
    }

    @GetMapping()
    public ResponseEntity<List<HabitDto>> getHabitsByUser(@RequestParam("userId") UUID userId) {
        List<HabitDto> habits = habitService.getHabitsByUser(userId);
        return ResponseEntity.ok(habits);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable UUID id) {
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }


}
