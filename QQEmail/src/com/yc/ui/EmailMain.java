package com.yc.ui;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JTree;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import com.yc.commons.Content;
import com.yc.commons.DbHelper;
import com.yc.commons.MessageUtil;
import com.yc.dao.EmailDAO;
import com.yc.dao.LoginDAO;
import com.yc.dao.ReadDAO;
import com.yc.dao.SendDAO;
import com.yc.dao.Tqyb;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.ui.IFileEditorMapping;
import org.eclipse.swt.widgets.Combo;
 

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;

public class EmailMain {

	protected Shell shell;
	private StackLayout stackLayout;
	private Composite con_content;
	private Composite com_menu;
	private Composite com_main;
	private Composite com_write;
	private Composite com_qun;
	Composite com_addfriends;
	private Text text_toid;
	private Text text_zhuti;
	private Text text_content;
	private Text text_quntoid;
	private Text text_qunzhuti;
	private Text text_quncontent;
	private Composite com_shoujianxiang;
	private Composite com_yifasong;
	private Composite com_lajixiang;
	private Composite com_yishanchu;
	private Composite com_qunshoujianxiang;
	private Composite com_caogaoxiang;
	private Table table_shoujianxiang;
	EmailDAO emailDAO=new EmailDAO();
	LoginDAO loginDAO=new LoginDAO();
	String string1;
	String selected;
	FileDialog fd;
	List<Object> list;
	DateFormat dateFormat;
	private Table table_caogaoxiang;
	private Table table_yishanchu;
	private Table table_lajixiang;
	private Table table_yifasong;
	Table table_yishanchu1;
	Label label_1;
	StringBuffer sb;
	String [] filename;
	String lujing;
	DbHelper db=null;
	String []str;
	int fromid=Integer.parseInt(Content.userID);
	String username;
	Composite composite;
	Label label_count;
	String string="";
	Composite com_select;
	Composite com_selectyifasong;
	SendDAO sendDAO=new SendDAO();
	ReadDAO readDAO=new ReadDAO();
	Label label_selectfrom;
	Label label_selectdate;
	Label label_selectzhuti;
	Label label_selectcontent;
	Label label_selecttoid;
	Label label_selectdate2;
	Label label_selectzhuti2;
	Label label_selectcontent2;
	private Text text_caogaoxiangzhuti;
	private Text text_caogaoxiangtoid;
	private Text text_caogaoxinagcontent;
	Composite com_selectcaogaoxiang;
	Label label_caogaoxiangdate;
	String[]filename1;
	private Table table;
	String string2;
	Label label_tishi;
	Label label_yishanchutishi;
	Label label_userid;
	Composite com_selectyishanchu;
	Label label_yishanchuzhuti;
	Label label_yishanchutoid;
	Label label_yishanchudate;
	Label label_yishanchucontent;
	Label label_yifasongtishi;
	Label label_caogaoxiangtishi;
	private Table table_1;
	String str1;
	String str2;
	private Table table_qunshoujianxiang;
	Composite com_tongxunlu;
	Display display;
	private Table table_tongxunlu;
	Label label_qunzhuti;
	Label label_qunfromid;
	Label label_qunid;
	Label label_qundate;
	Label label_quncontent;
	Table table_qun;
	Label label_qunlujing;
	private Table table_quntable;
	Composite com_selectqun;
	private Table table_selectlajitable;
	Composite com_selectlaji;
	Label label_selectlajititle;
	Label label_selectlajifrom;
	Label label_selectlajicontent;
	Label label_selectlajidate;
	Label label_quntishi;
	Label label_lajixiangtishi;
	Label label_tongxunlutishi;
	private Text text_updatefriendname;
	Composite com_updatefriend;
	Combo combo;
 	private Text text_adduserid;
	private Text text_addpersonname;
	Combo combo_addpersontype;
	TreeItem treeItemfriend;
	TreeItem treeItemqun;
	private Text text_addusername;
	private Text text_addusertel;
	private Text text_friendtel;
	private Text text_friendname;
	private Text text_friendid;
	Label label_weather;
	Label label_42;
	
	
	/**
	 * Launch the application.
	 * @param args
	 */
	
	public static void main(String[] args) {
		try {
			EmailMain window = new EmailMain();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
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
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell(SWT.MIN|SWT.CLOSE);
		shell.setImage(SWTResourceManager.getImage(EmailMain.class, "/images2/小图标.png"));
		shell.setSize(1116, 607);
		shell.setText("QQ邮箱");
		
		
		
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		//Dimension:����ߴ� ToolKit: ���߰�
		Dimension dem=Toolkit.getDefaultToolkit().getScreenSize();
		//���ھ�����ʾ
		shell.setLocation((dem.width-shell.getSize().x)/2,(dem.height-shell.getSize().y)/2);
		
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setSize(1112, 578);
		sashForm.setLocation(0, 0);
		
		composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/qqy邮箱主界面 - 副本.png"));
		
		
		final Label label_write = new Label(composite, SWT.NONE);
		//写信点击
		label_write.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				label_write.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/写信-事件.png"));
				stackLayout.topControl=com_write;
				com_menu.layout();
				text_toid.setText("");
				text_zhuti.setText("");
				text_content.setText("");
				label_1.setText("");
				treeItemfriend.removeAll();
				List<Map<String, Object>> listlianxiren;
				try {
					listlianxiren = emailDAO.addressbook(fromid);
					for(Map<String , Object> map2:listlianxiren){
						String personid3=map2.get("PERSONID").toString();
						Map<String , Object> maplianxiren=emailDAO.findFriendName(Integer.parseInt(personid3));
						TreeItem xinhaoyou=new TreeItem(treeItemfriend, SWT.NONE);
						if(map2.get("PERSONNAME")==null){
							xinhaoyou.setText(maplianxiren.get("USERNAME").toString());
						}else{
							xinhaoyou.setText(map2.get("PERSONNAME").toString());
						}
						System.out.println(maplianxiren);
//						text_toid.setText(xinhaoyou.getText());
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			@Override
			public void mouseUp(MouseEvent e) {
				label_write.setBackgroundImage(null);
			}
		});
		label_write.setBounds(19, 79, 158, 24);
		
		final Label label_receive = new Label(composite, SWT.NONE);
		//收件点击
		label_receive.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				label_receive.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/收信-事件.png"));
				stackLayout.topControl=com_shoujianxiang;
				com_menu.layout();
				try{
					List<Object> params5=new ArrayList<Object>();
					params5.add(fromid);
					List<Map<String , Object>> list=emailDAO.shoujianxiang(params5);
					table_shoujianxiang.removeAll();

					if(null!=list&&list.size()>0){
						TableItem tableItem;
						for(Map<String , Object> map:list){
							tableItem=new TableItem(table_shoujianxiang, SWT.None);
							
							int emailid1=Integer.parseInt(map.get("EMAILID").toString());
							Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
							int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
							if(type==0){
								tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
								if(map.get("TITLE")==null){
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
								}else{
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
								}
							}else{
								if(map.get("TITLE")==null){
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
								}else{
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
								}
								
							}
							
						}
					}else{
						//MessageUtil.promt(shell, "温馨提示", "无学生信息");
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseUp(MouseEvent e) {
				label_receive.setBackgroundImage(null);
			}
		});
		label_receive.setBounds(19, 109, 158, 24);
		
		final Label label_addressbook = new Label(composite, SWT.NONE);
		//通信录-事件.png
		label_addressbook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				label_addressbook.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/通信录-事件.png"));
				stackLayout.topControl=com_tongxunlu;
				com_menu.layout();
				try{
//					List<Object> paramstongxunlu=new ArrayList<Object>();
//					paramstongxunlu.add(fromid);
					List<Map<String , Object>> list3=emailDAO.addressbook(fromid);
					table_tongxunlu.removeAll();

					if(null!= list3 &&list3.size()>0){
						TableItem tableItem;
						for(Map<String,Object> map:list3){
							System.out.println(map);
							System.out.println(map.get("PERSONID").toString());
							String friendTel=null;
							tableItem = new TableItem(table_tongxunlu,SWT.NONE);
							Map<String , Object> mapfriend=emailDAO.findFriendName(Integer.parseInt(map.get("PERSONID").toString()));
							String friendname=mapfriend.get("USERNAME").toString();
							System.out.println(mapfriend);
							if(mapfriend.get("TEL")==null){
								friendTel=friendTel=null;
							}else{
								friendTel=mapfriend.get("TEL").toString();
							}
//							tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,map.get("PERSONNAME").toString()," ",map.get("PERSONTYPE").toString()});
							if(map.get("PERSONNAME")==null&&mapfriend.get("TEL")==null){
								tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,"","",map.get("PERSONTYPE").toString()});
							}
							if(map.get("PERSONNAME")!=null&&mapfriend.get("TEL")==null){
								tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,map.get("PERSONNAME").toString(),"",map.get("PERSONTYPE").toString()});
							}
							if(map.get("PERSONNAME")==null&&mapfriend.get("TEL")!=null){
								tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,"",friendTel,map.get("PERSONTYPE").toString()});
							}
							if(map.get("PERSONNAME")!=null&&mapfriend.get("TEL")!=null){
								tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,map.get("PERSONNAME").toString(),friendTel,map.get("PERSONTYPE").toString()});
							}
						}
						
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseUp(MouseEvent e) { 
				label_addressbook.setBackgroundImage(null);
			}
		});
		label_addressbook.setBounds(20, 139, 158, 24);
		
		com_menu = new Composite(composite, SWT.NONE);
		com_menu.setBounds(197, 79, 915, 499);
		stackLayout=new StackLayout();
		com_menu.setLayout(stackLayout);
		
		//主界面-首页.png主界面-首页.png
		com_main = new Composite(com_menu, SWT.NONE);
		com_main.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/主界面-首页.jpg"));
		stackLayout.topControl=com_main;
		composite.layout();
		
		label_count = new Label(com_main, SWT.CENTER);
		label_count.setForeground(SWTResourceManager.getColor(0, 0, 102));
		label_count.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		//未读邮件数量
		label_count.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				try{
					Thread.sleep(500);
					List<Map<String , Object>> list=emailDAO.weiduCount(fromid);
					if(null!=list&&list.size()>0){
						for(Map<String , Object> map:list){
							string=map.get("COUNT1").toString();
						}
						label_count.setText(string);
					}else{
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		label_count.setBounds(60, 61, 30, 17);
		
		Label label_14 = new Label(com_main, SWT.NONE);
		label_14.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		label_14.setBounds(10, 7, 277, 28);
		label_14.setText("您好！ "+Content.username);
		
		label_weather = new Label(com_main, SWT.NONE|SWT.WRAP);
		label_weather.setBounds(597, 43, 308, 70);
		
		Label label_32 = new Label(com_main, SWT.NONE);
		label_32.setImage(SWTResourceManager.getImage(EmailMain.class, "/images3/天气预报.png"));
		label_32.setBounds(597, 7, 84, 28);
		
		
		com_menu.layout();
		
		//个人信界面.jpg
		com_write = new Composite(com_menu, SWT.NONE);
		com_write.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/个人信界面.jpg"));
		
		Label label_qun = new Label(com_write, SWT.NONE);
		//群写信界面.jpg
		label_qun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//label_qun.setBackgroundImage(SWTResourceManager.getImage(EmailMainTest1.class,"/images/群写信界面.jpg"));
				stackLayout.topControl=com_qun;
				com_menu.layout();
				text_quntoid.setText("");
				text_qunzhuti.setText("");
				text_quncontent.setText("");
				label_qunlujing.setText("");
				treeItemqun.removeAll();
				List<Map<String, Object>> listlianxiren;
				try {
					listlianxiren = emailDAO.findqunName(fromid);
					for(Map<String , Object> map2:listlianxiren){
						String personid3=map2.get("FLOCKNAME").toString();
//						Map<String , Object> maplianxiren=emailDAO.findqunName(fromid);
						TreeItem qunName=new TreeItem(treeItemqun, SWT.NONE);
						qunName.setText(personid3);
//						text_toid.setText(xinhaoyou.getText());
					}
				} catch (FileNotFoundException e1) {
 					e1.printStackTrace();
				} catch (SQLException e1) {
 					e1.printStackTrace();
				}
			}
		});
		label_qun.setBounds(115, 1, 108, 26);
		
		text_toid = new Text(com_write,SWT.BORDER | SWT.MULTI);
		//自动补齐
		text_toid.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent arg0) {
						String temp = text_toid.getText();
						//输入的文本框末尾不能为空    且它的前面是空字符
						int len = temp.length();
						if(len<3){
							return ;
						}
						if((!(temp.charAt(len-1)+"").equals(" "))&&((temp.charAt(len-2)+"").equals(""))){
							text_toid.setSelection(len);
							str1= text_toid.getText();
							addNameTextFieldAssist(text_toid);
						}
					}
				});
				addNameTextFieldAssist(text_toid);
				
				
		text_toid.setBounds(68, 80, 624, 23);
		
		text_zhuti = new Text(com_write, SWT.BORDER);
		text_zhuti.setBounds(68, 136, 624, 23);
		
		text_content = new Text(com_write, SWT.BORDER|SWT.WRAP|SWT.H_SCROLL|SWT.V_SCROLL|SWT.CANCEL|SWT.MULTI);
		text_content.setBounds(68, 202, 624, 287);
		
		Button regularmail_receive = new Button(com_write, SWT.NONE);
			///发送邮件
			regularmail_receive.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
