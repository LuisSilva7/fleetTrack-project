package org.fleettrack.route;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.fleettrack.common.ApiResponse;
import org.fleettrack.common.PageResponse;

@Path("/api/v1/routes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RouteController {

    @Inject
    RouteService routeService;

    @POST
    @Transactional
    public Response createRoute(@Valid RouteRequest request) {
        Long routeId = routeService.createRoute(request);

        return Response.status(Response.Status.CREATED).entity(new ApiResponse<>(
                "Route with Id: " + routeId + " created successfully!",
                routeId)).build();
    }

    @GET
    public Response findAllDrivers(
            @QueryParam("pageNumber") int pageNumber,
            @QueryParam("pageSize") int pageSize
    ) {
        PageResponse<RouteResponse> response = routeService.findAllRoutes(pageNumber, pageSize);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Routes obtained successfully!",
                response)).build();
    }

    @GET
    @Path("/{id}")
    public Response findRouteById(
            @PathParam("id") Long routeId
    ) {
        RouteResponse response = routeService.findRouteById(routeId);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Route with ID: " + routeId + " obtained successfully!",
                response)).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response updateRouteVehicle(
            @PathParam("id") Long routeId,
            RouteVehicleRequest request
    ) {
        routeService.updateRouteVehicle(routeId, request);

        return Response.status(Response.Status.OK).entity(new ApiResponse<>(
                "Route vehicle with ID: " + request.vehicleId() + " updated successfully!",
                null)).build();
    }
}
