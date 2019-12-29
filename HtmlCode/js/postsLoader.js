window.onload=function(){
    checkUserStatus();
	var queryPageParam="page=1&rows=50";
	queryPages(queryPageParam);
}
function queryPages(param){
	$.ajax({
		url: "./postandcomment/pageManage?" + param,
		dataType: "json",
		type: "GET",
		success: function (data) {
			// alert("好啊3");
			if (data.rows.length > 0) {
				$.each(data.rows, function (index, posts) {
				    if (posts.postPassword =="" ||undefined || null) {
						var postUrl = "./comment.html?"+posts.postId;
				    }else {
						var postUrl = "./comment.html?psw#"+posts.postId;
					}
					$("#iwj7y").append("<div data-columns=\"1\" id=\"iagw6\" class=\"row\"><div data-column=\"1\" id=\"i0yqf\" class=\"cell\"></div> <div data-column=\"1\" id=\"ivpjm\" class=\"cell\"> <h2 id=\"iupul\">"
						+ posts.postTitle + "</h2> <div id=\"ibfle\">" + posts.postContent + "</div><a href="+postUrl
						+" class=\"button4\">看帖 <br></a><a href=\"#\" class=\"button4\" onclick=\"flowPost("+"'"+posts.postId+"'"+")\">收藏<br></a></div></div>");
				})
			}
		},
		error: function () {
			alert("请求失败");
		}
	});
};
// function search(a){
// 	var query=document.getElementById(a).value;
// 	// alert(query);
// 	window.location.href="./search.html?query="+query;
//
// }
function sleep(delay) {
	var start = (new Date()).getTime();
	while ((new Date()).getTime() - start < delay) {
		continue;
	}
}
