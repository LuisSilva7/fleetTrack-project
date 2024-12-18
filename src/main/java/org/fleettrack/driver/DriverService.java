package org.fleettrack.driver;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DriverService {

    @Inject
    DriverRepository driverRepository;
    @Inject
    DriverMapper driverMapper;
}
