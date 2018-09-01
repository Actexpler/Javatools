/**
 * 
 */
package JdbcUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 数据库连接数据源
 *
 * @author wzc
 * @date 2018年1月11日 下午9:00:18
 *
 */
public class DataSource {
	
	private static Logger logger = Logger.getLogger(DataSource.class);
	
	//连接数据库的url
	public static   String  URL = "";
	//用户名
	public static   String USERNAME = "";
	//密码
	public static   String  PASSWORD = "";
	//数据库连接驱动
	public static   String  DRIVER = "";
	
	//读取jar内部配置文件
	/*static {
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
	}*/
	
	
	//读取jar外部配置文件
	static {
		try {
			Properties props = new Properties(); 
			 // 读取系统外配置文件 (即Jar包外文件) --- 外部工程引用该Jar包时需要在工程下创建config目录存放配置文件
		    String filePath = System.getProperty("user.dir")
		    + "/jdbc.properties";
		    logger.info("[wzc]获取配置文件："+filePath+"...");
		    InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		    props.load(in);
	        URL = props.getProperty("URL");
	        USERNAME = props.getProperty("USERNAME");
	        PASSWORD = props.getProperty("PASSWORD");
	        DRIVER  = props.getProperty("DRIVER");
	        logger.info("请求连接的数据库URL为："+URL);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}
