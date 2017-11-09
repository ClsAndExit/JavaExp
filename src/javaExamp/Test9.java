/**
1.	两个矩阵相乘
编写两个矩阵相乘的方法，方法头如下：
public static double[][] multiplyMatrix(doublep[][] a, double[][] b)
为了使矩阵a能够和矩阵b相乘，矩阵a的列数必须与矩阵b的行数相同，并且两个矩阵的元素要具有相同或兼容的数据类型。

2.	交点
假设两个线段相交。第一个线段的两个端点是(x1,y1)和(x2,y2)，第二个线段的两个端点是(x3,y3)和(x4,y4)。编写一个程序，提示用户输入这4个端点，然后显示他们的交点。

 */
package javaExamp;

public class Test9 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		System.out.println("第一题：");
		double[][] m = { { 1, 2 }, { 3, 4 } };
		double[][] n = { { 7, 8 }, { 9, 10 } };
		System.out.println("矩陣a:");
		printMatrix(m);
		System.out.println("矩陣b:");
		printMatrix(n);
		System.out.println("矩陣aXb:");
		printMatrix(multiplyMatrix(m,n));
		
		
		System.out.println("第二题：");
	}
	/**
	 * 打印二維數組
	 * @param m
	 */
	public static void printMatrix(double[][] m) {
		int mRow = m.length;
		int mColumn = m[0].length;
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mColumn; j++) {
				System.out.print(m[i][j]+" ");
			}
			System.out.println();
		}
	}

	/**
	 * 兩個矩陣相乘，矩陣a的列數必須與b的行數相同，相稱之後的結果為
	 * 
	 * @param a
	 *            矩陣a
	 * @param b
	 *            矩陣b
	 * @return
	 */
	public static double[][] multiplyMatrix(double[][] a, double[][] b) {
		int aRow = a.length;
		int bRow = b.length;
		int aColumn = a[0].length;
		int bColumn = b[0].length;
		double[][] c = new double[aRow][bColumn];
		if (aColumn == bRow) {
			for (int i = 0; i < aRow; i++) {
				for (int j = 0; j < aColumn; j++) {
					for (int k = 0; k < bRow; ++k) {
						c[i][j] += a[i][k] * b[k][j];
					}
				}
			}
		} else {
			System.out.println("矩陣定義出錯，不滿足矩陣乘法要求規範！");
		}

		return c;
	}
}
