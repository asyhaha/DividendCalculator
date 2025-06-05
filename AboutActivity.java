package com.example.dividendcalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView githubLink = findViewById(R.id.githubLink);
        final String url = "https://github.com/yourusername/DividendCalculator";

        githubLink.setText(url);

        githubLink.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            // Intent to go to Home/MainActivity
            Intent intent = new Intent(AboutActivity.this, MainActivity.class);
            // Clear all activities on top of MainActivity (optional)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Close AboutActivity
        });
    }
}
