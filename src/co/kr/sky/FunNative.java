package co.kr.sky;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;



public class FunNative  {


	
//	//로컬 사파리 브라우져 이동
//    public void WebLoadUrl(String url , Activity ac , WebView vc , String return_fun , Handler hd){
//        Log.e("SKY" , "--WebLoadUrl-- :: ");
//        String val[] = url.split(",");
//        for (int i = 0; i < val.length; i++) {
//            Log.e("SKY" , "VAL["+i + "]  :: " + i + " --> " + val[i]);
//        }
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(val[0]));
//        ac.startActivity(intent);
//
//        Message msg2 = hd.obtainMessage();
//
//        msg2.obj = return_fun;
//        msg2.arg1 = 1;
//        hd.sendMessage(msg2);
//
//        //Log.e("SKY" , "javascript:"+return_fun + "('" + "true" +  "')");
//        //vc.loadUrl("javascript:"+return_fun + "('" + "true" +  "')");
//    }
//
//    //백그라운드 실행(ing) 여부
//    public void BackGroundING(String url , Activity ac , WebView vc , String return_fun , Handler hd){
//        Log.e("SKY" , "--BackGroundING-- :: ");
//        String val[] = url.split(",");
//        for (int i = 0; i < val.length; i++) {
//            Log.e("SKY" , "VAL["+i + "]  :: " + i + " --> " + val[i]);
//        }
//
//        Log.e("SKY" , "javascript:"+return_fun + "('" + Check_Preferences.getAppPreferencesboolean(ac , "BACKGROUND") +  "')");
//        vc.loadUrl("javascript:"+return_fun + "('" + Check_Preferences.getAppPreferencesboolean(ac , "BACKGROUND") +  "')");
//    }
//
//
//
//	public void SendStartGPS(String url , Activity ac , WebView vc , String return_fun , Handler hd){
//		Log.e("SKY" , "--SendStartGPS-- :: ");
//		String val[] = url.split(",");
//		for (int i = 0; i < val.length; i++) {
//			Log.e("SKY" , "VAL["+i + "]  :: " + i + " --> " + val[i]);
//		}
//		Check_Preferences.setAppPreferences(ac , "LOCATION_INTERVAL" , val[0].trim().replace(" " , ""));
//		//dataSet.LOCATION_INTERVAL = Integer.parseInt(val[0].trim().replace(" " , ""));
//
//
//
//        Message msg2 = hd.obtainMessage();
//
//        msg2.obj = return_fun;
//        msg2.arg1 = 2;
//        hd.sendMessage(msg2);
//	}
//
//	public void SendStopGPS(String url , Activity ac , WebView vc , String return_fun , Handler hd){
//		Log.e("SKY" , "--SendStopGPS-- :: ");
//		String val[] = url.split(",");
//		for (int i = 0; i < val.length; i++) {
//			Log.e("SKY" , "VAL["+i + "]  :: " + i + " --> " + val[i]);
//		}
//
//		Intent intent = new Intent(ac, ScreenService.class);
//		ac.stopService(intent);
//
//        Check_Preferences.setAppPreferences(ac , "BACKGROUND" , false);
//        Check_Preferences.setAppPreferences(ac , "ING" , false);
//
//        Log.e("SKY" , "javascript:"+return_fun + "('" + "true" +  "')");
//        vc.loadUrl("javascript:"+return_fun + "('" + "true" +  "')");
//
//	}
//	
//	/* 1.(푸시키 요청하기)
//	 * param 
//	 * url :: 안씀 
//	 * name :: 안씀
//	 * return :: JavaScript 함수명
//	 * window.location.href = "js2ios://GetPushToken?param1=null&param2=null&return=WGetPushToken";
//	 * */
//	public void GetPushToken(String url , Activity ac , WebView vc , String return_fun , Handler hd){
//		Log.e("SKY" , "--GetPushToken-- :: ");
//		String val[] = url.split(",");
//		for (int i = 0; i < val.length; i++) {
//			Log.e("SKY" , "VAL["+i + "]  :: " + i + " --> " + val[i]);
//		}
//		
//		Log.e("SKY" , "javascript:"+return_fun + "('" + Check_Preferences.getAppPreferences(ac , "REG_ID") +  "')");
//		vc.loadUrl("javascript:"+return_fun + "('" + Check_Preferences.getAppPreferences(ac , "REG_ID") +  "')");
//
//	}
//	/* 2.(Speach to Text STT)
//         * param 
//         * url :: 안씀 
//         * name :: 안씀
//         * return :: JavaScript 함수명
//         * window.location.href = "js2ios://StartSTT?param1=null&param2=null&return=WStartSTT";
//         * */
//	public void StartSTT(String url , Activity ac , WebView vc , String return_fun , Handler hd){
//		Log.e("SKY" , "--SearchSst-- :: ");
//
//
//        Message msg2 = hd.obtainMessage();
//
//        msg2.obj = return_fun;
//        msg2.arg1 = 3;
//        hd.sendMessage(msg2);
//
//
//	}
//
//	/* 3.(MP3 실행)
//         * param 
//         * url :: 안씀 
//         * name :: 안씀
//         * return :: JavaScript 함수명
//         * window.location.href = "js2ios://MusicStart?param1=null&param2=null&return=WMusicStart";
//         * */
//	public void MusicStart(String url , Activity ac , final WebView vc , final String return_fun , Handler hd){
//		Log.e("SKY" , "--MusicStart1-- :: ");
//		String val[] = url.split(",");
//		for (int i = 0; i < val.length; i++) {
//			Log.e("SKY" , "VAL["+i + "]  :: " + i + " --> " + val[i]);
//		}
//
//        Log.e("SKY" , "--MusicSㅇㅁㅇtart1-- :: ");
//
//        Message msg2 = hd.obtainMessage();
//
//        msg2.obj = return_fun;
//        msg2.arg1 = 4;
//        hd.sendMessage(msg2);
//
//		int sound_id = ac.getResources().getIdentifier(val[0], "raw",
//				ac.getPackageName());
//        Log.e("SKY" , "sound_id : " + sound_id);
//
//        if(sound_id != 0) {
//
//            try {
//                if (MainActivity.music == null){
//
//                    MainActivity.music = new MediaPlayer();
//                    MainActivity.music = MediaPlayer.create(ac, sound_id);
//                    MainActivity.music.setLooping(false);
//                    MainActivity.music.start();
//                    MainActivity.music.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        public void onCompletion(MediaPlayer mp) {
//                            Log.e("SKY" , "The Playing music is Completed.");
//                            Log.e("SKY" , "javascript:"+return_fun + "('" + "true" +  "')");
//                            vc.loadUrl("javascript:"+return_fun + "('" + "true" +  "')");
//                            MainActivity.music.release();
//                            MainActivity.music = null;
//
//
//                        }
//                    });
//                }else if(MainActivity.music.isPlaying()){
//                    MainActivity.music.stop();
//                    MainActivity.music.release();
//                    MainActivity.music = null;
//                    MainActivity.music = new MediaPlayer();
//                    MainActivity.music = MediaPlayer.create(ac, sound_id);
//                    MainActivity.music.setLooping(false);
//                    MainActivity.music.start();
//                    MainActivity.music.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        public void onCompletion(MediaPlayer mp) {
//                            Log.e("SKY" , "The Playing music is Completed.");
//                            Log.e("SKY" , "javascript:"+return_fun + "('" + "true" +  "')");
//                            vc.loadUrl("javascript:"+return_fun + "('" + "true" +  "')");
//                            MainActivity.music.release();
//                            MainActivity.music = null;
//
//
//                        }
//                    });
//                }else{
//                    //MainActivity.music.start();
//                    MainActivity.music.stop();
//                    MainActivity.music.release();
//                    MainActivity.music = null;
//                }
//            }catch (Exception e){
//                Log.e("SKY" , "Error : " + e.toString());
//                MainActivity.music = null;
//
//            }
//		}
//	}
//
//	public void MusicPause(String url , Activity ac , WebView vc , String return_fun , Handler hd){
//		Log.e("SKY" , "--MusicPause-- :: ");
//		String val[] = url.split(",");
//		for (int i = 0; i < val.length; i++) {
//			Log.e("SKY" , "VAL["+i + "]  :: " + i + " --> " + val[i]);
//		}
//
//		MainActivity.music.pause();
//
//
//	}
//	
//
//	/* 4.(MP3 멈춤)
//         * param 
//         * url :: 안씀 
//         * name :: 안씀
//         * return :: 안씀
//         * window.location.href = "js2ios://MusicStop?param1=null&param2=null&return=null";
//         * */
//	public void MusicStop(String url , Activity ac , WebView vc , String return_fun , Handler hd){
//		Log.e("SKY" , "--MusicStart-- :: ");
//		String val[] = url.split(",");
//		for (int i = 0; i < val.length; i++) {
//			Log.e("SKY" , "VAL["+i + "]  :: " + i + " --> " + val[i]);
//		}
//		MainActivity.music.stop();
//		MainActivity.music = null;
//	}
//
//	/* 5.(gpt on / off 정보 가져오기)
//         * param 
//         * url :: 안씀 
//         * name :: 안씀
//         * return :: 안씀
//         * window.location.href = "js2ios://MusicStop?param1=null&param2=null&return=null";
//         * */
//	public void GetGpsStatus(String url , Activity ac , WebView vc , String return_fun , Handler hd){
//		Log.e("SKY" , "--GetGpsStatus-- :: ");
//		String val[] = url.split(",");
//		for (int i = 0; i < val.length; i++) {
//			Log.e("SKY" , "VAL["+i + "]  :: " + i + " --> " + val[i]);
//		}
//
//		Boolean isGpsEnabled = MainActivity.myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//		Log.e("SKY" , "javascript:"+return_fun + "('" + isGpsEnabled +  "')");
//		vc.loadUrl("javascript:"+return_fun + "('" + isGpsEnabled +  "')");
//
//	}
//
//    public void SetBackKey(String url , Activity ac , WebView vc , String return_fun , Handler hd){
//        Log.e("SKY" , "--SetBackKey-- :: ");
//        String val[] = url.split(",");
//        for (int i = 0; i < val.length; i++) {
//            Log.e("SKY" , "VAL["+i + "]  :: " + i + " --> " + val[i]);
//        }
//        Check_Preferences.setAppPreferences(ac , "BACK_KEY" , val[0]);
//        Log.e("SKY" , "javascript:"+return_fun + "('" + val[0] +  "')");
//        vc.loadUrl("javascript:"+return_fun + "('" + val[0] +  "')");
//    }
//	/* 6.(로그인 정보 넘기기)
//         * param 
//         * url :: 안씀 
//         * name :: 안씀
//         * return :: 안씀
//         * window.location.href = "js2ios://MusicStop?param1=null&param2=null&return=null";
//         * */
//	public void SetGuid(String url , Activity ac , WebView vc , String return_fun , Handler hd){
//		Log.e("SKY" , "--SetGuid-- :: ");
//		String val[] = url.split(",");
//		for (int i = 0; i < val.length; i++) {
//			Log.e("SKY" , "VAL["+i + "]  :: " + i + " --> " + val[i]);
//		}
//		Log.e("SKY" , "LOGIN_UDID : " + val[0]);
//		Check_Preferences.setAppPreferences(ac , "LOGIN_UDID" , val[0]);
//		//Toast.makeText(ac, "저장되었습니다(" + val[0] + ")- 추후 삭제 예정", Toast.LENGTH_SHORT).show();
//        Log.e("SKY" , "javascript:"+return_fun + "('" + "true" +  "')");
//        vc.loadUrl("javascript:"+return_fun + "('" + "true" +  "')");
//	}
//
//	public void GetLocation(String url , Activity ac , WebView vc , String return_fun , Handler hd){
//		Log.e("SKY" , "--GetLocation-- :: ");
//		String val[] = url.split(",");
//		for (int i = 0; i < val.length; i++) {
//			Log.e("SKY" , "VAL["+i + "]  :: " + i + " --> " + val[i]);
//		}
//		//gps 체크
//        Boolean isGpsEnabled = MainActivity.myLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        Log.e("SKY" , "isGpsEnabled :: " + isGpsEnabled);
//        if (!isGpsEnabled) {
//            alertCheckGPS(ac);
//        }
//		Log.e("SKY" , "javascript:"+return_fun + "('" + dataSet.latitude +  "','" + dataSet.longitude + "')");
//		vc.loadUrl("javascript:"+return_fun + "('" + dataSet.latitude +  "','" + dataSet.longitude + "')");
//
//	}
//    private void alertCheckGPS(final Activity ac) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(ac , AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
//        builder.setMessage("원활한 서비스를 위해\nGPS를 활성화를 부탁 드립니다.");
//        builder.setCancelable(false);
//        builder.setPositiveButton("확인",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        moveConfigGPS(ac);
//                    }
//                });
//        builder.setNegativeButton("취소",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//
//        AlertDialog alert = builder.create();
//        alert.show();
//    }
//    // GPS 설정화면으로 이동
//    private void moveConfigGPS(Activity ac) {
//        Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//        ac.startActivityForResult(gpsOptionsIntent , 1);
//    }
//	
//
//	/* 6.(로그인 정보 넘기기)
//         * param 
//         * url :: 안씀 
//         * name :: 안씀
//         * return :: 안씀
//         * window.location.href = "js2ios://MusicStop?param1=null&param2=null&return=null";
//         * */
//	public void ShareKaKao(String url , Activity ac , WebView vc , String return_fun , Handler hd){
//		Log.e("SKY" , "--ShareKaKao-- :: ");
//		String val[] = url.split(",");
//		for (int i = 0; i < val.length; i++) {
//			Log.e("SKY" , "VAL["+i + "]  :: " + i + " --> " + val[i]);
//		}
//		Log.e("SKY" , "LOGIN_UDID : " + val[0]);
//
//
//
//		try {
//			final KakaoLink kakaoLink = KakaoLink.getKakaoLink(ac);
//			final KakaoTalkLinkMessageBuilder kakaoBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
//
//            /*메시지 추가*/
//			kakaoBuilder.addText(val[0]);
//
//            /*이미지 가로/세로 사이즈는 80px 보다 커야하며, 이미지 용량은 500kb 이하로 제한된다.*/
////			String url = "https://developers.kakao.com/assets/img/main/icon_account_login.png";
////			kakaoBuilder.addImage(url);
//
//            /*앱 실행버튼 추가*/
//			kakaoBuilder.addAppButton(val[1]);
//
//            /*메시지 발송*/
//			kakaoLink.sendMessage(kakaoBuilder, ac);
//		} catch (KakaoParameterException e){
//			e.printStackTrace();
//		}
//	}


}
