/**
1.	二进制转换为十进制
编写一个方法，将一个二进制数字作为一个字符串转换为一个十进制整数。这个方法头如下所示：
public static int binaryToDecimal(String binaryString)
2.	删除文本
编写一个程序，从一个文本文件中删除掉所有出现某个指定字符串的地方。例如调用
java Exercise9 John filename
则从指定文件中删除掉字符串John。

 */
package javaExamp;

import java.io.*;

public class Test11 {

	public static void main(String[] args) {
		System.out.println("第一题：");
		int value = binaryToDecimal("1001");
		System.out.println("二进制字符串\"1111\"对应的十进制数为：" + value);
		System.out.println("第二题：");
		exercise("1", "D:\\test.txt");
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
			// 读取
			File filename = new File(pathname); // 要读取以上路径的input。txt文件
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String line = null;
			StringBuilder sb = new StringBuilder();
			// line = br.readLine();
			while (true) {
				line = br.readLine(); // 一次读入一行数据
				if (line == null) {
					break;
				}
				sb.append(line.trim());
				sb.append("\r\n");// 加上换行之后保持源文件里面的文件段落不会发生改变
			}
			// 处理数据
			line = sb.toString();
			line = line.replace(str, "");
			// 写入
			File writename = new File(pathname);
			writename.createNewFile(); // 创建新文件 或者在写入数据之前把文件里面原有的数据清除
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.write(line); //
			out.flush(); // 把缓存区内容压入文件
			out.close(); // 最后记得关闭文件

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("文件处理完成！");
	}

}
