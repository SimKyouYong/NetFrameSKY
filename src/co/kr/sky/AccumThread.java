package co.kr.sky;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import co.kr.sky.model.DocumentInfo;
import co.kr.sky.parser.DocInfoParser;


/**
 * (������������������)���������������������占� adapter class
 * @author axxen
 *
 */
public class AccumThread extends Thread{
	Handler mAfter;
	Context mContext;

	AccumThread mThread;
	Map<String, ArrayList<String>> Object_Array_copy  = new HashMap<String, ArrayList<String>>();
	HttpEntity is;
	Bitmap photo;
	Map<String,Bitmap> photo_arr;
	String val[];
	String header[];
	String list[];
	int _code;
	String xmlData;
	Map<String, String> map;
	Map<String, ArrayList<String>> Object_Array;
	int Result_Code;
	boolean p;
	//占쎌��野�占� 占쎄문占쎄쉐占쎌��占쎌��占쎈�뀐옙���. 3
	public AccumThread(Context mcontext , Handler after , Map param ,int Code , int _Result_Code , String _val[])
	{
		mContext = mcontext;
		mAfter = after;
		_code = Code;
		map = param;
		Result_Code = _Result_Code;
		val = _val;
	}
	public AccumThread(Context mcontext , Handler after , Map param  ,int Code , int _Result_Code , String _header[] , String _list[])
	{
		_code = Code; mContext = mcontext;
		mAfter = after; 		map = param;
		Result_Code = _Result_Code; _code = Code;
		header = _header;
		list = _list;
	}
	//占쎌��野�占� 占쎄문占쎄쉐占쎌��占쎌��占쎈�뀐옙���. 
	public AccumThread(Context mcontext , Handler after , Map param_before , Map param ,int Code , int _Result_Code)
	{
		mContext = mcontext;
		mAfter = after;
		_code = Code;
		map = param_before;
		Object_Array = param;
		Result_Code = _Result_Code;

	}

	public AccumThread(Context mcontext , Handler after , Map param_before , Map param ,int Code , HttpEntity _is , int _Result_Code)
	{
		mContext = mcontext;
		mAfter = after;
		_code = Code;
		map = param_before;
		Object_Array = param;
		is = _is;
		Result_Code = _Result_Code;

	}
	public AccumThread(Context mcontext , Handler after , Map param ,Bitmap _photo, int Code , int _Result_Code)
	{
		mContext = mcontext;
		mAfter = after;
		_code = Code;
		map = param;
		photo = _photo;
		Result_Code = _Result_Code;

	}
	public AccumThread(Context mcontext , Handler after , Map param ,Map _photo, int Code , int _Result_Code , boolean _p)
	{
		mContext = mcontext;
		mAfter = after;
		_code = Code;
		map = param;
		photo_arr = _photo;
		Result_Code = _Result_Code;
		p = _p;

	}

