function logout(){
    $.ajax({
       url:"./user/logout",
       dataType:"json",
       type:"GET",
       success:function (data) {
           if(data.status==200){
               window.location.href="./index.html";
               return;
           }else{
               alert("登出失败");
               return;
           }
       },
       erro:function () {
           alert("登出请求发送失败");
       }
    });
}