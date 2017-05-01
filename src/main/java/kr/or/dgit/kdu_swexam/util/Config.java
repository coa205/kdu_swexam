package kr.or.dgit.kdu_swexam.util;

public class Config {
	public static final String DB_NAME = "ncs_erp";
	public static final String USER   = "root";
	public static final String PWD    = "rootroot";
	public static final String URL    = "jdbc:mysql://localhost:3306/";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static final String[] TABLE_NAME = {"department","title","employee"};
	
	public static final String[] CREATE_SQL_TABLE={
			//부서
			"CREATE TABLE department (   "
			+ "dcode    INT(11)  NOT NULL,    "
			+ "dname    CHAR(10) NOT NULL,   "
			+ "floor    INT(11) NULL,   "
			+ "PRIMARY KEY (dcode) ); "	,
			//직책
			"CREATE TABLE title (   "
			+ "tcode    INT(11)  NOT NULL,   "
			+ "tname    VARCHAR(10) NULL,   "
			+ "PRIMARY KEY (tcode)  );  ",
			// 사원
			"CREATE TABLE employee (   "
			+ "eno INT(11)  NOT NULL,      "
			+ "ename VARCHAR(20) NOT NULL,   "
			+ "salary INT(11) NULL,   "
			+ "dno INT(11) NULL,   "
			+ "gender TINYINT(1) NULL,   "
			+ "joindate DATE NULL,   "
			+ "title INT(11) NULL,   "
			+ "PRIMARY KEY (eno), "
			+ "FOREIGN KEY (dno) REFERENCES department (dcode) ON UPDATE CASCADE, "
			+ "FOREIGN KEY (title) REFERENCES title (tcode) ON UPDATE CASCADE  ); " ,
	};
	
	public static final String[] CREATE_USER={
			"CREATE USER 'user_ncs'@'%' ;",
			"UPDATE mysql.user SET Password=PASSWORD('user_ncs') WHERE User='user_ncs' AND Host='%' ;",
			//SELECT, INSERT, DELETE, UPDATE 권한 추가 
			"GRANT Select ON ncs_erp.* TO 'user_ncs'@'%' ;",
			"GRANT Insert ON ncs_erp.* TO 'user_ncs'@'%' ;",
			"GRANT Delete ON ncs_erp.* TO 'user_ncs'@'%' ;",
			"GRANT Update ON ncs_erp.* TO 'user_ncs'@'%' ;",
			//만일을 대비해 다른 권한들도 추가
			"GRANT Alter ON ncs_erp.* TO 'user_ncs'@'%' ;",
			"GRANT Create ON ncs_erp.* TO 'user_ncs'@'%' ;",
			"GRANT Create view ON ncs_erp.* TO 'user_ncs'@'%' ;",		
			"GRANT Drop ON ncs_erp.* TO 'user_ncs'@'%' ;",
			"GRANT Grant option ON ncs_erp.* TO 'user_ncs'@'%' ;",
			"GRANT Index ON ncs_erp.* TO 'user_ncs'@'%' ;",	
			"GRANT References ON ncs_erp.* TO 'user_ncs'@'%' ;",
			"GRANT Show view ON ncs_erp.* TO 'user_ncs'@'%' ;",
			"GRANT Trigger ON ncs_erp.* TO 'user_ncs'@'%' ;",
			"GRANT Alter routine ON ncs_erp.* TO 'user_ncs'@'%' ;",
			"GRANT Create routine ON ncs_erp.* TO 'user_ncs'@'%' ;",
			"GRANT Execute ON ncs_erp.* TO 'user_ncs'@'%' ;",
			"FLUSH PRIVILEGES ;"
	};
	
	public static final String DELETE_USER= "DROP USER 'user_ncs'@'%';";
						
	public static final String EXPORT_DIR = System.getProperty("user.dir")+ "\\BackupFiles\\";
	
	public static final String IMPORT_DIR = System.getProperty("user.dir")+ "\\DataFiles\\";	
}
