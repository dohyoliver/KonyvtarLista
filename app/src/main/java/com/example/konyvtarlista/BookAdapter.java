package com.example.konyvtarlista;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {
    private Context context;
    private ArrayList<Book> bookList;

    public BookAdapter(Context context, ArrayList<Book> bookList) {
        super(context, R.layout.item_book, bookList);
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        }

        Book currentBook = bookList.get(position);

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewAuthor = convertView.findViewById(R.id.textViewAuthor);
        TextView textViewPages = convertView.findViewById(R.id.textViewPages);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        textViewTitle.setText("Cím: " + currentBook.getTitle());
        textViewAuthor.setText("Szerző: " + currentBook.getAuthor());
        textViewPages.setText("Oldalszám: " + currentBook.getPages());

        buttonDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Törlés megerősítése")
                    .setMessage("Biztosan törölni szeretnéd a könyvet?")
                    .setPositiveButton("Igen", (dialog, which) -> {
                        bookList.remove(position);
                        notifyDataSetChanged();
                    })
                    .setNegativeButton("Nem", null)
                    .show();
        });

        return convertView;
    }
}
