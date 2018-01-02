package webChat;
import webChat.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.*;
//**********************************该类实现用户注册***************************************
public class clientRegister extends JFrame implements ActionListener{
	
	JPanel panelObj;
	JLabel labelName,labelName1;
	JLabel labelSex,labelSex1;
    JLabel logoimagePosition1;
	JLabel logoimagePosition2;
	JLabel labelPassword1,labelPassword11;
	JLabel labelPassword2,labelPassword22;
	JLabel labelEmail,labelEmail1;
	
	JTextField textName;
	JRadioButton radioBoy;
	JRadioButton radioGirl;
	ButtonGroup bg;
	JPasswordField textPassword1;
	JPasswordField textPassword2;
	JTextField textEmail;
	
	JButton b1;
	JButton b2;
	JButton b3;
	
	JLabel imagePos;

	RegisterMessage uobj;	
	//******************************** 设置用户注册窗口************************************
	public clientRegister()
	{
		this.setTitle(" 用户注册");
     	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	setSize(530,300);
     	Dimension us = this.getSize();
   		//获取屏幕的尺寸
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
		
		panelObj = new JPanel();
	    getContentPane().add(panelObj);
		panelObj.setLayout(null);    //设置框架窗口的布局管理器为null布局管理器
		
		//初始化各个组件
		bg = new ButtonGroup();
		Font f = new Font("宋体",Font.PLAIN,12);
		labelName = new JLabel("用户名：");
		labelName.setFont(f);
		labelName1 = new JLabel("(用户名长度限制为2－15字节)");
		labelName1.setFont(f);
		labelSex = new JLabel("性  别：");
		labelSex.setFont(f);
		labelSex1 = new JLabel("(请选择您的性别)");
		labelSex1.setFont(f);
		
	    
	    Icon logoImage1 = new ImageIcon("image/boy.gif");
		logoimagePosition1 = new JLabel(logoImage1);
		logoimagePosition1.setBackground(new Color(200,230,255));
		
		Icon logoImage2 = new ImageIcon("image/girl.gif");
		logoimagePosition2 = new JLabel(logoImage2);
		logoimagePosition2.setBackground(new Color(200,230,255));
		
		labelPassword1 = new JLabel("口  令：");
		labelPassword1.setFont(f);
		labelPassword11 = new JLabel("(密码不得少于6个字符多于12个字符)");
		labelPassword11.setFont(f);
		labelPassword2 = new JLabel("口  令：");
		labelPassword2.setFont(f);
		labelPassword22 = new JLabel("(请再输一遍确认)");
		labelPassword22.setFont(f);
		labelEmail = new JLabel("E-mail：");
		labelEmail.setFont(f);
		labelEmail1 = new JLabel("(请输入正确的邮件地址)");
		labelEmail1.setFont(f);

		radioBoy = new JRadioButton("男 生");
		radioBoy.setBackground(new Color(150,205,155));
		radioBoy.setSelected(true);
		bg.add(radioBoy);
		
		radioGirl = new JRadioButton("女 生");
		radioGirl.setBackground(new Color(150,205,155));
		bg.add(radioGirl);
		
		textName = new JTextField(10);
		textName.setToolTipText("☆请输入您要注册的用户名☆");
		textPassword1 = new JPasswordField(10);
		textPassword1.setToolTipText("☆请输入您的密码☆");
		textPassword2 = new JPasswordField(10);
		textPassword2.setToolTipText("☆请再次输入密码确认☆");
		textEmail = new JTextField(10);
		textEmail.setToolTipText("☆请输入您的E-mail地址☆");
		
		b1 = new JButton("提   交");
		b1.setBackground(new Color(0,155,0));
		b1.addActionListener(this);
		b2 = new JButton("重   置");
		b2.setBackground(new Color(0,155,0));
		b2.addActionListener(this);
		b3 = new JButton("返   回");
		b3.setBackground(new Color(0,155,0));
		b3.addActionListener(this);
		
		Icon image = new ImageIcon("image/ff.jpg");
		imagePos = new JLabel(image);
		
		//手工设置各个组件的位置和大小
		labelName.setBounds(new Rectangle(40, 20, 60, 20));
		textName.setBounds(new Rectangle(100, 20, 180, 20));
		labelName1.setBounds(new Rectangle(300, 20, 200, 20));
		labelSex.setBounds(new Rectangle(40, 55, 60, 20));
		radioBoy.setBounds(new Rectangle(95, 55, 55, 20));
		logoimagePosition1.setBounds(new Rectangle(150, 52, 30, 25));
		radioGirl.setBounds(new Rectangle(185, 55, 55, 20));
		logoimagePosition2.setBounds(new Rectangle(245, 52, 30, 25));
		labelSex1.setBounds(new Rectangle(300, 54, 150, 25));
		labelPassword1.setBounds(new Rectangle(40, 85, 60, 20));
		textPassword1.setBounds(new Rectangle(100, 85, 180, 20));
		labelPassword11.setBounds(new Rectangle(300, 85, 220, 20));
		labelPassword2.setBounds(new Rectangle(40, 120, 60, 20));
		textPassword2.setBounds(new Rectangle(100, 120, 180, 20));
		labelPassword22.setBounds(new Rectangle(300, 120, 220, 20));
		labelEmail.setBounds(new Rectangle(40, 155, 60, 20));
		textEmail.setBounds(new Rectangle(100, 155, 180, 20));
		labelEmail1.setBounds(new Rectangle(300, 155, 220, 20));
		b1.setBounds(new Rectangle(60, 210, 80, 25));
		b2.setBounds(new Rectangle(170,210, 80, 25));
		b3.setBounds(new Rectangle(280, 210, 80, 25));
		
		imagePos.setBounds(new Rectangle(0,0,530,300));
		//添加组件到面板
		panelObj.add(labelName);
		panelObj.add(textName);
		panelObj.add(labelName1);
		panelObj.add(labelSex);
		panelObj.add(radioBoy);
		panelObj.add(logoimagePosition1);
		panelObj.add(radioGirl);
		panelObj.add(logoimagePosition2);
		panelObj.add(labelSex1);
		panelObj.add(labelPassword1);
		panelObj.add(textPassword1);
		panelObj.add(labelPassword11);
		panelObj.add(labelPassword2);
		panelObj.add(textPassword2);
		panelObj.add(labelPassword22);
		panelObj.add(labelEmail);
		panelObj.add(textEmail);
		panelObj.add(labelEmail1);
		panelObj.add(b1);
		panelObj.add(b2);
		panelObj.add(b3);
		
		panelObj.add(imagePos);
		
		this.setResizable(false);
	    this.setVisible(true);
		
	}
	//***************************************监听各类按钮的动作*********************************************
	public void actionPerformed(ActionEvent evt){          
		
		Object obj = evt.getSource();
		
		if(obj == b1){                       //注册
     	    uobj = new RegisterMessage();       //接受用户的详细资料
			uobj.RegisterName = textName.getText();
			uobj.RegisterPassword = new String(textPassword1.getPassword());
			uobj.AffirmPassword = new String(textPassword2.getPassword());
		
			if(radioBoy.isSelected()){
				uobj.Sex = "male";
			}
			else{
				uobj.Sex = "female";
			}
			uobj.RegisterEmail = textEmail.getText();
			
			String userPassword2 = new String(textPassword2.getPassword()); 
			String userEmail = textEmail.getText();
		
			//判断各项输入是否符合规则
			if(uobj.RegisterName.length() == 0){
				
				JOptionPane jopNamePass = new JOptionPane();
				jopNamePass.showMessageDialog(null,"请输入用户名!","提示",JOptionPane.CANCEL_OPTION);
				return;
			}
			
			if((uobj.RegisterName.length()<2) || (uobj.RegisterName.length()>10)){
			
				JOptionPane jopNamePass = new JOptionPane();
				jopNamePass.showMessageDialog(null,"用户名长度限制为2－15字节!","提示",JOptionPane.CANCEL_OPTION);
				textName.setText("");
				return;
			}
			
	        if(uobj.RegisterPassword.length() == 0){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"请输入密码!","提示",JOptionPane.CANCEL_OPTION);
				return;
			}
			
			if((uobj.RegisterPassword.length() < 4) || (uobj.RegisterPassword.length() > 12)){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"密码不得少于6个字符多于10个字符!请重新输入!","提示",JOptionPane.CANCEL_OPTION);
				textPassword1.setText("");
				return;
			}
			
