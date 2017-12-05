package red_envelope;

import java.net.*;
import java.util.*;


import java.io.*;

public class ServerHttp {
	public static void main(String[] args) throws Exception {
		int port = 8784;
		ServerSocket server = new ServerSocket(port);
		// 自定端口，创建服务端Socket
		System.out.println("开始监听，端口号：" + port);
		while (true) {
			Socket client = server.accept();// 监听获取客户端请求socket，accept方法是一个阻塞方法，在客户端和服务端之间建立联系之前一直等待阻塞
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String Address=client.getInetAddress().toString();
			System.out.println("IP地址为："+Address+"开始请求");
			// GET /test.jpg /HTTP1.1
			
			String line = reader.readLine();
			System.out.println("line: " + line);
			StringBuilder str = new StringBuilder(); // 返回参数
			
			try {
			if (line.contains("/favicon.ico")||line.equals(null)||!line.contains("/action")) {
				//System.out.println("浏览器额外请求，无须理会！");
			} 
			else {
				HandleData handle = new HandleData();
				Map<String, String> map = getClientParam(line);
				if (map.containsKey("action")) {
					String action = map.get("action");
					switch (action) {
					case "3": // 获取所以红包
						str = handle.getAllRecordHtml();
						break;
					case "4": // 查看某个红包详细记录
						str = handle.getAllLogByRedIdHtml(map);
						break;
					case "2": // 发红包
						 str = handle.sendRedPacketHtml(map, Address);
						break;
					case "1": // 抢红包
						str = handle.getRedPacketHtml(map, Address);
						break;
					default:
						// an.setRetrunParam(404);
					}

				} else {
					str.append("<h1>解析失败！</h1>");
				}
		
				String[] strParma=InetAddress.getLocalHost().toString().split("/");
				String ip=strParma[1];
				OutputStream out = client.getOutputStream();
				
				String commonHtml = "<a href='http:\\\\"+ip+":8784/action=1?redId=红包号'>1:抢红包（格式如地址栏）</a><br/><a href='http:\\\\"+ip+":8784/action=2?money=金额&num=人数'>2:发红包（格式如地址栏）</a><br/><a href='http:\\\\"+ip+":8784/action=3'>3:查看所有红包</a><br/><a href='http:\\\\"+ip+":8784/action=4?redId=红包号'>4:查看某一红包详细记录（格式如地址栏）</a><br/>";
				String statusLine = "HTTP/1.1 200 OK\r\n";
				byte[] statusLineBytes = statusLine.getBytes("UTF-8");
				String responseBody = "<html><head><title>From Socket Server</title></head><body><div>" + commonHtml + str
						+ "</div></body></html>";
				byte[] responseBodyBytes = responseBody.getBytes("UTF-8");
				// 回应的头部
				String responseHeader = "Content-Type: text/html; charset=UTF-8\r\nContent-Length: " + responseBodyBytes.length
						+ "\r\n";
				byte[] responseHeaderBytes = responseHeader.getBytes("UTF-8");
				// 向客户端发送状态信息
				out.write(statusLineBytes);
				// 向客户端发送回应头
				out.write(responseHeaderBytes);
				// 头部与内容的分隔行
				out.write(new byte[] { 13, 10 });
				// 向客户端发送内容部分
				out.write(responseBodyBytes);
			    out.flush();
				// 断开与客户端的连接
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

				num = str2[0].split("=")[1]; // 操作码
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
