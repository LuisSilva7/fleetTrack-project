package org.fleettrack.route;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RouteRequest(
        @NotNull(message = "Origin street is required")
        @Size(min = 2, max = 100, message = "Origin street must be between 2 and 100 characters")
        String originStreet,

        @NotNull(message = "Origin city is required")
        @Size(min = 2, max = 50, message = "Origin city must be between 2 and 50 characters")
        String originCity,

        @NotNull(message = "Destination street is required")
        @Size(min = 2, max = 100, message = "Destination street must be between 2 and 100 characters")
        String destinationStreet,

        @NotNull(message = "Destination city is required")
        @Size(min = 2, max = 50, message = "Destination city must be between 2 and 50 characters")
        String destinationCity,

        @NotNull(message = "Distance is required")
        @Positive(message = "Distance must be greater than 0")
        double distance,

        @NotNull(message = "Estimated time is required")
        @Pattern(
                regexp = "\\d{1,2}:\\d{2}",
                message = "Estimated time must be in the format HH:mm (e.g., '02:30')"
        )
        String estimatedTime,

        @NotNull(message = "Tolls field is required")
        boolean tolls,
        Long vehicleId
) {
}

