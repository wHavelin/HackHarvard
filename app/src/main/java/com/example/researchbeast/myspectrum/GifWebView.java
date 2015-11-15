package com.example.researchbeast.myspectrum;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by ResearchBeast on 11/15/2015.
 */
public class GifWebView extends WebView {

    public GifWebView(Context context, String path) {
        super(context);

        loadUrl(path);
    }
}