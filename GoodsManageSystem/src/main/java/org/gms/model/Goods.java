package org.gms.model;

import java.math.BigDecimal;

/**
 * 日常物品模型
 * 
 * @author king
 * @date 2019-06-20 22:10:23
 * 
 */
@SuppressWarnings("unused")
public class Goods extends Entity{
	
	private GoodsType goodsType;//物品类型
	private String longName;//长名称
	private String shortName;//短名称
	private String inputTime;//添加时间
	private String beginTime;//开始时间
	private String endTime;//结束时间
	private BigDecimal price;//单价---decimal(6,2)---最大---9999.99
	private Distributor distributor;//经销商
	private GoodsFactory goodsFactory;//厂家
	private String remark;//备注
	private String update_time;//更新时间
}

/**

Decimal为SQL Server、MySql等数据库的一种数据类型，不属于浮点数类型，可以在定义时划定整数部分以及小数部分的位数。
使用精确小数类型不仅能够保证数据计算更为精确，还可以节省储存空间，例如百分比使用decimal(4,2)即可。

decimal(10,2)中的“2”表示小数部分的位数
“10”指的是整数部分加小数部分的总长度


*/