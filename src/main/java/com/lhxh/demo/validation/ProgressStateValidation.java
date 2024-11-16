package com.lhxh.demo.validation;

import com.lhxh.demo.anno.ProgressState;
import com.lhxh.demo.anno.State;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProgressStateValidation implements ConstraintValidator<ProgressState,String>{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null)
            return false;
        if(value.equals("未开始")||value.equals("已结束"))
            return true;

        return false;
    }
}
