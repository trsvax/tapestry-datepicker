package com.trsvax.datepicker.services;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.apache.tapestry5.Translator;
import org.apache.tapestry5.beaneditor.DataTypeConstants;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.services.BeanBlockContribution;
import org.apache.tapestry5.services.EditBlockContribution;
import org.apache.tapestry5.services.LibraryMapping;

import com.trsvax.datepicker.DatePickerConstants;
import com.trsvax.datepicker.translators.CalendarTranslator;
import com.trsvax.datepicker.translators.DateTranslator;
import com.trsvax.datepicker.translators.TimestampTranslator;

public class DatePickerModule {
	
	public static void contributeTranslatorSource(@SuppressWarnings("rawtypes") MappedConfiguration<Class, Translator> configuration){
		configuration.add(Date.class, 
				new DateTranslator("Date(MM/dd/yyyy)","MM/dd/yyyy","date-translator",
				"data-date-format","mm/dd/yy",
				"placeholder","mm/dd/yyyy"));
		configuration.add(Timestamp.class, 
				new TimestampTranslator("Timestamp(MM/dd/yyyy)","MM/dd/yyyy","timestamp-translator",
				"data-date-format","mm/dd/yy",
				"placeholder","mm/dd/yyyy"));
		configuration.add(Calendar.class, 
				new CalendarTranslator("Calendar(MM/dd/yyyy)","MM/dd/yyyy","calendar-translator",
				"data-date-format","mm/dd/yy",
				"placeholder","mm/dd/yyyy"));
		
		
	}
	
	public static void contributeTranslatorAlternatesSource(@SuppressWarnings("rawtypes") MappedConfiguration<String, Translator> configuration){
		
		Translator<?> translator = 
				new DateTranslator("Date(MM-dd-yyyy)","MM-dd-yyyy","date-translator",
				"data-date-format","mm-dd-yy",
				"placeholder","mm-dd-yyyy");
		configuration.add(translator.getName(), translator);
	}
	
    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {		
    	configuration.add(new LibraryMapping("datefield", "com.trsvax.datepicker"));	
    	
    }
    
	public static void contributeBeanBlockOverrideSource(Configuration<BeanBlockContribution> configuration) {
		configuration.add(new EditBlockContribution(DataTypeConstants.DATE, "datefield/AppPropertyEditBlocks", "date"));
		configuration.add(new EditBlockContribution(DataTypeConstants.CALENDAR, "datefield/AppPropertyEditBlocks", "calendar"));

	}
	
    public static void contributeFactoryDefaults(MappedConfiguration<String, Object> configuration) {   	
    	configuration.add(DatePickerConstants.JQUERY_LIBRARY,"classpath:/META-INF/assets/datefield/jquery-ui-1.10.3.custom.min.js");
    	configuration.add(DatePickerConstants.JQUERY_CSS,"classpath:/META-INF/assets/datefield/jquery-ui-1.10.3.custom.css");
    }

}
