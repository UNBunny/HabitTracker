package com.springlearn.bot.state;

import com.springlearn.bot.dto.HabitRequestDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HabitCache {
    private final Map<Long, HabitRequestDto> cache = new HashMap<>();

    public void putHabitData(long chatId, HabitRequestDto habit) {
        cache.put(chatId, habit);
    }

    public HabitRequestDto getHabitData(long chatId) {
        return cache.get(chatId);
    }

    public void clearHabitData(long chatId) {
        cache.remove(chatId);
    }
}
