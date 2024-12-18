package org.fleettrack.vehicle;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class VehicleMapper {

    public Vehicle toVehicleEntity(VehicleRequest request) {
        return Vehicle.builder()
                .plate(request.plate())
                .model(request.model())
                .manufactureYear(request.manufactureYear())
                .currentKm(request.currentKm())
                .capacity(request.capacity())
                .acquisitionDate(request.acquisitionDate())
                .status(VehicleStatus.ACTIVE)
                .build();
    }

    public VehicleResponse toVehicleResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getPlate(),
                vehicle.getModel(),
                vehicle.getManufactureYear(),
                vehicle.getCurrentKm(),
                vehicle.getCapacity(),
                vehicle.getAcquisitionDate(),
                vehicle.getStatus().getStatus()
        );
    }
}