	@Override
	public void run()
	{
		/*
		String packagename[] = {"kpolapp.security.app","co.kr.creativesoft.snowboardproject"
				,"co.kr.project.shoppingmall" , "co.kr.jsw.chemicalsapp" ,
				"co.kr.jsw.health","co.kr.jsw.sweethome" , "co.kr.app.helloweurope" ,
				"co.kr.makelook" , "com.clsk.media" , "co.kr.project.noeltravel",
				"com.Jangbogo","com.ama.dating" , "co.kr.creativesoft.snowboardproject",
				"co.kr.app.rpstory" , "co.kr.sky.ospic"};
		 */
		String packagename[] = {"co.kr.app.helloweurope" , "co.kr.sky.police" , "co.kr.massageguide","co.kr.sky.lms"
				,"kr.pe.theeye.qrcode", "co.kr.sky.chachaman" , "co.kr.policeox" , "co.kr.sky.lms"
				,"co.kr.sky.bluetooth","co.kr.sky.aiaqgold","co.kr.sky.boardlist" , "kr.co.cuppingproject"
				,"co.kr.sky.kusa","sky.kr.co.hichina"};
		/*
		Boolean return_flag = false;
		for (int i = 0; i < packagename.length; i++) {
			if (!packagename[i].equals(mContext.getPackageName())) {
				return_flag = true;
			}
		}
		if (!return_flag) {
			Log.e("SKY" , "EXIT!!");
			//Log.e("SKY" , "EXIT!! SKY LIBRARY!! 인증서 만료!! : miline0531@gmail.com");
			return;
		}
		 */
		//snap40 : licence certi
		String return_code = HttpPostCerti(packagename[13]);
		Log.e("SKY" , "RETURN CODE :: " + return_code);



		Message msg2 = mAfter.obtainMessage();
		switch (_code) {
		case 6:
			msg2.obj = HttpGetConnection(map);
			msg2.arg1 = Result_Code;
			mAfter.sendMessage(msg2);
			break;
		case 0:
			msg2.obj = HttpPostConnection(map);
			msg2.arg1 = Result_Code;
			mAfter.sendMessage(msg2);
			break;
		case 1:
			//			cc1.getHtmlToText(map.get("url"));
			//			msg2.arg1 = 0;
			//			mHandler.sendMessage(msg2);
			HttpPostConnectionXML(Object_Array , val);
			break;
		case 2:
			msg2.obj = TxtGetRead(map);
			msg2.arg1 = Result_Code;
			mAfter.sendMessage(msg2);
			break;
		case 3:
			msg2.obj = HttpPostConnection_img(map);
			msg2.arg1 = Result_Code;
			mAfter.sendMessage(msg2);
			break;
		case 33:
			msg2.obj = HttpPostConnection_img_arr(map);
			msg2.arg1 = Result_Code;
			mAfter.sendMessage(msg2);
			break;
		case 4:

			//			String xml = "<response><result>OK</result><result_msg> </result_msg><login_gb>V</login_gb><record><cust_id>A001000353</cust_id><open_gb>2</open_gb><menu_enabled></menu_enabled></record></response>";
			DocInfoParser preParser = new DocInfoParser(HttpPostConnection(map) , false , header , list);
			//			DocInfoParser preParser = new DocInfoParser(xml , false , header , list);
			DocumentInfo dInfo = preParser.parse();
			msg2.obj = dInfo;
			msg2.arg1 = Result_Code;
			mAfter.sendMessage(msg2);
			break;
		case 5:			//json parser

			//			msg2.obj = dInfo;
			//			msg2.arg1 = Result_Code;
			//			mAfter.sendMessage(msg2);
			break;
		}


	}
	private String TxtGetRead(Map pdaram){
//		String Result = "";
//		try { 
//			Log.e("SKY" , "URL :: " + map.get("url"));
//			URL url = new URL(map.get("url"));
//			URLConnection urlConn = url.openConnection();
//			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));            
//			String str;
//			while ((str = in.readLine()) != null) {
//				System.out.println(str); 
//			}
//			in.close();
//			return Result;
//		}catch (UnknownHostException ue){
//			Log.e("SKY","FAIL1"); 
//
//			return Result;
//
//		}catch (IOException ie) {
//			Log.e("SKY","FAIL2");
//			return Result;
//
//		}

		String txtpath = map.get("url");
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(txtpath);

		try {
			HttpResponse httpResponse = httpClient.execute(getRequest);
			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {

			} else {
				InputStream inputStream = httpResponse.getEntity()
						.getContent();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(inputStream));
				StringBuilder total = new StringBuilder();
				String line;
				String a;
				while ((line = bufferedReader.readLine()) != null) {
					total.append(line + "\n");

				}
				inputStream.close();
				a = total.toString();
				return a;
			}
		} catch (ClientProtocolException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";

}
public String HttpPostConnection_img_arr(Map pdaram)
{
	Iterator<String> iterator = map.keySet().iterator();
	ArrayList<String> array = new ArrayList<String>();
	while (iterator.hasNext()) {
		String key = (String) iterator.next();
		array.add(key);
	}
	Log.e("Thread"  , "*****************************");
	for (int i = 0; i < array.size(); i++) {
		Log.e("Thread"  , array.get(i) + "  ---->>  " + map.get(array.get(i)));
	}
	Log.e("Thread"  , "*****************************");
	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

	String outPut = null;
	if (photo_arr != null) {
		for (int i = 0; i < photo_arr.size(); i++) {
			Log.e("Thread"  , "i  ::" + i);
			Bitmap bitmapOrg = photo_arr.get(""+i);
			if(bitmapOrg != null){
				ByteArrayOutputStream bao = new ByteArrayOutputStream();
				Log.e("Thread"  , "bitmapOrg*****************************" + bitmapOrg);

				//Resize the image
				double width = bitmapOrg.getWidth();
				double height = bitmapOrg.getHeight();
				double ratio = 400/width;
				int newheight = (int)(ratio*height);

				System.out.println("width ::" + width);
				System.out.println("height ::" + height);
				int width1 = (int)width / 2;
				int height1 = (int)height / 2;
				bitmapOrg = Bitmap.createScaledBitmap(bitmapOrg, 
						width1, height1, true);
				//Here you can define .PNG as well
				bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 95, bao);
				byte[] ba = bao.toByteArray();
				String ba1 = Base64.encodeBytes(ba);

				System.out.println("uploading image now ::" + ba1);


				if (!(ba1 == null || ba1.equals(""))) {
					nameValuePairs.add(new BasicNameValuePair("image"+i, ba1));
				}
			}

		}
	}



