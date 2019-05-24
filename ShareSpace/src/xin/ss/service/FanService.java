package xin.ss.service;

import java.util.List;

import xin.ss.model.Fan;
import xin.ss.model.FanDto;

public interface FanService {
	
	/**
	 * 先检查是否存在关系，再添加关注/取消关注
	 * @param fan 【hostid 主人，guestid 我】
	 * @return
	 */
	boolean follow(Fan fan);
	
	/**
	 * 查看自己的关注列表
	 * @param guestid 我的id
	 * @return 关注列表
	 */
	List<FanDto> queryFollow(int guestid);
	
	/**
	 * 查看自己的粉丝列表
	 * @param hostid 我的id
	 * @return 关注列表
	 */
	List<FanDto> queryFan(int hostid);
	
	/**
	 * 查看当前正在浏览的个人主页的主人有无关注，以便在首页显示
	 * @param fan 【hostid 主人，guestid 我】
	 * @return true 表明有关注
	 */
	boolean isExistRelation(Fan fan);
}

































