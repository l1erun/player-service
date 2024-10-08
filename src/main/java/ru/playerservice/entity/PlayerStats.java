package ru.playerservice.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Статистика игрока. Вложенная сущность.
 */
@Embeddable
@Data
public class PlayerStats {
    private int gamesPlayed; // Количество сыгранных игр

    private int gamesWon; // Количество побед

    private int gamesLost; // Количество поражений

    private int totalScore; // Общий набранный счет
}
