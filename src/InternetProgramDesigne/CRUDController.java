package InternetProgramDesigne;

import java.util.ArrayList;
import java.util.List;

public class CRUDController {
	List <Student> student =new ArrayList<Student>();
	/**
	 * 向List集合里面添加一个学生的信息
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
	 * 添加学生到指定位置
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
	 * 根据学号查找学生
	 * @param stu
	 * @return
	 */
	public Student searchStu(Student stu){
		
		
		return stu;
	}
	/**
	 * 更改学生信息
	 * @param stu
	 * @return
	 */
	public boolean updateStu(Student stu){
		boolean result=false;
		
		return result;
	}
	/**
	 * 删除查找到的学生
	 * @param stu
	 * @return
	 */
	public boolean deleteStu(Student stu){
		boolean result=false;
		
		return result;
	}
}