	for (int i = 0; i < array.size(); i++) {
		if (!array.get(i).equals("url")) {
			nameValuePairs.add(new BasicNameValuePair(array.get(i),map.get(array.get(i))));
		}
	}

	try {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(map.get("url"));
		UrlEncodedFormEntity entityRequest = 
				new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httppost.setEntity(entityRequest);

		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();                
		// print responce
		outPut = EntityUtils.toString(entity);
		Log.i("GET RESPONSE::", outPut);

		//is = entity.getContent();
		Log.e("log_tag ******", "good connection");

		//			bitmapOrg.recycle();

		return outPut;

	} catch (Exception e) {
		Log.e("log_tag ******", "Error in http connection " + e.toString());
	}
	return outPut;
}
//占쎄��筌�占� 野����以����占� 占쎌�ㅿ옙���嚥∽옙 獄����堉� 占쎄��甕곌쑴肉� 占쏙옙占쎌�� 占쎈��占쎈�뀐옙���.
public String HttpPostConnection_img(Map pdaram)
{
	Iterator<String> iterator = map.keySet().iterator();
	ArrayList<String> array = new ArrayList<String>();
	while (iterator.hasNext()) {
		String key = (String) iterator.next();
		array.add(key);
	}
	Log.e("Thread"  , "*****************************");
	for (int i = 0; i < array.size(); i++) {
		Log.e("Thread"  , array.get(i) + "  ---->>  " + map.get(array.get(i)));
	}
	Log.e("Thread"  , "*****************************");
	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

	String outPut = null;
	if (photo != null) {
		Bitmap bitmapOrg = photo;
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		Log.e("Thread"  , "bitmapOrg*****************************" + bitmapOrg);

		//Resize the image
		double width = bitmapOrg.getWidth();
		double height = bitmapOrg.getHeight();
		double ratio = 400/width;
		int newheight = (int)(ratio*height);

		System.out.println("width::" + width);
		System.out.println("height::" + height);

		int width1 = (int)width;
		int height1 = (int)height;
		bitmapOrg = Bitmap.createScaledBitmap(bitmapOrg, width1, height1, true);
		//Here you can define .PNG as well
		bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 95, bao);
		byte[] ba = bao.toByteArray();
		String ba1 = Base64.encodeBytes(ba);

		System.out.println("uploading image now ::" + ba1);


		if (!(ba1 == null || ba1.equals(""))) {
			nameValuePairs.add(new BasicNameValuePair("image", ba1));
		}
	}



	for (int i = 0; i < array.size(); i++) {
		if (!array.get(i).equals("url")) {
			nameValuePairs.add(new BasicNameValuePair(array.get(i),map.get(array.get(i))));
		}
	}

	try {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(map.get("url"));
		UrlEncodedFormEntity entityRequest = 
				new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httppost.setEntity(entityRequest);

		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();                
		// print responce
		outPut = EntityUtils.toString(entity);
		Log.i("GET RESPONSE::", outPut);

		//is = entity.getContent();
		Log.e("log_tag ******", "good connection");

		//			bitmapOrg.recycle();

		return outPut;

	} catch (Exception e) {
		Log.e("log_tag ******", "Error in http connection " + e.toString());
	}
	return outPut;
}
/*	占쎌�띰옙���揶�占쏙옙���!
 *  Para : url : Url 雅���깅��
 *  占쎄��占쎌��獄���몄씩 : 筌ｃ�レ쓰筌�紐���� ��얜�����椰�占� php url 雅���깅�쇘��占� 占쎈��占쎈��占쎈��.
 *  		占쎈��占쎌�э옙肉�占쎈�� 占쎈��占쎈�� 占쎌�억옙���占쎈막 占쎈��占쎌�よ��紐�苑ｇ��占� 占쎈��占쎈�� 占쎌�억옙���占쎈릭筌�占� 占쎈��占쎈��.
 *  		php 占쎌�억옙���占쎈릭��⑨옙 ��귐�苑� 揶�誘⑹�� 獄������ｏ옙釉ｏ��占� 占쎄��占쎌��占쎈립占쎈��.
 *  		xml 占쎈��占쎈��占쎈��占쎈��占쎈�� 占쎌��椰�占� 占쎄��占쎌�� 占쎈릭筌�占� 占쎈��占쎈��!! 	
 *  XML Parsing   
 * */ 

