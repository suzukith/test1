package unit.employ;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.junit.Test;

import employ.DateString;
import employ.OutLog;

public class OutLogTest {
	
	String tmp_line = null;
	FileInputStream fis = null;
    BufferedReader readFile = null;

	@Test
	public void test003_001() {
		
		try {
		//ファイルのクリア
		PrintWriter writer=new PrintWriter(new BufferedWriter(new FileWriter("C:/test/log/log.txt")));
		
		//比較データの取得
		fis = new FileInputStream("C:/test/log/log.txt");
		readFile = new BufferedReader(new InputStreamReader(fis, "Shift_JIS"));
		
		//比較用日付の取得
		DateString tmpdate = new DateString();
		
		//テキストファイルへのログ出力
		OutLog log = new OutLog();
		log.outLogDmp("sample：サンプル\n");
		
		//リードラインを複数回実行できないため変数に結果を退避
		tmp_line = readFile.readLine()+"\n";
		
		//コンソール画面に出力
		System.out.println("-----UT003-001-----");
		System.out.println(tmp_line);
		
		//結果を比較
		assertEquals("一致しません",tmpdate.getDate14() + ":sample：サンプル\n",tmp_line);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test003_002() {
		
		try {	
			//ファイルのクリア
			PrintWriter writer=new PrintWriter(new BufferedWriter(new FileWriter("C:/test/log/log.txt")));
			
			//比較データの取得
			fis = new FileInputStream("C:/test/log/log.txt");
			readFile = new BufferedReader(new InputStreamReader(fis, "Shift_JIS"));
			
			//比較用日付の取得
			DateString tmpdate = new DateString();
			
			//テキストファイルへのログ出力
			OutLog log = new OutLog();
			log.outLogDmp(12345);
			
			//リードラインを複数回実行できないため変数に結果を退避
			tmp_line = readFile.readLine();
			
			//コンソール画面に出力
			System.out.println("-----UT003-002-----");
			System.out.println(tmp_line);
			System.out.println("");
			
			//結果を比較			
			assertEquals("一致しません",tmpdate.getDate14() + ":12345",tmp_line);
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
