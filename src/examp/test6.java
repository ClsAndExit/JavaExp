package examp;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class test6 {

	// �������ֵĳ��ִ��� ��д���򣬶�ȡ1��100֮���������Ȼ�����ÿ�������ֵĴ������ٶ���������0������

	// �����ɼ� ��дһ�����򣬶��������ȷ���Ŀ��Է����������ж��ж��ٸ������Ǵ��ڻ����ƽ���֣�
	// ���ٸ������ǵ���ƽ���ֵġ�����һ��������ʾ����Ľ������ٶ��ɼ�����߷�Ϊ10�֡�
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

		System.out.println("��һ��:");
		times();
		System.out.println("�ڶ��⣺");
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
		Set<Entry<Integer, Integer>> sets = map.entrySet(); // ��ȡHashMap��ֵ��
		for (Entry<Integer, Integer> set : sets) { // ����HashMap��ֵ��
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
		System.out.println("ƽ����Ϊ"+average+"���ڵ���ƽ�������ĸ�����"+big+"����С��ƽ�������ĸ�����"+less+"��");
	}

}
