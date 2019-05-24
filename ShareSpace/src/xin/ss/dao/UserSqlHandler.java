package xin.ss.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import xin.ss.model.User;
import xin.ss.model.UserDto;

public class UserSqlHandler extends AbstractTransactionHandler  {
	
	private static Logger logger = Logger.getLogger(UserSqlHandler.class);  
	/**
	 * 登录使用
	 * @param user 【账号和密码】
	 * @return
	 */
	public User select(User user){
		Connection conn=getConnection();
		User user1=null;
		String sql="select id,account,password,name,registertime,rank,usertype from User where account=? and password=?";
		Object[] params={user.getAccount(),user.getPassword()};
		try {
			user1=runner.query(conn,sql,new BeanHandler<>(User.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return user1;
	}
	
	/**
	 * 根据id查找出用户的所有信息
	 * 
	 * @param id 【userid】
	 * @return User全部信息
	 */
	public User selectById(int id){
		Connection conn=getConnection();
		User user1=null;
		String fields="id,account,name,url,location,description,isauthentication,title,follownumber,fannumber,registertime,rank";
		String sql="select "+fields+" from User where id=?";
		try {
			user1=runner.query(conn,sql,new BeanHandler<>(User.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return user1;
	}
	public int selectIdByName(String name){
		Connection conn=getConnection();
		User user1=null;
		String sql="select id from User where name=?";
		try {
			user1=runner.query(conn,sql,new BeanHandler<>(User.class),name);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		if(user1!=null){
			return user1.getId();
		}
		return -1;
	}
	/**
	 * 查询排名使用，无参，返回所有分数列表
	 * @return
	 */
	public List<UserDto> selectRanks(){
		Connection conn=getConnection();
		List<UserDto> userDto=null;
		String sql="select id,name,rank from User order by rank desc";
		try {
			userDto=runner.query(conn,sql,new BeanListHandler<>(UserDto.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return userDto;
	}
	
	/**
	 * 检查是否重名
	 * 
	 * @return true:表示存在此昵称。
	 */
	public Boolean selectByName(String name){
		Connection conn=getConnection();
		Long count=(long) 0;
		String sql="select count(*) from User where name=?";
		try {
			count=(Long) runner.query(conn,sql,new ScalarHandler<>(),name);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		if(count>1){
			logger.error("存在两个以上相同用户昵称。");
			return true;
		}else if(count==1){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 检查是否重账号
	 * @param account
	 * 
	 * @return true:表示存在此账号。
	 */
	public Boolean selectByAccount(String account){
		Connection conn=getConnection();
		Long count=(long) 0;
		String sql="select count(*) from User where account=?";
		try {
			count=(Long) runner.query(conn,sql,new ScalarHandler<>(),account);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		if(count>1){
			logger.error("存在两个以上相同用户账户。");
			return true;
		}else if(count==1){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 得到用户总数，网站统计使用
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int selectCount(){
		Connection conn=getConnection();
		long count=0;
		try {
			String sql="select count(*) from User";
			count=(Long)runner.query(conn,sql,new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return (int)count;
	}
	/**
	 * 账户注册使用
	 * 
	 * @param user 【账号，密码，名字，注册时间】
	 * @return
	 */
	public boolean insert(User user){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="insert into User(account,password,name,registertime) values(?,?,?,?)";
		Object[] obj=new Object[4];
		obj[0]=user.getAccount();
		obj[1]=user.getPassword();
		obj[2]=user.getName();
		obj[3]=user.getRegistertime();
		try {
			effectNum=runner.update(conn,sql,obj);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	/**
	 * 根据id更新location,description
	 * 
	 * @param user [location,description]
	 */
	public boolean update(User user){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update User set location=?,description=? where id=?";
		Object[] obj=new Object[3];
		obj[0]=user.getLocation();
		obj[1]=user.getDescription();
		obj[2]=user.getId();
		try {
			effectNum=runner.update(conn,sql,obj);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	/**
	 * 根据id更新用户头像
	 * @param user
	 * @return
	 */
	public boolean updateUrl(User user){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update User set url=? where id=?";
		Object[] obj=new Object[2];
		obj[0]=user.getUrl();
		obj[1]=user.getId();
		try {
			effectNum=runner.update(conn,sql,obj);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	/**
	 * 根据id更新名字
	 * @param user
	 * @return
	 */
	public boolean updateName(User user){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update User set name=? where id=?";
		Object[] obj=new Object[2];
		obj[0]=user.getName();
		obj[1]=user.getId();
		try {
			effectNum=runner.update(conn,sql,obj);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	/**
	 * 根据id更新密码
	 * @param user
	 * @return
	 */
	public boolean updatePsw(User user){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update User set password=? where id=?";
		Object[] obj=new Object[2];
		obj[0]=user.getPassword();
		obj[1]=user.getId();
		try {
			effectNum=runner.update(conn,sql,obj);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	/**
	 * 根据userid更新用户的rank
	 * 已使用log4j来记录
	 * @param user 【id和分数】
	 * 
	 * @return
	 */
	public boolean updateRank(User user){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update User set rank=? where id=?";
		Object[] obj=new Object[2];
		obj[0]=user.getRank();
		obj[1]=user.getId();
		try {
			effectNum=runner.update(conn,sql,obj);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
			logger.info("Id为"+user.getId()+"的用户更新rank为："+user.getRank());
		}
		return getResult(effectNum);
	}
	
	/**
	 * 更新认证状态
	 * @param user 【title,id】
	 * @return
	 */
	public boolean updateAuth(User user){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update User set isauthentication=1,title=? where id=?";
		Object[] obj=new Object[2];
		obj[0]=user.getTitle();
		obj[1]=user.getId();
		try {
			effectNum=runner.update(conn,sql,obj);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
	/**
	 * 减少粉丝
	 * @param id
	 * @return
	 */
	public boolean updateFan_Minus(int id){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update User set fannumber=fannumber-1 where id=?";
		try {
			effectNum=runner.update(conn,sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	/**
	 * 增加粉丝
	 * @param id
	 * @return
	 */
	public boolean updateFan_Plus(int id){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update User set fannumber=fannumber+1 where id=?";
		try {
			effectNum=runner.update(conn,sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	/**
	 * 增加关注
	 * @param id
	 * @return
	 */
	public boolean updateFollow_Plus(int id){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update User set follownumber=follownumber+1 where id=?";
		try {
			effectNum=runner.update(conn,sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	/**
	 * 减少关注
	 * @param id
	 * @return
	 */
	public boolean updateFollow_Minus(int id){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update User set follownumber=follownumber-1 where id=?";
		try {
			effectNum=runner.update(conn,sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
}




















