package InternetProgramDesigne;

import java.io.IOException;
import java.util.Scanner;

public class Test2 {
	/**
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		// CRUDController con = new CRUDController();

		CRUDFromFile con = new CRUDFromFile();

		System.out.println("學生信息列表：");
		for (Student student : con.returnList1()) {
			printStudent(student);
		}

		Student s = credateStudent();
		if (con.addStu(s)) {
			System.out.println("添加成功");
			// printStudent(s);
		}
		for (Student student : con.returnList1()) {
			printStudent(student);
		}
		System.out.println("输入学生的学号查询学生信息：");
		Scanner scan = new Scanner(System.in);
		String searchstucode = scan.nextLine();
		if (con.searchStu(searchstucode) == null) {
			System.out.println("该学生不存在！");
		} else {
			printStudent(con.searchStu(searchstucode));
		}

		System.out.println("输入学生的信息在记录中修改该學號学生的信息：");
		Student stu = credateStudent();
		if (con.updateStu(stu)) {
			System.out.println("修改成功！");
			printStudent(stu);
		} else {
			System.out.println("修改失败！");
		}

		System.out.println("输入学生的学号在记录中删除该学生：");
		String deletestucode = scan.nextLine();
		if (con.deleteStu(deletestucode)) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
	}

	public static Student credateStudent() {
		Student s = new Student();
		Scanner scan = new Scanner(System.in);
		System.out.println("输入学生姓名:");
		String stuname = scan.nextLine();
		System.out.println("输入学生学号:");
		String stucode = scan.nextLine();
		System.out.println("输入学生性别:");
		String stugender = scan.nextLine();
		System.out.println("输入学生年龄:");
		int stuage = scan.nextInt();
		s.setStu_name(stuname);
		s.setStu_code(stucode);
		s.setStu_age(stuage);
		s.setStu_gender(stugender);
		return s;
	}

	public static void printStudent(Student s) {
		System.out.println("姓名：" + s.getStu_name() + "，学号：" + s.getStu_code() + "，年龄" + s.getStu_age() + "，性别："
				+ s.getStu_gender());
	}
}
