package kr.or.dgit.kdu_swexam.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.kdu_swexam.connection.DataBaseMapperImpl;
import kr.or.dgit.kdu_swexam.util.MybatisSqlSessionFactory;

public class DataBaseService {
	
	public int DBExists(Map<String, Object> param){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.openSession()){
			DataBaseMapperImpl comp = new DataBaseMapperImpl(sqlsession);
			return comp.DBExists(param);
		}
	}
	
	public int EmployeeTableExists(Map<String, Object> param){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.openSession()){
			DataBaseMapperImpl comp = new DataBaseMapperImpl(sqlsession);
			return comp.EmployeeTableExists(param);
		}
	}
	
	public int DepartmentTableExists(Map<String, Object> param){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.openSession()){
			DataBaseMapperImpl comp = new DataBaseMapperImpl(sqlsession);
			return comp.DepartmentTableExists(param);
		}
	}
	
	public int TitleTableExists(Map<String, Object> param){
		try(SqlSession sqlsession = MybatisSqlSessionFactory.openSession()){
			DataBaseMapperImpl comp = new DataBaseMapperImpl(sqlsession);
			return comp.TitleTableExists(param);
		}
	}
}
