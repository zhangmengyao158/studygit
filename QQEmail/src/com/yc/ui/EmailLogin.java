package com.yc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import com.yc.commons.Content;
import com.yc.commons.MessageUtil;
import com.yc.dao.EmailDAO;
import com.yc.dao.LoginDAO;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class EmailLogin {

	protected Shell shell;
	private Text text_name;
	private Text text_pwd;
	private Label label_Register;
	private Label lblNewLabel;
	private Label label;
	EmailDAO emailDAO=new EmailDAO();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EmailLogin window = new EmailLogin();
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
//		shell = new Shell(SWT.MIN|SWT.CLOSE);
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(EmailLogin.class, "/images2/小图标.png"));
		shell.setSize(1116, 607);
		shell.setText("邮箱登录");
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		
		//Dimension:
		Dimension dem=Toolkit.getDefaultToolkit().getScreenSize();
		//窗口居中显示
		shell.setLocation((dem.width-shell.getSize().x)/2,(dem.height-shell.getSize().y)/2);
		shell.setLayout(new FormLayout());
				
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		FormData fd_sashForm = new FormData();
		fd_sashForm.bottom = new FormAttachment(0, 578);
		fd_sashForm.right = new FormAttachment(0, 1112);
		fd_sashForm.top = new FormAttachment(0);
		fd_sashForm.left = new FormAttachment(0);
		sashForm.setLayoutData(fd_sashForm);
		
		Composite Login_com = new Composite(sashForm, SWT.NONE);
		Login_com.setBackgroundImage(SWTResourceManager.getImage(EmailLogin.class,"/images2/登录界面.jpg"));
		
		text_name = new Text(Login_com, SWT.BORDER);
		text_name.addFocusListener(new FocusAdapter() {
			//文本框失去焦点的时候，触发事件，判断文本框中的数据格式是否正确，次文本框只允许汉字
			@Override
			public void focusLost(FocusEvent e) {
				//获取文本框的内容
				String username=text_name.getText();
				if(username.matches("[0-9]{6,8}")){
					label.setText("");
				}else{
					label.setText("*帐号必须为数字,长度在6-8位之间!");
				}
			}
		});
		text_name.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		text_name.setText("");
		text_name.setBounds(710, 223, 284, 40);
		
		text_pwd = new Text(Login_com, SWT.BORDER | SWT.PASSWORD);
		text_pwd.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		text_pwd.setText("");
		text_pwd.setBounds(710, 273, 284, 40);
		
		final Label label_login = new Label(Login_com, SWT.NONE);
		//登录按钮
		label_login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				label_login.setBackgroundImage(SWTResourceManager.getImage(EmailLogin.class,"/images2/登录按钮.png"));
			}
			@Override
			public void mouseUp(MouseEvent e) {
				label_login.setBackgroundImage(null); 
				String name=text_name.getText();
				
				if(name.matches("[0-9]{6,8}")){
					label.setText("");
				
					if("".equals(text_name.getText()) || "".equals(text_pwd.getText())){
					MessageUtil.promt(shell, "出错了", "用户名或密码不能为空，请重新输入");
					}else{
						name=text_name.getText();
						String pwd=text_pwd.getText();
						Map<String, Object> map=null;
						try {
							int userid=Integer.parseInt(name);
							LoginDAO logindao=new LoginDAO();
							map = new HashMap<String,Object>();
							map=logindao.login(userid, pwd);
							if(map!=null && map.size()>0){
								Content.userID=text_name.getText();
								try {
									List<Map<String, Object>> list1=emailDAO.findUserName(Integer.parseInt(Content.userID));
									if(null!=list1&&list1.size()>0){
										for(Map<String , Object> map1:list1){
											Content.username=(map1.get("USERNAME").toString());
										}
									}
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO 5Auto-generated catch block
									e1.printStackTrace();
								}
								shell.close();
								EmailMain emailMain=new EmailMain();
								emailMain.open();
							}else{
								MessageUtil.promt(shell, "出错了", "用户名或密码错误，请重新输入");
							}
						} catch (SQLException e1) {
							MessageUtil.promt(shell, "出错了", "用户名或密码错误，请重新输入");
							e1.printStackTrace();
						} catch (IOException e1) {
							MessageUtil.promt(shell, "出错了", "用户名或密码错误，请重新输入");
							e1.printStackTrace();
						}
					}
				}else{
					label.setText("*帐号必须为数字,长度在6-8位之间!");
				}
			}
		});
		//label_login.setBackgroundImage(SWTResourceManager.getImage(EmailLogin.class,"/images2/登录按钮.png"));
		label_login.setBounds(711, 372, 282, 38);
		
		final Event event = new Event();
		event.widget=label_login;
		text_pwd.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.keyCode==13){
					label_login.notifyListeners(SWT.MouseUp, event);
				}
			}
		});

		
		label_Register = new Label(Login_com, SWT.NONE);
		label_Register.addMouseListener(new MouseAdapter() {
			//用户注册
			@Override
			public void mouseDown(MouseEvent e) {
				shell.close();
				RegisterMain registerMain=new RegisterMain();
				registerMain.open();
			}
		});
		label_Register.setForeground(SWTResourceManager.getColor(0, 0, 255));
		label_Register.setBounds(947, 489, 43, 17);
		label_Register.setText("注册");
		
		lblNewLabel = new Label(Login_com, SWT.NONE);
		//忘记密码
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.close();
				FindPwdMain findPwdMain=new FindPwdMain();
				findPwdMain.open();				
			}
		});
		lblNewLabel.setForeground(SWTResourceManager.getColor(0, 0, 255));
		lblNewLabel.setBounds(860, 489, 61, 17);
		lblNewLabel.setText("忘记密码？");
		
		label = new Label(Login_com, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(710, 199, 284, 17);
		sashForm.setWeights(new int[] {1});
	}
}
