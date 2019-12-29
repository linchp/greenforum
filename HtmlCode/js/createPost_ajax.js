function createPost(){
	var postTitle=$("form input[name=theme]").val();
	var postContent=$("form input[name=content]").val();
	var userId=$.cookie("userId");
	var postPassword=$("form input[name=postPassword]").val();
	if (postPassword == ""){
	    postPassword = null;
	};
	$.ajax({
		url:"./postandcomment/savePost",
		type:"POST",
		data:{
			  "postTitle":postTitle,
			  "postContent":postContent,
			  "userId":userId,
			  "postPassword":postPassword
			 },
		dataType:"json",
		success:function(result){
			if(result.status==200){
				alert("发帖成功");
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
