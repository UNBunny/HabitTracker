package com.springlearn.backend.controller;

import com.springlearn.backend.dto.ReminderDto;
import com.springlearn.backend.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {
    private final ReminderService reminderService;

    @PostMapping
    public ResponseEntity<ReminderDto> createReminder(@RequestBody ReminderDto reminderDto) {
        return ResponseEntity.ok(reminderService.createReminder(reminderDto));
    }

    @GetMapping("/{habitId}")
    public ResponseEntity<List<ReminderDto>> getRemindersByHabitId(@PathVariable("habitId") UUID habitId) {
        return ResponseEntity.ok(reminderService.getRemindersByHabitId(habitId));
    }

    @DeleteMapping("/{reminderId}")
    public ResponseEntity<Void> deleteReminder(@PathVariable("reminderId") UUID reminderId) {
        reminderService.deleteReminder(reminderId);
        return ResponseEntity.noContent().build();
    }

}
