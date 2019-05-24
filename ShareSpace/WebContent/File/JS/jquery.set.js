var $MyTag=$("#MyTag");
var $ElseTag=$("#ElseTag");
var existTag=new Array();

$(function($){
	//填充信息
	writeMsg();
	
	$("#ut").click(function(){
		//打开模态框
    	$('#myModal').modal();
	});
	
	$("#submit").click(function(){
		updateUserTags();
	});
	
	
	$("#m").click(function(){
		var l=$("#location").val();
		var d=$("#description").val();
		if(verify(l)){
			layer.msg("地点为空", {icon: 5,time:1000});
			return;
		}
		if(verify(d)){
			layer.msg("描述为空", {icon: 5,time:1000});
			return;
		}
		if(l.length>40){
			layer.msg("地点长度不能超过40", {icon: 5,time:1000});
			return;
		}
		if(d.length>200){
			layer.msg("描述长度不能超过200", {icon: 5,time:1000});
			return;
		}
		$.ajax({
		   type: "POST",
		   url: "../User/Update",
		   data:"location="+l+"&description="+d,
		   success: function(msg){
			   if(msg.result=="1"){
				   layer.msg(msg.tip, {icon: 6,time:1000});
			   }else{
				   layer.msg(msg.tip, {icon: 5,time:1000});
			   }
		   }
		});
	});
	$("#n").click(function(){
		var n=$("#name").val();
		if(verify(n)){
			layer.msg("昵称为空", {icon: 5,time:1000});
			return;
		}
		if(n.length>15){
			layer.msg("昵称长度不能超过15", {icon: 5,time:1000});
			return;
		}
		$.ajax({
		   type: "POST",
		   url: "../User/updateName",
		   data:"name="+n,
		   success: function(msg){
			   if(msg.result=="1"){
				   layer.msg(msg.tip, {icon: 6,time:1000});
				   $("#name").attr("placeholder",n);
			   }else{
				   layer.msg("昵称已存在", {icon: 5,time:1000});
			   }
		   }
		});
	});
	
	$("#p").click(function(){
		var n=$("#password").val();
		if(n.replace(/\s+/g,"").length!=n.length){
			layer.msg("密码不允许有空格", {icon: 5,time:1000});
			return;
		}
		if(n.length<6){
			layer.msg("密码长度至少6位", {icon: 5,time:1000});
			return;
		}
		$.ajax({
		   type: "POST",
		   url: "../User/updatePassword",
		   data:"password="+n,
		   success: function(msg){
			   if(msg.result=="1"){
				   layer.msg(msg.tip, {icon: 6,time:1000});
				   $("#password").val("");
			   }else{
				   layer.msg(msg.tip, {icon: 5,time:1000});
			   }
		   }
		});
	});
	
	
	//处理文件信息
	var options = {  
	   beforeSubmit: showRequest,
	   success: showResponse,
	   url: "../User/updateUrl",
	   type: "POST",
	   dataType: "json",
	   timeout: 3000
	}  
	  
	function showRequest(formData, jqForm, options){  
		//暂时取消上传头像功能
		$("#t").val("禁止中...");
		return false;
		
		var formElement = jqForm[0];
		var file = formElement.url.value;
		if(file==""){
			layer.msg("图片为空", {icon: 5,time:1500});
			return false;
		}
		if(!image(file)){
			layer.msg("图片限于png,gif,jpeg,jpg格式", {icon: 5,time:1500});
			return false;
		}
		
		$("#t").addClass("disabled");
		$("#t").val("正在上传");
	    return true;  //只要不返回false，表单都会提交,在这里可以对表单元素进行验证  
	};  
	function image(filepath){
		var extStart=filepath.lastIndexOf(".");
		var ext=filepath.substring(extStart,filepath.length).toUpperCase();
		if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
			return false;
		}
		return true;
	}
	function showResponse(responseText, statusText){
	   if(responseText.result=="1"){
		   layer.msg(responseText.tip, {icon: 6,time:2000});
		   $("#t").removeClass("disabled");
		   $("#t").val("上传图片");
		   writeMsg();
		   $("input[name='url']").val("");
	   }else{
		   layer.msg(responseText.tip, {icon: 5,time:2000});
		   $("#t").removeClass("disabled");
		   $("#t").val("上传图片");
	   }
	};  
	
	$('#ajaxForm').submit(function() {  
	    $(this).ajaxSubmit(options);
	    return false; //阻止表单默认提交
	}); 
	
});

