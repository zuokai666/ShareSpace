package xin.ss.service;

import java.util.List;

import xin.ss.model.Statistics;
import xin.ss.model.User;
import xin.ss.model.UserDto;
import xin.ss.model.UserTag;

public interface UserService {
	
	/**
	 * 根据id可查出所有的用户信息
	 * @param id
	 * @return 用户信息
	 */
	User queryUser(int id);
	/**
	 * 根据id更新location,description
	 * 
	 * @param user [location,description]
	 */
	boolean update(User user);
	/**
	 * 根据id更新用户头像
	 * @param user 需要[id,url]
	 * @return
	 */
	boolean updateUrl(User user);
	/**
	 * 根据id更新名字
	 * @param user [名字]
	 * @return
	 */
	boolean updateName(User user);
	/**
	 * 根据id更新密码
	 * @param user [密码]
	 * @return
	 */
	boolean updatePsw(User user);
	
	
	
	
	/**
	 * 登录
	 * 
	 * @param user 【账号，密码】
	 * @return
	 */
	User login(User user);
	/**
	 * 账户注册使用
	 * 
	 * @param user 【账号，密码，名字，注册时间】
	 * @return
	 */
	boolean register(User user);
	
	/**
	 * 根据userid更新用户的rank
	 * 
	 * @param user 【id和分数】
	 * 
	 * @return
	 */
	boolean updateRank(User user);
	/**
	 * 列出所有的用户rank，用于排名
	 * @return 用户ranks
	 */
	List<UserDto> listUserRanks();
	/**
	 * 检查是否重名
	 * @return true:表示存在此昵称。
	 */
	Boolean checkName(String name);
	/**
	 * 检查是否重账号
	 * @param account
	 * @return true:表示存在此账户。
	 */
	Boolean checkAccount(String account);
	/**
	 * 得到网站统计信息
	 */
	Statistics GetStatistics();
	
	/**
	 * 根据昵称查找是否存在用户
	 * @param name
	 * @return 若存在，返回Id。若不存在，返回-1；
	 */
	int queryIdByName(String name);
	
	/**
	 * 批量更改标签
	 * @param tags
	 * @return
	 */
	boolean updateUserTag(List<UserTag> tags);
	
	boolean delUserTag(int userid);
}

































