package org.fleettrack.vehicle;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.fleettrack.common.PageResponse;
import org.fleettrack.exception.custom.ResourceAlreadyExistsException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@ApplicationScoped
public class VehicleService {

    @Inject
    VehicleRepository vehicleRepository;
    @Inject
    VehicleMapper vehicleMapper;

    public Long createVehicle(VehicleRequest request) {
        if(vehicleRepository.findByPlate(request.plate()).isPresent()) {
            throw new ResourceAlreadyExistsException(
                    "Vehicle with plate: " + request.plate() + " already exists");
        }

        return vehicleRepository.saveVehicle(
                vehicleMapper.toVehicleEntity(request)
        );
    }

    public PageResponse<VehicleResponse> findAllVehicles(int pageNumber, int pageSize) {
        if (pageNumber < 0) {
            pageNumber = 0;
        }

        if (pageSize <= 0) {
            pageSize = 10;
        }

        List<Vehicle> vehicles = vehicleRepository.findAll()
                .page(Page.of(pageNumber, pageSize))
                .list();

        long totalVehicles = vehicleRepository.count();
        int totalPages = (int) Math.ceil((double) totalVehicles / pageSize);

        return new PageResponse<>(
                vehicles.stream().map(vehicleMapper::toVehicleResponse).toList(),
                pageNumber,
                pageSize,
                totalVehicles,
                totalPages,
                pageNumber == 0,
                pageNumber == totalPages - 1
        );
    }

    public VehicleResponse findVehicleById(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findByIdOptional(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vehicle with ID: " + vehicleId + " does not exist!"));

        return vehicleMapper.toVehicleResponse(vehicle);
    }

    public VehicleResponse updateVehicle(Long vehicleId, VehicleRequest request) {
        Vehicle vehicle = vehicleRepository.findByIdOptional(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vehicle with ID: " + vehicleId + " does not exist!"));

        if(vehicleRepository.findByPlate(request.plate()).isPresent()) {
            throw new ResourceAlreadyExistsException(
                    "Vehicle with plate: " + request.plate() + " already exists!");
        }

        if (request.plate() != null) {
            if(request.plate().length() != 6 ) {
                throw new IllegalArgumentException("Plate must be exactly 6 characters!");
            }
            vehicle.setPlate(request.plate());
        }
        if (request.model() != null) {
            vehicle.setModel(request.model());
        }
        if (request.manufactureYear() != null) {
            if(request.manufactureYear() < 1995 || request.manufactureYear() > 2030) {
                throw new IllegalArgumentException("Manufacture year must be between 1995 and 2030!");
            }
            vehicle.setManufactureYear(request.manufactureYear());
        }
        if (request.currentKm() != null) {
            if(request.currentKm() < 0) {
                throw new IllegalArgumentException("Current Km must be higher than 0!");
            }
            vehicle.setCurrentKm(request.currentKm());
        }
        if (request.capacity() != null) {
            if(request.capacity() < 1) {
                throw new IllegalArgumentException("Capacity must be higher than 1!");
            }
            vehicle.setCapacity(request.capacity());
        }
        if (request.acquisitionDate() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate.parse(request.acquisitionDate(), formatter);
                vehicle.setAcquisitionDate(request.acquisitionDate());
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Acquisition date must be in the format yyyy-MM-dd!");
            }
            vehicle.setAcquisitionDate(request.acquisitionDate());
        }

        vehicleRepository.persist(vehicle);

        return vehicleMapper.toVehicleResponse(vehicle);
    }

    public void updateVehicleStatus(Long vehicleId, VehicleStatusRequest request) {
        Vehicle vehicle = vehicleRepository.findByIdOptional(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vehicle with ID: " + vehicleId + " does not exist!"));

        vehicle.setStatus(VehicleStatus.fromString(request.status()));
        vehicleRepository.persist(vehicle);
    }

    public void deleteVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findByIdOptional(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vehicle with ID: " + vehicleId + " does not exist!"));

        vehicleRepository.delete(vehicle);
    }
}
