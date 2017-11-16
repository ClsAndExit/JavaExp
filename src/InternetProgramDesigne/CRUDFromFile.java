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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CRUDFromFile {// ���ж����ݵ��޸Ĳ�������List������ɣ�����ֻ�ṩ�ļ���ȡ��д��Ĺ���
	static List<Student> student1 = new ArrayList<Student>();
	// �ҵ�Ŀ���ļ�
	public static File file = new File("D:\\test.txt");

	public List<Student> returnList1() throws ClassNotFoundException,
			IOException {
		OutputTheFile();
		return student1;
	}

	// �ж��ļ��Ƿ����
	public static boolean judeFileExists() {
		boolean fileExist = false;
		if (file.exists()) {
			System.out.println("�ҵ�Ŀ���ļ���");
			fileExist = true;
		} else {
			System.out.println("Ŀ���ļ������ڣ����ڴ��� ...");
			try {
				file.createNewFile();
				fileExist = true;
				System.out.println("�ļ������ɹ�����ʼ��ı��ݣ�");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileExist;
	}

	// �������ݵ�����ͨ��
	static void InputTheFile() throws IOException {
		if (judeFileExists()) {
			// �����ļ������ݵĹ�ϵ ���ҽ������л�
			FileOutputStream fos = new FileOutputStream(file, false);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			try{
				oos.writeObject(student1);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				oos.close();
				fos.close();
			}
			System.out.println("��Ϣ�ɹ���д�����ļ��У�");
		}else{
			System.out.println("��Ϣ����ʧ����");
		}
	}

	// ֻ�ڳ���ʼ��ʱ����ã�����չʾ�ļ�����ԭ�е���Ϣ
	static void OutputTheFile() throws IOException, ClassNotFoundException {
		if(judeFileExists()){
			FileInputStream fis = new FileInputStream(file);
			if (file.exists() && file.length() == 0) {
				System.out.println("�ļ�Ϊ�գ�");
			} else {
				ObjectInputStream ois = new ObjectInputStream(fis);
				try {
					while (true) {
						
						student1 = (List<Student>) ois.readObject();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					ois.close();
					fis.close();
				}
			}
			System.out.println("�ļ���ȡ��ɣ�����F�ЌW����Ϣ��");
			}else{
				System.out.println("�ļ���ȡʧ����");
		}
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
				try {
					InputTheFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
					try {
						InputTheFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
					result = true;
				}
				--len;// ����һ��
				--i;
			}
		}
		return result;
	}
}