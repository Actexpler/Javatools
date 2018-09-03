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
import java.util.PrimitiveIterator.OfDouble;

import org.apache.log4j.Logger;


/**
 * 
 * JDBC 连接数据库 ,操作数据库表
 * 封装对数据库的增删改操作
 * @author wzc
 * @date 2018年1月11日 下午9:20:06
 *
 */
public class MJDBC {
	
	private static Logger logger  = Logger.getLogger(MJDBC.class);
	
	/**
	 * 获取连接
	 * @return  Connection
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			logger.info("[wzc][info]正在获取数据库连接...");
			//一、加载驱动
			Class.forName(DataSource.DRIVER);
			//二、提供JDBC连接的URL 并创建连接
			conn = DriverManager.getConnection(DataSource.URL, DataSource.USERNAME, DataSource.PASSWORD);
			logger.info("[wzc][info]连接成功！！");
		} catch (ClassNotFoundException e) {
		    logger.error("[wzc][error]连接失败！！！");
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
			logger.info("[wzc][info]正在关闭数据库连接...");
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
			
		   logger.info("[wzc][info]关闭成功！！");
			
		} catch (SQLException e) {
			logger.error("[wzc][error]关闭失败！！");
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
			//加载驱动，提供JDBC连接的URL 并创建连接
			connection = getConnection();
            //三、创建一个Statement   
			/**
			 * 要执行SQL语句，必须获得java.sql.Statement实例，Statement实例分为以下3   种类型：   
			      1、执行静态SQL语句。通常通过Statement实例实现。   
			      2、执行动态SQL语句。通常通过PreparedStatement实例实现。   
			      3、执行数据库存储过程。通常通过CallableStatement实例实现。   
			 */
			prepareStatement = connection.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				prepareStatement.setObject(i+1, params[i]);
			}
			//四、执行SQL语句   
			/**
			 *   Statement接口提供了三种执行SQL语句的方法：executeQuery 、executeUpdate    和execute   
				 1、ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句   ，返回一个结果集（ResultSet）对象。   
				 2、int executeUpdate(String sqlString)：用于执行INSERT、UPDATE或   DELETE语句以及SQL DDL语句，如：CREATE TABLE和DROP TABLE等   
				 3、execute(sqlString):用于执行返回多个结果集、多个更新计数或二者组合的   语句。   
			 */
			//五处理结果   
		    /**两种情况：   
		     1、执行更新返回的是本次操作影响到的记录数。   
		     2、执行查询返回的结果是一个ResultSet对象。   
			*/
			result = prepareStatement.executeUpdate();
			
			System.out.println("[wzc][info]you are come on the sql is  "+sql);
			System.out.print("[wzc][info][params]");
			for (int i = 1; i <= params.length; i++) {
				System.out.print("param"+i+":"+params[i-1]+" ");
			}
			System.out.println(" ");
			logger.info("[wzc][info]the result is:"+result);
			
			
		} catch (SQLException e) {
			logger.error("[wzc][error]there are some mistakes ,you need to correct");
			System.err.println("[wzc][error][sql]"+sql);
			System.err.print("[wzc][error][params]");
			for (int i = 1; i < params.length; i++) {
				System.err.print("param"+i+":"+params[i-1]+" ");
			}
			System.err.println(" ");
			e.printStackTrace();
		}finally {
			//六、关闭JDBC对象    
			closeConnection(null, prepareStatement, connection);
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
		 int resultCount = 0;
		
		 try {
			//一、二、加载驱动创建连接
			connection = getConnection();
			 //三、创建一个Statement   
			/**
			 * 要执行SQL语句，必须获得java.sql.Statement实例，Statement实例分为以下3   种类型：   
			      1、执行静态SQL语句。通常通过Statement实例实现。   
			      2、执行动态SQL语句。通常通过PreparedStatement实例实现。   
			      3、执行数据库存储过程。通常通过CallableStatement实例实现。   
			 */
			pStatement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pStatement.setObject(i+1, params[i]);
					
				}
			}
			//四、执行SQL语句   
			/**
			 *   Statement接口提供了三种执行SQL语句的方法：executeQuery 、executeUpdate    和execute   
				 1、ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句   ，返回一个结果集（ResultSet）对象。   
				 2、int executeUpdate(String sqlString)：用于执行INSERT、UPDATE或   DELETE语句以及SQL DDL语句，如：CREATE TABLE和DROP TABLE等   
				 3、execute(sqlString):用于执行返回多个结果集、多个更新计数或二者组合的   语句。   
			 */
			rSet = pStatement.executeQuery();
			//五处理结果   
		    /**两种情况：   
		     1、执行更新返回的是本次操作影响到的记录数。   
		     2、执行查询返回的结果是一个ResultSet对象。   
			*/
			while(rSet.next()){
				//创建一个对象
				T t = rowMap.rowMapping(rSet);
				//通过rs给对象属性赋值
				
				//将对象放到list中
				result.add(t);
				resultCount++;
			}
			System.out.println("[wzc][info]you are come on the sql is  "+sql);
			System.out.print("[wzc][info][params]");
			for (int i = 1; i <= params.length; i++) {
				System.out.print("param"+i+":"+params[i-1]+" ");
			}
			System.out.println(" ");
			logger.info("[wzc][info]the result is:"+resultCount);
			
		} catch (SQLException e) {
			logger.error("[wzc][error]there are some mistakes ,you need to correct");
			System.err.println("[wzc][error][sql]"+sql);
			System.err.print("[wzc][error][params]");
			for (int i = 1; i < params.length; i++) {
				System.err.print("param"+i+":"+params[i-1]+" ");
			}
			System.err.println(" ");
			e.printStackTrace();
		}finally {
			//六、关闭连接
			closeConnection(rSet, pStatement, connection);
		}
		 return result;
	 }
	
	public static void main(String[] args) {
		System.out.println("main");
	}
}
