package com.springlearn.backend.service;


import com.springlearn.backend.dto.HabitRequestDto;
import com.springlearn.backend.dto.HabitResponseDto;
import com.springlearn.backend.exception.ResourceNotFoundException;
import com.springlearn.backend.mapper.HabitMapper;
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

    @Autowired
    private HabitMapper habitMapper;


    // Создаем привычку
    public HabitResponseDto createHabit(HabitRequestDto habitRequestDto) {

        if (habitRequestDto.getName() == null || habitRequestDto.getName().isBlank()) {
            throw new IllegalArgumentException("Habit name cannot be null or empty");
        }

        User user = userRepository.findById(habitRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + habitRequestDto.getUserId()));

        HabitCategory category = habitCategoryRepository.findById(habitRequestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + habitRequestDto.getCategoryId()));

        Habit habit = habitMapper.toEntity(habitRequestDto, user, category);
        habitRepository.save(habit);
//        Habit habit = new Habit();
//        habit.setFrequency(habitRequestDto.getFrequency());
//        habit.setName(habitRequestDto.getName());
//        habit.setDescription(habitRequestDto.getDescription());
//        habit.setCategory(category);
//        habit.setUser(user);
//        habit.setFrequency(Frequency.valueOf(habitRequestDto.getFrequency()));
        return habitMapper.toDto(habit);
    }


    public List<HabitResponseDto> getHabitsByUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Habit> habits = habitRepository.findByUser(user);
        return habits.stream().map(habitMapper::toDto).collect(Collectors.toList());
    }


    public HabitResponseDto getHabitById(UUID id) {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Habit not found with id: " + id));
        return habitMapper.toDto(habit);
    }

    public void deleteHabit(UUID id) {
        if (!habitRepository.existsById(id)) {
            throw new RuntimeException("Habit not found");
        }
        habitRepository.deleteById(id);
    }

}
