package com.lhxh.demo.validation;

import com.lhxh.demo.anno.State;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String>{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null)
            return false;
        if(value.equals("已发布")||value.equals("草稿"))
            return true;

        return false;
    }
}
