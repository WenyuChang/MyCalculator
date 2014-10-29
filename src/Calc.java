import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
public class Calc extends JFrame implements ActionListener,ItemListener 
{ 
//���±������ڸü������Ŀ�ѧ��ʽ������
	String str="";//�洢��ʽ
	char[] c=str.toCharArray();//���ڷָ�ÿ����ʽ��ÿ���ַ�
	char cur;
	int n=-1,TRn=-1,NDn=-1;//���ں�����ʽ����ջ��ջ
	double flag=0;//��־����
	int flag1=0;//��־С��
	char[] OPTR=new char[9999];//�洢����
	double[] OPND=new double[9999];//�洢�����
	int ifDre=1;//��־�Ƕ��ƻ��ǻ�����
	
//����������һЩ�õ��ı���
	JTextField Answer;//��ʾ���ݵ�Test��
	JPanel panel, panel1, panel2, panel3;//���������õ��ĸ����м�����
	JTextField Memory;//���ڼ������е�memory��ʾ
	JLabel Space;//������ʾ��ʱ������ģʽ״̬
	JTextArea help;//�����ı���
	int x=1,newt=1,action=0,Op=-1,preOp=-1;//��һЩ����
	double answer=0,vard=0,memory=0;//���ڴ������
	JRadioButton buD,buR;//�Ƕȣ����ȵ�ѡ��
	
//�ü��������а����Ķ���
	JButton buttonBk, buttonCe, buttonC;
	JButton button[];
	JButton buttonMC, buttonMR, buttonMS, buttonMAdd;
	JButton buttonKleft,buttonKright,buttonpi,buttonsquare;
	JButton buttonsin,buttoncos,buttontg,buttonlog;
	JButton buttonDot, buttonAddAndSub, buttonAdd, buttonSub, buttonMul,buttonDiv, buttonMod;
	JButton buttonSqrt, buttonDao, buttonEqual;
//�ü������˵�
	JMenuBar mainMenu;//�˵���
	JMenu fileMenu,editMenu, viewMenu, helpMenu;//�����˵���
	JMenuItem closeItem,copyItem, pasteItem, cutItem, tItem, sItem, topHelp, aboutCal;//�˵���
	JScrollPane scrollHelp;
	
	
	public Calc()//����Ĺ��캯�����ü��������п�ʼ��
	{
		//����ͼ�ν���
		super("��/��ѧ������");
		panel = new JPanel();
		setLocationRelativeTo(null);
		getContentPane().add(panel);
		//������ͼ�ν���Ľ�������
		Answer = new JTextField(15);//���ó���
		Answer.setText("0.");//���ó�ʼֵ
		Answer.setHorizontalAlignment(JTextField.RIGHT);//������ʾλ��
		Answer.setEditable(false);//���ÿɷ����
		Answer.setBackground(new Color(255, 255, 255));//������ɫ
		panel.setLayout(new BorderLayout());//������������
		//�����ǲ˵���������
		mainMenu = new JMenuBar();
		fileMenu=new JMenu("�ļ�(F)"); 
		editMenu = new JMenu("�༭(E)");
		viewMenu = new JMenu("�鿴(V)");
		helpMenu = new JMenu("����(H)");
		closeItem=new JMenuItem("   �˳�");
		fileMenu.add(closeItem);
		closeItem.addActionListener(this);
		copyItem = new JMenuItem("   ����(C) Ctrl+C");
		copyItem.addActionListener(this);
		pasteItem = new JMenuItem("   ճ��(V) Ctrl+V");
		pasteItem.addActionListener(this);
		cutItem = new JMenuItem("   ����(X) Ctrl+X");
		cutItem.addActionListener(this);
		editMenu.add(copyItem);
		editMenu.addSeparator();
		editMenu.add(cutItem);
		editMenu.addSeparator();
		editMenu.add(pasteItem);
		tItem = new JMenuItem("   ��׼��(T)");
		tItem.addActionListener(this);
		sItem = new JMenuItem("   ��ѧ��(S)");
		sItem.addActionListener(this);
		viewMenu.add(tItem);
		viewMenu.addSeparator();
		viewMenu.add(sItem);
		topHelp = new JMenuItem("   ����(H)");
		//�����˵�������
		topHelp.addActionListener(this);
		help=new JTextArea(5, 20);
		scrollHelp=new JScrollPane(help);
		help.setEditable(false);
		String str="";
		str=str+"�򵥼���\n"+"1.  �������ĵ�һ�����֡�\n"+"2.  ������+�������������\n"
	    	+"3.  ����������һ�����֡�\n"+"4.  ���� ��=����\n";
		str=str+"\n"+"��ѧ����\n"+"1.  ����һ������ʽ��\n"+"2.  ���� ��=����\n"+"���ӣ�1.7����5.3��8.6��\n";
		help.append(str);//��ԭ�����ı����к������Ҫ������
		aboutCal = new JMenuItem("   ���ڼ�����(A)");
		aboutCal.addActionListener(this);
		helpMenu.add(topHelp);
		helpMenu.addSeparator();
		helpMenu.add(aboutCal);
		mainMenu.add(fileMenu);
		mainMenu.add(editMenu);
		mainMenu.add(viewMenu);
		mainMenu.add(helpMenu);
		panel.add(mainMenu, BorderLayout.NORTH);//Ϊ�˷���Ѳ˵���������������
		panel.add(Answer, BorderLayout.CENTER);
		
		
		//�����Ƕ�����ͼ�ν���Ĳ�������
		panel1 = new JPanel();//����1
		panel2 = new JPanel();//����2
		panel3 = new JPanel();//����3
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
	    Space = new JLabel("ģʽ���򵥼���");
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
		for (int i = 0; i < button.length; i++) //Ϊ�˴���ļ��ʹ��ѭ���������ֵİ�����Ϊ����������ɫ
		{
			button[i] = new JButton(Integer.toString(i));
			button[i].setForeground(new Color(0, 0, 255));
		}
		//�����İ����Ĵ����Լ���ɫ������
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
		
		
		panel3.setLayout(new GridLayout(4,8));//����3�������ͣ������Ǹ��������ļ���
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
	
	//���������ڿ�ѧ��ʽ�������õ��ļ�������In������Precede������Operate������Operation()
	boolean In(char c)//�÷����������Խ��ܵ��ַ��Ƿ�������
	{
		if((c>='0')&&(c<='9')||(c=='.'))//�������ֵĲ���
		{
			if(c=='.')//��ʱ��.��ʱ���ʾ��������С������������
			{
				flag1=-1;//����������ʾ��������С����Ȩֵ
				flag=OPND[NDn];//���ֳ�ջ����
				NDn--;
				return false;//����
			}
			if(flag!=0)//��������������������ַ���ʾ��������10�������²���
			{
				if(flag1==0)//������������
				{
					double x=c;
					flag=OPND[NDn]*10+x-48;
					NDn--;
				}
				else//��С���ǵĲ���
				{
					double x=c;
					flag=OPND[NDn]+(x-48)*java.lang.Math.pow(10,-1);
					NDn--;
				}
			}
			if(flag==0)//���ֻ������һ�������ַ��ǵĲ���
			{
				double x=c;//x���ڴ洢�ַ�c��ASC��
				flag=x-48;//���ַ����͵����ֱ�Ϊ����
			}
			return false;
		}
		else//����������ֵĲ���
		{
			flag=0;
			return true;
		}
	}
	
	
	char Precede(char c1,char c2)//������������������ȼ���������һ�������ж����²����ķ���
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
	
	double Operate(double a,char theta,double b)//��a theta b�Ĳ���
	{
		switch(theta)
		{
		case '+'://�ӷ�����
			{
				double result=a+b;
				return result;
			}
		case '-'://��������
			{
				double result=a-b;
				return result;
			}
		case '*'://�˷�����
			{
				double result=a*b;
				return result;
			}
		case '/'://��������
			{
				double result=a/b;
				return result;
			}
		}
		double result=0;
		return result;
	}
	
	
	public String operation(String str)//��ѧ����������ܷ���������������һЩ������ɲ���
	{
		c=str.toCharArray();//����ʽ�ֽ��ÿ���ַ�����char������
		OPTR[++TRn]='#';//��һ���ַ���ջ
		cur=c[++n];//�ж��¸��ַ�
		//���������������в���
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
						OPTR[++TRn]=cur;//�ַ���ջ
						cur=c[++n];//�¸��ַ�
						flag=0;//flag��ʼ��
						break;
					}
				case '=':
					{
						TRn--;//��ջ
						cur=c[++n];//�¸��ַ�
						flag=0;//flag��ʼ��
						break;
					}
				case '>':
					{
						char theta;
						double a,b;
						flag=0;//flag��ʼ��
						b=OPND[NDn--];//����2��ջ
						a=OPND[NDn--];//����1��ջ
						theta=OPTR[TRn--];//�ַ���ջ
						OPND[++NDn]=Operate(a,theta,b);//����
						break;
					}
				default: break;
				}
			}
		}
		str=Double.toString(OPND[NDn]);
		return str;//�Ѵ𰸷���
	}
	public void actionPerformed(ActionEvent e)//�������е��¼�
	{
		try
		{
			if(e.getSource()==closeItem)//�����˳��˵���
			{
				System.exit(0);
			}
			if(e.getSource()==copyItem)//�������Ʋ˵���
			{
				Answer.copy();
			}
			if(e.getSource()==pasteItem)//����ճ����
			{
				Answer.paste();
			}
			if(e.getSource()==cutItem)//����������
			{
				Answer.cut();
			}
			if(e.getSource()==tItem)//�����򵥼�����
			{
				x=1;//��־�򵥼���
				Space.setText("ģʽ���򵥼���");
				Space.setForeground(new Color(50,100,255));
				memory=answer=vard=0;//���������ʼ��
			}
			if(e.getSource()==sItem)//������ѧ����
			{
				x=2;//��־��ѧ����
				Space.setText("ģʽ����ѧ����");
				Space.setForeground(new Color(100,100,100));
				memory=answer=vard=0;//���������ʼ��
				Answer.setText("");//�ı�����ʼ��
			}
			if(e.getSource()==topHelp)//���������˵���
			{
				JOptionPane.showMessageDialog(panel,scrollHelp);//���������Ի���
			}
			if(e.getSource()==aboutCal)//�������ڲ˵���
			{
				JOptionPane.showMessageDialog(panel,"            ������\n     �����ߣ�������");//�������ڶԻ���
			}
			
			for (int i=0;i<=9;i++)//�������������ְ���
		        if (e.getSource()==button[i])
		        {
		        	if(x==1)//����Ǽ򵥼���ʱ�����¼�����
		        	{
		        		if(newt==1)//���������������
		        		{
		        			Answer.setText(Integer.toString(i));
		        			newt=0;
		        		}
		        		else//������������ʱ
		        		{
		        			Answer.setText(Answer.getText()+Integer.toString(i));
		        			newt=0;
		        		}
		        	}
		        	else//��ʱ��ѧ����ʱ
		        	{
		        		Answer.setText(Answer.getText()+Integer.toString(i));
		        	}
		        }
			if (e.getSource()==buttonDot)//����С���㰴��
			{
				if(x==1)//��ʱ�򵥼���ʱ
				{
			        boolean isDot=false;//��ʾ�ܷ�������С����
			        if (Answer.getText().length() == 0)//���û�������κζ���ʱ
			        	isDot=true;
			        for (int i=0;i<Answer.getText().length();i++)//�������С�������
			        	if ('.'==Answer.getText().charAt(i)) 
			        	{
			        		isDot = true;
			        		break;
			        	}
			        if (isDot == false)//������
			          Answer.setText(Answer.getText() + ".");
				}
				else//��ѧ����ʱ
					Answer.setText(Answer.getText() + ".");
		    }
			if (e.getSource()==buttonAdd||e.getSource()==buttonSub||e.getSource()==buttonMul||
					e.getSource()==buttonDiv)//�����ǰ������������ʱ��Ҫ���Ĳ���
			{
				if(x==1)//����Ǽ򵥼���ʱ
				{
					newt=1;//��ʼ��
					switch(preOp)//�鿴�ϸ��������ʲô�������������� 
					{
		            case 0://�����Ǽӷ�����
		              answer+=Double.parseDouble(Answer.getText());
		              break;
		            case 1://����Ǽ�������
		              answer-=Double.parseDouble(Answer.getText());
		              break;
		            case 2://����ǳɷ�����
		              answer*=Double.parseDouble(Answer.getText());
		              break;
		            case 3://����ǳ�������
		              if(Double.parseDouble(Answer.getText())==0)//��������Ϊ��
		            	  Answer.setText("��������Ϊ��");
		              else
		                answer/=Double.parseDouble(Answer.getText());
		              break;
		            default://�����һ������ʱ�Ĳ���Ŷ
		              answer=Double.parseDouble(Answer.getText());
					}
			        if (e.getSource()==buttonAdd) //�ӷ�����
			        {
			          Answer.setText(Double.toString(answer));
			          preOp = Op = 0;//��־ǰһ�������ڲ������������־
			        }
			        if (e.getSource()==buttonSub) 
			        {
			          Answer.setText(Double.toString(answer));//��������
			          preOp = Op = 1;//��־ǰһ�������ڲ������������־
			        }
			        if (e.getSource()==buttonMul) //�˷�����
			        {
			          Answer.setText(Double.toString(answer));
			          preOp = Op = 2;//��־ǰһ�������ڲ������������־
			        }
			        if (e.getSource()==buttonDiv) //��������
			        {
			          Answer.setText(Double.toString(answer));
			          preOp = Op = 3;//��־ǰһ�������ڲ������������־
			        }
				}
				else//���ڿ�ѧ��ʽ
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
			if(e.getSource()==buttonEqual) //�������ڰ���
			{
				if(x==2)//����ǿ�ѧ��ʽ����
				{
					str=str+Answer.getText()+"#";//����ʽ��׷�ӡ�������Ϊ��������㷽��
					Answer.setText(operation(str));//ͨ������operation����
				}
				else//������
				{
					if(preOp==4)//�����ǰ�����㲻��һ��������
					{
						if(Op==0)//������������������Ǽӷ�
						{
							answer+=vard;//���ý��
				            Answer.setText(Double.toString(answer));//���ý���ı���
						}
						if (Op == 1)//������������������Ǽ���
						{
				            answer-=vard;//���ý��
				            Answer.setText(Double.toString(answer));//���ý���ı���
				        }
				        if(Op==2)//������������������ǳ˷�
				        {
				            answer*=vard;//���ý��
				            Answer.setText(Double.toString(answer));//���ý���ı���
				        }
				        if(Op==3)//������������������ǳ˷�
				        {
				            if (Double.parseDouble(Answer.getText())==0)//������Ϊ��
				              Answer.setText("��������Ϊ��");
				            else 
				            {
				              answer/=vard;//���ý��
				              Answer.setText(Double.toString(answer));//���ý���ı���
				            }
				        }
					}
					else//�����ǰ�Ѿ������㣬������������
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
				            	Answer.setText("��������Ϊ��");
				            else 
				            {
				              answer/= Double.parseDouble(Answer.getText());
				              Answer.setText(Double.toString(answer));
				            }
				         }
					}
				    preOp=4;//��ʼ��
					newt=1;//��ʼ��
				}
			}
			if(e.getSource()==buttonMod)//������������Ҫ�Ĳ���
			{
				Answer.setText(Double.toString(Double.parseDouble(Answer.getText())/100));
				newt=1;
			}
			if(e.getSource()==buttonSqrt)//�������Ű����Ĳ���
			{
				Answer.setText(Double.toString(java.lang.Math.sqrt(Double.parseDouble(Answer.getText()))));
				newt=1;
			}
			if(e.getSource()==buttonDao)//�������������Ĳ���
			{
				Answer.setText(Double.toString(1/Double.parseDouble(Answer.getText())));
				newt=1;
			}
			if(e.getSource()==buttonAddAndSub)//����������
			{
				if(Double.parseDouble(Answer.getText())>=0)//���ԭ���Ǵ��������ΪС����
				{
					Answer.setText('-'+Answer.getText());
				}
				else//���ԭ����С�������Ϊ������
				{
					Answer.setText(Double.toString(-Double.parseDouble(Answer.getText())));
				}
			}
			if(e.getSource()==buttonBk)//����BackSpace����
			{
				char[] a=Answer.getText().toCharArray();//�ѵ�ǰ��ʽת��Ϊchar����
				Answer.setText(new String(a,0,a.length-1));//�������ַ�ȥ��
			}
			if(e.getSource()==buttonCe)//��ʼ���ı���
			{
		        Answer.setText("0.");
		        newt=1;
		        
			}
		    if(e.getSource()==buttonC)//��ʼ����������
		    {
		    	if(x==1)//�򵥼���
		    	{
			        vard=answer=0;
			        Answer.setText("0."); 
			        newt=1;
		    	}
		    	else//��ѧ����
		    		Answer.setText(""); 
		    }
		    if(e.getSource()==buttonMC)//�������洢������
		    {
		    	memory=0;
		    	Memory.setText("");
		    	newt=1;
		    }
		    if(e.getSource()==buttonMS)//�������洢�����뵱ǰ������
		    {
		    	memory=Double.parseDouble(Answer.getText());
		    	Memory.setText("M");
		    	Memory.setForeground(new Color(100,100,100));
		    	newt=1;
		    }
		    if(e.getSource()==buttonMR)//�������洢����������
		    {
		    	Answer.setText(Double.toString(memory));
		    	newt=1;
		    }
		    if(e.getSource()==buttonMAdd)//�������洢������
		    {
		    	memory+=Double.parseDouble(Answer.getText());
		    	Answer.setText(Double.toString(memory));
		    	newt=1;
		    }
		    if(e.getSource()==buttonKleft)//���������Ű���
		    {
		    	if(x==2)//����ǿ�ѧ��ʽ���㣬������û�����Ź���
		    	{
		    		Answer.setText(Answer.getText()+"(");
		    	}
		    }
		    if(e.getSource()==buttonKright)//���������Ű���
		    {
		    	if(x==2)//����ǿ�ѧ��ʽ���㣬������û�����Ź���
		    	{
		    		Answer.setText(Answer.getText()+")");
		    	}
		    }
		    if(e.getSource()==buttonsquare) //����ƽ������
		    {
		    	Answer.setText(Double.toString(Double.parseDouble(Answer.getText())*Double.parseDouble(Answer.getText())));
				newt=1;
		    }
		    if(e.getSource()==buttonpi)//����pi���� 
		    {
		    	Answer.setText(Double.toString(java.lang.Math.PI));
		    	newt=1;
		    }
		    if(e.getSource()==buttonsin)//����sin����
		    {
		    	if(ifDre==1)//����ǽǶ���
		    		Answer.setText(Double.toString(java.lang.Math.sin(java.lang.Math.PI*Double.parseDouble(Answer.getText())/180)));
		    	else//������
		    		Answer.setText(Double.toString(java.lang.Math.sin(Double.parseDouble(Answer.getText()))));
		    	newt=1;
		    }
		    if(e.getSource()==buttoncos)//����cos����
		    {
		    	if(ifDre==1)//����ǽǶ���
		    		Answer.setText(Double.toString(java.lang.Math.PI*java.lang.Math.cos(180*Double.parseDouble(Answer.getText())/180)));
		    	else//������
		    		Answer.setText(Double.toString(java.lang.Math.cos(Double.parseDouble(Answer.getText()))));
		    	newt=1;
		    }
		    if(e.getSource()==buttontg)//����tan����
		    {
		    	if(ifDre==1)//����ǽǶ���
		    		Answer.setText(Double.toString(java.lang.Math.tan(java.lang.Math.PI*Double.parseDouble(Answer.getText())/180)));
		    	else//������
		    		Answer.setText(Double.toString(java.lang.Math.tan(Double.parseDouble(Answer.getText()))));
		    	newt=1;
		    }
		    if(e.getSource()==buttonlog)//����log����
		    {
		    		Answer.setText(Double.toString(java.lang.Math.log10(Double.parseDouble(Answer.getText()))));
		    		newt=1;
		    }
		    pack();//��������ʵĽ����С
		}catch(Exception e1)//��������г��ִ��󣬲�����ʲô����������²���
		{
			JOptionPane.showMessageDialog(null,"�����Ƿ�!!!!\n�뿴����","alert",JOptionPane.ERROR_MESSAGE);
			//����alert���棬��ʾ����
		}
	}
	public void itemStateChanged(ItemEvent e)//��ѡ����ѡ��
	{
		if(e.getItem()==buD)//��ѡ��Ƕ���
		{
			ifDre=1;//���ýǶȱ�־
		}
		if(e.getItem()==buR)//��ѡ�񻡶���
		{
			ifDre=0;//���ýǶȱ�־
		}
	}
	public static void main(String args[]) 
	{
		new Calc();//����ʼ����
	}
}
