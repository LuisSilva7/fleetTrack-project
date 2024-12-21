package org.fleettrack.driver;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fleettrack.common.ApiResponse;
import org.fleettrack.common.PageResponse;

@Path("/api/v1/drivers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DriverController {

    @Inject
    DriverService driverService;

    @POST
    @Transactional
    public Response createDriver(@Valid DriverRequest request) {
        Long driverId = driverService.createDriver(request);

        return Response.status(Response.Status.CREATED).entity(new ApiResponse<>(
                "Driver with name: " + request.name() + " created successfully!",
                driverId)).build();
    }

    @GET
    public Response findAllDrivers(
            @QueryParam("pageNumber") int pageNumber,
            @QueryParam("pageSize") int pageSize
    ) {
        PageResponse<DriverResponse> response = driverService.findAllDrivers(pageNumber, pageSize);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Drivers obtained successfully!",
                response)).build();
    }

    @GET
    @Path("/{id}")
    public Response findDriverById(
            @PathParam("id") Long driverId
    ) {
        DriverResponse response = driverService.findDriverById(driverId);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Driver with ID: " + driverId + " obtained successfully!",
                response)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateDriver(
            @PathParam("id") Long driverId,
            DriverRequest request
    ) {
        DriverResponse response = driverService.updateDriver(driverId, request);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Driver with ID: " + driverId + " updated successfully!",
                response)).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response updateDriverVehicle(
            @PathParam("id") Long driverId,
            DriverVehicleRequest request
    ) {
        driverService.updateDriverVehicle(driverId, request);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Driver vehicle with ID: " + request.vehicleId() + " updated successfully!",
                null)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteDriver(
            @PathParam("id") Long driverId
    ) {
        driverService.deleteDriver(driverId);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Driver with ID: " + driverId + " deleted successfully!",
                null)).build();
    }
}
