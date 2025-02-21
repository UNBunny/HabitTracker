package com.springlearn.backend.repository;

import com.springlearn.backend.model.Habit;
import com.springlearn.backend.model.HabitCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HabitCategoryRepository extends JpaRepository<HabitCategory, UUID> {
    Optional<HabitCategory> findByName(String name);
}
