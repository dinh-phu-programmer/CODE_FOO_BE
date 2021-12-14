package com.codefoo.app.validation;


import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsPasswordMatchValidator implements ConstraintValidator<FieldPasswordMatch,Object> {
    private String field;
    private String fieldMatch;
    @Override
    public void initialize(FieldPasswordMatch constraintAnnotation) {
        this.field=constraintAnnotation.field();
        this.fieldMatch=constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue=new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue= new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
        if (fieldValue != null){
            return fieldValue.equals(fieldMatchValue);
        }else{
            return fieldMatch==null;
        }

    }
}
