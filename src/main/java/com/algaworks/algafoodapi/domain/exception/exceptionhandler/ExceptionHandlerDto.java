package com.algaworks.algafoodapi.domain.exception.exceptionhandler;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ExceptionHandlerDto {

    @Setter(AccessLevel.NONE)
    private Date timestamp = new Date();

    private String resource;

    private String method;

    private int statusCode;

    private String reasonPhrase;

    private boolean handled;

    private String title;

    @Setter(AccessLevel.NONE)
    private List<String> details = new ArrayList<>();

}
