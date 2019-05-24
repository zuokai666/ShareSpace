var home=$("#notice");

$(function($){
	$("input[name='submit']").click(function(){
		var name=$("input[name='name']").val();
		var content=$("#content").val();
		
	});
	
	
	$.ajax({
	   type: "POST",
	   url: "../Web/getNotice",
	   success: function(data){
		   createNotices(data);
	   }
	});
});
function createNotices(datas){
	for(var i=0;i<datas.length;i++){
		createNotice(datas[i]);
	}
}
function createNotice(data){
	var title=$("<h3>"+data.title+"</h3>");
	var time=$("<h6>"+data.time+"</h6>");
	var content=$("<h4>"+data.content+"</h4>");
	home.append(title);
	home.append(time);
	home.append(content);
	home.append("<hr />");
}