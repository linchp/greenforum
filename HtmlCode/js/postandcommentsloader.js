window.onload=function(){
    checkUserStatus();
	var queryPageParam="page=1&rows=5";
	queryPages(queryPageParam);
}
function queryPages(param){
	$.ajax({
		url: "./postandcomment/pageManage?" + param,
		dataType: "json",
		type: "POST",
		success: function (data) {
			// alert("好啊3");
			if (data.rows.length > 0) {
				$.each(data.rows, function (index, posts) {
					// var product_html = "product-info.html?productId=" + product.productId;
					// var productId = product.productId;
					// $("#prod_content").append("<div id='prod_div'><a href=" + product_html + "><img src='" + product.productImgurl + "'width=\"200px\" height=\"200px\"></img> </a><div id='prod_name_div'><a href=" + product_html + ">" + product.productName + "</a></div><div id='prod_price_div'>￥" + product.productPrice + "元</div><div><div id='gotocart_div'><a href='javascript:void(0)' onclick='addCartOne(\"" + productId + "\",\"" + userId + "\")'>加入购物车</a></div><div id='say_div'>库存" + product.productNum + "件</div></div></div>");
					var postUrl = "./comment.html?"+posts.postId;
					$("#iwj7y").append("<div data-columns=\"1\" id=\"iagw6\" class=\"row\"><div data-column=\"1\" id=\"i0yqf\" class=\"cell\"></div> <div data-column=\"1\" id=\"ivpjm\" class=\"cell\"> <h2 id=\"iupul\">" + posts.postTitle + "</h2> <div id=\"ibfle\">" + posts.postContent + "</div><a href="+postUrl+" class=\"button4\">看帖 <br></a></div></div>");
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
