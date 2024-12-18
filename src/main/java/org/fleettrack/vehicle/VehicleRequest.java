package org.fleettrack.vehicle;

import jakarta.validation.constraints.*;

public record VehicleRequest(
        @NotNull(message = "Plate is required")
        @Size(min = 6, max = 6, message = "Plate must be exactly 6 characters")
        String plate,
        @NotNull(message = "Model is required")
        String model,
        @NotNull(message = "Model is required")
        @Min(value = 1995, message = "Manufacture year must be 1995 or later")
        @Max(value = 2030, message = "Manufacture year cannot be higher than 2030")
        Integer manufactureYear,
        @NotNull(message = "Model is required")
        @Min(value = 0, message = "Current kilometers must be non-negative")
        Integer currentKm,
        @NotNull(message = "Model is required")
        @Min(value = 1, message = "Capacity must be at least 1")
        Integer capacity,
        @NotNull(message = "Acquisition date is required")
        @Pattern(
                regexp = "\\d{4}-\\d{2}-\\d{2}",
                message = "Acquisition date must be in the format yyyy-MM-dd"
        )
        String acquisitionDate
) {
}