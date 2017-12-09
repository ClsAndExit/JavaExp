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
			System.out.println("开始与客户端交互");
			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
			AnalysisData an = (AnalysisData) ois.readObject();
			String state = an.getHandle();
			String Address = client.getInetAddress().toString();
			switch (state) {
			case "AllRed": // 获取所以红包
				an = handle.getAllRecord();
				break;
			case "AllLogById": // 查看某个红包详细记录
				an = handle.getAllLogByRedId(an.getConveryParam()[0]);
				break;
			case "Send": // 发红包
				an = handle.sendRedPacket(an, Address);
				break;
			case "Get": // 抢红包
				an = handle.getRedPacket(an, Address);
				break;
			default:
				an.setRetrunParam(404);
			}
			ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject(an);
			System.out.println("发送完毕！");
			oos.close();
			client.close();
		}
	}
}

