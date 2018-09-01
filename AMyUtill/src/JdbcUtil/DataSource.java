/**
 * 
 */
package JdbcUtil;

import java.util.Properties;

/**
 * 数据库连接数据源
 *
 * @author wzc
 * @date 2018年1月11日 下午9:00:18
 *
 */
public class DataSource {
	
	//连接数据库的url
	public static   String  URL = "";
	//用户名
	public static   String USERNAME = "";
	//密码
	public static   String  PASSWORD = "";
	//数据库连接驱动
	public static   String  DRIVER = "";
	
	static {
		try {
			 Properties props = new Properties(); 
	            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));//从自定义配置文件获取相关参数
	            URL = props.getProperty("URL");
	            USERNAME = props.getProperty("USERNAME");
	            PASSWORD = props.getProperty("PASSWORD");
	            DRIVER  = props.getProperty("DRIVER");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}
