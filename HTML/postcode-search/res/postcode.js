
var doc = document; 
var results = doc.getElementById("results");
var postcodeSearchForm = doc.getElementById('postcodeSearchForm');
postcodeSearchForm.onsubmit = function() {
	runXhr(); 
	return false; 
};

/*
var pagingNav = doc.getElementById("paging-nav");
var pagingNavLink = pagingNav.getElementsByTagName("A");
for(var i = 0; i < pagingNavLink.length; i++) {
	pagingNavLink[i].onclick = function() {
		runXhr();
		return false; 
	};
}
*/

var formModel = {
	"addressType": "PLAIN"
};
var addressTypeA = doc.getElementById("addressTypeA");
var addressTypeB = doc.getElementById("addressTypeB");
var address = doc.getElementById("address");

addressTypeA.onclick = addressTypeEvt;
addressTypeB.onclick = addressTypeEvt;

function addressTypeEvt(e) {
	formModel[this.name] = this.value;
}


function runXhr() {
	var URLtemplate = "{action}?addressType={addressType}&address={address}"
		.replace("{action}", postcodeSearchForm.action)
		.replace("{addressType}", formModel.addressType)
		.replace("{address}", address.value);

	xhr({
		type: "GET", 
		url: URLtemplate, 
		success: function(data) {
			results.innerHTML = data;
			console.log(data);
		}
	});
}