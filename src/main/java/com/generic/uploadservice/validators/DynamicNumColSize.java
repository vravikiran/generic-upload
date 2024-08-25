package com.generic.uploadservice.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
/**
 * Annotation used to configure column size validations for numeric fields dynamically configured
 * through application.properties file default message is used if not provided
 * when annotation is used
 */
@Target({ ElementType.FIELD })
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DynamicNumColSizeValidator.class)
@Documented
public @interface DynamicNumColSize {
	String message() default "size is not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String maxProperty() default "";

}
