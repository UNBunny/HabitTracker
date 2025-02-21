package com.springlearn.backend.service;


import com.springlearn.backend.model.Habit;
import com.springlearn.backend.model.User;
import com.springlearn.backend.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HabitService {
    @Autowired
    private HabitRepository habitRepository;

    public Habit createHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public List<Habit> getHabitsByUser(User user){
        return habitRepository.findByUser(user);
    }

    public Optional<Habit> getHabitById(UUID id) {
        return habitRepository.findById(id);
    }

    public void deleteHabit(UUID id) {
        habitRepository.deleteById(id);
    }
}
