package com.springlearn.backend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "habit_progress")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitProgress {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private boolean completed;
}
