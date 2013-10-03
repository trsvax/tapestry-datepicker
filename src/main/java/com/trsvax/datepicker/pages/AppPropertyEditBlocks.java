package com.trsvax.datepicker.pages;

import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.FieldValidator;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.MixinClasses;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.services.PropertyEditContext;

import com.trsvax.datepicker.mixins.JQueryDatePIcker;

public class AppPropertyEditBlocks {
	
	@Property
	@Environmental
	private PropertyEditContext context;
	
	@MixinClasses(value=JQueryDatePIcker.class)
	@Component(parameters =
		{ "value=context.propertyValue", "label=prop:context.label",
			"translate=prop:dateTranslator", "validate=prop:dateValidator",
			"clientId=prop:context.propertyId", "annotationProvider=context" })
	private TextField  dateField;


	public FieldValidator<?> getDateValidator() {
		return context.getValidator(dateField);
	}

	public FieldTranslator<?> getDateTranslator() {
		return context.getTranslator(dateField);
	}
	
	@MixinClasses(value=JQueryDatePIcker.class)
	@Component(parameters =
		{ "value=context.propertyValue", "label=prop:context.label",
			"translate=prop:calendarTranslator", "validate=prop:calendarValidator",
			"clientId=prop:context.propertyId", "annotationProvider=context" })
	private TextField  calendarField;


	public FieldValidator<?> getCalendarValidator() {
		return context.getValidator(calendarField);
	}

	public FieldTranslator<?> getCalendarTranslator() {
		return context.getTranslator(calendarField);
	}

}
