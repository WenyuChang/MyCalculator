import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
public class Calc extends JFrame implements ActionListener,ItemListener 
{ 
//以下变量用于该计算器的科学算式计算用
	String str="";//存储算式
	char[] c=str.toCharArray();//用于分割每个算式的每个字符
	char cur;
	int n=-1,TRn=-1,NDn=-1;//用于后面算式的入栈出栈
	double flag=0;//标志数字
	int flag1=0;//标志小数
	char[] OPTR=new char[9999];//存储数字
	double[] OPND=new double[9999];//存储运算符
	int ifDre=1;//标志角度制还是弧度制
	
//计算器其他一些用到的变量
	JTextField Answer;//显示数据的Test栏
	JPanel panel, panel1, panel2, panel3;//这个界面的用到的各个中间容器
	JTextField Memory;//用于计算器中的memory显示
	JLabel Space;//用于显示当时计算器模式状态
	JTextArea help;//帮助文本框
	int x=1,newt=1,action=0,Op=-1,preOp=-1;//以一些变量
	double answer=0,vard=0,memory=0;//用于存贮结果
	JRadioButton buD,buR;//角度，弧度单选键
	
//该计算器所有按键的定义
	JButton buttonBk, buttonCe, buttonC;
	JButton button[];
	JButton buttonMC, buttonMR, buttonMS, buttonMAdd;
	JButton buttonKleft,buttonKright,buttonpi,buttonsquare;
	JButton buttonsin,buttoncos,buttontg,buttonlog;
	JButton buttonDot, buttonAddAndSub, buttonAdd, buttonSub, buttonMul,buttonDiv, buttonMod;
	JButton buttonSqrt, buttonDao, buttonEqual;
//该计算器菜单
	JMenuBar mainMenu;//菜单栏
	JMenu fileMenu,editMenu, viewMenu, helpMenu;//各个菜单条
	JMenuItem closeItem,copyItem, pasteItem, cutItem, tItem, sItem, topHelp, aboutCal;//菜单项
	JScrollPane scrollHelp;
	
	
	public Calc()//主类的构造函数，该计算器运行开始点
	{
		//创建图形界面
		super("简单/科学计算器");
		panel = new JPanel();
		setLocationRelativeTo(null);
		getContentPane().add(panel);
		//以下是图形界面的界面设置
		Answer = new JTextField(15);//设置长度
		Answer.setText("0.");//设置初始值
		Answer.setHorizontalAlignment(JTextField.RIGHT);//设置显示位置
		Answer.setEditable(false);//设置可否更改
		Answer.setBackground(new Color(255, 255, 255));//设置颜色
		panel.setLayout(new BorderLayout());//容器布局设置
		//以下是菜单栏的设置
		mainMenu = new JMenuBar();
		fileMenu=new JMenu("文件(F)"); 
		editMenu = new JMenu("编辑(E)");
		viewMenu = new JMenu("查看(V)");
		helpMenu = new JMenu("帮助(H)");
		closeItem=new JMenuItem("   退出");
		fileMenu.add(closeItem);
		closeItem.addActionListener(this);
		copyItem = new JMenuItem("   复制(C) Ctrl+C");
		copyItem.addActionListener(this);
		pasteItem = new JMenuItem("   粘贴(V) Ctrl+V");
		pasteItem.addActionListener(this);
		cutItem = new JMenuItem("   剪切(X) Ctrl+X");
		cutItem.addActionListener(this);
		editMenu.add(copyItem);
		editMenu.addSeparator();
		editMenu.add(cutItem);
		editMenu.addSeparator();
		editMenu.add(pasteItem);
		tItem = new JMenuItem("   标准型(T)");
		tItem.addActionListener(this);
		sItem = new JMenuItem("   科学型(S)");
		sItem.addActionListener(this);
		viewMenu.add(tItem);
		viewMenu.addSeparator();
		viewMenu.add(sItem);
		topHelp = new JMenuItem("   帮助(H)");
		//帮助菜单的设置
		topHelp.addActionListener(this);
		help=new JTextArea(5, 20);
		scrollHelp=new JScrollPane(help);
		help.setEditable(false);
		String str="";
		str=str+"简单计算\n"+"1.  键入计算的第一个数字。\n"+"2.  单击“+”键入运算符。\n"
	    	+"3.  键入计算的下一个数字。\n"+"4.  单击 “=”。\n";
		str=str+"\n"+"科学计算\n"+"1.  键入一串计算式。\n"+"2.  单击 “=”。\n"+"例子：1.7×（5.3＋8.6）\n";
		help.append(str);//在原来的文本框中后加入需要的内容
		aboutCal = new JMenuItem("   关于计算器(A)");
		aboutCal.addActionListener(this);
		helpMenu.add(topHelp);
		helpMenu.addSeparator();
		helpMenu.add(aboutCal);
		mainMenu.add(fileMenu);
		mainMenu.add(editMenu);
		mainMenu.add(viewMenu);
		mainMenu.add(helpMenu);
		panel.add(mainMenu, BorderLayout.NORTH);//为了方便把菜单栏当作容器加入
		panel.add(Answer, BorderLayout.CENTER);
		
		
		//以下是对整个图形界面的布局设置
		panel1 = new JPanel();//布局1
		panel2 = new JPanel();//布局2
		panel3 = new JPanel();//布局3
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.add(panel1,BorderLayout.SOUTH);
		buttonBk = new JButton("Backspace");
		buttonBk.setForeground(new Color(255, 0, 0));
		buttonBk.addActionListener(this);
		buttonCe = new JButton("CE");
		buttonCe.setForeground(new Color(255, 0, 0));
		buttonCe.addActionListener(this);
		buttonC = new JButton("C");
		buttonC.setForeground(new Color(255, 0, 0));
		buttonC.addActionListener(this);
		
		Memory = new JTextField(6);
	    Memory.setEditable(false);
	    Memory.setHorizontalAlignment(JTextField.CENTER);
	    Memory.setBackground(new Color(217, 217, 217));
	    Space = new JLabel("模式：简单计算");
	    Space.setForeground(new Color(50,100,255));
	    panel2.add(Memory);
	    ButtonGroup group=new ButtonGroup();
	    buD=new JRadioButton("Degree       ",true);
	    buD.addItemListener(this);
	    buR=new JRadioButton("Radian       ");
	    buR.addItemListener(this);
	    group.add(buD);
	    group.add(buR);
	    panel2.add(buD);
		panel2.add(buR);
	    panel2.add(Space);
		panel2.add(buttonBk);
		panel2.add(buttonCe);
		panel2.add(buttonC);
		panel1.add(panel2, BorderLayout.NORTH);	
		panel1.add(panel3, BorderLayout.CENTER);
		button = new JButton[10];
		for (int i = 0; i < button.length; i++) //为了代码的简短使用循环创建数字的按键并为按键设置颜色
		{
			button[i] = new JButton(Integer.toString(i));
			button[i].setForeground(new Color(0, 0, 255));
		}
		//其他的按键的创建以及颜色的设置
		buttonMC = new JButton("MC");
		buttonMC.setForeground(new Color(255, 0, 0));
		buttonMR = new JButton("MR");
		buttonMR.setForeground(new Color(255, 0, 0));
		buttonMS = new JButton("MS");
		buttonMS.setForeground(new Color(255, 0, 0));
		buttonMAdd = new JButton("M+");
		buttonMAdd.setForeground(new Color(255, 0, 0));
		buttonDot = new JButton(".");
		buttonDot.setForeground(new Color(0, 0, 255));
		buttonAddAndSub = new JButton("+/-");
		buttonAddAndSub.setForeground(new Color(0, 0, 255));
		buttonAdd = new JButton("+");
		buttonAdd.setForeground(new Color(255, 0, 0));
		buttonSub = new JButton("-");
		buttonSub.setForeground(new Color(255, 0, 0));
		buttonMul = new JButton("*");
		buttonMul.setForeground(new Color(255, 0, 0));
		buttonDiv = new JButton("/");
		buttonDiv.setForeground(new Color(255, 0, 0));
		buttonMod = new JButton("%");
		buttonMod.setForeground(new Color(0, 0, 255));
		buttonSqrt = new JButton("sqrt");
		buttonSqrt.setForeground(new Color(0, 0, 255));
		buttonDao = new JButton("1/x");
		buttonDao.setForeground(new Color(0, 0, 255));
		buttonEqual = new JButton("=");
		buttonEqual.setForeground(new Color(255, 0, 0));
		buttonKleft=new JButton("(");
		buttonKright=new JButton(")");
		buttonpi=new JButton("pi");
		buttonsquare=new JButton("x^2");
		buttonsin=new JButton("Sin");
		buttoncos=new JButton("Cos");
		buttontg=new JButton("tan");
		buttonlog=new JButton("log");
		
		
		panel3.setLayout(new GridLayout(4,8));//容器3用网格型，以下是各个按键的加入
		panel3.add(buttonMC);
		buttonMC.addActionListener(this);
		panel3.add(buttonKleft);
		buttonKleft.addActionListener(this);
		panel3.add(buttonsin);
		buttonsin.addActionListener(this);
		panel3.add(new JLabel(""));
		panel3.add(button[7]);
		button[7].addActionListener(this);
		panel3.add(button[8]);
		button[8].addActionListener(this);
		panel3.add(button[9]);
		button[9].addActionListener(this);
		panel3.add(buttonDiv);
		buttonDiv.addActionListener(this);
		panel3.add(buttonSqrt);
		buttonSqrt.addActionListener(this);
		panel3.add(buttonMR);
		buttonMR.addActionListener(this);
		panel3.add(buttonKright);
		buttonKright.addActionListener(this);
		panel3.add(buttoncos);
		buttoncos.addActionListener(this);
		panel3.add(new JLabel(""));
		panel3.add(button[4]);
		button[4].addActionListener(this);
		panel3.add(button[5]);
		button[5].addActionListener(this);
		panel3.add(button[6]);
		button[6].addActionListener(this);
		panel3.add(buttonMul);
		buttonMul.addActionListener(this);
		panel3.add(buttonMod);
		buttonMod.addActionListener(this);
		panel3.add(buttonMS);
		buttonMS.addActionListener(this);
		panel3.add(buttonpi);
		buttonpi.addActionListener(this);
		panel3.add(buttontg);
		buttontg.addActionListener(this);
		panel3.add(new JLabel(""));
		panel3.add(button[1]);
		button[1].addActionListener(this);
		panel3.add(button[2]);
		button[2].addActionListener(this);
		panel3.add(button[3]);
		button[3].addActionListener(this);
		panel3.add(buttonSub);
		buttonSub.addActionListener(this);
		panel3.add(buttonDao);
		buttonDao.addActionListener(this);
		panel3.add(buttonMAdd);
		buttonMAdd.addActionListener(this);
		panel3.add(buttonsquare);
		buttonsquare.addActionListener(this);
		panel3.add(buttonlog);
		buttonlog.addActionListener(this);
		panel3.add(new JLabel(""));
		panel3.add(button[0]);
		button[0].addActionListener(this);
		panel3.add(buttonAddAndSub);
		buttonAddAndSub.addActionListener(this);
		panel3.add(buttonDot);
		buttonDot.addActionListener(this);
		panel3.add(buttonAdd);
		buttonAdd.addActionListener(this);
		panel3.add(buttonEqual);
		buttonEqual.addActionListener(this);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}
	
