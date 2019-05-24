var $table = $('#table');

function initTable() {
    $table.bootstrapTable({
    	url:"../Authentication/ListAction",
    	reinit: false,
    	pagination:true,
        columns:
            [
				{
                    field: 'id',
                    title: '编号',
                    sortable: true,
                    visible: false,
                    align: 'center'
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


function urlFormatter(value, row, index) {
    return "<img src=\".."+value+"\" width=\"100px;\" />";
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
    return "<a class=\"like\" href=\"javascript:void(0)\">取消认证</a>";
}

window.operateEvents = {
    'click .like': function (e, value, row, index) {
    	$.ajax({
		   type: "POST",
		   url: "../Authentication/CancelAction",
		   data:"id="+row.id,
		   success: function(msg){
			   if(msg.result=="1"){
				   layer.msg(msg.tip, {icon: 6,time:1000},function(){
					   $table.bootstrapTable('updateCell', {
						    index: index,
				            field: 'status',
				            value: '3'
				        });
					   $table.bootstrapTable('updateCell', {
						    index: index,
				            field: 'result',
				            value: '用户已取消'
				        });
				   });
			   }else{
				   layer.msg(msg.tip, {icon: 5});
			   }
		   }
 		});
    }
};

$(function () {
	initTable();
});






























