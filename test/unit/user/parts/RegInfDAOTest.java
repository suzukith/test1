package unit.user.parts;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.SQLException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import user.bean.RegistrantInfo;
import user.parts.RegInfDAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RegInfDAOTest{
	
	//宣言
	RegInfDAO dao;
	String tmp_str;
	
	//比較用データを用意
	String[] checkarray_UT1 = {"001,鈴木太郎,35","002,Tommy,25","003,山田花子,30","004,佐藤路未央,28"};
	String[] checkarray_UT2 = {"001,鈴木太郎,35","002,Michael,29","003,山田花子,30"};
	String[] checkarray_UT3 = {"002,Tommy,25","003,山田花子,30"};
	String[] checkarray_UT4 = {"001,鈴木太郎,35","002,Tommy,25","003,山田花子,30"};
	String checknum_UT5 = "001";	
	
	//TomCatを経由しないためソース内でDBアクセス設定を行う
	@BeforeClass
	public static void setUpBeforeClass() throws Exception  {

		//システムプロパティをセットするコードを記述
	    System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
	    System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

	    //ネーミング設定
	    InitialContext ic = new InitialContext();
	    ic.createSubcontext("java:");
	    ic.createSubcontext("java:comp");
	    ic.createSubcontext("java:comp/env");
	    ic.createSubcontext("java:comp/env/jdbc");

	    MysqlDataSource ds = new MysqlDataSource();
	    ds.setUser("root");
	    ds.setPassword("root");
	    ds.setURL("jdbc:mysql://localhost/Task");

	    //バインド実行
	    ic.bind("java:comp/env/jdbc/Task", ds);

	}
	
	@After
	public void tearDown(){
	    dao.close();
	}
	
	@Test
	public void test002_001() {
		dao = new RegInfDAO();
		//レコードの追加処理
		dao.insert("004", "佐藤路未央", "28");
		
		ArrayList<RegistrantInfo> tmpRegInfo = dao.getReglist();
		
		//コンソール画面出力＆出力結果の比較
		System.out.println("-----UT002-001-----");
		for(int i=0 ; i < tmpRegInfo.size() ; i++ ) {
			RegistrantInfo regInfo = tmpRegInfo.get(i);
			tmp_str = regInfo.getrId() + "," + regInfo.getrName() + "," + regInfo.getrAge();			
			System.out.println(tmp_str);
			//System.out.println(checkarray[i]);
			assertEquals("一致しません",checkarray_UT1[i],tmp_str);
		}
		System.out.println("");
		//初期設定に戻す
		dao.delete("004");
	}
	
	@Test
	public void test002_002() {
		dao = new RegInfDAO();
		//レコードの更新処理
		dao.update("002", "Michael", "29");

		ArrayList<RegistrantInfo> tmpRegInfo = dao.getReglist();
		
		//コンソール画面出力＆出力結果の比較
		System.out.println("-----UT002-002-----");
		for(int i=0 ; i < tmpRegInfo.size() ; i++ ) {
			RegistrantInfo regInfo = tmpRegInfo.get(i);
			tmp_str = regInfo.getrId() + "," + regInfo.getrName() + "," + regInfo.getrAge();			
			System.out.println(tmp_str);
			//System.out.println(checkarray[i]);
			assertEquals("一致しません",checkarray_UT2[i],tmp_str);
		}
		System.out.println("");
		//初期設定に戻す
		dao.update("002", "Tommy", "25");
	}
	
	@Test
	public void test002_003() {
		dao = new RegInfDAO();
		//レコードの削除処理
		dao.delete("001");
		
		ArrayList<RegistrantInfo> tmpRegInfo = dao.getReglist();
		
		//コンソール画面出力＆出力結果の比較
		System.out.println("-----UT002-003-----");
		for(int i=0 ; i < tmpRegInfo.size() ; i++ ) {
			RegistrantInfo regInfo = tmpRegInfo.get(i);
			tmp_str = regInfo.getrId() + "," + regInfo.getrName() + "," + regInfo.getrAge();			
			System.out.println(tmp_str);
			//System.out.println(checkarray[i]);
			assertEquals("一致しません",checkarray_UT3[i],tmp_str);
		}
		System.out.println("");
		//初期設定に戻す
		dao.insert("001", "鈴木太郎", "35");
	}
	
	@Test
	public void test002_004() {
		dao = new RegInfDAO();
		
		ArrayList<RegistrantInfo> tmpRegInfo = dao.getReglist();
		
		//一覧出力処理の実行＆出力結果の比較
		System.out.println("-----UT002-004-----");
		for(int i=0 ; i < tmpRegInfo.size() ; i++ ) {
			RegistrantInfo regInfo = tmpRegInfo.get(i);
			tmp_str = regInfo.getrId() + "," + regInfo.getrName() + "," + regInfo.getrAge();			
			System.out.println(tmp_str);
			assertEquals("一致しません",checkarray_UT4[i],tmp_str);
		}
		
		System.out.println("");
		
	}
	
	@Test
	public void test002_005() {
		String errnum = null;
				
		dao = new RegInfDAO();
		
		//レコードの消去
		dao.delete("001");
		dao.delete("002");
		dao.delete("003");
		
		//戻り値の取得
		errnum = dao.getNextId();
		
		//出力結果比較
		System.out.println("-----UT002-005-----");
		System.out.println(errnum);
		assertEquals("一致しません",checknum_UT5,errnum);
		System.out.println("");
		
		//初期設定に戻す
		dao.insert("001", "鈴木太郎", "35");
		dao.insert("002", "Tommy", "25");
		dao.insert("003", "山田花子", "30");
	}
}	


