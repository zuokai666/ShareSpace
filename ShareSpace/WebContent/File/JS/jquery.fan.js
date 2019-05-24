var fans=$("#fans");

$(function($){
	getFollow();
});
function getFollow(){
	$.ajax({
	   type: "POST",
	   url: "../Fan/FollowIndex",
	   success: function(msg){
		   if(msg.length==0){
			   fans.html("您还没有关注，快去关注小伙伴们吧！");
		   }else{
			   fans.html("");
			   for(var i=0;i<msg.length;i++){
				   createFollow(msg[i]);
			   }
		   }
	   }
	});
}
function getFan(){
	$.ajax({
	   type: "POST",
	   url: "../Fan/FanIndex",
	   success: function(msg){
		   if(msg.length==0){
			   fans.html("您还没有粉丝，召集您的小伙伴们一起互动吧!");
		   }else{
			   fans.html("");
			   for(var i=0;i<msg.length;i++){
				   createFan(msg[i]);
				   if((i+1)%6==0){
					   fans.append("<hr />");
				   }
			   }
		   }
	   }
	});
}

function createFan(fan){
	var guestid=fan.guestid;
	var guestname=fan.guestname;
	var guesturl=fan.guesturl;
	
	var div=$("<div class=\"col-md-2\" style=\"height:150px;\"></div>");
	var span=$("<span style=\"word-wrap:break-word;\"></span>");
	span.append(fanLink(guestid,guestname));
	div.append(img(guesturl));
	div.append("<br />");
	div.append(span);
	fans.append(div);
}

function createFollow(f){
	var hostid=f.hostid;
	var hostname=f.hostname;
	var hosturl=f.hosturl;
	
	var div=$("<div class=\"col-md-2\" style=\"height:150px;\"></div>");
	var span=$("<span style=\"word-wrap:break-word;\"></span>");
	span.append(fanLink(hostid,hostname));
	div.append(img(hosturl));
	div.append("<br />");
	div.append(span);
	fans.append(div);
}

function fanLink(id,name){
	return "<a target=\"_blank\" href=\"../User/Index?id="+id+"&math="+Math.random()+"\">"+name+"</a>";
}
function img(url){
	return "<img width=\"100px\" src=\".."+url+"\" />";
}

















