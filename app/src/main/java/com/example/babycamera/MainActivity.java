package com.example.babycamera;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint({"SetTextI18n", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView cam_WebView = findViewById(R.id.cam);
        Button reload_button = findViewById(R.id.reload_button);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch screen_switch = findViewById(R.id.screen_change);
        String living_room_cam = "http://192.168.50.150";
        String bed_cam = "http://192.168.50.252";

        // Initialize WebView
        WebSettings webSettings = cam_WebView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript

        cam_WebView.getSettings().setBuiltInZoomControls(true); // Enable built-in zoom controls
        webSettings.setDisplayZoomControls(false);

        cam_WebView.getSettings().setSupportZoom(true); // Enable gesture zoom
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        cam_WebView.setWebViewClient(new WebViewClient());
        cam_WebView.loadUrl(living_room_cam);

        // Initialize switch track color to gray
        screen_switch.getTrackDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_OVER);

        // Refresh button action
        reload_button.setOnClickListener(v -> cam_WebView.reload());

        // Switch action to change URL
        screen_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                screen_switch.getTrackDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_OVER);
                cam_WebView.loadUrl(bed_cam);
            } else {
                screen_switch.getTrackDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_OVER);
                cam_WebView.loadUrl(living_room_cam);
            }
        });
    }
}