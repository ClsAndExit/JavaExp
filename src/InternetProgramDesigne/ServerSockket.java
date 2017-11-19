package InternetProgramDesigne;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSockket {
	public static final int PORT = 8080;// �����Ķ˿ں�
	CRUDController con = new CRUDController();
	public static void main(String[] args) {
		System.out.println("����������...\n");
		ServerSockket server = new ServerSockket();
		server.init();
	}

	public void init() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();
				// �����������
				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("�������쳣: " + e.getMessage());
		}
	}

	private class HandlerThread implements Runnable {
		private Socket socket;
		

		public HandlerThread(Socket client) {
			socket = client;
			new Thread(this).start();
		}

		public String sudo(String clientInputStr) {
			String result = "";
			String[] arr = clientInputStr.split("\\s+");
			for (String ss : arr) {
				System.out.println(ss);
			}
			switch (arr[0]) {
			case "add":
				con.addStu(add(arr[1], arr[2], Integer.parseInt(arr[3]), arr[4]));
				result = "���ѧ���ɹ���";
				break;
			case "showall":
				for (Student s : con.returnList()) {
					result = "������" + s.getStu_name() + "��ѧ�ţ�" + s.getStu_code() + "������" + s.getStu_age() + "���Ա�"
							+ s.getStu_gender();
				}
				break;
			default:
				result = "OK";
				break;
			}
			return result;
		}

		public Student add(String Code, String Name, int Age, String Gendr) {
			Student student = new Student();
			student.setStu_code(Code);
			student.setStu_name(Name);
			student.setStu_age(Age);
			student.setStu_gender(Gendr);
			return student;
		}

		public void run() {
			try {
				// ��ȡ�ͻ�������
				DataInputStream input = new DataInputStream(socket.getInputStream());
				String clientInputStr = input.readUTF();// ����Ҫע��Ϳͻ����������д������Ӧ,������� EOFException
				// ����ͻ�������
				System.out.println("�ͻ��˷�����������:" + clientInputStr);

				String s = sudo(clientInputStr);

				// ��ͻ��˻ظ���Ϣ
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF(s);
				out.close();
				input.close();
			} catch (Exception e) {
				System.out.println("������ run �쳣: " + e.getMessage());
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (Exception e) {
						socket = null;
						System.out.println("����� finally �쳣:" + e.getMessage());
					}
				}
			}
		}
	}
}
