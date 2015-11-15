package com.example.researchbeast.myspectrum.activities;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.researchbeast.myspectrum.GifWebView;
import com.example.researchbeast.myspectrum.R;

import java.io.IOException;
import java.io.InputStream;

public class BreathingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing);

        InputStream stream = null;
        try {
            stream = getAssets().open("anxiety.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }

        GifWebView view = new GifWebView(this, "file:///android_asset/anxiety.gif");

        setContentView(view);
    }
}
