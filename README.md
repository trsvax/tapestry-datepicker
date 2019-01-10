No Longer maintained moving to node/graphql/react
---

tapestry-datepicker
===================

Alternative for Tapestry 5.4 Datapicker.

The alternative datepicker works by adding a date translator to any textfield with a type of date/calendar/timestamp. 
This restricts the input to a valid date. The other piece is a mixin that provides the client side datapicker. 
Currently the JQuery datepicker is supported. Datepicker options are provided by data- attributes. Keep in mind 
data- attributes are converted to lower case but data-day-name-short maps to the dayNamesShort option.



Example usage:

      <t:form>
      	<t:textfield value="date" t:mixins="datefield/JQueryDatePicker" data-duration="slow"/>
      	<input type="submit"/>
      </t:form>
      
With Icon
    
      <t:form>
      	<t:textfield value="date" t:mixins="datefield/JQueryDatePicker" 
      	   data-show-on="button" data-button-image-only="true" data-button-image="yourImageAsset" />
      	<input type="submit"/>
      </t:form>
      
You can also create your own datepicker component:


    public class DateField extends TextField {
	
	    @Mixin
        private JQueryDatePicker mixin;

    }

It's also possible to add Translators. The first three arguments are the name, java date format and the message key.
After that all the name/value pairs are added to the input element as attributes. Here are the included ones:

	public static void contributeTranslatorSource(MappedConfiguration<Class, Translator> configuration){
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
	
Alternates:
	
	public static void contributeTranslatorAlternatesSource(MappedConfiguration<String, Translator> configuration){
		
		Translator<?> translator = 
				new DateTranslator("Date(MM-dd-yyyy)","MM-dd-yyyy","date-translator",
				"data-date-format","mm-dd-yy",
				"placeholder","mm-dd-yyyy");
		configuration.add(translator.getName(), translator);
	}
	
You can also include jQuery data attributes in the translator:

	public static void contributeTranslatorAlternatesSource(MappedConfiguration<String, Translator> configuration){
		
		Translator<?> translator = 
				new DateTranslator("Date(MM-dd-yyyy)","MM-dd-yyyy","date-translator",
				"data-date-format","mm-dd-yy",
				"placeholder","mm-dd-yyyy"
				"data-duration","slow");
		configuration.add(translator.getName(), translator);
	}
	
As always with Tapestry it's possible to override the defaults. see configuration.override(arg0, arg1)

It's now possible to override the javascript/css files also. The default configuration is:

    public static void contributeFactoryDefaults(MappedConfiguration<String, Object> configuration) {   	
    	configuration.add(DatePickerConstants.JQUERY_LIBRARY,"classpath:/META-INF/assets/datefield/jquery-ui-1.10.3.custom.min.js");
    	configuration.add(DatePickerConstants.JQUERY_CSS,"classpath:/META-INF/assets/datefield/jquery-ui-1.10.3.custom.css");
    }
    
If you've already included jQueryUI or have css you can set the values to DatePickerConstants.NULL.

The datepicker now includes support for browsers that support html5 type="date". If it seems the browser has native
support it is used instead of the javascript datepicker. Currently this feature is experimental and may change.