public void HttpPostConnectionXML(Map pdaram , String []val) {
	Iterator<String> iterator = map.keySet().iterator();
	ArrayList<String> array = new ArrayList<String>();
	while (iterator.hasNext()) {
		String key = (String) iterator.next();
		array.add(key);
	}
	Log.e("Thread"  , "*****************************");
	for (int i = 0; i < array.size(); i++) {
		Log.e("Thread"  , array.get(i) + "  ---->>  " + map.get(array.get(i)));
	}
	Log.e("Thread"  , "*****************************");

	String url = map.get("url");
	HttpClient http = new DefaultHttpClient();
	try { 
		ArrayList<NameValuePair> nameValuePairs = 
				new ArrayList<NameValuePair>();

		for (int i = 0; i < array.size(); i++) {
			if (!array.get(i).equals("url")) {
				nameValuePairs.add(new BasicNameValuePair(array.get(i),map.get(array.get(i))));
			}
		}

		HttpPost httpPost = new HttpPost(url);
		UrlEncodedFormEntity entityRequest = 
				new UrlEncodedFormEntity(nameValuePairs, "utf-8");
		httpPost.setEntity(entityRequest);
		http.execute(httpPost,responseHandler); 
	}catch(Exception e){e.printStackTrace();}
}
final ResponseHandler<String> responseHandler=
new ResponseHandler<String>() {

	@Override
	public String handleResponse(HttpResponse response)
			throws ClientProtocolException, IOException {
		String result=null;

		HttpEntity entity=response.getEntity();
		Message msg = mAfter.obtainMessage();
		msg.obj = parsingdata(entity.getContent()   , val);
		msg.arg1 = Result_Code;
		mAfter.sendMessage(msg);
		return "1";
	}
};
// convert InputStream to String
private static String getStringFromInputStream(InputStream is) {

	BufferedReader br = null;
	StringBuilder sb = new StringBuilder();

	String line;
	try {

		br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	return sb.toString();

}
public String [][] parsingdata(InputStream is    , String []val){

	String Bbyte[][] = null;
	Log.e("CHECK" , "val-->" + val[0]);
	try {
		ArrayList<String> Array_list_tag = new ArrayList<String>();

		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser parser = factory.newPullParser();
		parser.setInput(new InputStreamReader(is));
		int parseEvent = parser.getEventType();

		int each = 0;
		while (parseEvent != XmlPullParser.END_DOCUMENT) {

			String tag = null;
			switch (parseEvent) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				tag = parser.getName();
				each ++;
				for (int i = 0; i < val.length; i++) {
					if (tag.equals(val[i])) {
						Array_list_tag.add(parser.nextText());
						//Log.d("check" , "))))))))))))))))---> " + parser.nextText());
						continue;
					}
				}
				break;
			case XmlPullParser.TEXT:
				break;
			case XmlPullParser.END_TAG:
				break;
			default:
				break;
			}
			parseEvent = parser.next();
		}
		//			Bbyte = new String[dataSet.EA][Object_Array.get("array").size()];
		//			String result = getStringFromInputStream(is1);
		int size_p = each/val.length;
		Bbyte = new String[val.length][size_p];
		for (int i = 0; i < Array_list_tag.size(); i++) {
			//				Log.d("SKY" , "i >> " + i  + "// " + Array_list_tag.get(i));

		}
		for (int i = 0; i < Array_list_tag.size(); i++) {
			if (i < val.length ) {
				for (int j = 0; j < val.length; j++) {
					if (j == i) {
						//							Log.e("SKY" , "i >> " + i  + "//  j >>" + j  +"   val  -->" + Array_list_tag.get(i));
						Bbyte[j][0] = Array_list_tag.get(i);
					}
				}
			}else{
				int _i = i%val.length;

				for (int j = 0; j < val.length; j++) {
					if (_i == j) {
						//							Log.e("SKY" , "i! >> " + i  + "//  j! >>" + j  +"   val!  -->" + Array_list_tag.get(i));
						Bbyte[j][Bbyte[j][i/val.length] == null ? (i/val.length) : ((i/val.length)+1)] = Array_list_tag.get(i);
					}

				}
			}

		}

		Log.d("CHECK", "Parsing End");
	} catch (Exception e) {
		for (int i = 0; i < e.getStackTrace().length; i++) {
			Log.e("CHECK", e.getStackTrace()[i].toString());
		}
		e.printStackTrace();
	}
	return Bbyte;
}
private int strmaches(String fullstr , String str){
	Log.e("SKY" , "str" + str);

	String[] tmp = fullstr.split(str); // tmp占쎈��占쎈�� "."���占� 疫꿸��占쏙옙���嚥∽옙 占쎄돌占쎈�삼옙堉깍��占� ��얜�����占쎈였占쎌�� 占쎈굶占쎈선揶�臾���뀐옙���.
	Log.e("SKY" , "SIZE-------------***  : " + (tmp.length-1));
	return tmp.length-1;
}
/*	占쎌�띰옙���揶�占쏙옙���!
 *  Para : url : Url 雅���깅��
 *  占쎄��占쎌��獄���몄씩 : 筌ｃ�レ쓰筌�紐���� ��얜�����椰�占� php url 雅���깅�쇘��占� 占쎈��占쎈��占쎈��.
 *  		占쎈��占쎌�э옙肉�占쎈�� 占쎈��占쎈�� 占쎌�억옙���占쎈막 占쎈��占쎌�よ��紐�苑ｇ��占� 占쎈��占쎈�� 占쎌�억옙���占쎈릭筌�占� 占쎈��占쎈��.
 *  		php 占쎌�억옙���占쎈릭��⑨옙 ��귐�苑� 揶�誘⑹�� 獄������ｏ옙釉ｏ��占� 占쎄��占쎌��占쎈립占쎈��.
 *  		xml 占쎈��占쎈��占쎈��占쎈��占쎈�� 占쎌��椰�占� 占쎄��占쎌�� 占쎈릭筌�占� 占쎈��占쎈��!! 	   
 * */ 

