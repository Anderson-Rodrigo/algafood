package com.algaworks.algafoodapi.core.validation;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {ValorZeroIncluiDescricaoValidator.class})
public @interface ValorZeroIncluiDescricao {

    @OverridesAttribute(constraint = PositiveOrZero.class, name="message")
    String message() default "descrição obrigatoria invalida";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String valorField();

    String descricaoField();

    String descricaoObrigatoria();

}
