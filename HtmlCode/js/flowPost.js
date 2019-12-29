function flowPost(postId){
	var userId=$.cookie("userId");
	$.ajax({
		url:"./postandcomment/saveFlow",
		type:"post",
		data:{
			"userId":userId,
			"postId":postId
		},
		dataType:"json",
		success:function(result){
			if(result.status==200){
				alert("跟随成功")
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
