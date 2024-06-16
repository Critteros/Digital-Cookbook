package dev.critteros.javaflavors.resolver;

import java.nio.file.AccessDeniedException;

import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;

@ControllerAdvice
public class GraphQLExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public GraphQLError handleAccessDeniedException(AccessDeniedException ex) {
        return GraphqlErrorBuilder.newError()
                .message(ex.getMessage())
                .errorType(ErrorType.FORBIDDEN)
                .build();
    }
}
