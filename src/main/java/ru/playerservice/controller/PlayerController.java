package ru.playerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.playerservice.dto.PlayerDto;
import ru.playerservice.entity.PlayerProfile;
import ru.playerservice.service.PlayerService;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

/**
 * Контроллер для управления профилями игроков.
 */
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/{idUser}")
    public ResponseEntity<?> createPlayerProfile(@PathVariable UUID idUser, @RequestBody Map<String, Object> user){
        System.out.println(user);
        PlayerProfile playerProfile = playerService.createPlayerProfile(idUser, user.get("username").toString());
        return ResponseEntity.ok(playerProfile);
    }

    /**
     * Получает профиль текущего игрока.
     */
    @GetMapping("/me/{userId}")
    public PlayerProfile getMyProfile(@PathVariable UUID userId) {
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

    @GetMapping("/{profileId}/player-response")
    public PlayerDto getPlayerResponse(@PathVariable UUID profileId) {
        System.out.println(profileId);
        return playerService.getPlayerResponse(profileId);
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
