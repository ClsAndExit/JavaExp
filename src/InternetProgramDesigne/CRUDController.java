package InternetProgramDesigne;

import java.util.ArrayList;
import java.util.List;

public class CRUDController {
	List<Student> student = new ArrayList<Student>();

	public List<Student> returnList(){
		return student;
	}
	/**
	 * ��List�����������һ��ѧ������Ϣ
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
	 * ���ѧ����ָ��λ��
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
	 * ����ѧ�Ų���ѧ��
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
	 * ����ѧ����Ϣ
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
	 * ɾ�����ҵ���ѧ��
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
				--len;// ����һ��
				--i;
			}
		}
		return result;
	}
}
