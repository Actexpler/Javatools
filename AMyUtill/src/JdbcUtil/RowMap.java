/**
 * 
 */
package JdbcUtil;

import java.sql.ResultSet;

/**
 * TODO 添加本类的描述，可多行，内容包含功能、使用条件等
 *
 * @author wzc
 * @date 2018年1月11日 下午10:42:50
 *
 */
public interface RowMap<T> {
	public T  rowMapping(ResultSet rs);

}
