package org.fleettrack.vehicle;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fleettrack.common.ApiResponse;
import org.fleettrack.common.PageResponse;

@Path("/api/v1/vehicles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehicleController {

    @Inject
    VehicleService vehicleService;

    @POST
    @Transactional
    public Response createVehicle(@Valid VehicleRequest request) {
        Long vehicleId = vehicleService.createVehicle(request);

        return Response.status(Response.Status.CREATED).entity(new ApiResponse<>(
                "Vehicle with plate: " + request.plate() + " created successfully!",
                vehicleId)).build();
    }

    @GET
    public Response findAllVehicles(
            @QueryParam("pageNumber") int pageNumber,
            @QueryParam("pageSize") int pageSize
    ) {
        PageResponse<VehicleResponse> response = vehicleService.findAllVehicles(pageNumber, pageSize);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Vehicles obtained successfully!",
                response)).build();
    }

    @GET
    @Path("/{id}")
    public Response findVehicleById(
            @PathParam("id") Long vehicleId
    ) {
        VehicleResponse response = vehicleService.findVehicleById(vehicleId);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Vehicle with ID: " + vehicleId + " obtained successfully!",
                response)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateVehicle(
            @PathParam("id") Long vehicleId,
            VehicleRequest request
    ) {
        VehicleResponse response = vehicleService.updateVehicle(vehicleId, request);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Vehicle with ID: " + vehicleId + " updated successfully!",
                response)).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response updateVehicleStatus(
            @PathParam("id") Long vehicleId,
            VehicleStatusRequest request
    ) {
        vehicleService.updateVehicleStatus(vehicleId, request);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Vehicle status with ID: " + vehicleId + " updated successfully!",
                null)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteVehicle(
            @PathParam("id") Long vehicleId
    ) {
        vehicleService.deleteVehicle(vehicleId);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Vehicle with ID: " + vehicleId + " deleted successfully!",
                null)).build();
    }
}
