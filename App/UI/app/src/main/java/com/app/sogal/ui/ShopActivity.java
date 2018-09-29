package com.app.sogal.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.app.sogal.R;

public class ShopActivity extends AppCompatActivity {
    WebView wvEbay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        wvEbay = (WebView)findViewById(R.id.wvEbay);
        WebSettings webSettings = wvEbay.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wvEbay.loadUrl("https://www.ebay.com/sch/i.html?_from=R40&_trksid=m570.l1313&_nkw=NFC&_sacat=0");
    }
}
