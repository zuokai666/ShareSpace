$(function($){
	display();
});

function display(){
	$.ajax({
	   type: "POST",
	   url: "../User/getRelation",
	   success: function(msg){
		   if(msg.length==0){
			   layer.msg("暂时没有关系图，赶快邀请你的小伙伴来和你一起互动吧。", {icon: 5,time:1500});
			   return;
		   }
		   if(msg.result=="0"){
			   layer.msg("未登录", {icon: 5,time:1500});
			   return;
		   }
		   //列表形式
		   list(msg);
		   //折线图
		   linechart(msg);
		   //饼图
		   piechart(msg);
		   piechart1(msg);
	   }
	});
}
function list(msg){
	var r=$("#relation");
   for(var i=0;i<msg.length;i++){
	   r.append("<h4>"+msg[i].guestname+","+msg[i].score+"<br /></h4>");
   }
}
function linechart(msg){
	var len=msg.length;
	if(len>10){
		len=10;
	}
	var x=new Array(len);
	var y=new Array(len);
	for(var i=0;i<len;i++){
		x[i]=msg[i].guestname;
		y[i]=Number(msg[i].score);
    }
   var title = {
       text: '关系折线图'   
   };
   var subtitle = {
        text: '提示：只显示前10名小伙伴，详情请查看列表'
   };
   var xAxis = {
       categories:x
   };
   var yAxis = {
      title: {
         text: '关系分数'
      },
      plotLines: [{
         value: 0,
         width: 1,
         color: '#808080'
      }]
   };   
   var plotOptions = {
      line: {
         dataLabels: {
            enabled: true
         }
      }
   };
   var legend = {
      layout: 'vertical',
      align: 'right',
      verticalAlign: 'middle',
      borderWidth: 0
   };
   var series =  [
      {
         name: '分数',
         data:y
      }
   ];

   var json = {};

   json.title = title;
   json.subtitle = subtitle;
   json.xAxis = xAxis;
   json.yAxis = yAxis;
   json.legend = legend;
   json.series = series;
   json.plotOptions = plotOptions;

   $('#line').highcharts(json);
}

function piechart(msg){
	var arr=new Array();
	var total=0;
	var _10per=0;
	var len=msg.length;
	for(var i=0;i<len;i++){
		var score=Number(msg[i].score);
		if(score>0){
			total=total+score;
		}else{
			break;
		}
	}
	for(var i=0;i<len;i++){
		var score=Number(msg[i].score);
		if(score>0){
			if(i==10){
				var a=["其他",1-_10per];
				arr[i]=a;
				break;
			}
			var per=score/total;
			_10per=_10per+per;
			var a=[msg[i].guestname,per];
			arr[i]=a;
		}else{
			break;
		}
	}
	
	
   var chart = {
       plotBackgroundColor: null,
       plotBorderWidth: null,
       plotShadow: false
   };
   var subtitle = {
	        text: '提示：只显示前10名小伙伴，详情请查看列表'
	   };
   var title = {
      text: '小伙伴关系<b>正值</b>占有正分数的比例'   
   };      
   var tooltip = {
      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
   };
   var plotOptions = {
      pie: {
         allowPointSelect: true,
         cursor: 'pointer',
         dataLabels: {
            enabled: true,
            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
            style: {
               color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
            }
         }
      }
   };
   var series= [{
      type: 'pie',
      name: '所占比例',
      data: arr
   }];     
      
   var json = {};   
   json.chart = chart; 
   json.subtitle = subtitle;
   json.title = title;     
   json.tooltip = tooltip;  
   json.series = series;
   json.plotOptions = plotOptions;
   
   $('#pie').highcharts(json);  
}

function piechart1(msg){
	var arr=new Array();
	var total=0;
	var _10per=0;
	var num=0;
	var len=msg.length;
	for(var i=len-1;i>=0;i--){
		var score=Number(msg[i].score);
		if(score<0){
			total=total+score;
		}else{
			break;
		}
	}
	for(var i=len-1;i>=0;i--){
		var score=Number(msg[i].score);
		if(score<0){
			if(i==(len-1-10)){
				var a=["其他",1-_10per];
				arr[num]=a;
				break;
			}
			var per=score/total;
			_10per=_10per+per;
			var a=[msg[i].guestname,per];
			arr[num++]=a;
		}else{
			break;
		}
	}
	
	
   var chart = {
       plotBackgroundColor: null,
       plotBorderWidth: null,
       plotShadow: false
   };
   var subtitle = {
	        text: '提示：只显示前10名小伙伴，详情请查看列表'
	   };
   var title = {
      text: '小伙伴关系<b>负值</b>占有负分数的比例'   
   };      
   var tooltip = {
      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
   };
   var plotOptions = {
      pie: {
         allowPointSelect: true,
         cursor: 'pointer',
         dataLabels: {
            enabled: true,
            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
            style: {
               color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
            }
         }
      }
   };
   var series= [{
      type: 'pie',
      name: '所占比例',
      data: arr
   }];     
      
   var json = {};   
   json.chart = chart; 
   json.subtitle = subtitle;
   json.title = title;     
   json.tooltip = tooltip;  
   json.series = series;
   json.plotOptions = plotOptions;
   
   $('#pie1').highcharts(json);  
}




