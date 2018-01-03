/**
1.	������ת��Ϊʮ����
��дһ����������һ��������������Ϊһ���ַ���ת��Ϊһ��ʮ�����������������ͷ������ʾ��
public static int binaryToDecimal(String binaryString)
2.	ɾ���ı�
��дһ�����򣬴�һ���ı��ļ���ɾ�������г���ĳ��ָ���ַ����ĵط����������
java Exercise9 John filename
���ָ���ļ���ɾ�����ַ���John��

 */
package javaExamp;

import java.io.*;

public class Test11 {

	public static void main(String[] args) {
//		System.out.println("��һ�⣺");
//		int value = binaryToDecimal("1001");
//		System.out.println("�������ַ���\"1111\"��Ӧ��ʮ������Ϊ��" + value);
//		System.out.println("�ڶ��⣺");
		exercise(".", "D:\\test.txt");
	}

	public static int binaryToDecimal(String binaryString) {
		int valueD = 0;
		int length = binaryString.length();
		char[] ch = binaryString.toCharArray();

		for (int i = 0; i < length;) {
			if (ch[i] == '1') {
				int temp = (int) Math.pow((double) 2, (double) i);
				valueD += temp;
			}
			i++;
		}
		return valueD;
	}

	@SuppressWarnings("resource")
	public static void exercise(String str, String pathname) {
		try {
			// ��ȡ
			File filename = new File(pathname); // Ҫ��ȡ����·����input��txt�ļ�
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // ����һ������������reader
			BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������
			String line = null;
			StringBuilder sb = new StringBuilder();
			// line = br.readLine();
			while (true) { 
				line = br.readLine(); // һ�ζ���һ������
				if (line == null) {
					break;
				}
				sb.append(line.trim());
				sb.append("\r\n");// ���ϻ���֮�󱣳�Դ�ļ�������ļ����䲻�ᷢ���ı�
			}
			// ��������
			line = sb.toString();
			line = line.replace(str, "\r\n");
			// д��
			File writename = new File(pathname);
			writename.createNewFile(); // �������ļ� ������д������֮ǰ���ļ�����ԭ�е��������
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.write(line); //
			out.flush(); // �ѻ���������ѹ���ļ�
			out.close(); // ���ǵùر��ļ�

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("�ļ�������ɣ�");
	}

}
