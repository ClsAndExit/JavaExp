package org.zm.model;

import java.util.Scanner;

public class exp3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ��һ��:1. ��ȡ����ȷ�Ľ��
		//һ���ܴ������һ����С��������ʱ���ᷢ����ȥ���.Ϊ�˱���������Ȳ��죬����Ҫ����Ҫ��������ֳ��� 10 �� n ���ݣ�
		//����ɼ�����ܹ���ȷʶ���������Ȼ���ٳ��� 10 �� n ���ݣ��󲿷ֱ�����Զ������������Ȳ���ģ�
		System.out.println("��һ��:");
		// �ڼ������������ʱ�����ҵ������Ҫ�ȴ����Ҽ���õ��Ľ������ȷ��1+1/2+1/3+������+1/n
		preciseResult();
		// �ڶ��� :2. �������
		System.out.println("�ڶ���:");
		// ��д���򣬼����������еĺͣ�1/3+3/5+5/7+������+95/97+97/99
		Sum();
	}

	public static void preciseResult() {
		System.out.println("����n��ֵ��");
		Scanner scan = new Scanner(System.in);
		double n = scan.nextInt();
		double result1=0.0;
		double result2=0.0;
		for(double i=1;i<=n;i++) {
			result1+=1/i;
		}
		System.out.println("�����Ҽ���õ��Ľ����"+result1);
		for(double i=n;i>0;i--) {
			result2+=1/i;
		}
		System.out.println("���ҵ������õ��Ľ����"+result2);
	}
	public static void Sum() {
		double sum=0;
		for (double i=1;i<=97;i+=2) {
			sum+=i/(i+2);
		}
		System.out.println(sum);
	}
}
