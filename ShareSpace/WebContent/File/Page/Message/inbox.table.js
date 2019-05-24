var $table = $('#table');
var $remove = $('#remove');
var $setRead = $('#setRead');

function initTable() {
    $table.bootstrapTable({
    	url:"../Message/getInBoxMessage",
    	reinit: false,
    	pagination:true,
    	toolbar:"#toolbar",
    	search:true,
        columns:
            [
			   	{
                    checkbox: true,
                }, {
                    field: 'id',
                    title: '编号',
                    sortable: true,
                    checkbox: true,
                    visible: false,
                    align: 'center'
                }, {
                    field: 'guestname',
                    title: '发件人',
                    align: 'center',
                    width: '100px',
                    formatter: nameFormatter
                }, {
                    field: 'content',
                    title: '内容',
                    align: 'left',
                    formatter: contentFormatter
                }, {
                    field: 'isread',
                    title: '状态',
                    align: 'center',
                    formatter: readFormatter
                }, {
                    field: 'time',
                    title: '时间',
                    align: 'center',
                    width: '180px'
                }
            ]
    });
}

function displayContent(con){
	layer.open({
	  type: 0,
	  title: '<i class="glyphicon glyphicon-pushpin"></i>&nbsp;&nbsp;邮件内容',
	  shade: [0],
	  area: '400px',
	  skin: 'layui-layer-molv',
	  shadeClose: true,
	  content: con
	});
}

function readFormatter(value, row, index) {
	if(value=="0"){
		return "未读";
	}else if(value=="1"){
		return "已读";
	}else{
		return "错误";
	}
}
function nameFormatter(value, row, index) {
	var dis=value;
	if(value.length>5){
		dis=value.substring(0,5)+"...";
	}
	return "<a target='_blank' title='"+value+"' href=\"../User/Index?id="+row.guestid+"&math="+Math.random()+"\">"+dis+"</a>";
}
function contentFormatter(value, row, index) {
	var dis=value;
	if(value.length>25){
		dis=value.substring(0,25)+"...";
	}
	return "<span title=\"点我查看详细\" style=\"cursor:pointer;\" onclick=\"displayContent('"+value+"')\">"+dis+"</span>";
}

function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

$(function () {
	initTable();
	setReadNum();
	$remove.click(function () {
	    var ids = getIdSelections();
	    if(ids==""){
	    	layer.msg("至少选择一条", {icon: 5});
	    	return;
	    }
	    $.ajax({
		   type: "POST",
		   url: "../Message/delInBoxMessage",
		   data:"ids="+ids,
		   success: function(msg){
			   if(msg.result=="1"){
				   layer.msg(msg.tip, {icon: 6,time:1000},function(){
					   $table.bootstrapTable('remove', {
					        field: 'id',
					        values: ids
					   });
				   });
			   }else{
				   layer.msg(msg.tip, {icon: 5});
			   }
		   }
 		});
	});
	
	$setRead.click(function () {
	    var ids = getIdSelections();
	    if(ids==""){
	    	layer.msg("至少选择一条", {icon: 5});
	    	return;
	    }
	    $.ajax({
		   type: "POST",
		   url: "../Message/updateInBoxMessage",
		   data:"ids="+ids,
		   success: function(msg){
			   if(msg.result=="1"){
				   layer.msg(msg.tip, {icon: 6,time:1000});
			   }else{
				   layer.msg(msg.tip, {icon: 5});
			   }
		   }
 		});
	});
	
	
});






























