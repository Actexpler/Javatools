/**
 * 
 */
package JunitTest;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import JdbcUtil.wzcJdbc;
import JdbcUtil.RowMap;
import pojo.Student;

/**
 * TODO 添加本类的描述，可多行，内容包含功能、使用条件等
 *
 * @author wzc
 * @date 2018年1月11日 下午9:38:22
 *
 */
public class excuteUpdateTest {

	public static void main(String[] args) {
		System.out.println("adsf");
	}
	@Test
	public void test() {
		int result = wzcJdbc.executeUpdate("insert into STUDENT values(?,?,?)", 6, "刘七","asdf1234");
		assertEquals(result, 1);
	}
	
	@Test
	public void executeQueryTest(){
		List<Student> reStudents = wzcJdbc.executeQuery("select * from STUDENT", new RowMap<Student>() {

			@Override
			public Student rowMapping(ResultSet rs) {
				Student student = new Student();
				try {
					student.setSid(rs.getInt("sid"));
					student.setSname(rs.getString("sname"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return student;
			}
		}, null);
		
		for (Student student : reStudents) {
			System.out.println(student);
		}
	}

}
