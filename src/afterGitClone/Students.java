package org.zm.model;
import java.util.Scanner;
public class Students {
	public static String stuNum;
	public String stuName;
	public Students(String stuName) {
		this.stuName=stuName;
		System.out.println(stuName+"�����ɹ���");
		if(IfExitId()) {
			System.out.println("ѧ��"+stuName+"ѧ���Ѿ�����"+stuNum);
		}
		else {
			System.out.println("������ѧ�ţ�");
			Scanner read=new Scanner(System.in);
			String stuId=read.nextLine();
			stuNum=stuId;
		}
	}
	public boolean IfExitId() {
		boolean result=false;
		if(stuNum!=null) {
			result=true;
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Students a=new Students("123");
		Students b=new Students("234");
	}
}
