package com.totalplay.empleados.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FileSizeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSize {
    String message() default "El tamaño del archivo no puede exceder los {max} bytes";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    long max();
}
