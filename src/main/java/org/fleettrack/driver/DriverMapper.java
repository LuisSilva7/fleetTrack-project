package org.fleettrack.driver;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fleettrack.vehicle.Vehicle;
import org.fleettrack.vehicle.VehicleMapper;
import org.fleettrack.vehicle.VehicleResponse;

@ApplicationScoped
public class DriverMapper {

    @Inject
    VehicleMapper vehicleMapper;

    public Driver toDriverEntity(DriverRequest request, Vehicle vehicle) {
        return Driver.builder()
                .name(request.name())
                .drivingLicenseNumber(request.drivingLicenseNumber())
                .drivingLicenseExpirationDate(request.drivingLicenseExpirationDate())
                .drivingLicenseCategory(request.drivingLicenseCategory())
                .phoneNumber(request.phoneNumber())
                .email(request.email())
                .vehicle(vehicle)
                .build();
    }

    public DriverResponse toDriverResponse(Driver driver) {
        VehicleResponse vehicleResponse = vehicleMapper.toVehicleResponse(driver.getVehicle());

        return new DriverResponse(
                driver.getId(),
                driver.getName(),
                driver.getDrivingLicenseNumber(),
                driver.getDrivingLicenseExpirationDate(),
                driver.getDrivingLicenseCategory(),
                driver.getPhoneNumber(),
                driver.getEmail(),
                vehicleResponse
        );
    }
}
