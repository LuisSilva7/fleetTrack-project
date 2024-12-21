package org.fleettrack.tracking;

import java.time.LocalDateTime;

public record TrackingResponse(
        Long id,
        double latitude,
        double longitude,
        LocalDateTime timestamp,
        Long vehicleId
) {
}
