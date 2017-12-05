package InternetProgramDesigne;


import java.io.*;
import java.util.*;
import java.net.*;

public class StaticServer {
	ServerSocket serversocket;
	//服务器监听端口
	public static int PORT=8080;
	//启动服务器的Socket线程
	public StaticServer(){
		try{
			serversocket=new ServerSocket(PORT);
		}catch(Exception e){
			System.out.println("无法启动服务器："+e.getMessage());
		}
		if (serversocket == null){
			System.exit(-1);//无法开启服务器
			System.out.println("HTTP服务正在运行，端口："+PORT);
		}
	}
	//运行服务器的方法，监听客户端请求并且返回响应
	public void run(){
		while (true){//一直运行
			try{
				Socket client=null;//客户Socket 定义套接字
				//客户机（这里可以使各种浏览器）连接到当前服务器
				client = serversocket.accept();//接收
				if(client==null){
					System.out.println("连接到服务器的用户："+client);
					try{
						//第一阶段：打开输入流
						BufferedReader in = new BufferedReader(
								new InputStreamReader(client.getInputStream()));
						//按行读取，读取第一行：请求地址
						String line = in.readLine();
						//第一个'/'，最后一个'/'的位置-5
						String resource = line.substring(line.indexOf('/'),line.indexOf('/')-5);
						//获得请求的资源的地址
							//解码
						resource = URLDecoder.decode(resource , "gbk" );
						String method = new StringTokenizer(line).nextElement().toString();//获取请求方法，分割出来的第一个元素
						System.out.println("用户请求的资源是："+resource);
						System.out.println("用户请求的类型是："+method);
						//检测是否有问号，判断是否传入参数
						if(resource.indexOf("?")>-1){
							//街区字符串：起始位置和长度
							resource=resource.substring(0,resource.indexOf("?"));
						}
						//读取资源并且返回给客户端
						if("/".equals(resource)){//设置欢迎页面
							resource="/index.html";
						}
						resource=resource.substring(1);//去掉前面的"/"
						PrintStream out = new PrintStream(client.getOutputStream(),true);
						File fileToSend = new File(resource);//创建File对象
						String fileEx = resource.substring(resource.indexOf("."+1));
						String contentType = null;//设置返回的内容类型
						if("htmlhtmxml".indexOf(fileEx)>-1){
							contentType="text/html;cahrest=GBK";
						}else if("jpegjpggifbpmpng".indexOf(fileEx)>-1){
							contentType="application/binary";
						}
						if(fileToSend.exists()&&!fileToSend.isDirectory()){
							//http协议返回头
							out.println("HTTP/1.0 200 OK");//返回应答消息
							out.println("Content-Type:"+ contentType);
							out.println("Content-length:"+ fileToSend.length());//返回内容字节数
							out.println();//空行结束头信息
							FileInputStream fis = null;
							byte data[];
							//省略404
							try{
								data = new byte[fis.available()];
								fis.read(data);
								out.write(data);
							}catch(IOException e){
								//500错误！
								e.printStackTrace();
							}finally{
								out.close();
								fis.close();
							}
						}else{
							//404错误
							out.close();
						}
						//关闭客户端链接
						client.close();
						System.out.println("客户端返回完成！");
					}catch(Exception r){
						System.out.println("服务器错误："+ r.getMessage());
					}
				}
			}catch(Exception e){
				System.out.println("服务器错误："+ e.getMessage());
			}
		}
	}
	
	public static void main(String []args){
		 try {
			 if(args.length!=1){
				 System.out.println("这是一个服务器，端口号：80");
			 }else if(args.length==1){
				 PORT = Integer.parseInt(args[0]);
			 }
		 }catch(Exception e){
			 System.out.println("服务器错误："+ e.getMessage());
		 }
		 new StaticServer().run();
	 }
}

