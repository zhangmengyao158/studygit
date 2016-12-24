package com.yc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.yc.commons.MessageUtil;
import com.yc.dao.LoginDAO;

public class FindPwdMain {

	protected Shell shell;
	private Text text_id;
	private Text text_pwd;
	private Text text_ensurepwd;
	private Text text_question;
	Label label_0;
	Label label_1;
	Label label_2;
	Label label_3;
	Label label_4;
	Label label_id;
	Label label_pwd;
	Label label_ensurepwd;
	Label label_question;
	Label label_answer;
	Combo combo_question;
	Button button_findpwd;
	Button button_return;
	LoginDAO loginDAO=new LoginDAO();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FindPwdMain window = new FindPwdMain();
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.MIN|SWT.CLOSE);
		shell.setImage(SWTResourceManager.getImage(FindPwdMain.class, "/images2/小图标.png"));
		shell.setSize(1116, 607);
		shell.setText("找回密码");
		
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		//Dimension:
		Dimension dem=Toolkit.getDefaultToolkit().getScreenSize();
		//窗口居中显示
		shell.setLocation((dem.width-shell.getSize().x)/2,(dem.height-shell.getSize().y)/2);
		
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setSize(1112, 578);
		sashForm.setLocation(0, 0);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(EmailLogin.class,"/images2/找回密码界面.jpg"));
		
		label_0 = new Label(composite, SWT.NONE);
		label_0.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_0.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_0.setBounds(715, 167, 48, 17);
		label_0.setText("账   号：");
		
		text_id = new Text(composite, SWT.BORDER);
		text_id.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String id = text_id.getText();
				if(id.matches("")){
					label_id.setText("*用户名不能为空请重新输入");
				}else{
					label_id.setText("");
				}
			}
		});
		text_id.setBounds(769, 164, 232, 23);
		
		label_1 = new Label(composite, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_1.setBounds(709, 217, 54, 17);
		label_1.setText(" 新密码：");
		
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
		text_pwd.setBounds(769, 214, 232, 23);
		
		text_ensurepwd = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_ensurepwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		text_ensurepwd.addFocusListener(new FocusAdapter() {
			//注册设置二次密码检验
			@Override
			public void focusLost(FocusEvent e) {
				String name=text_ensurepwd.getText();
				if((text_pwd.getText()).equals(name)){
					label_ensurepwd.setText("");
				}else{
					label_ensurepwd.setText("*密码不一致，请重新输入");
				}
			}
		});
		
		
		text_ensurepwd.setBounds(769, 266, 232, 23);
		
		label_2 = new Label(composite, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_2.setBounds(685, 267, 84, 17);
		label_2.setText(" 确认新密码：");
		 
		combo_question = new Combo(composite, SWT.NONE);
		combo_question.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		combo_question.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		combo_question.setBounds(769, 318, 232, 25);
		combo_question.setText("你的爸爸是谁");
		
		label_3 = new Label(composite, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_3.setBounds(696, 321, 67, 17);
		label_3.setText(" 密保问题：");
		
		text_question = new Text(composite, SWT.BORDER);
		text_question.addFocusListener(new FocusAdapter() {
			//密保答案验证-不能为空
			@Override
			public void focusLost(FocusEvent e) {
				String name=text_question.getText();
				if(name.matches("")){
					label_question.setText("*密保答案不能为空!");
				}else{
					label_question.setText("");
				}
			}
		});
		text_question.setBounds(769, 371, 232, 23);
		
		label_4 = new Label(composite, SWT.NONE);
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_4.setBounds(696, 372, 67, 17);
		label_4.setText(" 密保答案：");
		
		label_id = new Label(composite, SWT.NONE);
		label_id.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_id.setBounds(769, 193, 232, 17);
		
		label_pwd = new Label(composite, SWT.NONE);
		label_pwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_pwd.setBounds(769, 243, 232, 17);
		
		label_ensurepwd = new Label(composite, SWT.NONE);
		label_ensurepwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_ensurepwd.setBounds(769, 295, 232, 17);
		
		label_question = new Label(composite, SWT.NONE);
		label_question.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_question.setBounds(769, 398, 232, 17);
		
		button_findpwd = new Button(composite, SWT.NONE);
		button_findpwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		button_findpwd.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		button_findpwd.addSelectionListener(new SelectionAdapter() {
			//找回密码
			@Override
			public void widgetSelected(SelectionEvent e) {
				int userid=Integer.parseInt(text_id.getText());
				String newpwd=text_pwd.getText();
				String answer=text_question.getText();
				
				boolean flag;
				if(text_pwd.getText().equals(text_ensurepwd.getText())){
					
				
				try {
					flag=loginDAO.FindPwd(userid, newpwd, answer);
					if(flag){
						MessageUtil.promt(shell, "提示", "密码修改成功");
						text_id.setText("");
						text_pwd.setText("");
						text_ensurepwd.setText("");
						text_question.setText("");
					}else{
						MessageUtil.promt(shell, "提示", "密码修改失败");
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				MessageUtil.promt(shell, "提示", "密码修改失败");
			}
			}
		});
		button_findpwd.setBounds(769, 451, 92, 27);
		button_findpwd.setText("确认修改 ");
		
	    button_return = new Button(composite, SWT.NONE);
	    button_return.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
	     
	    	 
	    		
	    button_return.addMouseListener(new MouseAdapter() {
	    			@Override
	    			public void mouseDown(MouseEvent e) {
	    				 
//	     			    	Display.getDefault().close();
	     			    	 shell.close();
	     					EmailLogin emailLogin=new EmailLogin();
	     					emailLogin.open();
	     			    	
	    			}
	    			 
	    			 
	    			 
	    		});
	    
		button_return.setBounds(921, 451, 80, 27);
		button_return.setText("返回 ");
		sashForm.setWeights(new int[] {1});

	}
}
