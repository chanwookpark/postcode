var xmlhttp = function() {
	var req = null;
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();	
	} else {
		try { 
			req = new ActiveXObject("Msxml2.XMLHTTP"); 
		} catch (e1) { 
			try { 
				req = new ActiveXObject("Microsoft.XMLHTTP"); 
			} catch (e2) {
				alert("error");
			} 
		} 
	}
	return req;
};
xmlhttp.send = function(data) {
	var dum = null; 
	var send = data.send;
	switch(typeof send) {
		case 'object': 
			dum = JSON.stringify(send);
			break; 
		default: 
			dum = send;
			break; 
	}
	return dum; 
};

function xhr(x) {
	var _type = x.type;
	var _url = x.url; 
	var _success = x.success; 
	var _send = xmlhttp.send(x);
	var _thrw = x.thrw || function() {};
	var _error = x.error || function() {};
	var req = xmlhttp(); 

	req.onreadystatechange = function () {
		if (req.readyState == 4) {
			if (req.status == 200) {

				var data = req.responseText;
				try {
					var res = JSON.parse(data); 
					if ('webError' in res) {
						_thrw(res.webError); 
					} else {
						_success(res);
					}
				} catch (e) {
					_success(data);
				}


			} else {
				_error('A network error occurred');
			}
		}
	};
	req.open(_type, _url, true);
	req.send(_send);
}
