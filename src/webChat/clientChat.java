package webChat;
import webChat.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

//*******************************该类实现用户聊天****************************************
class clientChat extends JFrame{
	JPanel panelObj;
	JLabel status;
	JLabel status1;
	JLabel imagePos1;
	JLabel imagePos2;
	JLabel imagePos3;
	JLabel imagePos4;
	
	JLabel labelTo;
	JLabel labelColor;
	JLabel labelFace;
	java.awt.List listUser;
	TextArea textMain;
	JComboBox comboTalk;
	JRadioButton radioWhisper;
	JTextField textTalk;
	JComboBox comboFace;
	
	JButton sendMessage;
	JButton logout;	
	JButton update;
	JButton sendFile;
	JButton sendAdvice;
	JButton bClear;
	JButton bExit;
	JButton bColor;
	JButton setFont;
	
	boolean flag;
	String saveAdvice="";
	String userName;
	Listen listen;

	//**********************************构造函数*****************************************
	public clientChat(String name){
		
		userName = name;
		setTitle("Welcome Enter Chat System");  	
     	this.setSize(800,570);
     	Dimension us = this.getSize();
    	//获取屏幕的尺寸
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
	
		panelObj = new JPanel();
		getContentPane().add(panelObj);
		panelObj.setBackground(new Color(150,205,155));
		panelObj.setLayout(null);     		//设置框架窗口的布局管理器为null布局管理器
		Font f = new Font("宋体",Font.PLAIN,12);
		
		//初始化各个组件并赋值
		Icon image1 = new ImageIcon("image/bar.gif");
		imagePos1 = new JLabel(image1);
		imagePos2 = new JLabel(image1);
		
		Icon image3 = new ImageIcon("image/boy2.gif");
		imagePos3 = new JLabel(image3);
		
		Icon image4 = new ImageIcon("image/girl2.gif");
		imagePos4 = new JLabel(image4);
		
		status = new JLabel("海阔天空    任君畅谈");
		status.setForeground(Color.blue);       
		status1 = new JLabel("在线用户名 :"+userName); 
		status1.setForeground(Color.blue);                                                                                      
		labelTo = new JLabel("对");
		labelTo.setFont(f);
		labelColor = new JLabel("颜色");
		labelColor.setFont(f);
		labelFace = new JLabel("表情");
		labelFace.setFont(f);
		
		//用户列表
		String message1 = "************目前用户列表**************";
		listUser = new java.awt.List();
		listUser.add(message1);
		listUser.setBackground(new Color(225,225,225));
		listUser.addActionListener(new NameSelected());
	
	   	//聊天文本域	  
		String message2 = "********************************欢迎进入聊天室***************************************\n\n";
		textMain = new TextArea(message2,110,300,TextArea.SCROLLBARS_VERTICAL_ONLY);
		textMain.setEditable(false);   //聊天文本域不可写
		textMain.setBackground(new Color(225,225,225));
		
		//输入消息框
		textTalk = new JTextField(100);
		textTalk.setBackground(new Color(255,255,255));
		textTalk.setToolTipText("请输入你聊天的内容");
		
		//用户名选项
		String userlist[] ={"所有人"}; 
		comboTalk = new JComboBox(userlist);
		comboTalk.setFont(f);
		comboTalk.setEditable(true);
		comboTalk.setMaximumRowCount(3);  //设置最多显示3行
        comboTalk.setToolTipText("选择你要说话的对象");	
     
        //表情选项
        String face[] = {"无","微笑着","高兴地","冷冷地","不满地","愤怒地","不舍地","歉意地","深情地","大笑","安慰","委屈地","迷惑地","惊讶地","兴奋地","神经","沮丧","生气","撒娇","失望","痛苦"};
        comboFace = new JComboBox(face);

        comboFace.setEditable(true);
        comboFace.setMaximumRowCount(3);  //设置最多显示3行
        comboFace.setToolTipText("选择你说话的表情");	
		
		//私聊按钮	
		radioWhisper = new JRadioButton("悄悄话");
		radioWhisper.setBackground(new Color(150,205,155));
		radioWhisper.setFont(f);
		radioWhisper.setToolTipText("选择");
		radioWhisper.addActionListener(new Whisper());
	   
	 	//设置按钮
	 	sendMessage = new JButton("发   送");
	 	sendMessage.setFont(f);
	 	sendMessage.addMouseListener(new MessageSent());
	 	setFont = new JButton("字 体 设 置");
	 	setFont.setFont(f);
	 	setFont.addActionListener(new FontSet());
	 	sendMessage.setToolTipText("发送你聊天的内容");
	 	setFont.setToolTipText("改变你聊天文本区的字体");
		
		logout = new JButton("用 户 注 销");
		logout.setFont(f);
		logout.addActionListener(new removeUser());
		logout.setToolTipText("从系统中删除你的资料");
		
		update = new JButton("更 新 资 料");
		update.setFont(f);
		update.setToolTipText("更新注册资料");
		update.addActionListener(new ActionListener()
                  {
                      public void actionPerformed(ActionEvent evt)
                      {
                             new UpdateDetail(userName);
                      }
                  });
       	bColor = new JButton("字体颜色");
		bColor.setFont(f);
		bColor.addActionListener(new setColor());
		bColor.setToolTipText("选择你聊天文本区的字体颜色");
		
		sendFile = new JButton("传 送 文 件");
		sendFile.setFont(f);
		sendFile.addActionListener(new FileToServer());
		sendFile.setToolTipText("给在线的其它用户发送文件");
		
		sendAdvice = new JButton("发 送 留 言");
		sendAdvice.setFont(f);
		sendAdvice.addActionListener(new Advice());
		sendAdvice.setToolTipText("给其它用户留言");
			
		bClear = new JButton("清      屏");
		bClear.setFont(f);
		bClear.addActionListener(new ListClear());
		bClear.setToolTipText("清空聊天文本区");
		
		bExit = new JButton("退      出");	
		bExit.setFont(f);
		bExit.addActionListener(new ExitFrame());
		bExit.setToolTipText("退出聊天系统");
		
		sendMessage.setBackground(new Color(0,155,0));
		logout.setBackground(new Color(0,155,0));	
		update.setBackground(new Color(0,155,0));
		sendFile.setBackground(new Color(0,155,0));
		sendAdvice.setBackground(new Color(0,155,0));
		bClear.setBackground(new Color(0,155,0));
		bExit.setBackground(new Color(0,155,0));
		bColor.setBackground(new Color(0,155,0));
		setFont.setBackground(new Color(0,155,0));
	   
		//手工设置各个组件的位置和大小
		listUser.setBounds(new Rectangle(30, 70, 180, 320)); 
		update.setBounds(new Rectangle(30,465,180,22));
		logout.setBounds(new Rectangle(30, 497, 180, 22));
		textMain.setBounds(new Rectangle(220, 70, 550, 320));
		labelTo.setBounds(new Rectangle(220,395,30,20));
		comboTalk.setBounds(new Rectangle(260, 395, 90, 20));
		radioWhisper.setBounds(new Rectangle(360, 395, 70, 22));
		textTalk.setBounds(new Rectangle(440, 395, 330, 22));
		labelColor.setBounds(new Rectangle(220,427,30,22));
		bColor.setBounds(new Rectangle(260,427,90,22));
		labelFace.setBounds(new Rectangle(365,427,30,22));
		comboFace.setBounds(new Rectangle(405,427,90,22));
		setFont.setBounds(new Rectangle(535, 427, 100, 22));
		sendMessage.setBounds(new Rectangle(670, 427, 100, 22));
		sendFile.setBounds(new Rectangle(310, 465, 180, 22));
		sendAdvice.setBounds(new Rectangle(310,497,180,22));
		bClear.setBounds(new Rectangle(590, 465, 180, 22));
		bExit.setBounds(new Rectangle(590, 497, 180, 22));
		
		status.setBounds(new Rectangle(640,525,150,22));
		status1.setBounds(new Rectangle(30,525,200,22));
		
		imagePos1.setBounds(new Rectangle(0,0,400,65));
		imagePos2.setBounds(new Rectangle(400,0,400,65));
		imagePos3.setBounds(new Rectangle(30,395,82,65));
		imagePos4.setBounds(new Rectangle(120,395,90,65));
			
		//将各个组件添加到面板上
		panelObj.add(listUser);
		panelObj.add(textMain);
		panelObj.add(update);
		panelObj.add(logout);
		panelObj.add(labelTo);
		panelObj.add(comboTalk);
		panelObj.add(radioWhisper);
		panelObj.add(textTalk);
		panelObj.add(labelColor);
		panelObj.add(bColor);
		panelObj.add(labelFace);
		panelObj.add(comboFace);
		panelObj.add(setFont);
		panelObj.add(sendMessage);
		panelObj.add(sendFile);
		panelObj.add(bClear);
		panelObj.add(bExit);
		panelObj.add(sendAdvice);
		
		panelObj.add(status);
		panelObj.add(status1);
		
		panelObj.add(imagePos1);
		panelObj.add(imagePos2);
		panelObj.add(imagePos3);
		panelObj.add(imagePos4);

		setResizable(false);
		setVisible(true);	
		
		flag = true;
		listen = new Listen(userName,this);
	}
	//*********************************各种事件处理**************************************
	
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊设置聊天面板颜色＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	class setColor implements ActionListener{	
		public void actionPerformed(ActionEvent e){		
			Object obj = e.getSource();	
			if(obj==(JButton)bColor){	
				Color ch = JColorChooser.showDialog(null,"Swing颜色选取器",getForeground());
				if(ch != null){	
					textMain.setForeground(ch);	
					bColor.setBackground(ch);
				}
			}	
		}	
	}
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊设置聊天面板上的字体＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	class FontSet implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Object obj = e.getSource();
			if(obj== (JButton)setFont)
				setFont();   //调用设置字体方法
		}
	}
	public void setFont(){
		
		FontChooser fChooser = new FontChooser(this);
		fChooser.setVisible(true);
		Font myNewFont = fChooser.getSelectedFont();
		
		textMain.setFont(myNewFont);
		fChooser.dispose();
	}	

	//＊＊＊＊＊＊＊＊＊＊＊如果选择用户为"所有人"则悄悄话不可被选＊＊＊＊＊＊＊＊＊＊＊＊
	class Whisper implements ActionListener{
		public void actionPerformed(ActionEvent e){
	    	if(String.valueOf(comboTalk.getSelectedItem()).equals("所有人"))  	
		    	radioWhisper.setSelected(false);
		}	
	}
	//＊＊＊＊＊＊＊＊＊＊＊＊＊从列表中选中聊天对象＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊	
	class NameSelected implements ActionListener{	
		public void actionPerformed(ActionEvent e){
			boolean flag = false;
		    String uName = listUser.getSelectedItem();
		    //判断选择的用户是否已经存在组合框中
			for(int i = 0; i < comboTalk.getItemCount(); i++){	
				if(uName.equals(comboTalk.getItemAt(i))){    
				     flag = true;
				     break;
			    }
		    }
			if(!flag){             //如果选择用户没有在组合框中，则添加	
				comboTalk.insertItemAt(uName,comboTalk.getItemCount());
		    }
		   comboTalk.setSelectedItem(uName);	 //设置其为当前选项
		}
	}
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊发送聊天信息＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	class MessageSent implements MouseListener{
		Object msgObj;
		ChatMessage chatMessage = new ChatMessage();
		public void mousePressed(MouseEvent e){	
			Object obj = e.getSource();
			if(obj == (JButton)sendMessage){
				try{
					Socket toServer = new Socket("127.0.0.1",1001);
					ObjectOutputStream streamToServer = new ObjectOutputStream(toServer.getOutputStream());
					if(getSendMessage())
						streamToServer.writeObject(msgObj);
				}	
				catch(IOException ee){}
				catch(Exception ie){System.out.println(ie);}
			}
		}
		public void mouseReleased(MouseEvent e){		
			Object obj = e.getSource();		
			if(obj == (JButton)sendMessage){
				textTalk.setText("");
			}
		}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseClicked(MouseEvent e){}
		
		/////////////////////////////////获取信息/////////////////////////////////////////
		public boolean getSendMessage(){	
			String message = new String(textTalk.getText());       //获取聊天信息	
			if(message.length() == 0)        //判断输入是否为空
			{
				JOptionPane jopNamePass = new JOptionPane();
				jopNamePass.showMessageDialog(null,"无法发送空信息!");
				return false;
			}		
			else{
				String toUser =String.valueOf(comboTalk.getSelectedItem());
				if(userName.equals(toUser))                                     //判断是否向自己发送聊天信息
				{
					JOptionPane jopNamePass = new JOptionPane();
					jopNamePass.showMessageDialog(null,"不要自言自语哦!");
					return false;
				}
				GregorianCalendar  calendar = new GregorianCalendar();
       			String time ="【"+calendar.get(calendar.HOUR)+":"+calendar.get(calendar.MINUTE)+":"+
                                 calendar.get(calendar.SECOND)+"】";
				chatMessage.chattime = time;
				chatMessage.chatUser = userName;
				chatMessage.chatToUser = toUser;
				chatMessage.chatMessage = message;
				chatMessage.whisper = radioWhisper.isSelected();
				if(String.valueOf(comboFace.getSelectedItem()).compareTo("无")==0)	
					chatMessage.chatFace = "";
				else
					chatMessage.chatFace = String.valueOf(comboFace.getSelectedItem());
				msgObj = (ChatMessage)chatMessage;
				return true;
			}
		}
	}
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊清空聊天内容＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	class ListClear implements ActionListener{
	 	public void actionPerformed(ActionEvent e){
			Object obj = e.getSource(); 
		    if(obj == (JButton)bClear){
				textMain.setText("");
			}
		}
	}
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊接发留言＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	class Advice implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			Object object=e.getSource();
			
			if(object ==sendAdvice){
				if(flag){
					new AdviceFrame(userName,"发送","退出",saveAdvice);
				}
				else{
					AdviceFrame aFrame =  new AdviceFrame(userName,"保存","删除",saveAdvice);
					sendAdvice.setBackground(Color.lightGray);
					sendAdvice.setText("发 送 留 言");
					sendAdvice.setBackground(new Color(0,155,0));  //使颜色恢复原来的默认色
					flag=true;
				}
			}
		}	
	}
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊文件发送监听器＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	class FileToServer implements ActionListener{
		public void actionPerformed(ActionEvent e){	
			Object object=e.getSource();
			if(object==sendFile){	
				FileSent();	   //调用文件发送方法
			}
		}
	}
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊文件发送＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	public void FileSent(){	
		JOptionPane toUser=new JOptionPane();
		String to = toUser.showInputDialog(this,"请输入文件接收者的名字");
		boolean flag =false;
		
		for(int i=0;i<listUser.getItemCount();i++){	
			if(listUser.getItem(i).equals(to)){
				flag = true;
				break;
			}
		}
		if(!flag){
			JOptionPane jop = new JOptionPane();	
			jop.showMessageDialog(null,"请输入在线用户的名字");
			return;
		}
		String from =userName;
        if(from.equals(to)){
        	JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null,"不必向自己发文件!!!");
			return;
		}
		try{
			
			Socket toServer;
			toServer=new Socket("127.0.0.1",1001);   
            ObjectOutputStream out=new ObjectOutputStream(toServer.getOutputStream());  
            ObjectInputStream in =new ObjectInputStream(toServer.getInputStream());
            JFileChooser chooser = new JFileChooser();        //显示打开文件对话框
            int returnVal = chooser.showOpenDialog(this);   
            
            if(returnVal == JFileChooser.APPROVE_OPTION) {
				try{
					File file = new File(chooser.getSelectedFile().getPath());
				
					//获取文件类型
					String filename=file.toString();
					String format = filename.substring(filename.lastIndexOf("."),filename.length());
							
					//创建存储文件内容的数组
					int size=(int)file.length();
					byte[] fileContent=new byte[size];
							
					//把文件内容读入到数组中
					FileInputStream fileIn=new FileInputStream(file);
					fileIn.read(fileContent);
							
					//将数据存入类中
					FileObject fObject = new FileObject();
					fObject.from = from;
					fObject.to = to;
					fObject.fileContent = fileContent;
					fObject.format = format;		
					out.writeObject((FileObject)fObject);    //发送文件
					fObject.from ="";
					fObject.to ="";
					fObject.fileContent =null;
					fObject.format = "";
					
					ValidateMessage msg =new ValidateMessage();
					msg = (ValidateMessage)in.readObject();
					if(msg.validateMessage.compareTo("ok")==0){
								
						JOptionPane jopNamePass = new JOptionPane();
						jopNamePass.showMessageDialog(null,"成功发送!!!");
					}
				}
				catch(Exception ee){		
					System.out.println(ee);
				}		
			}
			else{
				System.out.println("你不能选择一个文件。");
			}
		}
		catch(Exception ee){		
			System.out.println(ee);					
		}
	}
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊离开聊天室＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	class ExitFrame implements ActionListener{		
		public void actionPerformed(ActionEvent e){
			String string1 = "quit";
        	String string2 = "确定退出!";
        	userExit(string1,string2);
		}
	}		
	//＊＊＊＊＊＊＊＊＊＊＊＊＊＊注销用户＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊	
	class removeUser implements ActionListener{	
		public void actionPerformed(ActionEvent e){
			String msg = "logout";
			String string = "确定注销!" ;	
			userExit(msg,string);
		}
	}
	//＊＊＊＊＊＊＊＊＊＊＊＊＊处理窗口事件＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	protected void processWindowEvent(WindowEvent e) {
    	if (e.getID() == WindowEvent.WINDOW_CLOSING) {
        	String string1 = "quit";
        	String string2 = "确定退出!";
        	userExit(string1,string2);
    	}
	}
	//＊＊＊＊＊＊＊＊＊＊＊＊退出处理＊＊＊＊＊＊＊＊＊�
	public void userExit(String str1,String str2){			
		ValidateMessage exObj = new ValidateMessage();
		exObj.validateName = userName;
		exObj.validateMessage =str1 ;	
		
		try {
       		Socket toServer = new Socket("127.0.0.1",1001);
       		ObjectOutputStream streamToServer = new ObjectOutputStream(toServer.getOutputStream());
			streamToServer.writeObject((ValidateMessage)exObj);
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null,str2);
			streamToServer.close();
 			toServer.close();
 			System.exit(1);
		}
		catch(IOException ee){
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null,"不能向服务器发送信息!");
		}
		catch(Exception ee){}
	}			
}	
	