package pl.crystalbud.crystallogistics.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTableDTO(
        @NotNull
        @Size(min = 3, max = 50)
        String title,
        @Size(min=5, max = 500)
        String details) {
}
