package InternetProgramDesigne;

import java.io.File;
/**
 * 在文件里面保存学生信息
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

public class CRUDFromFile {// 所有对数据的修改操作都在List里面完成，此类只提供文件读取和写入的功能
	static List<Student> student1 = new ArrayList<Student>();
	// 找到目标文件
	public static File file = new File("D:\\test.txt");

	public List<Student> returnList1() throws ClassNotFoundException,
			IOException {
		OutputTheFile();
		return student1;
	}

	// 判断文件是否存在
	public static boolean judeFileExists() {
		boolean fileExist = false;
		if (file.exists()) {
			System.out.println("找到目标文件！");
			fileExist = true;
		} else {
			System.out.println("目标文件不存在，正在创建 ...");
			try {
				file.createNewFile();
				fileExist = true;
				System.out.println("文件创建成功，开始你的表演！");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileExist;
	}

	// 建立数据的输入通道
	static void InputTheFile() throws IOException {
		if (judeFileExists()) {
			// 建立文件与数据的关系 并且将其序列化
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
			System.out.println("信息成功地写入了文件中！");
		}else{
			System.out.println("信息寫入失敗！");
		}
	}

	// 只在程序开始的时候调用，用来展示文件里面原有的信息
	static void OutputTheFile() throws IOException, ClassNotFoundException {
		if(judeFileExists()){
			FileInputStream fis = new FileInputStream(file);
			if (file.exists() && file.length() == 0) {
				System.out.println("文件为空！");
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
			System.out.println("文件读取完成，以下為現有學生信息！");
			}else{
				System.out.println("文件读取失敗！");
		}
	}

	/**
	 * 向List集合里面添加一个学生的信息
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
	 * 根据学号查找学生
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
	 * 更改学生信息
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
	 * 删除查找到的学生
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
				--len;// 减少一个
				--i;
			}
		}
		return result;
	}
}