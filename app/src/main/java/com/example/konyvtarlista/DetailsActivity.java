package com.example.konyvtarlista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class DetailsActivity extends AppCompatActivity {

    private TextView textViewTitle, textViewAuthor, textViewPages, textViewYear;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewAuthor = findViewById(R.id.textViewAuthor);
        textViewPages = findViewById(R.id.textViewPages);
        textViewYear = findViewById(R.id.textViewYear);
        buttonBack = findViewById(R.id.buttonBack);


        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");
        String author = intent.getStringExtra("AUTHOR");
        int pages = intent.getIntExtra("PAGES", 0);


        textViewTitle.setText("Cím: " + title);
        textViewAuthor.setText("Szerző: " + author);
        textViewPages.setText("Oldalszám: " + pages);


        Random random = new Random();
        int randomYear = 1900 + random.nextInt(123);
        textViewYear.setText("Kiadási év: " + randomYear);


        buttonBack.setOnClickListener(v -> finish());
    }
}