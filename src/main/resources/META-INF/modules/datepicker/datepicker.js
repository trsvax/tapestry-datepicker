define(["jquery"], function($) {
	return function(parameters) {

		var element = $('#' + parameters.id);
		element.datepicker(element.data());
	};
});