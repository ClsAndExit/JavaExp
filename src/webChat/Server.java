package webChat;
import webChat.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

//*************************************����������********************************************
public class Server extends Thread{
	
	ServerSocket serverSocket;                             //�����׽���
	static Vector userOnLine = new Vector(0,1);            //�洢�����û���Ϣ
  	static Vector chatGainedMessage = new Vector(0,1);     //�洢����������Ϣ������
	static Vector userAdvice =new Vector(0,1);

	public Server(){
		
		chatGainedMessage.addElement("");
		
		try{
			serverSocket = new ServerSocket(1001);  
		}
		catch(IOException e){	
			fail(e,"�޷�����������!");
		}
		System.out.println("����������........");
		
		this.start();    //����server�߳�
	}
	
	public static void fail(Exception e,String str){
	
		System.out.println(str + "." + e);                //�����쳣�����ʽ
	}
	//**********************************server �߳�******************************************	
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
    //********************************server ��������****************************************
	public static void main(String arg[]){
	
			new Server();                              	//����������
	}
	
}

  	