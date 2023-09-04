package com.example.ecotrak.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ecotrak.R;

public class AboutActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView tvUsername = findViewById(R.id.tvUsername);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("username")) {
            String username = intent.getStringExtra("username");
            tvUsername.setText("Welcome, " + username + "!");
        }

        // Set up buttons to open social media profiles
        ImageButton instagramButton = findViewById(R.id.instagramButton);

        WebView webView = findViewById(R.id.webView); // Make sure you have a WebView in your layout
        instagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String instagramUsername = "instagram_username";
                webView.loadUrl("http://instagram.com/" + instagramUsername);
            }
        });

        ImageButton facebookButton = findViewById(R.id.facebookButton);
        facebookButton.setOnClickListener(v -> {
            String facebookId = ""; // Replace with the desired Facebook profile or page ID
            webView.loadUrl("http://www.facebook.com/" + facebookId);            });

        ImageButton githubButton = findViewById(R.id.githubButton);
        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String githubUsername = "Har5H-Ag"; // Replace with the desired GitHub username
                webView.loadUrl("https://github.com/" + githubUsername);            }
        });
    }

}