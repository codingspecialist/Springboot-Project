package org.techtown.bsymapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static org.techtown.bsymapp.MainActivity.a;


public class ContentDetail extends AppCompatActivity {
    private WebView webView2;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_contentdetail);
        webView2 = (WebView)findViewById(R.id.webView2);
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.loadUrl(a);
        webView2.setWebViewClient(new WebViewClient());
    }
    private class WebViewClientClass extends  WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url){
            view.loadUrl(url);
            return true;
        }
    }
    //뒤로가기 버튼 동작 시키기 위한 것
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return true;
    }
}