package javaExamp;

import java.util.Scanner;

public class test5 {

	//五角数：一个五角数被定义为n(3n-1)/2，其中n=1,2,...。所以，开始的几个数字就是1，5，12，22，编写下面的方法返回一个五角数：
	//public static int getPentagonalNumber(int n) 编写一个程序显示前100个五角数，每行显示10个。
	
	//求一个整数各位数字之和。例如：sumDigits(234)返回9（2+3+4）。
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		System.out.println("第一题：");
		Scanner scan =new Scanner (System.in);
		System.out.println("请输入要打印的五角数的个数：");
		int n=scan.nextInt();
		getPentagonalNumber(n);
		scan.close();
		
		System.out.println("第二题：");
		System.out.println("请输入要计算各位数字之和的整数：");
		int num = scan.nextInt();
		sumDigits(num);
	}
	public static void sumDigits(int num) {
		int sum=0;
		int a=num;
		while(num!=0) {
			sum+=num%10;
			num/=10;
		}
		System.out.println(a+"计算得到的结果为："+sum);
	}
	public static void getPentagonalNumber(int n) {
		int []result=new int [125];
		for (int i=0;i<n;i++) {
			result[i]=(i+1)*(3*(i+1)-1)/2;
		}
		for(int j=0;j<n;j++) {
			if (j%10==0) {
				System.out.println();
			}
			System.out.print(result[j]+" ");
		}
	}

}
