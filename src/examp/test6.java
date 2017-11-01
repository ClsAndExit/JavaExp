package examp;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class test6 {

	// 计算数字的出现次数 编写程序，读取1到100之间的整数，然后计算每个数出现的次数。假定输入是以0结束的

	// 分析成绩 编写一个程序，读入个数不确定的考试分数，并且判断有多少个分数是大于或等于平均分，
	// 多少个分数是低于平均分的。输入一个负数表示输入的结束。假定成绩的最高分为10分。
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

		System.out.println("第一题:");
		times();
		System.out.println("第二题：");
		grade();
	}

	public static void times() {
		int[] arry = changeLength(createArry(0), 0);
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int value : arry) {
			int j = 1;
			if (map.get(value) != null) {
				j = map.get(value) + 1;
			}
			map.put(value, j);
		}
		Set<Entry<Integer, Integer>> sets = map.entrySet(); // 获取HashMap键值对
		for (Entry<Integer, Integer> set : sets) { // 遍历HashMap键值对
			System.out.println("Key:" + set.getKey() + "    value:" + set.getValue());
		}
	}

	public static int[] createArry(int choice) {
		Scanner scan = new Scanner(System.in);
		int[] arry = new int[255];
		int a = scan.nextInt();
		int i = 0;
		if (choice == 0) {
			while (a != 0) {
				arry[i] = a;
				a = scan.nextInt();
				i++;
			}
		} else if (choice < 0) {
			while (a > 0) {
				arry[i] = a;
				a = scan.nextInt();
				i++;
			}
		}
		//scan.close();
		return arry;
	}

	public static int[] changeLength(int[] arry, int choice) {
		int length = 0;
		if (choice == 0) {
			while (arry[length] != 0) {
				length++;
			}
		} else if (choice < 0) {
			while (arry[length] != 0) {
				length++;
			}
		}
		int[] newArry = new int[length];
		for (int i = 0; i < length; i++) {
			newArry[i] = arry[i];
		}
		return newArry;
	}

	public static void grade() {
		int[] arry = changeLength(createArry(-1), -1);
		int average = 0;
		for (int i = 0; i < arry.length; i++) {
			average += arry[i];
		}
		average /= arry.length;
		int less = 0;
		int big = 0;
		for (int j = 0; j < arry.length; j++) {
			if(arry[j]<average) {
				less++;
			}else {
				big++;
			}
		}
		System.out.println("平均数为"+average+"大于等于平均分数的个数有"+big+"个，小于平均分数的个数有"+less+"个");
	}

}
