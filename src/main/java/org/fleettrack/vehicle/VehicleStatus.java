package org.fleettrack.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VehicleStatus {
    ACTIVE("active"),
    INACTIVE("inactive");

    private final String status;

    public static VehicleStatus fromString(String status) {
        for (VehicleStatus vehicleStatus : VehicleStatus.values()) {
            if (vehicleStatus.getStatus().equalsIgnoreCase(status)) {
                return vehicleStatus;
            }
        }
        throw new IllegalArgumentException("Unknown vehicle status: " + status);
    }
}


