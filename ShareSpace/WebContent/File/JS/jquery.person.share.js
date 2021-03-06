var username="";//评论名字
var indexname="";//首页名字
var indexid=0;//首页用户Id
var shareDiv=$("#share");
var commentDiv=$("<div class=\"comments\"></div>");

var _existRelation="";

var CurrentPage=1;
var totalPage=0;
//传入类型条件
var typeid=0;
var userid=0;

$(function($){
	//得到首页名字
	$.ajax({
	   type: "POST",
	   url: "../Person/getMsg",
	   success: function(msg){
		   if(msg.result=="0"){
			   $("body").removeClass("back");
			   $("body").html("Illegal Access");
		   }else{
			   //成功之后再添加分享
			   setPersonIndexMessage(msg);
			   getIndexName();
			   //添加第一页分享,默认typeid为0，显示所有类型分享
			   createShareList(1);
		   }
	   }
	});
});
function setFollow(){
	if(indexname==username){
		$('#fan').html("");
	}else{
		//查看是否存在关系
		checkIsExistRelation();
	}
}

function checkIsExistRelation(){
	$.ajax({
	   type: "POST",
	   url: "../Fan/CheckFollowAction",
	   data: "hostid="+indexid,
	   success: function(msg){
		   if(msg.result=="1"){
			   //存在
			   existRelation();
		   }else{
			   noExistRelation();
	       }
	   }
	});
}

function follow(){
	$.ajax({
	   type: "POST",
	   url: "../Fan/FollowAction",
	   data: "hostid="+indexid,
	   success: function(msg){
		   if(msg.result=="1"){
			   if(_existRelation){
				   noExistRelation();
			   }else{
				   existRelation();
			   }
		   }else{
			   layer.msg(msg.tip, {icon: 5,time:1000});
	       }
	   }
	});
}

function existRelation(){
	$('#fan').html("");
	var button=$("<button type=\"button\" class=\"btn btn-success\" onclick=\"follow()\"></button>");
	var i=$("<i class=\"glyphicon glyphicon-ok\"></i>");
	var c="&nbsp;&nbsp;已关注";
	button.append(i);
	button.append(c);
	$('#fan').append(button);
	_existRelation=true;
}
function noExistRelation(){
	$('#fan').html("");
	var button=$("<button type=\"button\" class=\"btn btn-warning\" onclick=\"follow()\"></button>");
	var i=$("<i class=\"glyphicon glyphicon-plus\"></i>");
	var c="&nbsp;&nbsp;关注";
	button.append(i);
	button.append(c);
	$('#fan').append(button);
	_existRelation=false;
}

//设置个人首页信息
function setPersonIndexMessage(msg){
	//设计显示模板1.1
	indexid=msg.id;
	$("#name").html(msg.name);
	indexname=msg.name;
	$("#url").attr("src",".."+msg.url);
	$("#description").html(msg.description);
	$("#location").html(msg.location);
	$("#registertime").html(msg.registertime);
	$("#rank").html(msg.rank);
	$("#follownumber").html(msg.follownumber);
	$("#fannumber").html(msg.fannumber);
	//填充标签
    var _tagstr="";
    for(var i=0;i<msg.taglist.length;i++){
	    _tagstr=_tagstr+msg.taglist[i].name+"，";
    }
    if(_tagstr==""){
    	_tagstr="暂无。";
    }
    $("#tag").html(_tagstr.substring(0, _tagstr.length-1));
    //填充认证区域
    if(msg.isauthentication=="1"){
    	$("#title").html(msg.title);
    	var str="<button type=\"button\" class=\"btn btn-success\">经ShareSpace验证</button><p>"+msg.title+"<p>";
    	$("#verify").html(str);
    }else{
    	var str="<button type=\"button\" class=\"btn btn-danger\">未经ShareSpace验证</button>";
    	$("#verify").html(str);
    }
}
//得到评论名字
function getIndexName(){
	$.ajax({
	   type: "POST",
	   url: "../User/GetName",
	   success: function(msg){
		   if(msg.result=="1"){
			   username=msg.tip;
			   $("#status").html(msg.tip+"&nbsp;&nbsp;<a href=\"../User/Welcome\">个人中心</a>&nbsp;&nbsp;<a href=\"../User/LogOff\">退出</a>");
			   //所有加载完毕后判断是否是自己的主页来显示按钮及后续的删除
			   setFollow();
		   }else{
			   username="￥_￥";
			   //未登录游客查看
			   noExistRelation();
			   $("#status").html("<a href=\"../User/Register\">注册</a>&nbsp;&nbsp;<a href=\"../User/Login\">登录</a>");
		   }
	   }
	});
}

