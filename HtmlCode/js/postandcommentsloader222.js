window.onload=function(){
	//获取postId
	checkUserStatus();
	var localUrl=decodeURI(window.location.href).split("?")[1];
	if (localUrl.indexOf("psw") == 0){
		console.log("aaa");
		var postPsw = prompt("请输入密码","password");
		var postId = localUrl.split("#")[1];
	}else {
		var postPsw = null;
		var postId = localUrl;
	}

	// var postId=localUrl.substr(localUrl.lastIndexOf("?")+1);
	queryPages(postId, postPsw);
}
function queryPages(postId, postPsw){
	$.ajax({
		url: "./postandcomment/pagePost",
		data:{"postId":postId, "postPsw":postPsw},
		dataType: "json",
		type: "POST",
		success: function (data) {
			if (data.rows != null && data.rows.length > 0) {
			    console.log("addPost");
				$.each(data.rows, function (index, posts) {
					$("#postTitle").html(posts.postTitle);
					$("#postContent").html(posts.postContent);
				})
			}else {
				window.location.href="./secretCenter.html";
				alert("可能密码不对噢");
			}
		},
		error: function () {
			alert("请求失败");
		}
	});
	$.ajax({
		url: "./postandcomment/pageComment?postId=" + postId,
		// dataType: "json",
		type: "GET",
		success: function (data) {
			// alert("好啊3");
			if (data.rows.length > 0) {
				$.each(data.rows, function (index, comment) {
					// var product_html = "product-info.html?productId=" + product.productId;
					// var productId = product.productId;
					// $("#prod_content").append("<div id='prod_div'><a href=" + product_html + "><img src='" + product.productImgurl + "'width=\"200px\" height=\"200px\"></img> </a><div id='prod_name_div'><a href=" + product_html + ">" + product.productName + "</a></div><div id='prod_price_div'>￥" + product.productPrice + "元</div><div><div id='gotocart_div'><a href='javascript:void(0)' onclick='addCartOne(\"" + productId + "\",\"" + userId + "\")'>加入购物车</a></div><div id='say_div'>库存" + product.productNum + "件</div></div></div>");
					// $("#iwj7y").append("<div data-columns=\"1\" id=\"iagw6\" class=\"row\"><div data-column=\"1\" id=\"i0yqf\" class=\"cell\"></div> <div data-column=\"1\" id=\"ivpjm\" class=\"cell\"> <h2 id=\"iupul\">" + posts.postTitle + "</h2> <div id=\"ibfle\">" + posts.postContent + "</div><a href=\"##\" class=\"button4\">看帖 <br></a></div></div>");
					$("#iyj5").append("<div class=\"gpd-container\" id=\"ifkjk\"> <div class=\"gdp-row\"> <div class=\"cell\" id=\"iiy7g\"> <h1></h1> <div id=\"iegpj\"></div> <div class=\"gpd-text\">"+comment.commentContent+"</div></div> <div class=\"cell\" id=\"i81x1\"></div> </div> </div>");
				})
			}
		},
		error: function () {
			alert("请求失败");
		}
	});
};
function search(a){
	var query=document.getElementById(a).value;
	// alert(query);
	window.location.href="./search.html?query="+query;

}
function sleep(delay) {
	var start = (new Date()).getTime();
	while ((new Date()).getTime() - start < delay) {
		continue;
	}
}
