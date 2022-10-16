package Part;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Main {
	public static void main(String[] args){
		JFrame jFrame = new JFrame("经典软件体系结构教学软件");
		jFrame.setSize(2000, 1000);
		jFrame.setLayout(null);
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		JTabbedPane jTabbedPane= new JTabbedPane();
		//分别设置四种panel的样式,设置数字代表第几个
		jTabbedPane.add("主程序-子程序",createPanel(1));
		jTabbedPane.add("面向对象",createPanel(2));
		jTabbedPane.add("事件系统",createPanel(3));
		jTabbedPane.add("管道-过滤",createPanel(4));
		jTabbedPane.setSelectedIndex(0);

		jFrame.setContentPane(jTabbedPane);
		jFrame.setVisible(true);
	}
	private static JComponent createPanel(int type) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		String contentDesc = new String();
		String contentImg = new String();
		String contentCode = new String();

		/**
		 * 调用part方法获取想过结果以及描述等
		 */
		Part part = new Part(type).invoke();
		if (part.is()) return null;
		//描述
		contentDesc = part.getContent_desc();
		//原例图
		contentImg = part.getContent_imgurl();
		//结果
		contentCode = part.getContent_code();
		/**
		 * juc页面布局以及显示那几个模块
		 */
		//分为5块，文字说明、原理图、代码、运行按钮、展示区域
		/**
		 * 文字说明
		 */
		showText(panel, contentDesc);

		/**
		 * 原理图
		 */
		showPrinciple(panel, contentImg);

		/**
		 * 结果展示
		 */
		ShowResult showResult = new ShowResult(panel).invoke();
		JTextArea result = showResult.getResult();
		JScrollPane scroll_result = showResult.getScroll_result();
		/**
		 * 核心代码
		 */
		showCode(panel, contentCode, scroll_result);
		/**
		 * 运行按钮
		 */
		JButton button = showRun(panel);
		//分情况加监听器
		if (monitor(type, result, button)) return null;
		return panel;
	}

	private static boolean monitor(int type, JTextArea result, JButton button) {
		if(type == 1) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						//运行
						result.setText("主程序-子程序演示运行结果：");//表示清空
						clearFile();//清空文件内容
						onePart.demo1.main(null);
	    				//读取文件，显示结果
						String result_content = getFileContent();
						result.append(result_content);
					}
					catch (Exception e1) {
			            e1.printStackTrace();
			        }
				}
			});
		}
		else if(type == 2) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						//运行
						result.setText("面向对象演示运行结果：");//表示清空
						clearFile();//清空文件内容
						secondPart.Main.main(null);
	    				//读取文件，显示结果
						String result_content = getFileContent();
						result.append(result_content);
					}
					catch (Exception e1) {
			            e1.printStackTrace();
			        }
				}
			});
		}
		else if(type == 3) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						//运行
						result.setText("事件系统-观察者模式演示运行结果：");//表示清空
						clearFile();//清空文件内容
						thirdPart.Main.main(null);
	    				//读取文件，显示结果
						String result_content = getFileContent();
						result.append(result_content);
					}
					catch (Exception e1) {
			            e1.printStackTrace();
			        }
				}
			});
		}
		else if(type == 4) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						//运行
						result.setText("管道-过滤演示运行结果：");//表示清空
						clearFile();//清空文件内容
						fourPart.Main.main(null);
	    				//读取文件，显示结果
						String result_content = getFileContent();
						result.append(result_content);
					}
					catch (Exception e1) {
			            e1.printStackTrace();
			        }
				}
			});
		}
		else {
			return true;
		}
		return false;
	}

	private static JButton showRun(JPanel panel) {
		JButton button = new JButton("run");
		GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		button.setFont(new Font(null, Font.BOLD, 30));
		button.setForeground(Color.GRAY);
		button.setBackground(Color.WHITE);
		gridBagConstraints_4.gridy = 0;//行
		gridBagConstraints_4.gridx = 1;//列
		gridBagConstraints_4.weightx = 0.1;
		gridBagConstraints_4.weighty = 0.5;
		gridBagConstraints_4.ipadx = 100;
		gridBagConstraints_4.ipady = 100;
		panel.add(button,gridBagConstraints_4);
		return button;
	}

	private static void showCode(JPanel panel, String contentCode, JScrollPane scroll_result) {
		JTextArea code = new JTextArea(contentCode);
		JScrollPane scroll_code = new JScrollPane(code);
		scroll_result.setFont(new Font(null, Font.PLAIN, 10));
		GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.gridy = 1;//行
		gridBagConstraints_3.gridx = 0;//列
		gridBagConstraints_3.weightx = 0.9;
		gridBagConstraints_3.weighty =0.7;
		gridBagConstraints_3.fill = GridBagConstraints.BOTH;
		panel.add(scroll_code,gridBagConstraints_3);
	}

	private static void showPrinciple(JPanel panel, String contentImg) {
		ImageIcon img = new ImageIcon(contentImg);
		//对每张图片改变一定的比例
		img.setImage(img.getImage().getScaledInstance(600, 340, 0));
		JLabel label = new JLabel(img);
		GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.gridy = 2;//行
		gridBagConstraints_2.gridx = 0;//列

		gridBagConstraints_2.weightx = 0.9;
		gridBagConstraints_2.weighty = 0.7;
		gridBagConstraints_2.fill = GridBagConstraints.HORIZONTAL;
		panel.add(label,gridBagConstraints_2);
	}

	private static void showText(JPanel panel, String contentDesc) {
		JTextArea desc = new JTextArea(contentDesc);
		JScrollPane scroll_desc = new JScrollPane(desc);
		scroll_desc.setFont(new Font(null, Font.PLAIN, 50));
		GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.gridy = 1;//行他
		gridBagConstraints_1.gridx = 1;//列
		gridBagConstraints_1.weightx = 1.2;
		gridBagConstraints_1.weighty = 2.3;
		gridBagConstraints_1.fill = GridBagConstraints.BOTH;
		panel.add(scroll_desc,gridBagConstraints_1);
	}

	public static String getFileContent(){
		File file = new File("D:\\softAct\\output.txt");
	    StringBuilder result = new StringBuilder();
	    try{
	      BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
	      String res = null;
	      while((res = bufferedreader.readLine())!=null){
	        result.append(System.lineSeparator()+res);
	      }
	      bufferedreader.close();  
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	    return result.toString();
	  }
	public static void clearFile(){
		File file = new File("D:\\softAct\\output.txt");
		FileWriter filewriter;
		try {
			filewriter = new FileWriter (file);
			filewriter.write("");
			filewriter.flush();
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static class Part {
		private boolean myResult;
		private int type;
		private String content_desc;
		private String content_imgurl;
		private String content_code;

		public Part(int type) {
			this.type = type;
		}

		boolean is() {
			return myResult;
		}

		public String getContent_desc() {
			return content_desc;
		}

		public String getContent_imgurl() {
			return content_imgurl;
		}

		public String getContent_code() {
			return content_code;
		}

		public Part invoke() {
			if(type == 1) {
				content_desc = "主程序/子程序风格将系统组织成层次结构，\n包括一个主程序和一系列子程序，\n"
						+ "主要用于能够将系统功能依层次分解为多个顺序执行步骤的系统。";
				content_imgurl = "D:\\softAct\\pic1.png";
				content_code = "核心代码:\r\n"
						+ "public static void main(String[] args) {\r\n"
						+ "        demo1 kwic = new demo1();\r\n"
						+ "        kwic.input(\"D:\\softAct\\input.txt\");\r\n"
						+ "        kwic.shift();\r\n"
						+ "        kwic.alphabetizer();\r\n"
						+ "        kwic.output(\"D:\\softAct\\output.txt\");\r\n"
						+ "    }";
			}
			else if(type == 2) {
				content_desc = "面向对象式风格将系统组织为多个独立的对象，\n每个对象封装其内部的数据，\n并基于数据对外提供服务，\n"
						+ "适用于那些能够基于数据信息分解和组织的软件系统。";
				content_imgurl = "D:\\softAct\\pic2.png";
				content_code = "核心代码:\r\n"
						+ "public static void main(String[] args) {\r\n"
						+ "        Input input = new Input();\r\n"
						+ "        input.input(\"D:\\softAct\\input.txt\");\r\n"
						+ "        Shift shift = new Shift(input.getLineTxt());\r\n"
						+ "        shift.shift();\r\n"
						+ "        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());\r\n"
						+ "        alphabetizer.sort();\r\n"
						+ "        Output output = new Output(alphabetizer.getKwicList());\r\n"
						+ "        output.output(\"D:\\softAct\\output.txt\");\r\n"
						+ "    }";
			}
			else if(type == 3) {
				content_desc = "观察者模式定义了一种一对多的依赖关系，\n让多个观察者对象同时监听某一个主题对象。\n"
						+ "这个主题对象在状态变化时，\n会通知所有的观察者对象，\n使他们能够自动更新自己。\n它可以实现表示层和数据逻辑层的分离。";
				content_imgurl = "D:\\softAct\\pic3.png";
				content_code = "核心代码:\r\n"
						+ "public static void main(String[] args) {\r\n"
						+ "        //创建主题\r\n"
						+ "        KWICSubject kwicSubject = new KWICSubject();\r\n"
						+ "        //创建观察者\r\n"
						+ "        Input input = new Input(\"D:\\softAct\\input.txt\");\r\n"
						+ "        Shift shift = new Shift(input.getLineTxt());\r\n"
						+ "        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());\r\n"
						+ "        Output output = new Output(alphabetizer.getKwicList(), \"D:\\softAct\\output.txt\");\r\n"
						+ "\r\n"
						+ "        // 将观察者加入主题\r\n"
						+ "        kwicSubject.addObserver(input);\r\n"
						+ "        kwicSubject.addObserver(shift);\r\n"
						+ "        kwicSubject.addObserver(alphabetizer);\r\n"
						+ "        kwicSubject.addObserver(output);\r\n"
						+ "        // 逐步调用各个观察者\r\n"
						+ "        kwicSubject.startKWIC();\r\n"
						+ "    }";
			}
			else if(type == 4) {
				content_desc = "管道-过滤器模式结构就像是一条产品加工流水线，\n原材料在流水线上经过一个个工人的加工，\n最终生产出产品。\n"
						+ "适用于很容易地被分解成一组离散的、独立的步骤的软件。";
				content_imgurl = "D:\\softAct\\pic4.png";
				content_code = "核心代码:\r\n"
						+ "public static void main(String[] args) throws IOException {\r\n"
						+ "        File inFile = new File(\"D:\\softAct\\input.txt\");\r\n"
						+ "        File outFile = new File(\"D:\\softAct\\output.txt\");\r\n"
						+ "        Pipe pipe1 = new Pipe();\r\n"
						+ "        Pipe pipe2 = new Pipe();\r\n"
						+ "        Pipe pipe3 = new Pipe();\r\n"
						+ "        Input input = new Input(inFile, pipe1);\r\n"
						+ "        Shift shift = new Shift(pipe1, pipe2);\r\n"
						+ "        Alphabetizer alphabetizer  = new Alphabetizer(pipe2, pipe3);\r\n"
						+ "        Output output = new Output(outFile,pipe3);\r\n"
						+ "        input.transform();\r\n"
						+ "        shift.transform();\r\n"
						+ "        alphabetizer.transform();\r\n"
						+ "        output.transform();\r\n"
						+ "    }";
			}
			else {
				myResult = true;
				return this;
			}
			myResult = false;
			return this;
		}
	}

	private static class ShowResult {
		private JPanel panel;
		private JTextArea result;
		private JScrollPane scroll_result;

		public ShowResult(JPanel panel) {
			this.panel = panel;
		}

		public JTextArea getResult() {
			return result;
		}

		public JScrollPane getScroll_result() {
			return scroll_result;
		}

		public ShowResult invoke() {
			result = new JTextArea("运行结果");
			scroll_result = new JScrollPane(result);
			scroll_result.setFont(new Font(null, Font.PLAIN, 20));
			GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
			gridBagConstraints_5.gridy = 0;//行
			gridBagConstraints_5.gridx = 0;//列
//			gridBagConstraints_5.gridheight = 1;
			gridBagConstraints_5.weightx = 0.9;
			gridBagConstraints_5.weighty = 2;
			gridBagConstraints_5.fill = GridBagConstraints.BOTH;
			panel.add(scroll_result,gridBagConstraints_5);
			return this;
		}
	}
}