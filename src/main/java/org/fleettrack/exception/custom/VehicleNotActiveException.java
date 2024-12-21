package org.fleettrack.exception.custom;

public class VehicleNotActiveException extends RuntimeException {
    public VehicleNotActiveException(String message) {
        super(message);
    }
}
