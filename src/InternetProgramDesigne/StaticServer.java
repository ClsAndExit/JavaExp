package InternetProgramDesigne;


import java.io.*;
import java.util.*;
import java.net.*;

public class StaticServer {
	ServerSocket serversocket;
	//�����������˿�
	public static int PORT=8080;
	//������������Socket�߳�
	public StaticServer(){
		try{
			serversocket=new ServerSocket(PORT);
		}catch(Exception e){
			System.out.println("�޷�������������"+e.getMessage());
		}
		if (serversocket == null){
			System.exit(-1);//�޷�����������
			System.out.println("HTTP�����������У��˿ڣ�"+PORT);
		}
	}
	//���з������ķ����������ͻ��������ҷ�����Ӧ
	public void run(){
		while (true){//һֱ����
			try{
				Socket client=null;//�ͻ�Socket �����׽���
				//�ͻ������������ʹ��������������ӵ���ǰ������
				client = serversocket.accept();//����
				if(client==null){
					System.out.println("���ӵ����������û���"+client);
					try{
						//��һ�׶Σ���������
						BufferedReader in = new BufferedReader(
								new InputStreamReader(client.getInputStream()));
						//���ж�ȡ����ȡ��һ�У������ַ
						String line = in.readLine();
						//��һ��'/'�����һ��'/'��λ��-5
						String resource = line.substring(line.indexOf('/'),line.indexOf('/')-5);
						//����������Դ�ĵ�ַ
							//����
						resource = URLDecoder.decode(resource , "gbk" );
						String method = new StringTokenizer(line).nextElement().toString();//��ȡ���󷽷����ָ�����ĵ�һ��Ԫ��
						System.out.println("�û��������Դ�ǣ�"+resource);
						System.out.println("�û�����������ǣ�"+method);
						//����Ƿ����ʺţ��ж��Ƿ������
						if(resource.indexOf("?")>-1){
							//�����ַ�������ʼλ�úͳ���
							resource=resource.substring(0,resource.indexOf("?"));
						}
						//��ȡ��Դ���ҷ��ظ��ͻ���
						if("/".equals(resource)){//���û�ӭҳ��
							resource="/index.html";
						}
						resource=resource.substring(1);//ȥ��ǰ���"/"
						PrintStream out = new PrintStream(client.getOutputStream(),true);
						File fileToSend = new File(resource);//����File����
						String fileEx = resource.substring(resource.indexOf("."+1));
						String contentType = null;//���÷��ص���������
						if("htmlhtmxml".indexOf(fileEx)>-1){
							contentType="text/html;cahrest=GBK";
						}else if("jpegjpggifbpmpng".indexOf(fileEx)>-1){
							contentType="application/binary";
						}
						if(fileToSend.exists()&&!fileToSend.isDirectory()){
							//httpЭ�鷵��ͷ
							out.println("HTTP/1.0 200 OK");//����Ӧ����Ϣ
							out.println("Content-Type:"+ contentType);
							out.println("Content-length:"+ fileToSend.length());//���������ֽ���
							out.println();//���н���ͷ��Ϣ
							FileInputStream fis = null;
							byte data[];
							//ʡ��404
							try{
								data = new byte[fis.available()];
								fis.read(data);
								out.write(data);
							}catch(IOException e){
								//500����
								e.printStackTrace();
							}finally{
								out.close();
								fis.close();
							}
						}else{
							//404����
							out.close();
						}
						//�رտͻ�������
						client.close();
						System.out.println("�ͻ��˷�����ɣ�");
					}catch(Exception r){
						System.out.println("����������"+ r.getMessage());
					}
				}
			}catch(Exception e){
				System.out.println("����������"+ e.getMessage());
			}
		}
	}
	
	public static void main(String []args){
		 try {
			 if(args.length!=1){
				 System.out.println("����һ�����������˿ںţ�80");
			 }else if(args.length==1){
				 PORT = Integer.parseInt(args[0]);
			 }
		 }catch(Exception e){
			 System.out.println("����������"+ e.getMessage());
		 }
		 new StaticServer().run();
	 }
}

