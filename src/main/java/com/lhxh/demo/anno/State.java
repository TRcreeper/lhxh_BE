package com.lhxh.demo.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.lhxh.demo.validation.StateValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {StateValidation.class})
@Target({ FIELD })
@Retention(RUNTIME)
public @interface State {
    //提供校验失败后的提示信息
    String message() default "state参数的值只能是已发布或草稿";
    //指定分组
	Class<?>[] groups() default { };
    //负载，提供附加信息
	Class<? extends Payload>[] payload() default { };
}
