package webChat;
import webChat.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
//**********************************该类实现更新用户资料*********************************
public class UpdateDetail extends JDialog{
  
  	JLabel myImage;
	JButton  submit;
    JButton  close;
    JPasswordField   newPass;
    JPasswordField   Regain;
    JTextField   email;
    JLabel       Regain_Label;
    JLabel       Email_label;
    JLabel       newPass_Label;
 	JPanel panel;
    Font myfont;
    String name;
	//*******************************构造更新用户资料界面********************************	       
    public UpdateDetail(String uName){
    	name = uName;
        myfont=new Font("宋体",Font.PLAIN,13);
        this.setTitle("更新用户资料");
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(150,205,155));
         
        Icon image = new ImageIcon("image/bb.gif");
        myImage = new JLabel(image);     
        newPass_Label=new JLabel("新密码:");
        newPass_Label.setFont(myfont);      
        Regain_Label=new JLabel("验证密码:");
        Regain_Label.setFont(myfont);      
        Email_label=new JLabel("Email:");
        Email_label.setFont(myfont);     
        newPass=new JPasswordField(6);
        Regain=new JPasswordField(6);
        email=new JTextField(15);     
     
        close=new JButton("关闭"); //关闭
        close.setPreferredSize(new Dimension(60,20));
        close.setFont(myfont);
        close.setBackground(new Color(0,155,0));      
        close.addActionListener(new
                    ActionListener()
                    {
                    	public void actionPerformed(ActionEvent evt)
                        {
                         	setVisible(false);
                        }
                     });
		submit=new JButton("更新");
        submit.setPreferredSize(new Dimension(60,20));
        submit.setFont(myfont);
        submit.setBackground(new Color(0,155,0));
        submit.addActionListener(new UpdateClient());
            
        newPass_Label.setBounds(new Rectangle(15,15,60,20));
        newPass.setBounds(new Rectangle(100,15,180,20));
        Regain_Label.setBounds(new Rectangle(15,60,60,20));
        Regain.setBounds(new Rectangle(100,60,180,20));
        Email_label.setBounds(new Rectangle(15,105,60,20));
        email.setBounds(new Rectangle(100,105,240,20));
     	submit.setBounds(new Rectangle(90,150,90,20));
     	close.setBounds(new Rectangle(200,150,90,20));
     	myImage.setBounds(new Rectangle(285,0,100,100));
             
        panel.add(newPass_Label);           
        panel.add(newPass);              ///新密码            
        panel.add(Regain_Label);              ///验证密码            
        panel.add(Regain);              ///验证密码            
        panel.add(Email_label);              ///Email          
        panel.add(email);              ///Email 
        panel.add(close);
        panel.add(submit);
        
        panel.add(myImage);
        
        setSize(380,230);
        Dimension us = this.getSize();
   		//获取屏幕的尺寸
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
        setVisible(true);
	}
    //******************************实现各种按钮事件*************************************   
	class UpdateClient implements ActionListener{
       		 
    	public void actionPerformed(ActionEvent e){
       		 	
       		Object obj = e.getSource();
       		 	
       		if(obj==(JButton)submit){
       		 		
       			String password1=String.valueOf(newPass.getPassword());
            	String password2=String.valueOf(Regain.getPassword());	
            	String Email = email.getText();
			
	        	if(password1.length() == 0){
			
					JOptionPane jopName = new JOptionPane();
					jopName.showMessageDialog(null,"请输入密码!","提示",JOptionPane.CANCEL_OPTION);
					return;
				}
			
				if((password1.length() < 4) || (password1.length() > 12)){
			
					JOptionPane jopName = new JOptionPane();
					jopName.showMessageDialog(null,"密码不得少于6个字符多于10个字符!请重新输入!","提示",JOptionPane.CANCEL_OPTION);
					newPass.setText("");
					return;
				}
			
				if(password2.length() == 0){
			
					JOptionPane jopName = new JOptionPane();
					jopName.showMessageDialog(null,"请再次输入密码!","提示",JOptionPane.CANCEL_OPTION);
					return;
				}
			
				if(!password2.equals(password1)){
			
					JOptionPane jopName = new JOptionPane();
					jopName.showMessageDialog(null,"您输入的密码和确认密码不一致!","提示",JOptionPane.CANCEL_OPTION);
					Regain.setText("");
					return;
				}
            	updateUser();          //调用更新用户资料方法
            }
		}	
	}
    //**********************************更新方法******************************************
    public  void updateUser(){
       	
        UpdateUser uObj=new UpdateUser();    									       
		uObj.userName = name;
		uObj.UpdatePass = String.valueOf(newPass.getPassword());
		uObj.RePass = String.valueOf(Regain.getPassword());
		uObj.UpdateEmail = email.getText();   	
    		
		try{
		   	Socket toServer;
		    toServer = new Socket("127.0.0.1",1001);                                                
		    
		    ObjectOutputStream streamToServer = new ObjectOutputStream(toServer.getOutputStream());
			streamToServer.writeObject((UpdateUser)uObj);                          
			ObjectInputStream streamFromServer = new ObjectInputStream(toServer.getInputStream());
			ValidateMessage msg = (ValidateMessage)streamFromServer.readObject();
		
			String str = msg.validateMessage;	                                     
			if(str.toString().equals("ok")){                                       
			 	JOptionPane.showMessageDialog(null,"资料更新有效!!!");
			 	this.dispose();
			}
			else{
				JOptionPane.showMessageDialog(null,"资料更新不成功!");
				return;
			}
			streamToServer.close();
           	streamFromServer.close();
		}
		catch(Exception e){}
	} 
}