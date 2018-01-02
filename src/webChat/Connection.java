package webChat;
import webChat.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

//******************************Connection���������߳�**********************************
public class Connection extends Thread{
	
	Socket netClient;

  	ObjectInputStream fromClient;
  	ObjectOutputStream toClient;
  	
  	FileInputStream fileInStream;
  	ObjectInputStream objInStream;
  	FileOutputStream fileOutStream;
  	ObjectOutputStream objOutStream;
  	
  	Object obj;     //�洢�ӿͻ��˻�ȡ����Ϣ
    static FileObject fileObj;
 	//***************************Connection �������ӵķ���********************************
  	public Connection(Socket client){
  	
  		netClient = client;
  	  	try{
  	  		fromClient = new ObjectInputStream(netClient.getInputStream());
  	  		toClient = new ObjectOutputStream(netClient.getOutputStream());  	
  	  	}
  	  	catch(IOException e){
  	  		try{
  	  			netClient.close();           //�����������,��ر�Socket
  	  		}
  	  		catch(IOException ee){
  	  			System.out.println("���ܽ�����!" + ee);
  	  			return;
  	  		}
  	  	}
  	  	this.start();       //���� connection �߳�                                        
  	}
  	//******************************connection �߳�**************************************
  	public void run(){

  		try{
	  		obj = fromClient.readObject();    //��ȡ�ӿͻ��˴����Ķ�����

			if(obj == null)         //���ͻ����Ƿ�����Ϣ����
			   return;

			//����������������Ϣ������ѡ����Ӧ�ķ���
			
			if(obj.getClass().getName().compareTo("myclass.LoginMessage") == 0) //��¼
			
				userLogin();
			
			if(obj.getClass().getName().compareTo("myclass.RegisterMessage") == 0) //ע��
			
				userRegister();

			if(obj.getClass().getName().compareTo("myclass.ChatMessage") == 0)  //����
			
				chatMessage();
			
	    	if(obj.getClass().getName().compareTo("myclass.ValidateMessage") == 0)  //�˳�
			
				userExit();
			
			if(obj.getClass().getName().compareTo("myclass.ClientAdvice") == 0) //����
			
				userAdvice();
		
			if(obj.getClass().getName().compareTo("myclass.ClientName")==0)     //�����Ϣ
			
				sendMessage();	
				
	  		if(obj.getClass().getName().compareTo("myclass.FileObject")==0)  //�ļ�`
	
	  			saveFile();
	  			
	  		if(obj.getClass().getName().compareTo("myclass.UpdateUser")==0)  //��������
	  			
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
  	
  	//*******************************��¼�ķ���******************************************* 		
 	public void userLogin(){
  		
  		try{
  	  	    //���ļ��е���Ϣ  			
  			Vector temp; 
  			fileInStream = new FileInputStream("user.txt"); 
  			objInStream = new ObjectInputStream(fileInStream);     			 
  			temp = (Vector)objInStream.readObject();
  
  			LoginMessage logObj = (LoginMessage)obj; //����û������ĵ�½��Ϣ		
  	  	    
  	  	    //�жϵ�¼��Ϣ����ȷ��
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
	 					
						String strmessage = "�û�"+ logObj.loginName +" ��½�ɹ�!\n";
						System.out.println(strmessage);   //�������������ʾ
					}
					else{
						answer("online",loginName);
					}
					break;
				}
			} 
  							
			if(!flag){                                           
				answer("no",loginName);  //ƥ�䲻�ɹ��Ļ�����"no"��Ϣ���ͻ���	
			}
			fileInStream.close();
			objInStream.close();
  		}
  		catch(ClassNotFoundException e){
  			System.out.println(e+"class not found!!!");
  		}
  		
  		catch(NotSerializableException e){}
  		
  		catch(IOException e){
  			System.out.println("�Ҳ����ļ���������ע��!!!");
  		}
  		catch(Exception e){}
  	}   
  		
