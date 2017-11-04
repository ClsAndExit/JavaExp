package InternetProgramDesigne;

public class Test2 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("klasdfklajsklfgasjfkla");
		CRUDController con = new CRUDController();
		Student s = credateStudent();
		if(con.addStu(s)){
			System.out.println("添加成功");
			printStudent(s);
		}
		
	}
	public static Student credateStudent(){
		Student s = new Student();
		s.setStu_name("王二小");
		s.setStu_code("202020202");
		s.setStu_age(22);
		s.setStu_gender("man");
		
		return s;
	}
	public static void printStudent(Student s){
		System.out.println("姓名："+s.getStu_name()+"，学号："+s.getStu_code()+"，年龄"+s.getStu_age()+"，性别："+s.getStu_gender());
	}
}