function writeMsg(){
	$.ajax({
	   type: "GET",
	   url: "../User/getMsg",
	   success: function(msg){
		   $("#name").val(msg.name);
		   $("#name").attr("placeholder",msg.name);
		   $("#location").val(msg.location);
		   $("#description").val(msg.description);
		   $("#title_image").attr("src",".."+msg.url);
		   //填充我的标签框架
		   $MyTag.empty();
		   for(var i=0;i<msg.taglist.length;i++){
			   existTag[i]=""+msg.taglist[i].id;
			   createMyTag(msg.taglist[i]);
		   }
		   //生成标签布局
		   getTags();
		   //填充标签
		   var _tagstr="";
		   for(var i=0;i<msg.taglist.length;i++){
			   _tagstr=_tagstr+msg.taglist[i].name+"，";
		   }
		   $("#tag").val(_tagstr.substring(0, _tagstr.length-1));
	   }
	});
}


function getTags(){
	$.ajax({
	   type: "POST",
	   url: "../User/getTag",
	   success: function(Tags){
		   $ElseTag.empty();
		   for(var i=0;i<Tags.length;i++){
			   createElseTag(Tags[i]);
		   }
	   }
	});
}

function createElseTag(tag){//其它
	if(isExist(tag.id)){
		return;
	}
	var button=$("<button onclick=\"move('"+tag.id+"','"+tag.name+"','e')\" type=\"button\" id=\""+tag.id+"\" class=\"btn btn-warning\" style='margin-bottom:10px;margin-right:10px;'>"+tag.name+"</button>");
	$ElseTag.append(button);
}
function createMyTag(tag){//自己
	var button=$("<button onclick=\"move('"+tag.id+"','"+tag.name+"','m')\" type=\"button\" id=\""+tag.id+"\" class=\"btn btn-info\" style='margin-bottom:10px;margin-right:10px;'>"+tag.name+"</button>");
	$MyTag.append(button);
}
function createElseTag1(id,name){
	var button=$("<button onclick=\"move('"+id+"','"+name+"','e')\" type=\"button\" id=\""+id+"\" class=\"btn btn-warning\" style='margin-bottom:10px;margin-right:10px;'>"+name+"</button>");
	$ElseTag.append(button);
}
function createMyTag1(id,name){
	var button=$("<button onclick=\"move('"+id+"','"+name+"','m')\" type=\"button\" id=\""+id+"\" class=\"btn btn-info\" style='margin-bottom:10px;margin-right:10px;'>"+name+"</button>");
	$MyTag.append(button);
}

function isExist(id){
	for(var i=0;i<existTag.length;i++){
		if(id==existTag[i]){
			return true;
			break;
		}
	}
	return false;
}

function move(id,name,attr){
	removeButton(id);
	if(attr=="m"){
		createElseTag1(id,name);
	}else{
		createMyTag1(id,name);
	}
	
	
	
	
}
function removeButton(id){
	$("#"+id).remove();
}

function updateUserTags(){
	//遍历$MyTag得到Id
	var ids="";
	var names="";
	var len=$("#MyTag").children().length;
	for(var i=0;i<len;i++){
		var id=$("#MyTag").children()[i].id;
		ids=ids+id;
		names=names+$("#"+id).text();
		if(i!=(len-1)){
			ids=ids+",";
			names=names+",";
		}
	}
	$("#tag").val(names);
	$.ajax({
	   type: "POST",
	   url: "../User/updateUserTags",
	   data:"ids="+ids,
	   success: function(msg){
		   if(msg.result=="1"){
			   $('#myModal').modal("hide");
			   //填充标签
			   var _tagstr="";
			   for(var i=0;i<msg.taglist.length;i++){
				   _tagstr=_tagstr+msg.taglist[i].name+"，";
			   }
			   $("#tag").val(_tagstr.substring(0, _tagstr.length-1));
		   }else{
			   layer.msg(msg.tip, {icon: 5,time:1000});
		   }
	   }
	});
	
}

























