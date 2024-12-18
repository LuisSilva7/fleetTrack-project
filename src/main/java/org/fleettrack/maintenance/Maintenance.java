package org.fleettrack.maintenance;

import jakarta.persistence.*;
import lombok.*;
import org.fleettrack.vehicle.Vehicle;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String maintenanceType;
    private Date date;
    private double cost;
    private String description;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;
}
