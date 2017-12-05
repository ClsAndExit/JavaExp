package red_envelope;


import java.io.*;
import java.net.*;

public class Server_Main {

	private static final int PORT = 8008;

	public static void main(String[] args) throws IOException, Exception {
		ServerSocket serverSocket = new ServerSocket(PORT);
		while (true) {
			HandleData handle = new HandleData();
			Socket client = serverSocket.accept();
			System.out.println("��ʼ��ͻ��˽���");
			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
			AnalysisData an = (AnalysisData) ois.readObject();
			String state = an.getHandle();
			String Address = client.getInetAddress().toString();
			switch (state) {
			case "AllRed": // ��ȡ���Ժ��
				an = handle.getAllRecord();
				break;
			case "AllLogById": // �鿴ĳ�������ϸ��¼
				an = handle.getAllLogByRedId(an.getConveryParam()[0]);
				break;
			case "Send": // �����
				an = handle.sendRedPacket(an, Address);
				break;
			case "Get": // �����
				an = handle.getRedPacket(an, Address);
				break;
			default:
				an.setRetrunParam(404);
			}
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject(an);
			System.out.println("������ϣ�");
			oos.close();
			client.close();
		}
	}
}

