package webChat;
import webChat.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
 
//*************************客户端线程类用来监听服务器传来的信息**************************
public class Listen extends Thread{
	                         
	ObjectInputStream fromServer;                     
	ObjectOutputStream toServer;                             
	Socket socket;                                                
	
	clientChat frame;
	String name;  
	Object obj;
	int count;
	//*****************************线程构造函数******************************************
	public Listen(String userName,clientChat chatFrame){
		name = userName;
	 	frame = chatFrame;
		this.start();
    }   
    
    //*******************************线程运行方法***************************************
	public void run(){                               
	    
	    while(true){	
	    	
	    	try{
	    		socket = new Socket("127.0.0.1",1001);
	    		toServer=new ObjectOutputStream(socket.getOutputStream());
	    		fromServer = new ObjectInputStream(socket.getInputStream());	
	    		
	    		ClientName client= new ClientName();
	    		client.clientName = name;
	    		toServer.writeObject((ClientName)client);
	    	
	    		obj=fromServer.readObject();         //读取从服务器传来的信息
	    	     
			 	if(obj==null){        //如果从服务器传来的信息为空则断开此次连接              
			    	frame.textMain.setText("");
		    		socket.close();
			        return;
			    }
				if(obj.getClass().getName().compareTo("myclass.ServerMessage") == 0)    //判定是否为聊天信息	
					acceptMessage();
				
				if(obj.getClass().getName().compareTo("myclass.FileObject") == 0)      //判定是否为文件	
					acceptFile();
	
				toServer.close();
				fromServer.close();
				socket.close();
				GregorianCalendar  calendar = new GregorianCalendar();
       			String time ="当前的时间  [ "+calendar.get(calendar.HOUR)+":"+calendar.get(calendar.MINUTE)+":"+
                                 calendar.get(calendar.SECOND)+" ]";
				frame.status.setText(time);
				this.sleep(1000);
			}
			catch(ClassNotFoundException e){
				System.out.println("Error"+e);
			}
			catch(IOException e){
		    	System.out.println();
		    }
		    catch(InterruptedException e){}
		}  
	} 
	
	//******************************以下实现各种方法*************************************
	//获取面板消息
	public void acceptMessage(){
		
		//刷新用户列表
		ServerMessage msgObj = (ServerMessage)obj;	
		frame.listUser.removeAll();
		frame.listUser.add("***********当前用户列表************");
		
		for(int i=0; i<msgObj.userOnLine.size();i++){	
			String user = (String)msgObj.userOnLine.elementAt(i);
			frame.listUser.add(user);	
		}
		
		//接收留言
		String uAdvice="";
		for(int i=0;i<msgObj.userAdvice.size();i++){
			ClientAdvice ca =(ClientAdvice)msgObj.userAdvice.elementAt(i);
			uAdvice =uAdvice+ca.date+" from *"+ca.FromUser+"*  >>>"+ca.advice+"\n";
		}
		if(uAdvice.length()!=0){
			frame.sendAdvice.setBackground(Color.cyan);
			frame.flag=false;
			frame.sendAdvice.setText("查 看 留 言");
			frame.saveAdvice=uAdvice;
		}
		
		//获取聊天信息
		if(count == 0){
			count = msgObj.messageGained.size();
		}
		while(count < msgObj.messageGained.size()){
			
			ChatMessage chatMsg = (ChatMessage)msgObj.messageGained.elementAt(count);
			addChatMessage(chatMsg);    //添加聊天内容
			count++;
		}	
	}
	
	//******************************添加聊天内容*****************************************
	public void addChatMessage(ChatMessage chat){
		String msg = new String();
	
		if(chat.whisper){
			if(chat.chatUser.equals(name)){
				msg = chat.chattime+"★悄悄话★  "+"我对"+chat.chatToUser+chat.chatFace+"说：  "+chat.chatMessage+"\n\n";
			}
			else if(chat.chatToUser.equals(name)){	
				msg = chat.chattime+"★悄悄话★  "+chat.chatUser+chat.chatFace+"对我说：  "+chat.chatMessage+"\n\n";
			}
		}
		else{
			if(chat.chatUser.equals(name)){	
				msg = chat.chattime+"我对"+chat.chatToUser+chat.chatFace+"说：  "+chat.chatMessage+"\n\n";
			}
			else if(chat.chatToUser.equals(name)){	
				msg = chat.chattime+chat.chatUser+chat.chatFace+"对我说："+chat.chatMessage+"\n\n";
			}
			else{
				msg =chat.chattime+ chat.chatUser +chat.chatFace+"对"+chat.chatToUser+"说："+chat.chatMessage+"\n\n";
			}
		}
		frame.textMain.append(msg);
	}
	
	//*********************************接收文件*******************************************
	public void acceptFile(){
		
		try{
			FileObject fileFromSer = (FileObject)obj;
			String str;
			str = "是否保存"+fileFromSer.from+"发过来的文件?";	
			int chooserPane=JOptionPane.showConfirmDialog(frame,str);
	
			if(chooserPane == JOptionPane.OK_OPTION)
			{
				JFileChooser choo = new JFileChooser();
				choo.showSaveDialog(frame);
				File saveFile = choo.getSelectedFile();
				//输入文件到磁盘			
				FileOutputStream fo=new FileOutputStream(saveFile+((FileObject)fileFromSer).format);
				fo.write(((FileObject)fileFromSer).fileContent);
				fo.close();
			}
		}
		catch(Exception ep){System.out.println("Error"+ep);}
	}
}