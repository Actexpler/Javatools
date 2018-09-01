/**
 * 
 */
package pojo;

/**
 * TODO 添加本类的描述，可多行，内容包含功能、使用条件等
 *
 * @author wzc
 * @date 2018年1月12日 下午8:55:15
 *
 */
public class Student {
    private int sid;
    private String sname;
    private String spwd;
	public Student() {
		super();
	}
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public Student(int sid, String sname, String spwd) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.spwd = spwd;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + "]";
	}

	public String getSpwd() {
		return spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}
    
    
}
