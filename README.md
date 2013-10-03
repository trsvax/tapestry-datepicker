tapestry-datepicker
===================

Alternative for Tapestry 5.4 Datapicker.

The alternative datepicker works by adding a date translator to any textfield with a type of date. This restricts 
input to a valid date. The other piece is a mixin that provides the client side datapicker. Currently the JQuery
datepicker is supported. Datepicker options are provied by data- attributes.


Example usage:

      <t:form>
      	<t:textfield value="date" t:mixins="datefield/JQueryDatePicker" data-duration="slow"/>
      	<input type="submit"/>
      </t:form>
      
You can also create your own datepicker component:


    public class DateField extends TextField {
	
	    @Mixin
        private JQueryDatePIcker mixin;

    }

It's also possible to add Translators. Here are the included ones:

	public static void contributeTranslatorSource(MappedConfiguration<Class, Translator> configuration){
		configuration.add(Date.class, new DateTranslator("MM-dd-yyyy","mm/dd/yy","data-date-format","date"));
	}
	
	public static void contributeTranslatorAlternatesSource(MappedConfiguration<String, Translator> configuration){
		
		Translator<?> translator = new DateTranslator("MM-dd-yyyy","mm-dd-yy","date-format","date");
		configuration.add(translator.getName(), translator);
	}
