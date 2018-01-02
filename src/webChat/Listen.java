package webChat;
import webChat.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
 
//*************************�ͻ����߳�������������������������Ϣ**************************
public class Listen extends Thread{
	                         
	ObjectInputStream fromServer;                     
	ObjectOutputStream toServer;                             
	Socket socket;                                                
	
	clientChat frame;
	String name;  
	Object obj;
	int count;
	//*****************************�̹߳��캯��******************************************
	public Listen(String userName,clientChat chatFrame){
		name = userName;
	 	frame = chatFrame;
		this.start();
    }   
    
    //*******************************�߳����з���***************************************
	public void run(){                               
	    
	    while(true){	
	    	
	    	try{
	    		socket = new Socket("127.0.0.1",1001);
	    		toServer=new ObjectOutputStream(socket.getOutputStream());
	    		fromServer = new ObjectInputStream(socket.getInputStream());	
	    		
	    		ClientName client= new ClientName();
	    		client.clientName = name;
	    		toServer.writeObject((ClientName)client);
	    	
	    		obj=fromServer.readObject();         //��ȡ�ӷ�������������Ϣ
	    	     
			 	if(obj==null){        //����ӷ�������������ϢΪ����Ͽ��˴�����              
			    	frame.textMain.setText("");
		    		socket.close();
			        return;
			    }
				if(obj.getClass().getName().compareTo("myclass.ServerMessage") == 0)    //�ж��Ƿ�Ϊ������Ϣ	
					acceptMessage();
				
				if(obj.getClass().getName().compareTo("myclass.FileObject") == 0)      //�ж��Ƿ�Ϊ�ļ�	
					acceptFile();
	
				toServer.close();
				fromServer.close();
				socket.close();
				GregorianCalendar  calendar = new GregorianCalendar();
       			String time ="��ǰ��ʱ��  [ "+calendar.get(calendar.HOUR)+":"+calendar.get(calendar.MINUTE)+":"+
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
	
	//******************************����ʵ�ָ��ַ���*************************************
	//��ȡ�����Ϣ
	public void acceptMessage(){
		
		//ˢ���û��б�
		ServerMessage msgObj = (ServerMessage)obj;	
		frame.listUser.removeAll();
		frame.listUser.add("***********��ǰ�û��б�************");
		
		for(int i=0; i<msgObj.userOnLine.size();i++){	
			String user = (String)msgObj.userOnLine.elementAt(i);
			frame.listUser.add(user);	
		}
		
		//��������
		String uAdvice="";
		for(int i=0;i<msgObj.userAdvice.size();i++){
			ClientAdvice ca =(ClientAdvice)msgObj.userAdvice.elementAt(i);
			uAdvice =uAdvice+ca.date+" from *"+ca.FromUser+"*  >>>"+ca.advice+"\n";
		}
		if(uAdvice.length()!=0){
			frame.sendAdvice.setBackground(Color.cyan);
			frame.flag=false;
			frame.sendAdvice.setText("�� �� �� ��");
			frame.saveAdvice=uAdvice;
		}
		
		//��ȡ������Ϣ
		if(count == 0){
			count = msgObj.messageGained.size();
		}
		while(count < msgObj.messageGained.size()){
			
			ChatMessage chatMsg = (ChatMessage)msgObj.messageGained.elementAt(count);
			addChatMessage(chatMsg);    //�����������
			count++;
		}	
	}
	
	//******************************�����������*****************************************
	public void addChatMessage(ChatMessage chat){
		String msg = new String();
	
		if(chat.whisper){
			if(chat.chatUser.equals(name)){
				msg = chat.chattime+"�����Ļ���  "+"�Ҷ�"+chat.chatToUser+chat.chatFace+"˵��  "+chat.chatMessage+"\n\n";
			}
			else if(chat.chatToUser.equals(name)){	
				msg = chat.chattime+"�����Ļ���  "+chat.chatUser+chat.chatFace+"����˵��  "+chat.chatMessage+"\n\n";
			}
		}
		else{
			if(chat.chatUser.equals(name)){	
				msg = chat.chattime+"�Ҷ�"+chat.chatToUser+chat.chatFace+"˵��  "+chat.chatMessage+"\n\n";
			}
			else if(chat.chatToUser.equals(name)){	
				msg = chat.chattime+chat.chatUser+chat.chatFace+"����˵��"+chat.chatMessage+"\n\n";
			}
			else{
				msg =chat.chattime+ chat.chatUser +chat.chatFace+"��"+chat.chatToUser+"˵��"+chat.chatMessage+"\n\n";
			}
		}
		frame.textMain.append(msg);
	}
	
	//*********************************�����ļ�*******************************************
	public void acceptFile(){
		
		try{
			FileObject fileFromSer = (FileObject)obj;
			String str;
			str = "�Ƿ񱣴�"+fileFromSer.from+"���������ļ�?";	
			int chooserPane=JOptionPane.showConfirmDialog(frame,str);
	
			if(chooserPane == JOptionPane.OK_OPTION)
			{
				JFileChooser choo = new JFileChooser();
				choo.showSaveDialog(frame);
				File saveFile = choo.getSelectedFile();
				//�����ļ�������			
				FileOutputStream fo=new FileOutputStream(saveFile+((FileObject)fileFromSer).format);
				fo.write(((FileObject)fileFromSer).fileContent);
				fo.close();
			}
		}
		catch(Exception ep){System.out.println("Error"+ep);}
	}
}