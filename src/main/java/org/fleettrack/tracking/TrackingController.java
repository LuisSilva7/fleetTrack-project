package org.fleettrack.tracking;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fleettrack.common.ApiResponse;

@Path("/api/v1/trackings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrackingController {

    @Inject
    TrackingService trackingService;

    @POST
    @Transactional
    public Response saveTracking(@Valid TrackingRequest request) {
        Long trackingId = trackingService.saveTracking(request);

        return Response.status(Response.Status.CREATED).entity(new ApiResponse<>(
                "Tracking with Id: " + trackingId + " saved successfully!",
                trackingId)).build();
    }

    @GET
    @Path("/{id}")
    public Response findLastTracking(@PathParam("id") Long vehicleId) {
        TrackingResponse response = trackingService.findLastTrackingByVehicleId(vehicleId);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Last tracking obtained successfully!",
                response)).build();
    }
}
