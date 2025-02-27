package com.springlearn.bot.state;

import com.springlearn.bot.dto.HabitDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HabitCache {
    private final Map<Long, HabitDto> cache = new HashMap<>();

    public void putHabitData(long chatId, HabitDto habit) {
        cache.put(chatId, habit);
    }

    public HabitDto getHabitData(long chatId) {
        return cache.get(chatId);
    }

    public void clearHabitData(long chatId) {
        cache.remove(chatId);
    }
}