function createShareList(currentPage){
	$("#share").empty();
	$.ajax({
	   type: "POST",
	   url: "../Share/listSharesById",
	   data: "&currentPage="+currentPage+"&typeid="+typeid,
	   success: function(page){
		   createShares(page);
		   //添加图片方法效果
		   maxImage();
	   }
	});
}

function maxImage(){
	$("img").each(function(){
	    $(this).click(function(){
	    	var src=$(this).attr("src");
	    	var image = new Image();
	        image.src = src;
	        var naturalWidth = image.width;
	        var ww=$(window).width()-600;
	        if(ww<naturalWidth){
	        	naturalWidth=ww;
	        }
			layer.open({
			  type: 1,
			  title: false,
			  closeBtn: 0,
			  area: naturalWidth+'px',
			  skin: 'layui-layer-molv',
			  shadeClose: true,
			  content: "<img alt=\"图片丢失\" width=\""+naturalWidth+"px;\" src=\""+$(this).attr("src")+"\" />"
			});
		});
	});
}

function createShares(page){
	var count=page.count;
    totalPage=page.totalPage;
    var pageSize=page.pageSize;
    var currentPage=page.currentPage;
    var shareList=page.shareList;
    var length=page.shareList.length;

    for(var i=0;i<length;i++){
    	createshare(shareList[i]);
    }
    //添加分页
    if(length!=0){
    	var page=$("<div class=\"page\"><nav><ul class=\"pagination\">" +
        		"<li><a href=\"javascript:minpage()\">&laquo;</a></li>" +
        		createPages(totalPage)+
        		"<li><a href=\"javascript:maxpage()\">&raquo;</a></li>" +
        		"</ul></nav></div>");
        shareDiv.append(page);
    }else{
    	layer.msg("该用户还未发布过分享...", {icon: 5,time:2000});
    }
    
}
//为每个名字添加个人首页路径，并且防止缓存
function Link(id,name){
	return "<a href=\"../User/Index?id="+id+"&math="+Math.random()+"\">"+name+"</a>";
}
function createshare(share){
	var content=$("<div id="+share.id+" class=\"content\"></div>");
	var img="<img class=\"img-circle\" width=\"56px\" src=\".."+share.url+"\" />";
	
	var div_img=$("<div style=\"float:left;\"></div>");
	div_img.append(img);
	
	var h6_name=$("<h6>"+Link(share.userid,share.username)+"</h6>");
	var h6_time=$("<h6>"+timeFormatter(share.publishtime)+"&nbsp;&nbsp;分类:<span style=\"color:#005a94;\">"+share.typename+"</span></h6>");
	var div_name=$("<div style=\"float:left;margin-left:10px;\"></div>");
	div_name.append(h6_name);
	div_name.append(h6_time);
	
	//第三序列[操作]
	var div_operate=$("<div style=\"float:right;\"></div>");
	var btn_group=$("<div class=\"btn-group\"></div>");
	var button=$("<span class=\"btn dropdown-toggle\" data-toggle=\"dropdown\"></span>");
	var caret=$("<span class=\"caret\"></span>");
	var only=$("<span class=\"sr-only\">Toggle Dropdown</span>");
	button.append(caret);
	button.append(only);
	var dropdown=$("<ul class=\"dropdown-menu dropdown-menu-right\" role=\"menu\">");
	var li=$("<li><a href=\"javascript:report('"+share.id+"')\"><i class='glyphicon glyphicon-earphone'></i>&nbsp;&nbsp;我要举报此分享</a></li>");
	if(indexname==username){
		li=$("<li><a href=\"javascript:deleteShare('"+share.id+"')\">" +
				"<i class='glyphicon glyphicon-trash'></i>&nbsp;&nbsp;我要删除此分享</a></li>");
	}
	dropdown.append(li);
	btn_group.append(button);
	btn_group.append(dropdown);
	div_operate.append(btn_group);
	
	var h4=$("<h4>"+share.content.split("&lt;br /&gt;").join('<br/>')+"</h4>");
	var div_content=$("<div style=\"clear:both;padding-top:2px;\"></div>");
	div_content.append(h4);
	var div0=$("<div class=\"images\"></div>");
	var imageslength=share.imageList.length;
	for(var i=0;i<imageslength;i++){
		div0.append("<img alt=\"图片丢失\" src=\".."+share.imageList[i].url+"\" width=\"200px;\" />");
    }
	var div1=$("<div><hr style=\"margin:0px auto 10px auto;\" />" +
			"<a href=\"javascript:commitGood("+share.id+")\"><i class=\"glyphicon glyphicon-thumbs-up\"></i>&nbsp;推荐(<span id=\"good"+share.id+"\">"+share.goodnumber+"</span>)&nbsp;&nbsp;</a>" +
			"<a href=\"javascript:commitBad("+share.id+")\"><i class=\"glyphicon glyphicon-thumbs-down\"></i>&nbsp;反对(<span id=\"bad"+share.id+"\">"+share.badnumber+"</span>)&nbsp;&nbsp;</a>" +
			"<a href=\"javascript:commentShare("+share.id+")\"><i class=\"glyphicon glyphicon-comment\"></i>&nbsp;评论&nbsp;&nbsp;</a>" +
			"</div>");
	commentDiv=$("<div class=\"comments\"></div>");
	//创建Comments
	creatComments(share.commentList);

	content.append(div_img);
	content.append(div_name);
	content.append(div_operate);//3
	
	content.append(div_content);
	content.append(div0);
	content.append(div1);
	content.append(commentDiv);
	content.append("<div style=\"clear:both;\"></div>");
	
	var panel=$("<div id='isHide_"+share.id+"' class=\"panel panel-default\" style=\"border-radius:0px;\"></div>");
	panel.append(content);
	shareDiv.append(panel);
}


