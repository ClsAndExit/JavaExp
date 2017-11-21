package javaExamp;
/* 
 2.	实现String类
 Java库中提供了String类。给出你自己对下面方法的实现（将新类命名为MyString1）：
 public MyString1(char[] chars);
 public char charAt(int index);
 public int length();
 public MyString1 substring(int begin, int end);
 public static MyString1 valueOf(int i);
 */
public class MyString1 {
	private char[] stringChar;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
//	 public static MyString1 valueOf(int i){
//		 //https://www.cnblogs.com/lxcmyf/p/6427972.html
//		 if (i == Integer.MIN_VALUE)
//		        return "-2147483648";
//		    int size = (i < 0) ? stringSize(-i) + 1 : stringSize(i);
//		    char[] buf = new char[size];
//		    getChars(i, size, buf);
//		    return new String(buf, true);
//	 }
}

