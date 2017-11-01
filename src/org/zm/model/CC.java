package org.zm.model;

public class CC {
	public static void main(String[] args) {
		int[] m = new int[5]; // �����û���������5������
		boolean flag = true;
		while (flag) {
			flag = createNum(m);
		}
		System.out.println("���5���������������Ƭ:");
		for (int i = 0; i < 5; i++) {
			print(intToBinaryArry(m[i]));
		}
		// ���ÿ����Ƭ��Ӧ������������(���a���b�@ʾһ��)
		for (int i = 0; i < 5; i++) {
			System.out.print("��" + (i + 1) + "����Ƭ:");
			print(intToBinaryArry(m[i]));
			System.out.println("��Ӧ���������У�");
			int count = 0;
			for (int j = 0; j < 128; j++) {
				if (judge(intToBinaryArry(m[i]), intToBinaryArry(j))) {
					print(intToBinaryArry(j));
					count++;
				}
			}
			System.out.println(count + "��");
		}
	}

	// ��ӡ���
	public static void print(int[] arry) {
		for (int i = 0; i < arry.length; i++) {
			System.out.print(arry[i] + " ");
		}
		System.out.println();
	}

	// �����������ת��Ϊ���������飬�����ж������������Ƿ��������
	public static boolean createNum(int m[]) {
		for (int i = 0; i < 5; i++) {
			m[i] = (int) (Math.random() * 255);
			for (int j = 0; j < i; j++) {
				for (int q = j + 1; q < i; q++) {
					if (judge(intToBinaryArry(m[j]), intToBinaryArry(m[q]))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// ��������ɵ�����ת��Ϊ�����ƣ����ұ����ڶ��������鵱�У���ʱ��0ת��Ϊ-1��
	public static int[] intToBinaryArry(int number) {
		int[] b = new int[8];
		int i = 0;
		while (number > 0) {
			b[i] = number % 2;
			number /= 2;
			i++;
		}
		for (int j = 0; j < 8; j++) {
			if (b[j] == 0) {
				b[j] = -1;
			}
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
}
