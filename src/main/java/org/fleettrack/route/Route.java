package org.fleettrack.route;

import jakarta.persistence.*;
import lombok.*;
import org.fleettrack.vehicle.Vehicle;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originStreet;
    private String originCity;
    private String destinationStreet;
    private String destinationCity;
    private double distance;
    private String estimatedTime;
    private boolean tolls;

    @ManyToMany(mappedBy = "routes")
    private List<Vehicle> vehicles;

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
}
