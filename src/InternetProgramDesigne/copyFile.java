package InternetProgramDesigne;

import java.io.*;
import java.util.Scanner;

public class copyFile {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		String srcPath = "F:\\test.txt";
		String destPath = "F:\\msg\\info.txt";
		try {
			boolean a = copyFileCheck(srcPath, destPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean overlay() {
		System.out.println("Ŀ���ļ��Ѿ����ڣ��Ƿ�ɾ������yes/no��:");
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
		// �ж�ԭ�ļ��Ƿ����
		File srcFile = new File(srcFileName);
		if (!srcFile.exists()) {
			System.out.println("�����ļ�ʧ�ܣ�ԭ�ļ�" + srcFileName + "�����ڣ�");
			return false;
		} else if (!srcFile.isFile()) {
			System.out.println("�����ļ�ʧ�ܣ�" + srcFileName + "����һ���ļ���");
			return false;
		}
		// �ж�Ŀ���ļ��Ƿ����
		File destFile = new File(destFileName);
		if (destFile.exists()) {
			// ���Ŀ���ļ����ڣ����Ҹ���ʱ�����ǡ�
			if (overlay()) {
				// ɾ���Ѵ��ڵ�Ŀ���ļ�������Ŀ���ļ���Ŀ¼���ǵ����ļ�
				System.out.println("����ɾ������");
				if (!destFile.delete()) {
					System.out.println("�����ļ�ʧ�ܣ�ɾ��Ŀ���ļ�" + destFileName + "ʧ�ܣ�");
					return false;
				}
			} else {
				System.out.println("�����ļ�ʧ�ܣ�Ŀ���ļ�" + destFileName + "�Ѵ��ڣ�");
				return false;
			}
		} else {
			if (!destFile.getParentFile().exists()) {
				// ���Ŀ���ļ����ڵ�Ŀ¼�����ڣ��򴴽�Ŀ¼
				System.out.println("Ŀ���ļ����ڵ�Ŀ¼�����ڣ�׼����������");
				if (!destFile.getParentFile().mkdirs()) {
					System.out.println("�����ļ�ʧ�ܣ�����Ŀ���ļ����ڵ�Ŀ¼ʧ�ܣ�");
					return false;
				}
			}
		}
		// ׼�������ļ�
		int byteread = 0;// ��ȡ��λ��
		InputStream in = null;
		OutputStream out = null;
		try {
			// ��ԭ�ļ�
			in = new FileInputStream(srcFile);
			// �����ӵ�Ŀ���ļ��������
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];
			// һ�ζ�ȡ1024���ֽڣ���bytereadΪ-1ʱ��ʾ�ļ��Ѿ�����
			while ((byteread = in.read(buffer)) != -1) {
				// ����ȡ���ֽ�д�������
				out.write(buffer, 0, byteread);
			}
			System.out.println("���Ƶ����ļ�" + srcFileName + "��" + destFileName + "�ɹ���");
			return true;
		} catch (Exception e) {
			System.out.println("�����ļ�ʧ�ܣ�" + e.getMessage());
			return false;
		} finally {
			// �ر������������ע���ȹر���������ٹر�������
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