//**********************************ע��ķ���********************************************
  	public void userRegister(){
  		
  		try{
  			//���ļ��е���Ϣ
  			Vector temp;       //����һ����ʱ����
  			fileInStream = new FileInputStream("user.txt"); 
  			objInStream = new ObjectInputStream(fileInStream);     
  			temp = (Vector)objInStream.readObject();
  			    
  			RegisterMessage regObj = (RegisterMessage)obj;      //��ע����Ϣ���л���ʵ����
  	
  			//��֤�û����Ƿ�������ʹ��
  			String regName = regObj.RegisterName;
  			if(checkName(temp,regName)){         
   	
   				answer("no",regName);
  				fileInStream.close();
  				objInStream.close();
  			}
  			else{
  				//���û�ע����Ϣд���ı��ĵ�
	  			temp.addElement(regObj);        //���ע����Ϣ����ʱ����                 
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
  				//��һ���û�ע��
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
  	
  	//**********************************����������Ϣ**************************************	  
 	public void chatMessage(){
 		
		ChatMessage chatObj = new ChatMessage(); //�����յ��Ķ���ֵ����������Ϣ�����л�����
		chatObj = (ChatMessage)obj;
		Server.chatGainedMessage.addElement((ChatMessage)chatObj); //��������Ϣ����
	}
  	
 	//***************************�û��˳���ע����Ϣ***************************************
	public  void userExit(){
  	 	
  	 	ValidateMessage eObj = new ValidateMessage();
  		eObj = (ValidateMessage)obj;
  	
  		if(eObj.validateMessage.compareTo("quit")==0){            //�˳�
  				
  			for(int i=0; i<Server.userOnLine.size();i++){
  			
  				String client = (String)Server.userOnLine.elementAt(i);
  				
  				if(client.equals(eObj.validateName)){
  					
  					Server.userOnLine.removeElementAt(i);
  					System.out.println("�û�"+ eObj.validateName +"�˳�������! \n");
	  				break;
  				}
  			}	
  		}
  		else{                                                     //ע��
  			
  			for(int i=0; i<Server.userOnLine.size();i++){
  			
  				String client = (String)Server.userOnLine.elementAt(i);
  			
  				if(client.equals(eObj.validateName)){
  					Server.userOnLine.removeElementAt(i);
  					System.out.println("�û�"+ eObj.validateName +"�˳�������! \n");
  				}
  			}
  			try{
  				//��ע���ļ���ɾ���û�	
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
  	
  	//***********************************��������****************************************
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
	
	//***************************���������Ϣ�����Ժ��ļ�********************************	
	public  void sendMessage(){
		
		ClientName cName =new ClientName();
		cName=(ClientName)obj;
		
		if(fileObj!=null&&cName.clientName.equals(fileObj.to)){	       //�ļ�
			
			send((FileObject)fileObj);
			fileObj = null;
		}
		else{                                                      //������Ϣ������
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
	
	//***********************************�����ļ�****************************************
	public void saveFile(){

		fileObj = (FileObject)obj;
		answer("ok",fileObj.from);
			
	}
	
	//***********************************�����û�����*************************************
	public void updateDetail(){
		
		try{
  		
  			Vector temp;             //����һ����ʱ����
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
	  				
	  				objOutStream.writeObject((Vector)temp);  //���±���		
	  				
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
//	��������������������������������������������������������������������������������������
	
	//*****************************������Ϣ����send()����********************************
  	public  void send(Object object){
		
		try{
			toClient.writeObject(object);
			toClient.flush();
		}
		catch(IOException e){	
			System.out.println(e);
		}
	}
	
	//***********************************�˶��û���**************************************	
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
	
	//*********************************��Ӧ�û���ȷ����Ϣ********************************
	public void answer(String answer,String name){
		
		ValidateMessage msg = new ValidateMessage();
  		msg.validateName = name;
  		msg.validateMessage = answer;
  		send(msg);
	}
}