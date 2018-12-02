package com.example.dbproject;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class ChartHandler {
    private WebView webView;

    public ChartHandler(WebView webView){
        this.webView = webView;
    }

    @JavascriptInterface
    public void AddData(String week, int totalPoints){
        webView.loadUrl("javascript:addDataToChart('" + week + "'," + totalPoints + ")");
    }
}
