package ru.playerservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

/**
 * Профиль игрока. Хранится в базе данных.
 */
@Entity
@Table(name = "player_profiles")
@Data
public class PlayerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; // Уникальный идентификатор профиля игрока

    @Column(nullable = false, unique = true)
    private UUID userId; // Идентификатор пользователя (User.id)

    @Column(nullable = false)
    private String nickname; // Отображаемое имя игрока

    private String avatarUrl; // Ссылка на аватар игрока

    @Embedded
    private PlayerStats statistics; // Статистика игрока

    @Embedded
    private PlayerPrefs preferences; // Настройки игрока

    private long createdAt; // Дата создания профиля
}
