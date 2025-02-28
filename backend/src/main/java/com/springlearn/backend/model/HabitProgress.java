package com.springlearn.backend.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "habit_progress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitProgress {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", nullable = false)
    @NotNull
    private Habit habit;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime date;

    @Column(nullable = false)
    private boolean completed;
}

