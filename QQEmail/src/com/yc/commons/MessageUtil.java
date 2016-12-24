package com.yc.commons;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MessageUtil {
	public static void promt(Shell shell,String title,String message){
		MessageBox mes=new MessageBox(shell,SWT.NONE);
		mes.setText(title);
		mes.setMessage(message);
		//打开消息框
		mes.open();
		
	}
}
