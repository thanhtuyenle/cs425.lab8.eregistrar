package edu.mum.cs.cs425.lab8.eregistrar.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueStudentNumberValidator.class)
public @interface UniqueStudentNumber {
    String message() default "{edu.mum.cs.cs425.customvalidators.uniquestudentnumber.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
