package InternetProgramDesigne;

import java.util.ArrayList;
import java.util.List;

public class CRUDController {
	List <Student> student =new ArrayList<Student>();
	/**
	 * ��List�����������һ��ѧ������Ϣ
	 * @param stu
	 * @return
	 */
	public boolean addStu(Student stu){
		boolean result=false;
		if(student.add(stu)){
			result=true;
		}
		return result;
	}
	/**
	 * ���ѧ����ָ��λ��
	 * @param index
	 * @param stu
	 * @return
	 */
	public boolean addStu(int index,Student stu){
		boolean result=false;
		student.add(index,stu);
		return result;
	}
	/**
	 * ����ѧ�Ų���ѧ��
	 * @param stu
	 * @return
	 */
	public Student searchStu(Student stu){
		
		
		return stu;
	}
	/**
	 * ����ѧ����Ϣ
	 * @param stu
	 * @return
	 */
	public boolean updateStu(Student stu){
		boolean result=false;
		
		return result;
	}
	/**
	 * ɾ�����ҵ���ѧ��
	 * @param stu
	 * @return
	 */
	public boolean deleteStu(Student stu){
		boolean result=false;
		
		return result;
	}
}
