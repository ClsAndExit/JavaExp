package javaExamp;

/* 
 2.	ʵ��String��
 Java�����ṩ��String�ࡣ�������Լ������淽����ʵ�֣�����������ΪMyString1����
 --public MyString1(char[] chars);
 --public char charAt(int index);
 --public int length();
 --public MyString1 substring(int begin, int end);
 public static MyString1 valueOf(int i);
 */
public class MyString1 {
	private char[] stringChar;

	public static void main(String[] args) {
		// ��������
		char[] string = { 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'd', '!' };
		MyString1 string1 = new MyString1(string);
		// ����ַ���
		print(string1);
		// ���λ��Ϊ9���ַ�
		char ch = string1.charAt(9);
		System.out.println(ch);
		// ����ַ����ĳ���
		int length = string1.length();
		System.out.println(length);
		// ��ȡ�ַ������е�һ��3~7
		MyString1 string2 = string1.substring(3, 7);
		print(string2);
		// ��int���͵�����212121214ת��ΪMyString���
		MyString1 string3 = MyString1.valueOf(212121214);
		print(string3);
	}

	public static void print(MyString1 string) {
		char[] chars = string.stringChar;
		for (int i = 0; i < string.length(); i++) {
			System.out.print(chars[i]);
		}
		System.out.println();
	}

	public MyString1(char[] chars) {
		stringChar = chars;
	}

	public char charAt(int index) {
		return stringChar[index - 1];
	}

	public int length() {
		return stringChar.length;
	}

	public MyString1 substring(int begin, int end) {
		char[] string = new char[end - begin + 1];
		int a = 0;
		int b = 0;
		for (a = begin - 1; a < end; a++) {
			string[b] = this.stringChar[a];
			b++;
		}
		MyString1 newString = new MyString1(string);
		return newString;
	}

	public static MyString1 valueOf(int i) {
		int j;
		//����Ӌ�㔵�ֵ�λ���ĕr����є������㣬���ԣ��Ȍ�������ֵ������
		int value=i;
		for (j = 0; i > 0; j++) {
			i = i / 10;
		} 
		char[] string = new char[j];
		for (int m = j - 1; value > 0; m--) {
			// chat(int) ���Զ��İ�int���͵�����ת��ΪASCII���ʾ������Ҫ����ASCII���б�ʾ���֡�������λ��
			string[m] = (char) (value % 10 + 48);
			value = value / 10;
		}
		MyString1 newString = new MyString1(string);
		return newString;
	}
}
