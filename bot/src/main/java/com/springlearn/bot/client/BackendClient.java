package com.springlearn.bot.client;

import com.springlearn.bot.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class BackendClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BACKEND_URL = "http://localhost:8080/api/users";

    public UserDto getUserByTelegramChatId(Long telegramChatId) {
        try {
            return restTemplate.getForObject(BACKEND_URL + "/telegram/" + telegramChatId, UserDto.class);
        } catch (HttpClientErrorException.NotFound e) {
            // Пользователь не найден
            return null;
        }
    }

    public UserDto createUser(UserDto userDto) {
        return restTemplate.postForObject(BACKEND_URL, userDto, UserDto.class);
    }
}
