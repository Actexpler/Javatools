/**
 * 
 */
package JdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * JDBC 连接数据库 ,操作数据库表
 * 封装对数据库的增删改操作
 * @author wzc
 * @date 2018年1月11日 下午9:20:06
 *
 */
public class wzcJdbc {
	
	/**
	 * 获取连接
	 * @return  Connection
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(DataSource.DRIVER);
			conn = DriverManager.getConnection(DataSource.URL, DataSource.USERNAME, DataSource.PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭连接
	 * @param rs
	 * @param ps
	 * @param conn
	 */
	public static void  closeConnection(ResultSet rs,PreparedStatement ps, Connection conn){
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 封装数据操作的增删改操作
	 * @param sql
	 * @param params  动态参数
	 * @return
	 */
	public static int executeUpdate(String sql , Object... params){
		int result = 0 ;
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = getConnection();
			prepareStatement = connection.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				prepareStatement.setObject(i+1, params[i]);
			}
			result = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 * 数据库查询操作
	 * @param sql
	 * @param rowMap
	 * @param params
	 * @return
	 */
	 public static <T>List<T> executeQuery(String sql , RowMap<T> rowMap , Object...params){
		 List<T> result = new ArrayList<>();
		 Connection connection= null;
		 PreparedStatement pStatement = null;
		 ResultSet rSet = null;
		 
		
		 try {
			connection = getConnection();
			pStatement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pStatement.setObject(i+1, params[i]);
					
				}
			}
			rSet = pStatement.executeQuery();
			while(rSet.next()){
				//创建一个对象
				T t = rowMap.rowMapping(rSet);
				//通过rs给对象复制
				
				//将对象放到list中
				result.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return result;
	 }
	

}
