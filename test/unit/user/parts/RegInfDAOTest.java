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
	
	//�錾
	RegInfDAO dao;
	String tmp_str;
	
	//��r�p�f�[�^��p��
	String[] checkarray_UT1 = {"001,��ؑ��Y,35","002,Tommy,25","003,�R�c�Ԏq,30","004,�����H����,28"};
	String[] checkarray_UT2 = {"001,��ؑ��Y,35","002,Michael,29","003,�R�c�Ԏq,30"};
	String[] checkarray_UT3 = {"002,Tommy,25","003,�R�c�Ԏq,30"};
	String[] checkarray_UT4 = {"001,��ؑ��Y,35","002,Tommy,25","003,�R�c�Ԏq,30"};
	String checknum_UT5 = "001";	
	
	//TomCat���o�R���Ȃ����߃\�[�X����DB�A�N�Z�X�ݒ���s��
	@BeforeClass
	public static void setUpBeforeClass() throws Exception  {

		//�V�X�e���v���p�e�B���Z�b�g����R�[�h���L�q
	    System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
	    System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

	    //�l�[�~���O�ݒ�
	    InitialContext ic = new InitialContext();
	    ic.createSubcontext("java:");
	    ic.createSubcontext("java:comp");
	    ic.createSubcontext("java:comp/env");
	    ic.createSubcontext("java:comp/env/jdbc");

	    MysqlDataSource ds = new MysqlDataSource();
	    ds.setUser("root");
	    ds.setPassword("root");
	    ds.setURL("jdbc:mysql://localhost/Task");

	    //�o�C���h���s
	    ic.bind("java:comp/env/jdbc/Task", ds);

	}
	
	@After
	public void tearDown(){
	    dao.close();
	}
	
	@Test
	public void test002_001() {
		dao = new RegInfDAO();
		//���R�[�h�̒ǉ�����
		dao.insert("004", "�����H����", "28");
		
		ArrayList<RegistrantInfo> tmpRegInfo = dao.getReglist();
		
		//�R���\�[����ʏo�́��o�͌��ʂ̔�r
		System.out.println("-----UT002-001-----");
		for(int i=0 ; i < tmpRegInfo.size() ; i++ ) {
			RegistrantInfo regInfo = tmpRegInfo.get(i);
			tmp_str = regInfo.getrId() + "," + regInfo.getrName() + "," + regInfo.getrAge();			
			System.out.println(tmp_str);
			//System.out.println(checkarray[i]);
			assertEquals("��v���܂���",checkarray_UT1[i],tmp_str);
		}
		System.out.println("");
		//�����ݒ�ɖ߂�
		dao.delete("004");
	}
	
	@Test
	public void test002_002() {
		dao = new RegInfDAO();
		//���R�[�h�̍X�V����
		dao.update("002", "Michael", "29");

		ArrayList<RegistrantInfo> tmpRegInfo = dao.getReglist();
		
		//�R���\�[����ʏo�́��o�͌��ʂ̔�r
		System.out.println("-----UT002-002-----");
		for(int i=0 ; i < tmpRegInfo.size() ; i++ ) {
			RegistrantInfo regInfo = tmpRegInfo.get(i);
			tmp_str = regInfo.getrId() + "," + regInfo.getrName() + "," + regInfo.getrAge();			
			System.out.println(tmp_str);
			//System.out.println(checkarray[i]);
			assertEquals("��v���܂���",checkarray_UT2[i],tmp_str);
		}
		System.out.println("");
		//�����ݒ�ɖ߂�
		dao.update("002", "Tommy", "25");
	}
	
	@Test
	public void test002_003() {
		dao = new RegInfDAO();
		//���R�[�h�̍폜����
		dao.delete("001");
		
		ArrayList<RegistrantInfo> tmpRegInfo = dao.getReglist();
		
		//�R���\�[����ʏo�́��o�͌��ʂ̔�r
		System.out.println("-----UT002-003-----");
		for(int i=0 ; i < tmpRegInfo.size() ; i++ ) {
			RegistrantInfo regInfo = tmpRegInfo.get(i);
			tmp_str = regInfo.getrId() + "," + regInfo.getrName() + "," + regInfo.getrAge();			
			System.out.println(tmp_str);
			//System.out.println(checkarray[i]);
			assertEquals("��v���܂���",checkarray_UT3[i],tmp_str);
		}
		System.out.println("");
		//�����ݒ�ɖ߂�
		dao.insert("001", "��ؑ��Y", "35");
	}
	
	@Test
	public void test002_004() {
		dao = new RegInfDAO();
		
		ArrayList<RegistrantInfo> tmpRegInfo = dao.getReglist();
		
		//�ꗗ�o�͏����̎��s���o�͌��ʂ̔�r
		System.out.println("-----UT002-004-----");
		for(int i=0 ; i < tmpRegInfo.size() ; i++ ) {
			RegistrantInfo regInfo = tmpRegInfo.get(i);
			tmp_str = regInfo.getrId() + "," + regInfo.getrName() + "," + regInfo.getrAge();			
			System.out.println(tmp_str);
			assertEquals("��v���܂���",checkarray_UT4[i],tmp_str);
		}
		
		System.out.println("");
		
	}
	
	@Test
	public void test002_005() {
		String errnum = null;
				
		dao = new RegInfDAO();
		
		//���R�[�h�̏���
		dao.delete("001");
		dao.delete("002");
		dao.delete("003");
		
		//�߂�l�̎擾
		errnum = dao.getNextId();
		
		//�o�͌��ʔ�r
		System.out.println("-----UT002-005-----");
		System.out.println(errnum);
		assertEquals("��v���܂���",checknum_UT5,errnum);
		System.out.println("");
		
		//�����ݒ�ɖ߂�
		dao.insert("001", "��ؑ��Y", "35");
		dao.insert("002", "Tommy", "25");
		dao.insert("003", "�R�c�Ԏq", "30");
	}
}	


