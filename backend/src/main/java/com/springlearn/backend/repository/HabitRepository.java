package com.springlearn.backend.repository;

import com.springlearn.backend.model.Habit;
import com.springlearn.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HabitRepository extends JpaRepository<Habit, UUID> {
    List<Habit> findByUser(User user);
}
