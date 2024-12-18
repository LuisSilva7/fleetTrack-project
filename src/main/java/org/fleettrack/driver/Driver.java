package org.fleettrack.driver;

import jakarta.persistence.*;
import lombok.*;
import org.fleettrack.vehicle.Vehicle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String drivingLicenseNumber;
    private String drivingLicenseExpirationDate;
    private String drivingLicenseCategory;
    private String phoneNumber;
    private String email;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
