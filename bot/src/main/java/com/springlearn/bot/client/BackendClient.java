package com.springlearn.bot.client;

import com.springlearn.bot.dto.HabitDto;
import com.springlearn.bot.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class BackendClient {


    private final RestTemplate restTemplate;
    private static final String BACKEND_URL = "http://localhost:8080/api/users";

    @Autowired
    public BackendClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto getUserByTelegramChatId(Long telegramChatId) {
        try {
            return restTemplate.getForObject(BACKEND_URL + "/telegram/" + telegramChatId, UserDto.class);
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        } catch (RestClientException e) {
            throw new RuntimeException("Ошибка при получении пользователя: " + e.getMessage(), e);
        }
    }

    public UserDto createUser(UserDto userDto) {
        return restTemplate.postForObject(BACKEND_URL, userDto, UserDto.class);
    }

    public String addHabit(HabitDto habitDto) {
        try {
            return restTemplate.postForObject(BACKEND_URL + "/habits", habitDto, String.class);
        } catch (HttpClientErrorException e) {
            return "Ошибка при добавлении привычки: " + e.getStatusCode();
        } catch (RestClientException e) {
            return "Ошибка при добавлении привычки: " + e.getMessage();
        }
    }

}
