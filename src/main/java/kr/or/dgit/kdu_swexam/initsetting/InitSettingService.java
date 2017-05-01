package kr.or.dgit.kdu_swexam.initsetting;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import kr.or.dgit.kdu_swexam.dao.Dao;
import kr.or.dgit.kdu_swexam.util.Config;
import kr.or.dgit.kdu_swexam.util.DBCon;
import kr.or.dgit.kdu_swexam.util.JdbcUtil;

public class InitSettingService {
	public void initSetting(int init, int resto) {
		File buFile = new File(Config.EXPORT_DIR);// 현재 작업하고 있는 프로젝트 경로안의 BackupFiles폴더
		File dtFile = new File(Config.IMPORT_DIR);// 현재 작업하고 있는 프로젝트 경로안의 DataFiles폴더
		File[] buFiles = buFile.listFiles(); // BackupFiles 안에 있는 파일들을 배열에 넣음
		File[] dtFiles = dtFile.listFiles(); // DataFiles 안에 있는 파일들을 배열에 넣음
		
		if(init==1){// 초기화
			if(resto==1){// 복원
				try(BufferedReader reader = new BufferedReader(new FileReader(dtFiles[0]))){
				initSet();
					for(int i=0 ; i<Config.TABLE_NAME.length ; i++){
						loadTableData(i); // BackupFiles폴더에 있는 파일들을 가져와 테이블에 삽입	
					}
					System.out.println();
					JOptionPane.showMessageDialog(null, "데이터 복원 완료");
				}catch(NullPointerException e){
					JOptionPane.showMessageDialog(null, "복원 폴더가 없어서 DataFiles폴더를 생성합니다.");
					dtFile.mkdir();
				} catch (ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "복원 파일이 없습니다.");
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				} catch (IOException e3) {
					e3.printStackTrace();
				} 
			}else{
				initSet();
				System.out.println("drop database if exists " + Config.DB_NAME);
				System.out.println("create database if not exists " +  Config.DB_NAME);
				System.out.println("use " + Config.DB_NAME);
				System.out.println();
				JOptionPane.showMessageDialog(null, "초기화 완료");
			}
		}else{// 백업
			if(buFile.exists()==false){ // 폴더 존재여부
				buFile.mkdir(); // 없다면 폴더생성
			}
			try{ // BackupFiles 안에 파일이 하나도 없는지 체크
				for(File f : buFiles){ // BackupFiles 안에 있는 파일들을 하나씩 검사
					if(f.exists()){ // 안에 파일이 존재한다면
						f.delete(); // 파일을 지움
					}
				}
			}catch(NullPointerException e){
			}finally{
				for(int i=0 ; i<Config.CREATE_SQL_TABLE.length ; i++){
					BackupTableData(i); // 테이블의 내용을 BackupFiles폴더안에 삽입
				}
				System.out.println();
				JOptionPane.showMessageDialog(null, "데이터 백업 완료");	
			}
		}
	}
	
	public void initSet(){// 초기화
		try {
			Dao dao = Dao.getInstance();
			dao.getUpdateResult("drop database if exists " + Config.DB_NAME);
			dao.getUpdateResult("create database if not exists " +  Config.DB_NAME);
			dao.getUpdateResult("use " + Config.DB_NAME);
			try{
				for(int i=0 ; i<Config.CREATE_USER.length ; i++){
					dao.getUpdateResult(Config.CREATE_USER[i]);
				}
			}catch(SQLException e){
				dao.getUpdateResult(Config.DELETE_USER);
				for(int i=0 ; i<Config.CREATE_USER.length ; i++){
					dao.getUpdateResult(Config.CREATE_USER[i]);
				}
			}
			for(int i=0;i<Config.CREATE_SQL_TABLE.length;i++){
				dao.getUpdateResult(Config.CREATE_SQL_TABLE[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadTableData(int tables){// 파일 복원
		File file = new File(Config.IMPORT_DIR+Config.TABLE_NAME[tables]+".txt");
		String sql = "load data local infile '%s' "
					+"into table "+Config.TABLE_NAME[tables]+" "
					+"character set 'UTF8' "
					+"fields terminated by ',' "
					+"lines terminated by '\n'";
		
		executeImportData(String.format(sql,file.getAbsolutePath().replace("\\", "/")), Config.TABLE_NAME[tables]);
	}
	
	public void BackupTableData(int tables){// 파일 백업
		String sql = "select * from "+Config.TABLE_NAME[tables];
		Connection con = DBCon.getConnection(Config.URL+Config.DB_NAME,Config.USER,Config.PWD );
		try(PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery();){
			StringBuilder sb = new StringBuilder();
			int colCnt = rs.getMetaData().getColumnCount();
			int cheCnt = 0;
			while(rs.next()){
				for(int i=1 ; i<=colCnt ; i++){
					Object obj = rs.getObject(i);
					if(obj.equals(true)){
						obj=1;
					}else if(obj.equals(false)){
						obj=0;
					}
					sb.append(obj+",");
				}
				sb.replace(sb.length()-1, sb.length(), "");
				sb.append("\n");
				cheCnt++;
			}
			System.out.printf("Export Table(%s) %d Rows Success! %n",Config.TABLE_NAME[tables], cheCnt);
			
			try(BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(Config.EXPORT_DIR+Config.TABLE_NAME[tables]+".txt"));
					OutputStreamWriter osw = new OutputStreamWriter(bw, "UTF-8")){
				osw.write(sb.toString());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void executeImportData(String sql, String tableName) {
		Statement stmt = null;
		try {
			Connection con = DBCon.getConnection(Config.URL+Config.DB_NAME,Config.USER,Config.PWD);
			stmt = con.createStatement();
			stmt.execute(sql);
			System.out.printf("Import Table(%s) %d Rows Success! %n",tableName, stmt.getUpdateCount());
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				System.err.println("중복데이터 존재");
			}
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
		}
	}
}