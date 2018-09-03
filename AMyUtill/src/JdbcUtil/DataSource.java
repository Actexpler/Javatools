package JdbcUtil;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @ClassName: DataSource 
 * @Description: 配置JDBCTool数据源
 * @author ws
 * @date 2018年9月2日 下午11:52:21 
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
	//读取jar所在项目内部配置文件
	static {
		try {		
			 logger.info("[wzc]读取数据源配置");
			  Properties props = new Properties(); 
	          props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));//从自定义配置文件获取相关参数
	          URL = props.getProperty("URL");
	          logger.info("你将连接的数据库URL为："+URL);
	          USERNAME = props.getProperty("USERNAME");
	          PASSWORD = props.getProperty("PASSWORD");
	          DRIVER  = props.getProperty("DRIVER");
		} catch (Exception e) {
			logger.error("[wzc]读取数据源出错，请在资源文件夹下添加jdbc.properties文件，并正确配置数据源  \n "+ 
		                 "URL = xxx \n" + 
					     "USERNAME = xxx \n" + 
					     "PASSWORD = xxx \n" + 
					     "#DRIVER = xxx \n" + 
					     "DRIVER = xxx  ");
		}
	}
	
	
	//读取jar所在项目外部文件中配置文件
	/*static {
		try {
			Properties props = new Properties(); 
			 // 读取系统外配置文件 (即Jar包外文件) --- 外部工程引用该Jar包时需要在工程下创建config目录存放配置文件
		    String filePath = System.getProperty("user.dir")
		    + "\\config\\jdbc.properties";
		    logger.info("[wzc]获取配置文件："+filePath+"...");
		    InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		    props.load(in);
		    System.out.println("========"+props.getProperty("URL"));
	        URL = props.getProperty("URL");
	        USERNAME = props.getProperty("USERNAME");
	        PASSWORD = props.getProperty("PASSWORD");
	        DRIVER  = props.getProperty("DRIVER");
	        logger.info("请求连接的数据库URL为："+URL);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}*/
	

}
