var $table = $('#table');
var $remove = $('#remove');
var $add = $('#add');
var $update = $('#update');

function initTable() {
    $table.bootstrapTable({
    	url:"../goodsType/listAll",
    	reinit: false,
    	pagination:true,
    	toolbar:"#toolbar",
    	search:true,
    	responseHandler: function (res) {
            return res.body.data;
        },
        columns:
            [
			   	{
                    checkbox: true,
                }, {
                    field: 'id',
                    title: '编号',
                    align: 'center'
                }, {
                    field: 'name',
                    title: '名称',
                    align: 'center',
                    width: '200px'
                }, {
                    field: 'version',
                    title: '版本',
                    align: 'center'
                }, {
                    field: 'activity',
                    title: '状态',
                    align: 'center'
                }, {
                    field: 'update_time',
                    title: '时间',
                    align: 'center',
                    width: '180px',
                    sortable: true
                }
            ]
    });
}


function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

function addButtonEvent(){
	$remove.click(function () {
	    var ids = getIdSelections();
	    if(ids==""){
	    	layer.msg("至少选择一条", {icon: 5});
	    	return;
	    }
	    $.ajax({
		   type: "POST",
		   url: "../goodsType/delInBoxMessage",
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
}

$(function() {
	initTable();
	
});