function creatComments(comments){
	var length=comments.length;
	for(var i=0;i<length;i++){
		creatComment(comments[i]);
    }
}
function creatComment(comment){
	var id=comment.id;
	var username=comment.username;
	var content=comment.content;
	var commenttime=comment.commenttime;
	
	var div=$("<div style=\"\"></div>");
	var img="<div style=\"clear:both;float:left;\"><img class=\"img-rounded\" style=\"vertical-align:text-top;\" width=\"35px\" src=\".."+comment.url+"\" /></div>";
	var con=comment.content.split("&lt;br /&gt;").join('<br/>');
	var title=$("<p style=\"margin:0px;\">"+Link(comment.userid,comment.username)+"：<span style=\"word-wrap:break-word;\">"+con+"</span></p>");
	var _i_comment="<a href=\"#\"><i class=\"glyphicon glyphicon-comment\"></i></a>";
	var time=$("<h6 style=\"margin-top:0px;\">"+timeFormatter(comment.commenttime)+"&nbsp;&nbsp;&nbsp;&nbsp;"+_i_comment+"</h6>");
	
	div.append(img);
	var div_name_con=$("<div style=\"float:left;margin-left:10px;width:560px;\"></div>");
	div_name_con.append(title);
	div_name_con.append(time);
	div.append(div_name_con);
	commentDiv.append(div);
}

