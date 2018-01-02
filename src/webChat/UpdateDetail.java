package webChat;
import webChat.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
//**********************************����ʵ�ָ����û�����*********************************
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
	//*******************************��������û����Ͻ���********************************	       
    public UpdateDetail(String uName){
    	name = uName;
        myfont=new Font("����",Font.PLAIN,13);
        this.setTitle("�����û�����");
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(150,205,155));
         
        Icon image = new ImageIcon("image/bb.gif");
        myImage = new JLabel(image);     
        newPass_Label=new JLabel("������:");
        newPass_Label.setFont(myfont);      
        Regain_Label=new JLabel("��֤����:");
        Regain_Label.setFont(myfont);      
        Email_label=new JLabel("Email:");
        Email_label.setFont(myfont);     
        newPass=new JPasswordField(6);
        Regain=new JPasswordField(6);
        email=new JTextField(15);     
     
        close=new JButton("�ر�"); //�ر�
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
		submit=new JButton("����");
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
        panel.add(newPass);              ///������            
        panel.add(Regain_Label);              ///��֤����            
        panel.add(Regain);              ///��֤����            
        panel.add(Email_label);              ///Email          
        panel.add(email);              ///Email 
        panel.add(close);
        panel.add(submit);
        
        panel.add(myImage);
        
        setSize(380,230);
        Dimension us = this.getSize();
   		//��ȡ��Ļ�ĳߴ�
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2 ;
		int newY = (them.height - us.height) / 2 ;		
		this.setLocation(newX,newY);
        setVisible(true);
	}
    //******************************ʵ�ָ��ְ�ť�¼�*************************************   
	class UpdateClient implements ActionListener{
       		 
    	public void actionPerformed(ActionEvent e){
       		 	
       		Object obj = e.getSource();
       		 	
       		if(obj==(JButton)submit){
       		 		
       			String password1=String.valueOf(newPass.getPassword());
            	String password2=String.valueOf(Regain.getPassword());	
            	String Email = email.getText();
			
	        	if(password1.length() == 0){
			
					JOptionPane jopName = new JOptionPane();
					jopName.showMessageDialog(null,"����������!","��ʾ",JOptionPane.CANCEL_OPTION);
					return;
				}
			
				if((password1.length() < 4) || (password1.length() > 12)){
			
					JOptionPane jopName = new JOptionPane();
					jopName.showMessageDialog(null,"���벻������6���ַ�����10���ַ�!����������!","��ʾ",JOptionPane.CANCEL_OPTION);
					newPass.setText("");
					return;
				}
			
				if(password2.length() == 0){
			
					JOptionPane jopName = new JOptionPane();
					jopName.showMessageDialog(null,"���ٴ���������!","��ʾ",JOptionPane.CANCEL_OPTION);
					return;
				}
			
				if(!password2.equals(password1)){
			
					JOptionPane jopName = new JOptionPane();
					jopName.showMessageDialog(null,"������������ȷ�����벻һ��!","��ʾ",JOptionPane.CANCEL_OPTION);
					Regain.setText("");
					return;
				}
            	updateUser();          //���ø����û����Ϸ���
            }
		}	
	}
    //**********************************���·���******************************************
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
			 	JOptionPane.showMessageDialog(null,"���ϸ�����Ч!!!");
			 	this.dispose();
			}
			else{
				JOptionPane.showMessageDialog(null,"���ϸ��²��ɹ�!");
				return;
			}
			streamToServer.close();
           	streamFromServer.close();
		}
		catch(Exception e){}
	} 
}