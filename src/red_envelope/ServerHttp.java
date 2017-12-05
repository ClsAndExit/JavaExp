package red_envelope;

import java.net.*;
import java.util.*;


import java.io.*;

public class ServerHttp {
	public static void main(String[] args) throws Exception {
		int port = 8784;
		ServerSocket server = new ServerSocket(port);
		// �Զ��˿ڣ����������Socket
		System.out.println("��ʼ�������˿ںţ�" + port);
		while (true) {
			Socket client = server.accept();// ������ȡ�ͻ�������socket��accept������һ�������������ڿͻ��˺ͷ����֮�佨����ϵ֮ǰһֱ�ȴ�����
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String Address=client.getInetAddress().toString();
			System.out.println("IP��ַΪ��"+Address+"��ʼ����");
			// GET /test.jpg /HTTP1.1
			
			String line = reader.readLine();
			System.out.println("line: " + line);
			StringBuilder str = new StringBuilder(); // ���ز���
			
			try {
			if (line.contains("/favicon.ico")||line.equals(null)||!line.contains("/action")) {
				//System.out.println("�������������������ᣡ");
			} 
			else {
				HandleData handle = new HandleData();
				Map<String, String> map = getClientParam(line);
				if (map.containsKey("action")) {
					String action = map.get("action");
					switch (action) {
					case "3": // ��ȡ���Ժ��
						str = handle.getAllRecordHtml();
						break;
					case "4": // �鿴ĳ�������ϸ��¼
						str = handle.getAllLogByRedIdHtml(map);
						break;
					case "2": // �����
						 str = handle.sendRedPacketHtml(map, Address);
						break;
					case "1": // �����
						str = handle.getRedPacketHtml(map, Address);
						break;
					default:
						// an.setRetrunParam(404);
					}

				} else {
					str.append("<h1>����ʧ�ܣ�</h1>");
				}
		
				String[] strParma=InetAddress.getLocalHost().toString().split("/");
				String ip=strParma[1];
				OutputStream out = client.getOutputStream();
				
				String commonHtml = "<a href='http:\\\\"+ip+":8784/action=1?redId=�����'>1:���������ʽ���ַ����</a><br/><a href='http:\\\\"+ip+":8784/action=2?money=���&num=����'>2:���������ʽ���ַ����</a><br/><a href='http:\\\\"+ip+":8784/action=3'>3:�鿴���к��</a><br/><a href='http:\\\\"+ip+":8784/action=4?redId=�����'>4:�鿴ĳһ�����ϸ��¼����ʽ���ַ����</a><br/>";
				String statusLine = "HTTP/1.1 200 OK\r\n";
				byte[] statusLineBytes = statusLine.getBytes("UTF-8");
				String responseBody = "<html><head><title>From Socket Server</title></head><body><div>" + commonHtml + str
						+ "</div></body></html>";
				byte[] responseBodyBytes = responseBody.getBytes("UTF-8");
				// ��Ӧ��ͷ��
				String responseHeader = "Content-Type: text/html; charset=UTF-8\r\nContent-Length: " + responseBodyBytes.length
						+ "\r\n";
				byte[] responseHeaderBytes = responseHeader.getBytes("UTF-8");
				// ��ͻ��˷���״̬��Ϣ
				out.write(statusLineBytes);
				// ��ͻ��˷��ͻ�Ӧͷ
				out.write(responseHeaderBytes);
				// ͷ�������ݵķָ���
				out.write(new byte[] { 13, 10 });
				// ��ͻ��˷������ݲ���
				out.write(responseBodyBytes);
			    out.flush();
				// �Ͽ���ͻ��˵�����
				client.close();

			}
			}
			catch(java.lang.NullPointerException e) {
				client.close();
			}

	
		}

	}

	public static Map<String, String> getClientParam(String line) {
		Map<String, String> param = new HashMap<String, String>();
		String[] str = line.split(" ");
		String[] str2;
		String[] str3;
		String num;
		if (str.length == 3 && str[1].startsWith("/action=")) {
			str2 = str[1].split("\\?");

				num = str2[0].split("=")[1]; // ������
				param.put("action", num);
				if (str2.length == 2) {
				str3 = str2[1].split("=|&");
				if (str3.length % 2 == 0) {
					for (int i = 0; i < str3.length; i++) {
						param.put(str3[i], str3[++i]);
					}
					
				}
			}
		}
		return param;
	}

}