function timeFormatter(time){
	time=time.substring(0,time.length-2);
	var date=time.split(" ");
	if(judgeTime(date[0])){
		return "今日&nbsp;"+date[1];
	}
	return time;
}
function judgeTime(time){
	var SystemDate=new Date().toLocaleDateString();
	var date1=SystemDate.split("/");//"2016/9/5"
	var num=Number(date1[1]);
	var num1=Number(date1[2]);
	if((num/10)<1){
		date1[1]="0"+date1[1];
	}
	if((num1/10)<1){
		date1[2]="0"+date1[2];
	}
	var date2=time.split("-");//2016-09-05
	if(date1[0]==date2[0] && date1[1]==date2[1] && date1[2]==date2[2]){
		return true;
	}
	return false;
}

function page(index){
	createShareList(index);
	CurrentPage=index;
}
function minpage(){
	if(CurrentPage==1){
		layer.msg("已到达第一页", {icon: 5,time:1500});
		return;
	}
	createShareList(--CurrentPage);
}
function maxpage(){
	if(CurrentPage==totalPage){
		layer.msg("已到达最后一页", {icon: 5,time:1500});
		return;
	}
	createShareList(++CurrentPage);
}
//从1到max.
function createPages(max){
	var res="";
	for(var i=1;i<=max;i++){
		res=res+"<li><a href=\"javascript:page("+i+")\">"+i+"</a></li>";
		if(i==18){
			break;
		}
	}
	return res;
}



var first=true;//第一次创建
var shareid_commit=-1;
function commentShare(shareid){
	shareid_commit=shareid;
	if(first){
		if(username==""){
			username="￥_￥";
		}
		var div=$("<div id=\"customComment\"></div>");
		var title=$("<h6><span style=\"color:#005a94;\">"+username+"&nbsp;</span>&nbsp;评论:</h6>");
		var con=$("<textarea placeholder=\"请输入评论\" class=\"form-control\" rows=\"3\" id=\"comment\"></textarea>");
		var button1=$("<input type=\"button\" class=\"btn btn-default\" style=\"margin:8px 0px 0px 0px;\" onclick=\"commitComment()\" name=\"submit\" value=\"提交\" />&nbsp;&nbsp;");
		var button2=$("<input type=\"button\" class=\"btn btn-default\" style=\"margin:8px 0px 0px 8px;\" onclick=\"removecustomComment()\" value=\"关闭\" />");
		div.append(title);
		div.append(con);
		div.append(button1);
		div.append(button2);
		$("#"+shareid).append(div);
		first=false;
	}else{
		removecustomComment();
	}
}
function removecustomComment(){
	$("#customComment").remove();
	first=true;
}


function commitComment(){
	var c=document.getElementById('comment').value;
	if(verify(c)){
		layer.msg("评论不能为空哦", {icon: 6,time:1500});
		return;
	}
	$.ajax({
	   type: "POST",
	   url: "../Comment/CommitComment",
	   data:"shareid="+shareid_commit+"&content="+c,
	   success: function(msg){
		   if(msg.result=="1"){
			   removecustomComment();
			    var div=$("<div></div>");
				var title=$("<h6><span style=\"color:#005a94;\">"+username+"&nbsp;</span>&nbsp;评论于&nbsp;<span>"+new Date().toLocaleTimeString()+"</span></h6>");
				var con=$("<h5><xmp>"+$('<div/>').text(c).html()+"</xmp></h5>");
				div.append(title);
				div.append(con);
				$("#"+shareid_commit).append(div);
		   }else{
			   layer.msg(msg.tip, {icon: 5,time:2000});
		   }
	   }
	});
}

function commitGood(shareid){
	$.ajax({
	   type: "POST",
	   url: "../Share/CommitGood",
	   data:"shareid="+shareid,
	   success: function(msg){
		   if(msg.result=="1"){
			   layer.msg(msg.tip, {icon: 6,time:2000});
			   //推荐+1
			   var good=$("#good"+shareid).html();
			   var goodnum=Number(good)+1;
			   $("#good"+shareid).html(goodnum);
		   }else{
			   layer.msg(msg.tip, {icon: 5,time:2000});
		   }
	   }
	});
}

