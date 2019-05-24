$(function($){
	$("input[name='return']").click(function(){
		window.location.href="../index.html";
	});
	
	
	var options = {  
	   //target: '#output',        //把服务器返回的内容放入id为output的元素中      
	   beforeSubmit: showRequest,  //提交前的回调函数  
	   success: showResponse,      //提交后的回调函数  
	   error:showError,
	   url: "../Share/CommitShare",//默认是form的action， 如果申明，则会覆盖  
	   type: "POST",               //默认是form的method（get or post），如果申明，则会覆盖  
	   dataType: "json",           //html(默认), xml, script, json...接受服务端返回的类型  
	   //clearForm: true,          //成功提交后，清除所有表单元素的值  
	   resetForm: true,            //成功提交后，重置所有表单元素的值  
	   timeout: 9000               //限制请求的时间，当请求大于3秒后，跳出请求  
	}  
	function showError(responseText, statusText){
		if(statusText=='timeout'){
			$("input[name='submit']").removeClass("disabled");
			$("input[name='submit']").val("立刻分享");
			layer.msg("服务器繁忙，请稍后再试！", {icon: 5,time:1500});
			return;
		}
	}
	function showRequest(formData, jqForm, options){  
		var formElement = jqForm[0];              //将jqForm转换为DOM对象  
		var content = formElement.content.value;  //访问jqForm的DOM元素  
		if(verify(content)){
			layer.msg("内容不能为空哦", {icon: 5,time:1500});
			return false;
		}
		var file1 = formElement.file1.value;
		var file2 = formElement.file2.value;
		var file3 = formElement.file3.value;
		if(!(image(file1)&&image(file1)&&image(file1))){
			layer.msg("图片限于png,gif,jpeg,jpg格式", {icon: 5,time:1500});
			return false;
		}
		$("input[name='submit']").addClass("disabled");
		$("input[name='submit']").val("正在提交...");
	    return true;  //只要不返回false，表单都会提交,在这里可以对表单元素进行验证  
	};  
	function image(filepath){
		if(filepath==""){
			return true;
		}
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
		   $("input[name='submit']").removeClass("disabled");
		   $("input[name='submit']").val("立刻分享");
	   }else{
		   layer.msg(responseText.tip, {icon: 5,time:2000});
		   $("input[name='submit']").removeClass("disabled");
		   $("input[name='submit']").val("立刻分享");
	   }
	};  
	
	$('#ajaxForm').submit(function() {  
	    $(this).ajaxSubmit(options);
	    return false; //阻止表单默认提交
	});  
});



































