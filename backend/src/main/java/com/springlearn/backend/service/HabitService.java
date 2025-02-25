package com.springlearn.backend.service;


import com.springlearn.backend.dto.HabitDto;
import com.springlearn.backend.exception.ResourceNotFoundException;
import com.springlearn.backend.model.Frequency;
import com.springlearn.backend.model.Habit;
import com.springlearn.backend.model.HabitCategory;
import com.springlearn.backend.model.User;
import com.springlearn.backend.repository.HabitCategoryRepository;
import com.springlearn.backend.repository.HabitRepository;
import com.springlearn.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j

@Service
public class HabitService {
    @Autowired
    private HabitRepository habitRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HabitCategoryRepository habitCategoryRepository;

    public Habit createHabit(HabitDto habitDto) {
        User user = userRepository.findById(habitDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + habitDto.getUserId()));

        HabitCategory category = habitCategoryRepository.findById(habitDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + habitDto.getCategoryId()));


        Habit habit = new Habit();
        habit.setName(habitDto.getName());
        habit.setCategory(category);
        habit.setUser(user);
        habit.setFrequency(Frequency.valueOf(habitDto.getFrequency()));
        log.error("Habit creation failed");
        return habitRepository.save(habit);

    }

    public List<HabitDto> getHabitsByUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Habit> habits = habitRepository.findByUser(user);

        return habits.stream()
                .map(habit -> new HabitDto(
                        habit.getId(),
                        habit.getName(),
                        habit.getDescription(),
                        habit.getUser().getId(),
                        habit.getFrequency()
                ))
                .collect(Collectors.toList());
    }


    public Optional<Habit> getHabitById(UUID id) {
        return habitRepository.findById(id);
    }

    public void deleteHabit(UUID id) {
        habitRepository.deleteById(id);
    }
}