//					String path1 = label_pri.getText();
//					File file1 = new File(path1);
					//获得用户邮箱号
					String userid = text_toid.getText();
					String title1;
					if(text_zhuti.getText()==null){
						title1=null;
					}else{
						title1=text_zhuti.getText();
					}
					String content=text_content.getText();
					int emailid1=0;
					//String email_date="20160520";
					List<Object> params = new ArrayList<>();
					
					//params.add(email_date);
					if(userid.matches("[0-9]+@(sina|qq|163)+(\\.(com|cn|org|edu|hk))")){
						params.add(fromid);
						int uid=Integer.parseInt(userid.substring(0,userid.lastIndexOf("@")));
						params.add(uid);
						params.add(title1);
						params.add(content);
//						if(title1.matches("")||content.matches("")){
//							MessageUtil.promt(shell, "温馨提示", "您的主题或内容不能为空");
//						}else{
							boolean flag =false;
							try {
								flag =sendDAO.sendEmail(params);
								//文件表插入
								if (flag) {
									List<Object> params1=new ArrayList<Object>();
//									params1.add(userid);
//									Map<String, Object> map=emailDAO.findEmailID(params1);
									Map<String, Object> map=emailDAO.DatecaogaofindEmailID();
									String string2=map.get("EMAILID").toString();
//									System.out.println(string2);
									emailid1=Integer.parseInt(string2);
//									System.out.println(emailid1);
									Map<String , Object> map22=readDAO.findemailexist(emailid1);
									if(map22!=null){
										int id=Integer.parseInt(map22.get("EMAILID").toString());
										readDAO.deletecaogaofujian(id);
										readDAO.deletecaogao(id);
									}
									try {	
										boolean flag4=false;
										if(label_1.getText()==""){	
											List<Object> params3 = new ArrayList<>();
											params3.add(emailid1);
											flag4=true;
										}else{
											str=label_1.getText().trim().split(",");
											String namefile;
											System.out.println(label_1.getText()+"---------------=");
											Content.string=str;
											for(int i=0;i<str.length;i++){
												File file=new File(str[i]);
												List<Object> params2 = new ArrayList<>();
												params2.add(emailid1);
												int zuihou=str[i].lastIndexOf("\\");
												System.out.println(str[i].substring(str[i].lastIndexOf("\\")+1)+"----------=++++");
												namefile=str[i].substring(zuihou+1,str[i].length());
												System.out.println(namefile);
												params2.add(namefile);
												params2.add(file);
												flag4=sendDAO.accessory(params2);
												System.out.println("namefile"+namefile);
												
											}
										}
										if(flag4){
											
											MessageUtil.promt(shell, "温馨提示", "邮件发送成功！！！！！");
											text_toid.setText("");
											text_zhuti.setText("");
											text_content.setText("");
											label_1.setText("");
										}else{
											MessageUtil.promt(shell, "错误提示", "邮件发送失败！！！！！");
											List<Object> params4 = new ArrayList<>();
											System.out.println("===============");
											params4.add(emailid1);
											emailDAO.delfailEmail(params4);
										}
										Map<String , Object> maplaji=emailDAO.lajixiangExist(emailid1);
//										int z=maplaji.size();
//										System.out.println(z);
										if(maplaji!=null){
											boolean flaglaji=emailDAO.type_laji(emailid1);
											System.err.println(flaglaji);
										}else{
										}
										
									} catch (Exception e2) {
										// TODO: handle exception
										e2.printStackTrace();
									}
								}else{
									MessageUtil.promt(shell, "错误提示", "邮件发送失败！！！！！");
									List<Object> params4 = new ArrayList<>();
									System.out.println("===============");
									params4.add(emailid1);
									emailDAO.delfailEmail(params4);
								}
							} catch (FileNotFoundException e1) {
								MessageUtil.promt(shell, "错误提示", e1.getMessage());
								e1.printStackTrace();
							} catch (SQLException e1) {
//								MessageUtil.promt(shell, "错误提示", "主题或内容不能为空");
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
//						}
					}else{
						MessageUtil.promt(shell, "温馨提示", "请输入正确的邮箱号");
					}
					
				}
				
		});
		regularmail_receive.setBounds(7, 32, 53, 26);
		regularmail_receive.setText("\u53D1\u9001");
		
		Button button = new Button(com_write, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//存草稿箱
			@Override
			public void widgetSelected(SelectionEvent e) {
				//获得用户邮箱号
				String userid = text_toid.getText();
//				String title1 = text_zhuti.getText();
				String title1;
				if(text_zhuti.getText()==null){
					title1=null;
				}else{
					title1=text_zhuti.getText();
				}
				String content = text_content.getText();
				String fujianlujing=label_1.getText();
				//String email_date="20160520";
				List<Object> params = new ArrayList<>();
				boolean flag =false;
				params.add(fromid);
//				params.add(userid);
//				int uid=Integer.parseInt(userid);
//				int uid=Integer.parseInt(userid.substring(0,userid.lastIndexOf("@")));
//				params.add(uid);
				params.add(title1);
				params.add(content);
				params.add(fujianlujing);
				params.add(userid);
				try {
					flag =sendDAO.cuncaogaoEmail(params);
					String string2;
					//文件表插入
					if (flag) {
//						List<Object> params1=new ArrayList<Object>();
//						params1.add(userid);
//						Map<String, Object> map1=emailDAO.caogaofindEmailID(params1);
						Map<String, Object> map=emailDAO.DatecaogaofindEmailID();
						string2=map.get("EMAILID").toString();
						System.out.println(string2);
						int emailid=Integer.parseInt(string2);
						try {	
							boolean flag1=false;
							if(lujing==null){	
								List<Object> params3 = new ArrayList<>();
								params3.add(emailid);
								flag1=true;
							}else{
								str=lujing.trim().split(",");
								Content.string=str;
//								for(Map<String , Object> map1:listfilename){
//									strname=map1.get("FILESNAME").toString();
//								}
								for(int i=0;i<str.length;i++){
									File file=new File(str[i]);
									System.out.println(str[i]);
									//读取文件名
									List<Object> params5 = new ArrayList<>();
									params5.add(emailid);
//									for(int j=0;j<filename.length;j++){
//										params2.add(filename[j]);
//									}
									params5.add(filename[i]);
									params5.add(file);
									flag1=sendDAO.accessory(params5);
									System.out.println(flag1);
								}
							}
							if(flag1){
//								int emailid1=Integer.parseInt(string2);
//								List<Object> params4 = new ArrayList<>();
//								params4.add(emailid);
//								sendDAO(params4);
								MessageUtil.promt(shell, "温馨提示", "存储草稿成功");
								text_toid.setText("");
								text_zhuti.setText("");
								text_content.setText("");
								label_1.setText("");
							}else{
								MessageUtil.promt(shell, "错误提示", "存储草稿失败");
								List<Object> params4 = new ArrayList<>();
								System.out.println("===============");
								params4.add(emailid);
								emailDAO.delfailEmail(params4);
							}
							
						} catch (Exception e2) {
 							e2.printStackTrace();
						}
					}else{
						MessageUtil.promt(shell, "错误提示", "邮件发送失败！！！！！");
						
					}
				} catch (FileNotFoundException e1) {
					MessageUtil.promt(shell, "错误提示", e1.getMessage());
					e1.printStackTrace();
				} catch (SQLException e1) {
					MessageUtil.promt(shell, "错误提示", e1.getMessage());
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(61, 32, 64, 26);
		button.setText("\u5B58\u8349\u7A3F");
		
		Button button_1 = new Button(com_write, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//关闭写信
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_main;
				com_menu.layout();
				try{
					List<Map<String , Object>> list=emailDAO.weiduCount(fromid);
					if(null!=list&&list.size()>0){
						for(Map<String , Object> map:list){
							string=map.get("COUNT1").toString();
						}
						label_count.setText(string);
					}else{
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(125, 32, 53, 26);
		button_1.setText("\u5173\u95ED");
		
		Button btn_fujian = new Button(com_write, SWT.NONE);
		//添加附件
		btn_fujian.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fg=new FileDialog(shell.getShell(),SWT.MULTI);
				//设置对话框标题
				fg.setFilterPath("SystemRoot");//当前文件路径			
				fg.setFilterExtensions(new String[]{"*.txt","*.sql","*.java","*.*"});		
				selected =fg.open();
				if(selected==null){
					return;
				}else{
					int position=selected.lastIndexOf("\\");			
					String rootpath=selected.substring(0, position);
					System.out.println(rootpath);
					filename=fg.getFileNames();
					
					sb=new StringBuffer();
					for(int i=0;i<filename.length;i++){
						if(i==filename.length-1){
							sb.append(rootpath+"\\"+filename[i]);
						}else{
							sb.append(rootpath+"\\"+filename[i]+",");
						}
						lujing=sb.toString();
					}			
					System.out.println("=====>"+sb);
//					label_1.setText(filename.toString());
					label_1.setText(lujing);
					System.out.println(lujing);
				}
			}
		});
		btn_fujian.setBounds(68, 169, 80, 27);
		btn_fujian.setText("添加附件");
		
		label_1 = new Label(com_write, SWT.NONE);
		label_1.setBounds(154, 179, 533, 17);
		
		Tree tree = new Tree(com_write, SWT.BORDER);
		tree.addSelectionListener(new SelectionAdapter() {
			//点击联系人
			@Override
			public void widgetSelected(SelectionEvent e) {
				//获取具体点击的item
				TreeItem selectedItem=(TreeItem)e.item;
				//System.out.println(selectedItem.getText());
//				String code=(String) selectedItem.getData("part1");
				String username1=selectedItem.getText().toString();
				System.out.println(username1+"========");
				Map<String, Object> mapfinduserid;
//				text_toid.setText(selectedItem.getText().toString());
				try {
					mapfinduserid = emailDAO.finduserid(fromid,username1);
					if(mapfinduserid==null){
						Map<String , Object>mapusername=emailDAO.finduserid(username1);
						if(mapusername==null){
							text_toid.setText("");
						}else{
							text_toid.setText(mapusername.get("USERID").toString()+"@qq.com");
						}
					}else{
						if(mapfinduserid.get("PERSONID")==null){
							text_toid.setText("");
						}else{
							text_toid.setText(mapfinduserid.get("PERSONID").toString()+"@qq.com");
							System.out.println(mapfinduserid.get("PERSONID").toString());
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		tree.setBounds(716, 169, 176, 320);
		
		treeItemfriend = new TreeItem(tree, 0);
		treeItemfriend.setChecked(true);
		treeItemfriend.setText("我的好友");
		
		
		
		//群写信界面.jpg
		com_qun = new Composite(com_menu, SWT.NONE);
		com_qun.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/群写信界面.jpg"));
		
		Label label_putong = new Label(com_qun, SWT.NONE);
		//个人信界面.jpg
		label_putong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//label_putong.setBackgroundImage(SWTResourceManager.getImage(EmailMainTest1.class,"/images/个人信界面.jpg"));
				stackLayout.topControl=com_write;
				com_menu.layout();
			}
		});
		label_putong.setBounds(1, 1, 108, 26);
		
		Button button_2 = new Button(com_qun, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//群邮件发送
			@Override
			public void widgetSelected(SelectionEvent e) {
				//获得用户邮箱号
				String userid = text_quntoid.getText();
				String title1;
				if(text_qunzhuti.getText()==null){
					title1=null;
				}else{
					title1=text_qunzhuti.getText();
				}
				String content=text_quncontent.getText();
				int emailid1=0;
				//String email_date="20160520";
				//params.add(email_date);
				if(userid.matches("[0-9]+@(sina|qq|163)+(\\.(com|cn|org|edu|hk))")){
//					if(title1.matches("")||content.matches("")){
//						MessageUtil.promt(shell, "温馨提示", "您的主题或内容不能为空");
//					}else{
						boolean flag =false;
						boolean flag4=false;
						try {
							int uid=Integer.parseInt(userid.substring(0,userid.lastIndexOf("@")));
							List<Map<String , Object>> listQunUser=emailDAO.findQunUser(uid);
							Map<String , Object> mapexist=emailDAO.findisexist(fromid, uid);
							if(mapexist==null){
								MessageUtil.promt(shell, "提示", "您不是群成员");
								flag=false;
							}else{
								for(Map<String , Object> mapQunUser:listQunUser){
									Thread.sleep(500);
									String qunUser=mapQunUser.get("USERID").toString();
									int qunUser1=Integer.parseInt(qunUser);
									Map<String , Object> mapQunName=emailDAO.qunName(uid);
									String QunName=mapQunName.get("FLOCKNAME").toString();
									List<Object> params = new ArrayList<>();
									params.add(fromid);
									params.add(qunUser1);
									System.out.println(qunUser1);
									params.add(title1);
									params.add(content);
									params.add(QunName);
									params.add(uid);
									
									flag =sendDAO.sendqunEmail(params);
									//文件表插入
									if (flag) {
//										List<Object> params1=new ArrayList<Object>();
//										params1.add(userid);
//										Map<String, Object> map=emailDAO.findEmailID(params1);
										Map<String, Object> map1=emailDAO.DatecaogaofindEmailID();
//										System.out.println(map1.get("EMAILID").toString());
										String string2=map1.get("EMAILID").toString();
//										System.out.println(string2);
										emailid1=Integer.parseInt(string2);
//										System.out.println(emailid1);
										Map<String , Object> map22=readDAO.findemailexist(emailid1);
										if(map22!=null){
											int id=Integer.parseInt(map22.get("EMAILID").toString());
											readDAO.deletecaogaofujian(id);
											readDAO.deletecaogao(id);
										}
										try {	
											
											if(label_qunlujing.getText()==""){	
												List<Object> params3 = new ArrayList<>();
												params3.add(emailid1);
												flag4=true;
											}else{
												str=label_qunlujing.getText().trim().split(",");
												String namefile;
												System.out.println(label_qunlujing.getText()+"---------------=");
												Content.string=str;
												for(int i=0;i<str.length;i++){
													File file=new File(str[i]);
													List<Object> params2 = new ArrayList<>();
													params2.add(emailid1);
													int zuihou=str[i].lastIndexOf("\\");
													System.out.println(str[i].substring(str[i].lastIndexOf("\\")+1)+"----------=++++");
													namefile=str[i].substring(zuihou+1,str[i].length());
													System.out.println(namefile);
													params2.add(namefile);
													params2.add(file);
													flag4=sendDAO.accessory(params2);
													System.out.println("namefile"+namefile);
													
												}
											}
											
										} catch (Exception e2) {
											// TODO: handle exception
											e2.printStackTrace();
										}
									}else{
										MessageUtil.promt(shell, "错误提示", "邮件发送失败！！！！！");
										List<Object> params4 = new ArrayList<>();
										System.out.println("===============");
										params4.add(emailid1);
										emailDAO.delfailEmail(params4);
									}
									Map<String , Object> maplaji=emailDAO.lajixiangExist(emailid1);
//									int z=maplaji.size();
//									System.out.println(z);
									if(maplaji!=null){
										boolean flaglaji=emailDAO.type_laji(emailid1);
										System.err.println(flaglaji);
									}else{
									}
									
								}
								if(flag4){
									MessageUtil.promt(shell, "温馨提示", "邮件发送成功！！！！！");
									text_quntoid.setText("");
									text_qunzhuti.setText("");
									text_quncontent.setText("");
									label_qunlujing.setText("");
								}else{
									MessageUtil.promt(shell, "错误提示", "邮件发送失败！！！！！");
									List<Object> params4 = new ArrayList<>();
									System.out.println("===============");
									params4.add(emailid1);
									emailDAO.delfailEmail(params4);
								}
								
								
							}
							
							
						} catch (FileNotFoundException e1) {
							MessageUtil.promt(shell, "错误提示", e1.getMessage());
							e1.printStackTrace();
						} catch (SQLException e1) {
//							MessageUtil.promt(shell, "错误提示", "您不是群成员");
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}else{
					MessageUtil.promt(shell, "温馨提示", "请输入正确的邮箱号");
				}
			}
		});
		button_2.setText("\u53D1\u9001");
		button_2.setBounds(7, 32, 53, 26);
		
		Button button_3 = new Button(com_qun, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			//群存草稿
			@Override
			public void widgetSelected(SelectionEvent e) {
				//获得用户邮箱号
				String userid = text_quntoid.getText();
//				String title1 = text_zhuti.getText();
				String title1;
				if(text_qunzhuti.getText()==null){
					title1=null;
				}else{
					title1=text_qunzhuti.getText();
				}
				String content = text_quncontent.getText();
				String fujianlujing=label_qunlujing.getText();
				//String email_date="20160520";
				List<Object> params = new ArrayList<>();
				boolean flag =false;
				params.add(fromid);
//				params.add(userid);
//				int uid=Integer.parseInt(userid);
//				int uid=Integer.parseInt(userid.substring(0,userid.lastIndexOf("@")));
//				params.add(uid);
				params.add(title1);
				params.add(content);
				params.add(fujianlujing);
				params.add(userid);
				try {
					flag =sendDAO.cunquncaogaoEmail(params);
					String string2;
					//文件表插入
					if (flag) {
//						List<Object> params1=new ArrayList<Object>();
//						params1.add(userid);
//						Map<String, Object> map=emailDAO.caogaofindEmailID(params1);
						Map<String, Object> map=emailDAO.DatecaogaofindEmailID();
						string2=map.get("EMAILID").toString();
						System.out.println(string2);
						int emailid=Integer.parseInt(string2);
						try {	
							boolean flag1=false;
							if(lujing==null){	
								List<Object> params3 = new ArrayList<>();
								params3.add(emailid);
								flag1=true;
							}else{
								str=lujing.trim().split(",");
								Content.string=str;
//								for(Map<String , Object> map1:listfilename){
//									strname=map1.get("FILESNAME").toString();
//								}
								for(int i=0;i<str.length;i++){
									File file=new File(str[i]);
									System.out.println(str[i]);
									//读取文件名
									List<Object> params5 = new ArrayList<>();
									params5.add(emailid);
//									for(int j=0;j<filename.length;j++){
//										params2.add(filename[j]);
//									}
									params5.add(filename[i]);
									params5.add(file);
									flag1=sendDAO.accessory(params5);
									System.out.println(flag1);
								}
							}
							if(flag1){
//								int emailid1=Integer.parseInt(string2);
//								List<Object> params4 = new ArrayList<>();
//								params4.add(emailid);
//								sendDAO(params4);
								MessageUtil.promt(shell, "温馨提示", "存储草稿成功");
								text_quntoid.setText("");
								text_qunzhuti.setText("");
								text_quncontent.setText("");
								label_qunlujing.setText("");
							}else{
								MessageUtil.promt(shell, "错误提示", "存储草稿失败");
								List<Object> params4 = new ArrayList<>();
								System.out.println("===============");
								params4.add(emailid);
								emailDAO.delfailEmail(params4);
							}
							
						} catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
						}
					}else{
						MessageUtil.promt(shell, "错误提示", "邮件发送失败！！！！！");
						
					}
				} catch (FileNotFoundException e1) {
					MessageUtil.promt(shell, "错误提示", e1.getMessage());
					e1.printStackTrace();
				} catch (SQLException e1) {
					MessageUtil.promt(shell, "错误提示", e1.getMessage());
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_3.setText("\u5B58\u8349\u7A3F");
		button_3.setBounds(61, 32, 64, 26);
		
		Button button_4 = new Button(com_qun, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			//关闭
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_main;
				com_menu.layout();
				try{
					List<Map<String , Object>> list=emailDAO.weiduCount(fromid);
					if(null!=list&&list.size()>0){
						for(Map<String , Object> map:list){
							string=map.get("COUNT1").toString();
						}
						label_count.setText(string);
					}else{
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_4.setText("\u5173\u95ED");
		button_4.setBounds(125, 32, 53, 26);
		
		text_quntoid = new Text(com_qun, SWT.BORDER);
		text_quntoid.setBounds(68, 80, 624, 23);
		//自动补齐
		text_quntoid.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String temp = text_quntoid.getText();
				//输入的文本框末尾不能为空    且它的前面是空字符
				int len = temp.length();
				if(len<3){
					return ;
				}
				if((!(temp.charAt(len-1)+"").equals(" "))&&((temp.charAt(len-2)+"").equals(""))){
					text_quntoid.setSelection(len);
					str2= text_quntoid.getText();
					addNameTextFieldAssist1(text_quntoid);
				}
			}
		});
		addNameTextFieldAssist1(text_quntoid);		
		
		text_qunzhuti = new Text(com_qun, SWT.BORDER);
		text_qunzhuti.setBounds(68, 136, 624, 23);
		
		text_quncontent = new Text(com_qun, SWT.BORDER|SWT.WRAP|SWT.H_SCROLL|SWT.V_SCROLL|SWT.CANCEL|SWT.MULTI);
		text_quncontent.setBounds(61, 202, 624, 287);
		
		Button button_qunfujian = new Button(com_qun, SWT.NONE);
		button_qunfujian.addSelectionListener(new SelectionAdapter() {
			//群邮件添加附件
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fg=new FileDialog(shell.getShell(),SWT.MULTI);
				//设置对话框标题
				fg.setFilterPath("SystemRoot");//当前文件路径			
				fg.setFilterExtensions(new String[]{"*.txt","*.sql","*.java","*.*"});		
				selected =fg.open();
				if(selected==null){
					return;
				}else{
					int position=selected.lastIndexOf("\\");			
					String rootpath=selected.substring(0, position);
					System.out.println(rootpath);
					filename=fg.getFileNames();
					
					sb=new StringBuffer();
					for(int i=0;i<filename.length;i++){
						if(i==filename.length-1){
							sb.append(rootpath+"\\"+filename[i]);
						}else{
							sb.append(rootpath+"\\"+filename[i]+",");
						}
						lujing=sb.toString();
					}			
					System.out.println("=====>"+sb);
//					label_1.setText(filename.toString());
					label_qunlujing.setText(lujing);
					System.out.println(lujing);
				}
			}
		});
		button_qunfujian.setText("添加附件");
		button_qunfujian.setBounds(68, 169, 80, 27);
		
		label_qunlujing = new Label(com_qun, SWT.NONE);
		label_qunlujing.setBounds(154, 179, 538, 17);
		
		Tree tree_qun = new Tree(com_qun, SWT.BORDER);
		tree_qun.addSelectionListener(new SelectionAdapter() {
			//根据群号查询账号
			@Override
			public void widgetSelected(SelectionEvent e) {
				//获取具体点击的item
				TreeItem selectedItem=(TreeItem)e.item;
				//System.out.println(selectedItem.getText());
//				String code=(String) selectedItem.getData("part1");
				String username1=selectedItem.getText().toString();
				System.out.println(username1+"========");
				Map<String, Object> mapfindqunid;
//				text_toid.setText(selectedItem.getText().toString());
				try {
					mapfindqunid = emailDAO.findqunid(username1);
					if(mapfindqunid==null){
						text_quntoid.setText("");
					}else{
						text_quntoid.setText(mapfindqunid.get("FLOCKID").toString()+"@qq.com");
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tree_qun.setBounds(713, 103, 176, 386);
		
		treeItemqun = new TreeItem(tree_qun, 0);
		treeItemqun.setText("我的群");
		treeItemqun.setChecked(true);
		
		//收件箱界面
		com_shoujianxiang = new Composite(com_menu, SWT.NONE);
		com_shoujianxiang.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images3/收件箱界面.jpg"));
		
		table_shoujianxiang = new Table(com_shoujianxiang, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK|SWT.SINGLE);
		table_shoujianxiang.setBounds(10, 91, 881, 374);
		table_shoujianxiang.setHeaderVisible(true);
		table_shoujianxiang.setLinesVisible(true);
		
		
		TableColumn tblclmnNewColumn = new TableColumn(table_shoujianxiang, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("邮件号");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table_shoujianxiang, SWT.NONE);
		tblclmnNewColumn_1.setWidth(150);
		tblclmnNewColumn_1.setText("发件人");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table_shoujianxiang, SWT.NONE);
		tblclmnNewColumn_2.setWidth(350);
		tblclmnNewColumn_2.setText("主题");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table_shoujianxiang, SWT.NONE);
		tblclmnNewColumn_4.setWidth(200);
		tblclmnNewColumn_4.setText("时间");
		
		Menu menu = new Menu(table_shoujianxiang);
		table_shoujianxiang.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			//查看收件箱邮件
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_shoujianxiang.getItemCount();
				String string="";
				int sum=0;
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_shoujianxiang.getItem(i).getChecked()==true){
							System.out.println(table_shoujianxiang.getItem(i).getText(0));
							string=table_shoujianxiang.getItem(i).getText(0);
							sum++;
							
						}
					}
					if(sum==1&&string!=null){
						stackLayout.topControl=com_select;
						com_menu.layout();
						int count1=Integer.parseInt(string);
						Content.Emailidfujian=count1;
						readDAO.index_type(count1);
						
						java.util.List<Map<String, Object>> list=readDAO.readEmail(count1);
						System.out.println(count);
						if(null!=list&&list.size()>0){
							for(Map<String , Object> map:list){
								label_selectfrom.setText(map.get("FROMID").toString()+"@qq.com");	
								Content.toid=Integer.parseInt(map.get("FROMID").toString());
//								label_selectzhuti.setText(map.get("TITLE").toString()); 
								if(map.get("TITLE")==null){
									label_selectzhuti.setText("");
							    }else{
							    	label_selectzhuti.setText(map.get("TITLE").toString());
							    }
							    label_selectdate.setText(map.get("EMAIL_DATE").toString());
							    if(map.get("CON")==null){
							    	label_selectcontent.setText("");
							    }else{
							    	label_selectcontent.setText(map.get("CON").toString());
							    }
							    
							    System.out.println("=====");
							}
						}
						List<Object> params=new ArrayList<Object>();
						params.add(Content.Emailidfujian);
						List<Map<String , Object>> list1=readDAO.ReadFujian(params);
						table.removeAll();
						if(null!=list1&&list1.size()>0){
							for(Map<String , Object> map:list1){
								TableItem tableItem=new TableItem(table, SWT.None);
								tableItem.setText(new String[]{map.get("FILESID").toString(),map.get("FILESNAME").toString()});
//								String string=map.get("FILESNAME").toString();
							}
						}else{
							//MessageUtil.promt(shell, "温馨提示", "无学生信息");
						}
					}else{
						MessageUtil.promt(shell, "提示", "请选择您要查看的一封邮件");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem.setText("查看");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			//邮件删除收件箱邮件
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_shoujianxiang.getItemCount();
				String string="";
				int sum=0;
				boolean flag4=false;
				int shanchu=0;
				List<Object> shanchuid=new ArrayList<Object>();
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_shoujianxiang.getItem(i).getChecked()==true){
							System.out.println(table_shoujianxiang.getItem(i).getText(0));
							string=table_shoujianxiang.getItem(i).getText(0);
							sum++;
							shanchu=Integer.parseInt(string);
							flag4=emailDAO.shanchuEmail(shanchu);
							
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(flag4){
						label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_tishi.setText("删除成功");
						Thread.sleep(500);
						label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tishi.setText("");
						stackLayout.topControl=com_shoujianxiang;
						com_menu.layout();
						try{
							List<Object> params5=new ArrayList<Object>();
							params5.add(fromid);
							List<Map<String , Object>> list=emailDAO.shoujianxiang(params5);
							table_shoujianxiang.removeAll();

							if(null!=list&&list.size()>0){
								TableItem tableItem;
								for(Map<String , Object> map:list){
									tableItem=new TableItem(table_shoujianxiang, SWT.None);
									
									int emailid1=Integer.parseInt(map.get("EMAILID").toString());
									Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
									int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
									if(type==0){
										tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
										}
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
									}else{
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										}
									}
								}
							}else{
								//MessageUtil.promt(shell, "温馨提示", "无学生信息");
							}
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}else{
						label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_tishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(shanchu);
						Thread.sleep(1000);
						label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_1.setText("删除");
		
		TableColumn tableColumn_16 = new TableColumn(table_shoujianxiang, SWT.NONE);
		tableColumn_16.setWidth(75);
		tableColumn_16.setText("状态");
		
		label_tishi = new Label(com_shoujianxiang, SWT.CENTER);
		label_tishi.setBounds(340, 10, 157, 17);
		
		Button button_7 = new Button(com_shoujianxiang, SWT.NONE);
		button_7.addSelectionListener(new SelectionAdapter() {
			//彻底删除
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_shoujianxiang.getItemCount();
				String string="";
				int sum=0;
				boolean chedishanchuflag=false;
				int shanchu=0;
				List<Object> shanchuid=new ArrayList<Object>();
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_shoujianxiang.getItem(i).getChecked()==true){
							System.out.println(table_shoujianxiang.getItem(i).getText(0));
							string=table_shoujianxiang.getItem(i).getText(0);
							sum++;
							shanchu=Integer.parseInt(string);
							chedishanchuflag=emailDAO.chedidrop(shanchu);
							
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(chedishanchuflag){
						label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_tishi.setText("删除成功");
						Thread.sleep(500);
						label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tishi.setText("");
						stackLayout.topControl=com_shoujianxiang;
						com_menu.layout();
						try{
							List<Object> params5=new ArrayList<Object>();
							params5.add(fromid);
							List<Map<String , Object>> list=emailDAO.shoujianxiang(params5);
							table_shoujianxiang.removeAll();

							if(null!=list&&list.size()>0){
								TableItem tableItem;
								for(Map<String , Object> map:list){
									tableItem=new TableItem(table_shoujianxiang, SWT.None);
									
									int emailid1=Integer.parseInt(map.get("EMAILID").toString());
									Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
									int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
									if(type==0){
										tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
										}
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
									}else{
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										}
									}
								}
							}else{
								//MessageUtil.promt(shell, "温馨提示", "无学生信息");
							}
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}else{
						label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_tishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(shanchu);
						Thread.sleep(1000);
						label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_7.setBounds(66, 39, 75, 25);
		button_7.setText("彻底删除");
		
		Button button_14 = new Button(com_shoujianxiang, SWT.NONE);
		button_14.addSelectionListener(new SelectionAdapter() {
			//删除收件箱邮件
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_shoujianxiang.getItemCount();
				String string="";
				int sum=0;
				boolean flag4=false;
				int shanchu=0;
				List<Object> shanchuid=new ArrayList<Object>();
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_shoujianxiang.getItem(i).getChecked()==true){
							System.out.println(table_shoujianxiang.getItem(i).getText(0));
							string=table_shoujianxiang.getItem(i).getText(0);
							sum++;
							shanchu=Integer.parseInt(string);
							flag4=emailDAO.shanchuEmail(shanchu);
							
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(flag4){
						label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_tishi.setText("删除成功");
						Thread.sleep(500);
						label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tishi.setText("");
						stackLayout.topControl=com_shoujianxiang;
						com_menu.layout();
						try{
							List<Object> params5=new ArrayList<Object>();
							params5.add(fromid);
							List<Map<String , Object>> list=emailDAO.shoujianxiang(params5);
							table_shoujianxiang.removeAll();

							if(null!=list&&list.size()>0){
								TableItem tableItem;
								for(Map<String , Object> map:list){
									tableItem=new TableItem(table_shoujianxiang, SWT.None);
									
									int emailid1=Integer.parseInt(map.get("EMAILID").toString());
									Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
									int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
									if(type==0){
										tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
										}
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
									}else{
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										}
									}
								}
							}else{
								//MessageUtil.promt(shell, "温馨提示", "无学生信息");
							}
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}else{
						label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_tishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(shanchu);
						Thread.sleep(1000);
						label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_14.setBounds(10, 39, 56, 25);
		button_14.setText("删除");
		
		Button button_15 = new Button(com_shoujianxiang, SWT.NONE);
		button_15.addSelectionListener(new SelectionAdapter() {
			//个人邮件		 全部标记为已读
			@Override
			public void widgetSelected(SelectionEvent e) {
					try {
						int yidu=Integer.parseInt(Content.userID);
						boolean updateyidu=readDAO.updateyidu(yidu);
						if(updateyidu){
							stackLayout.topControl=com_shoujianxiang;
							com_menu.layout();
							try{
								List<Object> params5=new ArrayList<Object>();
								params5.add(fromid);
								List<Map<String , Object>> list=emailDAO.shoujianxiang(params5);
								table_shoujianxiang.removeAll();

								if(null!=list&&list.size()>0){
									TableItem tableItem;
									for(Map<String , Object> map:list){
										tableItem=new TableItem(table_shoujianxiang, SWT.None);
										
										int emailid1=Integer.parseInt(map.get("EMAILID").toString());
										Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
										int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
										if(type==0){
											tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
											if(map.get("TITLE")==null){
												tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
											}else{
												tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
											}
										}else{
											if(map.get("TITLE")==null){
												tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
											}else{
												tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
											}
//											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										}
									}
								}else{
									//MessageUtil.promt(shell, "温馨提示", "无学生信息");
								}
							}catch(SQLException e1){
								e1.printStackTrace();
							}catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				
			}
		});
		button_15.setBounds(143, 39, 96, 25);
		button_15.setText("全部标记为已读");
		
		com_qunshoujianxiang = new Composite(com_menu, SWT.NONE);
		com_qunshoujianxiang.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images3/群邮件收件箱界面.jpg"));
		
		table_qunshoujianxiang = new Table(com_qunshoujianxiang, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION | SWT.VIRTUAL);
		table_qunshoujianxiang.setLinesVisible(true);
		table_qunshoujianxiang.setHeaderVisible(true);
		table_qunshoujianxiang.setBounds(10, 91, 881, 374);
		
		TableColumn tableColumn_21 = new TableColumn(table_qunshoujianxiang, SWT.NONE);
		tableColumn_21.setWidth(100);
		tableColumn_21.setText("邮件号");
		
		TableColumn tableColumn_22 = new TableColumn(table_qunshoujianxiang, SWT.NONE);
		tableColumn_22.setWidth(150);
		tableColumn_22.setText("群");
		
		TableColumn tableColumn_23 = new TableColumn(table_qunshoujianxiang, SWT.NONE);
		tableColumn_23.setWidth(350);
		tableColumn_23.setText("主题");
		
		TableColumn tableColumn_24 = new TableColumn(table_qunshoujianxiang, SWT.NONE);
		tableColumn_24.setWidth(200);
		tableColumn_24.setText("时间");
		
		Menu menu_8 = new Menu(table_qunshoujianxiang);
		table_qunshoujianxiang.setMenu(menu_8);
		
		MenuItem menuItem_13 = new MenuItem(menu_8, SWT.NONE);
		menuItem_13.addSelectionListener(new SelectionAdapter() {
			//查看群邮件
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_qunshoujianxiang.getItemCount();
				String string="";
				int sum=0;
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_qunshoujianxiang.getItem(i).getChecked()==true){
							System.out.println(table_qunshoujianxiang.getItem(i).getText(0));
							string=table_qunshoujianxiang.getItem(i).getText(0);
							sum++;
						}
					}
					if(sum==1&&string!=null){
						stackLayout.topControl=com_selectqun;
						com_menu.layout();
						int count1=Integer.parseInt(string);
						Content.Emailidfujian=count1;
						readDAO.index_type(count1);
						
						java.util.List<Map<String, Object>> list=readDAO.readqunEmail(count1);
						System.out.println(count);
						if(null!=list&&list.size()>0){
							for(Map<String , Object> map:list){
								label_qunfromid.setText(map.get("FROMID").toString()+"@qq.com");
								label_qunid.setText(map.get("EMAILA4").toString());
								Content.quntoid=Integer.parseInt(map.get("EMAILA5").toString());
//								label_selectzhuti.setText(map.get("TITLE").toString()); 
								if(map.get("TITLE")==null){
									label_qunzhuti.setText("");
							    }else{
							    	label_qunzhuti.setText(map.get("TITLE").toString());
							    }
							    label_qundate.setText(map.get("EMAIL_DATE").toString());
							    if(map.get("CON")==null){
							    	label_quncontent.setText("");
							    }else{
							    	label_quncontent.setText(map.get("CON").toString());
							    }
							    
							    System.out.println("=====");
							}
						}
						List<Object> params=new ArrayList<Object>();
						params.add(Content.Emailidfujian);
						List<Map<String , Object>> list1=readDAO.ReadFujian(params);
						table_quntable.removeAll();
						if(null!=list1&&list1.size()>0){
							for(Map<String , Object> map:list1){
								TableItem tableItem=new TableItem(table_quntable, SWT.None);
								tableItem.setText(new String[]{map.get("FILESID").toString(),map.get("FILESNAME").toString()});
//								String string=map.get("FILESNAME").toString();
							}
						}else{
							//MessageUtil.promt(shell, "温馨提示", "无学生信息");
						}
					}else{
						MessageUtil.promt(shell, "提示", "请选择您要查看的一封邮件");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_13.setText("查看");
		
		MenuItem menuItem_14 = new MenuItem(menu_8, SWT.NONE);
		menuItem_14.addSelectionListener(new SelectionAdapter() {
			//群收件右键删除
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_qunshoujianxiang.getItemCount();
				String string="";
				int sum=0;
				boolean flag4=false;
				int shanchu=0;
				List<Object> shanchuid=new ArrayList<Object>();
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_qunshoujianxiang.getItem(i).getChecked()==true){
							System.out.println(table_qunshoujianxiang.getItem(i).getText(0));
							string=table_qunshoujianxiang.getItem(i).getText(0);
							sum++;
							shanchu=Integer.parseInt(string);
							flag4=emailDAO.shanchuEmail(shanchu);
							
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(flag4){
						label_quntishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_quntishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_quntishi.setText("删除成功");
						Thread.sleep(500);
						label_quntishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_quntishi.setText("");
						stackLayout.topControl=com_qunshoujianxiang;
						com_menu.layout();
						try{
							
							List<Object> params5=new ArrayList<Object>();
							params5.add(fromid);
							List<Map<String , Object>> listexist=emailDAO.FindUserQunid(params5);
							table_qunshoujianxiang.removeAll();
							for(Map<String , Object> mapqunexist:listexist){
								String qunid1=mapqunexist.get("FLOCKID").toString();
								int qunid2=Integer.parseInt(qunid1);
//								List<Object> params6=new ArrayList<Object>();
//								params6.add(qunid2);
//								params5.add(fromid);
								List<Map<String , Object>> list = emailDAO.Qunshoujian(qunid2);
								if(null!=list&&list.size()>0){
									TableItem tableItem;
									for(Map<String , Object> map:list){
										tableItem=new TableItem(table_qunshoujianxiang, SWT.None);
										//获取邮件ID
										int emailid1=Integer.parseInt(map.get("EMAILID").toString());
										//获取群ID
										Map<String, Object> mapqunid=emailDAO.qunID(emailid1);
										int flockid=Integer.parseInt(mapqunid.get("TOID").toString());
//										System.out.println(flockid);
										//获取群名称
										Map<String , Object> mapqunname=emailDAO.qunName(flockid);
										String qunName=mapqunname.get("FLOCKNAME").toString();
//										System.out.println(qunName);
										Map<String , Object> map1=readDAO.qunEmailreadtype(emailid1);
										int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
										if(type==0){
											tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
											if(map.get("TITLE")==null){
												tableItem.setText(new String[]{map.get("EMAILID").toString(),qunName,"(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
											}else{
												tableItem.setText(new String[]{map.get("EMAILID").toString(),qunName,map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
											}
//											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
										}else{
//											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
											if(map.get("TITLE")==null){
												tableItem.setText(new String[]{map.get("EMAILID").toString(),qunName,"(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
											}else{
												tableItem.setText(new String[]{map.get("EMAILID").toString(),qunName,map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
											}
										}
									}
								}else{
									//MessageUtil.promt(shell, "温馨提示", "无学生信息");
								}
							}
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}else{
						label_quntishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_quntishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_quntishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(shanchu);
						Thread.sleep(1000);
						label_quntishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_quntishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_14.setText("删除");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table_qunshoujianxiang, SWT.NONE);
		tblclmnNewColumn_6.setWidth(72);
		tblclmnNewColumn_6.setText("状态");
		
		Button button_18 = new Button(com_qunshoujianxiang, SWT.NONE);
		button_18.addSelectionListener(new SelectionAdapter() {
			//删除群邮件
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_qunshoujianxiang.getItemCount();
				String string="";
				int sum=0;
				boolean flag4=false;
				int shanchu=0;
				List<Object> shanchuid=new ArrayList<Object>();
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_qunshoujianxiang.getItem(i).getChecked()==true){
							System.out.println(table_qunshoujianxiang.getItem(i).getText(0));
							string=table_qunshoujianxiang.getItem(i).getText(0);
							sum++;
							shanchu=Integer.parseInt(string);
							flag4=emailDAO.shanchuEmail(shanchu);
							
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(flag4){
						label_quntishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_quntishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_quntishi.setText("删除成功");
						Thread.sleep(500);
						label_quntishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_quntishi.setText("");
						stackLayout.topControl=com_qunshoujianxiang;
						com_menu.layout();
						try{
							
							List<Object> params5=new ArrayList<Object>();
							params5.add(fromid);
							List<Map<String , Object>> listexist=emailDAO.FindUserQunid(params5);
							table_qunshoujianxiang.removeAll();
							for(Map<String , Object> mapqunexist:listexist){
								String qunid1=mapqunexist.get("FLOCKID").toString();
								int qunid2=Integer.parseInt(qunid1);
//								List<Object> params6=new ArrayList<Object>();
//								params6.add(qunid2);
//								params5.add(fromid);
								List<Map<String , Object>> list = emailDAO.Qunshoujian(qunid2);
								if(null!=list&&list.size()>0){
									TableItem tableItem;
									for(Map<String , Object> map:list){
										tableItem=new TableItem(table_qunshoujianxiang, SWT.None);
										//获取邮件ID
										int emailid1=Integer.parseInt(map.get("EMAILID").toString());
										//获取群ID
										Map<String, Object> mapqunid=emailDAO.qunID(emailid1);
										int flockid=Integer.parseInt(mapqunid.get("TOID").toString());
//										System.out.println(flockid);
										//获取群名称
										Map<String , Object> mapqunname=emailDAO.qunName(flockid);
										String qunName=mapqunname.get("FLOCKNAME").toString();
//										System.out.println(qunName);
										Map<String , Object> map1=readDAO.qunEmailreadtype(emailid1);
										int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
										if(type==0){
											tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
											if(map.get("TITLE")==null){
												tableItem.setText(new String[]{map.get("EMAILID").toString(),qunName,"(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
											}else{
												tableItem.setText(new String[]{map.get("EMAILID").toString(),qunName,map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
											}
//											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
										}else{
//											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
											if(map.get("TITLE")==null){
												tableItem.setText(new String[]{map.get("EMAILID").toString(),qunName,"(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
											}else{
												tableItem.setText(new String[]{map.get("EMAILID").toString(),qunName,map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
											}
										}
									}
								}else{
									//MessageUtil.promt(shell, "温馨提示", "无学生信息");
								}
							}
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}else{
						label_quntishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_quntishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_quntishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(shanchu);
						Thread.sleep(1000);
						label_quntishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_quntishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_18.setText("删除");
		button_18.setBounds(10, 39, 56, 25);
		
		Button button_25 = new Button(com_qunshoujianxiang, SWT.NONE);
		button_25.addSelectionListener(new SelectionAdapter() {
			//群邮件彻底删除
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_qunshoujianxiang.getItemCount();
				String string="";
				int sum=0;
				boolean chedishanchuflag=false;
				int shanchu=0;
				List<Object> shanchuid=new ArrayList<Object>();
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_qunshoujianxiang.getItem(i).getChecked()==true){
							System.out.println(table_qunshoujianxiang.getItem(i).getText(0));
							string=table_qunshoujianxiang.getItem(i).getText(0);
							sum++;
							shanchu=Integer.parseInt(string);
							chedishanchuflag=emailDAO.chedidrop(shanchu);
							
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(chedishanchuflag){
						label_quntishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_quntishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_quntishi.setText("删除成功");
						Thread.sleep(500);
						label_quntishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_quntishi.setText("");
						stackLayout.topControl=com_qunshoujianxiang;
						com_menu.layout();
						try{
							
							List<Object> params5=new ArrayList<Object>();
							params5.add(fromid);
							List<Map<String , Object>> listexist=emailDAO.FindUserQunid(params5);
							table_qunshoujianxiang.removeAll();
							for(Map<String , Object> mapqunexist:listexist){
								String qunid1=mapqunexist.get("FLOCKID").toString();
								int qunid2=Integer.parseInt(qunid1);
//								List<Object> params6=new ArrayList<Object>();
//								params6.add(qunid2);
//								params5.add(fromid);
								List<Map<String , Object>> list = emailDAO.Qunshoujian(qunid2);
								if(null!=list&&list.size()>0){
									TableItem tableItem;
									for(Map<String , Object> map:list){
										tableItem=new TableItem(table_qunshoujianxiang, SWT.None);
										//获取邮件ID
										int emailid1=Integer.parseInt(map.get("EMAILID").toString());
										//获取群ID
										Map<String, Object> mapqunid=emailDAO.qunID(emailid1);
										int flockid=Integer.parseInt(mapqunid.get("TOID").toString());
//										System.out.println(flockid);
										//获取群名称
										Map<String , Object> mapqunname=emailDAO.qunName(flockid);
										String qunName=mapqunname.get("FLOCKNAME").toString();
//										System.out.println(qunName);
										Map<String , Object> map1=readDAO.qunEmailreadtype(emailid1);
										int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
										if(type==0){
											tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
											if(map.get("TITLE")==null){
												tableItem.setText(new String[]{map.get("EMAILID").toString(),qunName,"(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
											}else{
												tableItem.setText(new String[]{map.get("EMAILID").toString(),qunName,map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
											}
//											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
										}else{
//											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
											if(map.get("TITLE")==null){
												tableItem.setText(new String[]{map.get("EMAILID").toString(),qunName,"(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
											}else{
												tableItem.setText(new String[]{map.get("EMAILID").toString(),qunName,map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
											}
										}
									}
								}else{
									//MessageUtil.promt(shell, "温馨提示", "无学生信息");
								}
							}
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}else{
						label_quntishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_quntishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_quntishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(shanchu);
						Thread.sleep(1000);
						label_quntishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_quntishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_25.setText("彻底删除");
		button_25.setBounds(66, 39, 75, 25);
		
		Button button_26 = new Button(com_qunshoujianxiang, SWT.NONE);
		button_26.addSelectionListener(new SelectionAdapter() {
			//群全部标记为已读
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					int yidu=Integer.parseInt(Content.userID);
					boolean updateyidu=readDAO.updatequnyidu(yidu);
					if(updateyidu){
						stackLayout.topControl=com_qunshoujianxiang;
						com_menu.layout();
						try{
							List<Object> params5=new ArrayList<Object>();
							params5.add(fromid);
							List<Map<String , Object>> list=emailDAO.Qunshoujian(fromid);
							table_qunshoujianxiang.removeAll();
							if(null!=list&&list.size()>0){
								TableItem tableItem;
								for(Map<String , Object> map:list){
									tableItem=new TableItem(table_qunshoujianxiang, SWT.None);
									int emailid1=Integer.parseInt(map.get("EMAILID").toString());
									Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
									int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
									if(type==0){
										tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA4").toString(),"(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA4").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
										}
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
									}else{
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA4").toString(),"(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA4").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										}
									}
								}
							}else{
								//MessageUtil.promt(shell, "温馨提示", "无学生信息");
							}
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		button_26.setText("全部标记为已读");
		button_26.setBounds(143, 39, 96, 25);
		
		label_quntishi = new Label(com_qunshoujianxiang, SWT.CENTER);
		label_quntishi.setBounds(340, 10, 157, 17);
		
		com_caogaoxiang = new Composite(com_menu, SWT.NONE);
		com_caogaoxiang.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images3/草稿箱.jpg"));
		
		table_caogaoxiang = new Table(com_caogaoxiang, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL|SWT.CHECK);
		table_caogaoxiang.setLinesVisible(true);
		table_caogaoxiang.setHeaderVisible(true);
		table_caogaoxiang.setBounds(10, 91, 881, 374);
		
		TableColumn tableColumn = new TableColumn(table_caogaoxiang, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("邮件号");
		
		TableColumn tableColumn_1 = new TableColumn(table_caogaoxiang, SWT.NONE);
		tableColumn_1.setWidth(150);
		tableColumn_1.setText("收件人");
		
		TableColumn tableColumn_2 = new TableColumn(table_caogaoxiang, SWT.NONE);
		tableColumn_2.setWidth(350);
		tableColumn_2.setText("主题");
		
		TableColumn tableColumn_3 = new TableColumn(table_caogaoxiang, SWT.NONE);
		tableColumn_3.setWidth(275);
		tableColumn_3.setText("时间");
		
		Menu menu_1 = new Menu(table_caogaoxiang);
		table_caogaoxiang.setMenu(menu_1);
		
		MenuItem menuItem_2 = new MenuItem(menu_1, SWT.NONE);
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			//查看草稿箱邮件
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_caogaoxiang.getItemCount();
				String string="";
				int sum=0;
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_caogaoxiang.getItem(i).getChecked()==true){
							System.out.println(table_caogaoxiang.getItem(i).getText(0));
							string=table_caogaoxiang.getItem(i).getText(0);
							sum++;
						}
					}
					if(sum==1&&string!=null){
						stackLayout.topControl=com_write;
						com_menu.layout();
						int count1=Integer.parseInt(string);
						Content.caogaoxiangEmailid=count1;
						Map<String, Object> mapchakan=readDAO.readcaogaoEmail(Integer.parseInt(string));
						System.out.println(count);
						System.out.println(mapchakan);
						if(mapchakan!=null){
							if(mapchakan.get("EMAILA2")==null){
								text_toid.setText("");
							}else{
								System.out.println(mapchakan.get("EMAILA2").toString());
								text_toid.setText(mapchakan.get("EMAILA2").toString()); 
								
							}
							if(mapchakan.get("TITLE")==null){
								text_zhuti.setText("");
							}else{
								text_zhuti.setText(mapchakan.get("TITLE").toString());	
							}
							
							if(mapchakan.get("CON")==null){
							   	text_content.setText("");
							}else{
								text_content.setText(mapchakan.get("CON").toString());
							}
//							   label_1.setText(map.get("EMAILA1").toString());
							if(mapchakan.get("EMAILA1")==null){
							   	 label_1.setText("");
							}else{
							     label_1.setText(mapchakan.get("EMAILA1").toString());
							}
							System.out.println("=====");
						}
					}else{
						MessageUtil.promt(shell, "提示", "请选择您要查看的一封邮件");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_2.setText("查看");
		
		MenuItem menuItem_3 = new MenuItem(menu_1, SWT.NONE);
		menuItem_3.addSelectionListener(new SelectionAdapter() {
			//草稿箱右键删除
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_caogaoxiang.getItemCount();
				String string="";
				int sum=0;
				boolean shanchuyifasongflag=false;
				int yifasong=0;
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_caogaoxiang.getItem(i).getChecked()==true){
							System.out.println(table_caogaoxiang.getItem(i).getText(0));
							string=table_caogaoxiang.getItem(i).getText(0);
							sum++;
							yifasong=Integer.parseInt(string);
							
							shanchuyifasongflag=emailDAO.deletecaogaoxiang(yifasong);
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(shanchuyifasongflag){
						label_caogaoxiangtishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_caogaoxiangtishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_caogaoxiangtishi.setText("删除成功");
						Thread.sleep(500);
						label_caogaoxiangtishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_caogaoxiangtishi.setText("");
						stackLayout.topControl=com_caogaoxiang;
						com_menu.layout();
						try{
							List<Object> params5=new ArrayList<Object>();
							params5.add(fromid);
							List<Map<String , Object>> list=emailDAO.caogaoxiang(params5);
							table_caogaoxiang.removeAll();
							if(null!=list&&list.size()>0){
								for(Map<String , Object> map:list){
									TableItem tableItem=new TableItem(table_caogaoxiang, SWT.None);
//									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
									if(map.get("EMAILA2")==null&&map.get("TITLE")==null){
										tableItem.setText(new String[]{map.get("EMAILID").toString(),"","(无主题)",map.get("EMAIL_DATE").toString()});
									}
									if(map.get("EMAILA2")==null&&map.get("TITLE")!=null){
										tableItem.setText(new String[]{map.get("EMAILID").toString(),"",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
									}
									if(map.get("EMAILA2")!=null&&map.get("TITLE")==null){
										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA2").toString(),"(无主题)",map.get("EMAIL_DATE").toString()});
									}
									if(map.get("EMAILA2")!=null&&map.get("TITLE")!=null){
										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA2").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
									}
								}
							}else{
								//MessageUtil.promt(shell, "温馨提示", "无学生信息");
							}
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}else{
						label_caogaoxiangtishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_caogaoxiangtishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_caogaoxiangtishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(yifasong);
						Thread.sleep(1000);
						label_caogaoxiangtishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_caogaoxiangtishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_3.setText("删除");
		
		label_caogaoxiangtishi = new Label(com_caogaoxiang, SWT.CENTER);
		label_caogaoxiangtishi.setBounds(340, 10, 157, 17);
		
		Button button_17 = new Button(com_caogaoxiang, SWT.NONE);
		button_17.addSelectionListener(new SelectionAdapter() {
			//删除草稿箱邮件
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_caogaoxiang.getItemCount();
				String string="";
				int sum=0;
				boolean shanchuyifasongflag=false;
				int yifasong=0;
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_caogaoxiang.getItem(i).getChecked()==true){
							System.out.println(table_caogaoxiang.getItem(i).getText(0));
							string=table_caogaoxiang.getItem(i).getText(0);
							sum++;
							yifasong=Integer.parseInt(string);
							
							shanchuyifasongflag=emailDAO.deletecaogaoxiang(yifasong);
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(shanchuyifasongflag){
						label_caogaoxiangtishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_caogaoxiangtishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_caogaoxiangtishi.setText("删除成功");
						Thread.sleep(500);
						label_caogaoxiangtishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_caogaoxiangtishi.setText("");
						stackLayout.topControl=com_caogaoxiang;
						com_menu.layout();
						try{
							List<Object> params5=new ArrayList<Object>();
							params5.add(fromid);
							List<Map<String , Object>> list=emailDAO.caogaoxiang(params5);
							table_caogaoxiang.removeAll();
							if(null!=list&&list.size()>0){
								for(Map<String , Object> map:list){
									TableItem tableItem=new TableItem(table_caogaoxiang, SWT.None);
//									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
									if(map.get("EMAILA2")==null&&map.get("TITLE")==null){
										tableItem.setText(new String[]{map.get("EMAILID").toString(),"","(无主题)",map.get("EMAIL_DATE").toString()});
									}
									if(map.get("EMAILA2")==null&&map.get("TITLE")!=null){
										tableItem.setText(new String[]{map.get("EMAILID").toString(),"",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
									}
									if(map.get("EMAILA2")!=null&&map.get("TITLE")==null){
										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA2").toString(),"(无主题)",map.get("EMAIL_DATE").toString()});
									}
									if(map.get("EMAILA2")!=null&&map.get("TITLE")!=null){
										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA2").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
									}
								}
							}else{
								//MessageUtil.promt(shell, "温馨提示", "无学生信息");
							}
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}else{
						label_caogaoxiangtishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_caogaoxiangtishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_caogaoxiangtishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(yifasong);
						Thread.sleep(1000);
						label_caogaoxiangtishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_caogaoxiangtishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_17.setText("彻底删除");
		button_17.setBounds(10, 39, 75, 25);
		
		com_yifasong = new Composite(com_menu, SWT.NONE);
		com_yifasong.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images3/已发送界面.jpg"));
		
		table_yifasong = new Table(com_yifasong, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL|SWT.CHECK);
		table_yifasong.setLinesVisible(true);
		table_yifasong.setHeaderVisible(true);
		table_yifasong.setBounds(10, 91, 881, 374);
		
		TableColumn tableColumn_12 = new TableColumn(table_yifasong, SWT.NONE);
		tableColumn_12.setWidth(100);
		tableColumn_12.setText("邮件号");
		
		TableColumn tableColumn_13 = new TableColumn(table_yifasong, SWT.NONE);
		tableColumn_13.setWidth(150);
		tableColumn_13.setText("收件人");
		
		TableColumn tableColumn_14 = new TableColumn(table_yifasong, SWT.NONE);
		tableColumn_14.setWidth(350);
		tableColumn_14.setText("主题");
		
		TableColumn tableColumn_15 = new TableColumn(table_yifasong, SWT.NONE);
		tableColumn_15.setWidth(275);
		tableColumn_15.setText("时间");
		
		Menu menu_4 = new Menu(table_yifasong);
		table_yifasong.setMenu(menu_4);
		
		MenuItem menuItem_8 = new MenuItem(menu_4, SWT.NONE);
		menuItem_8.addSelectionListener(new SelectionAdapter() {
			//查看已发送邮件
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_yifasong.getItemCount();
				String string="";
				int sum=0;
				try {
					
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_yifasong.getItem(i).getChecked()==true){
							System.out.println(table_yifasong.getItem(i).getText(0));
							string=table_yifasong.getItem(i).getText(0);
							sum++;
						}
					}
					if(sum==1&&string!=null){
						stackLayout.topControl=com_selectyifasong;
						com_menu.layout();
						int count1=Integer.parseInt(string);
						
						java.util.List<Map<String, Object>> list=readDAO.readyifasongEmail(count1);
						System.out.println(count);
						if(null!=list&&list.size()>0){
							for(Map<String , Object> map:list){
								label_selecttoid.setText(map.get("TOID").toString()+"@qq.com");
								Content.yifasongtoid=map.get("TOID").toString();
								if(map.get("TITLE")==null){
									label_selectzhuti2.setText("");
							    }else{
							    	label_selectzhuti2.setText(map.get("TITLE").toString()); 
							    }
							    label_selectdate2.setText(map.get("EMAIL_DATE").toString());
							    if(map.get("CON")==null){
							    	label_selectcontent2.setText("");
							    }else{
							    	label_selectcontent2.setText(map.get("CON").toString());
							    }
							    System.out.println("=====");
							}
						}
						List<Object> paramsyifasong=new ArrayList<Object>();
						paramsyifasong.add(count1);
//						System.out.println(Content.Emailidfujian+"=================");
						List<Map<String , Object>> listyifasong=readDAO.ReadFujian(paramsyifasong);
						table_1.removeAll();
						if(null!=listyifasong&&listyifasong.size()>0){
							for(Map<String , Object> map:listyifasong){
								TableItem tableItem=new TableItem(table_1, SWT.None);
								tableItem.setText(new String[]{map.get("FILESID").toString(),map.get("FILESNAME").toString()});
//								String string=map.get("FILESNAME").toString();
							}
						}else{
							//MessageUtil.promt(shell, "温馨提示", "无学生信息");
						}
					}else{
						MessageUtil.promt(shell, "提示", "请选择您要查看的一封邮件");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		menuItem_8.setText("查看");
		
		MenuItem menuItem_9 = new MenuItem(menu_4, SWT.NONE);
		menuItem_9.addSelectionListener(new SelectionAdapter() {
			//已发送邮件的删除
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_yifasong.getItemCount();
				String string="";
				int sum=0;
				boolean shanchuyifasongflag=false;
				int yifasong=0;
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_yifasong.getItem(i).getChecked()==true){
							System.out.println(table_yifasong.getItem(i).getText(0));
							string=table_yifasong.getItem(i).getText(0);
							sum++;
							yifasong=Integer.parseInt(string);
							shanchuyifasongflag=emailDAO.dropyifasong(yifasong);
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(shanchuyifasongflag){
						label_yifasongtishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yifasongtishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_yifasongtishi.setText("删除成功");
						Thread.sleep(500);
						label_yifasongtishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yifasongtishi.setText("");
						stackLayout.topControl=com_yifasong;
						com_menu.layout();
						try {
							List<Object> yifasongid=new ArrayList<Object>();
							yifasongid.add(fromid);
							List<Map<String, Object>> list=emailDAO.yifasong(yifasongid);
							table_yifasong.removeAll();
							if(null!=list&&list.size()>0){
//								for(Map<String, Object> map:list){
//									TableItem tableItem2=new TableItem(table_yifasong,SWT.NONE);
//									tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
//								}
								for(Map<String, Object> map:list){
									TableItem tableItem2=new TableItem(table_yifasong, SWT.NONE);
									if(map.get("TITLE")==null){
										tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString()});
									}else{
										tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
									}
//									tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
									}
							}else{
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						label_yifasongtishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yifasongtishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_yifasongtishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(yifasong);
						Thread.sleep(1000);
						label_yifasongtishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yifasongtishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_9.setText("删除");
		
		Button button_10 = new Button(com_yifasong, SWT.NONE);
		button_10.addSelectionListener(new SelectionAdapter() {
			//已发送删除
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_yifasong.getItemCount();
				String string="";
				int sum=0;
				boolean shanchuyifasongflag=false;
				int yifasong=0;
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_yifasong.getItem(i).getChecked()==true){
							System.out.println(table_yifasong.getItem(i).getText(0));
							string=table_yifasong.getItem(i).getText(0);
							sum++;
							yifasong=Integer.parseInt(string);
							shanchuyifasongflag=emailDAO.dropyifasong(yifasong);
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(shanchuyifasongflag){
						label_yifasongtishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yifasongtishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_yifasongtishi.setText("删除成功");
						Thread.sleep(500);
						label_yifasongtishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yifasongtishi.setText("");
						stackLayout.topControl=com_yifasong;
						com_menu.layout();
						try {
							List<Object> yifasongid=new ArrayList<Object>();
							yifasongid.add(fromid);
							List<Map<String, Object>> list=emailDAO.yifasong(yifasongid);
							table_yifasong.removeAll();
							if(null!=list&&list.size()>0){
//								for(Map<String, Object> map:list){
//									TableItem tableItem2=new TableItem(table_yifasong,SWT.NONE);
//									tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
//								}
								for(Map<String, Object> map:list){
									TableItem tableItem2=new TableItem(table_yifasong, SWT.NONE);
									if(map.get("TITLE")==null){
										tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString()});
									}else{
										tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
									}
//									tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
									}
							}else{
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						label_yifasongtishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yifasongtishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_yifasongtishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(yifasong);
						Thread.sleep(1000);
						label_yifasongtishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yifasongtishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_10.setText("彻底删除");
		button_10.setBounds(10, 39, 75, 25);
		
		label_yifasongtishi = new Label(com_yifasong, SWT.CENTER);
		label_yifasongtishi.setBounds(340, 10, 157, 17);
		
		com_yishanchu = new Composite(com_menu, SWT.NONE);
		com_yishanchu.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images3/已删除界面.jpg"));
		
		table_yishanchu = new Table(com_yishanchu, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL|SWT.CHECK);
		table_yishanchu.setLinesVisible(true);
		table_yishanchu.setHeaderVisible(true);
		table_yishanchu.setBounds(10, 91, 881, 374);
		
		TableColumn tableColumn_4 = new TableColumn(table_yishanchu, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("邮件号");
		
		TableColumn tableColumn_5 = new TableColumn(table_yishanchu, SWT.NONE);
		tableColumn_5.setWidth(150);
		tableColumn_5.setText("发件人");
		
		TableColumn tableColumn_6 = new TableColumn(table_yishanchu, SWT.NONE);
		tableColumn_6.setWidth(350);
		tableColumn_6.setText("主题");
		
		TableColumn tableColumn_7 = new TableColumn(table_yishanchu, SWT.NONE);
		tableColumn_7.setWidth(276);
		tableColumn_7.setText("时间");
		
		Menu menu_2 = new Menu(table_yishanchu);
		table_yishanchu.setMenu(menu_2);
		
		MenuItem menuItem_4 = new MenuItem(menu_2, SWT.NONE);
		menuItem_4.addSelectionListener(new SelectionAdapter() {
			//查看已删除邮件
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_yishanchu.getItemCount();
				String string="";
				int sum=0;
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_yishanchu.getItem(i).getChecked()==true){
							System.out.println(table_yishanchu.getItem(i).getText(0));
							string=table_yishanchu.getItem(i).getText(0);
							sum++;
						}
					}
					if(sum==1&&string!=null){
						stackLayout.topControl=com_selectyishanchu;
						com_menu.layout();
						
						int count1=Integer.parseInt(string);
						
						readDAO.index_type(count1);
						java.util.List<Map<String, Object>> list=readDAO.readEmail(count1);
						System.out.println(count);
						if(null!=list&&list.size()>0){
							for(Map<String , Object> map:list){
								label_yishanchutoid.setText(map.get("FROMID").toString()+"@qq.com");	
								Content.toid=Integer.parseInt(map.get("FROMID").toString());
								Content.lajiid=map.get("FROMID").toString();
								if(map.get("TITLE")==null){
									label_yishanchuzhuti.setText("");
								}else{
									label_yishanchuzhuti.setText(map.get("TITLE").toString()); 
								}
								
							    label_yishanchudate.setText(map.get("EMAIL_DATE").toString());
							    if(map.get("CON")==null){
							    	label_yishanchucontent.setText("");
							    }else{
							    	label_yishanchucontent.setText(map.get("CON").toString());
							    }
							    
							    System.out.println("=====");
							}
						}
						List<Object> params=new ArrayList<Object>();
						params.add(Content.Emailidfujian);
						List<Map<String , Object>> list1=readDAO.ReadFujian(params);
						table_yishanchu1.removeAll();
						if(null!=list1&&list1.size()>0){
							for(Map<String , Object> map:list1){
								TableItem tableItem=new TableItem(table_yishanchu1, SWT.None);
								tableItem.setText(new String[]{map.get("FILESID").toString(),map.get("FILESNAME").toString()});
//								String string=map.get("FILESNAME").toString();
							}
						}else{
							//MessageUtil.promt(shell, "温馨提示", "无学生信息");
						}
					}else{
						MessageUtil.promt(shell, "提示", "请选择您要查看的一封邮件");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_4.setText("查看");
		
		MenuItem menuItem_5 = new MenuItem(menu_2, SWT.NONE);
		menuItem_5.addSelectionListener(new SelectionAdapter() {
			//已删除  	彻底删除
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_yishanchu.getItemCount();
				String string="";
				int sum=0;
				boolean chedishanchuflag=false;
				int shanchu=0;
				List<Object> shanchuid=new ArrayList<Object>();
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_yishanchu.getItem(i).getChecked()==true){
							System.out.println(table_yishanchu.getItem(i).getText(0));
							string=table_yishanchu.getItem(i).getText(0);
							sum++;
							shanchu=Integer.parseInt(string);
							chedishanchuflag=emailDAO.chedidrop(shanchu);
							
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(chedishanchuflag){
						label_yishanchutishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yishanchutishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_yishanchutishi.setText("删除成功");
						Thread.sleep(500);
						label_yishanchutishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yishanchutishi.setText("");
						stackLayout.topControl=com_yishanchu;
						com_menu.layout();
						try {
							List<Object> params8=new ArrayList<Object>();
							params8.add(fromid);
							List<Map<String , Object>> list8=emailDAO.yishanchu(params8);
							table_yishanchu.removeAll();
							if(null!=list8&&list8.size()>0){
								for(Map<String, Object> map:list8){
									TableItem tableItem2=new TableItem(table_yishanchu, SWT.NONE);
									if(map.get("TITLE")==null){
										tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString()});
										}else{
											tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
										}
//									tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
									}
							}else{
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						label_yishanchutishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yishanchutishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_yishanchutishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(shanchu);
						Thread.sleep(1000);
						label_yishanchutishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yishanchutishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_5.setText("彻底删除");
		
		Button button_16 = new Button(com_yishanchu, SWT.NONE);
		button_16.addSelectionListener(new SelectionAdapter() {
			//已删除中 彻底删除
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_yishanchu.getItemCount();
				String string="";
				int sum=0;
				boolean chedishanchuflag=false;
				int shanchu=0;
				List<Object> shanchuid=new ArrayList<Object>();
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_yishanchu.getItem(i).getChecked()==true){
							System.out.println(table_yishanchu.getItem(i).getText(0));
							string=table_yishanchu.getItem(i).getText(0);
							sum++;
							shanchu=Integer.parseInt(string);
							chedishanchuflag=emailDAO.chedidrop(shanchu);
							
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(chedishanchuflag){
						label_yishanchutishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yishanchutishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_yishanchutishi.setText("删除成功");
						Thread.sleep(500);
						label_yishanchutishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yishanchutishi.setText("");
						stackLayout.topControl=com_yishanchu;
						com_menu.layout();
						try {
							List<Object> params8=new ArrayList<Object>();
							params8.add(fromid);
							List<Map<String , Object>> list8=emailDAO.yishanchu(params8);
							table_yishanchu.removeAll();
							if(null!=list8&&list8.size()>0){
								for(Map<String, Object> map:list8){
									TableItem tableItem2=new TableItem(table_yishanchu, SWT.NONE);
									if(map.get("TITLE")==null){
										tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","无主题",map.get("EMAIL_DATE").toString()});
										}else{
											tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
										}
//									tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
									}
							}else{
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						label_yishanchutishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yishanchutishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_yishanchutishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(shanchu);
						Thread.sleep(1000);
						label_yishanchutishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_yishanchutishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_16.setText("彻底删除");
		button_16.setBounds(10, 39, 75, 25);
		
		label_yishanchutishi = new Label(com_yishanchu, SWT.CENTER);
		label_yishanchutishi.setBounds(340, 10, 157, 17);
		
		com_lajixiang = new Composite(com_menu, SWT.NONE);
		com_lajixiang.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images3/垃圾箱界面.jpg"));
		
		table_lajixiang = new Table(com_lajixiang, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL|SWT.CHECK);
		table_lajixiang.setLinesVisible(true);
		table_lajixiang.setHeaderVisible(true);
		table_lajixiang.setBounds(10, 91, 881, 374);
		
		TableColumn tableColumn_8 = new TableColumn(table_lajixiang, SWT.NONE);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("邮件号");
		
		TableColumn tableColumn_9 = new TableColumn(table_lajixiang, SWT.NONE);
		tableColumn_9.setWidth(150);
		tableColumn_9.setText("发件人");
		
		TableColumn tableColumn_10 = new TableColumn(table_lajixiang, SWT.NONE);
		tableColumn_10.setWidth(350);
		tableColumn_10.setText("主题");
		
		TableColumn tableColumn_11 = new TableColumn(table_lajixiang, SWT.NONE);
		tableColumn_11.setWidth(200);
		tableColumn_11.setText("时间");
		
		Menu menu_3 = new Menu(table_lajixiang);
		table_lajixiang.setMenu(menu_3);
		
		MenuItem menuItem_6 = new MenuItem(menu_3, SWT.NONE);
		menuItem_6.addSelectionListener(new SelectionAdapter() {
			//查看垃圾邮件
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_lajixiang.getItemCount();
				String string="";
				int sum=0;
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_lajixiang.getItem(i).getChecked()==true){
							System.out.println(table_lajixiang.getItem(i).getText(0));
							string=table_lajixiang.getItem(i).getText(0);
							sum++;
							
						}
					}
					if(sum==1&&string!=null){
						stackLayout.topControl=com_selectlaji;
						com_menu.layout();
						int count1=Integer.parseInt(string);
						Content.Emailidfujian=count1;
						readDAO.index_type(count1);
						
						java.util.List<Map<String, Object>> list=readDAO.readEmail(count1);
						System.out.println(count);
						if(null!=list&&list.size()>0){
							for(Map<String , Object> map:list){
								label_selectlajifrom.setText(map.get("FROMID").toString()+"@qq.com");	
								Content.toid=Integer.parseInt(map.get("FROMID").toString());
								Content.lajiid=map.get("FROMID").toString();
//								label_selectzhuti.setText(map.get("TITLE").toString()); 
								if(map.get("TITLE")==null){
									label_selectlajititle.setText("");
							    }else{
							    	label_selectlajititle.setText(map.get("TITLE").toString());
							    }
							    label_selectlajidate.setText(map.get("EMAIL_DATE").toString());
							    if(map.get("CON")==null){
							    	label_selectlajicontent.setText("");
							    }else{
							    	label_selectlajicontent.setText(map.get("CON").toString());
							    }
							    
							    System.out.println("=====");
							}
						}
						List<Object> params=new ArrayList<Object>();
						params.add(Content.Emailidfujian);
						List<Map<String , Object>> list1=readDAO.ReadFujian(params);
						table_selectlajitable.removeAll();
						if(null!=list1&&list1.size()>0){
							for(Map<String , Object> map:list1){
								TableItem tableItem=new TableItem(table_selectlajitable, SWT.None);
								tableItem.setText(new String[]{map.get("FILESID").toString(),map.get("FILESNAME").toString()});
//								String string=map.get("FILESNAME").toString();
							}
						}else{
							//MessageUtil.promt(shell, "温馨提示", "无学生信息");
						}
					}else{
						MessageUtil.promt(shell, "提示", "请选择您要查看的一封邮件");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_6.setText("查看");
		
		MenuItem menuItem_7 = new MenuItem(menu_3, SWT.NONE);
		menuItem_7.addSelectionListener(new SelectionAdapter() {
			//垃圾邮件 右键删除
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_lajixiang.getItemCount();
				String string="";
				int sum=0;
				boolean flag4=false;
				int shanchu=0;
				List<Object> shanchuid=new ArrayList<Object>();
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_lajixiang.getItem(i).getChecked()==true){
							System.out.println(table_lajixiang.getItem(i).getText(0));
							string=table_lajixiang.getItem(i).getText(0);
							sum++;
							shanchu=Integer.parseInt(string);
							flag4=emailDAO.shanchuEmail(shanchu);
							
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(flag4){
						label_lajixiangtishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_lajixiangtishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_lajixiangtishi.setText("删除成功");
						Thread.sleep(500);
						label_lajixiangtishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_lajixiangtishi.setText("");
						stackLayout.topControl=com_lajixiang;
						com_menu.layout();
						try{
							List<Object> params5=new ArrayList<Object>();
							params5.add(fromid);
							List<Map<String , Object>> list=emailDAO.lajixiangEmail(params5);
							table_lajixiang.removeAll();

							if(null!=list&&list.size()>0){
								TableItem tableItem;
								for(Map<String , Object> map:list){
									tableItem=new TableItem(table_lajixiang, SWT.None);
									
									int emailid1=Integer.parseInt(map.get("EMAILID").toString());
									Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
									int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
									if(type==0){
										tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
										}
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
									}else{
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										}
									}
								}
							}else{
								//MessageUtil.promt(shell, "温馨提示", "无学生信息");
							}
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}else{
						label_lajixiangtishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_lajixiangtishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_lajixiangtishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(shanchu);
						Thread.sleep(1000);
						label_lajixiangtishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_lajixiangtishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuItem_7.setText("删除");
		
		TableColumn tableColumn_31 = new TableColumn(table_lajixiang, SWT.NONE);
		tableColumn_31.setWidth(74);
		tableColumn_31.setText("状态");
		
		label_lajixiangtishi = new Label(com_lajixiang, SWT.CENTER);
		label_lajixiangtishi.setBounds(340, 10, 157, 17);
		
		Button button_22 = new Button(com_lajixiang, SWT.NONE);
		button_22.addSelectionListener(new SelectionAdapter() {
			//垃圾邮件删除
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_lajixiang.getItemCount();
				String string="";
				int sum=0;
				boolean flag4=false;
				int shanchu=0;
				List<Object> shanchuid=new ArrayList<Object>();
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_lajixiang.getItem(i).getChecked()==true){
							System.out.println(table_lajixiang.getItem(i).getText(0));
							string=table_lajixiang.getItem(i).getText(0);
							sum++;
							shanchu=Integer.parseInt(string);
							flag4=emailDAO.shanchuEmail(shanchu);
							
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(flag4){
						label_lajixiangtishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_lajixiangtishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_lajixiangtishi.setText("删除成功");
						Thread.sleep(500);
						label_lajixiangtishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_lajixiangtishi.setText("");
						stackLayout.topControl=com_lajixiang;
						com_menu.layout();
						try{
							List<Object> params5=new ArrayList<Object>();
							params5.add(fromid);
							List<Map<String , Object>> list=emailDAO.lajixiangEmail(params5);
							table_lajixiang.removeAll();

							if(null!=list&&list.size()>0){
								TableItem tableItem;
								for(Map<String , Object> map:list){
									tableItem=new TableItem(table_lajixiang, SWT.None);
									
									int emailid1=Integer.parseInt(map.get("EMAILID").toString());
									Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
									int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
									if(type==0){
										tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
										}
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
									}else{
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										}
									}
								}
							}else{
								//MessageUtil.promt(shell, "温馨提示", "无学生信息");
							}
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}else{
						label_lajixiangtishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_lajixiangtishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_lajixiangtishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(shanchu);
						Thread.sleep(1000);
						label_lajixiangtishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_lajixiangtishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_22.setText("删除");
		button_22.setBounds(10, 39, 56, 25);
		
		Button button_23 = new Button(com_lajixiang, SWT.NONE);
		button_23.addSelectionListener(new SelectionAdapter() {
			//垃圾箱彻底删除
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_lajixiang.getItemCount();
				String string="";
				int sum=0;
				boolean chedishanchuflag=false;
				int shanchu=0;
				List<Object> shanchuid=new ArrayList<Object>();
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_lajixiang.getItem(i).getChecked()==true){
							System.out.println(table_lajixiang.getItem(i).getText(0));
							string=table_lajixiang.getItem(i).getText(0);
							sum++;
							shanchu=Integer.parseInt(string);
							chedishanchuflag=emailDAO.chedidrop(shanchu);
							
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(chedishanchuflag){
						label_lajixiangtishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_lajixiangtishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_lajixiangtishi.setText("删除成功");
						Thread.sleep(500);
						label_lajixiangtishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_lajixiangtishi.setText("");
						stackLayout.topControl=com_lajixiang;
						com_menu.layout();
						try{
							List<Object> params5=new ArrayList<Object>();
							params5.add(fromid);
							List<Map<String , Object>> list=emailDAO.lajixiangEmail(params5);
							table_lajixiang.removeAll();

							if(null!=list&&list.size()>0){
								TableItem tableItem;
								for(Map<String , Object> map:list){
									tableItem=new TableItem(table_lajixiang, SWT.None);
									
									int emailid1=Integer.parseInt(map.get("EMAILID").toString());
									Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
									int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
									if(type==0){
										tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
										}
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
									}else{
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										}
									}
								}
							}else{
								//MessageUtil.promt(shell, "温馨提示", "无学生信息");
							}
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}else{
						label_lajixiangtishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_lajixiangtishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_lajixiangtishi.setText("未选中任何邮件");
						emailDAO.huifushanchuEmail(shanchu);
						Thread.sleep(1000);
						label_lajixiangtishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_lajixiangtishi.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_23.setText("彻底删除");
		button_23.setBounds(66, 39, 75, 25);
		
		Button button_24 = new Button(com_lajixiang, SWT.NONE);
		button_24.addSelectionListener(new SelectionAdapter() {
			//垃圾箱标记为已读
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					int yidu=Integer.parseInt(Content.userID);
					boolean updateyidu=readDAO.updatelajiyidu(yidu);
					if(updateyidu){
						stackLayout.topControl=com_lajixiang;
						com_menu.layout();
						try{
							List<Object> params5=new ArrayList<Object>();
							params5.add(fromid);
							List<Map<String , Object>> list=emailDAO.lajixiangEmail(params5);
							table_lajixiang.removeAll();

							if(null!=list&&list.size()>0){
								TableItem tableItem;
								for(Map<String , Object> map:list){
									tableItem=new TableItem(table_lajixiang, SWT.None);
									
									int emailid1=Integer.parseInt(map.get("EMAILID").toString());
									Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
									int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
									if(type==0){
										tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
										}
									}else{
										if(map.get("TITLE")==null){
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
										}else{
											tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
										}
//										tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
									}
								}
							}else{
								//MessageUtil.promt(shell, "温馨提示", "无学生信息");
							}
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		button_24.setText("全部标记为已读");
		button_24.setBounds(143, 39, 96, 25);
		
		com_select = new Composite(com_menu, SWT.NONE);
		
		SashForm sashForm_1 = new SashForm(com_select, SWT.VERTICAL);
		sashForm_1.setSize(915, 499);
		sashForm_1.setLocation(0, 0);
		
		Composite composite_1 = new Composite(sashForm_1, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(107, 142, 35));
		
		Button button_5 = new Button(composite_1, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			//查看邮件后回复
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_write;
				com_menu.layout();
				text_toid.setText(Content.toid+"@qq.com");
			}
		});
		button_5.setBounds(21, 10, 53, 23);
		button_5.setText("回复");
		
		Button button_6 = new Button(composite_1, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			//关闭
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_shoujianxiang;
				com_menu.layout();
				try{
					List<Object> params5=new ArrayList<Object>();
					params5.add(fromid);
					List<Map<String , Object>> list=emailDAO.shoujianxiang(params5);
					table_shoujianxiang.removeAll();

					if(null!=list&&list.size()>0){
						TableItem tableItem;
						for(Map<String , Object> map:list){
							tableItem=new TableItem(table_shoujianxiang, SWT.None);
							
							int emailid1=Integer.parseInt(map.get("EMAILID").toString());
							Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
							int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
							if(type==0){
								tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
								if(map.get("TITLE")==null){
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
								}else{
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
								}
//								tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
							}else{
//								tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
								if(map.get("TITLE")==null){
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
								}else{
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
								}
							}
						}
					}else{
						//MessageUtil.promt(shell, "温馨提示", "无学生信息");
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_6.setText("关闭");
		button_6.setBounds(109, 10, 53, 23);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(154, 205, 50));
		
		Label label = new Label(composite_2, SWT.NONE);
		label.setBounds(20, 10, 61, 17);
		label.setText("主     题：");
		
		Label label_3 = new Label(composite_2, SWT.NONE);
		label_3.setText("发 件 人：");
		label_3.setBounds(20, 44, 61, 17);
		
		Label label_4 = new Label(composite_2, SWT.NONE);
		label_4.setText("发件时间：");
		label_4.setBounds(20, 73, 61, 17);
		
		label_selectzhuti = new Label(composite_2, SWT.NONE);
		label_selectzhuti.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_selectzhuti.setBounds(87, 10, 516, 17);
		
		label_selectfrom = new Label(composite_2, SWT.NONE);
		label_selectfrom.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_selectfrom.setBounds(87, 44, 516, 17);
		
		label_selectdate = new Label(composite_2, SWT.NONE);
		label_selectdate.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_selectdate.setBounds(87, 73, 516, 17);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(245, 245, 245));
		
		Label label_8 = new Label(composite_3, SWT.NONE);
		label_8.setText("正     文：");
		label_8.setBounds(22, 10, 61, 17);
		
		label_selectcontent = new Label(composite_3, SWT.NONE);
		label_selectcontent.setBounds(22, 33, 668, 310);
		
		table = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION|SWT.CHECK);
		table.setBounds(696, 33, 195, 310);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(68);
		tblclmnNewColumn_3.setText("附件");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(121);
		
		Menu menu_5 = new Menu(table);
		table.setMenu(menu_5);
		
		MenuItem menuItem_10 = new MenuItem(menu_5, SWT.NONE);
		menuItem_10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//根据复选框或取到所选的项
				//1.获取表格的所有项
				int j=table.getItemCount();
				int filesid=0;
				String string3=null;
				for(int i=0;i<j;i++){
					//判断是否被选中
					if(table.getItem(i).getChecked()==true){
						System.out.println(table.getItem(i).getText(0));
						String string=table.getItem(i).getText(0);
						string3=table.getItem(i).getText(1);//获取文件名
						filesid=Integer.parseInt(string);
						try{
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","email","a");
							Statement stmt=con.createStatement();
							ResultSet rs;
							String sql="select NewFile from files where filesid="+filesid;
							rs=stmt.executeQuery(sql);
							
							while(rs.next()){
								
								InputStream image = rs.getBinaryStream("NewFile");                                          
					                                                 //photoimg为image类型,取方法为getBinaryStream();
								String len=image.toString();
								long length=len.length();
								int ch = 0;
								byte[] buffer = new byte[(int)length/7];          //定义 buffer为缓冲区每次读取内容的长度
								File file = new File("c:\\"+string3);//将数据写入文件
								if(!file.exists()){
									file.createNewFile();
								}
//								try{
//									//Runtime.getRuntime().exec("D:/软件/plsqldev.exe");
//									Runtime.getRuntime().exec("cmd /c start c:\\test.txt");
//								}catch(Exception e2){
//									System.out.println("error");
//								}
								Desktop.getDesktop().open(new File("c:\\"+string3));
								FileOutputStream newFile = new FileOutputStream(file);
								InputStream in = new FileInputStream(file);
								byte [] bt = new byte[(int)file.length()];
								in.read(bt);//
								String c = new String(bt);
							//	System.out.println(c);
								in.close();
//								System.out.println(newFile);////流
//								System.out.println(file);
								boolean go = true;
								while(go){
									while((ch = image.read(buffer))!=-1){
										newFile.write(buffer);
									}
									go=false;
								}
							}
						}catch(Exception e1){
							System.out.print(e1);
						}
					}
				}
			}
		});
		menuItem_10.setText("查看");
		sashForm_1.setWeights(new int[] {40, 100, 353});
		
		com_selectyifasong = new Composite(com_menu, SWT.NONE);
		
		SashForm sashForm_2 = new SashForm(com_selectyifasong, SWT.VERTICAL);
		sashForm_2.setBounds(0, 0, 915, 499);
		
		Composite composite_5 = new Composite(sashForm_2, SWT.NONE);
		composite_5.setBackground(SWTResourceManager.getColor(107, 142, 35));
		
		Button button_8 = new Button(composite_5, SWT.NONE);
		button_8.addSelectionListener(new SelectionAdapter() {
			//查看已发送   写信
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_write;
				com_menu.layout();
				text_toid.setText(Content.yifasongtoid+"@qq.com");
				text_zhuti.setText("");
				text_content.setText("");
				label_1.setText("");
			}
		});
		button_8.setText("写信");
		button_8.setBounds(21, 10, 53, 23);
		
		Button button_9 = new Button(composite_5, SWT.NONE);
		button_9.addSelectionListener(new SelectionAdapter() {
			//已发送界面关闭
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_yifasong;
				com_menu.layout();
				try {
					List<Object> yifasongid=new ArrayList<Object>();
					yifasongid.add(fromid);
					List<Map<String, Object>> list=emailDAO.yifasong(yifasongid);
					table_yifasong.removeAll();
					if(null!=list&&list.size()>0){
//						for(Map<String, Object> map:list){
//							TableItem tableItem2=new TableItem(table_yifasong,SWT.NONE);
//							tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
//						}
						for(Map<String, Object> map:list){
							TableItem tableItem2=new TableItem(table_yifasong, SWT.NONE);
//							tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});	
							if(map.get("TITLE")==null){
								tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString()});
							}else{
								tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
							}
						}
					}else{
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_9.setText("关闭");
		button_9.setBounds(109, 10, 53, 23);
		
		Composite composite_6 = new Composite(sashForm_2, SWT.NONE);
		composite_6.setBackground(SWTResourceManager.getColor(154, 205, 50));
		
		Label label_9 = new Label(composite_6, SWT.NONE);
		label_9.setText("主     题：");
		label_9.setBounds(20, 10, 61, 17);
		
		Label label_10 = new Label(composite_6, SWT.NONE);
		label_10.setText("收 件 人：");
		label_10.setBounds(20, 39, 61, 17);
		
		Label label_11 = new Label(composite_6, SWT.NONE);
		label_11.setText("发件时间：");
		label_11.setBounds(20, 73, 61, 17);
		
		label_selectzhuti2 = new Label(composite_6, SWT.NONE);
		label_selectzhuti2.setBounds(87, 10, 516, 17);
		
		label_selecttoid = new Label(composite_6, SWT.NONE);
		label_selecttoid.setBounds(87, 39, 516, 17);
		
		label_selectdate2 = new Label(composite_6, SWT.NONE);
		label_selectdate2.setBounds(87, 73, 516, 17);
		
		Composite composite_7 = new Composite(sashForm_2, SWT.NONE);
		composite_7.setBackground(SWTResourceManager.getColor(245, 245, 245));
		
		Label label_15 = new Label(composite_7, SWT.NONE);
		label_15.setText("正     文：");
		label_15.setBounds(22, 10, 61, 17);
		
		label_selectcontent2 = new Label(composite_7, SWT.NONE);
		label_selectcontent2.setBounds(22, 33, 667, 321);
		
		table_1 = new Table(composite_7, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(695, 33, 195, 310);
		
		TableColumn tableColumn_19 = new TableColumn(table_1, SWT.NONE);
		tableColumn_19.setWidth(68);
		tableColumn_19.setText("附件");
		
		TableColumn tableColumn_20 = new TableColumn(table_1, SWT.NONE);
		tableColumn_20.setWidth(121);
		
		Menu menu_7 = new Menu(table_1);
		table_1.setMenu(menu_7);
		
		MenuItem menuItem_12 = new MenuItem(menu_7, SWT.NONE);
		menuItem_12.addSelectionListener(new SelectionAdapter() {
			//查看已发送邮件附件
			@Override
			public void widgetSelected(SelectionEvent e) {
				//根据复选框或取到所选的项
				//1.获取表格的所有项
				int j=table_1.getItemCount();
				int filesid=0;
				String string3=null;
				for(int i=0;i<j;i++){
					//判断是否被选中
					if(table_1.getItem(i).getChecked()==true){
						System.out.println(table_1.getItem(i).getText(0));
						String string=table_1.getItem(i).getText(0);
						string3=table_1.getItem(i).getText(1);//获取文件名
						filesid=Integer.parseInt(string);
						try{
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","email","a");
							Statement stmt=con.createStatement();
							ResultSet rs;
							String sql="select NewFile from files where filesid="+filesid;
							rs=stmt.executeQuery(sql);
							
							while(rs.next()){
								
								InputStream image = rs.getBinaryStream("NewFile");                                          
					                                                 //photoimg为image类型,取方法为getBinaryStream();
								String len=image.toString();
								long length=len.length();
								int ch = 0;
								byte[] buffer = new byte[(int)length/7];          //定义 buffer为缓冲区每次读取内容的长度
								File file = new File("c:\\"+string3);//将数据写入文件
								if(!file.exists()){
									file.createNewFile();
								}
//								try{
//									//Runtime.getRuntime().exec("D:/软件/plsqldev.exe");
//									Runtime.getRuntime().exec("cmd /c start c:\\test.txt");
//								}catch(Exception e2){
//									System.out.println("error");
//								}
								Desktop.getDesktop().open(new File("c:\\"+string3));
								FileOutputStream newFile = new FileOutputStream(file);
								InputStream in = new FileInputStream(file);
								byte [] bt = new byte[(int)file.length()];
								in.read(bt);//
								String c = new String(bt);
							//	System.out.println(c);
								in.close();
//								System.out.println(newFile);////流
//								System.out.println(file);
								boolean go = true;
								while(go){
									while((ch = image.read(buffer))!=-1){
										newFile.write(buffer);
									}
									go=false;
								}
							}
						}catch(Exception e1){
							System.out.print(e1);
						}
					}
					
				}
			}
		});
		menuItem_12.setText("查看");
		sashForm_2.setWeights(new int[] {40, 100, 353});
		
		com_selectcaogaoxiang = new Composite(com_menu, SWT.NONE);
		
		SashForm sashForm_3 = new SashForm(com_selectcaogaoxiang, SWT.VERTICAL);
		sashForm_3.setBounds(0, 0, 915, 499);
		
		Composite composite_8 = new Composite(sashForm_3, SWT.NONE);
		composite_8.setBackground(SWTResourceManager.getColor(107, 142, 35));
		
		Button button_11 = new Button(composite_8, SWT.NONE);
		button_11.addSelectionListener(new SelectionAdapter() {
			//发送草稿箱邮件
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String zhuti=text_caogaoxiangzhuti.getText();
					String toid=text_caogaoxiangtoid.getText();
					String content=text_caogaoxinagcontent.getText();
					List<Object> paramscaogaoEmail=new ArrayList<Object>();
					paramscaogaoEmail.add(toid);
					paramscaogaoEmail.add(zhuti);
					paramscaogaoEmail.add(content);
					paramscaogaoEmail.add(Content.caogaoxiangEmailid);
					boolean flag4=sendDAO.updatecaogaoEmail(paramscaogaoEmail);
					if(flag4){
						List<Object> params6=new ArrayList<Object>();
						params6.add(Content.caogaoxiangEmailid);
						boolean flag2=sendDAO.sendcaogaoEmail(params6);
						if(flag2){
							MessageUtil.promt(shell, "温馨提示", "邮件发送成功");
						}else{
							MessageUtil.promt(shell, "温馨提示", "邮件发送失败");
						}
					}else{
						MessageUtil.promt(shell, "温馨提示", "您的输入有误");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button_11.setText("发送");
		button_11.setBounds(21, 10, 53, 23);
		
		Button button_12 = new Button(composite_8, SWT.NONE);
		button_12.addSelectionListener(new SelectionAdapter() {
			//关闭草稿箱
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_caogaoxiang;
				com_menu.layout();
			}
		});
		button_12.setText("关闭");
		button_12.setBounds(109, 10, 53, 23);
		
		Composite composite_9 = new Composite(sashForm_3, SWT.NONE);
		composite_9.setBackground(SWTResourceManager.getColor(154, 205, 50));
		
		Label label_5 = new Label(composite_9, SWT.NONE);
		label_5.setText("主     题：");
		label_5.setBounds(20, 10, 61, 17);
		
		Label label_6 = new Label(composite_9, SWT.NONE);
		label_6.setText("收 件 人：");
		label_6.setBounds(20, 39, 61, 17);
		
		Label label_7 = new Label(composite_9, SWT.NONE);
		label_7.setText("发件时间：");
		label_7.setBounds(20, 73, 61, 17);
		
		label_caogaoxiangdate = new Label(composite_9, SWT.NONE);
		label_caogaoxiangdate.setBounds(88, 73, 516, 17);
		
		text_caogaoxiangzhuti = new Text(composite_9, SWT.BORDER);
		text_caogaoxiangzhuti.setBounds(86, 10, 819, 23);
		
		text_caogaoxiangtoid = new Text(composite_9, SWT.BORDER);
		text_caogaoxiangtoid.setBounds(86, 36, 819, 23);
		
		Composite composite_10 = new Composite(sashForm_3, SWT.NONE);
		composite_10.setBackground(SWTResourceManager.getColor(245, 245, 245));
		
		Label label_16 = new Label(composite_10, SWT.NONE);
		label_16.setText("正     文：");
		label_16.setBounds(22, 10, 61, 17);
		
		text_caogaoxinagcontent = new Text(composite_10, SWT.BORDER|SWT.WRAP|SWT.H_SCROLL|SWT.V_SCROLL|SWT.CANCEL|SWT.MULTI);
		text_caogaoxinagcontent.setBounds(22, 40, 883, 323);
		sashForm_3.setWeights(new int[] {40, 100, 353});
		
		com_selectyishanchu = new Composite(com_menu, SWT.NONE);
		
		SashForm sashForm_4 = new SashForm(com_selectyishanchu, SWT.VERTICAL);
		sashForm_4.setBounds(0, 0, 915, 499);
		
		Composite composite_11 = new Composite(sashForm_4, SWT.NONE);
		composite_11.setBackground(SWTResourceManager.getColor(107, 142, 35));
		
		Button button_huifu = new Button(composite_11, SWT.NONE);
		button_huifu.addSelectionListener(new SelectionAdapter() {
			//回复已删除
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_write;
				com_menu.layout();
				text_toid.setText(Content.lajiid+"@qq.com");
				text_zhuti.setText("");
				text_content.setText("");
				label_1.setText("");
			}
		});
		button_huifu.setText("回复");
		button_huifu.setBounds(21, 10, 53, 23);
		
		Button button_yishanchuguanbi = new Button(composite_11, SWT.NONE);
		button_yishanchuguanbi.addSelectionListener(new SelectionAdapter() {
			//关闭已删除界面
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_yishanchu;
				com_menu.layout();
				try {
					List<Object> params8=new ArrayList<Object>();
					params8.add(fromid);
					List<Map<String , Object>> list8=emailDAO.yishanchu(params8);
					table_yishanchu.removeAll();
					if(null!=list8&&list8.size()>0){
						for(Map<String, Object> map:list8){
							TableItem tableItem2=new TableItem(table_yishanchu, SWT.NONE);
//							tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
							if(map.get("TITLE")==null){
							tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString()});
							}else{
								tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
							}
						}
					}else{
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_yishanchuguanbi.setText("关闭");
		button_yishanchuguanbi.setBounds(109, 10, 53, 23);
		
		Composite composite_12 = new Composite(sashForm_4, SWT.NONE);
		composite_12.setBackground(SWTResourceManager.getColor(154, 205, 50));
		
		Label label_2 = new Label(composite_12, SWT.NONE);
		label_2.setText("主     题：");
		label_2.setBounds(20, 10, 61, 17);
		
		Label label_12 = new Label(composite_12, SWT.NONE);
		label_12.setText("发 件 人：");
		label_12.setBounds(20, 44, 61, 17);
		
		Label label_13 = new Label(composite_12, SWT.NONE);
		label_13.setText("发件时间：");
		label_13.setBounds(20, 73, 61, 17);
		
		label_yishanchuzhuti = new Label(composite_12, SWT.NONE);
		label_yishanchuzhuti.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_yishanchuzhuti.setBounds(87, 10, 516, 17);
		
		label_yishanchutoid = new Label(composite_12, SWT.NONE);
		label_yishanchutoid.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_yishanchutoid.setBounds(87, 44, 516, 17);
		
		label_yishanchudate = new Label(composite_12, SWT.NONE);
		label_yishanchudate.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_yishanchudate.setBounds(87, 73, 516, 17);
		
		Composite composite_13 = new Composite(sashForm_4, SWT.NONE);
		composite_13.setBackground(SWTResourceManager.getColor(245, 245, 245));
		
		Label label_19 = new Label(composite_13, SWT.NONE);
		label_19.setText("正     文：");
		label_19.setBounds(22, 10, 61, 17);
		
		label_yishanchucontent = new Label(composite_13, SWT.NONE);
		label_yishanchucontent.setBounds(22, 33, 668, 310);
		
		table_yishanchu1 = new Table(composite_13, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		table_yishanchu1.setLinesVisible(true);
		table_yishanchu1.setHeaderVisible(true);
		table_yishanchu1.setBounds(696, 33, 195, 310);
		
		TableColumn tableColumn_17 = new TableColumn(table_yishanchu1, SWT.NONE);
		tableColumn_17.setWidth(68);
		tableColumn_17.setText("附件");
		
		TableColumn tableColumn_18 = new TableColumn(table_yishanchu1, SWT.NONE);
		tableColumn_18.setWidth(121);
		
		Menu menu_6 = new Menu(table_yishanchu1);
		table_yishanchu1.setMenu(menu_6);
		
		MenuItem menuItem_11 = new MenuItem(menu_6, SWT.NONE);
		menuItem_11.addSelectionListener(new SelectionAdapter() {
			//打开已删除邮件附件
			@Override
			public void widgetSelected(SelectionEvent e) {
				//根据复选框或取到所选的项
				//1.获取表格的所有项
				int j=table_yishanchu1.getItemCount();
				int filesid=0;
				String string3=null;
				for(int i=0;i<j;i++){
					//判断是否被选中
					if(table_yishanchu1.getItem(i).getChecked()==true){
						System.out.println(table_yishanchu1.getItem(i).getText(0));
						String string=table_yishanchu1.getItem(i).getText(0);
						string3=table_yishanchu1.getItem(i).getText(1);//获取文件名
						filesid=Integer.parseInt(string);
						try{
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","email","a");
							Statement stmt=con.createStatement();
							ResultSet rs;
							String sql="select NewFile from files where filesid="+filesid;
							rs=stmt.executeQuery(sql);
							
							while(rs.next()){
								
								InputStream image = rs.getBinaryStream("NewFile");                                          
					                                                 //photoimg为image类型,取方法为getBinaryStream();
								String len=image.toString();
								long length=len.length();
								int ch = 0;
								byte[] buffer = new byte[(int)length/7];          //定义 buffer为缓冲区每次读取内容的长度
								File file = new File("c:\\"+string3);//将数据写入文件
								if(!file.exists()){
									file.createNewFile();
								}
//								try{
//									//Runtime.getRuntime().exec("D:/软件/plsqldev.exe");
//									Runtime.getRuntime().exec("cmd /c start c:\\test.txt");
//								}catch(Exception e2){
//									System.out.println("error");
//								}
								Desktop.getDesktop().open(new File("c:\\"+string3));
								FileOutputStream newFile = new FileOutputStream(file);
								InputStream in = new FileInputStream(file);
								byte [] bt = new byte[(int)file.length()];
								in.read(bt);//
								String c = new String(bt);
							//	System.out.println(c);
								in.close();
//								System.out.println(newFile);////流
//								System.out.println(file);
								boolean go = true;
								while(go){
									while((ch = image.read(buffer))!=-1){
										newFile.write(buffer);
									}
									go=false;
								}
							}
						}catch(Exception e1){
							System.out.print(e1);
						}
					}
					
				}
			}
		});
		menuItem_11.setText("查看");
		sashForm_4.setWeights(new int[] {40, 100, 353});
		
		com_addfriends = new Composite(com_menu, SWT.NONE);
		com_addfriends.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images3/添加好友.jpg"));
		
		Label label_35 = new Label(com_addfriends, SWT.NONE);
		label_35.setBounds(218, 150, 67, 17);
		label_35.setText(" 账   号：");
		
		text_adduserid = new Text(com_addfriends, SWT.BORDER);
		text_adduserid.addFocusListener(new FocusAdapter() {
			//根据账号查询信息
			@Override
			public void focusLost(FocusEvent e) {
				
				try {
					Map<String , Object> mapadd=emailDAO.findFriendName(Integer.parseInt(text_adduserid.getText()));
					if(mapadd.get("TEL")!=null){
						text_addusername.setText(mapadd.get("USERNAME").toString());
						text_addusertel.setText(mapadd.get("TEL").toString());
					}else{
						text_addusername.setText(mapadd.get("USERNAME").toString());
						text_addusertel.setText("");
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		text_adduserid.setBounds(291, 147, 160, 23);
		
		Label label_36 = new Label(com_addfriends, SWT.NONE);
		label_36.setText(" 用 户 名：");
		label_36.setBounds(218, 200, 67, 17);
		
		Label label_38 = new Label(com_addfriends, SWT.NONE);
		label_38.setText(" 电   话：");
		label_38.setBounds(218, 250, 67, 17);
		
		Label label_40 = new Label(com_addfriends, SWT.NONE);
		label_40.setText(" 备   注：");
		label_40.setBounds(218, 300, 67, 17);
		
		text_addpersonname = new Text(com_addfriends, SWT.BORDER);
		text_addpersonname.setBounds(291, 294, 160, 23);
		
		Label label_41 = new Label(com_addfriends, SWT.NONE);
		label_41.setText(" 分   组：");
		label_41.setBounds(218, 350, 67, 17);
		
		combo_addpersontype = new Combo(com_addfriends, SWT.NONE);
		combo_addpersontype.select(0);
		combo_addpersontype.add("我的好友");
//		combo_addpersontype.add("同事");
		combo_addpersontype.setBounds(291, 342, 117, 25);
		
		Button button_29 = new Button(com_addfriends, SWT.NONE);
		button_29.addSelectionListener(new SelectionAdapter() {
			//通讯录保存好友信息
			@Override
			public void widgetSelected(SelectionEvent e) {
				int addpersonid=0;
				if(text_adduserid.getText()==""){
					MessageUtil.promt(shell, "温馨提示", "请输入查询的账号");
				}else{
					addpersonid=Integer.parseInt(text_adduserid.getText());
				}
				String addpersonname=text_addpersonname.getText().toString();
				String addpersontype=combo_addpersontype.getText().toString();
				try {
					List<Object> paramsadd=new ArrayList<Object>();
					paramsadd.add(fromid);
//					System.out.println(fromid+"\\\\");
					paramsadd.add(addpersonid);
//					System.out.println(addpersonid+"\\\\");
					paramsadd.add(addpersonname);
//					System.out.println(addpersonname+"\\\\");
					paramsadd.add(addpersontype);
//					System.out.println(addpersontype+"\\\\");
					boolean flagadd=emailDAO.addfriends(paramsadd);
					if(flagadd){
						MessageUtil.promt(shell, "温馨提示", "保存成功");
						text_adduserid.setText("");
						text_addusername.setText("");
						text_addusertel.setText("");
						text_addpersonname.setText("");
						combo_addpersontype.setText("");
					}else{
						MessageUtil.promt(shell, "温馨提示", "保存失败");
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					MessageUtil.promt(shell, "温馨提示", e1.getMessage());
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_29.setBounds(225, 400, 80, 27);
		button_29.setText("保存");
		
		Button button_30 = new Button(com_addfriends, SWT.NONE);
		button_30.addSelectionListener(new SelectionAdapter() {
			//添加好友 取消
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_tongxunlu;
				com_menu.layout();
				try{
//					List<Object> paramstongxunlu=new ArrayList<Object>();
//					paramstongxunlu.add(fromid);
					List<Map<String , Object>> list3=emailDAO.addressbook(fromid);
					table_tongxunlu.removeAll();

					if(null!= list3 &&list3.size()>0){
						TableItem tableItem;
						for(Map<String,Object> map:list3){
							System.out.println(map);
							System.out.println(map.get("PERSONID").toString());
							String friendTel=null;
							tableItem = new TableItem(table_tongxunlu,SWT.NONE);
							Map<String , Object> mapfriend=emailDAO.findFriendName(Integer.parseInt(map.get("PERSONID").toString()));
							String friendname=mapfriend.get("USERNAME").toString();
							System.out.println(mapfriend);
							if(mapfriend.get("TEL")==null){
								friendTel=null;
							}else{
								friendTel=mapfriend.get("TEL").toString();
							}
//							tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,map.get("PERSONNAME").toString()," ",map.get("PERSONTYPE").toString()});
							if(map.get("PERSONNAME")==null&&mapfriend.get("TEL")==null){
								tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,"","",map.get("PERSONTYPE").toString()});
							}
							if(map.get("PERSONNAME")!=null&&mapfriend.get("TEL")==null){
								tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,map.get("PERSONNAME").toString(),"",map.get("PERSONTYPE").toString()});
							}
							if(map.get("PERSONNAME")==null&&mapfriend.get("TEL")!=null){
								tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,"",friendTel,map.get("PERSONTYPE").toString()});
							}
							if(map.get("PERSONNAME")!=null&&mapfriend.get("TEL")!=null){
								tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,map.get("PERSONNAME").toString(),friendTel,map.get("PERSONTYPE").toString()});
							}
						}
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_30.setText("取消");
		button_30.setBounds(371, 400, 80, 27);
		
		text_addusername = new Text(com_addfriends, SWT.BORDER);
		text_addusername.setBounds(291, 194, 160, 23);
		
		text_addusertel = new Text(com_addfriends, SWT.BORDER);
		text_addusertel.setBounds(291, 244, 160, 23);
		
		com_tongxunlu = new Composite(com_menu, SWT.NONE);
		com_tongxunlu.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images3/通讯录界面.jpg"));
		
		table_tongxunlu = new Table(com_tongxunlu, SWT.BORDER | SWT.FULL_SELECTION|SWT.CHECK);
		table_tongxunlu.setBounds(10, 80, 895, 397);
		table_tongxunlu.setHeaderVisible(true);
		table_tongxunlu.setLinesVisible(true);
		
		TableColumn tableColumn_25 = new TableColumn(table_tongxunlu, SWT.NONE);
		tableColumn_25.setWidth(130);
		tableColumn_25.setText("账号");
		
		TableColumn tableColumn_26 = new TableColumn(table_tongxunlu, SWT.NONE);
		tableColumn_26.setWidth(150);
		tableColumn_26.setText("名称");
		
		TableColumn tableColumn_27 = new TableColumn(table_tongxunlu, SWT.NONE);
		tableColumn_27.setWidth(150);
		tableColumn_27.setText("备注");
		
		TableColumn tblclmnNewColumn_7 = new TableColumn(table_tongxunlu, SWT.NONE);
		tblclmnNewColumn_7.setWidth(230);
		tblclmnNewColumn_7.setText("电话");
		
		TableColumn tableColumn_28 = new TableColumn(table_tongxunlu, SWT.NONE);
		tableColumn_28.setWidth(230);
		tableColumn_28.setText("分组");
		
		Menu menu_11 = new Menu(table_tongxunlu);
		table_tongxunlu.setMenu(menu_11);
		
		MenuItem menuItem_17 = new MenuItem(menu_11, SWT.NONE);
		menuItem_17.addSelectionListener(new SelectionAdapter() {
			//通讯录 写信
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_tongxunlu.getItemCount();
				String string="";
				int sum=0;
				for(int i=0;i<count;i++){
					//判断是否被选中
					if(table_tongxunlu.getItem(i).getChecked()==true){
						System.out.println(table_tongxunlu.getItem(i).getText(0));
						string=table_tongxunlu.getItem(i).getText(0);
						
						sum++;
					}
				}
				if(sum==1&&string!=null){
					stackLayout.topControl=com_write;
					com_menu.layout();
					int count1=Integer.parseInt(string);
					text_toid.setText(string+"@qq.com");
				}else{
					MessageUtil.promt(shell, "提示", "请选择一个好友");
				}
			}
		});
		menuItem_17.setText("写信");
		
		MenuItem menuItem_18 = new MenuItem(menu_11, SWT.NONE);
		menuItem_18.addSelectionListener(new SelectionAdapter() {
			//修改好友信息
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_friendid.setText("");
				text_friendname.setText("");
				text_friendtel.setText("");
				int count =table_tongxunlu.getItemCount();
				String string="";
				int sum=0;
				for(int i=0;i<count;i++){
					//判断是否被选中
					if(table_tongxunlu.getItem(i).getChecked()==true){
//						System.out.println(table_tongxunlu.getItem(i).getText(0));
						string=table_tongxunlu.getItem(i).getText(0);
						sum++;
					}
				}
				if(sum==1&&string!=null){
					stackLayout.topControl=com_updatefriend;
					com_menu.layout();
					Content.personid=string;
					try {
						String personid=Content.personid;
//						System.out.println("personid:"+personid);
						text_friendid.setText(personid);
						Map<String , Object> mapfriendxinxi=emailDAO.findFriendName(Integer.parseInt(Content.personid));
						Map<String , Object> mapperson=emailDAO.findperson(Integer.parseInt(Content.personid));
						if(mapperson.get("PERSONNAME")==null){
							text_updatefriendname.setText("");
							combo.setText(mapperson.get("PERSONTYPE").toString());
						}else{
							text_updatefriendname.setText(mapperson.get("PERSONNAME").toString());
							combo.setText(mapperson.get("PERSONTYPE").toString());
						}
//						System.out.println("mapfriendxinxi:"+mapfriendxinxi);
						if(mapfriendxinxi.get("TEL")!=null){
							text_friendname.setText(mapfriendxinxi.get("USERNAME").toString());
							text_friendtel.setText(mapfriendxinxi.get("TEL").toString());
						}else{
							text_friendname.setText(mapfriendxinxi.get("USERNAME").toString());
							text_friendtel.setText("");
						}
						
					} catch (NumberFormatException e1) {
 						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
 						e1.printStackTrace();
					} catch (SQLException e1) {
 						e1.printStackTrace();
					}
//					Map<String , Object> mappersonxinxi=emailDAO.updateperson(personname, persontype, personid)
				}else{
					MessageUtil.promt(shell, "提示", "请选择一个好友");
				}
			}
		});
		menuItem_18.setText("编辑");
		
		MenuItem menuItem_19 = new MenuItem(menu_11, SWT.NONE);
		menuItem_19.addSelectionListener(new SelectionAdapter() {
			//删除通讯录好友
			@Override
			public void widgetSelected(SelectionEvent e) {
				int count =table_tongxunlu.getItemCount();
				String string="";
				int sum=0;
				boolean flag4=false;
				int shanchu=0;
				List<Object> shanchuid=new ArrayList<Object>();
				try {
					for(int i=0;i<count;i++){
						//判断是否被选中
						if(table_tongxunlu.getItem(i).getChecked()==true){
							System.out.println(table_tongxunlu.getItem(i).getText(0));
							string=table_tongxunlu.getItem(i).getText(0);
							sum++;
							shanchu=Integer.parseInt(string);
							flag4=emailDAO.deletefriend(shanchu);
							
						}else{
//							label_tishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
//							label_tishi.setText("未选中任何邮件");
//							Thread.sleep(500);
//							label_tishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
//							label_tishi.setText("");
						}
					}
					if(flag4){
						label_tongxunlutishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tongxunlutishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_tongxunlutishi.setText("删除成功");
						Thread.sleep(500);
						label_tongxunlutishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tongxunlutishi.setText("");
						stackLayout.topControl=com_tongxunlu;
						com_menu.layout();
						try{
							List<Map<String , Object>> list=emailDAO.addressbook(fromid);
							table_tongxunlu.removeAll();
							if(null!= list &&list.size()>0){
								TableItem tableItem;
								for(Map<String,Object> map:list){
									System.out.println(map);
									System.out.println(map.get("PERSONID").toString());
									String friendTel=null;
									tableItem = new TableItem(table_tongxunlu,SWT.NONE);
									Map<String , Object> mapfriend=emailDAO.findFriendName(Integer.parseInt(map.get("PERSONID").toString()));
									String friendname=mapfriend.get("USERNAME").toString();
									System.out.println(mapfriend);
									if(mapfriend.get("TEL")==null){
										friendTel=friendTel=null;
									}else{
										friendTel=mapfriend.get("TEL").toString();
									}
//									tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,map.get("PERSONNAME").toString()," ",map.get("PERSONTYPE").toString()});
									if(map.get("PERSONNAME")==null&&mapfriend.get("TEL")==null){
										tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,"","",map.get("PERSONTYPE").toString()});
									}
									if(map.get("PERSONNAME")!=null&&mapfriend.get("TEL")==null){
										tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,map.get("PERSONNAME").toString(),"",map.get("PERSONTYPE").toString()});
									}
									if(map.get("PERSONNAME")==null&&mapfriend.get("TEL")!=null){
										tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,"",friendTel,map.get("PERSONTYPE").toString()});
									}
									if(map.get("PERSONNAME")!=null&&mapfriend.get("TEL")!=null){
										tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,map.get("PERSONNAME").toString(),friendTel,map.get("PERSONTYPE").toString()});
									}
								}
							}
						}catch(SQLException e1){
							e1.printStackTrace();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}else{
						label_tongxunlutishi.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tongxunlutishi.setBackground(SWTResourceManager.getColor(154, 205, 50));
						label_tongxunlutishi.setText("未选中任何好友");
						emailDAO.huifushanchuEmail(shanchu);
						Thread.sleep(1000);
						label_tongxunlutishi.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						label_tongxunlutishi.setText("");
					}
				} catch (SQLException e1) {
 					e1.printStackTrace();
				} catch (IOException e1) {
 					e1.printStackTrace();
				} catch (InterruptedException e1) {
 					e1.printStackTrace();
				}
			}
		});
		menuItem_19.setText("删除");
		
		label_tongxunlutishi = new Label(com_tongxunlu, SWT.CENTER);
		label_tongxunlutishi.setBounds(340, 10, 157, 17);
		
		Label label_34 = new Label(com_tongxunlu, SWT.NONE);
		label_34.addMouseListener(new MouseAdapter() {
			//添加好友
			@Override
			public void mouseDown(MouseEvent e) {
				stackLayout.topControl=com_addfriends;
				com_menu.layout();
				
			}
		});
		label_34.setBounds(10, 35, 44, 26);
		
		com_selectqun = new Composite(com_menu, SWT.NONE);
		
		SashForm sashForm_5 = new SashForm(com_selectqun, SWT.VERTICAL);
		sashForm_5.setBounds(0, 0, 915, 499);
		
		Composite composite_4 = new Composite(sashForm_5, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(107, 142, 35));
		
		Button button_13 = new Button(composite_4, SWT.NONE);
		button_13.addSelectionListener(new SelectionAdapter() {
			//群邮件回复
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_qun;
				com_menu.layout();
				text_quntoid.setText(Content.quntoid+"@qq.com");
			}
		});
		button_13.setText("回复");
		button_13.setBounds(21, 10, 53, 23);
		
		Button button_19 = new Button(composite_4, SWT.NONE);
		button_19.addSelectionListener(new SelectionAdapter() {
			//群查看信息  关闭
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_qunshoujianxiang;
				com_menu.layout();
				try{
					List<Object> params5=new ArrayList<Object>();
					params5.add(fromid);
					List<Map<String , Object>> list=emailDAO.Qunshoujian(fromid);
					table_qunshoujianxiang.removeAll();
					if(null!=list&&list.size()>0){
						TableItem tableItem;
						for(Map<String , Object> map:list){
							tableItem=new TableItem(table_qunshoujianxiang, SWT.None);
							int emailid1=Integer.parseInt(map.get("EMAILID").toString());
							Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
							int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
							if(type==0){
								tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
								if(map.get("TITLE")==null){
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA4").toString(),"(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
								}else{
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA4").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
								}
//								tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
							}else{
//								tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
								if(map.get("TITLE")==null){
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA4").toString(),"(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
								}else{
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA4").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
								}
							}
						}
					}else{
						//MessageUtil.promt(shell, "温馨提示", "无学生信息");
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_19.setText("关闭");
		button_19.setBounds(109, 10, 53, 23);
		
		Composite composite_14 = new Composite(sashForm_5, SWT.NONE);
		composite_14.setBackground(SWTResourceManager.getColor(154, 205, 50));
		
		Label label_17 = new Label(composite_14, SWT.NONE);
		label_17.setText("主     题：");
		label_17.setBounds(20, 5, 61, 17);
		
		Label label_18 = new Label(composite_14, SWT.NONE);
		label_18.setText("发 件 人：");
		label_18.setBounds(20, 27, 61, 17);
		
		Label label_20 = new Label(composite_14, SWT.NONE);
		label_20.setText("发件时间：");
		label_20.setBounds(20, 73, 61, 17);
		
		label_qunzhuti = new Label(composite_14, SWT.NONE);
		label_qunzhuti.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_qunzhuti.setBounds(87, 5, 516, 17);
		
		label_qunfromid = new Label(composite_14, SWT.NONE);
		label_qunfromid.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_qunfromid.setBounds(87, 27, 516, 17);
		
		label_qundate = new Label(composite_14, SWT.NONE);
		label_qundate.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_qundate.setBounds(87, 73, 516, 17);
		
		Label label_26 = new Label(composite_14, SWT.NONE);
		label_26.setText("    群    ：");
		label_26.setBounds(20, 50, 61, 17);
		
		label_qunid = new Label(composite_14, SWT.NONE);
		label_qunid.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_qunid.setBounds(87, 50, 516, 17);
		
		Composite composite_15 = new Composite(sashForm_5, SWT.NONE);
		composite_15.setBackground(SWTResourceManager.getColor(245, 245, 245));
		
		Label label_24 = new Label(composite_15, SWT.NONE);
		label_24.setText("正     文：");
		label_24.setBounds(22, 10, 61, 17);
		
		label_quncontent = new Label(composite_15, SWT.NONE);
		label_quncontent.setBounds(22, 33, 668, 310);
		
		table_quntable = new Table(composite_15, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		table_quntable.setLinesVisible(true);
		table_quntable.setHeaderVisible(true);
		table_quntable.setBounds(696, 33, 195, 310);
		
		TableColumn tableColumn_29 = new TableColumn(table_quntable, SWT.NONE);
		tableColumn_29.setWidth(68);
		tableColumn_29.setText("附件");
		
		TableColumn tableColumn_30 = new TableColumn(table_quntable, SWT.NONE);
		tableColumn_30.setWidth(121);
		
		Menu menu_9 = new Menu(table_quntable);
		table_quntable.setMenu(menu_9);
		
		MenuItem menuItem_15 = new MenuItem(menu_9, SWT.NONE);
		menuItem_15.addSelectionListener(new SelectionAdapter() {
			//查看群邮件附件
			@Override
			public void widgetSelected(SelectionEvent e) {
				//根据复选框或取到所选的项
				//1.获取表格的所有项
				int j=table_quntable.getItemCount();
				int filesid=0;
				String string3=null;
				for(int i=0;i<j;i++){
					//判断是否被选中
					if(table_quntable.getItem(i).getChecked()==true){
						System.out.println(table_quntable.getItem(i).getText(0));
						String string=table_quntable.getItem(i).getText(0);
						string3=table_quntable.getItem(i).getText(1);//获取文件名
						filesid=Integer.parseInt(string);
						try{
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","email","a");
							Statement stmt=con.createStatement();
							ResultSet rs;
							String sql="select NewFile from files where filesid="+filesid;
							rs=stmt.executeQuery(sql);
							
							while(rs.next()){
								
								InputStream image = rs.getBinaryStream("NewFile");                                          
					                                                 //photoimg为image类型,取方法为getBinaryStream();
								String len=image.toString();
								long length=len.length();
								int ch = 0;
								byte[] buffer = new byte[(int)length/7];          //定义 buffer为缓冲区每次读取内容的长度
								File file = new File("c:\\"+string3);//将数据写入文件
								if(!file.exists()){
									file.createNewFile();
								}
//								try{
//									//Runtime.getRuntime().exec("D:/软件/plsqldev.exe");
//									Runtime.getRuntime().exec("cmd /c start c:\\test.txt");
//								}catch(Exception e2){
//									System.out.println("error");
//								}
								Desktop.getDesktop().open(new File("c:\\"+string3));
								FileOutputStream newFile = new FileOutputStream(file);
								InputStream in = new FileInputStream(file);
								byte [] bt = new byte[(int)file.length()];
								in.read(bt);//
								String c = new String(bt);
							//	System.out.println(c);
								in.close();
//								System.out.println(newFile);////流
//								System.out.println(file);
								boolean go = true;
								while(go){
									while((ch = image.read(buffer))!=-1){
										newFile.write(buffer);
									}
									go=false;
								}
							}
						}catch(Exception e1){
							System.out.print(e1);
						}
					}
				}
			}
		});
		menuItem_15.setText("查看");
		sashForm_5.setWeights(new int[] {40, 100, 353});
		
		com_selectlaji = new Composite(com_menu, SWT.NONE);
		
		SashForm sashForm_6 = new SashForm(com_selectlaji, SWT.VERTICAL);
		sashForm_6.setBounds(0, 0, 915, 499);
		
		Composite composite_16 = new Composite(sashForm_6, SWT.NONE);
		composite_16.setBackground(SWTResourceManager.getColor(107, 142, 35));
		
		Button button_20 = new Button(composite_16, SWT.NONE);
		button_20.addSelectionListener(new SelectionAdapter() {
			//垃圾箱回复
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_write;
				com_menu.layout();
				text_toid.setText(Content.lajiid+"@qq.com");
				text_zhuti.setText("");
				text_content.setText("");
				label_1.setText("");
			}
		});
		button_20.setText("回复");
		button_20.setBounds(21, 10, 53, 23);
		
		Button button_21 = new Button(composite_16, SWT.NONE);
		button_21.addSelectionListener(new SelectionAdapter() {
			//垃圾箱关闭
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_lajixiang;
				com_menu.layout();
				try{
					List<Object> params5=new ArrayList<Object>();
					params5.add(fromid);
					List<Map<String , Object>> list=emailDAO.lajixiangEmail(params5);
					table_lajixiang.removeAll();

					if(null!=list&&list.size()>0){
						TableItem tableItem;
						for(Map<String , Object> map:list){
							tableItem=new TableItem(table_lajixiang, SWT.None);
							
							int emailid1=Integer.parseInt(map.get("EMAILID").toString());
							Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
							int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
							if(type==0){
								tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
								if(map.get("TITLE")==null){
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
								}else{
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
								}
//								tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
							}else{
//								tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
								if(map.get("TITLE")==null){
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
								}else{
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
								}
							}
						}
					}else{
						//MessageUtil.promt(shell, "温馨提示", "无学生信息");
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_21.setText("关闭");
		button_21.setBounds(109, 10, 53, 23);
		
		Composite composite_17 = new Composite(sashForm_6, SWT.NONE);
		composite_17.setBackground(SWTResourceManager.getColor(154, 205, 50));
		
		Label label_21 = new Label(composite_17, SWT.NONE);
		label_21.setText("主     题：");
		label_21.setBounds(20, 10, 61, 17);
		
		Label label_22 = new Label(composite_17, SWT.NONE);
		label_22.setText("发 件 人：");
		label_22.setBounds(20, 44, 61, 17);
		
		Label label_23 = new Label(composite_17, SWT.NONE);
		label_23.setText("发件时间：");
		label_23.setBounds(20, 73, 61, 17);
		
		label_selectlajititle = new Label(composite_17, SWT.NONE);
		label_selectlajititle.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_selectlajititle.setBounds(87, 10, 516, 17);
		
		label_selectlajifrom = new Label(composite_17, SWT.NONE);
		label_selectlajifrom.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_selectlajifrom.setBounds(87, 44, 516, 17);
		
		label_selectlajidate = new Label(composite_17, SWT.NONE);
		label_selectlajidate.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_selectlajidate.setBounds(87, 73, 516, 17);
		
		Composite composite_18 = new Composite(sashForm_6, SWT.NONE);
		composite_18.setBackground(SWTResourceManager.getColor(245, 245, 245));
		
		Label label_29 = new Label(composite_18, SWT.NONE);
		label_29.setText("正     文：");
		label_29.setBounds(22, 10, 61, 17);
		
		label_selectlajicontent = new Label(composite_18, SWT.NONE);
		label_selectlajicontent.setBounds(22, 33, 668, 310);
		
		table_selectlajitable = new Table(composite_18, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION);
		table_selectlajitable.setLinesVisible(true);
		table_selectlajitable.setHeaderVisible(true);
		table_selectlajitable.setBounds(696, 33, 195, 310);
		
		TableColumn tableColumn_32 = new TableColumn(table_selectlajitable, SWT.NONE);
		tableColumn_32.setWidth(68);
		tableColumn_32.setText("附件");
		
		TableColumn tableColumn_33 = new TableColumn(table_selectlajitable, SWT.NONE);
		tableColumn_33.setWidth(121);
		
		Menu menu_10 = new Menu(table_selectlajitable);
		table_selectlajitable.setMenu(menu_10);
		
		MenuItem menuItem_16 = new MenuItem(menu_10, SWT.NONE);
		menuItem_16.addSelectionListener(new SelectionAdapter() {
			//垃圾箱附件
			@Override
			public void widgetSelected(SelectionEvent e) {
				//根据复选框或取到所选的项
				//1.获取表格的所有项
				int j=table_selectlajitable.getItemCount();
				int filesid=0;
				String string3=null;
				for(int i=0;i<j;i++){
					//判断是否被选中
					if(table_selectlajitable.getItem(i).getChecked()==true){
						System.out.println(table_selectlajitable.getItem(i).getText(0));
						String string=table_selectlajitable.getItem(i).getText(0);
						string3=table_selectlajitable.getItem(i).getText(1);//获取文件名
						filesid=Integer.parseInt(string);
						try{
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","email","a");
							Statement stmt=con.createStatement();
							ResultSet rs;
							String sql="select NewFile from files where filesid="+filesid;
							rs=stmt.executeQuery(sql);
							
							while(rs.next()){
								
								InputStream image = rs.getBinaryStream("NewFile");                                          
					                                                 //photoimg为image类型,取方法为getBinaryStream();
								String len=image.toString();
								long length=len.length();
								int ch = 0;
								byte[] buffer = new byte[(int)length/7];          //定义 buffer为缓冲区每次读取内容的长度
								File file = new File("c:\\"+string3);//将数据写入文件
								if(!file.exists()){
									file.createNewFile();
								}
//								try{
//									//Runtime.getRuntime().exec("D:/软件/plsqldev.exe");
//									Runtime.getRuntime().exec("cmd /c start c:\\test.txt");
//								}catch(Exception e2){
//									System.out.println("error");
//								}
								Desktop.getDesktop().open(new File("c:\\"+string3));
								FileOutputStream newFile = new FileOutputStream(file);
								InputStream in = new FileInputStream(file);
								byte [] bt = new byte[(int)file.length()];
								in.read(bt);//
								String c = new String(bt);
							//	System.out.println(c);
								in.close();
//								System.out.println(newFile);////流
//								System.out.println(file);
								boolean go = true;
								while(go){
									while((ch = image.read(buffer))!=-1){
										newFile.write(buffer);
									}
									go=false;
								}
							}
						}catch(Exception e1){
							System.out.print(e1);
						}
					}
				}
			}
		});
		menuItem_16.setText("查看");
		sashForm_6.setWeights(new int[] {40, 100, 353});
		
		com_updatefriend = new Composite(com_menu, SWT.NONE);
		com_updatefriend.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class, "/images3/添加好友.jpg"));
		
		Label label_25 = new Label(com_updatefriend, SWT.NONE);
		label_25.setBounds(185, 150, 72, 17);
		label_25.setText("  账   号：");
		
		Label label_27 = new Label(com_updatefriend, SWT.NONE);
		label_27.setText("  名   称：");
		label_27.setBounds(185, 200, 72, 17);
		
		Label label_28 = new Label(com_updatefriend, SWT.NONE);
		label_28.setText("  电   话：");
		label_28.setBounds(185, 250, 72, 17);
		
		Label label_30 = new Label(com_updatefriend, SWT.NONE);
		label_30.setText("  备   注：");
		label_30.setBounds(185, 303, 72, 17);
		
		Label label_31 = new Label(com_updatefriend, SWT.NONE);
		label_31.setText("  分   组：");
		label_31.setBounds(185, 353, 72, 17);
		
		text_updatefriendname = new Text(com_updatefriend, SWT.BORDER);
		text_updatefriendname.setBounds(263, 300, 178, 23);
		
		Button button_27 = new Button(com_updatefriend, SWT.NONE);
		button_27.addSelectionListener(new SelectionAdapter() {
			//保存修改的好友信息
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					boolean flagupdatefriend=emailDAO.updateperson(text_updatefriendname.getText(), combo.getText(), Integer.parseInt(Content.personid));
					if(flagupdatefriend){
						MessageUtil.promt(shell, "提示", "修改成功");
					}else{
						MessageUtil.promt(shell, "提示", "修改失败");
					}
				} catch (NumberFormatException e1) {
 					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
 					e1.printStackTrace();
				} catch (SQLException e1) {
 					MessageUtil.promt(shell, "温馨提示", "您的数据不完整");
					e1.printStackTrace();
				}
				
			}
		});
		button_27.setBounds(185, 421, 80, 27);
		button_27.setText("保存");
		
		Button button_28 = new Button(com_updatefriend, SWT.NONE);
		button_28.addSelectionListener(new SelectionAdapter() {
			//退出编辑好友
			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl=com_tongxunlu;
				com_menu.layout();
				try{
//					List<Object> paramstongxunlu=new ArrayList<Object>();
//					paramstongxunlu.add(fromid);
					List<Map<String , Object>> list3=emailDAO.addressbook(fromid);
					table_tongxunlu.removeAll();
					if(null!= list3 &&list3.size()>0){
						TableItem tableItem;
						for(Map<String,Object> map:list3){
							System.out.println(map);
							System.out.println(map.get("PERSONID").toString());
							String friendTel=null;
							tableItem = new TableItem(table_tongxunlu,SWT.NONE);
							Map<String , Object> mapfriend=emailDAO.findFriendName(Integer.parseInt(map.get("PERSONID").toString()));
							String friendname=mapfriend.get("USERNAME").toString();
							System.out.println(mapfriend);
							if(mapfriend.get("TEL")==null){
								friendTel=friendTel=null;
							}else{
								friendTel=mapfriend.get("TEL").toString();
							}
//							tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,map.get("PERSONNAME").toString()," ",map.get("PERSONTYPE").toString()});
							if(map.get("PERSONNAME")==null&&mapfriend.get("TEL")==null){
								tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,"","",map.get("PERSONTYPE").toString()});
							}
							if(map.get("PERSONNAME")!=null&&mapfriend.get("TEL")==null){
								tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,map.get("PERSONNAME").toString(),"",map.get("PERSONTYPE").toString()});
							}
							if(map.get("PERSONNAME")==null&&mapfriend.get("TEL")!=null){
								tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,"",friendTel,map.get("PERSONTYPE").toString()});
							}
							if(map.get("PERSONNAME")!=null&&mapfriend.get("TEL")!=null){
								tableItem.setText(new String[]{map.get("PERSONID").toString(),friendname,map.get("PERSONNAME").toString(),friendTel,map.get("PERSONTYPE").toString()});
							}
						}
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_28.setBounds(374, 421, 80, 27);
		button_28.setText("取消");
		
		combo = new Combo(com_updatefriend, SWT.NONE);
		combo.select(0);
		combo.add("我的好友");
//		combo.add("同事");
		combo.setBounds(263, 345, 125, 25);
		
		text_friendtel = new Text(com_updatefriend, SWT.BORDER);
		text_friendtel.setBounds(263, 244, 178, 23);
		
		text_friendname = new Text(com_updatefriend, SWT.BORDER);
		text_friendname.setBounds(263, 194, 178, 23);
		
		text_friendid = new Text(com_updatefriend, SWT.BORDER);
		text_friendid.setBounds(263, 144, 178, 23);
		
		final Label label_logo = new Label(composite, SWT.NONE);
		//logo
		label_logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				label_logo.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/qq邮箱-图标.png"));
				stackLayout.topControl=com_main;
				com_menu.layout();
				try{
					List<Map<String , Object>> list=emailDAO.weiduCount(fromid);
					if(null!=list&&list.size()>0){
						for(Map<String , Object> map:list){
							string=map.get("COUNT1").toString();
						}
						label_count.setText(string);
					}else{
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		label_logo.setBounds(8, 2, 200, 59);
		
		final Label label_shoujianxiang = new Label(composite, SWT.NONE);
		label_shoujianxiang.addMouseListener(new MouseAdapter() {
			//收件箱界面
			@Override
			public void mouseDown(MouseEvent e) {
				label_shoujianxiang.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/收件箱-点击.png"));
				stackLayout.topControl=com_shoujianxiang;
				com_menu.layout();
				try{
					List<Object> params5=new ArrayList<Object>();
					params5.add(fromid);
					List<Map<String , Object>> list=emailDAO.shoujianxiang(params5);
					table_shoujianxiang.removeAll();

					if(null!=list&&list.size()>0){
						TableItem tableItem;
						for(Map<String , Object> map:list){
							tableItem=new TableItem(table_shoujianxiang, SWT.None);
							
							int emailid1=Integer.parseInt(map.get("EMAILID").toString());
							Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
							int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
							if(type==0){
								tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
								if(map.get("TITLE")==null){
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
								}else{
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
								}
//								tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
							}else{
//								tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
								if(map.get("TITLE")==null){
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
								}else{
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
								}
							}
						}
					}else{
						//MessageUtil.promt(shell, "温馨提示", "无学生信息");
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseUp(MouseEvent e) {
				label_shoujianxiang.setBackgroundImage(null);
			}
		});
		label_shoujianxiang.setBounds(19, 183, 158, 21);
		
		final Label label_xing = new Label(composite, SWT.NONE);
		label_xing.addMouseListener(new MouseAdapter() {
			//星标邮件
			@Override
			public void mouseDown(MouseEvent e) {
				label_xing.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/星标邮件-点击.png"));
				
			}
			@Override
			public void mouseUp(MouseEvent e) {
				label_xing.setBackgroundImage(null);
				MessageUtil.promt(shell, "提示",	 "此功能暂未开通，敬请期待！");
			}
		});
		label_xing.setBounds(19, 207, 158, 21);
		
		final Label label_qunshoujianxiang = new Label(composite, SWT.NONE);
		label_qunshoujianxiang.addMouseListener(new MouseAdapter() {
			//群收件箱
			@Override
			public void mouseDown(MouseEvent e) {
				label_qunshoujianxiang.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/群邮件-点击.png"));
				stackLayout.topControl=com_qunshoujianxiang;
				com_menu.layout();
				try{
					List<Object> params5=new ArrayList<Object>();
					params5.add(fromid);
					List<Map<String , Object>> list=emailDAO.Qunshoujian(fromid);
					table_qunshoujianxiang.removeAll();
					if(null!=list&&list.size()>0){
						TableItem tableItem;
						for(Map<String , Object> map:list){
							tableItem=new TableItem(table_qunshoujianxiang, SWT.None);
							int emailid1=Integer.parseInt(map.get("EMAILID").toString());
							Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
							int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
							if(type==0){
								tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
								if(map.get("TITLE")==null){
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA4").toString(),"(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
								}else{
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA4").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
								}
//								tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
							}else{
//								tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
								if(map.get("TITLE")==null){
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA4").toString(),"(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
								}else{
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA4").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
								}
							}
						}
					}else{
						//MessageUtil.promt(shell, "温馨提示", "无学生信息");
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseUp(MouseEvent e) {
				label_qunshoujianxiang.setBackgroundImage(null);
			}
		});
		label_qunshoujianxiang.setBounds(19, 231, 158, 21);
		
		final Label label_caogaoxiang = new Label(composite, SWT.NONE);
		label_caogaoxiang.addMouseListener(new MouseAdapter() {
			//草稿箱
			@Override
			public void mouseDown(MouseEvent e) {
				label_caogaoxiang.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/草稿箱-点击.png"));
				stackLayout.topControl=com_caogaoxiang;
				com_menu.layout();
				try{
					List<Object> params5=new ArrayList<Object>();
					params5.add(fromid);
					List<Map<String , Object>> list=emailDAO.caogaoxiang(params5);
					table_caogaoxiang.removeAll();
					if(null!=list&&list.size()>0){
						for(Map<String , Object> map:list){
							TableItem tableItem=new TableItem(table_caogaoxiang, SWT.None);
//							tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
							if(map.get("EMAILA2")==null&&map.get("TITLE")==null){
								tableItem.setText(new String[]{map.get("EMAILID").toString(),"","(无主题)",map.get("EMAIL_DATE").toString()});
							}
							if(map.get("EMAILA2")==null&&map.get("TITLE")!=null){
								tableItem.setText(new String[]{map.get("EMAILID").toString(),"",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
							}
							if(map.get("EMAILA2")!=null&&map.get("TITLE")==null){
								tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA2").toString(),"(无主题)",map.get("EMAIL_DATE").toString()});
							}
							if(map.get("EMAILA2")!=null&&map.get("TITLE")!=null){
								tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("EMAILA2").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
							}
						}
					}else{
						//MessageUtil.promt(shell, "温馨提示", "无学生信息");
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseUp(MouseEvent e) {
				label_caogaoxiang.setBackgroundImage(null);
			}
		});
		label_caogaoxiang.setBounds(19, 253, 158, 21);
		
		final Label label_yifasong = new Label(composite, SWT.NONE);
		label_yifasong.addMouseListener(new MouseAdapter() {
			//已发送界面
			@Override
			public void mouseDown(MouseEvent e) {
				label_yifasong.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/已发送-点击.png"));
				stackLayout.topControl=com_yifasong;
				com_menu.layout();
				try {
					List<Object> yifasongid=new ArrayList<Object>();
					yifasongid.add(fromid);
					List<Map<String, Object>> list=emailDAO.yifasong(yifasongid);
					table_yifasong.removeAll();
					if(null!=list&&list.size()>0){
//						for(Map<String, Object> map:list){
//							TableItem tableItem2=new TableItem(table_yifasong,SWT.NONE);
//							tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
//						}
						for(Map<String, Object> map:list){
							TableItem tableItem2=new TableItem(table_yifasong, SWT.NONE);
//							tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});	
							if(map.get("TITLE")==null){
								tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString()});
							}else{
								tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("TOID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
							}
						}
					}else{
					}
				} catch (SQLException e1) {
 					e1.printStackTrace();
				} catch (IOException e1) {
   					e1.printStackTrace();
				}
			}
			@Override
			public void mouseUp(MouseEvent e) {
				label_yifasong.setBackgroundImage(null);
			}
		});
		label_yifasong.setBounds(19, 276, 158, 21);
		
		final Label label_yishanchu = new Label(composite, SWT.NONE);
		label_yishanchu.addMouseListener(new MouseAdapter() {
			//已删除
			@Override
			public void mouseDown(MouseEvent e) {
				label_yishanchu.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/已删除-点击.png"));
				stackLayout.topControl=com_yishanchu;
				com_menu.layout();
				try {
					List<Object> params8=new ArrayList<Object>();
					params8.add(fromid);
					List<Map<String , Object>> list8=emailDAO.yishanchu(params8);
					table_yishanchu.removeAll();
					if(null!=list8&&list8.size()>0){
						for(Map<String, Object> map:list8){
							TableItem tableItem2=new TableItem(table_yishanchu, SWT.NONE);
//							tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString(),map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
							if(map.get("TITLE")==null){
							tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString()});
							}else{
								tableItem2.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString()});
							}
						}
					}else{
					}
				} catch (SQLException e1) {
 					e1.printStackTrace();
				} catch (IOException e1) {
 					e1.printStackTrace();
				}
			}
			@Override
			public void mouseUp(MouseEvent e) {
				label_yishanchu.setBackgroundImage(null);
			}
		});
		label_yishanchu.setBounds(19, 300, 158, 21);
		
		final Label label_lajixiang = new Label(composite, SWT.NONE);
		label_lajixiang.addMouseListener(new MouseAdapter() {
			//垃圾箱
			@Override
			public void mouseDown(MouseEvent e) {
				label_lajixiang.setBackgroundImage(SWTResourceManager.getImage(EmailMain.class,"/images/垃圾箱-点击.png"));
				stackLayout.topControl=com_lajixiang;
				com_menu.layout();
				try{
					List<Object> params5=new ArrayList<Object>();
					params5.add(fromid);
					List<Map<String , Object>> list=emailDAO.lajixiangEmail(params5);
					table_lajixiang.removeAll();

					if(null!=list&&list.size()>0){
						TableItem tableItem;
						for(Map<String , Object> map:list){
							tableItem=new TableItem(table_lajixiang, SWT.None);
							
							int emailid1=Integer.parseInt(map.get("EMAILID").toString());
							Map<String , Object> map1=readDAO.Emailreadtype(emailid1,Integer.parseInt(Content.userID));
							int type=Integer.parseInt(map1.get("INBOX_TYPE").toString());
							if(type==0){
								tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
								if(map.get("TITLE")==null){
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"未读"});
								}else{
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
								}
//								tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"未读"});
							}else{
//								tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
								if(map.get("TITLE")==null){
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com","(无主题)",map.get("EMAIL_DATE").toString(),"已读"});
								}else{
									tableItem.setText(new String[]{map.get("EMAILID").toString(),map.get("FROMID").toString()+"@qq.com",map.get("TITLE").toString(),map.get("EMAIL_DATE").toString(),"已读"});
								}
							}
						}
					}else{
						//MessageUtil.promt(shell, "温馨提示", "无学生信息");
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseUp(MouseEvent e) {
				label_lajixiang.setBackgroundImage(null);
			}
		});
		label_lajixiang.setBounds(19, 324, 158, 21);
		
		label_userid = new Label(composite, SWT.NONE);
		label_userid.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_userid.setBounds(214, 10, 280, 24);
		sashForm.setWeights(new int[] {1});
		label_userid.setText(Content.username+"<"+Content.userID+"@qq.com>");
		
		Label label_exit = new Label(composite, SWT.NONE);
		label_exit.addMouseListener(new MouseAdapter() {
			//注销
			@Override
			public void mouseDown(MouseEvent e) {
				boolean flag11 = MessageDialog.openConfirm(shell,  "温馨提示", "您确定要退出吗");
 			    if(flag11){
// 			    	Display.getDefault().close();
 			    	shell.close();
 					EmailLogin emailLogin=new EmailLogin();
 					emailLogin.open();
 			    }
			}
		});
		label_exit.setText(" ");
		label_exit.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_exit.setBounds(1071, 2, 41, 25);
		
		
		
		Label label_39 = new Label(composite, SWT.NONE);
		label_39.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		label_39.setAlignment(SWT.CENTER);
		label_39.setText(">");
		label_39.setBounds(895, 2, 25, 25);
		
		label_42 = new Label(composite, SWT.NONE);
		label_42.setImage(SWTResourceManager.getImage(EmailMain.class, "/images3/播放音乐1.png"));
//		label_42.addMouseListener(new MouseAdapter() {
//			//播放音乐
//			@Override
//			public void mouseDown(MouseEvent e) {
//				label_42.setImage(SWTResourceManager.getImage(EmailMain.class, "/images3/音乐播放2.png"));
//				//************************音乐
//				FileInputStream in = null;
//				//只能是wav或者mid这种格式的才能播放
//				try {
//					in = new FileInputStream(System.getProperty("user.dir")+"/src/com/yc/ui/a.wav");
//					as = new AudioStream(in);
//					AudioPlayer.player.start(as);
//				} catch (FileNotFoundException e1) {
// 					e1.printStackTrace();
//				} catch (IOException e1) {
// 					e1.printStackTrace();
//				}
//				
//			}
//		});
		label_42.setBounds(833, 2, 25, 25);
		
		Label label_37 = new Label(composite, SWT.NONE);
		label_37.setImage(SWTResourceManager.getImage(EmailMain.class, "/images3/停止音乐.png"));
//		label_37.addMouseListener(new MouseAdapter() {
//			//停止播放音乐
//			@Override
//			public void mouseDown(MouseEvent e) {
//				AudioPlayer.player.stop(as);
//				label_42.setImage(SWTResourceManager.getImage(EmailMain.class, "/images3/播放音乐1.png"));
//			}
//		});
		label_37.setBounds(864, 2, 25, 25);
		
		Label label_43 = new Label(composite, SWT.NONE);
		label_43.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		label_43.setText("<");
		label_43.setBounds(802, 2, 25, 25);
		
		
		
//		/**
//		 * 托盘
//		 */
//		final Menu menu2 = new Menu(shell);
//		final MenuItem mil = new MenuItem(menu2,SWT.PUSH);
//		mil.setText("隐藏窗口");
//		mil.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				if(shell.isVisible()){
//					shell.setVisible(false);
//					mil.setText("显示");
//				}else{
//					shell.setVisible(true);
//					mil.setText("隐藏");
//					shell.forceActive();
//				}
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//				// TODO Auto-generated method stub
//				widgetSelected(e);
//			}
//		});
//		
//		MenuItem quItem = new MenuItem(menu2, SWT.PUSH);
//		quItem.setText("退出");
//		quItem.addListener(SWT.Selection, new Listener(){
//
//			@Override
//			public void handleEvent(Event arg0) {
//				// TODO Auto-generated method stub
//				display.dispose();
//				System.exit(0);
//			}
//			
//		});
//		Tray tray = display.getSystemTray();
//		TrayItem trayItem = new TrayItem(tray, SWT.DROP_DOWN);
//		trayItem.setText("托盘");
//		trayItem.setToolTipText("系统托盘");
//		trayItem.setImage(new Image(display, "D://小图标.png"));
//		
//		trayItem.addMenuDetectListener(new MenuDetectListener() {
//			
//			@Override
//			public void menuDetected(MenuDetectEvent arg0) {
//				// TODO Auto-generated method stub
//				menu2.setLocation(display.getCursorLocation());
//				menu2.setVisible(true);
//			}
//		});
//		
//		
//		trayItem.addSelectionListener(new SelectionAdapter() {
//            public void widgetSelected(SelectionEvent e) {
//                 toggleDisplay(shell, tray);
//             }
//
//			private void toggleDisplay(Shell shell, Tray tray) {
//				// TODO Auto-generated method stub
//				try {
//		             shell.setVisible(!shell.isVisible());
//		             //tray.getItem(0).setVisible(!shell.isVisible());
//		            if (shell.getVisible()) {
//		                 shell.setMinimized(false);
//		                 shell.setActive();
//		             }
//		         } catch (Exception e) {
//		             e.printStackTrace();
//		         }
//			}
//         });
//
//        final Menu trayMenu = new Menu(shell, SWT.POP_UP);
//         MenuItem showMenuItem = new MenuItem(trayMenu, SWT.PUSH);
//         showMenuItem.setText("显示窗口(&s)");
//
//        //显示窗口，并显示系统栏中的图??
//         showMenuItem.addSelectionListener(new SelectionAdapter() {
//            public void widgetSelected(SelectionEvent event) {
//                 toggleDisplay(shell, tray);
//             }
//            
//            private void toggleDisplay(Shell shell, Tray tray) {
//				// TODO Auto-generated method stub
//				try {
//		             shell.setVisible(!shell.isVisible());
//		             //tray.getItem(0).setVisible(!shell.isVisible());
//		            if (shell.getVisible()) {
//		                 shell.setMinimized(false);
//		                 shell.setActive();
//		             }
//		         } catch (Exception e) {
//		             e.printStackTrace();
//		         }
//			}
//         });
//
//		
//		 shell.addShellListener(new ShellAdapter() {
//
//	            //点击窗口??小化按钮时，窗口隐藏??
//	            public void shellIconified(ShellEvent e) {
//	                 toggleDisplay(shell, tray);
//	             }
//
//	            //点击窗口关闭按钮时，并不终止程序，???时隐藏窗口，同时系统栏显示图标
//	            public void shellClosed(ShellEvent e) {
//	                 e.doit = false; //消???掉原本系统来处理的事件
//	                 toggleDisplay(shell, tray);
//	             }
//	            
//	            private void toggleDisplay(Shell shell, Tray tray) {
//					// TODO Auto-generated method stub
//					try {
//			             shell.setVisible(!shell.isVisible());
//			             //tray.getItem(0).setVisible(!shell.isVisible());
//			            if (shell.getVisible()) {
//			                 shell.setMinimized(false);
//			                 shell.setActive();
//			             }
//			         } catch (Exception e) {
//			             e.printStackTrace();
//			         }
//				}
//	         });
		new Thread(
				new Runnable(){
					@Override
					public void run() {
						while(1==1){
							Display.getDefault().asyncExec(new Runnable(){
								@Override
								public void run() {
									label_weather.setText(Tqyb.getCityWeather("1780")+"");									
								}								
							});
							try {
								Thread.sleep(1000000);
				     		} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}//休眠1秒比不休眠忙1秒  为什么
						}
					}					
				}				
		).start();
		
	}
	
	
	
	
	
	
	
	private void addNameTextFieldAssist(Text text) {
    	TextContentAdapter ad = new TextContentAdapter(){
    		/**
    		 * 在控件中添加该关键字
    		 * @param control
    		 * @param text
    		 * @param cursorPosition
    		 */
    		@Override
			public void insertControlContents(Control control, String text,int cursorPosition) {
				Point selection = ((Text) control).getSelection();
		        ((Text) control).insert(text);
		        if (cursorPosition < text.length()) {
		            ((Text) control).setSelection(selection.x + cursorPosition,  selection.x + cursorPosition);
		        }
			}
			/**
			 * 设置关键字
			 * @param control
			 * @param text
			 * @param cursorPosition
			 */
			@Override
			public void setControlContents(Control control, String text,
					int cursorPosition) {
    			int len = text_toid.getText().lastIndexOf(" ");
    			int l1=text_toid.getText().lastIndexOf("@");
    			String temp = text_toid.getText().substring(0,l1);
    			if(len==-1){
    				super.setControlContents(control, temp+text, cursorPosition);
    			}else{
					temp = temp.substring(0, temp.lastIndexOf(" "));
					temp = temp.concat(" ").concat(text);
					
					System.out.println(temp);
					
					
					super.setControlContents(control, temp, cursorPosition);
    			}
    			
    		}
			/**
    		 * 处理搜索关键字
    		 * @param control
    		 * @return
    		 */
			@Override
			public String getControlContents(Control control) {
				String temp = ((Text) control).getText();
				temp = temp.substring(temp.lastIndexOf("0")+1);
				temp = temp.substring(temp.lastIndexOf("1")+1);
				temp = temp.substring(temp.lastIndexOf("2")+1);
				temp = temp.substring(temp.lastIndexOf("3")+1);
				temp = temp.substring(temp.lastIndexOf("4")+1);
				temp = temp.substring(temp.lastIndexOf("5")+1);
				temp = temp.substring(temp.lastIndexOf("6")+1);
				temp = temp.substring(temp.lastIndexOf("7")+1);
				temp = temp.substring(temp.lastIndexOf("8")+1);
				temp = temp.substring(temp.lastIndexOf("9")+1);
				
				return temp;
			}
			public Point getSelection(Control control){
				
				return super.getSelection(text_toid);
			}
			@Override
			public Rectangle getInsertionBounds(Control control) {
				return super.getInsertionBounds(control);
			}
			@Override
			public void setSelection(Control control, Point range) {
				
				super.setSelection(control, range);
			}
    	};
    	
    	AutoCompleteField au = new AutoCompleteField(text,ad, new String[]{str1+"@qq.com","@163.com","@sina.com","@sina.cn"});
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("@qq.com");
    	list.add("@163.com");
    	list.add("@sina.com");
    	list.add("@sina.cn");
    	String strs[] = new String[list.size()];
	    for (int i = 0; i < strs.length; i++) {
			strs[i] = list.get(i).toLowerCase();
		}
	    au.setProposals(strs);
    }
	
	private void addNameTextFieldAssist1(Text text) {
    	TextContentAdapter ad = new TextContentAdapter(){
    		/**
    		 * 在控件中添加该关键字
    		 * @param control
    		 * @param text
    		 * @param cursorPosition
    		 */
    		@Override
			public void insertControlContents(Control control, String text,int cursorPosition) {
				Point selection = ((Text) control).getSelection();
		        ((Text) control).insert(text);
		        if (cursorPosition < text.length()) {
		            ((Text) control).setSelection(selection.x + cursorPosition,  selection.x + cursorPosition);
		        }
			}
			/**
			 * 设置关键字
			 * @param control
			 * @param text
			 * @param cursorPosition
			 */
			@Override
			public void setControlContents(Control control, String text,
					int cursorPosition) {
    			int len = text_quntoid.getText().lastIndexOf(" ");
    			int l1=text_quntoid.getText().lastIndexOf("@");
    			String temp = text_quntoid.getText().substring(0,l1);
    			if(len==-1){
    				super.setControlContents(control, temp+text, cursorPosition);
    			}else{
					temp = temp.substring(0, temp.lastIndexOf(" "));
					temp = temp.concat(" ").concat(text);
					
					System.out.println(temp);
					
					
					super.setControlContents(control, temp, cursorPosition);
    			}
    			
    		}
			/**
    		 * 处理搜索关键字
    		 * @param control
    		 * @return
    		 */
			@Override
			public String getControlContents(Control control) {
				String temp = ((Text) control).getText();
				temp = temp.substring(temp.lastIndexOf("0")+1);
				temp = temp.substring(temp.lastIndexOf("1")+1);
				temp = temp.substring(temp.lastIndexOf("2")+1);
				temp = temp.substring(temp.lastIndexOf("3")+1);
				temp = temp.substring(temp.lastIndexOf("4")+1);
				temp = temp.substring(temp.lastIndexOf("5")+1);
				temp = temp.substring(temp.lastIndexOf("6")+1);
				temp = temp.substring(temp.lastIndexOf("7")+1);
				temp = temp.substring(temp.lastIndexOf("8")+1);
				temp = temp.substring(temp.lastIndexOf("9")+1);
				
				return temp;
			}
			public Point getSelection(Control control){
				
				return super.getSelection(text_quntoid);
			}
			@Override
			public Rectangle getInsertionBounds(Control control) {
				return super.getInsertionBounds(control);
			}
			@Override
			public void setSelection(Control control, Point range) {
				
				super.setSelection(control, range);
			}
    	};
    	
    	AutoCompleteField au = new AutoCompleteField(text,ad, new String[]{str2+"@qq.com","@163.com","@sina.com","@sina.cn"});
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("@qq.com");
    	list.add("@163.com");
    	list.add("@sina.com");
    	list.add("@sina.cn");
    	String strs[] = new String[list.size()];
	    for (int i = 0; i < strs.length; i++) {
			strs[i] = list.get(i).toLowerCase();
		}
	    au.setProposals(strs);
    }
}
