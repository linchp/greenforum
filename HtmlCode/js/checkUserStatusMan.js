function checkUserStatus(){
		var _ticket = $.cookie("EM_TICKET");
		if (!_ticket) {
			window.location.href = "./index.html";
			// console.log(_ticket+"123");
			return;
		}
		//当dataType类型为jsonp时，jQuery就会自动在请求链接上增加一个callback的参数
		$.ajax({
			url: "./user/query/" + _ticket,
			dataType: "json",
			type: "GET",
			success: function(data) {
				if (data.status == 200) {
					var _data = JSON.parse(data.data); //jackson
					var html = _data.userName;
					var userName = _data.userId;
					// document.cookie="userId="+ userName;
					$.cookie("userId",userName);
					$("#loginbar").html(html);
				}
			},
			error: function() {
				alert('index error.');
			}
		});
	}

function logout() {
	$.cookie("EM_TICKET",null);
	$.cookie("userId",null);
}
