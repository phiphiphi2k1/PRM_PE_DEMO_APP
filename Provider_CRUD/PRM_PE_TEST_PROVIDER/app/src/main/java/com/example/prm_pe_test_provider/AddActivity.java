package com.example.prm_pe_test_provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    EditText title_input, author_input, pages_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);

        add_button = findViewById(R.id.add_item_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insert value
                //get data form EditText
                String title = title_input.getText().toString().trim();
                String author = author_input.getText().toString().trim();
                String pages = pages_input.getText().toString().trim();
                if (title.isEmpty() || author.isEmpty() || pages.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Add unsuccessful", Toast.LENGTH_SHORT).show();
                    return;
                }
                ContentValues cv = new ContentValues();
                cv.put(StaticProviderConnect.TITLE, title);
                cv.put(StaticProviderConnect.AUTHOR, author);
                cv.put(StaticProviderConnect.PAGES, pages);

                Uri uri = getContentResolver().insert(StaticProviderConnect.CONTENT_URI, cv);
                if (uri != null) {
                    Toast.makeText(AddActivity.this, "Add successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}