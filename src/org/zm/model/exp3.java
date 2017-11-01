package org.zm.model;

import java.util.Scanner;

public class exp3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 第一题:1. 获取更精确的结果
		//一个很大的数与一个很小的数运算时，会发生消去误差.为了避免产生精度差异，我们要把需要计算的数字乘以 10 的 n 次幂，
		//换算成计算机能够精确识别的整数，然后再除以 10 的 n 次幂，大部分编程语言都是这样处理精度差异的，
		System.out.println("第一题:");
		// 在计算下面的数列时，从右到左计算要比从左到右计算得到的结果更精确：1+1/2+1/3+。。。+1/n
		preciseResult();
		// 第二题 :2. 数列求和
		System.out.println("第二题:");
		// 编写程序，计算下面数列的和：1/3+3/5+5/7+。。。+95/97+97/99
		Sum();
	}

	public static void preciseResult() {
		System.out.println("输入n的值：");
		Scanner scan = new Scanner(System.in);
		double n = scan.nextInt();
		double result1=0.0;
		double result2=0.0;
		for(double i=1;i<=n;i++) {
			result1+=1/i;
		}
		System.out.println("从左到右计算得到的结果："+result1);
		for(double i=n;i>0;i--) {
			result2+=1/i;
		}
		System.out.println("从右到左计算得到的结果："+result2);
	}
	public static void Sum() {
		double sum=0;
		for (double i=1;i<=97;i+=2) {
			sum+=i/(i+2);
		}
		System.out.println(sum);
	}
}
