package org.techtown.bsymapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class InternetActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_internet);
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://m.search.naver.com/search.naver?sm=mtp_hty.top&where=m&query=%EB%A7%9B%EC%A7%91\n");
        webView.setWebViewClient(new WebViewClient());
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