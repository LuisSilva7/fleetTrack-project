package org.fleettrack.cost;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fleettrack.common.ApiResponse;

@Path("/api/v1/costs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CostController {

    @Inject
    CostService costService;

    @GET
    @Path("/vehicle/{id}")
    public Response findVehicleCostById(
            @PathParam("id") Long vehicleId
    ) {
        double totalCost = costService.findVehicleCostById(vehicleId);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Vehicle with ID: " + vehicleId + " cost obtained successfully!",
                totalCost)).build();
    }
}
