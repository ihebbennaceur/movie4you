package org.example.films.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SeanceDTO {

    @NotBlank(message = "Movie title cannot be blank")
    private String movieTitle;

    @NotNull(message = "Start time cannot be null")
    private LocalDateTime startTime;

    @NotNull(message = "Duration cannot be null")
    private Integer duration;

    @NotNull(message = "Cinema ID cannot be null")
    private Integer cinemaId;
}
