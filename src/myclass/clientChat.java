package myclass;

import myclass.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

//*******************************该类实现用户聊天****************************************
class clientChat extends JFrame {
	JPanel panelObj;
	JLabel status;
	JLabel status1;
	java.awt.List listUser;// 用户列表
	TextArea textMain;// 显示消息窗口不可写
	JTextField textTalk;// 写消息窗口
	JButton sendMessage;// 发送按钮
	JButton logout;// 注销按钮
	JButton update;// 更新信息按钮
	JButton bClear;// 清屏按钮
	JButton bExit;// 退出按钮
	boolean flag;
	String saveAdvice = "";
	String userName;
	Listen listen;

	// **********************************构造函数-窗口初始化*****************************************
	public clientChat(String name) {
		userName = name;
		setTitle("Welcome Enter Chat System");
		this.setSize(800, 570);
		Dimension us = this.getSize();
		// 获取屏幕的尺寸
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2;
		int newY = (them.height - us.height) / 2;
		this.setLocation(newX, newY);
		panelObj = new JPanel();
		getContentPane().add(panelObj);
		panelObj.setBackground(new Color(150, 205, 155));
		panelObj.setLayout(null); // 设置框架窗口的布局管理器为null布局管理器
		Font f = new Font("宋体", Font.PLAIN, 12);
		// 初始化各个组件并赋值
		status = new JLabel("当前登录的用户");
		status.setForeground(Color.blue);
		status1 = new JLabel("在线用户名 :" + userName);
		status1.setForeground(Color.blue);

		// 用户列表
		listUser = new java.awt.List();
		listUser.setBackground(new Color(225, 225, 225));
		// 聊天文本域
		String message2 = "********************************欢迎进入聊天室***************************************\n\n";
		textMain = new TextArea(message2, 110, 300, TextArea.SCROLLBARS_VERTICAL_ONLY);
		textMain.setEditable(false); // 聊天文本域不可写
		textMain.setBackground(new Color(225, 225, 225));

		// 输入消息框
		textTalk = new JTextField(100);
		textTalk.setBackground(new Color(255, 255, 255));
		textTalk.setToolTipText("请输入你聊天的内容");

		// 用户名选项
		String userlist[] = { "所有人" };

		// 设置按钮
		sendMessage = new JButton("发   送");
		sendMessage.setFont(f);
		sendMessage.addMouseListener(new MessageSent());
		sendMessage.setToolTipText("发送你聊天的内容");

		logout = new JButton("用 户 注 销");
		logout.setFont(f);
		logout.addActionListener(new removeUser());
		logout.setToolTipText("从系统中删除你的资料");

		update = new JButton("更 新 资 料");
		update.setFont(f);
		update.setToolTipText("更新注册资料");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new UpdateDetail(userName);
			}
		});
		bClear = new JButton("清      屏");
		bClear.setFont(f);
		bClear.addActionListener(new ListClear());
		bClear.setToolTipText("清空聊天文本区");

		bExit = new JButton("退      出");
		bExit.setFont(f);
		bExit.addActionListener(new ExitFrame());
		bExit.setToolTipText("退出聊天系统");

		sendMessage.setBackground(new Color(0, 155, 0));
		logout.setBackground(new Color(0, 155, 0));
		update.setBackground(new Color(0, 155, 0));

		bClear.setBackground(new Color(0, 155, 0));
		bExit.setBackground(new Color(0, 155, 0));

		// 手工设置各个组件的位置和大小
		listUser.setBounds(new Rectangle(30, 70, 180, 320));
		update.setBounds(new Rectangle(30, 465, 180, 22));
		logout.setBounds(new Rectangle(30, 497, 180, 22));
		textMain.setBounds(new Rectangle(220, 70, 550, 320));
		textTalk.setBounds(new Rectangle(440, 395, 330, 22));
		sendMessage.setBounds(new Rectangle(670, 427, 100, 22));
		bClear.setBounds(new Rectangle(590, 465, 180, 22));
		bExit.setBounds(new Rectangle(590, 497, 180, 22));
		status.setBounds(new Rectangle(640, 525, 150, 22));
		status1.setBounds(new Rectangle(30, 525, 200, 22));

		// 将各个组件添加到面板上
		panelObj.add(listUser);
		panelObj.add(textMain);
		panelObj.add(update);
		panelObj.add(logout);
		panelObj.add(textTalk);
		panelObj.add(sendMessage);
		panelObj.add(bClear);
		panelObj.add(bExit);
		panelObj.add(status);
		panelObj.add(status1);

		setResizable(false);
		setVisible(true);

		flag = true;
		listen = new Listen(userName, this);
	}

	// *********************************各种事件处理**************************************
	// *********************************发送聊天信息*********************************
	class MessageSent implements MouseListener {
		Object msgObj;
		ChatMessage chatMessage = new ChatMessage();

		public void mousePressed(MouseEvent e) {
			Object obj = e.getSource();
			if (obj == (JButton) sendMessage) {
				try {
					Socket toServer = new Socket("127.0.0.1", 1001);
					ObjectOutputStream streamToServer = new ObjectOutputStream(toServer.getOutputStream());
					if (getSendMessage())
						streamToServer.writeObject(msgObj);
				} catch (IOException ee) {
				} catch (Exception ie) {
					System.out.println(ie);
				}
			}
		}

		public void mouseReleased(MouseEvent e) {
			Object obj = e.getSource();
			if (obj == (JButton) sendMessage) {
				textTalk.setText("");
			}
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
		}

		// ********************************* 获取输入框内的信息*********************************
		public boolean getSendMessage() {
			String message = new String(textTalk.getText()); // 获取聊天信息
			if (message.length() == 0) // 判断输入是否为空
			{
				JOptionPane jopNamePass = new JOptionPane();
				jopNamePass.showMessageDialog(null, "无法发送空信息!");
				return false;
			} else {
				GregorianCalendar calendar = new GregorianCalendar();// 提供标准时间
				String time = "【" + calendar.get(calendar.HOUR) + ":" + calendar.get(calendar.MINUTE) + ":"
						+ calendar.get(calendar.SECOND) + "】";
				chatMessage.chattime = time;
				chatMessage.chatUser = userName;
				chatMessage.chatToUser = "所有人";
				chatMessage.chatMessage = message;
				chatMessage.chatFace = "";
				msgObj = (ChatMessage) chatMessage;
				return true;
			}
		}
	}

	// ***********************清空聊天内容***********************
	class ListClear implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (obj == (JButton) bClear) {
				textMain.setText(
						"********************************欢迎进入聊天室***************************************\\n\\n");
			}
		}
	}

	// ***********************离开聊天室***********************
	class ExitFrame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String string1 = "quit";
			String string2 = "确定退出!";
			userExit(string1, string2);
		}
	}

	// ***********************注销用户***********************
	class removeUser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String msg = "logout";
			String string = "确定注销!";
			userExit(msg, string);
		}
	}

	// ***********************处理窗口事件***********************
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			String string1 = "quit";
			String string2 = "确定退出!";
			userExit(string1, string2);
		}
	}

	// ***********************退出处理***********************
	public void userExit(String str1, String str2) {
		ValidateMessage exObj = new ValidateMessage();
		exObj.validateName = userName;
		exObj.validateMessage = str1;

		try {
			Socket toServer = new Socket("127.0.0.1", 1001);
			ObjectOutputStream streamToServer = new ObjectOutputStream(toServer.getOutputStream());
			streamToServer.writeObject((ValidateMessage) exObj);
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, str2);
			streamToServer.close();
			toServer.close();
			System.exit(1);
		} catch (IOException ee) {
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, "不能向服务器发送信息!");
		} catch (Exception ee) {
		}
	}
}
