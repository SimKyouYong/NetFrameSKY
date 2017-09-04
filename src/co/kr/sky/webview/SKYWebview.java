package co.kr.sky.webview;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class SKYWebview extends WebView {
    private String key = "kr.co.earthcitizenclub";
//    private String key = "sky.onispiano";
    //private String key = "kr.co.inergy.selftest";
    
    private ValueCallback<Uri> mUploadMessage;
    private final static int FILECHOOSER_RESULTCODE = 42;
    private ValueCallback<Uri[]> mFilePathCallback;
    private static final String TYPE_IMAGE = "image/*";
    private String mCameraPhotoPath;
    private static final int INPUT_FILE_REQUEST_CODE = 40;
    private final static int FILECHOOSER_LOLLIPOP_REQ_CODE = 41;
    private ValueCallback<Uri[]> filePathCallbackLollipop;

    //private WebView mWebView;
    private Context cmx;
    private Activity avx;
    private Handler mAfter;
    private SKYWebview wc;
    private String FunLocation;
    private RelativeLayout mainBody;
    private WebView pWebView;
    public void Setting(Activity avx , Handler mAfter , SKYWebview wc , String FunLocation , RelativeLayout mainBody , WebView pWebView){
        this.avx = avx;
        this.mAfter = mAfter;
        this.wc = wc;
        this.FunLocation = FunLocation;
        this.mainBody = mainBody;
        this.pWebView = pWebView;
        //key 점검
        //Log.e("SKY" , "KEY1 : " + avx.getPackageName());
        //Log.e("SKY" , "KEY2 : " + key);

        if (!key.equals(avx.getPackageName())){
        	Log.e("SKY" , "This is Copy!!");
            System.exit(0);
        }
        	
    }

    public SKYWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        cmx = context;
    	//getSettings().setUserAgentString("hybridweb_android");
        getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setAppCacheEnabled(true);
        getSettings().setBuiltInZoomControls(true);
        getSettings().setDisplayZoomControls(true);
        getSettings().setSupportZoom(false);
        getSettings().setSupportMultipleWindows(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getSettings().setUserAgentString(getSettings().getUserAgentString()+" Hybrid 2.0");
        setWebChromeClient(new SMOWebChromeClient(cmx));
        setWebViewClient(new ITGOWebChromeClient());
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setUseWideViewPort(true);
        setInitialScale(1);
        setVerticalScrollbarOverlay(true);
        setHorizontalScrollbarOverlay(true);
        setHorizontalScrollBarEnabled(false);
        //		mWebView.setInitialScale(100);
        setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
            	String value = "";
            	try {
	                value = URLDecoder.decode(contentDisposition, "utf-8");
	                Log.e("SKY" , "value :: "  + value);

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setMimeType(mimeType);                request.setMimeType(mimeType);
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading file...");
                Log.e("SKY", "url :: " + url);
                Log.e("SKY", "contentDisposition :: " + value);
                Log.e("SKY", "mimeType :: " + mimeType);
                request.setTitle(URLUtil.guessFileName(url, value, mimeType));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(1);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, value, mimeType));
                DownloadManager dm = (DownloadManager)SKYWebview.this.cmx.getSystemService("download");
                dm.enqueue(request);
                Toast.makeText(SKYWebview.this.cmx, "Downloading File", 1).show();
            }
        });
        Log.e("SKY" , "WEBVIEW 생성");
    }
    class SMOWebChromeClient extends WebChromeClient {
        private View mCustomView;
        private Context mActivity;

        public SMOWebChromeClient(Context activity) {
            this.mActivity = activity;
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean dial, boolean userGesture, android.os.Message resultMsg) {
            super.onCreateWindow(view, dial, userGesture, resultMsg);

            mainBody.removeView(pWebView);

            Log.e("SKY", "PopupvIEW");
            pWebView = new WebView(cmx);
            pWebView.getSettings().setJavaScriptEnabled(true);
            pWebView.getSettings().setBuiltInZoomControls(true);
            pWebView.getSettings().setSupportZoom(true);
            pWebView.getSettings().setUseWideViewPort(true);
            pWebView.getSettings().setLoadWithOverviewMode(true);
            pWebView.setWebChromeClient(this);
            pWebView.setWebViewClient(new WebAppViewClient(){
                @Override
                public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon){
                    super.onPageStarted(view, url, favicon);
                }
                @Override
                public void onPageFinished(WebView view, String url){
                    super.onPageFinished(view, url);
                }
            });
            pWebView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            mainBody.addView(pWebView);

            WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
            transport.setWebView(pWebView);
            resultMsg.sendToTarget();
            //popup=true;

            //핸들러 반환
            Message msg2 = mAfter.obtainMessage();
            msg2.obj = true;
            msg2.arg1 = 1000;
            mAfter.sendMessage(msg2);
            
          //핸들러 반환
            Message msg3 = mAfter.obtainMessage();
            msg3.obj = pWebView;
            msg3.arg1 = 902;
            mAfter.sendMessage(msg3);
            return true;
        }
        @Override
        public void onCloseWindow(WebView window) {
            super.onCloseWindow(window);

            closePop();
        }
        @SuppressWarnings("unused")
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");

            //핸들러 반환
            Message msg2 = mAfter.obtainMessage();
            msg2.obj = mUploadMessage;
            msg2.arg1 = 900;
            mAfter.sendMessage(msg2);

            avx.startActivityForResult(Intent.createChooser(i, "파일 선택"), FILECHOOSER_RESULTCODE);
        }
        @SuppressWarnings("unused")
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");

            //핸들러 반환
            Message msg2 = mAfter.obtainMessage();
            msg2.obj = mUploadMessage;
            msg2.arg1 = 900;
            mAfter.sendMessage(msg2);

            avx.startActivityForResult(Intent.createChooser(i, "파일 선택"), FILECHOOSER_RESULTCODE);
        }
        @SuppressWarnings("unused")
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");

            //핸들러 반환
            Message msg2 = mAfter.obtainMessage();
            msg2.obj = mUploadMessage;
            msg2.arg1 = 900;
            mAfter.sendMessage(msg2);

            avx.startActivityForResult(Intent.createChooser(i, "파일 선택"), FILECHOOSER_RESULTCODE);
        }

        public boolean onShowFileChooser(WebView webView,
                                         ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            Log.e("SKY", "5.0+");
            System.out.println("WebViewActivity A>5, OS Version : " + Build.VERSION.SDK_INT + "\t onSFC(WV,VCUB,FCP), n=3");
            /*
            if (mFilePathCallback != null) {
                mFilePathCallback.onReceiveValue(null);
            }
            */
            mFilePathCallback = filePathCallback;

            //핸들러 반환
            Message msg2 = mAfter.obtainMessage();
            msg2.obj = mFilePathCallback;
            msg2.arg1 = 901;
            mAfter.sendMessage(msg2);

            imageChooser();




            return true;

        }
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            //super.onGeolocationPermissionsShowPrompt(origin, callback);
            callback.invoke(origin, true, false);
        }
        @Override
        public void onExceededDatabaseQuota(String url, String
                databaseIdentifier, long currentQuota, long estimatedSize,
                                            long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {

            super.onExceededDatabaseQuota(url, databaseIdentifier, currentQuota,
                    estimatedSize, totalUsedQuota, quotaUpdater);
        }
        @Override
        public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result)
        {
            new AlertDialog.Builder(cmx , AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                    .setMessage(message)
                    .setPositiveButton("확인",
                            new AlertDialog.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    result.confirm();
                                }
                            })
                    .setCancelable(false)
                    .create()
                    .show();

            return true;
        };



    }

    private void imageChooser() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(avx.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
                takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath);
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e(getClass().getName(), "Unable to create Image File", ex);
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                mCameraPhotoPath = "file:"+photoFile.getAbsolutePath();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
            } else {
                takePictureIntent = null;
            }
        }

        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType(TYPE_IMAGE);

        Intent[] intentArray;
        if(takePictureIntent != null) {
            intentArray = new Intent[]{takePictureIntent};
        } else {
            intentArray = new Intent[0];
        }

        Intent chooserIntent = new Intent(Intent.ACTION_GET_CONTENT);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
        chooserIntent.setType("image/*");
        chooserIntent.addCategory(Intent.CATEGORY_OPENABLE);

        avx.startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return imageFile;
    }


    class ITGOWebChromeClient extends WebViewClient {
        @Override
        public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(cmx);
            builder.setMessage("안전하지 않는 페이지 입니다.\n그래도 진행하시겠습니까?");
            builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.proceed();
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.cancel();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String overrideUrl) {
            Log.e("SKY", "overrideUrl :: " + overrideUrl);
            Message msg2 = SKYWebview.this.mAfter.obtainMessage();
            msg2.arg1 = 8001;
            msg2.obj = ""+overrideUrl;
            SKYWebview.this.mAfter.sendMessage(msg2);
            if(overrideUrl.startsWith("https://www.facebook.com/dialog/return/close?#_=_")) {
                Log.e("SKY", "back--");
                view.goBackOrForward(-1);
                SKYWebview.this.mainBody.removeView(SKYWebview.this.pWebView);
                return true;
            }else if(overrideUrl.startsWith("com.eccb.17060501://")){		//지구 시민 클럽
            	return true;
            	
            }else if(!overrideUrl.startsWith("intent") && !overrideUrl.startsWith("Intent")) {
                if(overrideUrl.startsWith("js2ios://")) {
                    try {
                        Log.e("SKY", "shouldOverrideUrlLoading");
                        overrideUrl = URLDecoder.decode(overrideUrl, "UTF-8");
                        SKYWebview.this.SplitFun(overrideUrl);
                        Log.e("SKY", "함수 시작");
                    } catch (Exception var6) {
                        Log.e("SKY", "e :: " + var6.toString());
                    }

                    return true;
                } else {
                    Log.e("SKY", "can url not :: " + overrideUrl);
                    boolean override1 = false;
                    Intent i;
                    if(overrideUrl.startsWith("sms:")) {
                        i = new Intent("android.intent.action.SENDTO", Uri.parse(overrideUrl));
                        SKYWebview.this.cmx.startActivity(i);
                        return true;
                    } else if(overrideUrl.startsWith("tel:")) {
                        i = new Intent("android.intent.action.DIAL", Uri.parse(overrideUrl));
                        SKYWebview.this.cmx.startActivity(i);
                        return true;
                    } else if(overrideUrl.startsWith("mailto:")) {
                        i = new Intent("android.intent.action.SENDTO", Uri.parse(overrideUrl));
                        SKYWebview.this.cmx.startActivity(i);
                        return true;
                    } else if(overrideUrl.startsWith("geo:")) {
                        i = new Intent("android.intent.action.VIEW", Uri.parse(overrideUrl));
                        SKYWebview.this.cmx.startActivity(i);
                        return true;
                    } else {
                        Log.e("SKY", "overrideUrl  ELSE:: " + overrideUrl);
                        view.loadUrl(overrideUrl);
                        return true;
                    }
                }
            } else {
                Intent override = null;

                try {
                    override = Intent.parseUri(overrideUrl, 1);
                } catch (URISyntaxException var9) {
                    Log.e("Browser", "Bad URI " + overrideUrl + ":" + var9.getMessage());
                }

                try {
                    view.getContext().startActivity(Intent.parseUri(overrideUrl, 0));
                } catch (URISyntaxException var7) {
                    var7.printStackTrace();
                } catch (ActivityNotFoundException var8) {
                    var8.printStackTrace();
                }

                return true;
            }
        }

        public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon){
            super.onPageStarted(view, url, favicon);
            Message msg2 = SKYWebview.this.mAfter.obtainMessage();
            msg2.arg1 = 8002;
            msg2.obj = ""+url;
            SKYWebview.this.mAfter.sendMessage(msg2);
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Message msg2 = SKYWebview.this.mAfter.obtainMessage();
            msg2.arg1 = 8000;
            msg2.obj = ""+url;
            SKYWebview.this.mAfter.sendMessage(msg2);
            Log.e("SKY", "onPageFinished = = = = = = = " + url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            //Toast.makeText(getApplicationContext(), "Error: "+description, Toast.LENGTH_SHORT).show();
        }
    }
    private void SplitFun(String url){
        url = url.replace("js2ios://", "");
        String Fun = url.substring(0, url.indexOf("?"));
        Log.e("SKY", "Fun :: "+Fun);
        String param[] = url.split("&");
        String val[]  = new String[param.length];
        Log.e("SKY", "parameter ea :: "+param.length);
        String par = "" , return_fun = "";
        for (int i = 0; i < param.length; i++) {
            //Log.e("SKY", "parameter ea :: " + "i :: " + i + " ::" +param[i]);
            val[i] = param[i].substring(param[i].indexOf("=")+1, param[i].length());
            Log.e("SKY", "parameter ea :: " + "i :: " + i + " ::" +val[i]);
            if (i == 0) {
                par = val[i];
            }else if( i == (param.length-1)){
                return_fun = val[i];
            }else{
                par += "," +val[i];
            }
        }
        try {
            //String parameter
            Class[] paramString = new Class[5];
            paramString[0] = String.class;
            paramString[1] = Activity.class;
            paramString[2] = WebView.class;
            paramString[3] = String.class;
            paramString[4] = Handler.class;
            @SuppressWarnings("rawtypes")
            Class cls = Class.forName(FunLocation);
            Object obj = cls.newInstance();
            //call the printIt method
            Method method = cls.getDeclaredMethod(Fun, paramString);
            method.invoke(obj, new String(par) , avx , wc , new String(return_fun) , mAfter);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    @SuppressLint("NewApi")
    public class WebAppViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String overrideUrl){
            if(overrideUrl.contains(".mp4")){
                Intent i = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(overrideUrl);
                i.setDataAndType(uri, "video/mp4");
                avx.startActivity(i);
                return super.shouldOverrideUrlLoading(view, overrideUrl);
            }else if(overrideUrl.startsWith("about:")){
                return true;
            }else if(overrideUrl.startsWith("http://")||overrideUrl.startsWith("https://")){
                view.loadUrl(overrideUrl);
                return true;
            }
            else if(overrideUrl.startsWith("intent")||overrideUrl.startsWith("Intent"))
            {
                Intent intent = null;
                try{
                    intent = Intent.parseUri(overrideUrl,  Intent.URI_INTENT_SCHEME);
                }
                catch(URISyntaxException ex){
                    Log.e("Browser", "Bad URI " +overrideUrl + ":" + ex.getMessage());
                }
                try{
                    view.getContext().startActivity(intent.parseUri(overrideUrl,0));
                }catch(URISyntaxException e){
                    e.printStackTrace();
                }catch(ActivityNotFoundException e){
                    e.printStackTrace();
                }
                return true;
            }else {
                boolean override = false;
                if (overrideUrl.startsWith("sms:")) {
                    Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(overrideUrl));
                    avx.startActivity(i);
                    return true;
                }
                if (overrideUrl.startsWith("tel:")) {
                    Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(overrideUrl));
                    avx.startActivity(i);
                    return true;
                }
                if (overrideUrl.startsWith("mailto:")) {
                    Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(overrideUrl));
                    avx.startActivity(i);
                    return true;
                }
                if (overrideUrl.startsWith("geo:")) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(overrideUrl));
                    avx.startActivity(i);
                    return true;
                }else if(overrideUrl.startsWith("about:")){
                    return true;
                }
                try{
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(overrideUrl));
                    avx.startActivity(intent);
                    override = true;
                }
                catch(ActivityNotFoundException ex) {}
                return override;
            }
        }
    }
    public void closePop(){
        mainBody.removeView(pWebView);
        //핸들러 반환
        Message msg2 = mAfter.obtainMessage();
        msg2.obj = false;
        msg2.arg1 = 1000;
        mAfter.sendMessage(msg2);
        //popup=false;
    }
}
