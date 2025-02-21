package com.springlearn.backend.repository;

import com.springlearn.backend.model.Habit;
import com.springlearn.backend.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReminderRepository extends JpaRepository<Reminder, UUID> {
    Optional<List<Reminder>> findByHabit(Habit habit);
}
