package myclass;
import myclass.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

//******************************Connection类来连接线程**********************************
public class Connection extends Thread{
	
	Socket netClient;

  	ObjectInputStream fromClient;
  	ObjectOutputStream toClient;
  	
  	FileInputStream fileInStream;
  	ObjectInputStream objInStream;
  	FileOutputStream fileOutStream;
  	ObjectOutputStream objOutStream;
  	
  	Object obj;     //存储从客户端获取的信息
    static FileObject fileObj;
 	//***************************Connection 建立连接的方法********************************
  	public Connection(Socket client){
  	
  		netClient = client;
  	  	try{
  	  		fromClient = new ObjectInputStream(netClient.getInputStream());
  	  		toClient = new ObjectOutputStream(netClient.getOutputStream());  	
  	  	}
  	  	catch(IOException e){
  	  		try{
  	  			netClient.close();           //如果产生错误,则关闭Socket
  	  		}
  	  		catch(IOException ee){
  	  			System.out.println("不能建立流!" + ee);
  	  			return;
  	  		}
  	  	}
  	  	this.start();       //启动 connection 线程                                        
  	}
  	//******************************connection 线程**************************************
  	public void run(){

  		try{
	  		obj = fromClient.readObject();    //读取从客户端传来的对象流

			if(obj == null)         //检查客户端是否有信息传来
			   return;

			//根据所传递来的消息类名来选择相应的方法
			
			if(obj.getClass().getName().compareTo("myclass.LoginMessage") == 0) //登录
			
				userLogin();
			
			if(obj.getClass().getName().compareTo("myclass.RegisterMessage") == 0) //注册
			
				userRegister();

			if(obj.getClass().getName().compareTo("myclass.ChatMessage") == 0)  //聊天
			
				chatMessage();
			
	    	if(obj.getClass().getName().compareTo("myclass.ValidateMessage") == 0)  //退出
			
				userExit();
			
			if(obj.getClass().getName().compareTo("myclass.ClientAdvice") == 0) //留言
			
				userAdvice();
		
			if(obj.getClass().getName().compareTo("myclass.ClientName")==0)     //面板信息
			
				sendMessage();	
				
	  		if(obj.getClass().getName().compareTo("myclass.FileObject")==0)  //文件`
	
	  			saveFile();
	  			
	  		if(obj.getClass().getName().compareTo("myclass.UpdateUser")==0)  //更新资料
	  			
	  			updateDetail();
	  	}
		catch(IOException e){}
	  	
	  	catch(ClassNotFoundException e){
	  		System.out.println(e);
	  	}
	  	catch(Exception e){System.out.println(e);}
  	    finally{	
	  		try{
	  			netClient.close();
	  		}
	  		catch(IOException e){}
	  	}		  
  	}
  	
