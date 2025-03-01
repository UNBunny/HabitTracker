package com.springlearn.backend.service;


import com.springlearn.backend.dto.ReminderDto;
import com.springlearn.backend.mapper.ReminderMapper;
import com.springlearn.backend.model.Habit;
import com.springlearn.backend.model.Reminder;
import com.springlearn.backend.repository.HabitRepository;
import com.springlearn.backend.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReminderService {
    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;
    private final HabitRepository habitRepository;

    public ReminderDto createReminder(ReminderDto reminderDto) {
        Habit habit = habitRepository.findById(reminderDto.getHabitId()).orElseThrow(() -> new RuntimeException("Habit not found"));

        // Можно заменить с помощью mapstruct (toEntity), но в учебных целях оставил так
        Reminder reminder = Reminder.builder()
                .habit(habit)
                .time(reminderDto.getTime())
                .isSent(false)
                .build();

        return reminderMapper.toDto(reminderRepository.save(reminder));
    }

    public List<ReminderDto> getRemindersByHabitId(UUID habitId) {
        List<Reminder> reminders = reminderRepository.findByHabitId(habitId);
        return reminders.stream().map(reminderMapper::toDto).collect(Collectors.toList());
    }

    public void deleteReminder(UUID reminderId) {
        reminderRepository.deleteById(reminderId);
    }
}
