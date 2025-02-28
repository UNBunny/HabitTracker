package com.springlearn.backend.controller;


import com.springlearn.backend.dto.HabitCompletionDto;
import com.springlearn.backend.dto.HabitProgressDto;
import com.springlearn.backend.model.HabitProgress;
import com.springlearn.backend.service.HabitProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/habit-progress")
@RequiredArgsConstructor

public class HabitProgressController {
    private final HabitProgressService habitProgressService;

    @PostMapping("/complete")
    public ResponseEntity<HabitProgressDto> completeHabit(@RequestBody HabitCompletionDto habitCompletionDto) {
        HabitProgressDto progress = habitProgressService.completeHabit(habitCompletionDto);
        return ResponseEntity.ok(progress);
    }

    @GetMapping("/{habitId}")
    public ResponseEntity<List<HabitProgressDto>> getHabitProgress(@PathVariable UUID habitId) {
        List<HabitProgressDto> progressList = habitProgressService.getHabitProgress(habitId);
        return ResponseEntity.ok(progressList);
    }
}
