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
	public int DBExists(Map<String, Object> param) {
		log.debug("DBExists()");
		return sqlsession.selectOne(namespace +"DBExists", param);
	}

	@Override
	public int EmployeeTableExists(Map<String, Object> param) {
		log.debug("EmployeeTableExists()");
		return sqlsession.selectOne(namespace +"EmployeeTableExists", param);
	}

	@Override
	public int DepartmentTableExists(Map<String, Object> param) {
		log.debug("DepartmentTableExists()");
		return sqlsession.selectOne(namespace +"DepartmentTableExists", param);
	}

	@Override
	public int TitleTableExists(Map<String, Object> param) {
		log.debug("TitleTableExists()");
		return sqlsession.selectOne(namespace +"TitleTableExists", param);
	}
}
