/**
1.	储物柜难题
一个学校有100个储物柜和100个学生。所有的储物柜在上学第一天都是关着的。随着学生进来，第一个学生，用S1表示，打开每个柜子。
然后，第二个学生，用S2表示，从第二个柜子开始，第二个柜子用L2表示，然后改变每个第二个柜子。学生S3从第三个柜子开始，
然后改变每个第三个柜子（如果它是开的就关上，如果它是关的就打开）。学生S4从柜子L4开始，然后改变每个第四个柜子。
学生S5从L5开始，然后改变每个第五个柜子，以此类推，直到学生S100改变L100为止。在所有学生都经过教学楼并且改变了柜子之后，
哪些柜子是开着的？编写程序找出答案。

2.	打乱行
编写一个方法打乱一个二维整型数组的行：
public static void shuffle(int[][] m)
编写一个测试程序，打乱下面的矩阵：
Int[][] m = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};
 */
package javaExamp;

public class TestClass8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("第一题：");
		openBox();
		System.out.println("第二题：");
		int[][] m = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};
		shuffle(m);
	}

	public static void openBox() {
		int [] arry = new int[101];
		///用数组存放标志信息，数组下标用来表示储物柜的编号,奇数表示储物柜是打开状态
		//给100个柜子赋初值0
		for(int i=0;i<101;i++) {
			arry[i]=0;
		}
		//j表示第几个学生，+1表示状态发生一次改变
		for (int j=1;j<101;j++) {
			for(int i=j;i<101;i+=j) {
				arry[i]+=1;
			}
		}
		//最后打印出数组内容为奇数的索引即为开着的柜子的标号
		for(int i=0;i<101;i++) {
			if (arry[i]%2==1) {
				System.out.println(i+",");
			}
		}
		System.out.println("程序执行完毕");
	}

	public static void shuffle(int[][] m) {
		 //
	}
}
