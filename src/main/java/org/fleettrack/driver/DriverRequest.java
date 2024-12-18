package org.fleettrack.driver;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DriverRequest(
        @NotNull(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,
        @NotNull(message = "Driving license number is required")
        @Size(min = 5, max = 20, message = "Driving license number must be between 5 and 20 characters")
        String drivingLicenseNumber,
        @NotNull(message = "Driving license expiration date is required")
        @Pattern(
                regexp = "\\d{4}-\\d{2}-\\d{2}",
                message = "Driving license expiration date must be in the format yyyy-MM-dd"
        )
        String drivingLicenseExpirationDate,
        @NotNull(message = "Driving license category is required")
        @Pattern(
                regexp = "[A-Z]{1,3}",
                message = "Driving license category must contain 1 to 3 uppercase letters (e.g., 'B', 'C1')"
        )
        String drivingLicenseCategory,
        @NotNull(message = "Phone number is required")
        @Pattern(
                regexp = "\\+?[0-9]{7,15}",
                message = "Phone number must contain only digits and can optionally start with a '+'"
        )
        String phoneNumber,
        @NotNull(message = "Email is required")
        @Email(message = "Email must be a valid format")
        String email,
        Long vehicleId
) {
}
