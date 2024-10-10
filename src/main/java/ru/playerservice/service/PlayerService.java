package ru.playerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.playerservice.entity.PlayerPrefs;
import ru.playerservice.entity.PlayerProfile;
import ru.playerservice.entity.PlayerStats;
import ru.playerservice.repository.PlayerProfileRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Сервис для управления профилями игроков.
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerProfileRepository playerProfileRepository;

    /**
     * Создает новый профиль игрока.
     */
    public PlayerProfile createPlayerProfile(UUID idUser, String nickname) {
        PlayerStats playerStats = new PlayerStats();
        PlayerPrefs playerPrefs = new PlayerPrefs();
        PlayerProfile profile = new PlayerProfile();
        profile.setUserId(idUser);
        profile.setPreferences(playerPrefs);
        profile.setNickname(nickname);
        profile.setStatistics(playerStats);
        profile.setCreatedAt(System.currentTimeMillis());
        profile.setUpdatedAt(System.currentTimeMillis());
        return playerProfileRepository.save(profile);
    }

    /**
     * Получает профиль игрока по идентификатору профиля.
     */
    public Optional<PlayerProfile> getPlayerProfile(UUID profileId) {
        return playerProfileRepository.findById(profileId);
    }

    /**
     * Получает профиль игрока по идентификатору пользователя.
     */
    public Optional<PlayerProfile> getPlayerProfileByUserId(UUID userId) {
        return playerProfileRepository.findByUserId(userId);
    }

    /**
     * Обновляет профиль игрока.
     */
    public PlayerProfile updatePlayerProfile(UUID profileId, PlayerProfile updatedProfile) {
        PlayerProfile existingProfile = playerProfileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Player profile not found"));

        existingProfile.setNickname(updatedProfile.getNickname());
        existingProfile.setAvatarUrl(updatedProfile.getAvatarUrl());
        existingProfile.setPreferences(updatedProfile.getPreferences());
        existingProfile.setUpdatedAt(System.currentTimeMillis());

        return playerProfileRepository.save(existingProfile);
    }

    /**
     * Удаляет профиль игрока.
     */
    public void deletePlayerProfile(UUID profileId) {
        playerProfileRepository.deleteById(profileId);
    }

    // Методы для работы со статистикой

    /**
     * Обновляет статистику игрока.
     */
    public PlayerProfile updatePlayerStats(UUID userId, PlayerProfile updatedStats) {
        PlayerProfile existingProfile = playerProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Player profile not found"));

        existingProfile.setStatistics(updatedStats.getStatistics());
        existingProfile.setUpdatedAt(System.currentTimeMillis());

        return playerProfileRepository.save(existingProfile);
    }
}
