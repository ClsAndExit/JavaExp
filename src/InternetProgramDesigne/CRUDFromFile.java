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
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class CRUDFromFile {
	
	 //找到目标文件  
    public static File file = new File("D:\\test.txt");  
    //建立数据的输入通道  
	 static void InputTheFile(List<Student> mylis) throws IOException{
			//建立文件与数据的关系   并且将其序列化
	    	FileOutputStream fos = new FileOutputStream(file);
	    	ObjectOutputStream oos = new ObjectOutputStream(fos);
	    	
	    	oos.writeObject(mylis);
	    	System.out.println("信息成功地写入了文件中！");
	    	oos.close();
	    	fos.close();
	    	
		}
	    
	    static List<Student> OutputTheFile() throws IOException, ClassNotFoundException
	    {
	    	FileOutputStream fos = new FileOutputStream(file);
	    	ObjectOutputStream oos = new ObjectOutputStream(fos);
	    	List<Student> mylis = new ArrayList();
//	    	MyClass MC = new MyClass();
	    	try{
	    		while(true){
	        		
	    			Student MC = (Student)((ObjectInput) oos).readObject();
	                mylis.add(MC);
	       	    }
	    	   }catch(Exception e){
	    		   
	    	   }
	    	   finally{
	    		   oos.close();
	           	   fos.close();
	    	   }
	    	   System.out.println("文件读取成功！");
	       	   return mylis;
	    }
	
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
			try {
				InputTheFile(student);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
