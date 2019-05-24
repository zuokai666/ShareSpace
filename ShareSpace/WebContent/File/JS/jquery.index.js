$(function($){
	//设置推荐排行宽度
	changeDivHeight();
	//添加第一页分享,默认typeid为0，显示所有类型分享
	createShareList(1);
	//判断登录
	login();
	//添加统计
	getStatistics();
	//添加Rank
	getRanks();
	//添加最新公告
	getNotice();
});

window.onresize=function(){  
    changeDivHeight();
}  


function changeDivHeight(){
	var width=$(window).width()-180-800-60;
	$(".frame_right").width(width);
}
//根据分享条件Id显示特定分享内容
function queryShare(_typeid){
	$("#type").remove();
	//改变typeid
	typeid=_typeid;
	if(_typeid!=0){
		$("#index").removeClass("active");
		var tag="";
		if(_typeid==1){
			tag="技术专区";
		}else if(_typeid==3){
			tag="每日分享";
		}else if(_typeid==5){
			tag="秋名车队";
		}
		var a=$("<li id=\"type\" class=\"active\" role=\"presentation\"><a href=\"#\">"+tag+"</a></li>");
		//创建导航
		$("#tablist").append(a);
	}else{
		$("#index").addClass("active");
	}
	//显示结果
	createShareList(1);
}

function getStatistics(){
	$.ajax({
	   type: "GET",
	   url: "./Web/GetStatistics",
	   success: function(msg){
		   $("#Statisticsshare").html(msg.share);
		   $("#Statisticscomment").html(msg.comment);
		   $("#Statisticsregister").html(msg.register);
		   $("#Statisticspageview").html(msg.pageview);
	   }
	});
}

function getRanks(){
	$.ajax({
	   type: "POST",
	   url: "./User/GetRanks",
	   data: "num=20",
	   success: function(msg){
		   var r=$("#rank");
		   r.append("<tr><td style=\"padding:3px;\"><img src=\"./File/Image/Rank/f.png\" /></td><td style=\"padding:3px;width:250px;\"><span style=\"color:red\">"+msg[0].name+"</span></td></tr>");
		   r.append("<tr><td style=\"padding:3px;\"><img src=\"./File/Image/Rank/s.png\" /></td><td style=\"padding:3px;width:250px;\"><span style=\"color:red\">"+msg[1].name+"</span></td></tr>");
		   r.append("<tr><td style=\"padding:3px;\"><img src=\"./File/Image/Rank/t.png\" /></td><td style=\"padding:3px;width:250px;\"><span style=\"color:red\">"+msg[2].name+"</span></td></tr>");
		   for(var i=3;i<msg.length;i++){
			   r.append("<tr><td>&nbsp;<span style=\"width:30px;height:30px;background:#CDC9C9;color:white\">&nbsp;"+(i+1)+"&nbsp;</span></td><td style=\"padding:3px;\">"+msg[i].name+"</td></tr>");
		   }
	   }
	});
}

function getNotice(){
	$.ajax({
	   type: "POST",
	   url: "./Web/getNotice",
	   success: function(data){
		   createNotice(data[0]);
	   }
	});
}
function createNotice(data){
	var home=$("#notice");
	var title=$("<h4>"+data.title+"</h4>");
	var time=$("<h6>"+data.time+"</h6>");
	var content=$("<h5>"+data.content+"</h5>");
	home.append(title);
	home.append(time);
	home.append(content);
}


























