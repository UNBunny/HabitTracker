package com.springlearn.backend.service;

import com.springlearn.backend.model.Habit;
import com.springlearn.backend.model.HabitProgress;
import com.springlearn.backend.repository.HabitProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class HabitProgressService {
    @Autowired
    private HabitProgressRepository habitProgressRepository;

    public HabitProgress createHabitProgress(HabitProgress habitProgress) {
        return habitProgressRepository.save(habitProgress);
    }

    public Optional<List<HabitProgress>> getHabitProgressByHabit(Habit habit) {
        return habitProgressRepository.findByHabit(habit);
    }

    public void deleteHabitProgress(UUID id) {
        habitProgressRepository.deleteById(id);
    }
}
