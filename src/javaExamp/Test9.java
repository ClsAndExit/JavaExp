/**
1.	�����������
��д����������˵ķ���������ͷ���£�
public static double[][] multiplyMatrix(doublep[][] a, double[][] b)
Ϊ��ʹ����a�ܹ��;���b��ˣ�����a���������������b��������ͬ���������������Ԫ��Ҫ������ͬ����ݵ��������͡�

2.	����
���������߶��ཻ����һ���߶ε������˵���(x1,y1)��(x2,y2)���ڶ����߶ε������˵���(x3,y3)��(x4,y4)����дһ��������ʾ�û�������4���˵㣬Ȼ����ʾ���ǵĽ��㡣

 */
package javaExamp;

public class Test9 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		System.out.println("��һ�⣺");
		double[][] m = { { 1, 2 }, { 3, 4 } };
		double[][] n = { { 7, 8 }, { 9, 10 } };
		System.out.println("���a:");
		printMatrix(m);
		System.out.println("���b:");
		printMatrix(n);
		System.out.println("���aXb:");
		printMatrix(multiplyMatrix(m,n));
		
		
		System.out.println("�ڶ��⣺");
	}
	/**
	 * ��ӡ���S���M
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
	 * �ɂ������ˣ����a���Д�����cb���Д���ͬ����Q֮��ĽY����
	 * 
	 * @param a
	 *            ���a
	 * @param b
	 *            ���b
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
			System.out.println("��ꇶ��x���e�����M���ꇳ˷�Ҫ��Ҏ����");
		}

		return c;
	}
}