  	//*******************************登录的方法******************************************* 		
 	public void userLogin(){
  		
  		try{
  	  	    //读文件中的信息  			
  			Vector temp; 
  			fileInStream = new FileInputStream("user.txt"); 
  			objInStream = new ObjectInputStream(fileInStream);     			 
  			temp = (Vector)objInStream.readObject();
  
  			LoginMessage logObj = (LoginMessage)obj; //获得用户传来的登陆信息		
  	  	    
  	  	    //判断登录信息的正确性
  			String loginName = logObj.loginName;
  			String loginPass = logObj.loginPassword;
  			boolean reName =false;
  			boolean flag = false;
			for(int i=0;i<temp.size();i++){
			
				RegisterMessage reg_temp=(RegisterMessage)temp.elementAt(i);
				if(reg_temp.RegisterName.equals(loginName)&&reg_temp.RegisterPassword.equals(loginPass)){
					flag =true;
					for(int j=0;j<Server.userOnLine.size();j++){	
  						if(loginName.equals((String)Server.userOnLine.elementAt(j)))	
  						reName = true;
  						break;
  					}
					if(!reName){
						answer("ok",loginName);
						Server.userOnLine.addElement((String)logObj.loginName);
	 					
						String strmessage = "用户"+ logObj.loginName +" 登陆成功!\n";
						System.out.println(strmessage);   //服务器端输出显示
					}
					else{
						answer("online",loginName);
					}
					break;
				}
			} 
  							
			if(!flag){                                           
				answer("no",loginName);  //匹配不成功的话发送"no"信息到客户端	
			}
			fileInStream.close();
			objInStream.close();
  		}
  		catch(ClassNotFoundException e){
  			System.out.println(e+"class not found!!!");
  		}
  		
  		catch(NotSerializableException e){}
  		
  		catch(IOException e){
  			System.out.println("找不到文件，必须先注册!!!");
  		}
  		catch(Exception e){}
  	}   
  		
//**********************************注册的方法********************************************
  	public void userRegister(){
  		
  		try{
  			//读文件中的信息
  			Vector temp;       //定义一个临时向量
  			fileInStream = new FileInputStream("user.txt"); 
  			objInStream = new ObjectInputStream(fileInStream);     
  			temp = (Vector)objInStream.readObject();
  			    
  			RegisterMessage regObj = (RegisterMessage)obj;      //将注册信息序列化类实例化
  	
  			//验证用户名是否已有人使用
  			String regName = regObj.RegisterName;
  			if(checkName(temp,regName)){         
   	
   				answer("no",regName);
  				fileInStream.close();
  				objInStream.close();
  			}
  			else{
  				//将用户注册信息写入文本文档
	  			temp.addElement(regObj);        //添加注册信息到临时向量                 
	  			fileOutStream = new FileOutputStream("user.txt");       
	  			objOutStream = new ObjectOutputStream(fileOutStream);
	  			objOutStream.writeObject(temp);
	  			
	  			answer("ok",regName);
	  			
	  			fileOutStream.close();
  			    objOutStream.close();
	  		}
  		}
  		catch(IOException e){
  			try{
  				//第一个用户注册
  				Vector temp = new Vector(0,1);
  				RegisterMessage regObj = (RegisterMessage)obj; 
  				temp.addElement(regObj);
  				
  				fileOutStream = new FileOutputStream("user.txt");
  				objOutStream = new ObjectOutputStream(fileOutStream);
  				objOutStream.writeObject(temp);
  				
  				answer("ok",regObj.RegisterName);
  				
  				fileOutStream.close();
  	  	        objOutStream.close();
  	  	    }
  			catch(IOException ex){
  				System.out.println(e);
  			}
  		}
  		catch(ClassNotFoundException e){	
  			System.out.println(e);
  		}
  	}
  	
  	//**********************************保存聊天信息**************************************	  
 	public void chatMessage(){
 		
		ChatMessage chatObj = new ChatMessage(); //将接收到的对象值赋给聊天信息的序列化对象
		chatObj = (ChatMessage)obj;
		Server.chatGainedMessage.addElement((ChatMessage)chatObj); //将聊天信息保存
	}
  	
 	//***************************用户退出与注销信息***************************************
	public  void userExit(){
  	 	
  	 	ValidateMessage eObj = new ValidateMessage();
  		eObj = (ValidateMessage)obj;
  	
  		if(eObj.validateMessage.compareTo("quit")==0){            //退出
  				
  			for(int i=0; i<Server.userOnLine.size();i++){
  			
  				String client = (String)Server.userOnLine.elementAt(i);
  				
  				if(client.equals(eObj.validateName)){
  					
  					Server.userOnLine.removeElementAt(i);
  					System.out.println("用户"+ eObj.validateName +"退出聊天室! \n");
	  				break;
  				}
  			}	
  		}
  		else{                                                     //注销
  			
  			for(int i=0; i<Server.userOnLine.size();i++){
  			
  				String client = (String)Server.userOnLine.elementAt(i);
  			
  				if(client.equals(eObj.validateName)){
  					Server.userOnLine.removeElementAt(i);
  					System.out.println("用户"+ eObj.validateName +"退出聊天室! \n");
  				}
  			}
  			try{
  				//从注册文件中删除用户	
  				Vector temp = new Vector(0,1);
  				fileInStream = new FileInputStream("user.txt"); 
  				objInStream = new ObjectInputStream(fileInStream);     
  				temp = (Vector)objInStream.readObject();
  			    	  				
  				for(int i=0; i<temp.size();i++){
  				
  					RegisterMessage regtemp = (RegisterMessage)temp.elementAt(i);
  					if(eObj.validateName.compareTo(regtemp.RegisterName)==0){
  			
  						temp.removeElementAt(i);
  						break;
  					}
  				}
  				fileInStream.close();
  				objInStream.close();
  				
  				fileOutStream = new FileOutputStream("user.txt");
  				objOutStream = new ObjectOutputStream(fileOutStream);
  				objOutStream.writeObject((Vector)temp);
  				
  				fileOutStream.close();
  				objOutStream.close();
  			}
  			catch(Exception ee){}
  		}
  	}
  	
