package kr.or.dgit.kdu_swexam;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.or.dgit.kdu_swexam.service.DataBaseService;
import kr.or.dgit.kdu_swexam.util.ConnectionFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainDataBaseTest {
	private static DataBaseService dataBaseService;
	private static Connection connection;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		connection = ConnectionFactory.getInstance();
		dataBaseService = new DataBaseService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		connection = null;
		dataBaseService = null;
	}

	@Test
	public void aTestDBConnection() {
		Assert.assertNotNull(connection);
	}
	
	@Test
	public void bTestDBExists() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("param1", "ncs_erp");
		
		int res = dataBaseService.dBExists(param);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void cTestUserExists() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("param1", "user_ncs");
		
		int res = dataBaseService.userExists(param);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void dTestEmployeeTableExists() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("param1", "ncs_erp");
		param.put("param2", "employee");
		
		int res = dataBaseService.employeeTableExists(param);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void eTestDepartmentTableExists() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("param1", "ncs_erp");
		param.put("param2", "department");
		
		int res = dataBaseService.departmentTableExists(param);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void fTestTitleTableExists() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("param1", "ncs_erp");
		param.put("param2", "title");
		
		int res = dataBaseService.titleTableExists(param);
		Assert.assertNotNull(res);
	}
}
