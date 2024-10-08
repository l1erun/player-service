package ru.playerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.playerservice.entity.PlayerProfile;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с профилями игроков.
 */
@Repository
public interface PlayerProfileRepository extends JpaRepository<PlayerProfile, UUID> {
    Optional<PlayerProfile> findByUserId(UUID userId);

    // Другие необходимые методы
}
