package com.trsvax.datepicker.translators;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.tapestry5.Field;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.internal.translator.AbstractTranslator;
import org.apache.tapestry5.services.FormSupport;

public class CalendarTranslator extends AbstractTranslator<Calendar> {
	
	private final String formatString;
	private final String dataName;
	
	public CalendarTranslator(String format, String dataName, String messageKey) {
		super("CalendarTranslator(" + format + ")",Calendar.class,messageKey);
		formatString = format;
		this.dataName = dataName;
	}


	public String toClient(Calendar value) {
		return new SimpleDateFormat(formatString).format(value);
	}


	public Calendar parseClient(Field field, String clientValue, String message)
			throws ValidationException {
					
		ParsePosition parsePosition = new ParsePosition(0);
		DateFormat format = new SimpleDateFormat(formatString);
		format.setLenient(false);

		Date date = format.parse(clientValue,parsePosition);
		if ( parsePosition.getIndex() != clientValue.length() ) {
			throw new ValidationException(String.format(message,formatString));
		} 
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setLenient(false);
		calendar.setTime(date);
		return calendar;
	}


	public void render(Field field, String message, MarkupWriter writer, FormSupport formSupport) {
		writer.attributes(dataName,formatString);
	}		

}

