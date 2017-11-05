package InternetProgramDesigne;

import java.util.ArrayList;
import java.util.List;

public class CRUDController {
	List<Student> student = new ArrayList<Student>();

	public List<Student> returnList(){
		return student;
	}
	/**
	 * 向List集合里面添加一个学生的信息
	 * 
	 * @param stu
	 * @return
	 */
	public boolean addStu(Student stu) {
		boolean result = false;
		if (student.add(stu)) {
			result = true;
		}
		return result;
	}

	/**
	 * 添加学生到指定位置
	 * 
	 * @param index
	 * @param stu
	 * @return
	 */
	public boolean addStu(int index, Student stu) {
		boolean result = false;
		student.add(index, stu);
		return result;
	}

	/**
	 * 根据学号查找学生
	 * 
	 * @param stu
	 * @return
	 */
	public Student searchStu(String stu) {
		for (Student who : student) {
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
		for (Student who : student) {
			if (who.getStu_code().equals(stu.getStu_code())) {
				who.setStu_name(stu.getStu_name());
				who.setStu_age(stu.getStu_age());
				who.setStu_gender(stu.getStu_gender());
				result=true;
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
		for (int i = 0, len = student.size(); i < len; ++i) {
			if (student.get(i).getStu_code().equals(stu)) {
				if (student.remove(i)!=null) {
					result=true;
				}
				--len;// 减少一个
				--i;
			}
		}
		return result;
	}
}
