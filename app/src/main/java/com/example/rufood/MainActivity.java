package com.example.rufood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    WebView webview;
    String weburl ="https://rufooddelivery.com/shop/";
    ProgressBar progresstop;
    ProgressDialog dialog;
    RelativeLayout relativeLayout;
    Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        webview = (WebView) findViewById(R.id.mywebview);
        progresstop =(ProgressBar) findViewById(R.id.progress_horizontal);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading please wait");


        relativeLayout = (RelativeLayout) findViewById(R.id.layout);
        btn = (Button) findViewById(R.id.retrybutton);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);


        checkConnection();

        webview.setWebViewClient(new WebViewClient(){


        });

        webview.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                progresstop.setVisibility(View.VISIBLE);
                progresstop.setProgress(newProgress);
                setTitle("Loading...");
                dialog.show();
                if(newProgress == 100){
                    progresstop.setVisibility(View.GONE);
                    setTitle(view.getTitle());
                    dialog.dismiss();

                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                checkConnection();
            }

        });

    }

    @Override
    public void onBackPressed() {
        if(webview.canGoBack()){
            webview.goBack();
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Want to Exit?")
                    .setNegativeButton("NO", null)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                        }
                    }).show();
        }
    }

    public void checkConnection(){

        ConnectivityManager manager = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo data = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifi.isConnected()){

            webview.loadUrl(weburl);
            webview.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
        }
        else if(data.isConnected()){

            webview.loadUrl(weburl);
            webview.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
        }
        else {
            relativeLayout.setVisibility(View.VISIBLE);
            webview.setVisibility(View.GONE);

        }



    }

}
