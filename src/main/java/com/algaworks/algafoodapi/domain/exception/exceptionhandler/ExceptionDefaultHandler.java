package com.algaworks.algafoodapi.domain.exception.exceptionhandler;

import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.hibernate.PropertyValueException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Order(1)
@RestControllerAdvice
public class ExceptionDefaultHandler {

    @ResponseBody
    @ExceptionHandler(value = {Exception.class, RuntimeException.class, HttpMessageNotReadableException.class, PropertyBindingException.class})
    public ExceptionHandlerDto handleDefaultException(HttpServletRequest request, Exception ex, RuntimeException rn) throws Exception {
        if (ex.getClass().getAnnotation(ResponseStatus.class) != null) {
            throw ex;
        }

        ExceptionHandlerDto responseBody = new ExceptionHandlerDto();

        responseBody.setHandled(false);
        responseBody.setResource(request.getRequestURI());
        responseBody.setMethod(request.getMethod());
        responseBody.setReasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase());
        responseBody.setTitle("Exceção em tempo de execução do sistema");
        responseBody.setStatusCode(HttpStatus.BAD_REQUEST.value());

        Throwable properties = NestedExceptionUtils.getRootCause(ex);

        if (ex instanceof MethodArgumentNotValidException) {
            if (((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrorCount() > 0) {
                for (FieldError error : ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors()) {
                    responseBody.setTitle("Erro de validação de dados");
                    responseBody.getDetails().add("O valor do campo '".concat(error.getField()).concat("' ").concat(Objects.requireNonNull(error.getDefaultMessage())));
                    responseBody.setHandled(true);
                }
            }
        } else if (ex.getMessage().contains("Cannot deserialize value of type `java.util.Date`")) {
            responseBody.setTitle("Erro de validação de dados");
            responseBody.getDetails().add("Data inválida.");
            responseBody.setHandled(true);
        } else if (ex.getMessage().contains("Failed to convert value of type 'java.lang.String' to required type 'java.util.UUID'")) {
            responseBody.setTitle("Erro na nos parâmetros o sistema");
            responseBody.getDetails().add("Não foi possível interpretar o UUID.");
            responseBody.setHandled(true);
        } else if (ex.getCause().getMessage().contains("not-null property references a null or transient value")){
            responseBody.setTitle("O campo não pode ser nulo.");
            responseBody.getDetails().add(String.format("Não foi informado o campo %s", ((PropertyValueException) properties).getPropertyName()));
            responseBody.setHandled(true);
        }

        if (!responseBody.isHandled()) {
            responseBody.getDetails().add(ex.getMessage());
        }

        ex.printStackTrace();
        return responseBody;
    }

}
