package com.trsvax.datepicker.mixins;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@Import(library="jquery-datepicker.js")
@MixinAfter
public class JQueryDateField {
	
	
    @Inject
	@Path("calendar.png")
    private Asset icon;
    
	@Inject
	private JavaScriptSupport javaScriptSupport;

	
	@BeginRender
	void beginRender(MarkupWriter writer) {		
		writer.getElement().forceAttributes("type","date");	
		writer.getElement().addClassName("datepicker");
		writer.getElement().attribute("data-provider", "datepicker");
		
		javaScriptSupport.addScript("$('*[data-provider=\"datepicker\"]').datepicker(" +
				"{ dateFormat: 'mm/dd/yy',showOn: 'button',buttonImage: '%s',buttonImageOnly: true });"
				,  icon.toClientURL());
	}
}
