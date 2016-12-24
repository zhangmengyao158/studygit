package com.yc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes.Name;

import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.bean.EmailBean;
import com.yc.commons.DbHelper;
import com.yc.commons.MessageUtil;
import com.yc.dao.EmailDAO;
import com.yc.dao.LoginDAO;
import com.yc.dao.LoginDAO;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class RegisterMain {

	protected Shell register;
	private Text text_name;
	private Text text_pwd;
	private Text text_checkPwd;
	private Text text_tel;
	private Text text_id;
	private Text text_anw;
	private Label label_name;
	private Label label_pwd;
	private Label label_checkPwd;
	private Label label_tel;
	private Label label_id;
	private Label label_problem;
	private Label label_anw;
	private Button button_register;
	
	EmailDAO emailDAO=new EmailDAO();
	LoginDAO loginDAO=new LoginDAO();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RegisterMain window = new RegisterMain();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		register.open();
		register.layout();
		while (!register.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		register = new Shell(SWT.MIN|SWT.CLOSE);
		register.setImage(SWTResourceManager.getImage(RegisterMain.class, "/images2/小图标.png"));
		register.setSize(1116, 607);
		register.setText("邮箱注册");
		
		register.setBackgroundMode(SWT.INHERIT_DEFAULT);
		//Dimension:
		Dimension dem=Toolkit.getDefaultToolkit().getScreenSize();
		//窗口居中显示
		register.setLocation((dem.width-register.getSize().x)/2,(dem.height-register.getSize().y)/2);
		
		
		SashForm sashForm = new SashForm(register, SWT.NONE);
		sashForm.setSize(1112, 578);
		sashForm.setLocation(0, 0);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
//		composite.setBackgroundImage(SWTResourceManager.getImage(RegisterMain.class,"/images2/注册界面.jpg"));
		composite.setBackgroundImage(SWTResourceManager.getImage(RegisterMain.class,"/images2/用户注册.jpg"));

		
		Label label = new Label(composite, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label.setText("用 户 名：");
		label.setBounds(401, 110, 61, 17);
		
		text_name = new Text(composite, SWT.BORDER);
		text_name.addFocusListener(new FocusAdapter() {
			//用户名检验
			@Override
			public void focusLost(FocusEvent e) {
				String name=text_name.getText();
				if(name.matches("")){
					label_name.setText("*用户名不能为空!");
				}else{
					label_name.setText("");
				}
			}
		});
		text_name.setBounds(472, 110, 196, 23);
		
		Label label_1 = new Label(composite, SWT.NONE|SWT.PASSWORD);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_1.setText("密     码：");
		label_1.setBounds(401, 150, 61, 17);
		
		Label label_2 = new Label(composite, SWT.NONE|SWT.PASSWORD);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_2.setText("确认密码：");
		label_2.setBounds(401, 190, 61, 17);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_3.setText("性      别：");
		label_3.setBounds(401, 230, 61, 17);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_4.setText("生      日：");
		label_4.setBounds(401, 270, 61, 17);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_5.setText("电话号码：");
		label_5.setBounds(401, 310, 61, 17);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_6.setText(" 身 份 证：");
		label_6.setBounds(401, 350, 61, 17);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_7.setText("密保问题：");
		label_7.setBounds(401, 390, 61, 17);
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_8.setText("密保答案：");
		label_8.setBounds(401, 430, 61, 17);
		
		text_pwd = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_pwd.addFocusListener(new FocusAdapter() {
			//注册设置密码检验
			@Override
			public void focusLost(FocusEvent e) {
				String name=text_pwd.getText();
				if(name.matches("")){
					label_pwd.setText("*密码不能为空请重新输入！");
				}else{
					label_pwd.setText("");
				}
				
			}
		});
		text_pwd.setBounds(472, 147, 196, 23);
		
		text_checkPwd = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_checkPwd.addFocusListener(new FocusAdapter() {
			//注册设置二次密码检验
			@Override
			public void focusLost(FocusEvent e) {
				String name=text_checkPwd.getText();
				if((text_pwd.getText()).equals(name)){
					label_checkPwd.setText("");
				}else{
					label_checkPwd.setText("*密码不一致，请重新输入");
				}
			}
		});
		text_checkPwd.setBounds(472, 184, 196, 23);
		
		text_tel = new Text(composite, SWT.BORDER);
		text_tel.addFocusListener(new FocusAdapter() {
			//注册信息：电话号码验证-不能为空
			@Override
			public void focusLost(FocusEvent e) {
				String name=text_tel.getText();
				if(name.matches("[0-9]{11}")){
					label_tel.setText("");
				}else{
					label_tel.setText("*请输入有效的手机号码");
				}
			}
		});
		text_tel.setBounds(472, 304, 196, 23);
		
		text_id = new Text(composite, SWT.BORDER);
		text_id.addFocusListener(new FocusAdapter() {
			//注册信息：身份证验证
			@Override
			public void focusLost(FocusEvent e) {
				String name=text_id.getText();
				if(name.matches("[0-9]{18}")&&!name.matches("")){
					label_id.setText("");
				}else{
					label_id.setText("*身份证只能为18位,不能为空");
				}
			}
		});
		text_id.setBounds(472, 347, 196, 23);
		
		text_anw = new Text(composite, SWT.BORDER);
		text_anw.addFocusListener(new FocusAdapter() {
			//密保答案
			@Override
			public void focusLost(FocusEvent e) {
				if(text_anw.getText().matches("")){
					label_anw.setText("密保答案不能为空");
				}else{
					label_anw.setText("");
				}
			}
		});
		text_anw.setBounds(472, 424, 196, 23);
		
		final Button button_boy = new Button(composite, SWT.RADIO);
		button_boy.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		button_boy.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		button_boy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_boy.setText("男");
		button_boy.setBounds(496, 230, 70, 17);
		
		final Button button_gril = new Button(composite, SWT.RADIO);
		button_gril.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		button_gril.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		button_gril.setText("女");
		button_gril.setBounds(581, 230, 70, 17);
		
		DateTime dateTime = new DateTime(composite, SWT.BORDER);
		
		final CalendarCombo calendarCombo=new CalendarCombo(composite, SWT.BORDER);
		calendarCombo.setBounds(472, 263, 196, 24);
		
		button_register = new Button(composite, SWT.NONE);
		button_register.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		button_register.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		button_register.addSelectionListener(new SelectionAdapter() {
			//用户注册
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Button btnNewButton = new Button(composite, SWT.TOGGLE); //TOGGLE 开关，触发器
				//insert into userInfo values(1,用户名,密码,性别,生日,电话,身份证,'你的初恋是谁？',密保答案,'');
				//获取用户名和密码
				String name = text_name.getText();
				String pwd = text_pwd.getText();
//				String sex=null;
				String sex="";
			    if(button_boy.getSelection()){
			    	sex=button_boy.getText();
			    }else if(button_gril.getSelection()){
			    	sex=button_gril.getText();
			    }
			    String birthday=null;
			    if(calendarCombo.getDateAsString()==null){
			    	birthday="";
			    }else{
			    	birthday=calendarCombo.getDateAsString();
			    }
			    String tel;
				if(text_tel.getText()==null){
					tel="";
				}else{
					tel = text_tel.getText();
				}
				String ID = text_id.getText();
				String anw = text_anw.getText();

				//List<Map<String,Object>> list = null;
				LoginDAO loginDAO=new LoginDAO();
				try {
					List<Object> paramsRegister=new ArrayList<Object>();
					paramsRegister.add(name);
					paramsRegister.add(pwd);
					paramsRegister.add(sex);
					paramsRegister.add(birthday);
					paramsRegister.add(tel);
					paramsRegister.add(ID);
					paramsRegister.add(anw);
					boolean flagRegister=loginDAO.Register(paramsRegister);
					if(flagRegister){
						//EmailBean emailBean=new EmailBean();
						List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
						String string1=null;
						list=loginDAO.findRegisterID(name, ID);
						for(Map<String , Object> map:list){
							string1=map.get("USERID").toString();
						}
						MessageUtil.promt(register, "温馨提示", "注册成功,您的新账号为:"+string1);
						register.close();
						EmailLogin emailLogin=new EmailLogin();
						emailLogin.open();
					}else{
						MessageUtil.promt(register, "温馨提示", "注册失败，请检查您的信息");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					MessageUtil.promt(register, "温馨提示","请填入完整的信息");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//判断是否注册成功
				

			} 
		});
		
		button_register.setBounds(496, 484, 80, 27);
		button_register.setText("注册");
		//button_register.setImage(SWTResourceManager.getImage(RegisterMain.class,"/img/"));

		label_name = new Label(composite, SWT.NONE);
		label_name.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_name.setBounds(686, 110, 203, 17);
		
		label_pwd = new Label(composite, SWT.NONE);
		label_pwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_pwd.setBounds(686, 150, 203, 17);
		
		label_checkPwd = new Label(composite, SWT.NONE);
		label_checkPwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_checkPwd.setBounds(686, 190, 203, 17);
		
		label_tel = new Label(composite, SWT.NONE);
		label_tel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_tel.setBounds(686, 304, 203, 17);
		
		label_id = new Label(composite, SWT.NONE);
		label_id.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_id.setBounds(686, 344, 203, 17);
		
		label_problem = new Label(composite, SWT.NONE);
		label_problem.setBounds(686, 390, 203, 17);
		
		label_anw = new Label(composite, SWT.NONE);
		label_anw.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_anw.setBounds(686, 424, 203, 17);
		
		Label label_9 = new Label(composite, SWT.NONE);
		label_9.addMouseListener(new MouseAdapter() {
			//返回上一页
			@Override
			public void mouseDown(MouseEvent e) {
				register.close();
				EmailLogin emailLogin=new EmailLogin();
				emailLogin.open();
			}
		});
		label_9.setForeground(SWTResourceManager.getColor(0, 0, 205));
		label_9.setBounds(1022, 35, 80, 17);
		label_9.setText("返回上一页");
		
		final Combo combo_question = new Combo(composite, SWT.NONE);
		combo_question.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		combo_question.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		combo_question.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String question=combo_question.getText();

			}
		});
		combo_question.setBounds(472, 390, 192, 25);
		combo_question.setText("你的爸爸是谁");
		sashForm.setWeights(new int[] {1});
		//////
		
	}
}