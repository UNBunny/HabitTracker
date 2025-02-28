package com.springlearn.backend.service;

import com.springlearn.backend.dto.HabitCompletionDto;
import com.springlearn.backend.dto.HabitProgressDto;
import com.springlearn.backend.mapper.HabitProgressMapper;
import com.springlearn.backend.model.Habit;
import com.springlearn.backend.model.HabitProgress;
import com.springlearn.backend.repository.HabitProgressRepository;
import com.springlearn.backend.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class HabitProgressService {
    @Autowired
    private HabitProgressRepository habitProgressRepository;
    @Autowired
    private HabitProgressMapper habitProgressMapper;
    @Autowired
    private HabitRepository habitRepository;

    public HabitProgress createHabitProgress(HabitProgress habitProgress) {
        return habitProgressRepository.save(habitProgress);
    }

    public Optional<List<HabitProgress>> getHabitProgressByHabit(Habit habit) {
        return habitProgressRepository.findByHabit(habit);
    }

    public void deleteHabitProgress(UUID id) {
        habitProgressRepository.deleteById(id);
    }

    public HabitProgressDto completeHabit(HabitCompletionDto habitCompletionDto) {
        Habit habit = habitRepository.findById(habitCompletionDto.getHabitId()).orElseThrow(() -> new RuntimeException("Habit not found"));
        HabitProgress habitProgress = HabitProgress.builder()
                .habit(habit)
                .date(LocalDateTime.now())
                .completed(true)
                .build();
        return habitProgressMapper.toDto(habitProgressRepository.save(habitProgress));
    }

    public List<HabitProgressDto> getHabitProgress(UUID habitId) {
        List<HabitProgress> progressList = habitProgressRepository.findByHabitId(habitId);
        return progressList.stream().map(habitProgressMapper::toDto).collect(Collectors.toList());
    }
}
