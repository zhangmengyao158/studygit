package com.yc.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;







import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.yc.commons.Content;
import com.yc.commons.MessageUtil;
import com.yc.dao.EmailDAO;
import com.yc.dao.ReadDAO;

import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;

public class Fujian {

	protected Shell shell;
	private Table table;
	EmailDAO emailDAO=new EmailDAO();
	ReadDAO readDAO=new ReadDAO();
	//int FEmailid=Integer.parseInt(Content.fujianEmailid);
	String string2 = null;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Fujian window = new Fujian();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 444, 272);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION|SWT.CHECK);
		
		table.setBounds(0, 0, 444, 272);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//根据复选框或取到所选的项
				//1.获取表格的所有项
				int j=table.getItemCount();
				int filesid=0;
				
				TableItem count1 =table.getItem(0);
				for(int i=0;i<j;i++){
					//判断是否被选中
					if(table.getItem(i).getChecked()==true){
						System.out.println(table.getItem(i).getText(0));
						String string=table.getItem(i).getText(0);
						string2=table.getItem(i).getText(1);
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
								File file = new File("c:\\"+string2);//将数据写入文件
								if(!file.exists()){
									file.createNewFile();
								}
//								try{
//									//Runtime.getRuntime().exec("D:/软件/plsqldev.exe");
//									Runtime.getRuntime().exec("cmd /c start c:\\test.txt");
//								}catch(Exception e2){
//									System.out.println("error");
//								}
								Desktop.getDesktop().open(new File("c:\\"+string2));
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
		menuItem.setText("查看");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("附件编号");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(283);
		tblclmnNewColumn_1.setText("文件名");
		try{
			List<Object> params=new ArrayList<Object>();
			params.add(Content.Emailidfujian);
			List<Map<String , Object>> list=readDAO.ReadFujian(params);
			table.removeAll();
			if(null!=list&&list.size()>0){
				for(Map<String , Object> map:list){
					TableItem tableItem=new TableItem(table, SWT.None);
					tableItem.setText(new String[]{map.get("FILESID").toString(),map.get("FILESNAME").toString()});
//					String string=map.get("FILESNAME").toString();
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
	
	
}
