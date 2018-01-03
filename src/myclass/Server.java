package myclass;
import myclass.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

//*************************************创建服务器********************************************
public class Server extends Thread {

	
	public ServerSocket serverSocket;                             //声明套接字
	static Vector userOnLine = new Vector(0,1);            //存储在线用户信息
  	static Vector chatGainedMessage = new Vector(0,1);     //存储在线聊天信息与留言
	static Vector userAdvice =new Vector(0,1);

	public Server(int port){
	
		chatGainedMessage.addElement("");
		try{
			serverSocket = new ServerSocket(port); 
		}
		catch(IOException e){	
			fail(e,"无法启动服务器!");
		}
		System.out.println("服务器启动........");
		
		this.start();    //启动server线程
	}
	
	public static void fail(Exception e,String str){
	
		System.out.println(str + "." + e);                //定义异常输出格式
	}
	//**********************************server 线程******************************************	
	public void run(){                                   
	    
		try{
			while(true){   
			
				Socket client = serverSocket.accept(); 	
				Connection connect=new Connection(client);
			}		
		}
		catch(IOException e){
			fail(e,"Not listening");		      
		}
	}
}

  	