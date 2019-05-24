package xin.ss.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import xin.ss.model.Authentication;

public class AuthenticationSqlHandler extends AbstractTransactionHandler  {
	
	/**
	 * 根据id查询用户id，增加用户认证使用
	 * @param id
	 * @return
	 */
	public Authentication selectById(int id){
		Authentication auths=null;
		Connection conn=getConnection();
		try {
			String sql="select userid,title from Authentication where id=?";
			auths=runner.query(conn, sql,new BeanHandler<>(Authentication.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return auths;
	}
	
	/**
	 * 根据用户Id查询他的所有认证
	 * @param userid
	 * @return
	 */
	public List<Authentication> selectByUserId(int userid){
		List<Authentication> auths=null;
		Connection conn=getConnection();
		try {
			String sql="select id,url,title,content,time,status,result from Authentication "
					+ "where userid=? order by time desc";
			auths=runner.query(conn, sql,new BeanListHandler<>(Authentication.class),userid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return auths;
	}
	/**
	 * 通过status来查询所有认证，status=0返回所有，管理员专用
	 * @param userid
	 * @return
	 */
	public List<Authentication> selectByStatus(int status){
		List<Authentication> auths=null;
		Connection conn=getConnection();
		try {
			if(status==0){
				String sql="select id,userid,url,title,content,time,status,result "
						+ "from Authentication order by time desc";
				auths=runner.query(conn, sql,new BeanListHandler<>(Authentication.class));
			}else {
				String sql="select id,userid,url,title,content,time,status,result "
						+ "from Authentication  where status=? order by time desc";
				auths=runner.query(conn, sql,new BeanListHandler<>(Authentication.class),status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return auths;
	}
	
	/**
	 * 提交认证前的检查
	 * @param userid
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean selectBy12(int userid){
		long count=0;
		Connection conn=getConnection();
		try {
			String sql="select count(*) from Authentication where userid=? and status in (1,2)";
			count=(Long)runner.query(conn,sql,new ScalarHandler(),userid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		if((int)count>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 取消认证前的检查
	 * @param userid
	 * @return true 存在
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean selectBy245(Authentication a){
		long count=0;
		Connection conn=getConnection();
		try {
			String sql="select count(*) from Authentication where userid=? and id=? and status in (4,5,2)";
			count=(Long)runner.query(conn,sql,new ScalarHandler(),a.getUserid(),a.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		if((int)count>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 增加认证，用于用户提交认证使用
	 * @param auth
	 * @return
	 */
	public boolean insert(Authentication auth){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="insert into Authentication(userid,url,title,content,time,status,result) values(?,?,?,?,?,?,?)";
		Object[] obj=new Object[7];
		obj[0]=auth.getUserid();
		obj[1]=auth.getUrl();
		obj[2]=auth.getTitle();
		obj[3]=auth.getContent();
		obj[4]=auth.getTime();
		obj[5]=auth.getStatus();
		obj[6]=auth.getResult();
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
	 * 1.设置status，result，用于取消认证使用
	 * 2.管理员更新认证状态使用
	 * @param auth
	 * @return
	 */
	public boolean update(Authentication auth){
		int effectNum=0;
		Connection conn=getConnection();
		try {
			int userid=auth.getUserid();
			String result=auth.getResult();
			int status=auth.getStatus();
			int id=auth.getId();
			if(userid!=0){//普通用户入口
				String sql="update Authentication set status=?,result=? where id=? and userid=?";
				effectNum=runner.update(conn,sql,status,result,id,userid);
			}else {//管理员
				String sql="update Authentication set status=?,result=? where id=?";
				effectNum=runner.update(conn,sql,status,result,id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
}




















