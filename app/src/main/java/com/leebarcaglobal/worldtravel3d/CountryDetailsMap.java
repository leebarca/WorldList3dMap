package com.leebarcaglobal.worldtravel3d;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CountryDetailsMap extends BaseActivity {

    private ImageButton back_button;
    private WebView map_webview;
    private TextView map_error;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details_map);

        map_webview = findViewById(R.id.map_webview);
        back_button = findViewById(R.id.back_button);
        map_error = findViewById(R.id.map_error);

        if (NetworkUtils.isNetworkAvailable(this)) {
            // Configure WebView settings
            map_error.setVisibility(TextView.GONE);
            map_webview.setVisibility(WebView.VISIBLE);
            openMap();
        } else {
            // Show error message
            map_error.setVisibility(TextView.VISIBLE);
            map_webview.setVisibility(WebView.GONE);
        }

        back_button.setOnClickListener(v -> {
            // Handle click event
            finish();
        });
    }

    private void openMap() {
        WebSettings webSettings = map_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        map_webview.setOnTouchListener((view, event) -> {
            view.getParent().requestDisallowInterceptTouchEvent(true);  // Prevent parent view from intercepting touch events
            return false;  // Let WebView handle the event
        });

        // Clear cache and load the map
        map_webview.clearCache(true);
        map_webview.clearHistory();

        map_webview.setWebViewClient(new WebViewClient());
        String mapUrl = "<!DOCTYPE html>" +
                "<html>" +
                "<body>" +
                "<iframe" +
                "    width='100%'" +
                "    height='100%'" +
                "    style='border:0'" +
                "    loading='lazy'" +
                "    allowfullscreen" +
                "    referrerpolicy='no-referrer-when-downgrade'" +
                "    src='https://www.google.com/maps/embed/v1/place?key=AIzaSyAfv8r9eyr7wDwXuZ9cPNmdmvzVC9P2DEg&q=" +
                "10.748817,-73.985428" +
                "&maptype=satellite'>" +
                "</iframe>" +
                "</body>" +
                "</html>";
        map_webview.loadDataWithBaseURL(null, mapUrl, "text/html", "UTF-8", null);
        map_webview.loadUrl(mapUrl);
    }
}