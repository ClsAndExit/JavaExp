package myclass;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//***********************************该类用来设置字体************************************
public class FontChooser extends JDialog {
	
	//声明变量
	protected Font resultFont;
	protected String resultName;
	protected int resultSize;
	protected boolean isBold;
	protected boolean isItalic;
	protected String displayText = "NIIT World!";
	protected String fontList[];
	protected List fNameChoice;
	protected List fSizeChoice;
	Checkbox bold, italic;

	protected String fontSizes[] = {
		"8", "10", "11", "12", "14", "16", "18", "20", "24",
		"30", "36", "40", "48", "60", "72"
		};	
	protected static final int defaultSize = 4; /** 缺省的大小  4的point=14*/
	protected JLabel previewArea;
	//********************************构造字体设置对话框*********************************
	public FontChooser(Frame f) {
		super(f, "字体选择", true);
		Container cp = getContentPane();
		Panel top = new Panel();
		top.setLayout(new FlowLayout());
		fNameChoice = new List(8);
		top.add(fNameChoice);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		for (int i=0; i<fontList.length; i++)
			fNameChoice.add(fontList[i]);
		
		fNameChoice.select(0);
		fSizeChoice = new List(8);
		top.add(fSizeChoice);

		for (int i=0; i<fontSizes.length; i++){
			fSizeChoice.add(fontSizes[i]);
		}
		
		fSizeChoice.select(defaultSize);
		cp.add(BorderLayout.NORTH, top);
		
		Panel attrs = new Panel();
		top.add(attrs);
		attrs.setLayout(new GridLayout(0,1));
		attrs.add(bold  =new Checkbox("（粗体）Bold", false));
		attrs.add(italic=new Checkbox("（斜体）Italic", false));

		previewArea = new JLabel(displayText, JLabel.CENTER);
		previewArea.setSize(200, 50);
		cp.add(BorderLayout.CENTER, previewArea);
		
		Panel bot = new Panel();
		JButton okButton = new JButton("确定");
		bot.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previewFont();   //查看字体 
				dispose();
				setVisible(false);
			}
		});

		JButton pvButton = new JButton("预览");
		bot.add(pvButton);
		pvButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previewFont();  //查看字体
			}
		});

		JButton canButton = new JButton("取消");
		bot.add(canButton);
		canButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 设置所有的值为空
				resultFont = null;
				resultName = null;
				resultSize = 0;
				isBold = false;
				isItalic = false;

				dispose();
				setVisible(false);
			}
		});

		cp.add(BorderLayout.SOUTH, bot);
		previewFont(); // 确保看到更新的字体!
		pack();
		setLocation(200, 200);
	}
	//*******************************预览字体方法****************************************
	protected void previewFont() {		
		resultName = fNameChoice.getSelectedItem();
		String resultSizeName = fSizeChoice.getSelectedItem();
		int resultSize = Integer.parseInt(resultSizeName);
		isBold = bold.getState();
		isItalic = italic.getState();
		int attrs = Font.PLAIN;
		if (isBold) attrs = Font.BOLD;
		if (isItalic) attrs |= Font.ITALIC;
		resultFont = new Font(resultName, attrs, resultSize);
		previewArea.setFont(resultFont);
		pack();		// 确保对话框足够大.
	}
	//*********************************获取已设置字卫体*********************************
	public Font getSelectedFont() {
		return resultFont;
	}
}
