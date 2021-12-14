package com.codefoo.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailCustomValidator implements ConstraintValidator<EmailConstraint,String> {
    @Override
    public void initialize(EmailConstraint emailConstraint){
    }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext ctx){
        String emailPattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return email != null && email.matches(emailPattern);

    }

}
