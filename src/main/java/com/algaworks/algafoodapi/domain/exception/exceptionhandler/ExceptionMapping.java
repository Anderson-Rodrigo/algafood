package com.algaworks.algafoodapi.domain.exception.exceptionhandler;

import org.springframework.http.HttpStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionMapping {

    HttpStatus statusCode() default HttpStatus.BAD_REQUEST;

    String message();

}
