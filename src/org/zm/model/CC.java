package org.zm.model;

public class CC {
	public static void main(String[] args) {
		int[] m = new int[5]; // 接受用户随机输入的5个整数
		boolean flag = true;
		while (flag) {
			flag = createNum(m);
		}
		System.out.println("输出5个随机不正交的码片:");
		for (int i = 0; i < 5; i++) {
			print(intToBinaryArry(m[i]));
		}
		// 输出每个码片对应的所有正交码(反碼的隻顯示一個)
		for (int i = 0; i < 5; i++) {
			System.out.print("第" + (i + 1) + "个码片:");
			print(intToBinaryArry(m[i]));
			System.out.println("对应的正交码有：");
			int count = 0;
			for (int j = 0; j < 128; j++) {
				if (judge(intToBinaryArry(m[i]), intToBinaryArry(j))) {
					print(intToBinaryArry(j));
					count++;
				}
			}
			System.out.println(count + "个");
		}
	}

	// 打印输出
	public static void print(int[] arry) {
		for (int i = 0; i < arry.length; i++) {
			System.out.print(arry[i] + " ");
		}
		System.out.println();
	}

	// 产生随机数，转化为二进制数组，并且判断在这组数中是否存在正交
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

	// 将随机生成的整数转化为二进制，并且保存在二进制数组当中（这时把0转化为-1）
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
}
