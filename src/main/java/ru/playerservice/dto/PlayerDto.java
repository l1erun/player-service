package ru.playerservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@ToString
public class PlayerDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("avatarUrl")
    private String avatarUrl;
    @JsonProperty("language")
    private String language;
}
