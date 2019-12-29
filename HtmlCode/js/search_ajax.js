function searchForPosts(key){
    var keys=$("form textarea[name=keys]").val();
    if (!keys) {
        // window.location.href = "./index.html";
        // console.log(keys+"123");
        return;
    }
    //当dataType类型为jsonp时，jQuery就会自动在请求链接上增加一个callback的参数
    $.ajax({
        url: "./searchs/query?query=" + keys + "&page=1&rows=5",
        dataType: "json",
        type: "GET",
        success: function (data) {
            if (data.length > 0) {
            // var _data = JSON.parse(data);//jackson
                $("#iwj7y").replaceWith(" <div id=\"iwj7y\" class=\"container\"> </div>");
                $.each(data, function (index, posts) {
                    // var product_html = "product-info.html?productId=" + product.productId;
                    // var productId = product.productId;
                    // $("#prod_content").append("<div id='prod_div'><a href=" + product_html + "><img src='" + product.productImgurl + "'width=\"200px\" height=\"200px\"></img> </a><div id='prod_name_div'><a href=" + product_html + ">" + product.productName + "</a></div><div id='prod_price_div'>￥" + product.productPrice + "元</div><div><div id='gotocart_div'><a href='javascript:void(0)' onclick='addCartOne(\"" + productId + "\",\"" + userId + "\")'>加入购物车</a></div><div id='say_div'>库存" + product.productNum + "件</div></div></div>");
                    var postUrl = "./comment.html?"+posts.postId;
                    // $("#iwj7y").append("<div data-columns=\"1\" id=\"iagw6\" class=\"row\"><div data-column=\"1\" id=\"i0yqf\" class=\"cell\"></div> <div data-column=\"1\" id=\"ivpjm\" class=\"cell\"> <h2 id=\"iupul\">" + posts.postTitle + "</h2> <div id=\"ibfle\">" + posts.postContent + "</div><a href="+postUrl+" class=\"button4\">看帖 <br></a></div></div>");
                    $("#iwj7y").append("<div data-columns=\"1\" id=\"iagw6\" class=\"row\"><img src=\"http://lorempixel.com/240/320\"> <div data-column=\"1\" id=\"ivpjm\" class=\"cell\"> <h2 id=\"iupul\">" + posts.postTitle + "</h2> <div id=\"ibfle\">" + posts.postContent + "</div><a href="+postUrl+" class=\"button4\">看帖 <br></a></div></div>");
                    // $("#iwj7y").replaceWith("<div data-columns=\"1\" id=\"iagw6\" class=\"row\"><div data-column=\"1\" id=\"i0yqf\" class=\"cell\"></div> <div data-column=\"1\" id=\"ivpjm\" class=\"cell\"> <h2 id=\"iupul\">" + posts.postTitle + "</h2> <div id=\"ibfle\">" + posts.postContent + "</div><a href="+postUrl+" class=\"button4\">看帖 <br></a></div></div>");
            })
            }else {
                alert("未找到相关信息");
            }
        },
        error: function () {
            alert("请求失败");
        }
    });
}