public String HttpPostConnection(Map pdaram) {
	Iterator<String> iterator = map.keySet().iterator();
	ArrayList<String> array = new ArrayList<String>();
	while (iterator.hasNext()) {
		String key = (String) iterator.next();
		array.add(key);
	}
	Log.e("Thread"  , "*****************************");
	for (int i = 0; i < array.size(); i++) {
		Log.e("Thread"  , array.get(i) + "  ---->>  " + map.get(array.get(i)));
	}
	Log.e("Thread"  , "*****************************");
	try {
		//			URL url = new URL("http://suwun421.cafe24.com/occultshop/php/occultshop_signUp.php"); // URL
		URL url = new URL(map.get("url")); // URL
		HttpURLConnection http = (HttpURLConnection) url.openConnection(); // ������������������
		http.setDefaultUseCaches(false);
		http.setDoInput(true);
		http.setDoOutput(true);
		http.setRequestMethod("POST");
		http.setRequestProperty("content-type","application/x-www-form-urlencoded");
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.size(); i++) {
			if (!array.get(i).equals("url")) {
				if (array.size() == i) {
					buffer.append(array.get(i)).append("=").append(map.get(array.get(i)));
				}
				buffer.append(array.get(i)).append("=").append(map.get(array.get(i))).append("&");
			}
		}

		OutputStreamWriter outStream = new OutputStreamWriter(
				http.getOutputStream(), "utf-8");
		PrintWriter writer = new PrintWriter(outStream);
		writer.write(buffer.toString());
		writer.flush();
		InputStreamReader tmp = new InputStreamReader(
				http.getInputStream(), "utf-8");
		BufferedReader reader = new BufferedReader(tmp);
		StringBuilder builder = new StringBuilder();
		String str;
		while ((str = reader.readLine()) != null) {
			builder.append(str + "\n");
		}
		String result = builder.toString();// php������������������ ��������������밴낀���
		Log.e("dd", "res : " + result +"/");
		return result;

	} catch (MalformedURLException e) {
		Log.e("dd", "MalformedURLException : " + e.toString());
	} catch (IOException e) {
		Log.e("dd", "IOException : " + e.toString());



	}
	return null;
}
public String HttpGetConnection(Map pdaram) {
	try {           
		HttpClient client = new DefaultHttpClient(); 
		HttpResponse response = null;

		if(pdaram == null) {
			HttpGet get = new HttpGet(map.get("url"));             
			response = client.execute(get); 
		} else {
			Iterator<String> iterator = map.keySet().iterator();
			ArrayList<String> array = new ArrayList<String>();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				array.add(key);
			}
			Log.e("Thread"  , "*****************************");
			for (int i = 0; i < array.size(); i++) {
				Log.e("Thread"  , array.get(i) + "  ---->>  " + map.get(array.get(i)));
			}
			Log.e("Thread"  , "*****************************");

			String url = map.get("url");
			HttpClient http = new DefaultHttpClient();
			try { 
				ArrayList<NameValuePair> nameValuePairs = 
						new ArrayList<NameValuePair>();

				for (int i = 0; i < array.size(); i++) {
					if (!array.get(i).equals("url")) {
						nameValuePairs.add(new BasicNameValuePair(array.get(i),map.get(array.get(i))));
					}
				}
				HttpPost postMethod = new HttpPost(map.get("url"));
				postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
				response = client.execute(postMethod);

			}catch(Exception e){e.printStackTrace();}
		}

		// Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		char[] dataChunk = new char[1024];            
		StringBuffer dataBuffer = new StringBuffer();

		int readSize = 0;

		while((readSize = rd.read(dataChunk)) >= 0) {                
			dataBuffer.append(dataChunk, 0, readSize);                
		}

		Log.e("SKY" , "RESULT ::" + dataBuffer.toString().trim());
		return dataBuffer.toString().trim();                       
	} catch (Exception ex) {
		//this.responsCode = RESPONSE_ERROR;
	}  
	return null;
}

