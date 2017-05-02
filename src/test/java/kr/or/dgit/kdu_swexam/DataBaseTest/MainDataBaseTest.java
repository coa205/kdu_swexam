package kr.or.dgit.kdu_swexam.DataBaseTest;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.kdu_swexam.util.ConnectionFactory;

public class MainDataBaseTest {
	private static Connection connection;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		connection = ConnectionFactory.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		connection = null;
	}

	@Test
	public void test() {
		Assert.assertNotNull(connection);
	}

}
