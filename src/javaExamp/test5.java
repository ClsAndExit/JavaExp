package javaExamp;

import java.util.Scanner;

public class test5 {

	//�������һ�������������Ϊn(3n-1)/2������n=1,2,...�����ԣ���ʼ�ļ������־���1��5��12��22����д����ķ�������һ���������
	//public static int getPentagonalNumber(int n) ��дһ��������ʾǰ100���������ÿ����ʾ10����
	
	//��һ��������λ����֮�͡����磺sumDigits(234)����9��2+3+4����
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		System.out.println("��һ�⣺");
		Scanner scan =new Scanner (System.in);
		System.out.println("������Ҫ��ӡ��������ĸ�����");
		int n=scan.nextInt();
		getPentagonalNumber(n);
		scan.close();
		
		System.out.println("�ڶ��⣺");
		System.out.println("������Ҫ�����λ����֮�͵�������");
		int num = scan.nextInt();
		sumDigits(num);
	}
	public static void sumDigits(int num) {
		int sum=0;
		int a=num;
		while(num!=0) {
			sum+=num%10;
			num/=10;
		}
		System.out.println(a+"����õ��Ľ��Ϊ��"+sum);
	}
	public static void getPentagonalNumber(int n) {
		int []result=new int [125];
		for (int i=0;i<n;i++) {
			result[i]=(i+1)*(3*(i+1)-1)/2;
		}
		for(int j=0;j<n;j++) {
			if (j%10==0) {
				System.out.println();
			}
			System.out.print(result[j]+" ");
		}
	}

}
