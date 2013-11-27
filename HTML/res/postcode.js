
var results = document.getElementById("results");
var postcodeSearchForm = document.getElementById('postcodeSearchForm');
var formElements = postcodeSearchForm.elements;
postcodeSearchForm.model = {};

for (var i = 0; i < formElements.length; i++) {
	// console.log(i);
	// alert(i);
}
postcodeSearchForm.onsubmit = function() {
	return false; 
};