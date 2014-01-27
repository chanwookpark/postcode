(function(doc, formID){
	var form = form = doc.getElementById(formID);

	form.select1 = doc.getElementById('addrSelect1');
	form.select1.onchange = function() {
		form.select2.setValue(this.value);
		form.dataList[this.id] = this.value;
	};



	form.select2 = doc.getElementById('addrSelect2');
	form.select2.setValue = function(v) {
		this.value = v; 
		form.dataList[this.id] = v;
	};
	form.select2.onchange = function() {
		form.dataList[this.id] = this.value;
	};


	form.text1 = doc.getElementById('addrText');
	form.text1.onfocus = function() {

	};
	form.text1.onblur = function() {
		form.dataList[this.id] = this.value;
	};


	form.dataList = {
		"addrSelect1": null, 
		"addrSelect2": null, 
		"addrText": null
	};
	form.submit = doc.getElementById('submit');

	/* 
		- 조회 클릭
		- 조회 버튼 비활성화
		- GET/url로 전송
		- 반환된값 자체를 alert으로 사용자에게 공지
		- 조회 버튼 활성화
	*/ 


	form.onsubmit = function() {
		this.submit.disabled = true; 

		inputValidator(this, form);
		// xhrMapping(); 
		
		return false;
	};

})(document, 'postcodeSearchForm');

function validator(evt, form) {
	for(x in evt.dataList) {
		if(evt.dataList[x] == null) {
			alert('입력하지 않은 부분이 있습니다');
			form.submit.disabled = false;
			return false; 
		}
	}
}

function xhrMapping() {
	xhr({
		type: "GET", 
		url: "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=donghyuc", 
		success: function(data) {
			alert(data);
			form.submit.disabled = false;
		}, 
		error: function(err) {
			alert(err);
			form.submit.disabled = false;
		}
	});
}