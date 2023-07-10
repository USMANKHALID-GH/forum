package com.usman.forum.Annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Documented
@Constraint(validatedBy = RoleTypeValidation.class)
public @interface RoleTypeAnnotation {

    String message() default "The Length Must Be More Than 11";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
