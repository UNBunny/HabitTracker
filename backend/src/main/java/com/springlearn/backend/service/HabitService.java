package com.springlearn.backend.service;


import com.springlearn.backend.dto.HabitRequestDto;
import com.springlearn.backend.dto.HabitResponseDto;
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

    public HabitResponseDto createHabit(HabitRequestDto habitRequestDto) {
        User user = userRepository.findById(habitRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + habitRequestDto.getUserId()));

        HabitCategory category = habitCategoryRepository.findById(habitRequestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + habitRequestDto.getCategoryId()));


        Habit habit = new Habit();
        habit.setName(habitRequestDto.getName());
        habit.setCategory(category);
        habit.setUser(user);
        habit.setFrequency(Frequency.valueOf(habitRequestDto.getFrequency()));

        Habit savedDto = habitRepository.save(habit);

        return convertToResponseDto(savedDto);

    }

    public List<HabitResponseDto> getHabitsByUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Habit> habits = habitRepository.findByUser(user);
        return habits.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }


    public Optional<Habit> getHabitById(UUID id) {
        return habitRepository.findById(id);
    }

    public void deleteHabit(UUID id) {
        habitRepository.deleteById(id);
    }

    private HabitResponseDto convertToResponseDto(Habit habit) {
        return new HabitResponseDto(
                habit.getId(),
                habit.getName(),
                habit.getDescription(),
                habit.getUser().getId(),
                habit.getFrequency().name()
        );
    }
}
