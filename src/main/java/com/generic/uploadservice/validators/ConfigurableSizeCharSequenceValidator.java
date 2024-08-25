package com.generic.uploadservice.validators;

import org.springframework.core.env.PropertyResolver;

import com.generic.uploadservice.config.ContextProvider;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
/**
 *  validates the size of numeric columns configured using configurablesize annotation
 */
public class ConfigurableSizeCharSequenceValidator implements ConstraintValidator<ConfigurableSize, String> {
	private PropertyResolver propertyResolver;
	private int max;

	@Override
	public void initialize(ConfigurableSize configurableSize) {
		String maxProperty = configurableSize.maxProperty();
		this.propertyResolver = (PropertyResolver) ContextProvider.getBean(PropertyResolver.class);
		max = propertyResolver.getRequiredProperty(maxProperty, Integer.class);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		} else {
			return value.length() <= this.max;
		}
	}

	public ConfigurableSizeCharSequenceValidator() {
		super();
	}
}
