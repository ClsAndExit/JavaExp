package webChat;
import webChat.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.*;
//**********************************����ʵ���û�ע��***************************************
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
	//******************************** �����û�ע�ᴰ��************************************
	public clientRegister()
	{
		this.setTitle(" �û�ע��");
     	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	setSize(530,300);
     	Dimension us = this.getSize();
   		//��ȡ��Ļ�ĳߴ�
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
		
		panelObj = new JPanel();
	    getContentPane().add(panelObj);
		panelObj.setLayout(null);    //���ÿ�ܴ��ڵĲ��ֹ�����Ϊnull���ֹ�����
		
		//��ʼ���������
		bg = new ButtonGroup();
		Font f = new Font("����",Font.PLAIN,12);
		labelName = new JLabel("�û�����");
		labelName.setFont(f);
		labelName1 = new JLabel("(�û�����������Ϊ2��15�ֽ�)");
		labelName1.setFont(f);
		labelSex = new JLabel("��  ��");
		labelSex.setFont(f);
		labelSex1 = new JLabel("(��ѡ�������Ա�)");
		labelSex1.setFont(f);
		
	    
	    Icon logoImage1 = new ImageIcon("image/boy.gif");
		logoimagePosition1 = new JLabel(logoImage1);
		logoimagePosition1.setBackground(new Color(200,230,255));
		
		Icon logoImage2 = new ImageIcon("image/girl.gif");
		logoimagePosition2 = new JLabel(logoImage2);
		logoimagePosition2.setBackground(new Color(200,230,255));
		
		labelPassword1 = new JLabel("��  �");
		labelPassword1.setFont(f);
		labelPassword11 = new JLabel("(���벻������6���ַ�����12���ַ�)");
		labelPassword11.setFont(f);
		labelPassword2 = new JLabel("��  �");
		labelPassword2.setFont(f);
		labelPassword22 = new JLabel("(������һ��ȷ��)");
		labelPassword22.setFont(f);
		labelEmail = new JLabel("E-mail��");
		labelEmail.setFont(f);
		labelEmail1 = new JLabel("(��������ȷ���ʼ���ַ)");
		labelEmail1.setFont(f);

		radioBoy = new JRadioButton("�� ��");
		radioBoy.setBackground(new Color(150,205,155));
		radioBoy.setSelected(true);
		bg.add(radioBoy);
		
		radioGirl = new JRadioButton("Ů ��");
		radioGirl.setBackground(new Color(150,205,155));
		bg.add(radioGirl);
		
		textName = new JTextField(10);
		textName.setToolTipText("����������Ҫע����û�����");
		textPassword1 = new JPasswordField(10);
		textPassword1.setToolTipText("�����������������");
		textPassword2 = new JPasswordField(10);
		textPassword2.setToolTipText("�����ٴ���������ȷ�ϡ�");
		textEmail = new JTextField(10);
		textEmail.setToolTipText("������������E-mail��ַ��");
		
		b1 = new JButton("��   ��");
		b1.setBackground(new Color(0,155,0));
		b1.addActionListener(this);
		b2 = new JButton("��   ��");
		b2.setBackground(new Color(0,155,0));
		b2.addActionListener(this);
		b3 = new JButton("��   ��");
		b3.setBackground(new Color(0,155,0));
		b3.addActionListener(this);
		
		Icon image = new ImageIcon("image/ff.jpg");
		imagePos = new JLabel(image);
		
		//�ֹ����ø��������λ�úʹ�С
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
		//�����������
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
	//***************************************�������ఴť�Ķ���*********************************************
	public void actionPerformed(ActionEvent evt){          
		
		Object obj = evt.getSource();
		
		if(obj == b1){                       //ע��
     	    uobj = new RegisterMessage();       //�����û�����ϸ����
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
		
			//�жϸ��������Ƿ���Ϲ���
			if(uobj.RegisterName.length() == 0){
				
				JOptionPane jopNamePass = new JOptionPane();
				jopNamePass.showMessageDialog(null,"�������û���!","��ʾ",JOptionPane.CANCEL_OPTION);
				return;
			}
			
			if((uobj.RegisterName.length()<2) || (uobj.RegisterName.length()>10)){
			
				JOptionPane jopNamePass = new JOptionPane();
				jopNamePass.showMessageDialog(null,"�û�����������Ϊ2��15�ֽ�!","��ʾ",JOptionPane.CANCEL_OPTION);
				textName.setText("");
				return;
			}
			
	        if(uobj.RegisterPassword.length() == 0){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"����������!","��ʾ",JOptionPane.CANCEL_OPTION);
				return;
			}
			
			if((uobj.RegisterPassword.length() < 4) || (uobj.RegisterPassword.length() > 12)){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"���벻������6���ַ�����10���ַ�!����������!","��ʾ",JOptionPane.CANCEL_OPTION);
				textPassword1.setText("");
				return;
			}
			
			if(userPassword2.length() == 0){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"���ٴ���������!","��ʾ",JOptionPane.CANCEL_OPTION);
				return;
			}
			
			if(!uobj.RegisterPassword.equals(userPassword2)){
			
				JOptionPane jopName = new JOptionPane();
				jopName.showMessageDialog(null,"������������ȷ�����벻һ��!","��ʾ",JOptionPane.CANCEL_OPTION);
				textPassword2.setText("");
				return;
			}
			
			if(userEmail.length() == 0){
			
				JOptionPane jopPassword = new JOptionPane();
				jopPassword.showMessageDialog(null,"�����������ַ!","��ʾ",JOptionPane.CANCEL_OPTION);
				return;
			}
			
			boolean flay=false;         
			for( int i= 1;i < userEmail.length()-3;i++){   //�ж�Email�����Ƿ���ȷ
			
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
				jopPassword.showMessageDialog(null,"��������ȷ��Ч�������ַ��","��ʾ",JOptionPane.CANCEL_OPTION);
				textEmail.setText("");
				return;
			}
			
			//����������Ӳ��ӷ���Ϣ						
			try{
		    	
		    	Socket toServer;
		    	toServer = new Socket("127.0.0.1",1001);     //���ӵ�������
		    	
		    	//д�ͻ���ϸ���ϵ�������socket
		    	ObjectOutputStream streamToServer=new ObjectOutputStream(toServer.getOutputStream());
			    streamToServer.writeObject((RegisterMessage)uobj);
				//�����Է�����socket�ĵ�½״̬
             	ObjectInputStream streamFromServer= new ObjectInputStream(toServer.getInputStream());
             	ValidateMessage msg =(ValidateMessage)streamFromServer.readObject();
				String str = msg.validateMessage;
				if(str.toString().equals("ok")){        //ע��ɹ�
					
					JOptionPane jopPassword = new JOptionPane();
					jopPassword.showMessageDialog(null,"ע��ɹ���");
					this.dispose();
					
					//��ȡ��½��Ϣ
					clientLogin login=new clientLogin();
					login.txtUserName.setText(uobj.RegisterName);
					login.txtUserPwd.setText(uobj.RegisterPassword);
				}
				else if(str.toString().equals("no")){   //ע��ʧ��
			
					JOptionPane jopPassword = new JOptionPane();
					jopPassword.showMessageDialog(null,"���û���������ʹ�ã�","��ʾ",JOptionPane.CANCEL_OPTION);
					textName.setText("");
					return;
				}
				streamToServer.close();
            	streamFromServer.close();
			}			
			catch(Exception e){
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null,"�������ӷ�������","��ʾ",JOptionPane.CANCEL_OPTION);
			}	
		}
		if(obj == b2){             //���		
			
			textName.setText("");
			textPassword1.setText("");
			textPassword2.setText("");
			textEmail.setText("");
		}	
		if(obj==b3){               //����
			
			new clientLogin();		
			this.setVisible(false);
		}
	}
}


