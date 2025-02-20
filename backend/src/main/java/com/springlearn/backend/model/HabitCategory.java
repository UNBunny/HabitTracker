package com.springlearn.backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "habit_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitCategory {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
}
