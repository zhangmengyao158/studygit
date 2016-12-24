package com.yc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Tqyb {
	//服务地址
	private static String SERVICES_HOST = "www.webxml.com.cn";

	//查询天气国家
	//private static String WEATHER_COUNTRY_URL ="http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx/getRegionCountry";

	//获得中国省份、直辖市、地区和与之对应的ID
	//private static String WEATHER_PRVO_URL = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx/getRegionProvince";
	private static String WEATHER_PRVO_URL = "http://ws.webxml.com.cn/WebServices/WeatherWS.asmx/getRegionProvince";

	//查询城市
	private static String WEATHER_CITY_URL = "http://ws.webxml.com.cn/WebServices/WeatherWS.asmx/getSupportCityString?theRegionCode=";

	//天气查询地址
	private static String WEATHER_QUERY_URL ="http://ws.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather?theUserID=&theCityCode=";

	public static InputStream getSoapStream(String url ){
		InputStream inputStream=null;
		try {
			URL urlObj=new URL(url);
			/*
			 * 1.通过在URL上调用openConnection 方法创建连接对象
			 * 2.处理设置参数和一般请求属性
			 * 3.使用connect方法建立到远程对象的实际连接
			 * 4.远程对象变为可用，远程对象的头字段和内容变为可访问
			 */
			URLConnection conn=urlObj.openConnection();
			conn.setRequestProperty("Host", SERVICES_HOST);
			conn.connect();
			inputStream =conn.getInputStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputStream;

	}

	//获取省份信息
	public static  List<String> getProvCode(String url){
		List<String> provinces=new ArrayList<String>();
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=null;
		Document document=null;
		try {
			db=dbf.newDocumentBuilder();
			document=db.parse(getSoapStream(url));
			NodeList nodes=document.getElementsByTagName("string");
			for(int i=0;i<nodes.getLength();i++){
				Node node=nodes.item(i);
				String values=node.getFirstChild().getNodeValue().trim();
				provinces.add(values);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return provinces;
	}
	
	
	//获取城市信息
	/*provCode 省份编号
	 * url 城市xml地址
	 */
	public static List<String> getCityCode(String provCode){
		List<String> citys=new ArrayList<String>();
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document document=db.parse(getSoapStream(WEATHER_CITY_URL+provCode));
			NodeList nodes=document.getElementsByTagName("string");
			for(int i=0;i<nodes.getLength();i++){
				Node node=nodes.item(i);
				String value=node.getFirstChild().getNodeValue();
				citys.add(value);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return citys;
	}
	
	public static void main(String[] args) {
		System.out.println(Tqyb.getCityWeather("1780"));
	}
	
	//获取某个城市的天气
	/*
	 * cityCode 城市的编号
	 * weatherUrl 获取天气的url地址
	 * return
	 */
	public static List<String> getCityWeather(String cityCode){
		List<String> weathers=new ArrayList<String>();
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=null;
		try {
			db=dbf.newDocumentBuilder();
			Document document=db.parse(getSoapStream(WEATHER_QUERY_URL+cityCode));
			NodeList nodes=document.getElementsByTagName("string");
			for(int i=0;i<nodes.getLength();i++){
				Node node=nodes.item(i);
				String value=node.getFirstChild().getNodeValue();
				weathers.add(value);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weathers;

	}

	public static void tree(Shell shell,Tree tree){
		List<String> provs=getProvCode(WEATHER_PRVO_URL);//获取省份信息
		TreeItem tt;
		tt=new TreeItem(tree,SWT.NONE);
		tt.setText("查询地选择");
		TreeItem prov=null;
		if(null!=provs&&provs.size()>0){
			for(int i=0;i<provs.size();i++){
				prov=new TreeItem(tt,SWT.NONE);
				String temp=provs.get(i).trim();
				String provName=temp.substring(0, temp.indexOf(",")).toString().trim();
				String provCode=temp.substring(temp.indexOf(",")+1).toString().trim();
				prov.setText(provName);
				prov.setData("provCode", provCode);
			}
		}else{
			prompt(shell,"温馨提示","查无此信息！！");
			return ;
		}
	}

	public static void prompt(Shell shell,String title,String content){
		MessageBox mb=new MessageBox(shell,SWT.NONE);
		mb.setMessage(content);
		mb.setText(title);
		mb.open();
	}







}
