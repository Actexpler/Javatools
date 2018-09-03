JDBC连接数据库的几个步骤：
		1、加载JDBC驱动程序：
		2、提供JDBC连接的URL
		3、创建数据库的连接
		4、创建一个Statement
		5、执行SQL语句
		6、处理结果
		7、关闭JDBC对象
		
首先需要导入数据库相应的jar包
Mysql：mysql-connector-java-5.1.42.jar
Oracle: ojdbc14.jar

参数配置：
     //连接数据库的url
     public static   String  URL = "";
     //用户名
     public static   String USERNAME = "";
     //密码
     public static   String  PASSWORD = "";
     //数据库连接驱动
     public static   String  DRIVER = "";
     
     
增删改的使用：
   int insertCount = jdbc.executeUpdate("insert into student value(?,?,?)",student1.getSid(),student1.getSname(),student1.getSpwd());
   
   int delCount = jdbc.executeUpdate("delete from student where stu_id = ?", student1.getSid());
   
   int uptCount = jdbc.executeUpdate("update student set stu_pwd = ? where stu_id = ?", new_pwd,student2.getSid());
   
   
   查询的使用：
   List<Student> students = jdbc.executeQuery("select * from student", new RowMap<Student>() {
			@Override
			public Student rowMapping(ResultSet rSet) {
				Student student  =new Student();
				try {
					student.setSid(rSet.getInt("stu_id"));
					student.setSname(rSet.getString("stu_name"));
					student.setSpwd(rSet.getString("stu_pwd"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return student;
			}
		}, null);