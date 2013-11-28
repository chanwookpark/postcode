
var doc = document; 
var results = doc.getElementById("results");
var postcodeSearchForm = doc.getElementById('postcodeSearchForm');
var formModel = {
	"action": "/postcode/search",
	"addressType": "PLAIN", 
	"address": "수락산"
};
var addressTypeA = {};
var addressTypeB = {};
var address = {};
if (postcodeSearchForm != null) {
	postcodeSearchForm.onsubmit = function() {
		formModel.action = this.action;
		runXhr(this); 
		return false; 
	};

	addressTypeA = doc.getElementById("addressTypeA");
	addressTypeB = doc.getElementById("addressTypeB");
	address = doc.getElementById("address");

	addressTypeA.onclick = addressTypeEvt;
	addressTypeB.onclick = addressTypeEvt;
	address.onblur = function(e) {
		formModel[this.name] = this.value;
	};

	function addressTypeEvt(e) {
		formModel[this.name] = this.value;
	}
}


var pagingNav = doc.getElementById("paging-nav");
if (pagingNav != null) {
	var pagingNavLink = pagingNav.getElementsByTagName("A");
	for(var i = 0; i < pagingNavLink.length; i++) {
		pagingNavLink[i].onclick = function() {
			runXhr(this);
			return false; 
		};
	}
}





function runXhr(node) {
	var URLtemplate = "{action}?addressType={addressType}&address={address}&_pageItemSize=5&_pageNumber={pageNumber}"
		.replace("{action}", formModel.action)
		.replace("{addressType}", formModel.addressType)
		.replace("{address}", formModel.address);

	if (node.nodeName == "A") {
		URLtemplate = URLtemplate.replace("{pageNumber}", node.title);
	} else {
		URLtemplate = URLtemplate.replace("{pageNumber}", "1");
	}

	xhr({
		type: "GET", 
		url: URLtemplate, 
		success: function(data) {
			results.innerHTML = data;
			console.log(data);
		}
	});
}