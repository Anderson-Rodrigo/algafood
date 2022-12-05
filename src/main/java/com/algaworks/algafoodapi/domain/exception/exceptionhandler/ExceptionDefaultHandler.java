package com.algaworks.algafoodapi.domain.exception.exceptionhandler;

import lombok.extern.java.Log;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

@Log
@Order(1)
@RestControllerAdvice
public class ExceptionDefaultHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionHandlerDto> handleDefaultException(HttpServletRequest request, MethodArgumentNotValidException ex) throws Exception {
        if (ex.getClass().getAnnotation(ResponseStatus.class) != null) {
            throw ex;
        }

        ExceptionHandlerDto response = new ExceptionHandlerDto();
        response.setResource(request.getRequestURI());
        response.setMethod(request.getMethod());
        response.setTitle("Exceção em tempo de execução do sistema");

        HttpStatus status;

        ExceptionMapping exceptionMapping = ex.getClass().getAnnotation(ExceptionMapping.class);


	BindingResult bindingResult = ex.getBindingResult();
	List<FieldError> errors = bindingResult.getFieldErrors();

        if (exceptionMapping != null) {
            status = exceptionMapping.statusCode();
            response.setStatusCode(status.value());
            response.setReasonPhrase(status.getReasonPhrase());
            List<String> messages = Arrays.asList(ex.getMessage().split("\n"));
            response.getDetails().addAll(messages);
        } else {
            status = HttpStatus.BAD_REQUEST;
            response.setStatusCode(status.value());
            response.setReasonPhrase(status.getReasonPhrase());
            if(!ex.getMessage().isEmpty()){
                List<String> messages = Arrays.asList(ex.getMessage().split("default message"));
                response.getDetails().addAll(messages);
            }else{
                response.getDetails().add("Não foi possível obter detalhes do erro ocorrido");
            }
        }
        ex.printStackTrace();
        log.log(Level.SEVERE, ex.getMessage());

        return new ResponseEntity<>(response, status);
    }

    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}