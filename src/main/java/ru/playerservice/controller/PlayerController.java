package ru.playerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.playerservice.entity.PlayerProfile;
import ru.playerservice.service.PlayerService;

import java.security.Principal;
import java.util.UUID;

/**
 * Контроллер для управления профилями игроков.
 */
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    /**
     * Получает профиль текущего игрока.
     */
    @GetMapping("/me")
    public PlayerProfile getMyProfile(Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        return playerService.getPlayerProfileByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Player profile not found"));
    }

    /**
     * Получает профиль игрока по идентификатору профиля.
     */
    @GetMapping("/{profileId}")
    public PlayerProfile getPlayerProfile(@PathVariable UUID profileId) {
        return playerService.getPlayerProfile(profileId)
                .orElseThrow(() -> new RuntimeException("Player profile not found"));
    }

    /**
     * Обновляет профиль текущего игрока.
     */
    @PutMapping("/me")
    public PlayerProfile updateMyProfile(@RequestBody PlayerProfile updatedProfile, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        PlayerProfile existingProfile = playerService.getPlayerProfileByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Player profile not found"));

        return playerService.updatePlayerProfile(existingProfile.getId(), updatedProfile);
    }

    /**
     * Получает статистику текущего игрока.
     */
    @GetMapping("/me/statistics")
    public PlayerProfile getMyStatistics(Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        return playerService.getPlayerProfileByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Player profile not found"));
    }

    /**
     * Обновляет статистику игрока (для администраторов или внутренних сервисов).
     */
    @PutMapping("/{userId}/statistics")
    public PlayerProfile updatePlayerStatistics(@PathVariable UUID userId, @RequestBody PlayerProfile updatedStats) {
        return playerService.updatePlayerStats(userId, updatedStats);
    }
}
