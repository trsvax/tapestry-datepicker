define(["jquery"], function($) {
	return function(parameters) {

		var input = document.createElement('input');
        input.setAttribute('type', 'date');
        input.value = 'testing';
        
        var id = '#' + parameters.id;
        var clientID = '#' + parameters.clientID;
        var formID = '#' + parameters.formID;
        
        if ( input.type !== 'date' || input.type === 'testing' ) {
        	var element = $(id);
        	element.datepicker(element.data());        	
       } else {
        	$(id).get(0).setAttribute('type','hidden');
        	$(clientID).get(0).setAttribute('type','date');
    	   
    	   $(formID).submit( function() {       		
       			$(id).val($(clientID).val() );
       		});
       }
        	
        	
	};
});