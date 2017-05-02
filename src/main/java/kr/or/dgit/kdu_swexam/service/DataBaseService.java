package kr.or.dgit.kdu_swexam.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.kdu_swexam.connection.DataBaseMapperImpl;
import kr.or.dgit.kdu_swexam.util.MybatisSqlSessionFactory;

public class DataBaseService {
	
	public int dBExists(Map<String, Object> param){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.openSession()){
			DataBaseMapperImpl comp = new DataBaseMapperImpl(sqlsession);
			return comp.dBExists(param);
		}
	}
	
	public int userExists(Map<String, Object> param){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.openSession()){
			DataBaseMapperImpl comp = new DataBaseMapperImpl(sqlsession);
			return comp.userExists(param);
		}
	}
	
	public int employeeTableExists(Map<String, Object> param){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.openSession()){
			DataBaseMapperImpl comp = new DataBaseMapperImpl(sqlsession);
			return comp.employeeTableExists(param);
		}
	}
	
	public int departmentTableExists(Map<String, Object> param){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.openSession()){
			DataBaseMapperImpl comp = new DataBaseMapperImpl(sqlsession);
			return comp.departmentTableExists(param);
		}
	}
	
	public int titleTableExists(Map<String, Object> param){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.openSession()){
			DataBaseMapperImpl comp = new DataBaseMapperImpl(sqlsession);
			return comp.titleTableExists(param);
		}
	}
}
