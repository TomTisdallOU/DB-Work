package com.example.dbproject;


import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class WebViewBridge {
    WebView webView = null;


    public WebViewBridge(WebView webView) {
        this.webView = webView;
    }
}