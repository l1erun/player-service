package ru.playerservice.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Настройки игрока. Вложенная сущность.
 */
@Embeddable
@Data
public class PlayerPrefs {
    private String language; // Предпочтительный язык интерфейса

    private boolean notificationsEnabled; // Включены ли уведомления
}
