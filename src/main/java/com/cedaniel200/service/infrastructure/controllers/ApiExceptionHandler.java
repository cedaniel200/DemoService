package com.cedaniel200.service.infrastructure.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgument(IllegalArgumentException exception,
                                               HttpServletRequest request) {
        return problem(HttpStatus.BAD_REQUEST, "Solicitud inválida", exception.getMessage(), request);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ProblemDetail handleMissingParameter(MissingServletRequestParameterException exception,
                                                HttpServletRequest request) {
        String detail = "Falta el parámetro obligatorio: " + exception.getParameterName();
        return problem(HttpStatus.BAD_REQUEST, "Solicitud inválida", detail, request);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ProblemDetail handleIllegalState(IllegalStateException exception,
                                            HttpServletRequest request) {
        return problem(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno", exception.getMessage(), request);
    }

    @ExceptionHandler(DataAccessException.class)
    public ProblemDetail handleDataAccess(DataAccessException exception,
                                          HttpServletRequest request) {
        return problem(HttpStatus.SERVICE_UNAVAILABLE, "Servicio no disponible",
                "No se pudo acceder a la base de datos", request);
    }

    private ProblemDetail problem(HttpStatus status, String title, String detail,
                                  HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setTitle(title);
        problem.setInstance(URI.create(request.getRequestURI()));
        return problem;
    }
}
