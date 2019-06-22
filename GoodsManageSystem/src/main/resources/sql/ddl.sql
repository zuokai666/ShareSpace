drop table if exists Goods;
create table Goods(
	id int(11) not null auto_increment,
	activity int(1) not null,
	version int(11) not null,
	goodsType varchar(255) not null comment '物品类型',
	longName varchar(255) not null comment '长名称',
	shortName varchar(255) default null comment '短名称',
	inputTime varchar(19) not null comment '添加时间yyyy-MM-dd HH:mm:ss',
	beginTime varchar(19) not null comment '开始时间yyyy-MM-dd HH:mm:ss',
	endTime varchar(19) not null comment '结束时间yyyy-MM-dd HH:mm:ss',
	price decimal(6,2) not null comment '单价max9999.99',
	unit varchar(19) not null comment '单位',
	distributor varchar(255) default null comment '经销商',
	goodsFactory varchar(255) default null comment '厂家',
	remark varchar(255) default null comment '备注',
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key(id)
)engine=innodb default charset=utf8 comment='日常物品模型';




drop table if exists Distributor;
create table Distributor(
	id int(11) not null auto_increment,
	activity int(1) not null,
	version int(11) not null,
	name varchar(255) not null,
	address varchar(255) not null,
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key(id)
)engine=innodb default charset=utf8;



drop table if exists GoodsFactory;
create table GoodsFactory(
	id int(11) not null auto_increment,
	activity int(1) not null,
	version int(11) not null,
	name varchar(255) not null,
	address varchar(255) not null,
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key(id)
)engine=innodb default charset=utf8;



drop table if exists GoodsType;
create table GoodsType(
	id int(11) not null auto_increment,
	activity int(1) not null,
	version int(11) not null,
	name varchar(255) not null,
	update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key(id)
)engine=innodb default charset=utf8;


