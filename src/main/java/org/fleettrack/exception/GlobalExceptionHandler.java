package org.fleettrack.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.fleettrack.common.ApiResponse;
import org.fleettrack.exception.custom.ResourceAlreadyExistsException;
import org.fleettrack.exception.custom.ResourceNotFoundException;
import org.fleettrack.exception.custom.VehicleNotActiveException;

import java.util.stream.Collectors;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.*;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        ApiResponse<?> apiResponse = new ApiResponse<>(exception.getMessage(), null);

        if (exception instanceof ResourceNotFoundException) {
            return Response.status(NOT_FOUND)
                    .entity(apiResponse)
                    .type(APPLICATION_JSON)
                    .build();
        }

        if (exception instanceof ResourceAlreadyExistsException) {
            return Response.status(CONFLICT)
                    .entity(apiResponse)
                    .type(APPLICATION_JSON)
                    .build();
        }

        if (exception instanceof VehicleNotActiveException) {
            return Response.status(CONFLICT)
                    .entity(apiResponse)
                    .type(APPLICATION_JSON)
                    .build();
        }

        if (exception instanceof IllegalArgumentException) {
            return Response.status(BAD_REQUEST)
                    .entity(apiResponse)
                    .type(APPLICATION_JSON)
                    .build();
        }

        if (exception instanceof ConstraintViolationException) {
            String errorMessage = ((ConstraintViolationException) exception).getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            apiResponse = new ApiResponse<>(errorMessage, null);
            return Response.status(BAD_REQUEST)
                    .entity(apiResponse)
                    .type(APPLICATION_JSON)
                    .build();
        }

        if (exception instanceof WebApplicationException webEx) {
            return Response.status(webEx.getResponse().getStatus())
                    .entity(apiResponse)
                    .type(APPLICATION_JSON)
                    .build();
        }

        return Response.status(INTERNAL_SERVER_ERROR)
                .entity(apiResponse)
                .type(APPLICATION_JSON)
                .build();
    }
}
