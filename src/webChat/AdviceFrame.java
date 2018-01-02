package webChat;
import webChat.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
//***********************************�������ʵ�����Եķ���*****************************
public class AdviceFrame extends JDialog{
 
       JPanel   panel1;
       TextArea   ta1;
       JTextField   tf1;
       JLabel   lb1;
       JButton   bt1,bt2;
       
       String bName;
       String b2Name;
       String name;
       String content;
       //***************************���캯��********************************************
       public AdviceFrame(String userName,String button,String button2,String msg)
       {
       		  name =userName;
              this.setTitle("���Բ�");
              bName = button;
              b2Name=button2;
              content=msg;
              lb1 = new JLabel("To");
              lb1.setPreferredSize(new Dimension(20,20));
              tf1 = new JTextField(10);
              tf1.setPreferredSize(new Dimension(80,20));
              
              ta1 = new TextArea(content,15,50,TextArea.SCROLLBARS_VERTICAL_ONLY);	   	          
              bt1=new JButton(bName);
              bt1.setPreferredSize(new Dimension(60,23));
              bt1.addActionListener(new UserAdvice());
              bt2=new JButton(b2Name);
              bt2.setPreferredSize(new Dimension(60,23));
              bt2.addActionListener(new UserAdvice());
                    
                       
              bt1.setBackground(new Color(0,155,0));
              bt2.setBackground(new Color(0,155,0));
              
              panel1=new JPanel();
              this.getContentPane().add(panel1);
   
              panel1.add(ta1);
              panel1.add(lb1);
              panel1.add(tf1);
              panel1.add(bt1);
              panel1.add(bt2);
              
              panel1.setBackground(new Color(150,205,155));
              
              setSize(380,320);
              Dimension us = this.getSize();
   			  //��ȡ��Ļ�ĳߴ�
			  Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		      int newX = (them.width - us.width) / 2 ;
		      int newY = (them.height - us.height) / 2 ;		
		      this.setLocation(newX,newY);
              show();
       }
       //*******************************�¼�����*****************************************	       
       class UserAdvice implements ActionListener{

       		public void actionPerformed(ActionEvent e){
       			
       			GregorianCalendar  calendar = new GregorianCalendar();
       			String strDate ="��"+(calendar.get(calendar.MONTH)+1)+"/"+calendar.get(calendar.DATE)+"/"
       			                +calendar.get(calendar.YEAR)+"��";
                                 
       			Object obj = e.getSource();
       			if(obj == (JButton)bt1){
       				
       				if(bName =="����"){
       					
       					ClientAdvice uObj=new ClientAdvice();    									       
						uObj.FromUser = name;
						uObj.advice = ta1.getText();
						uObj.ToUser = tf1.getText(); 
						uObj.date = strDate;
						if(uObj.advice.length()==0){
							JOptionPane.showMessageDialog(null,"�������ݲ���Ϊ��!!!");
							return;
						}
						if(uObj.ToUser.length()==0){
							JOptionPane.showMessageDialog(null,"���Ͷ�����Ϊ��!!!");
							return;
						}
						if(uObj.FromUser.equals(uObj.ToUser)){
							JOptionPane.showMessageDialog(null,"�������Լ�����!!!");
							return;
						}	
    	
    					try{
		   	 				Socket toServer;
		    				toServer = new Socket("127.0.0.1",1001);                                                
		    			
		    				ObjectOutputStream streamToServer = new ObjectOutputStream(toServer.getOutputStream());
							streamToServer.writeObject((ClientAdvice)uObj);   
							ObjectInputStream streamFromServer = new ObjectInputStream(toServer.getInputStream());
							ValidateMessage msg =new ValidateMessage();
							msg = (ValidateMessage)streamFromServer.readObject();
							if(msg.validateMessage.compareTo("ok")==0){
								
								JOptionPane jopNamePass = new JOptionPane();
								jopNamePass.showMessageDialog(null,"�ɹ�����!!!");
							}
							                       
							streamToServer.close();
							streamFromServer.close();
							
							ta1.setText("");
							tf1.setText("");
						}
		
						catch(Exception ee){}
       				}
       				
       				else{	
       					saveAdvice();
       			   }
       		   }
       		   else{
       		   	
       		   	   delAdvice();
       		   }
       	  }
      }
      //**************************************��������************************************ 
      public void saveAdvice(){
       		try{
				String AdviceContent =ta1.getText();
       					
				JFileChooser choo = new JFileChooser();
				choo.showSaveDialog(this);
				File saveFile = choo.getSelectedFile();
		
				//�����ļ�������			
				FileOutputStream fo=new FileOutputStream(saveFile+".txt");
				fo.write(AdviceContent.getBytes());
				fo.close();	
				
				ta1.setText("");
				tf1.setText("");
				b2Name="�˳�";
				bt2.setText(b2Name);
				bName="����";
				bt1.setText(bName);
			}
			catch(IOException e){}		
      }
      //*******************************ɾ������****************************************
      public void delAdvice(){
      	
      	if(bName =="����"){
      		this.setVisible(false);
      	}
      	else{
			ta1.setText("");
			tf1.setText("");
			b2Name="�˳�";
			bt2.setText(b2Name);
			bName="����";
			bt1.setText(bName);
      	}
      }
}