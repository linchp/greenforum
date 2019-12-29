window.onload=function(){
    // checkUserStatus();
	// var queryPageParam="page=1&rows=20&pageName=posts";
	queryPagesByName(1,2,"posts");
}
function queryPagesByName(page,rows,pageName){
    var userId=$.cookie("userId");
	$.ajax({
		url: "./postandcomment/LoadUserPostsPage",
		type:"post",
		data:{
			"page":page,
			"rows":rows,
			"pageName":pageName,
			"userId":userId
		},
		dataType:"json",
		success: function (data) {
			if (data.rows.length > 0) {
				$("#ise6h").replaceWith("<div id=\"ise6h\"></div>")
				$.each(data.rows, function (index, posts) {
					var postUrl = "./comment.html?"+posts.postId;
					// var deleteUrl = "onclick=\"deleteByPostIdWithName('"+posts.postId+"')";
					// $("#tab3").append("<div data-columns=\"1\" id=\"iagw6\" class=\"row\"><div data-column=\"1\" id=\"i0yqf\" class=\"cell\"></div> <div data-column=\"1\" id=\"ivpjm\" class=\"cell\"> <h2 id=\"iupul\">"
					// 	+ posts.postTitle + "</h2> <div id=\"ibfle\">" + posts.postContent + "</div><a href="+postUrl
					// 	+" class=\"button4\">看帖 <br></a><a href=\"##\" class=\"button4\" onclick=\"flowPost("+"'"+posts.postId+"'"+")\">跟随<br></a></div></div>");
					$("#ise6h").append("    <div class=\"gdp-row\">\n" +
						"        <div class=\"cell\" id=\"iu0ah\"></div>\n" +
						"        <div class=\"cell\" id=\"ifhe2\">\n" +
						"            <a id=\"i957v\" href=\""+postUrl+"\" class=\"gpd-link\">Title : "+posts.postTitle+"</a></div>\n" +
						"        <div class=\"cell\" id=\"i41cx\">\n" +
						"            <div class=\"gpd-text\" id=\"iy4pe\" ></div></div>\n" +
						"    </div>\n" +
						"    <div class=\"gdp-row\">\n" +
						"        <div class=\"cell\" id=\"ijai4\">\n" +
						"            <div class=\"gpd-text\" id=\"izla5\">Content : "+posts.postContent+"</div></div>\n" +
						"    </div>\n")
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
