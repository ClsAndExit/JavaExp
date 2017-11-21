package javaExamp;

import java.util.Scanner;

/*
 ���һ����ΪLocation���࣬��λ��ά�����е����ֵ����λ�á���������������������row��column��maxValue��
 ��ά�����е����ֵ�����±���int�͵�row��column�Լ�double�͵�maxValue�洢��
 ��д����ķ���������һ����ά�����е����ֵ��λ�á�
 //public static Location locateLargest(double[][] a)
 ����ֵ��һ��Location��ʵ������дһ�����Գ�����ʾ�û�����һ����ά���飬Ȼ����ʾ��������е����Ԫ�ء�
 */
public class Location {
	int row;
	int column;
	double maxValue;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("��һ�⣺");
		System.out.println("�����ά������У�");
		int r = scan.nextInt();
		System.out.println("�����ά������У�");
		int c = scan.nextInt();
		System.out.println("�����ά����Ԫ�أ�");
		double[][] m = createM(r, c);
		Location la = locateLargest(m);
		System.out.println("��ά�����е����ֵλ��" + la.row + "�У�" + la.column
				+ "�С�������ֵΪ��" + la.maxValue);
	}

	public static double[][] createM(int r, int c) {
		double[][] m = new double[r][c];
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				m[i][j] = scan.nextDouble();
			}
		}
		return m;
	}

	public static Location locateLargest(double[][] a) {
		Location lo = new Location();
		int r = a.length;
		int c = a[0].length;
		int row = 0;
		int column = 0;
		double maxValue = 0.0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (maxValue < a[i][j]) {
					maxValue = a[i][j];
					row = i;
					column = j;
				}
			}
		}
		lo.row = row;
		lo.column = column;
		lo.maxValue = maxValue;
		return lo;
	}
}