function commitBad(shareid){
	$.ajax({
	   type: "POST",
	   url: "../Share/CommitBad",
	   data:"shareid="+shareid,
	   success: function(msg){
		   if(msg.result=="1"){
			   layer.msg(msg.tip, {icon: 6,time:2000});
			   //反对+1
			   var bad=$("#bad"+shareid).html();
			   var badnum=Number(bad)+1;
			   $("#bad"+shareid).html(badnum);
		   }else{
			   layer.msg(msg.tip, {icon: 5,time:2000});
		   }
	   }
	});
}


function report(shareid){
	var home=$("<div></div>");
	var a=$("<h4><i class='glyphicon glyphicon-question-sign'></i>&nbsp;&nbsp;您为什么要举报此信息？</h4>");
	var b=$("<div><input type='radio' value='1' name='report_type' />&nbsp;色情暴力&nbsp;&nbsp;&nbsp;&nbsp;" +
			"<input type='radio' value='2' name='report_type' />&nbsp;骚扰谩骂&nbsp;&nbsp;&nbsp;&nbsp;" +
			"<input type='radio' value='3' name='report_type' />&nbsp;广告欺诈&nbsp;&nbsp;&nbsp;&nbsp;</div>");
	var c=$("<div><input type='radio' value='4'  name='report_type'/>&nbsp;病毒木马&nbsp;&nbsp;&nbsp;&nbsp;" +
			"<input type='radio' value='5' name='report_type' />&nbsp;反对政治&nbsp;&nbsp;&nbsp;&nbsp;" +
			"<input type='radio' value='6' name='report_type' />&nbsp;其它问题&nbsp;&nbsp;&nbsp;&nbsp;</div>");
	var d=$("<h4><i class='glyphicon glyphicon-list-alt'></i>&nbsp;&nbsp;举报说明（可选）</h4>");
	var e=$("<input type='text' name='report_content' style='width:100%;' />");
	home.append(a);
	home.append(b);
	home.append(c);
	home.append(d);
	home.append(e);
	
	layer.open({
	  type: 1,
	  title: "<i class='glyphicon glyphicon-earphone'></i>&nbsp;&nbsp;举报",
	  closeBtn: 0,
	  btn: ['提交', '取消'],
	  area: ['350px','270px'],
	  skin: 'layui-layer-molv',
	  shadeClose: true,
	  content: "<div style='padding:10px;'>"+home.html()+"</div>",
	  yes: function(index, layero){
		  var type=$("input[name='report_type']:checked").val(); 
		  var content=$("input[name='report_content']").val();
		  if(typeof type=='undefined'){
			  layer.msg("举报类型不可为空", {icon: 6,time:1000});
			  return;
		  }
		  $.ajax({
			   type: "POST",
			   url: "../Web/submitReport",
			   data:"shareid="+shareid+"&type="+type+"&content="+content,
			   success: function(msg){
				   if(msg.result=="1"){
					   layer.msg(msg.tip, {icon: 6,time:1000},function(){
						   layer.close(index);
					   });
				   }else{
					   layer.msg(msg.tip, {icon: 5,time:1000});
				   }
			   }
		  });
	  }
	});
}


function deleteShare(shareid){
	$.ajax({
	   type: "POST",
	   url: "../Share/delShare",
	   data:"shareid="+shareid,
	   success: function(msg){
		   if(msg.result=="1"){
			   $("#isHide_"+shareid).fadeTo("slow", 0.01, function(){//fade
				    $(this).slideUp("slow", function() {//slide up
				        $(this).remove();//then remove from the DOM
				    });
				});
		   }else{
			   layer.msg(msg.tip, {icon: 5,time:1000});
		   }
	   }
	});
	
	
}



























