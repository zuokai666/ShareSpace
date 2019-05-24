var $table = $('#table');

function initTable() {
    $table.bootstrapTable({
    	url:"../Admin/getReport",
    	reinit: false,
    	pagination:true,
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
                    field: 'username',
                    title: '举报人',
                    align: 'center',
                    width: '100px',
                    formatter: nameFormatter
                }, {
                    field: 'shareid',
                    title: '被举报分享',
                    align: 'left',
                    width: '80px',
                    formatter: shareidFormatter
                }, {
                    field: 'content',
                    title: '内容',
                    align: 'left',
                    formatter: contentFormatter
                }, {
                    field: 'type',
                    title: '类型',
                    align: 'left',
                    width: '80px',
                    formatter: typeFormatter
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
	  title: '<i class="glyphicon glyphicon-pushpin"></i>&nbsp;&nbsp;举报说明',
	  shade: [0],
	  area: '400px',
	  skin: 'layui-layer-molv',
	  shadeClose: true,
	  content: con
	});
}
function shareidFormatter(value, row, index){
	return value;
}
function typeFormatter(value, row, index) {
	if(value=="1"){
		return "色情暴力";
	}else if(value=="2"){
		return "骚扰谩骂";
	}else if(value=="3"){
		return "广告欺诈";
	}else if(value=="4"){
		return "病毒木马";
	}else if(value=="5"){
		return "反对政治";
	}else if(value=="6"){
		return "其它问题";
	}
}
function nameFormatter(value, row, index) {
	var dis=value;
	if(value.length>5){
		dis=value.substring(0,5)+"...";
	}
	return "<a title='"+value+"' href=\"javascript:void(0)\">"+dis+"</a>";
}
function contentFormatter(value, row, index) {
	if(value==""){
		return "空空如也...";
	}
	var dis=value;
	if(value.length>25){
		dis=value.substring(0,25)+"...";
	}
	return "<span title=\"点我查看详细\" style=\"cursor:pointer;\" onclick=\"displayContent('"+value+"')\">"+dis+"</span>";
}

function operateFormatter(value, row, index) {
	return '<a class="remove" href="javascript:void(0)" title="删除">'+
			'<i class="glyphicon glyphicon-remove"></i>删除</a>';
}

window.operateEvents = {
    'click .remove': function (e, value, row, index) {
    	$.ajax({
		   type: "POST",
		   url: "../Admin/delReport",
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
	
	
	
	
});






























