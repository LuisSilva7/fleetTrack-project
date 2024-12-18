package org.fleettrack.driver;

import org.fleettrack.vehicle.VehicleResponse;

public record DriverResponse(
        Long id,
        String name,
        String drivingLicenseNumber,
        String drivingLicenseExpirationDate,
        String drivingLicenseCategory,
        String phoneNumber,
        String email,
        VehicleResponse vehicleResponse
) {
}
