/**
1.	���������
һ��ѧУ��100��������100��ѧ�������еĴ��������ѧ��һ�춼�ǹ��ŵġ�����ѧ����������һ��ѧ������S1��ʾ����ÿ�����ӡ�
Ȼ�󣬵ڶ���ѧ������S2��ʾ���ӵڶ������ӿ�ʼ���ڶ���������L2��ʾ��Ȼ��ı�ÿ���ڶ������ӡ�ѧ��S3�ӵ��������ӿ�ʼ��
Ȼ��ı�ÿ�����������ӣ�������ǿ��ľ͹��ϣ�������ǹصľʹ򿪣���ѧ��S4�ӹ���L4��ʼ��Ȼ��ı�ÿ�����ĸ����ӡ�
ѧ��S5��L5��ʼ��Ȼ��ı�ÿ����������ӣ��Դ����ƣ�ֱ��ѧ��S100�ı�L100Ϊֹ��������ѧ����������ѧ¥���Ҹı��˹���֮��
��Щ�����ǿ��ŵģ���д�����ҳ��𰸡�

2.	������
��дһ����������һ����ά����������У�
public static void shuffle(int[][] m)
��дһ�����Գ��򣬴�������ľ���
Int[][] m = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};
 */
package javaExamp;

public class TestClass8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("��һ�⣺");
		openBox();
		System.out.println("�ڶ��⣺");
		int[][] m = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};
		shuffle(m);
	}

	public static void openBox() {
		int [] arry = new int[101];
		///�������ű�־��Ϣ�������±�������ʾ�����ı��,������ʾ������Ǵ�״̬
		//��100�����Ӹ���ֵ0
		for(int i=0;i<101;i++) {
			arry[i]=0;
		}
		//j��ʾ�ڼ���ѧ����+1��ʾ״̬����һ�θı�
		for (int j=1;j<101;j++) {
			for(int i=j;i<101;i+=j) {
				arry[i]+=1;
			}
		}
		//����ӡ����������Ϊ������������Ϊ���ŵĹ��ӵı��
		for(int i=0;i<101;i++) {
			if (arry[i]%2==1) {
				System.out.println(i+",");
			}
		}
		System.out.println("����ִ�����");
	}

	public static void shuffle(int[][] m) {
		System.out.println("ԭ���飺");
		for (int i=0;i<5;i++) {
			for (int j=0;j<2;j++) {
				System.out.print(m[i][j]+",");
			}
		}
		System.out.println();
		int [][] temp = new int[1][];
		for(int i=0;i<5;i++) {
			int index=(int)(Math.random()*5);
			temp[0]=m[i];
			m[i]=m[index];
			m[index]=temp[0];
		}
		System.out.println("�������飺");
		for (int i=0;i<5;i++) {
			for (int j=0;j<2;j++) {
				System.out.print(m[i][j]+",");
			}
		}
	}
}