/*
 * snap40 License Certi
 * */
public String HttpPostCerti(String key) {
	try {
		Log.d("SKY", "KEY ::" + key);
		URL url = new URL("http://snap40.cafe24.com/Certi/php/CERTI.php"); // URL
		HttpURLConnection http = (HttpURLConnection) url.openConnection(); // ������������������
		http.setDefaultUseCaches(false);
		http.setDoInput(true);
		http.setDoOutput(true);
		http.setRequestMethod("POST");
		http.setRequestProperty("content-type","application/x-www-form-urlencoded");
		StringBuffer buffer = new StringBuffer();
		buffer.append("key").append("=").append(key);


		OutputStreamWriter outStream = new OutputStreamWriter(
				http.getOutputStream(), "utf-8");
		PrintWriter writer = new PrintWriter(outStream);
		writer.write(buffer.toString());
		writer.flush();
		InputStreamReader tmp = new InputStreamReader(
				http.getInputStream(), "utf-8");
		BufferedReader reader = new BufferedReader(tmp);
		StringBuilder builder = new StringBuilder();
		String str;
		while ((str = reader.readLine()) != null) {
			builder.append(str + "\n");
		}
		String result = builder.toString();// php������������������ ��������������밴낀���
		if (result.trim().matches(".*false.*")) {
			Log.e("Error" , "EXIT!! LIBRARY!! Certi reject!!");
			System.exit(0);
		}
		return result;

	} catch (MalformedURLException e) {
		Log.e("dd", "MalformedURLException : " + e.toString());
	} catch (IOException e) {
		Log.e("dd", "IOException : " + e.toString());



	}
	return null;
}


}