	//以下是用于科学算式计算是用到的几个方法In（）；Precede（）；Operate（）；Operation()
	boolean In(char c)//该方法用来测试接受的字符是否是数字
	{
		if((c>='0')&&(c<='9')||(c=='.'))//当是数字的操作
		{
			if(c=='.')//当时‘.’时则表示该数字是小数做以下造作
			{
				flag1=-1;//设置用来表示该数字是小数的权值
				flag=OPND[NDn];//数字出栈操作
				NDn--;
				return false;//返回
			}
			if(flag!=0)//如果连续接受两个数字字符表示该数大于10，做以下操作
			{
				if(flag1==0)//当是整数操作
				{
					double x=c;
					flag=OPND[NDn]*10+x-48;
					NDn--;
				}
				else//是小数是的操作
				{
					double x=c;
					flag=OPND[NDn]+(x-48)*java.lang.Math.pow(10,-1);
					NDn--;
				}
			}
			if(flag==0)//如果只接受了一个数字字符是的操作
			{
				double x=c;//x用于存储字符c的ASC码
				flag=x-48;//将字符类型的数字变为数字
			}
			return false;
		}
		else//如果不是数字的操作
		{
			flag=0;
			return true;
		}
	}
	
	
	char Precede(char c1,char c2)//分析两个运算符的优先级，并返回一个用于判断以下操作的符号
	{
		if((c1=='-')||(c1=='+'))
		{
			if((c2=='/')||(c2=='(')||(c2=='*')) return '<';
			else return '>';
		}
		if((c1=='*')||(c1=='/'))
		{
			if(c2=='(') return '<';
			else return '>';
		}
		if(c1=='(')
		{
			if(c2==')') return '=';
			else return '<';
		}
		if(c1==')')
		{
			return '>';
		}
		if(c1=='#')
		{
			if(c2=='#') return '=';
			else return '<';
		}
		if((c2>=48)&&(c2<=57))
			return 'E';
		return 'E';
	}
	
