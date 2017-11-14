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


public class CRUDFromFile {
	
	 //�ҵ�Ŀ���ļ�  
    public static File file = new File("D:\\test.txt");  
    //�������ݵ�����ͨ��  
	 static void InputTheFile(List<Student> mylis) throws IOException{
			//�����ļ������ݵĹ�ϵ   ���ҽ������л�
	    	FileOutputStream fos = new FileOutputStream(file);
	    	ObjectOutputStream oos = new ObjectOutputStream(fos);
	    	
	    	oos.writeObject(mylis);
	    	System.out.println("��Ϣ�ɹ���д�����ļ��У�");
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
	    	   System.out.println("�ļ���ȡ�ɹ���");
	       	   return mylis;
	    }
	
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
