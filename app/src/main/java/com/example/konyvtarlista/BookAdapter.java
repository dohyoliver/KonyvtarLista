package com.example.konyvtarlista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
        }

        Book currentBook = getItem(position);

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewAuthor = convertView.findViewById(R.id.textViewAuthor);
        TextView textViewPages = convertView.findViewById(R.id.textViewPages);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        textViewTitle.setText(currentBook.getTitle());
        textViewAuthor.setText(currentBook.getAuthor());
        textViewPages.setText(String.valueOf(currentBook.getPages()));

        buttonDelete.setOnClickListener(v -> {
            remove(currentBook);
            notifyDataSetChanged();
        });

        return convertView;
    }
}

