package afterGitClone;
import java.io 

.IOException;
import java.net.*; 

public class UDPmodel {
		public static void main(String[] args) throws IOException {
//			InetAddress localAddress=InetAddress.getLocalHost();
//			InetAddress remoteAddress=InetAddress.getByName("www.baidu.com 

//			System.out.println("本机的地址为："+localAddress.getHostAddress());
//			System.out.println("谷歌的地址为"+remoteAddress.getHostAddress());
//			System.out.println("3秒可达否"+remoteAddress.isReachable(3000));
//			System.out.println("谷歌度的主机名："+remoteAddress.getHostName());
//			System.out.println(remoteAddress.getClass());
			byte[] buf=new byte[10000];
			DatagramSocket ds=new DatagramSocket(8954);
			DatagramPacket dp=new DatagramPacket(buf,10000);
			System.out.println("等待接收程序:");
			ds.receive(dp);
			System.out.println(dp.getLength());
			String str=new String(dp.getData(),0,dp.getLength())+" from "+dp.getAddress().getHostAddress()+":"+dp.getPort();
			System.out.println(str);
			ds.close();
			
		}

}