	double Operate(double a,char theta,double b)//做a theta b的操作
	{
		switch(theta)
		{
		case '+'://加法操作
			{
				double result=a+b;
				return result;
			}
		case '-'://减法操作
			{
				double result=a-b;
				return result;
			}
		case '*'://乘法操作
			{
				double result=a*b;
				return result;
			}
		case '/'://除法操作
			{
				double result=a/b;
				return result;
			}
		}
		double result=0;
		return result;
	}
	
	
	public String operation(String str)//科学算术运算的总方法，调用上述的一些方法完成操作
	{
		c=str.toCharArray();//把算式分解成每个字符存入char数组中
		OPTR[++TRn]='#';//第一个字符入栈
		cur=c[++n];//判断下个字符
		//以下是算术的所有操作
		while((cur!='#')||(OPTR[TRn]!='#'))
		{
			if(!In(cur))
			{
				OPND[++NDn]=flag;
				cur=c[++n];
			}
			else
			{
				if((cur=='#')&&(OPTR[TRn]=='#')) break;
				switch(Precede(OPTR[TRn],cur))
				{
				case '<':
					{
						OPTR[++TRn]=cur;//字符入栈
						cur=c[++n];//下个字符
						flag=0;//flag初始化
						break;
					}
				case '=':
					{
						TRn--;//出栈
						cur=c[++n];//下个字符
						flag=0;//flag初始化
						break;
					}
				case '>':
					{
						char theta;
						double a,b;
						flag=0;//flag初始化
						b=OPND[NDn--];//数字2出栈
						a=OPND[NDn--];//数字1出栈
						theta=OPTR[TRn--];//字符出栈
						OPND[++NDn]=Operate(a,theta,b);//运算
						break;
					}
				default: break;
				}
			}
		}
		str=Double.toString(OPND[NDn]);
		return str;//把答案返回
	}
	public void actionPerformed(ActionEvent e)//操作所有的事件
	{
		try
		{
			if(e.getSource()==closeItem)//按动退出菜单项
			{
				System.exit(0);
			}
			if(e.getSource()==copyItem)//按动复制菜单项
			{
				Answer.copy();
			}
			if(e.getSource()==pasteItem)//按动粘贴项
			{
				Answer.paste();
			}
			if(e.getSource()==cutItem)//按动剪切项
			{
				Answer.cut();
			}
			if(e.getSource()==tItem)//按动简单计算项
			{
				x=1;//标志简单计算
				Space.setText("模式：简单计算");
				Space.setForeground(new Color(50,100,255));
				memory=answer=vard=0;//结果变量初始化
			}
			if(e.getSource()==sItem)//按动科学计算
			{
				x=2;//标志科学计算
				Space.setText("模式：科学计算");
				Space.setForeground(new Color(100,100,100));
				memory=answer=vard=0;//结果变量初始化
				Answer.setText("");//文本栏初始化
			}
			if(e.getSource()==topHelp)//按动帮助菜单项
			{
				JOptionPane.showMessageDialog(panel,scrollHelp);//弹出帮助对话框
			}
			if(e.getSource()==aboutCal)//按动关于菜单项
			{
				JOptionPane.showMessageDialog(panel,"            计算器\n     开发者：常闻宇");//弹出关于对话框
			}
			
			for (int i=0;i<=9;i++)//当按动的是数字按键
		        if (e.getSource()==button[i])
		        {
		        	if(x==1)//如果是简单计算时做的事件处理
		        	{
		        		if(newt==1)//如果重新输入数字
		        		{
		        			Answer.setText(Integer.toString(i));
		        			newt=0;
		        		}
		        		else//连续输入数字时
		        		{
		        			Answer.setText(Answer.getText()+Integer.toString(i));
		        			newt=0;
		        		}
		        	}
		        	else//当时科学计算时
		        	{
		        		Answer.setText(Answer.getText()+Integer.toString(i));
		        	}
		        }
			if (e.getSource()==buttonDot)//按动小数点按键
			{
				if(x==1)//当时简单计算时
				{
			        boolean isDot=false;//表示能否再输入小数点
			        if (Answer.getText().length() == 0)//如果没有输入任何东西时
			        	isDot=true;
			        for (int i=0;i<Answer.getText().length();i++)//如果已有小数点存在
			        	if ('.'==Answer.getText().charAt(i)) 
			        	{
			        		isDot = true;
			        		break;
			        	}
			        if (isDot == false)//能输入
			          Answer.setText(Answer.getText() + ".");
				}
				else//科学计算时
					Answer.setText(Answer.getText() + ".");
		    }
			if (e.getSource()==buttonAdd||e.getSource()==buttonSub||e.getSource()==buttonMul||
					e.getSource()==buttonDiv)//以下是按动各个运算符时所要做的操作
			{
				if(x==1)//如果是简单计算时
				{
					newt=1;//初始化
					switch(preOp)//查看上个运算符是什么，用在连续运算 
					{
		            case 0://若果是加法运算
		              answer+=Double.parseDouble(Answer.getText());
		              break;
		            case 1://如果是减法运算
		              answer-=Double.parseDouble(Answer.getText());
		              break;
		            case 2://如果是成法运算
		              answer*=Double.parseDouble(Answer.getText());
		              break;
		            case 3://如果是除法运算
		              if(Double.parseDouble(Answer.getText())==0)//除数不能为零
		            	  Answer.setText("除数不能为零");
		              else
		                answer/=Double.parseDouble(Answer.getText());
		              break;
		            default://如果第一个输入时的操作哦
		              answer=Double.parseDouble(Answer.getText());
					}
			        if (e.getSource()==buttonAdd) //加法运算
			        {
			          Answer.setText(Double.toString(answer));
			          preOp = Op = 0;//标志前一个和正在操作的运算符标志
			        }
			        if (e.getSource()==buttonSub) 
			        {
			          Answer.setText(Double.toString(answer));//减法运算
			          preOp = Op = 1;//标志前一个和正在操作的运算符标志
			        }
			        if (e.getSource()==buttonMul) //乘法运算
			        {
			          Answer.setText(Double.toString(answer));
			          preOp = Op = 2;//标志前一个和正在操作的运算符标志
			        }
			        if (e.getSource()==buttonDiv) //除法运算
			        {
			          Answer.setText(Double.toString(answer));
			          preOp = Op = 3;//标志前一个和正在操作的运算符标志
			        }
				}
				else//用于科学算式
				{
					if (e.getSource()==buttonAdd) 
			          Answer.setText(Answer.getText()+"+");
			        if (e.getSource()==buttonSub) 
			        	Answer.setText(Answer.getText()+"-");
			        if (e.getSource()==buttonMul) 
			        	Answer.setText(Answer.getText()+"*");
			        if (e.getSource()==buttonDiv) 
			        	Answer.setText(Answer.getText()+"/");
				}
		      }
			if(e.getSource()==buttonEqual) //按动等于按键
			{
				if(x==2)//如果是科学算式计算
				{
					str=str+Answer.getText()+"#";//在算式后追加“＃”，为后面的运算方便
					Answer.setText(operation(str));//通过方法operation运算
				}
				else//简单运算
				{
					if(preOp==4)//如果先前的运算不是一般简单运算符
					{
						if(Op==0)//如果正在运算的运算符是加法
						{
							answer+=vard;//设置结果
				            Answer.setText(Double.toString(answer));//设置结果文本栏
						}
						if (Op == 1)//如果正在运算的运算符是减法
						{
				            answer-=vard;//设置结果
				            Answer.setText(Double.toString(answer));//设置结果文本栏
				        }
				        if(Op==2)//如果正在运算的运算符是乘法
				        {
				            answer*=vard;//设置结果
				            Answer.setText(Double.toString(answer));//设置结果文本栏
				        }
				        if(Op==3)//如果正在运算的运算符是乘法
				        {
				            if (Double.parseDouble(Answer.getText())==0)//除数不为零
				              Answer.setText("除数不能为零");
				            else 
				            {
				              answer/=vard;//设置结果
				              Answer.setText(Double.toString(answer));//设置结果文本栏
				            }
				        }
					}
					else//如果先前已经有运算，用于连续运算
					{
						vard=Double.parseDouble(Answer.getText());
						if(Op==0) 
						{
				            answer+=Double.parseDouble(Answer.getText());
				            Answer.setText(Double.toString(answer));
				        }
				        if(Op==1) 
				        {
				            answer-= Double.parseDouble(Answer.getText());
				            Answer.setText(Double.toString(answer));
				        }
				        if(Op==2) 
				        {
				            answer*= Double.parseDouble(Answer.getText());
				            Answer.setText(Double.toString(answer));
				        }
				        if (Op==3) 
				        {
				            if(Double.parseDouble(Answer.getText())==0)
				            	Answer.setText("除数不能为零");
				            else 
				            {
				              answer/= Double.parseDouble(Answer.getText());
				              Answer.setText(Double.toString(answer));
				            }
				         }
					}
				    preOp=4;//初始化
					newt=1;//初始化
				}
			}
			if(e.getSource()==buttonMod)//按动％按键所要的操作
			{
				Answer.setText(Double.toString(Double.parseDouble(Answer.getText())/100));
				newt=1;
			}
			if(e.getSource()==buttonSqrt)//按动根号按键的操作
			{
				Answer.setText(Double.toString(java.lang.Math.sqrt(Double.parseDouble(Answer.getText()))));
				newt=1;
			}
			if(e.getSource()==buttonDao)//按动倒数按键的操作
			{
				Answer.setText(Double.toString(1/Double.parseDouble(Answer.getText())));
				newt=1;
			}
			if(e.getSource()==buttonAddAndSub)//按动正负号
			{
				if(Double.parseDouble(Answer.getText())>=0)//如果原先是大于零则改为小于零
				{
					Answer.setText('-'+Answer.getText());
				}
				else//如果原先是小于零则改为大于零
				{
					Answer.setText(Double.toString(-Double.parseDouble(Answer.getText())));
				}
			}
			if(e.getSource()==buttonBk)//按动BackSpace按键
			{
				char[] a=Answer.getText().toCharArray();//把当前算式转换为char数组
				Answer.setText(new String(a,0,a.length-1));//把最后的字符去掉
			}
			if(e.getSource()==buttonCe)//初始化文本栏
			{
		        Answer.setText("0.");
		        newt=1;
		        
			}
		    if(e.getSource()==buttonC)//初始化所有数据
		    {
		    	if(x==1)//简单计算
		    	{
			        vard=answer=0;
			        Answer.setText("0."); 
			        newt=1;
		    	}
		    	else//科学计算
		    		Answer.setText(""); 
		    }
		    if(e.getSource()==buttonMC)//计算器存储器清零
		    {
		    	memory=0;
		    	Memory.setText("");
		    	newt=1;
		    }
		    if(e.getSource()==buttonMS)//计算器存储器存入当前的数据
		    {
		    	memory=Double.parseDouble(Answer.getText());
		    	Memory.setText("M");
		    	Memory.setForeground(new Color(100,100,100));
		    	newt=1;
		    }
		    if(e.getSource()==buttonMR)//计算器存储器读出数据
		    {
		    	Answer.setText(Double.toString(memory));
		    	newt=1;
		    }
		    if(e.getSource()==buttonMAdd)//计算器存储器连加
		    {
		    	memory+=Double.parseDouble(Answer.getText());
		    	Answer.setText(Double.toString(memory));
		    	newt=1;
		    }
		    if(e.getSource()==buttonKleft)//按动左括号按键
		    {
		    	if(x==2)//如果是科学算式运算，简单运算没有括号功能
		    	{
		    		Answer.setText(Answer.getText()+"(");
		    	}
		    }
		    if(e.getSource()==buttonKright)//按动右括号按键
		    {
		    	if(x==2)//如果是科学算式运算，简单运算没有括号功能
		    	{
		    		Answer.setText(Answer.getText()+")");
		    	}
		    }
		    if(e.getSource()==buttonsquare) //按动平方按键
		    {
		    	Answer.setText(Double.toString(Double.parseDouble(Answer.getText())*Double.parseDouble(Answer.getText())));
				newt=1;
		    }
		    if(e.getSource()==buttonpi)//按动pi按键 
		    {
		    	Answer.setText(Double.toString(java.lang.Math.PI));
		    	newt=1;
		    }
		    if(e.getSource()==buttonsin)//按动sin按键
		    {
		    	if(ifDre==1)//如果是角度制
		    		Answer.setText(Double.toString(java.lang.Math.sin(java.lang.Math.PI*Double.parseDouble(Answer.getText())/180)));
		    	else//弧度制
		    		Answer.setText(Double.toString(java.lang.Math.sin(Double.parseDouble(Answer.getText()))));
		    	newt=1;
		    }
		    if(e.getSource()==buttoncos)//按动cos按键
		    {
		    	if(ifDre==1)//如果是角度制
		    		Answer.setText(Double.toString(java.lang.Math.PI*java.lang.Math.cos(180*Double.parseDouble(Answer.getText())/180)));
		    	else//弧度制
		    		Answer.setText(Double.toString(java.lang.Math.cos(Double.parseDouble(Answer.getText()))));
		    	newt=1;
		    }
		    if(e.getSource()==buttontg)//按动tan按键
		    {
		    	if(ifDre==1)//如果是角度制
		    		Answer.setText(Double.toString(java.lang.Math.tan(java.lang.Math.PI*Double.parseDouble(Answer.getText())/180)));
		    	else//弧度制
		    		Answer.setText(Double.toString(java.lang.Math.tan(Double.parseDouble(Answer.getText()))));
		    	newt=1;
		    }
		    if(e.getSource()==buttonlog)//按动log按键
		    {
		    		Answer.setText(Double.toString(java.lang.Math.log10(Double.parseDouble(Answer.getText()))));
		    		newt=1;
		    }
		    pack();//设置最合适的界面大小
		}catch(Exception e1)//如果按动中出现错误，不管是什么错误完成以下操作
		{
			JOptionPane.showMessageDialog(null,"操作非法!!!!\n请看帮助","alert",JOptionPane.ERROR_MESSAGE);
			//弹出alert界面，提示错误
		}
	}
	public void itemStateChanged(ItemEvent e)//单选键的选择
	{
		if(e.getItem()==buD)//如选择角度制
		{
			ifDre=1;//设置角度标志
		}
		if(e.getItem()==buR)//如选择弧度制
		{
			ifDre=0;//设置角度标志
		}
	}
	public static void main(String args[]) 
	{
		new Calc();//程序开始运行
	}
}
