package org.fleettrack.tracking;

import jakarta.persistence.*;
import lombok.*;
import org.fleettrack.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Tracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latitude;
    private double longitude;
    private LocalDateTime dateTime;

    @ManyToMany(mappedBy = "trackings")
    private List<Vehicle> vehicles;
}
