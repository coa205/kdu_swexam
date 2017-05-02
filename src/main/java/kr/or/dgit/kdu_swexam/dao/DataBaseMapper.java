package kr.or.dgit.kdu_swexam.dao;

import java.util.Map;

public interface DataBaseMapper {
	int DBExists(Map<String, Object> param);
	int EmployeeTableExists(Map<String, Object> param);
	int DepartmentTableExists(Map<String, Object> param);
	int TitleTableExists(Map<String, Object> param);
}
