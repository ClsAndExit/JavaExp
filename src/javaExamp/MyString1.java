package javaExamp;

/* 
 2.	实现String类
 Java库中提供了String类。给出你自己对下面方法的实现（将新类命名为MyString1）：
 --public MyString1(char[] chars);
 --public char charAt(int index);
 --public int length();
 --public MyString1 substring(int begin, int end);
 public static MyString1 valueOf(int i);
 */
public class MyString1 {
	private char[] stringChar;

	public static void main(String[] args) {
		// 测试数据
		char[] string = { 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'd', '!' };
		MyString1 string1 = new MyString1(string);
		// 输出字符串
		print(string1);
		// 输出位置为9的字符
		char ch = string1.charAt(9);
		System.out.println(ch);
		// 输出字符串的长度
		int length = string1.length();
		System.out.println(length);
		// 截取字符串当中的一段3~7
		MyString1 string2 = string1.substring(3, 7);
		print(string2);
		// 将int类型的数据212121214转化为MyString输出
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
		//，在計算數字的位數的時候會把數字清零，所以，先將數據的值保存下來
		int value=i;
		for (j = 0; i > 0; j++) {
			i = i / 10;
		} 
		char[] string = new char[j];
		for (int m = j - 1; value > 0; m--) {
			// chat(int) 会自动的把int类型的数据转化为ASCII码表示，所以要加上ASCII码中表示数字“０”的位数
			string[m] = (char) (value % 10 + 48);
			value = value / 10;
		}
		MyString1 newString = new MyString1(string);
		return newString;
	}
}
