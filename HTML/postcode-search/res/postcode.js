
// 비동기로 조회된 값을 포함하는 영역
var results = document.getElementById("results");
var postcodeSearchForm = document.getElementById('postcodeSearchForm');
postcodeSearchForm.onsubmit = function() {
	xhr({
		type: "GET", 
		url: "http://10.52.41.91:9090/postcode/search?address=영등포&addressType=PLAIN&_pageItemSize=5&_pageNumber=0", 
		success: function(data) {
			results.innerHTML = data;
			console.log(data);
		}
	});
	return false; 
};