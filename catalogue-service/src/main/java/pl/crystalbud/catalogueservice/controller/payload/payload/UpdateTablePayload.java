package pl.crystalbud.catalogueservice.controller.payload.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTablePayload(
        @NotNull(message = "{catalogue.tables.update.errors.title_is_null}")
        @Size(min = 3, max = 50, message = "{catalogue.tables.update.errors.title_size_is_invalid}")
        String title,
        @Size(max = 1000, message = "{catalogue.tables.update.errors.details_size_is_invalid}")
        String details) {
}