			if(userPassword2.length() == 0){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"请再次输入密码!","提示",JOptionPane.CANCEL_OPTION);
				return;
			}
			
			if(!uobj.RegisterPassword.equals(userPassword2)){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"您输入的密码和确认密码不一致!","提示",JOptionPane.CANCEL_OPTION);
				textPassword2.setText("");
				return;
			}
			
			if(userEmail.length() == 0){
			
				JOptionPane jopPassword = new JOptionPane();
				jopPassword.showMessageDialog(null,"请输入邮箱地址!","提示",JOptionPane.CANCEL_OPTION);
				return;
			}
			
			boolean flay=false;         
			for( int i= 1;i < userEmail.length()-3;i++){   //判断Email输入是否正确
			
				if(userEmail.charAt(i)=='@'){
					for(int j=i+2;j<userEmail.length()-1;j++){
						if(userEmail.charAt(j) =='.'){
							flay=true;	
						}
					}
				}
			}
			if(!flay){
			
				JOptionPane jopPassword = new JOptionPane();
				jopPassword.showMessageDialog(null,"请输入正确有效的邮箱地址！","提示",JOptionPane.CANCEL_OPTION);
				textEmail.setText("");
				return;
			}
			
			//与服务器连接并接发消息						
			try{
		    	
		    	Socket toServer;
		    	toServer = new Socket("127.0.0.1",1001);     //连接到服务器
		    	
		    	//写客户详细资料到服务器socket
		    	ObjectOutputStream streamToServer=new ObjectOutputStream(toServer.getOutputStream());
			    streamToServer.writeObject((RegisterMessage)uobj);
				//读来自服务器socket的登陆状态
             	ObjectInputStream streamFromServer= new ObjectInputStream(toServer.getInputStream());
             	ValidateMessage msg =(ValidateMessage)streamFromServer.readObject();
				String str = msg.validateMessage;
				if(str.toString().equals("ok")){        //注册成功
					
					JOptionPane jopPassword = new JOptionPane();
					jopPassword.showMessageDialog(null,"注册成功！");
					this.dispose();
					
					//获取登陆信息
					clientLogin login=new clientLogin();
					login.txtUserName.setText(uobj.RegisterName);
					login.txtUserPwd.setText(uobj.RegisterPassword);
				}
				else if(str.toString().equals("no")){   //注册失败
			
					JOptionPane jopPassword = new JOptionPane();
					jopPassword.showMessageDialog(null,"该用户名已有人使用！","提示",JOptionPane.CANCEL_OPTION);
					textName.setText("");
					return;
				}
				streamToServer.close();
            	streamFromServer.close();
			}			
			catch(Exception e){
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null,"不能连接服务器！","提示",JOptionPane.CANCEL_OPTION);
			}	
		}
		if(obj == b2){             //清空		
			
			textName.setText("");
			textPassword1.setText("");
			textPassword2.setText("");
			textEmail.setText("");
		}	
		if(obj==b3){               //返回
			
			new clientLogin();		
			this.setVisible(false);
		}
	}
}


