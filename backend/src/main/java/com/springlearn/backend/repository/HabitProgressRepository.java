package com.springlearn.backend.repository;

import com.springlearn.backend.model.Habit;
import com.springlearn.backend.model.HabitProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HabitProgressRepository extends JpaRepository<HabitProgress, UUID> {
    Optional<List<HabitProgress>> findByHabit(Habit habit);

    List<HabitProgress> findByHabitId(UUID id);
}
