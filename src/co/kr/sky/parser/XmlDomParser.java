package co.kr.sky.parser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

public abstract class XmlDomParser {
	
	// 정보를 가져오거나 정보를 입력할 doc
	protected Document doc;
	private static final String TAG = "XmlDomParser";
	private String mXmlPath="";
	
	/**
	 * xml을 입력받아 파싱
	 * @param xml
	 */
	public XmlDomParser(String xml) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream istream = new ByteArrayInputStream(xml.getBytes("utf-8"));
			doc = builder.parse(istream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 파일을 입력받아 파싱
	 * @param file
	 */
	public XmlDomParser(File file) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream istream = new FileInputStream(file);
			mXmlPath = file.getAbsolutePath();
			doc = builder.parse(istream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void xmlToFile(String xml, String filePath) {
		try{
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(new File(filePath));
			Source input = new DOMSource(doc);
			transformer.transform(input, output);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected String findNodeValue(String tag) {
		Log.d(TAG, "find node value key="+tag);
		try{
			Element order = doc.getDocumentElement();
			NodeList nodeList = order.getElementsByTagName(tag);
			Node element = nodeList.item(0);
			Node text = element.getFirstChild();
			return text.getNodeValue();
		}catch(Exception e){
			Log.e(TAG,e.getMessage()+"("+tag+")");
			return "";
		}
	}
	
	/**
	 * 특정 element에 값을 대입 
	 * @param elementName
	 * @param xmlValue
	 */
	public void addElementValue(String elementName, String xmlValue){
		Log.d(TAG, "add element name = "+elementName +", value" +xmlValue);
		NodeList items = findNodeList(elementName);
		
		Node item = items.item(0);
		if(item == null){
			Log.w(TAG, "add element error item "+elementName+" is null ");
			return;
		}
		item.setTextContent(xmlValue);
		if(!"".equals(mXmlPath)){
			changeFile();
		}			
	}
	
	/**
	 * 특정 element집합의 특정 위치에 특정값을 삽입
	 * @param elementName
	 * @param xmlValue
	 * @param idx
	 */
	public void addElementValues(String elementName, String xmlValue, int idx){
		Log.d(TAG, "add element name = "+elementName +", value" +xmlValue+ ", index="+idx);
		NodeList items = findNodeList(elementName);
		if(items == null){
			Log.e(TAG, "item "+elementName+" is null ");
		}else{
			Node item = items.item(idx);
			item.setTextContent(xmlValue);
			if(!"".equals(mXmlPath)){
				changeFile();
			}		
		}
	}
	
	public void addElement(String rootElement, String element){
		Log.d(TAG, "add element root = "+rootElement +", element" +element);
		NodeList items = findNodeList(rootElement);
		Node item = items.item(0);
		item.appendChild(doc.createElement(element));
		if(!"".equals(mXmlPath)){
			changeFile();
		}
	}
	
	public void changeFile(){
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(mXmlPath));
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			Log.e(TAG, "file change error ",e);
		} catch (TransformerException e) {
			Log.e(TAG, "file change error ",e);
		}
	}
	
	protected String findNodeValue(Element order, String tag){
		Log.d(TAG, "find node value key="+tag);
		try{
			NodeList nodeList = order.getElementsByTagName(tag);
			Node element = nodeList.item(0);
			Node text = element.getFirstChild();
			return text.getNodeValue();
		}catch(Exception e){
			Log.e(TAG,e.getMessage()+"("+tag+")");
			return "";
		}
	}

	protected NodeList findNodeList(String tag) {
		Element order = doc.getDocumentElement();
		NodeList items = order.getElementsByTagName(tag);
		return items;
	}

	/**
	 * 객체 타입에 xml을 맵핑
	 * 
	 * @param xml
	 * @param vo
	 * @return 맵핑된 객체타입
	 */
	abstract Object parse();
}
