package org.fleettrack.config;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.fleettrack.driver.Driver;
import org.fleettrack.driver.DriverRepository;
import org.fleettrack.vehicle.Vehicle;
import org.fleettrack.vehicle.VehicleRepository;
import org.fleettrack.vehicle.VehicleStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class DataInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);

    @Inject
    VehicleRepository vehicleRepository;
    @Inject
    DriverRepository driverRepository;

    @Transactional
    public void init(@Observes StartupEvent ev) {
        if (vehicleRepository.count() == 0) {
            LOGGER.info("Inserting initial data into the database...");

            List<Vehicle> vehicles = List.of(
                    Vehicle.builder()
                            .plate("13AS91")
                            .model("Toyota Corolla")
                            .manufactureYear(2020)
                            .currentKm(50000)
                            .capacity(5)
                            .acquisitionDate("2022-05-15")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("10LM22")
                            .model("Honda Civic")
                            .manufactureYear(2018)
                            .currentKm(75000)
                            .capacity(5)
                            .acquisitionDate("2020-03-10")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("19CC02")
                            .model("Ford Focus")
                            .manufactureYear(2021)
                            .currentKm(20000)
                            .capacity(5)
                            .acquisitionDate("2023-01-20")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("75ZX34")
                            .model("Chevrolet Malibu")
                            .manufactureYear(2019)
                            .currentKm(62000)
                            .capacity(5)
                            .acquisitionDate("2021-08-11")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("42KL77")
                            .model("Nissan Sentra")
                            .manufactureYear(2017)
                            .currentKm(82000)
                            .capacity(5)
                            .acquisitionDate("2019-07-23")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("33AA99")
                            .model("Volkswagen Jetta")
                            .manufactureYear(2022)
                            .currentKm(15000)
                            .capacity(5)
                            .acquisitionDate("2023-02-14")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("60PY10")
                            .model("Subaru Impreza")
                            .manufactureYear(2020)
                            .currentKm(34000)
                            .capacity(5)
                            .acquisitionDate("2022-04-09")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("89TR12")
                            .model("Mazda3")
                            .manufactureYear(2016)
                            .currentKm(91000)
                            .capacity(5)
                            .acquisitionDate("2018-09-30")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("51VU88")
                            .model("Hyundai Elantra")
                            .manufactureYear(2015)
                            .currentKm(105000)
                            .capacity(5)
                            .acquisitionDate("2017-06-12")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("18BN44")
                            .model("Kia Optima")
                            .manufactureYear(2019)
                            .currentKm(47000)
                            .capacity(5)
                            .acquisitionDate("2021-12-05")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("45GH12")
                            .model("Mercedes Sprinter")
                            .manufactureYear(2021)
                            .currentKm(15000)
                            .capacity(12)
                            .acquisitionDate("2022-08-15")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("67RT89")
                            .model("Ford Transit")
                            .manufactureYear(2020)
                            .currentKm(20000)
                            .capacity(10)
                            .acquisitionDate("2021-04-22")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("34YU56")
                            .model("Toyota HiAce")
                            .manufactureYear(2022)
                            .currentKm(10000)
                            .capacity(14)
                            .acquisitionDate("2023-01-10")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("12JK34")
                            .model("Volkswagen Crafter")
                            .manufactureYear(2021)
                            .currentKm(12000)
                            .capacity(15)
                            .acquisitionDate("2022-05-08")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("89PL21")
                            .model("Renault Master")
                            .manufactureYear(2019)
                            .currentKm(25000)
                            .capacity(16)
                            .acquisitionDate("2020-11-19")
                            .status(VehicleStatus.ACTIVE)
                            .build(),
                    Vehicle.builder()
                            .plate("78QW90")
                            .model("Hyundai H350")
                            .manufactureYear(2020)
                            .currentKm(18000)
                            .capacity(13)
                            .acquisitionDate("2021-07-15")
                            .status(VehicleStatus.ACTIVE)
                            .build()
            );

            vehicleRepository.persist(vehicles);

            List<Driver> drivers = List.of(
                    Driver.builder()
                            .name("John Doe")
                            .drivingLicenseNumber("D1234567")
                            .drivingLicenseExpirationDate("2025-12-31")
                            .drivingLicenseCategory("B")
                            .phoneNumber("+11234567890")
                            .email("john.doe@example.com")
                            .vehicle(vehicles.get(0))
                            .build(),
                    Driver.builder()
                            .name("Jane Smith")
                            .drivingLicenseNumber("D7654321")
                            .drivingLicenseExpirationDate("2024-07-20")
                            .drivingLicenseCategory("C1")
                            .phoneNumber("+11234567891")
                            .email("jane.smith@example.com")
                            .vehicle(vehicles.get(1))
                            .build(),
                    Driver.builder()
                            .name("Michael Johnson")
                            .drivingLicenseNumber("D1112233")
                            .drivingLicenseExpirationDate("2026-01-01")
                            .drivingLicenseCategory("D")
                            .phoneNumber("+11234567892")
                            .email("michael.johnson@example.com")
                            .vehicle(vehicles.get(2))
                            .build(),
                    Driver.builder()
                            .name("Emily Davis")
                            .drivingLicenseNumber("D4455667")
                            .drivingLicenseExpirationDate("2025-10-10")
                            .drivingLicenseCategory("B")
                            .phoneNumber("+11234567893")
                            .email("emily.davis@example.com")
                            .vehicle(vehicles.get(3))
                            .build(),
                    Driver.builder()
                            .name("Chris Brown")
                            .drivingLicenseNumber("D9988776")
                            .drivingLicenseExpirationDate("2027-03-15")
                            .drivingLicenseCategory("C1")
                            .phoneNumber("+11234567894")
                            .email("chris.brown@example.com")
                            .vehicle(vehicles.get(4))
                            .build()
            );

            driverRepository.persist(drivers);

            LOGGER.info("Initial data successfully inserted.");
        } else {
            LOGGER.info("Data already exist in the database. Skipping initialization.");
        }
    }

}

