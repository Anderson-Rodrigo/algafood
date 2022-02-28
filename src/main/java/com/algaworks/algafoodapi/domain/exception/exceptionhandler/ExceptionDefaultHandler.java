package com.algaworks.algafoodapi.domain.exception.exceptionhandler;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Order(1)
@RestControllerAdvice
public class ExceptionDefaultHandler {

    @ResponseBody
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ExceptionHandlerDto handleDefaultException(HttpServletRequest request, Exception ex) {
        ExceptionHandlerDto responseBody = new ExceptionHandlerDto();

        responseBody.setHandled(false);
        responseBody.setResource(request.getRequestURI());
        responseBody.setMethod(request.getMethod());
        responseBody.setReasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase());
        responseBody.setTitle("Exceção em tempo de execução do sistema");
        responseBody.setStatusCode(HttpStatus.BAD_REQUEST.value());

        if (ex instanceof MethodArgumentNotValidException && ((MethodArgumentNotValidException) ex).getBindingResult() != null) {
            if (((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrorCount() > 0) {
                for (FieldError error : ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors()) {
                    responseBody.setTitle("Erro de validação de dados");
                    responseBody.getDetails().add("O valor do campo '".concat(error.getField()).concat("' ").concat(error.getDefaultMessage()));
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
        }

        if (!responseBody.isHandled()) {
            responseBody.getDetails().add(ex.getMessage());
        }

        ex.printStackTrace();
        return responseBody;
    }

}
