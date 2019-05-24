package xin.ss.dao;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import xin.ss.model.Relation;

public class GBSqlHandler extends AbstractTransactionHandler  {
	
	/**
	 * 根据shareid和userid查看GoodOrBad表是否已经存在推荐
	 * @param relation shareid和【guestid 为推荐人id】
	 * @return -1 0 （未推荐）1（表示推荐）
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int selectGood(Relation relation){
		Connection conn=getConnection();
		Object good=null;
		String sql="select isgood from GoodOrBad where shareid=? and userid=?";
		try {
			good=runner.query(conn,sql,new ScalarHandler(),relation.getShareid(),relation.getGuestid());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		if(good!=null){
			return (Integer)good;
		}
		return -1;
	}
	
	/**
	 * 根据shareid和userid查看是否已经反对
	 * @param relation shareid和【guestid 为反对人id】
	 * @return -1 0 （未反对）1（表示反对）
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int selectBad(Relation relation){
		Connection conn=getConnection();
		Object bad=null;
		String sql="select isbad from GoodOrBad where shareid=? and userid=?";
		try {
			bad=runner.query(conn,sql,new ScalarHandler(),relation.getShareid(),relation.getGuestid());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		if(bad!=null){
			return (Integer)bad;
		}
		return -1;
	}
	
	/**
	 * 
	 * @param relation  shareid和【guestid 为推荐人id】
	 * @return
	 */
	public boolean insertGood(Relation relation){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="insert into GoodOrBad(shareid,userid,isgood,isbad) values(?,?,1,0)";
		Object[] obj=new Object[2];
		obj[0]=relation.getShareid();
		obj[1]=relation.getGuestid();
		try {
			effectNum=runner.update(conn,sql,obj);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
	public boolean insertBad(Relation relation){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="insert into GoodOrBad(shareid,userid,isgood,isbad) values(?,?,0,1)";
		Object[] obj=new Object[2];
		obj[0]=relation.getShareid();
		obj[1]=relation.getGuestid();
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
	 * 
	 * @param relation shareid和【guestid 为推荐人id】
	 * @return
	 */
	public boolean updateGood(Relation relation){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update GoodOrBad set isgood=1 where shareid=? and userid=?";
		try {
			effectNum=runner.update(conn,sql,relation.getShareid(),relation.getGuestid());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
	public boolean updateBad(Relation relation){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update GoodOrBad set isbad=1 where shareid=? and userid=?";
		try {
			effectNum=runner.update(conn,sql,relation.getShareid(),relation.getGuestid());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
	/**
	 * 删除该条记录，删除分享使用
	 * @param shareidid
	 * @return
	 */
	public boolean deleteByShareId(int shareid){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="delete from GoodOrBad where shareid=?";
		try {
			effectNum=runner.update(conn,sql,shareid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
}

















