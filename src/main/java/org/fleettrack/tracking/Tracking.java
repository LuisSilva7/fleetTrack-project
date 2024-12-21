package org.fleettrack.tracking;

import jakarta.persistence.*;
import lombok.*;
import org.fleettrack.vehicle.Vehicle;

import java.time.LocalDateTime;

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
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "trackings", nullable = false)
    private Vehicle vehicle;
}
