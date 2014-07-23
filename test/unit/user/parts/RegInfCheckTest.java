package unit.user.parts;

import static org.junit.Assert.*;

import org.junit.Test;

import user.parts.RegInfCheck;

public class RegInfCheckTest {
	
	String pattern1 = "名前は10桁以内で入力してください。<br />";
	String pattern2 = "年齢は(16-60)の範囲で入力してください。<br />";
	String pattern3 = "年齢は数値(半角)で入力してください。<br />";
	String pattern4 = "登録可能なID（999）を超えています。管理者に問い合わせてください。<br />";
	
	@Test
	public void test001_001() {
		RegInfCheck chk = new RegInfCheck();
		chk.checkName("0123456789");
		System.out.println("UT001-001 : "+chk.getErrMsg());
		assertEquals(chk.getErrMsg(),"",chk.getErrMsg());
	}
	
	@Test
	public void test001_002() {
		RegInfCheck chk = new RegInfCheck();
		chk.checkName("あいうえおかきくけこ");
		System.out.println("UT001-002 : "+chk.getErrMsg());
		assertEquals(chk.getErrMsg(),"",chk.getErrMsg());
	}
	
	@Test
	public void test001_003() {
		RegInfCheck chk = new RegInfCheck();
		chk.checkName("01234567890");
		System.out.println("UT001-003 : "+chk.getErrMsg());
		assertEquals(chk.getErrMsg(),pattern1,chk.getErrMsg());
	}
	
	@Test
	public void test001_004() {
		RegInfCheck chk = new RegInfCheck();
		chk.checkName("あいうえおかきくけこさ");
		System.out.println("UT001-004 : "+chk.getErrMsg());
		assertEquals(chk.getErrMsg(),pattern1,chk.getErrMsg());
	}
	
	@Test
	public void test001_005() {
		RegInfCheck chk = new RegInfCheck();
		chk.checkAge("16");
		System.out.println("UT001-005 : "+chk.getErrMsg());
		assertEquals(chk.getErrMsg(),"",chk.getErrMsg());
	}
	
	@Test
	public void test001_006() {
		RegInfCheck chk = new RegInfCheck();
		chk.checkAge("60");
		System.out.println("UT001-006 : "+chk.getErrMsg());
		assertEquals(chk.getErrMsg(),"",chk.getErrMsg());
	}
	
	@Test
	public void test001_007() {
		RegInfCheck chk = new RegInfCheck();
		chk.checkAge("15");
		System.out.println("UT001-007 : "+chk.getErrMsg());
		assertEquals(chk.getErrMsg(),pattern2,chk.getErrMsg());
	}
	
	@Test
	public void test001_008() {
		RegInfCheck chk = new RegInfCheck();
		chk.checkAge("61");
		System.out.println("UT001-008 : "+chk.getErrMsg());
		assertEquals(chk.getErrMsg(),pattern2,chk.getErrMsg());
	}
	
	@Test
	public void test001_009() {
		RegInfCheck chk = new RegInfCheck();
		chk.checkAge("１６");
		System.out.println("UT001-009 : "+chk.getErrMsg());
		assertEquals(chk.getErrMsg(),pattern3,chk.getErrMsg());
	}
	
	@Test
	public void test001_010() {
		RegInfCheck chk = new RegInfCheck();
		chk.checkAge("あいうえお");
		System.out.println("UT001-010 : "+chk.getErrMsg());
		assertEquals(chk.getErrMsg(),pattern3+pattern2,chk.getErrMsg());
	}
	
	@Test
	public void test001_011() {
		RegInfCheck chk = new RegInfCheck();
		chk.checkAge("16あいうえお");
		System.out.println("UT001-011 : "+chk.getErrMsg());
		assertEquals(chk.getErrMsg(),pattern3+pattern2,chk.getErrMsg());
	}
	
	@Test
	public void test001_012() {
		RegInfCheck chk = new RegInfCheck();
		chk.checkId("999");
		System.out.println("UT001-012 : "+chk.getErrMsg());
		assertEquals(chk.getErrMsg(),"",chk.getErrMsg());
	}
	
	@Test
	public void test001_013() {
		RegInfCheck chk = new RegInfCheck();
		chk.checkId("1000");
		System.out.println("UT001-013 : "+chk.getErrMsg());
		assertEquals(chk.getErrMsg(),pattern4,chk.getErrMsg());
		System.out.println("");
	}
}
