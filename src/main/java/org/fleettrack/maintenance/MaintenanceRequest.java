package org.fleettrack.maintenance;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MaintenanceRequest(
        @NotNull(message = "Maintenance type is required")
        @Size(min = 3, max = 50, message = "Maintenance type must be between 3 and 50 characters")
        String maintenanceType,

        @NotNull(message = "Date is required")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in the format yyyy-MM-dd")
        String date,

        @Positive(message = "Cost must be a positive value")
        double cost,

        @Size(max = 255, message = "Description should be at most 255 characters")
        String description,

        @NotNull(message = "Vehicle ID is required")
        Long vehicleId
) {
}
