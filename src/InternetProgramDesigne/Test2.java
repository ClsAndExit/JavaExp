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
			System.out.println("��ӳɹ�");
			printStudent(s);
		}
		
	}
	public static Student credateStudent(){
		Student s = new Student();
		s.setStu_name("����С");
		s.setStu_code("202020202");
		s.setStu_age(22);
		s.setStu_gender("man");
		
		return s;
	}
	public static void printStudent(Student s){
		System.out.println("������"+s.getStu_name()+"��ѧ�ţ�"+s.getStu_code()+"������"+s.getStu_age()+"���Ա�"+s.getStu_gender());
	}
}
