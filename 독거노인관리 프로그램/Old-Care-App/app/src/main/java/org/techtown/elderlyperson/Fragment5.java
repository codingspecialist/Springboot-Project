package org.techtown.elderlyperson;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static org.techtown.elderlyperson.Fragment1.ht;

public class Fragment5 extends Fragment {
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment5, container, false);
        webView = (WebView) v.findViewById(R.id.webView);

        webView.loadUrl(ht);
        webView.setWebViewClient(new WebViewClient());// 클릭시 새창 안뜨게
        webView.getSettings().setLoadWithOverviewMode(true);// 메타태그 허용 여부
        webView.getSettings().setUseWideViewPort(true);// 화면 사이즈 맞추기 허용 여부
        webView.getSettings().setBuiltInZoomControls(true);// 화면 확대 축소 허용 여부
        webView.getSettings().setSupportZoom(true);// 화면 줌 허용 여부

        return v;
    }

}
