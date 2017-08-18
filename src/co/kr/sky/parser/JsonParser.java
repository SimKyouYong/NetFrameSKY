package co.kr.sky.parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.kr.sky.model.DocumentInfo;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class JsonParser extends AsyncTask<String, Integer,String>{
	Handler mAfter;
	Context mContext;
	String xmlData;
	int Result_Code;
	Map<String, String> map;
	public JsonParser(Context mcontext , Handler after , Map param , int _Result_Code )
	{
		mContext = mcontext;
		mAfter = after;
		map = param;
		Result_Code = _Result_Code;
	}
	@Override
	protected String doInBackground(String... urls) {
		StringBuilder jsonHtml = new StringBuilder();
		try{
			// ���� url ����
			URL url = new URL(urls[0]);
			// Ŀ�ؼ� ��ü ��
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			// ����Ǿ�����.
			if(conn != null){
				conn.setConnectTimeout(10000);
				conn.setUseCaches(false);
				// ����Ǿ��� �ڵ尡 ���ϵǸ�.
				if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
					for(;;){
						// ���� �������� �ؽ�Ʈ�� ���δ����� �о� ����.  
						String line = br.readLine();
						if(line == null) break;
						// ����� �ؽ�Ʈ ������ jsonHtml�� �ٿ�����
						jsonHtml.append(line + "\n");
					}
					br.close();
				}
				conn.disconnect();
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return jsonHtml.toString();
	}
	protected void onPostExecute(String str){
		Log.e("SKY" , "STR ::" + str);
		String imgurl;
		String txt1;
		String txt2;
		try{
			JSONObject root = new JSONObject(str);
			Message msg2 = mAfter.obtainMessage();
			msg2.obj = root;
			msg2.arg1 = Result_Code;
			mAfter.sendMessage(msg2);
			/*
			JSONArray ja = root.getJSONArray("results");
			for(int i=0; i<ja.length(); i++){
				ArrayList<String> obj = new ArrayList<String>();
				JSONObject jo = ja.getJSONObject(i);
				imgurl = jo.getString("imgurl");
				txt1 = jo.getString("txt1");
				txt2 = jo.getString("txt2");
				Info.add(new JsonInfo(imgurl,txt1,txt2));
			}
			 */
		}catch(JSONException e){
			e.printStackTrace();
		}



	}
}
