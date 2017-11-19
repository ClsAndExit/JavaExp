package InternetProgramDesigne;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSockket {
	public static final int PORT = 8080;// 监听的端口号
	CRUDController con = new CRUDController();
	public static void main(String[] args) {
		System.out.println("服务器启动...\n");
		ServerSockket server = new ServerSockket();
		server.init();
	}

	public void init() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serverSocket.accept();
				// 处理这次连接
				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("服务器异常: " + e.getMessage());
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
				result = "添加学生成功！";
				break;
			case "showall":
				for (Student s : con.returnList()) {
					result = "姓名：" + s.getStu_name() + "，学号：" + s.getStu_code() + "，年龄" + s.getStu_age() + "，性别："
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
				// 读取客户端数据
				DataInputStream input = new DataInputStream(socket.getInputStream());
				String clientInputStr = input.readUTF();// 这里要注意和客户端输出流的写方法对应,否则会抛 EOFException
				// 处理客户端数据
				System.out.println("客户端发过来的内容:" + clientInputStr);

				String s = sudo(clientInputStr);

				// 向客户端回复信息
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF(s);
				out.close();
				input.close();
			} catch (Exception e) {
				System.out.println("服务器 run 异常: " + e.getMessage());
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (Exception e) {
						socket = null;
						System.out.println("服务端 finally 异常:" + e.getMessage());
					}
				}
			}
		}
	}
}
