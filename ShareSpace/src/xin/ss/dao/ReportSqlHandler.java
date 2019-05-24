package xin.ss.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import xin.ss.model.Report;
import xin.ss.model.ReportDto;

public class ReportSqlHandler extends AbstractTransactionHandler  {
	
	/**
	 * 返回举报列表
	 * @return
	 */
	public List<ReportDto> select(){
		Connection conn=getConnection();
		List<ReportDto> reports=null;
		String sql="select id,userid,(select name from User where id=userid)'username',"
				+ "(select url from User where id=userid)'userurl',shareid,type,content,time from Report order by time desc";
		try {
			reports=runner.query(conn,sql,new BeanListHandler<>(ReportDto.class));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeLocalConn(conn);
		}
		return reports;
	}
	
	/**
	 * 添加举报
	 * @param report
	 * @return
	 */
	public boolean insert(Report report){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="insert into Report(userid,shareid,type,content,time) values(?,?,?,?,?)";
		Object[] obj=new Object[5];
		obj[0]=report.getUserid();
		obj[1]=report.getShareid();
		obj[2]=report.getType();
		obj[3]=report.getContent();
		obj[4]=report.getTime();
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
	 * 删除举报
	 * @return
	 */
	public boolean delete(int id){
		int effectNum=0;
		Connection conn=getConnection();
		String sql="delete from Report where id=?";
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




















