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
		//�t�@�C���̃N���A
		PrintWriter writer=new PrintWriter(new BufferedWriter(new FileWriter("C:/test/log/log.txt")));
		
		//��r�f�[�^�̎擾
		fis = new FileInputStream("C:/test/log/log.txt");
		readFile = new BufferedReader(new InputStreamReader(fis, "Shift_JIS"));
		
		//��r�p���t�̎擾
		DateString tmpdate = new DateString();
		
		//�e�L�X�g�t�@�C���ւ̃��O�o��
		OutLog log = new OutLog();
		log.outLogDmp("sample�F�T���v��\n");
		
		//���[�h���C���𕡐�����s�ł��Ȃ����ߕϐ��Ɍ��ʂ�ޔ�
		tmp_line = readFile.readLine()+"\n";
		
		//�R���\�[����ʂɏo��
		System.out.println("-----UT003-001-----");
		System.out.println(tmp_line);
		
		//���ʂ��r
		assertEquals("��v���܂���",tmpdate.getDate14() + ":sample�F�T���v��\n",tmp_line);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test003_002() {
		
		try {	
			//�t�@�C���̃N���A
			PrintWriter writer=new PrintWriter(new BufferedWriter(new FileWriter("C:/test/log/log.txt")));
			
			//��r�f�[�^�̎擾
			fis = new FileInputStream("C:/test/log/log.txt");
			readFile = new BufferedReader(new InputStreamReader(fis, "Shift_JIS"));
			
			//��r�p���t�̎擾
			DateString tmpdate = new DateString();
			
			//�e�L�X�g�t�@�C���ւ̃��O�o��
			OutLog log = new OutLog();
			log.outLogDmp(12345);
			
			//���[�h���C���𕡐�����s�ł��Ȃ����ߕϐ��Ɍ��ʂ�ޔ�
			tmp_line = readFile.readLine();
			
			//�R���\�[����ʂɏo��
			System.out.println("-----UT003-002-----");
			System.out.println(tmp_line);
			System.out.println("");
			
			//���ʂ��r			
			assertEquals("��v���܂���",tmpdate.getDate14() + ":12345",tmp_line);
			
		} catch (Exception e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
	}
}
