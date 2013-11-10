package com.trsvax.datepicker.translators;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tapestry5.Field;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.internal.translator.AbstractTranslator;
import org.apache.tapestry5.services.FormSupport;

public class DateTranslator extends AbstractTranslator<Date> {

	private final String formatString;
	private final Object[] attributes;
	
	public DateTranslator(String name, String format, String messageKey, String... attributes) {
		super(name != null ? name : "DateFormat(" + format + ")",Date.class,messageKey);
		formatString = format;
		this.attributes = attributes;
	}

	public String toClient(Date value) {
		return new SimpleDateFormat(formatString).format(value);
	}

	public Date parseClient(Field field, String clientValue, String message)
			throws ValidationException {
					
		ParsePosition parsePosition = new ParsePosition(0);
		DateFormat format = new SimpleDateFormat(formatString);
		format.setLenient(false);
		
		Date date = format.parse(clientValue,parsePosition);
		if ( parsePosition.getIndex() == clientValue.length() ) {
			return date;
		} 
		
		parsePosition = new ParsePosition(0);
		format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		
		date = format.parse(clientValue,parsePosition);
		if ( parsePosition.getIndex() == clientValue.length() ) {
			return date;
		}
		
		throw new ValidationException(String.format(message,clientValue));			

	}

	public void render(Field field, String message, MarkupWriter writer, FormSupport formSupport) {

		writer.attributes(attributes);
	}		
}

