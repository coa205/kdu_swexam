package kr.or.dgit.kdu_swexam.connection;

import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.kdu_swexam.dao.DataBaseMapper;

public class DataBaseMapperImpl implements DataBaseMapper {
	private static final Log log = LogFactory.getLog(DataBaseMapperImpl.class);
	private SqlSession sqlsession;
	private String namespace ="kr.or.dgit.kdu_swexam.dao.DataBaseMapper.";

	public DataBaseMapperImpl(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	@Override
	public int dBExists(Map<String, Object> param) {
		log.debug("dBExists()");
		return sqlsession.selectOne(namespace +"dBExists", param);
	}
	
	@Override
	public int userExists(Map<String, Object> param) {
		log.debug("userExists()");
		return sqlsession.selectOne(namespace +"userExists", param);
	}

	@Override
	public int employeeTableExists(Map<String, Object> param) {
		log.debug("employeeTableExists()");
		return sqlsession.selectOne(namespace +"employeeTableExists", param);
	}

	@Override
	public int departmentTableExists(Map<String, Object> param) {
		log.debug("departmentTableExists()");
		return sqlsession.selectOne(namespace +"departmentTableExists", param);
	}

	@Override
	public int titleTableExists(Map<String, Object> param) {
		log.debug("titleTableExists()");
		return sqlsession.selectOne(namespace +"titleTableExists", param);
	}
}
