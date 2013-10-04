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

import com.trsvax.datepicker.translators.CalendarTranslator;
import com.trsvax.datepicker.translators.DateTranslator;
import com.trsvax.datepicker.translators.TimestampTranslator;

public class DatePickerModule {
	
	public static void contributeTranslatorSource(MappedConfiguration<Class, Translator> configuration){
		configuration.add(Date.class, new DateTranslator("slowDate","MM-dd-yyyy","date","data-date-format","mm-dd-yy","data-duration","slow"));
		configuration.add(Timestamp.class, new TimestampTranslator(null,"MM/dd/yyyy","timestamp","data-date-format","mm/dd/yy"));
		configuration.add(Calendar.class, new CalendarTranslator(null,"MM/dd/yyyy","calendar","data-date-format","mm/dd/yy"));
	}
	
	public static void contributeTranslatorAlternatesSource(MappedConfiguration<String, Translator> configuration){
		
		Translator<?> translator = new DateTranslator(null,"MM/dd/yyyy","date","data-date-format","mm/dd/yy");
		configuration.add(translator.getName(), translator);
	}
	
    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {		
    	configuration.add(new LibraryMapping("datefield", "com.trsvax.datepicker"));	
    	
    }
    
	public static void contributeBeanBlockOverrideSource(Configuration<BeanBlockContribution> configuration) {
		configuration.add(new EditBlockContribution(DataTypeConstants.DATE, "datefield/AppPropertyEditBlocks", "date"));
		configuration.add(new EditBlockContribution(DataTypeConstants.CALENDAR, "datefield/AppPropertyEditBlocks", "calendar"));

	}

}
