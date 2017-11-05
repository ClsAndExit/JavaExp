package examp;

import java.math.*;
import java.util.Scanner;

public class ComputerInternet {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[][] num = allChip();
		int[] m = new int[5]; // 接受用户随机输入的5个整数
		boolean flag = true;
		while (flag) {
			flag = createNum(m);
		}
		if (!flag) {
			for (int i = 0; i < 5; i++) {
				System.out.println(m[i]);
			}
		}
	}

	// 产生随机数，转化为二进制数组，并且判断在这组数中是否存在正交
	public static boolean createNum(int m[]) {
		for (int i = 0; i < 5; i++) {
			m[i] = (int) (Math.random() * 255);
			if (i > 0) {
				for (int j = 0; j < i - 1; j++) {
					for (int q = j + 1; q < i; q++) {
						if (judge(intToBinaryArry(m[j]), intToBinaryArry(m[q]))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	// 生成所有的码片，因为是8位的，所以2^8=256长度
	public static int[][] allChip() {
		int[][] num = new int[256][];
		for (int i = 0; i < 256; i++) {
			num[i] = intToBinaryArry(i); // 初始化码片
		}
		for (int i = 0; i < 256; i++) {
			for (int j = 0; j < 8; j++) {
				if (num[i][j] == 0)
					num[i][j] = -1; // 将0都转化为-1
			}
		}
		return num;
	}

	// 将随机生成的整数转化为二进制，并且保存在二进制数组当中
	public static int[] intToBinaryArry(int number) {
		int[] b = new int[8];
		for (int i = 0; i < 8; i++)
			b[i] = 0;
		int i = 0;
		while (number > 0) {
			b[i] = number % 2;
			number /= 2;
			i++;
		}
		return b;
	}

	// 判断是否正交
	public static boolean judge(int[] arry1, int[] arry2) {
		int sum = 0;
		for (int i = 0; i < 8; i++)
			sum += arry1[i] * arry2[i];
		if (sum == 0)
			return true;
		else
			return false;
	}

	// 判断是否互为反码
	public static boolean reverseCode(int[] arry1, int[] arry2) {
		for (int i = 0; i < 8; i++) {
			if (arry1[i] != -arry2[i]) {
				return false;
			}
		}
		return true;
	}
}