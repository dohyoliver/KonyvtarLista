package com.example.konyvtarlista;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextAuthor, editTextPages;
    private Button buttonAdd;
    private ListView listViewBooks;
    private ArrayList<Book> bookList;
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        editTextPages = findViewById(R.id.editTextPages);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewBooks = findViewById(R.id.listViewBooks);


        bookList = new ArrayList<>();
        adapter = new BookAdapter(this, bookList);
        listViewBooks.setAdapter(adapter);


        buttonAdd.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString();
            String author = editTextAuthor.getText().toString();
            String pagesStr = editTextPages.getText().toString();

            if (title.isEmpty() || author.isEmpty() || pagesStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Minden mező kitöltése kötelező!", Toast.LENGTH_SHORT).show();
                return;
            }

            int pages;
            try {
                pages = Integer.parseInt(pagesStr);
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Az oldalszámnak érvényes számnak kell lennie!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (pages < 50) {
                Toast.makeText(MainActivity.this, "Az oldalszámnak legalább 50-nek kell lennie!", Toast.LENGTH_SHORT).show();
                return;
            }

            Book newBook = new Book(title, author, pages);
            bookList.add(newBook);
            adapter.notifyDataSetChanged();


            editTextTitle.setText("");
            editTextAuthor.setText("");
            editTextPages.setText("");
        });


        listViewBooks.setOnItemClickListener((parent, view, position, id) -> {
            Book selectedBook = bookList.get(position);
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("TITLE", selectedBook.getTitle());
            intent.putExtra("AUTHOR", selectedBook.getAuthor());
            intent.putExtra("PAGES", selectedBook.getPages());
            startActivity(intent);
        });


        listViewBooks.setOnItemLongClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Törlés")
                    .setMessage("Biztosan törölni akarja a könyvet?")
                    .setPositiveButton("Igen", (dialog, which) -> {
                        bookList.remove(position);
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("Nem", null)
                    .show();
            return true;
        });
    }
}