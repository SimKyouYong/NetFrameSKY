package co.kr.sky.parser;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.util.Log;
import co.kr.sky.model.DocumentInfo;
import co.kr.sky.model.SubscriptionInfo;

/**
 * XML파서 
 * 
 * @author SPKOO
 */
public class DocInfoParser extends XmlDomParser{

	private final static String TAG = "DocInfoParser";

	private String[] header;
	private String[] list;
	public DocInfoParser(File file, boolean needInit) {
		super(file);
		if(needInit){
		}
	}

	public DocInfoParser(String xml, boolean needInit,String[] _header , String[] _list) {
		super(xml);
		header = _header;
		list = _list;
		if(needInit){
		}
	}


	@Override
	public DocumentInfo parse() {
		
		ArrayList<String> arr = new ArrayList<String>();
		for (int i = 0; i < header.length; i++) {
			arr.add(findNodeValue(header[i]));
			Log.d(TAG, "OBJ_HEADER  ->" + arr.get(i));
		}
		
		ArrayList<ArrayList<String>> arraylist = new ArrayList<ArrayList<String>>();
		ArrayList<String> arr1 = new ArrayList<String>();
		NodeList pdfList = findNodeList("record");
		String[][] ARR_LIST = new String[pdfList.getLength()][list.length];

		for (int i = 0; i < pdfList.getLength(); i++) {
			for (int j = 0; j < list.length; j++) {
				Element element = (Element) pdfList.item(i);
				ARR_LIST[i][j] = findNodeValue(element, list[j]);
//				arr1.add(findNodeValue(element, list[j]));
				Log.e(TAG, "OBJ_LIST  ->" + list[j] + "::" + findNodeValue(element, list[j]));
			}
			arraylist.add(arr1); 
			arr1.clear();
		}
		
		String[] OBJ_HEADER = arr.toArray(new String[arr.size()]);
		
		
//		String[][] ARR_LIST = new String[arraylist.size()][];
//		for (int i = 0; i < arraylist.size(); i++) {
//			for (int j = 0; j < arraylist.get(i).size(); j++) {
//				ArrayList<String> row = arraylist.get(i);
//			    ARR_LIST[j] = row.toArray(new String[row.size()]);
//			}
//		}
		for (int j = 0; j < ARR_LIST.length; j++) {
			for (int j2 = 0; j2 < ARR_LIST[j].length; j2++) {
				Log.e("SKY" ,"val:: ARR_LIST  value----> ---> Object_Array [" +j+"]["+j2+"]"+  ARR_LIST[j][j2]);
			}
		}
		
		
		DocumentInfo obj = new DocumentInfo();
		obj.setHeader(OBJ_HEADER);
		obj.setList(ARR_LIST);
		return obj;
	}

}
