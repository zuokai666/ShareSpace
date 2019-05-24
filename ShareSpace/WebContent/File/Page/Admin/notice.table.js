var $table = $('#table');
var $add = $('#add');

function initTable() {
    $table.bootstrapTable({
    	url:"../Web/getNotice",
    	reinit: false,
    	pagination:true,
    	toolbar:"#toolbar",
    	search:true,
        columns:
            [
			   {
                    field: 'id',
                    title: '编号',
                    sortable: true,
                    checkbox: true,
                    visible: false,
                    align: 'center'
                }, {
                    field: 'title',
                    title: '标题',
                    align: 'center',
                    width: '100px',
                    formatter: nameFormatter
                }, {
                    field: 'content',
                    title: '内容',
                    align: 'left',
                    formatter: contentFormatter
                }, {
                    field: 'time',
                    title: '时间',
                    align: 'center',
                    width: '180px'
                },  {
                	field: 'operate',
                    title: '操作',
                    align: 'center',
                    width: '80px',
                    events: operateEvents,
                    formatter: operateFormatter
                }
            ]
    });
}

function displayContent(con){
	layer.open({
	  type: 0,
	  title: '<i class="glyphicon glyphicon-pushpin"></i>&nbsp;&nbsp;公告内容',
	  shade: [0],
	  area: '400px',
	  skin: 'layui-layer-molv',
	  shadeClose: true,
	  content: con
	});
}

function nameFormatter(value, row, index) {
	var dis=value;
	if(value.length>5){
		dis=value.substring(0,5)+"...";
	}
	return "<a title='"+value+"' href=\"javascript:void(0)\">"+dis+"</a>";
}
function contentFormatter(value, row, index) {
	var dis=value;
	if(value.length>25){
		dis=value.substring(0,25)+"...";
	}
	return "<span title=\"点我查看详细\" style=\"cursor:pointer;\" onclick=\"displayContent('"+value+"')\">"+dis+"</span>";
}

function operateFormatter(value, row, index) {
	return [
            '<a class="update" href="javascript:void(0)" title="修改">',
            '<i class="glyphicon glyphicon-pencil"></i>',
            '</a>&nbsp;&nbsp;&nbsp;',
            '<a class="remove" href="javascript:void(0)" title="删除">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join('');
}

window.operateEvents = {
    'click .update': function (e, value, row, index) {
    	//设置模态框标题
    	$('#myModalLabel').html('修改公告');
    	//打开模态框
    	$('#myModal').modal();
    	//设置值
    	$("input[name='id']").val(row.id);
    	$("input[name='title']").val(row.title);
    	$("#content").val(row.content);
    },
    'click .remove': function (e, value, row, index) {
    	$.ajax({
		   type: "POST",
		   url: "../Admin/delNotice",
		   data:"id="+row.id,
		   success: function(data){
			   if(data.result=="1"){
				   layer.msg(data.tip, {icon: 6,time:1000},function(){
					   $table.bootstrapTable('remove', {
				            field: 'id',
				            values: [row.id]
				        });
				   });
				   
			   }else{
				   layer.msg(data.tip, {icon: 5,time:1000});
			   }
		   }
		});
    	
       
    }
};



$(function () {
	initTable();
	
	$add.click(function () {
		//设置模态框标题
    	$('#myModalLabel').html('新增公告');
		//打开模态框
    	$('#myModal').modal();
    	//设置空值
    	$("input[name='id']").val("");
    	$("input[name='title']").val("");
    	$("#content").val("");
	});
	
	$("#submit").click(function(){
		var id=$("input[name='id']").val();
		var title=$("input[name='title']").val();
    	var con=$("#content").val();
		if(id==""){
			//alert("添加");
			$.ajax({
			   type: "POST",
			   url: "../Admin/InsertNotice",
			   data:"title="+title+"&content="+con,
			   success: function(data){
				   if(data.result=="1"){
					   layer.msg(data.tip, {icon: 6,time:1000});
					   //关闭模态框
					   $('#myModal').modal('hide');
				   }else{
					   layer.msg(data.tip, {icon: 5,time:1000});
				   }
			   }
			});
		}else{
			//alert("修改");
			$.ajax({
			   type: "POST",
			   url: "../Admin/UpdateNotice",
			   data:'id='+id+"&title="+title+"&content="+con,
			   success: function(data){
				   if(data.result=="1"){
					   layer.msg(data.tip, {icon: 6,time:1000});
					   //关闭模态框
					   $('#myModal').modal('hide');
				   }else{
					   layer.msg(data.tip, {icon: 5,time:1000});
				   }
			   }
			});
		}
	});
	
	
});






























