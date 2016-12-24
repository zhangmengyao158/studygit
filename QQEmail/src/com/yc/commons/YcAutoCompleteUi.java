package com.yc.commons;
import java.util.ArrayList;
import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class YcAutoCompleteUi {

	protected Shell shell;
	private Text text_1;
	String str1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			YcAutoCompleteUi window = new YcAutoCompleteUi();
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
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(YcAutoCompleteUi.class, "/images/yc.ico"));
		shell.setSize(450, 300);
		shell.setText("自动提示");
		
		text_1 = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		text_1.setBounds(78, 28, 280, 120);

		//自动补齐
		text_1.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String temp = text_1.getText();
				//输入的文本框末尾不能为空    且它的前面是空字符
				int len = temp.length();
				if(len<3){
					return ;
				}
//				if((!(temp.charAt(len-1)+"").equals(" "))&&((temp.charAt(len-2)+"").equals(""))){
				if((!(temp.charAt(len-1)+"").equals(" "))&&((temp.charAt(len-2)+"").equals(""))){
					text_1.setSelection(len);
					str1= text_1.getText();
					addNameTextFieldAssist(text_1);
					
				}
			}
		});
		
		addNameTextFieldAssist(text_1);
		
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
    			int len = text_1.getText().lastIndexOf(" ");
    			String temp = text_1.getText();
    			if(len==-1){
    				super.setControlContents(control, text, cursorPosition);
    			}else{
					temp = temp.substring(0, temp.lastIndexOf(" "));
					temp = temp.concat(" ").concat(text);
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
//				temp = temp.substring(temp.lastIndexOf("")+113);
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
				
				return super.getSelection(text_1);
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
    	
    	AutoCompleteField au = new AutoCompleteField(text,ad , new String[]{str1+"@qq.com","@163.com","@sina.com","@sina.cn"});
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("@qq.com");
    	list.add("@163.com");
    	list.add("@sina.com");
    	list.add("@sina.cn");
//    	list.add("from");
//    	list.add("alter");
//    	list.add("create");
//    	list.add("table");
    	String strs[] = new String[list.size()];
	    for (int i = 0; i < strs.length; i++) {
			strs[i] = list.get(i).toLowerCase();
		}
	    au.setProposals(strs);
    }
}