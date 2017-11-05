package InternetProgramDesigne;

import java.io.*;
import java.util.Scanner;

public class copyFile {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String srcPath = "F:\\test.txt";
		String destPath = "F:\\msg\\info.txt";
		try {
			boolean a = copyFileCheck(srcPath, destPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean overlay() {
		System.out.println("目标文件已经存在！是否删除？（yes/no）:");
		Scanner scan = new Scanner(System.in);
		String choice = scan.nextLine();
		scan.close();
		if (choice.equals("yes")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean copyFileCheck(String srcFileName, String destFileName) {
		// 判断原文件是否存在
		File srcFile = new File(srcFileName);
		if (!srcFile.exists()) {
			System.out.println("复制文件失败：原文件" + srcFileName + "不存在！");
			return false;
		} else if (!srcFile.isFile()) {
			System.out.println("复制文件失败：" + srcFileName + "不是一个文件！");
			return false;
		}
		// 判断目标文件是否存在
		File destFile = new File(destFileName);
		if (destFile.exists()) {
			// 如果目标文件存在，而且复制时允许覆盖。
			if (overlay()) {
				// 删除已存在的目标文件，无论目标文件是目录还是单个文件
				System.out.println("正在删除它！");
				if (!destFile.delete()) {
					System.out.println("复制文件失败：删除目标文件" + destFileName + "失败！");
					return false;
				}
			} else {
				System.out.println("复制文件失败：目标文件" + destFileName + "已存在！");
				return false;
			}
		} else {
			if (!destFile.getParentFile().exists()) {
				// 如果目标文件所在的目录不存在，则创建目录
				System.out.println("目标文件所在的目录不存在，准备创建它！");
				if (!destFile.getParentFile().mkdirs()) {
					System.out.println("复制文件失败：创建目标文件所在的目录失败！");
					return false;
				}
			}
		}
		// 准备复制文件
		int byteread = 0;// 读取的位数
		InputStream in = null;
		OutputStream out = null;
		try {
			// 打开原文件
			in = new FileInputStream(srcFile);
			// 打开连接到目标文件的输出流
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];
			// 一次读取1024个字节，当byteread为-1时表示文件已经读完
			while ((byteread = in.read(buffer)) != -1) {
				// 将读取的字节写入输出流
				out.write(buffer, 0, byteread);
			}
			System.out.println("复制单个文件" + srcFileName + "至" + destFileName + "成功！");
			return true;
		} catch (Exception e) {
			System.out.println("复制文件失败：" + e.getMessage());
			return false;
		} finally {
			// 关闭输入输出流，注意先关闭输出流，再关闭输入流
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
