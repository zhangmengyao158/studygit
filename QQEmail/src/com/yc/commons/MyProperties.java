package com.yc.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
// �̳�Properties �������Զ����MyPropertiesӵ��Properties�е���Դ
//���Ϊ����ģʽ
public class MyProperties extends Properties {
   private static MyProperties myProperties;
   
   private MyProperties() throws IOException {
	   InputStream in = MyProperties.class.getClassLoader().getResourceAsStream("db.properties");
	   this.load(in);
   }
   
   public static MyProperties getInstance() throws IOException{
	   if(null==myProperties){
		   return new MyProperties();
	   }
	   return myProperties;
   }
}
