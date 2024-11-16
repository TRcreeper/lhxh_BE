package com.lhxh.demo.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.lhxh.demo.validation.ProgressStateValidation;
import com.lhxh.demo.validation.StateValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {ProgressStateValidation.class})
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ProgressState {
    //提供校验失败后的提示信息
    String message() default "progressState参数的值只能是'已结束'或'未开始'";
    //指定分组
	Class<?>[] groups() default { };
    //负载，提供附加信息
	Class<? extends Payload>[] payload() default { };
}
