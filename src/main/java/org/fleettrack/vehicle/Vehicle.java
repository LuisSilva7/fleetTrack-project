package org.fleettrack.vehicle;

import jakarta.persistence.*;
import lombok.*;
import org.fleettrack.driver.Driver;
import org.fleettrack.maintenance.Maintenance;
import org.fleettrack.route.Route;
import org.fleettrack.tracking.Tracking;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String plate;
    private String model;
    private int manufactureYear;
    private int currentKm;
    private int capacity;
    private String acquisitionDate;
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Driver> driver;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Maintenance> maintenances;

    @ManyToMany
    @JoinTable(
            name = "vehicle_route",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id")
    )
    private List<Route> routes;

    @ManyToMany
    @JoinTable(
            name = "vehicle_tracking",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "tracking_id")
    )
    private List<Tracking> trackings;
}
