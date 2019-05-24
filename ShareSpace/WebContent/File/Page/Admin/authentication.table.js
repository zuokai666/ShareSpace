var $table = $('#table');

function initTable() {
    $table.bootstrapTable({
    	url:"../Admin/ListAuths",
    	reinit: false,
    	pagination:true,
    	detailView:true,
    	detailFormatter:detailFormatter,
        columns:
            [
				{
                    field: 'id',
                    title: '编号',
                    sortable: true,
                    visible: false,
                    align: 'center'
                }, {
                    field: 'userid',
                    title: '用户',
                    align: 'center',
                    formatter: useridFormatter
                }, {
                    field: 'title',
                    title: '头衔',
                    align: 'center'
                }, {
                    field: 'content',
                    title: '阐述',
                    align: 'center'
                }, {
                    field: 'url',
                    title: '图片',
                    align: 'center',
                    formatter: urlFormatter
                }, {
                    field: 'time',
                    title: '时间',
                    align: 'center'
                }, {
                    field: 'status',
                    title: '状态',
                    align: 'center',
                    formatter: statusFormatter
                }, {
                    field: 'result',
                    title: '理由',
                    align: 'center'
                },  {
                	field: 'operate',
                    title: '操作',
                    align: 'center',
                    events: operateEvents,
                    formatter: operateFormatter
                }
            ]
    });
}

function detailFormatter(value, row, index) {
    var html = [];
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
    });
    return html.join('');
}

function urlFormatter(value, row, index) {
    return "<img src=\".."+value+"\" width=\"100px;\" />";
}
function useridFormatter(value, row, index) {
	return "<a target=\"_blank\" href=\"../User/Index?id="+value+"&math="+Math.random()+"\">"+value+"</a>";
}
function statusFormatter(value, row, index) {
	if(value=="1"){
		return "用户提交";
	}else if(value=="2"){
		return "审核中";
	}else if(value=="3"){
		return "用户取消";
	}else if(value=="4"){
		return "审核通过";
	}else if(value=="5"){
		return "审核未通过";
	}
}
function operateFormatter(value, row, index) {
    return "<a class=\"operate\" href=\"javascript:void(0)\">操作</a>";
}
var _index="";
window.operateEvents = {
    'click .operate': function (e, value, row, index) {
    	//打开模态框
    	$('#myModal').modal();
    	//设置值
    	$("input[name='modalid']").attr("value",row.id);
    	$("#modalresult").val("");
    	_index=index;
    }
};

$(function () {
	$('#side-menu').metisMenu();
	initTable();
	$("#submit").click(function(){
		var id=$("input[name='modalid']").val();
		var status=$("#modalstatus option:selected").val();
		var result=$("#modalresult").val();
		var s="id="+id+"&status="+status+"&result="+result;
		$.ajax({
	        type: "POST",
	        url:"../Admin/UpdateAuth",
	        data:s,
			success: function(msg){
			   if(msg.result=="1"){
				   $('#myModal').modal('hide');
				   //layer.msg(msg.tip, {icon: 6,time:1000});
				   //更改表格
				   $table.bootstrapTable('updateCell', {
					    index: _index,
			            field: 'status',
			            value: status
			        });
				   $table.bootstrapTable('updateCell', {
					    index: _index,
			            field: 'result',
			            value: result
			        });
			   }else{
				   layer.msg(msg.tip, {icon: 5,time:1000});
			   }
			} 
		}); 
	});
});

function queryStatus(status){
	$.ajax({
        type: "POST",
        url:"../Admin/ListAuths?status="+status,
		success: function(msg){
			$table.bootstrapTable('load',msg); 
		} 
	}); 
}






























