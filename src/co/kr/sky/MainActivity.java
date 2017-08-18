package co.kr.sky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import co.kr.sky.model.JsonInfo;
import co.kr.sky.obj.Loginobj;

public class MainActivity extends Activity {
	JsonInfo Info;
	AccumThread mThread;
	Map<String, String> map = new HashMap<String, String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.json_sample).setOnClickListener(btnListener);
		findViewById(R.id.json_sample2).setOnClickListener(btnListener);

		
		Log.e("SKY"  , "--json_sample2--");
		String json = "";
        // 3. build jsonObject
        JSONObject jsonObject = new JSONObject();
        try {
			jsonObject.accumulate("id", "opgame@naver.com");
            jsonObject.accumulate("pw", "1111");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // 4. convert JSONObject to JSON to String
        json = jsonObject.toString();
        Log.e("SKY" , "JSON DATA ::"+json);
		map.put("url", "http://sc-group.kr/collraboration/login/login.ajax");
		map.put("_JSON_DATA",json);
		//스레드 생성 
		mThread = new AccumThread(MainActivity.this , mAfterAccum , map , 0 , 0 , null);
		mThread.start();		//스레드 시작!!
	}
	View.OnClickListener btnListener = new View.OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {

			case R.id.json_sample:	
				Log.e("SKY"  , "--json_sample--");
				String str[] = {"imgurl" , "txt1" , "txt2"};
				new HttpAsyncTask().execute("http://sc-group.kr/collraboration/login/login.ajax");

				break;
			case R.id.json_sample2:	
				Log.e("SKY"  , "--json_sample2--");
				String json = "";
	            // 3. build jsonObject
	            JSONObject jsonObject = new JSONObject();
	            try {
					jsonObject.accumulate("id", "opgame@naver.com");
		            jsonObject.accumulate("pw", "1111");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            // 4. convert JSONObject to JSON to String
	            json = jsonObject.toString();
	            Log.e("SKY" , "JSON DATA ::"+json);
				map.put("url", "http://sc-group.kr/collraboration/login/login.ajax");
				map.put("_JSON_DATA",json);
				//스레드 생성 
				mThread = new AccumThread(MainActivity.this , mAfterAccum , map , 0 , 0 , null);
				mThread.start();		//스레드 시작!!
				break;
			}
		}
	};
	Handler mAfterAccum = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			if (msg.arg1  == 0 ) {
				String res = (String)msg.obj;
				Log.e("CHECK" , "RESULT  -> " + res);
				try {
	                // JSON 구문을 파싱해서 JSONArray 객체를 생성

	                JSONArray jAr = new JSONArray("["+res+"]");
	            	Log.e("SKY" , "jAr.length()  ->"  + jAr.length());
//	            	{"message":"Bad credentials","_JSON_DATA":{"pw":"1111","id":"opgame@naver.com"},"success":false}

	                for(int i=0; i < jAr.length(); i++) {
	                    // 개별 객체를 하나씩 추출
	                    JSONObject obj = jAr.getJSONObject(i);
	                    // 객체에서 하위 객체를 추출
	                    JSONObject content = obj.getJSONObject("_JSON_DATA");
	                    // 하위 객체에서 데이터를 추출
	                    Log.e("SKY" , "1---::" + obj.getString("message"));
	                    Log.e("SKY" , "2---::" + content.getString("pw"));
//	                    seq = obj.getString("seq");
//	                    status = obj.getString("status");
//	                    error = content.getString("error");
//	                    11-06 15:58:46.583: E/SKY(2609): --json_sample2--
//	                    11-06 15:58:46.583: E/SKY(2609): JSON DATA ::{"id":"opgame@naver.com","pw":"1111"}
//	                    11-06 15:58:47.403: E/SKY(2609): res : true
//	                    11-06 15:58:47.403: E/SKY(2609): RETURN CODE :: true
//	                    11-06 15:58:47.583: E/SKY(2609): jAr.length()  ->1
//	                    11-06 15:58:47.583: E/SKY(2609): 1---::Bad credentials
//	                    11-06 15:58:47.583: E/SKY(2609): 2---::1111

	                }
	            } catch (JSONException e) {
	                Log.d("tag", "Parse Error" + e);
	            }
			}
		}
	};
	public static String POST(String url, Loginobj person){
        InputStream inputStream = null;
        String result = "";
        try {
 
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
 
            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);
            String json = "";
            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("id", person.getId());
            jsonObject.accumulate("pw", person.getPw());
            
            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();
 
            Log.e("SKY" , "JSON ::" + json);
            // ** Alternative way to convert Person object to JSON string usin Jackson Lib 
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person); 
 
            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json , "UTF-8");
            se.setContentType("application/json");
            // 6. set httpPost Entity
            httpPost.setEntity(se);
 
            // 7. Set some headers to inform server about the type of the content   
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
 
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);
 
            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
 
            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
 
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
 
        // 11. return result
        return result;
    }
	public static String convertInputStreamToString(InputStream inputStream) throws IOException{
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	} 
	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
        	Loginobj login = new Loginobj();
        	login.setId("opgame@naver.com");
        	login.setPw("1111");
 
            return POST(urls[0],login);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
//        	03-05 15:25:21.153: E/SKY(27006): RESULT  ->{"seq":0,"status":1,"content":{"error":"USERID_EXISTS"}}

        	Log.e("SKY" , "RESULT  ->"  + result);
            String error = "";
            String seq = "";
            String status = "";
            		
            try {
                // JSON 구문을 파싱해서 JSONArray 객체를 생성

                JSONArray jAr = new JSONArray("["+result+"]");
            	Log.e("SKY" , "jAr.length()  ->"  + jAr.length());

                for(int i=0; i < jAr.length(); i++) {
                    // 개별 객체를 하나씩 추출
                    JSONObject obj = jAr.getJSONObject(i);
                    // 객체에서 하위 객체를 추출
                    JSONObject content = obj.getJSONObject("content");
                    // 하위 객체에서 데이터를 추출
                    seq = obj.getString("seq");
                    status = obj.getString("status");
                    error = content.getString("error");
                }
            } catch (JSONException e) {
                Log.d("tag", "Parse Error" + e);
            }
        	String msg = "";

        	
       }
    }
}
