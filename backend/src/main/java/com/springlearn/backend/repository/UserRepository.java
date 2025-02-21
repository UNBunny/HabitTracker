package com.springlearn.backend.repository;

import com.springlearn.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String username);

    Optional<User> findByTelegramChatId(Long telegramChatId);


}
