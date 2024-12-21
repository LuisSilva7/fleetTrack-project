package org.fleettrack.tracking;

import jakarta.enterprise.context.ApplicationScoped;
import org.fleettrack.vehicle.Vehicle;

@ApplicationScoped
public class TrackingMapper {

    public Tracking toTrackingEntity(TrackingRequest request, Vehicle vehicle) {
        return Tracking.builder()
                .latitude(request.latitude())
                .longitude(request.longitude())
                .timestamp(request.timestamp())
                .vehicle(vehicle)
                .build();
    }

    public TrackingResponse toTrackingResponse(Tracking lastTracking, Long vehicleId) {
        return new TrackingResponse(
                lastTracking.getId(),
                lastTracking.getLatitude(),
                lastTracking.getLongitude(),
                lastTracking.getTimestamp(),
                vehicleId
        );
    }
}
