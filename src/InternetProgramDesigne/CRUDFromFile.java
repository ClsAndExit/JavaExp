package InternetProgramDesigne;

import java.io.File;
/**
 * ���ļ����汣��ѧ����Ϣ
 * @author Admin
 *
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CRUDFromFile {// ���ж����ݵ��޸Ĳ�������List������ɣ�����ֻ�ṩ�ļ���ȡ��д��Ĺ���
	static List<Student> student1 = new ArrayList<Student>();
	// �ҵ�Ŀ���ļ�
	public static File file = new File("D:\\test.txt");

	public List<Student> returnList1() throws ClassNotFoundException, IOException {
		OutputTheFile();
		return student1;
	}

	// �������ݵ�����ͨ��
	static void InputTheFile() throws IOException {
		// �����ļ������ݵĹ�ϵ ���ҽ������л�
		FileOutputStream fos = new FileOutputStream(file,false);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(student1);
		System.out.println("��Ϣ�ɹ���д�����ļ��У�");
		oos.close();
		fos.close();
	}

	// ֻ�ڳ���ʼ��ʱ����ã�����չʾ�ļ�����ԭ�е���Ϣ
	static void OutputTheFile() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			while (true) {
				student1 = (List<Student>) ois.readObject();
			}
		} catch (Exception e) {
		} finally {
			ois.close();
			fis.close();
		}
		System.out.println("�ļ���ȡ�ɹ���");
	}

	/**
	 * ��List�����������һ��ѧ������Ϣ
	 * 
	 * @param stu
	 * @return
	 */
	public boolean addStu(Student stu) {
		boolean result = false;
		if (student1.add(stu)) {
			try {
				InputTheFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = true;
		}
		return result;
	}

	/**
	 * ����ѧ�Ų���ѧ��
	 * 
	 * @param stu
	 * @return
	 */
	public Student searchStu(String stu) {
		for (Student who : student1) {
			if (who.getStu_code().equals(stu)) {
				return who;
			}
		}
		return null;
	}

	/**
	 * ����ѧ����Ϣ
	 * 
	 * @param stu
	 * @return
	 */
	public boolean updateStu(Student stu) {
		boolean result = false;
		for (Student who : student1) {
			if (who.getStu_code().equals(stu.getStu_code())) {
				who.setStu_name(stu.getStu_name());
				who.setStu_age(stu.getStu_age());
				who.setStu_gender(stu.getStu_gender());
				result = true;
			}
		}
		return result;
	}

	/**
	 * ɾ�����ҵ���ѧ��
	 * 
	 * @param stu
	 * @return
	 */
	public boolean deleteStu(String stu) {
		boolean result = false;
		for (int i = 0, len = student1.size(); i < len; ++i) {
			if (student1.get(i).getStu_code().equals(stu)) {
				if (student1.remove(i) != null) {
					result = true;
				}
				--len;// ����һ��
				--i;
			}
		}
		return result;
	}
}
