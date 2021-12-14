package com.codefoo.app.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy=FieldsPasswordMatchValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldPasswordMatch {
    String message() default "Fields values don't match";
    String field();
    String fieldMatch();
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List{
        FieldPasswordMatch[] value();
    }
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
