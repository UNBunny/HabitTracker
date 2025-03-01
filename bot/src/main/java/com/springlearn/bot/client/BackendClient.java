package com.springlearn.bot.client;

import com.springlearn.bot.dto.HabitRequestDto;
import com.springlearn.bot.dto.HabitResponseDto;
import com.springlearn.bot.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class BackendClient {


    private final RestTemplate restTemplate;
    private static final String BACKEND_URL = "http://localhost:8080/api/";

    @Autowired
    public BackendClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto getUserByTelegramChatId(Long telegramChatId) {
        try {
            return restTemplate.getForObject(BACKEND_URL + "users/telegram/" + telegramChatId, UserDto.class);
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        } catch (RestClientException e) {
            throw new RuntimeException("Ошибка при получении пользователя: " + e.getMessage(), e);
        }
    }

    public UserDto createUser(UserDto userDto) {
        return restTemplate.postForObject(BACKEND_URL + "/users", userDto, UserDto.class);
    }

    public HabitResponseDto addHabit(HabitRequestDto habitRequestDto) {
        return restTemplate.postForObject(BACKEND_URL + "/habits", habitRequestDto, HabitResponseDto.class);
    }

}
