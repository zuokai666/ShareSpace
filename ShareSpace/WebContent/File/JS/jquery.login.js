var username="";

function login(){
	var a=$.cookie('account');
	var p=$.cookie('password');
	if(typeof a=="undefined"||typeof p=="undefined"){
		//如果用户未设置Cookie,或者Cookie到期，验证用户是否从登录界面登录，并得到姓名，设置status
		getNameAndSetStatus();
	}else{
		//使用Cookie自动登录,并得到姓名，设置status
		indexLogin(a,p);
	}
}
function getNameAndSetStatus(){
	$.ajax({
	   type: "POST",
	   url: "./User/GetName",
	   success: function(msg){
		   if(msg.result=="1"){
			   username=msg.tip;
			   var l=typeof msg.img;
			   if(l=="string"){
				   $("#status").html(username+"&nbsp;&nbsp;"+msg.img+"&nbsp;&nbsp;<a href=\"./User/Welcome\">" +
				   		"个人中心</a>" +
				   		"<span id='read' style=\"color:red;\"></span>" +
				   		"&nbsp;&nbsp;<a href=\"./User/LogOff\">退出</a>");
			   }else{
				   setStatusSuccess(username);
			   }
			   setReadNum();
		   }else{
			   setStatusFail();
		   }
	   }
	});
}

function indexLogin(account,password,remember){
	$.ajax({
	   type: "POST",
	   url: "./User/AutoLoginAction",
	   data:"account="+account+"&password="+password,
	   success: function(msg){
		   if(msg.result=="1"){
			   getNameAndSetStatus();
		   }else{
			   setStatusFail();
		   }
	   }
	});
}
function setStatusSuccess(name){
	$("#status").html(name+"&nbsp;&nbsp;<a href=\"./User/Welcome\">" +
			"个人中心" +
			"<span id='read' style=\"color:red;\"></span>" +
			"</a>&nbsp;&nbsp;<a href=\"./User/LogOff\">退出</a>");
}
function setStatusFail(){
	$("#status").html("<a href=\"./User/Register\">注册</a>&nbsp;&nbsp;<a href=\"./User/Login\">登录</a>");
}


function setReadNum(){
	$.ajax({
	   type: "POST",
	   url: "./Message/getReadNum",
	   success: function(msg){
		   if(msg.tip!="0"){
			   $("#read").html("["+msg.tip+"]");
			   $("#read").attr("title","您有"+msg.tip+"条信息未读");
		   }
	   }
	});
}





















