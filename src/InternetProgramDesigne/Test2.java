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

		System.out.println("�W����Ϣ�б�");
		for (Student student : con.returnList1()) {
			printStudent(student);
		}

		Student s = credateStudent();
		if (con.addStu(s)) {
			System.out.println("��ӳɹ�");
			// printStudent(s);
		}
		for (Student student : con.returnList1()) {
			printStudent(student);
		}
		System.out.println("����ѧ����ѧ�Ų�ѯѧ����Ϣ��");
		Scanner scan = new Scanner(System.in);
		String searchstucode = scan.nextLine();
		if (con.searchStu(searchstucode) == null) {
			System.out.println("��ѧ�������ڣ�");
		} else {
			printStudent(con.searchStu(searchstucode));
		}

		System.out.println("����ѧ������Ϣ�ڼ�¼���޸ĸÌW̖ѧ������Ϣ��");
		Student stu = credateStudent();
		if (con.updateStu(stu)) {
			System.out.println("�޸ĳɹ���");
			printStudent(stu);
		} else {
			System.out.println("�޸�ʧ�ܣ�");
		}

		System.out.println("����ѧ����ѧ���ڼ�¼��ɾ����ѧ����");
		String deletestucode = scan.nextLine();
		if (con.deleteStu(deletestucode)) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}

	public static Student credateStudent() {
		Student s = new Student();
		Scanner scan = new Scanner(System.in);
		System.out.println("����ѧ������:");
		String stuname = scan.nextLine();
		System.out.println("����ѧ��ѧ��:");
		String stucode = scan.nextLine();
		System.out.println("����ѧ���Ա�:");
		String stugender = scan.nextLine();
		System.out.println("����ѧ������:");
		int stuage = scan.nextInt();
		s.setStu_name(stuname);
		s.setStu_code(stucode);
		s.setStu_age(stuage);
		s.setStu_gender(stugender);
		return s;
	}

	public static void printStudent(Student s) {
		System.out.println("������" + s.getStu_name() + "��ѧ�ţ�" + s.getStu_code() + "������" + s.getStu_age() + "���Ա�"
				+ s.getStu_gender());
	}
}