  	//***********************************保存留言****************************************
	public void userAdvice(){
	
		ClientAdvice eObj = new ClientAdvice();
  		eObj = (ClientAdvice)obj;
	
		String str=eObj.ToUser;
		try{
			Vector temp; 
  			fileInStream = new FileInputStream("user.txt"); 
  			objInStream = new ObjectInputStream(fileInStream);     			 
  			temp = (Vector)objInStream.readObject();
  			if(!checkName(temp,str))
  				return;
  			fileInStream.close();
  			objInStream.close();
  		}
  		catch(Exception ie){}
  		String str1=eObj.FromUser;
		String str2="ok";
		answer(str2,str1);
  		Server.userAdvice.addElement(eObj);  	
	}
	
	//***************************发送面板信息、留言和文件********************************	
	public  void sendMessage(){
		
		ClientName cName =new ClientName();
		cName=(ClientName)obj;
		
		if(fileObj!=null&&cName.clientName.equals(fileObj.to)){	       //文件
			
			send((FileObject)fileObj);
			fileObj = null;
		}
		else{                                                      //聊天消息与留言
			Vector temp =new Vector(0,1);
			
			int i=0;
			while (i<Server.userAdvice.size()){
			
				ClientAdvice ca =(ClientAdvice)Server.userAdvice.elementAt(i);
			
				if(ca.ToUser.equals(cName.clientName)){
					temp.addElement((ClientAdvice)ca);
					Server.userAdvice.remove(ca);
					i--;
				}
				i++;
			}
			ServerMessage msg = new ServerMessage();

			msg.messageGained = Server.chatGainedMessage;
			msg.userOnLine =Server.userOnLine;
			msg.userAdvice =temp;
	
			send(msg);
		}
	}
	
	//***********************************保存文件****************************************
	public void saveFile(){

		fileObj = (FileObject)obj;
		answer("ok",fileObj.from);
			
	}
	
	//***********************************更新用户资料*************************************
	public void updateDetail(){
		
		try{
  		
  			Vector temp;             //定义一个临时向量
  			fileInStream = new FileInputStream("user.txt"); 
  			objInStream = new ObjectInputStream(fileInStream);     
  			temp = (Vector)objInStream.readObject();
  			    
  			UpdateUser uObj = (UpdateUser)obj;                                
  			String uName = uObj.userName;
  			
  			boolean flag = false;
  			for(int i=0;i<temp.size();i++){
  				
  				RegisterMessage regtemp = (RegisterMessage)temp.elementAt(i);
  				if(uName.compareTo(regtemp.RegisterName)==0){
  					
  					regtemp.RegisterPassword = uObj.UpdatePass;
  					regtemp.AffirmPassword = uObj.RePass;
  					regtemp.RegisterEmail = uObj.UpdateEmail;
  				
  					fileInStream.close();
  					objInStream.close();
  					
  					fileOutStream = new FileOutputStream("user.txt");                        
	  				objOutStream = new ObjectOutputStream(fileOutStream);
	  				
	  				objOutStream.writeObject((Vector)temp);  //重新保存		
	  				
	  				fileOutStream.close();
	  				objOutStream.close();
	  				
	  				answer("ok",uName);
	  				flag = true;
	  				break;
  				}	
  			}
  			if(!flag){
  				
  				fileInStream.close();
  				objInStream.close();
  				answer("no",uName);
  			}
  		}
  		catch(Exception e){}
	}
//	＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	
	//*****************************定义信息发送send()方法********************************
  	public  void send(Object object){
		
		try{
			toClient.writeObject(object);
			toClient.flush();
		}
		catch(IOException e){	
			System.out.println(e);
		}
	}
	
	//***********************************核对用户名**************************************	
	public boolean checkName(Vector vector,String name){
	
  		boolean flag=false;
   		
   		for(int i = 0; i < vector.size(); i++){
  				
  			RegisterMessage regtemp = (RegisterMessage)vector.elementAt(i);
 
  			if(name.compareTo(regtemp.RegisterName)==0){
  				flag=true;
  				break;
  			}
  		}
  		return flag;
	}
	
	//*********************************响应用户需确认信息********************************
	public void answer(String answer,String name){
		
		ValidateMessage msg = new ValidateMessage();
  		msg.validateName = name;
  		msg.validateMessage = answer;
  		send(msg);
	}
}