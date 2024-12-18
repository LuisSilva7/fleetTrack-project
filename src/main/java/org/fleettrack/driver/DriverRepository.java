package org.fleettrack.driver;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class DriverRepository implements PanacheRepository<Driver> {

    public Long saveDriver(Driver driver) {
        persist(driver);
        return driver.getId();
    }

    public Optional<Driver> findByDrivingLicenseNumber(String drivingLicenseNumber) {
        return find("drivingLicenseNumber", drivingLicenseNumber).firstResultOptional();
    }
}
