package org.fleettrack.maintenance;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fleettrack.common.ApiResponse;
import org.fleettrack.common.PageResponse;

@Path("/api/v1/maintenances")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MaintenanceController {

    @Inject
    MaintenanceService maintenanceService;

    @POST
    @Transactional
    public Response createMaintenance(@Valid MaintenanceRequest request) {
        Long maintenanceId = maintenanceService.createMaintenance(request);

        return Response.status(Response.Status.CREATED).entity(new ApiResponse<>(
                "Maintenance for vehicle with ID: " + request.vehicleId() + " created successfully!",
                maintenanceId)).build();
    }

    @GET
    public Response findAllMaintenances(
            @QueryParam("pageNumber") int pageNumber,
            @QueryParam("pageSize") int pageSize
    ) {
        PageResponse<MaintenanceResponse> response = maintenanceService.findAllMaintenances(pageNumber, pageSize);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Maintenances obtained successfully!",
                response)).build();
    }

    @GET
    @Path("/{id}")
    public Response findMaintenanceById(
            @PathParam("id") Long maintenanceId
    ) {
        MaintenanceResponse response = maintenanceService.findMaintenanceById(maintenanceId);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Maintenance with ID: " + maintenanceId + " obtained successfully!",
                response)).build();
    }
}
