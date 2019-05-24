package xin.ss.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import xin.ss.model.Relation;
import xin.ss.model.RelationDto;

public class RelationSqlHandler extends AbstractTransactionHandler  {
	
	/**
	 * 根据客户id，分享id，得到客户与主人之间的分数
	 * 
	 * @param relation 客户id，分享id
	 * @return 客户与主人之间的分数
	 */
	public int selectByCondition(Relation relation){
		Connection conn=getConnection();
		Relation rel=null;
		String sql="select score from relation where guestid=? and hostid=(SELECT userid from Share where id=?)";
		try {
			rel=runner.query(conn,sql,new BeanHandler<>(Relation.class),relation.getGuestid(),relation.getShareid());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		if(rel!=null){
			return rel.getScore();
		}else{
			return -1;
		}
	}
	/**
	 * 判断客户与主人之间是否已经存在记录
	 * 
	 * @param relation 客户id，分享id
	 * @return false，表明未创建该条记录
	 */
	public Boolean selectReturnTF(Relation relation){
		Connection conn=getConnection();
		Relation rel=null;
		String sql="select score from relation where guestid=? and hostid=(SELECT userid from Share where id=?)";
		try {
			rel=runner.query(conn,sql,new BeanHandler<>(Relation.class),relation.getGuestid(),relation.getShareid());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		if(rel!=null){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 用于查看关系图使用，根据用户id查询出所有与他有关系的用户。
	 * 
	 * @param hostid 用户id
	 * @return 关系列表
	 */
	public List<RelationDto> selectByHostId(int hostid){
		Connection conn=getConnection();
		List<RelationDto> list=null;
		String sql="select (select name from User where id=guestid)'guestname',score from relation where hostid=? order by score desc";
		try {
			list=runner.query(conn,sql,new BeanListHandler<>(RelationDto.class),hostid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return list;
	}
	/**
	 * 插入关系，但之前应该判断是否已经存在关系
	 * 
	 * @param relation 【分数，分享id，推荐或反对人id】
	 * @return 
	 */
	public boolean insert(Relation relation){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="insert into relation(hostid,guestid,score) values((SELECT userid from Share where id=?),?,?)";
		Object[] obj=new Object[3];
		obj[0]=relation.getShareid();
		obj[1]=relation.getGuestid();
		obj[2]=relation.getScore();
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
	 * 更新关系，但之前应该判断是否已经存在关系
	 * @param relation 【分数，分享id，推荐或反对人id】
	 * @return
	 */
	public boolean update(Relation relation){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="update relation set score=? where hostid=(SELECT userid from Share where id=?) and guestid=?";
		Object[] obj=new Object[3];
		obj[0]=relation.getScore();
		obj[1]=relation.getShareid();
		obj[2]=relation.getGuestid();
		try {
			effectNum=runner.update(conn,sql,obj);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return getResult(effectNum);
	}
	
}





























