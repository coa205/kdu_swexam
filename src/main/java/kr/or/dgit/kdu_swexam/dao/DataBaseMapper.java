package kr.or.dgit.kdu_swexam.dao;

import java.util.Map;

public interface DataBaseMapper {
	int dBExists(Map<String, Object> param);
	int userExists(Map<String, Object> param);
	int employeeTableExists(Map<String, Object> param);
	int departmentTableExists(Map<String, Object> param);
	int titleTableExists(Map<String, Object> param);
}
