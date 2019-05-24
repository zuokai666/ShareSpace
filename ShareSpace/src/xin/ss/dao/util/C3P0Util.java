package xin.ss.dao.util;

import java.sql.*;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class C3P0Util {

	private static ComboPooledDataSource cpds = new ComboPooledDataSource();
	//private static int i=1;
	public static ComboPooledDataSource getCpds() {
		return cpds;
	}

	public static Connection getConnection(){
		Connection conn=null;
		try {
			conn=cpds.getConnection();
			//System.out.print("["+(i++)+":"+conn.hashCode()+"=");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection ct){
		if(ct!=null){
			try {
				//System.out.println(ct.hashCode()+"]");
				ct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}


