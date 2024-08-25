package com.generic.uploadservice.validators;

import org.springframework.core.env.PropertyResolver;

import com.generic.uploadservice.config.ContextProvider;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
/**
 * validates the size of numeric columns configured using DynamicNumColSize annotation
 */
public class DynamicNumColSizeValidator implements ConstraintValidator<DynamicNumColSize, Integer> {

	private PropertyResolver propertyResolver;
	private int max;

	@Override
	public void initialize(DynamicNumColSize configurableSize) {
		String maxProperty = configurableSize.maxProperty();
		this.propertyResolver = (PropertyResolver) ContextProvider.getBean(PropertyResolver.class);
		max = propertyResolver.getRequiredProperty(maxProperty, Integer.class);
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value < this.max;
	}

	public DynamicNumColSizeValidator() {
		super();
	}
}
