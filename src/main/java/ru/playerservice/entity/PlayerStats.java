package ru.playerservice.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Статистика игрока. Вложенная сущность.
 */
@Embeddable
@Data
public class PlayerStats {
    private int gamesPlayed = 0; // Количество сыгранных игр

    private int gamesWon = 0; // Количество побед

    private int gamesLost = 0; // Количество поражений

    private int totalScore = 0; // Общий набранный счет
}
