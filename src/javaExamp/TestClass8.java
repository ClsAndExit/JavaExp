package javaExamp;

import java.util.Scanner;

public class TestClass8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("��һ�⣺");

		System.out.println("�ڶ��⣺");

	}

	public static void openBox() {
		int [] arry = new int[100];
		//�������ű�־��Ϣ�������±�������ʾ�����ı��,������ʾ������Ǵ�״̬
		for(int i=0;i<100;i++) {
			arry[i]=0;
		}
		for (int j=0;j<100;j++) {
			while(j<100) {
				arry[j]+=1;
				j+=j;
			}
		}
	}

	public static int[][] createArry(int row, int col) {
		int[][] arry = new int[row][col];
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				arry[i][j] = scan.nextInt();
			}
		}
		scan.close();
		return arry;
	}
}
