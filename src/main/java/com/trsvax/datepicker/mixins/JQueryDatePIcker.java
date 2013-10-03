package com.trsvax.datepicker.mixins;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@Import(library="classpath:/META-INF/assets/datefield/jquery-ui-1.10.3.custom.min.js",
stylesheet="classpath:/META-INF/assets/datefield/jquery-ui-1.10.3.custom.css")
@MixinAfter
public class JQueryDatePIcker {
	
	
    @Inject
	@Path("classpath:/META-INF/assets/datefield/calendar.png")
    private Asset icon;
    
	@Inject
	private JavaScriptSupport javaScriptSupport;

	@InjectContainer
	private ClientElement clientElement;
	
	@BeginRender
	void beginRender(MarkupWriter writer) {		
		writer.getElement().forceAttributes("type","date");			
		javaScriptSupport.require("datepicker/datepicker").with(new JSONObject("id", clientElement.getClientId()));
	}
}
