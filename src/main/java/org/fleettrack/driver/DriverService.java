package org.fleettrack.driver;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fleettrack.common.PageResponse;
import org.fleettrack.exception.custom.ResourceAlreadyExistsException;
import org.fleettrack.exception.custom.ResourceNotFoundException;
import org.fleettrack.vehicle.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DriverService {

    @Inject
    DriverRepository driverRepository;
    @Inject
    DriverMapper driverMapper;
    @Inject
    VehicleRepository vehicleRepository;

    public Long createDriver(DriverRequest request) {
        if(driverRepository.findByDrivingLicenseNumber(request.drivingLicenseNumber()).isPresent()) {
            throw new ResourceAlreadyExistsException(
                    "Driving license number: " + request.drivingLicenseNumber() + " already exists!");
        }

        Optional<Vehicle> vehicle = vehicleRepository.findByIdOptional(request.vehicleId());

        Driver driver = driverMapper.toDriverEntity(request, vehicle.orElse(null));

        return driverRepository.saveDriver(driver);
    }

    public PageResponse<DriverResponse> findAllDrivers(int pageNumber, int pageSize) {
        if (pageNumber < 0) {
            pageNumber = 0;
        }

        if (pageSize <= 0) {
            pageSize = 10;
        }

        List<Driver> drivers = driverRepository.findAll()
                .page(Page.of(pageNumber, pageSize))
                .list();

        long totalDrivers = driverRepository.count();
        int totalPages = (int) Math.ceil((double) totalDrivers / pageSize);

        return new PageResponse<>(
                drivers.stream().map(driverMapper::toDriverResponse).toList(),
                pageNumber,
                pageSize,
                totalDrivers,
                totalPages,
                pageNumber == 0,
                pageNumber == totalPages - 1
        );
    }

    public DriverResponse findDriverById(Long driverId) {
        Driver driver = driverRepository.findByIdOptional(driverId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Driver with ID: " + driverId + " does not exist!"));

        return driverMapper.toDriverResponse(driver);
    }

    public DriverResponse updateDriver(Long driverId, DriverRequest request) {
        Driver driver = driverRepository.findByIdOptional(driverId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Driver with ID: " + driverId + " does not exist!"));

        if(driverRepository.findByDrivingLicenseNumber(request.drivingLicenseNumber()).isPresent()) {
            throw new ResourceAlreadyExistsException(
                    "Driving license number: " + request.drivingLicenseNumber() + " already exists!");
        }

        if (request.name() != null) {
            if(request.name().length() < 2 || request.name().length() > 50) {
                throw new IllegalArgumentException("Name must be between 2 and 50 characters!");
            }
            driver.setName(request.name());
        }
        if (request.drivingLicenseNumber() != null) {
            if(request.drivingLicenseNumber().length() < 5 || request.drivingLicenseNumber().length() > 20) {
                throw new IllegalArgumentException("Driving license number must be between 5 and 20 characters!");
            }
            driver.setDrivingLicenseNumber(request.drivingLicenseNumber());
        }
        if (request.drivingLicenseExpirationDate() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate.parse(request.drivingLicenseExpirationDate(), formatter);
                driver.setDrivingLicenseExpirationDate(request.drivingLicenseExpirationDate());
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException(
                        "Driving license expiration date must be in the format yyyy-MM-dd!");
            }
            driver.setDrivingLicenseExpirationDate(request.drivingLicenseExpirationDate());
        }
        if (request.drivingLicenseCategory() != null) {
            if (!request.drivingLicenseCategory().matches("[A-Z]{1,3}")) {
                throw new IllegalArgumentException(
                        "Driving license category must contain 1 to 3 uppercase letters (e.g., 'B', 'C1')!"
                );
            }
            driver.setDrivingLicenseCategory(request.drivingLicenseCategory());
        }
        if (request.phoneNumber() != null) {
            if (!request.phoneNumber().matches("\\+?[0-9]{7,15}")) {
                throw new IllegalArgumentException(
                        "Phone number must contain only digits and can optionally start with a '+'"
                );
            }
            driver.setPhoneNumber(request.phoneNumber());
        }
        if (request.email() != null) {
            if (!request.email().matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                throw new IllegalArgumentException("Email must be a valid format");
            }
            driver.setEmail(request.email());
        }
        if (request.vehicleId() != null) {
            Optional<Vehicle> vehicle = vehicleRepository.findByIdOptional(request.vehicleId());
            driver.setVehicle(vehicle.orElse(null));
        }

        driverRepository.persist(driver);

        return driverMapper.toDriverResponse(driver);
    }

    public void updateDriverVehicle(Long driverId, DriverVehicleRequest request) {
        Driver driver = driverRepository.findByIdOptional(driverId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Driver with ID: " + driverId + " does not exist!"));

        Optional<Vehicle> vehicle = vehicleRepository.findByIdOptional(request.vehicleId());
        vehicle.ifPresent(driver::setVehicle);
        driverRepository.persist(driver);
    }

    public void deleteDriver(Long driverId) {
        Driver driver = driverRepository.findByIdOptional(driverId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Driver with ID: " + driverId + " does not exist!"));

        driverRepository.delete(driver);
    }
}
