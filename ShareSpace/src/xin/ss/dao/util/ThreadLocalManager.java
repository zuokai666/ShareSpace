package xin.ss.dao.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ThreadLocalManager {
	private static ThreadLocal<Connection> local=new ThreadLocal<Connection>();
	
	public static Connection getConnection(){
		Connection conn=local.get();
		if(conn==null){
			conn=C3P0Util.getConnection();
			local.set(conn);
		}
		return conn;
	}
	
	public static void startTransaction(){
		try {
			getConnection().setAutoCommit(false);
			//System.out.print("事务:开启,");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commitTransaction(){
		try {
			getConnection().commit();
			//System.out.print("提交,");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollbackTransaction(){
		try {
			getConnection().rollback();
			//System.out.print("回滚");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(){
		try {
			C3P0Util.close(getConnection());//放回连接池
		} catch (Exception e) {
			e.printStackTrace();
		}
		local.remove();//删除ThreadLocal中的当前线程的这条Map数据
	}
	
}
































