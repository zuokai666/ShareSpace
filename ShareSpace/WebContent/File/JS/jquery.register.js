var ok_a=false;
var ok_n=false;
var ok_p=false;

$(function($){
	
	$("input[name='account']").blur(function(){
		var u=$("input[name='account']").val();
		if(verify(u)){
			$("#account").html("账号为空");ok_a=false;
			return;
		}
		if(checknum(u)==false){
			$("#account").html("账号只能是数字或字母组合");ok_a=false;
			return;
		}
		$.ajax({
		   type: "POST",
		   url: "../User/CheckAccount",
		   data:"account="+u,
		   success: function(msg){
			   if(msg.result=="1"){
				   $("#account").html("");ok_a=true;
			   }else{
				   $("#account").html(msg.tip);ok_a=false;
			   }
		   }
		});
	});
	$("input[name='name']").blur(function(){
		var n=$("input[name='name']").val();
		if(verify(n)){
			$("#name").html("昵称为空");ok_n=false;
			return;
		}
		if(n.length>15){
			$("#name").html("昵称长度不能超过15");ok_n=false;
			return;
		}
		$.ajax({
		   type: "POST",
		   url: "../User/CheckName",
		   data:"name="+n,
		   success: function(msg){
			   if(msg.result=="1"){
				   $("#name").html("");ok_n=true;
			   }else{
				   $("#name").html(msg.tip);ok_n=false;
			   }
		   }
		});
	});
	$("input[name='password']").blur(function(){
		var n=$("input[name='password']").val();
		if(n.replace(/\s+/g,"").length!=n.length){
			$("#password").html("密码不允许有空格");ok_p=false;
			return;
		}
		if(n.length<6){
			$("#password").html("密码长度至少6位");ok_p=false;
			return;
		}
		$("#password").html("");
		ok_p=true;
	});
	
	
	$("input[name='submit']").click(function(){
		var u=$("input[name='account']").val();
		var n=$("input[name='name']").val();
		var p=$("input[name='password']").val();
		var s=$("input[name='password2']").val();
		if(p!=s){
			$("#password2").html("两次密码输入不一致");
			return;
		}
		if(verify(u) ||verify(n) || verify(p)|| verify(s)){
			layer.msg("亲，你的注册信息不全", {icon: 5,time:1500});
			return;
		}
		if((!ok_a)||(!ok_n)||(!ok_p)){
			layer.msg("亲，你的注册信息有错误", {icon: 5,time:1500});
			return;
		}
		$("input[name='submit']").addClass("disabled");
		$("input[name='submit']").val("正在注册...");
		$.ajax({
		   type: "POST",
		   url: "../User/RegisterAction",
		   data:"account="+u+"&password="+p+"&name="+n,
		   success: function(msg){
			   if(msg.result=="1"){
				   layer.msg(msg.tip, {icon: 6,time:2500},function(){
					   window.location.href="../User/Register";
				   });
			   }else{
				   layer.msg(msg.tip, {icon: 5,time:1500});
				   $("input[name='submit']").removeClass("disabled");
				   $("input[name='submit']").val("注册");
			   }
		   }
		});
	});
	$("input[name='login']").click(function(){
		window.location.href="../User/Login";
	});
	$("input[name='return']").click(function(){
		window.location.href="../index.html";
	});
});



























