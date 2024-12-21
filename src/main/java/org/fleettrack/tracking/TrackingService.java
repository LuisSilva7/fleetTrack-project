package org.fleettrack.tracking;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fleettrack.exception.custom.ResourceNotFoundException;
import org.fleettrack.exception.custom.VehicleNotActiveException;
import org.fleettrack.vehicle.Vehicle;
import org.fleettrack.vehicle.VehicleRepository;

import static org.fleettrack.vehicle.VehicleStatus.ACTIVE;

@ApplicationScoped
public class TrackingService {

    @Inject
    TrackingRepository trackingRepository;
    @Inject
    TrackingMapper trackingMapper;
    @Inject
    VehicleRepository vehicleRepository;

    public Long saveTracking(TrackingRequest request) {
        Vehicle vehicle = vehicleRepository.findByIdOptional(request.vehicleId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vehicle with ID: " + request.vehicleId() + " does not exist!"
                ));

        if (vehicle.getStatus() != ACTIVE) {
            throw new VehicleNotActiveException("Vehicle must be active to save tracking!");
        }

        return trackingRepository.save(trackingMapper.toTrackingEntity(request, vehicle));
    }

    public TrackingResponse findLastTrackingByVehicleId(Long vehicleId) {
        vehicleRepository.findByIdOptional(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vehicle with ID: " + vehicleId + " does not exist!"
                ));

        Tracking lastTracking = trackingRepository.findLastTrackingByVehicleId(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No tracking data found for vehicle with ID: " + vehicleId + "!"
                ));

        return trackingMapper.toTrackingResponse(lastTracking, vehicleId);
    }
}
