package myclass;

import myclass.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;

//************************************该类实现用户登陆***********************************
public class clientLogin extends JFrame implements ActionListener {

	// 申明变量
	JLabel labelUserName;
	JLabel labelUserPwd;
	JPasswordField txtUserPwd;
	JTextField txtUserName;
	JButton buttonLogin;
	JButton buttonCancel;
	JButton buttonRegister;
	JLabel label;
	JLabel imagePos;

	// ************************************设置登陆窗口方法***********************************
	public clientLogin() {

		this.setTitle("欢迎使用聊天室");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font myfont = new Font("宋体", Font.PLAIN, 12);

		// 设置标签
		labelUserName = new JLabel("用户名：");
		labelUserName.setFont(myfont);
		labelUserPwd = new JLabel("口  令：");
		labelUserPwd.setFont(myfont);

		Icon image = new ImageIcon("image/webchat.jpg");
		imagePos = new JLabel(image);

		// 设置按钮组件
		buttonLogin = new JButton("登录");
		buttonLogin.setFont(myfont);
		buttonLogin.setBackground(new Color(0, 155, 0));
		buttonLogin.setToolTipText("进入聊天界面  ");
		buttonLogin.addActionListener(this);

		buttonRegister = new JButton("注册");
		buttonRegister.setFont(myfont);
		buttonRegister.setBackground(new Color(0, 155, 0));
		buttonRegister.setToolTipText(" 进入注册界面 ");
		buttonRegister.addActionListener(this);

		buttonCancel = new JButton("取消");
		buttonCancel.setFont(myfont);
		buttonCancel.setBackground(new Color(0, 155, 0));
		buttonCancel.setToolTipText(" 清除所填内容 ");
		buttonCancel.addActionListener(this);

		// 设置文本框
		txtUserName = new JTextField();
		txtUserName.setToolTipText(" 输入您的姓名 ");

		txtUserPwd = new JPasswordField();
		txtUserPwd.setToolTipText(" 输入密码 ");

		// 设置框架窗口布局管理为空布局管理
		JPanel panelObj = new JPanel();
		getContentPane().add(panelObj);
		panelObj.setLayout(null);

		// 手工设置各控件的大小和位置
		labelUserName.setBounds(new Rectangle(40, 20, 60, 20));
		txtUserName.setBounds(new Rectangle(100, 20, 180, 20));
		labelUserPwd.setBounds(new Rectangle(40, 60, 60, 20));
		txtUserPwd.setBounds(new Rectangle(100, 60, 180, 20));

		buttonLogin.setBounds(new Rectangle(40, 100, 60, 20));
		buttonRegister.setBounds(new Rectangle(130, 100, 60, 20));
		buttonCancel.setBounds(new Rectangle(220, 100, 60, 20));

		imagePos.setBounds(new Rectangle(0, 0, 320, 160));

		// 把各组件加到框架窗口中
		panelObj.add(labelUserName);
		panelObj.add(labelUserPwd);
		panelObj.add(txtUserName);
		panelObj.add(txtUserPwd);
		panelObj.add(buttonLogin);
		panelObj.add(buttonRegister);
		panelObj.add(buttonCancel);
		panelObj.add(imagePos);

		setResizable(false);
		setSize(320, 160);
		Dimension us = this.getSize();
		// 获取屏幕的尺寸
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2;
		int newY = (them.height - us.height) / 2;
		this.setLocation(newX, newY);
		setVisible(true);
	}

	// ***********************************按钮事件****************************************
	public void actionPerformed(ActionEvent even) {

		JButton obj = (JButton) even.getSource();

		if (obj == buttonCancel) { // 取消
			txtUserName.setText(null);
			txtUserPwd.setText(null);
		} else if (obj == buttonLogin) { // 提交
			String n = txtUserName.getText();
			String p = String.valueOf(txtUserPwd.getPassword());
			if (n.length() == 0 && p.length() != 0) {
				JOptionPane.showMessageDialog(this, "您没有输入姓名", "提示!", JOptionPane.CANCEL_OPTION);
				return;
			} else if (n.length() != 0 && p.length() == 0) {
				JOptionPane.showMessageDialog(this, "您没有输入密码", "提示!", JOptionPane.CANCEL_OPTION);
				return;
			} else if (n.length() == 0 && p.length() == 0) {
				JOptionPane.showMessageDialog(this, "您没有输入姓名和密码", "提示!", JOptionPane.CANCEL_OPTION);
				return;
			}
			enterChatRoom(); // 调用用户登陆方法
		} else if (obj == buttonRegister) { // 注册
			setVisible(false);
			new clientRegister();
		}
	}

	// *********************************main()方法
	public static void main(String args[]) {
		new clientLogin();
	}

	// *********************************用户登陆******************************************
	public void enterChatRoom() {
		LoginMessage uObj = new LoginMessage(); // 接受用户登陆的详细资料
		uObj.loginName = new String(txtUserName.getText());
		uObj.loginPassword = new String(txtUserPwd.getPassword());
		try {
			Socket toServer;
			toServer = new Socket("127.0.0.1", 1001); // 连接到服务器
			String userName;
			ObjectOutputStream streamToServer = new ObjectOutputStream(toServer.getOutputStream());
			streamToServer.writeObject((LoginMessage) uObj); // 写客户详细资料到服务器socket
			ObjectInputStream streamFromServer = new ObjectInputStream(toServer.getInputStream());
			ValidateMessage msg = (ValidateMessage) streamFromServer.readObject();// 读来自服务器socket的登陆状态
			String str = msg.validateMessage;
			if (str.toString().equals("ok")) { // 获取服务器传来的消息并进行判断
				try {
					userName = txtUserName.getText();
					clientChat chatFrame = new clientChat(userName);
					this.dispose();
				} catch (Exception e) {
					System.out.println("没有启动线程" + e);
					toServer.close();
				}
			} else if (str.toString().equals("online")) {
				JOptionPane jopPassword = new JOptionPane();
				jopPassword.showMessageDialog(null, "用户已登陆!");
				return;
			} else {
				JOptionPane jopPassword = new JOptionPane();
				jopPassword.showMessageDialog(null, "用户名或密码错误！");
				return;
			}
			streamToServer.close();
			streamFromServer.close();
		} catch (ClassNotFoundException e) {
		} catch (InvalidClassException e) {
		} catch (NotSerializableException e) {
		} catch (IOException e) {
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, "不能连接服务器或你还没注册！！！");
		}
	}
}
