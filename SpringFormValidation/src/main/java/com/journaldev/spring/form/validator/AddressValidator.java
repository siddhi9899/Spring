package com.journaldev.spring.form.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;

import com.journaldev.spring.form.controllers.CustomerController;

public class AddressValidator implements ConstraintValidator<Address, Object> {
	
	private static final Logger logger = LoggerFactory
			.getLogger(AddressValidator.class);
	
	private String field;
    private String fieldMatch;
	
	@Override
	public void initialize(Address constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }
	
	@Override
	public boolean isValid(Object address, ConstraintValidatorContext ctx) {
		Object fieldValue = new BeanWrapperImpl(address).getPropertyValue(field);
		Object fieldMatchValue = new BeanWrapperImpl(address).getPropertyValue(fieldMatch);
		logger.info("AddressValidator-->fieldValue=="+fieldValue+"fieldMatchValue=="+fieldMatchValue);        
		        if (fieldValue != null) {
		            return fieldValue.equals(fieldMatchValue);
		        } else {
		            return fieldMatchValue == null;
		        }
	}
}
