var $table = $('#table');
var $add = $('#add');

function initTable() {
    $table.bootstrapTable({
    	url:"../User/getTag",
    	reinit: false,
    	pagination:true,
    	toolbar:"#toolbar",
    	search:true,
        columns:
            [
			   {
                    field: 'id',
                    title: '编号',
                    align: 'center'
                }, {
                    field: 'name',
                    title: '标签名称',
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

function operateFormatter(value, row, index) {
	return '<a class="update" href="javascript:void(0)" title="修改">'+
			'<i class="glyphicon glyphicon-pencil"></i></a>';
}

window.operateEvents = {
    'click .update': function (e, value, row, index) {
    	//设置模态框标题
    	$('#myModalLabel').html('修改标签');
    	//打开模态框
    	$('#myModal').modal();
    	//设置值
    	$("input[name='id']").val(row.id);
    	$("input[name='name']").val(row.name);
    }
};



$(function () {
	initTable();
	
	$add.click(function () {
		//设置模态框标题
    	$('#myModalLabel').html('新增标签');
		//打开模态框
    	$('#myModal').modal();
    	//设置空值
    	$("input[name='id']").val("");
    	$("input[name='name']").val("");
	});
	
	$("#submit").click(function(){
		var id=$("input[name='id']").val();
		var name=$("input[name='name']").val();
		if(id==""){
			$.ajax({
			   type: "POST",
			   url: "../Admin/InsertTag",
			   data:"name="+name,
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
			$.ajax({
			   type: "POST",
			   url: "../Admin/UpdateTag",
			   data:'id='+id+"&name="+name,
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






























