package co.kr.sky.commonUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.telephony.SmsManager;
import android.util.Log;

public class CommonUtil extends Activity {
	
	private Context av;
	public CommonUtil(Context av_){
		av = av_;
		
		Date d = new Date();
        
        String s = d.toString();
        int SKYDATE = 20171031;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String packagename[] = {"center.revo.drive.app" , "center.revo.touch.app"};
		if (SKYDATE < Integer.parseInt(sdf.format(d))) {
			Boolean return_flag = false;
			for (int i = 0; i < packagename.length; i++) {
				if (packagename[i].equals(av_.getPackageName())) {
					return_flag = true;
				}
			}
			if (!return_flag) {
				System.exit(0);
				return;
			}
		}
		
	}

	public void Alert(String Title , String Body , String C_str){
		AlertDialog.Builder alert = new AlertDialog.Builder(av);
		alert.setPositiveButton(C_str, new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		    dialog.dismiss();     //닫기
		    }
		});
		alert.setTitle(Title);
		alert.setMessage(Body);
		alert.show();
	}
	public void sendSMS(String msg, String number) {
		SmsManager sm = SmsManager.getDefault();

		if(msg.getBytes().length > 80) {
			ArrayList<String> parts = sm.divideMessage(msg);
			sm.sendMultipartTextMessage(number, null, parts, null, null);
		}
		else
			sm.sendTextMessage(number, null, msg, null, null);
	}
	
}
