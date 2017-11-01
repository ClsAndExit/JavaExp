package examp;

import java.math.*;
import java.util.Scanner;

public class ComputerInternet {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[][] num = allChip();
		int[] m = new int[5]; // �����û���������5������
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

	// �����������ת��Ϊ���������飬�����ж������������Ƿ��������
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

	// �������е���Ƭ����Ϊ��8λ�ģ�����2^8=256����
	public static int[][] allChip() {
		int[][] num = new int[256][];
		for (int i = 0; i < 256; i++) {
			num[i] = intToBinaryArry(i); // ��ʼ����Ƭ
		}
		for (int i = 0; i < 256; i++) {
			for (int j = 0; j < 8; j++) {
				if (num[i][j] == 0)
					num[i][j] = -1; // ��0��ת��Ϊ-1
			}
		}
		return num;
	}

	// ��������ɵ�����ת��Ϊ�����ƣ����ұ����ڶ��������鵱��
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

	// �ж��Ƿ�����
	public static boolean judge(int[] arry1, int[] arry2) {
		int sum = 0;
		for (int i = 0; i < 8; i++)
			sum += arry1[i] * arry2[i];
		if (sum == 0)
			return true;
		else
			return false;
	}

	// �ж��Ƿ�Ϊ����
	public static boolean reverseCode(int[] arry1, int[] arry2) {
		for (int i = 0; i < 8; i++) {
			if (arry1[i] != -arry2[i]) {
				return false;
			}
		}
		return true;
	}
}