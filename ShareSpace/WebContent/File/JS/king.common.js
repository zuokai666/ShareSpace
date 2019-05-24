//验证值是否为空
function verify(value){
	if(value.replace(/\s+/g,"").length==0){
		return true;
	}else{
		return false;
	}
}
//验证值是否只包含字母与数字，注册时账号验证使用
function checknum(value) {
    var Regx = /^[A-Za-z0-9]*$/;
    if (Regx.test(value)) {
        return true;
    }else {
        return false;
    }
}

//为每个目录添加个人首页路径，防止缓存
function link(){
	window.location.href="../User/Index?math="+Math.random();
}

//信箱三个页面使用
function setReadNum(){
	$.ajax({
	   type: "POST",
	   url: "../Message/getReadNum",
	   success: function(msg){
		   if(msg.tip!="0"){
			   $("#read").html(msg.tip);
			   $("#read").attr("title","您有"+msg.tip+"条信息未读");
		   }
	   }
	});
}

