function createcomment(){
	var commentContent=$("form textarea[name=comment]").val();
	// var userId=document.cookie.split(";")[0].split("=")[1];
	var userId=$.cookie("userId");
	//Parse the Url to get the userid
	var localUrl=decodeURI(window.location.href);
	var postId=localUrl.substr(localUrl.lastIndexOf("?")+1);
		$.ajax({
			url:"./postandcomment/saveComment",
			type:"post",
			data:{
				  "commentContent":commentContent,
				  "userId":userId,
				  "postId":postId
				 },
			dataType:"json",
			success:function(result){
				if(result.status==200){
					// alert("发帖成功")
					window.location.reload();
				}else{
					alert(result.message);
				}
			},
			error:function(){
				alert("请求失败！");
			}
		});
	return false;
};
