package org.fleettrack.vehicle;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class VehicleRepository implements PanacheRepository<Vehicle> {

    public Long saveVehicle(Vehicle vehicle) {
        persist(vehicle);
        return vehicle.getId();
    }

    public Optional<Vehicle> findByPlate(String plate) {
        return find("plate", plate).firstResultOptional();
    }
}
