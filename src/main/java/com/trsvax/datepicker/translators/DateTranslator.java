package com.trsvax.datepicker.translators;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tapestry5.Field;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.ValidationTracker;
import org.apache.tapestry5.internal.translator.AbstractTranslator;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.FormSupport;

public class DateTranslator extends AbstractTranslator<Date> {

	private final String formatString;
	private final String dataName;
	
	public DateTranslator(String format,String dataName, String messageKey) {
		super("DateFormat(" + format + ")",Date.class,messageKey);
		formatString = format;
		this.dataName = dataName;
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
		if ( parsePosition.getIndex() != clientValue.length() ) {
			throw new ValidationException(String.format(message,formatString));			
		} 
		return date;
	}

	public void render(Field field, String message, MarkupWriter writer, FormSupport formSupport) {
		writer.attributes(dataName,formatString);
	}		
}

