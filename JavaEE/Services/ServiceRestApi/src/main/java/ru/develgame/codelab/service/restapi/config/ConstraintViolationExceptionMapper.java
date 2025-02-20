package ru.develgame.codelab.service.restapi.config;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ru.develgame.codelab.service.restapi.dto.ErrorResponses;

import java.util.Set;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(final ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponses(constraintViolations.stream()
                        .map(t -> t.getMessage())
                        .toList()))
                .build();
    }
}
