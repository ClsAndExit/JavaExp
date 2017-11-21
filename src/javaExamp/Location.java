package javaExamp;

import java.util.Scanner;

/*
 设计一个名为Location的类，定位二维数组中的最大值及其位置。这个类包括公共的数据域row、column和maxValue，
 二维数组中的最大值及其下标用int型的row和column以及double型的maxValue存储。
 编写下面的方法，返回一个二维数组中的最大值的位置。
 //public static Location locateLargest(double[][] a)
 返回值是一个Location的实例。编写一个测试程序，提示用户输入一个二维数组，然后显示这个数组中的最大元素。
 */
public class Location {
	int row;
	int column;
	double maxValue;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("第一题：");
		System.out.println("输入二维数组的行：");
		int r = scan.nextInt();
		System.out.println("输入二维数组的列：");
		int c = scan.nextInt();
		System.out.println("输入二维数组元素：");
		double[][] m = createM(r, c);
		Location la = locateLargest(m);
		System.out.println("二维数组中的最大值位置" + la.row + "行，" + la.column
				+ "列。这个最大值为：" + la.maxValue);
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
