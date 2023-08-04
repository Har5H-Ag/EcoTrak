package com.example.ecotrak.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ecotrak.R;

public class HomeActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tvUsername = findViewById(R.id.tvUsername);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("username")) {
            String username = intent.getStringExtra("username");
            tvUsername.setText("Welcome, " + username + "!");
        }

        // Set up buttons to open social media profiles
        ImageButton instagramButton = findViewById(R.id.instagramButton);
        instagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstagram();
            }
        });

        ImageButton facebookButton = findViewById(R.id.facebookButton);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebook();
            }
        });

        ImageButton githubButton = findViewById(R.id.githubButton);
        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGitHub();
            }
        });
    }
    private void openInstagram() {
        String instagramUsername = "instagram_username"; // Replace with the desired Instagram username
        try {
            // Convert the Instagram username to a URL
            Uri uri = Uri.parse("http://instagram.com/" + instagramUsername);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.instagram.android");
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException e) {
            // Instagram app is not installed, open in browser
            Uri uri = Uri.parse("http://instagram.com/" + instagramUsername);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    private void openFacebook() {
        String facebookId = ""; // Replace with the desired Facebook profile or page ID
        try {
            // Try opening the Facebook app
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/" + facebookId));
            startActivity(intent);
        } catch (Exception e) {
            // Facebook app is not installed, open in browser
            Uri uri = Uri.parse("http://www.facebook.com/" + facebookId);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    private void openGitHub() {
        String githubUsername = "Har5H-Ag"; // Replace with the desired GitHub username
        Uri uri = Uri.parse("https://github.com/" + githubUsername);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}