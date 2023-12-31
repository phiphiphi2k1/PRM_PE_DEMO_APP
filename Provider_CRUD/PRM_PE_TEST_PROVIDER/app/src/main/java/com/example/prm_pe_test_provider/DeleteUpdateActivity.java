package com.example.prm_pe_test_provider;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteUpdateActivity extends AppCompatActivity {
    ItemModel itemModel;
    EditText title_output, author_output, pages_output;
    Button update_button, delete_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_update);

        title_output = findViewById(R.id.title_output);
        author_output = findViewById(R.id.author_output);
        pages_output = findViewById(R.id.pages_output);

        update_button = findViewById(R.id.update_button);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = title_output.getText().toString().trim();
                String author = author_output.getText().toString().trim();
                String pages = pages_output.getText().toString().trim();

                if (title.isEmpty() || author.isEmpty() || pages.isEmpty()) {
                    Toast.makeText(DeleteUpdateActivity.this, "Add unsuccessful", Toast.LENGTH_SHORT).show();
                    return;
                }
                ContentValues cv = new ContentValues();
                cv.put(StaticProviderConnect.TITLE, title);
                cv.put(StaticProviderConnect.AUTHOR, author);
                cv.put(StaticProviderConnect.PAGES, pages);
                String[] condition_value = {itemModel.getId()};
                int resultCount = getContentResolver().update(StaticProviderConnect.CONTENT_URI, cv, "_id = ?", condition_value);
                if (resultCount > 0) {
                    Toast.makeText(DeleteUpdateActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
                }
                if (resultCount < 0) {
                    Toast.makeText(DeleteUpdateActivity.this, "Update unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete_button = findViewById(R.id.delete_button);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

        loadItem();
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + itemModel.getTitle() + " ?");
        builder.setMessage("Are you sure you want to delete " + itemModel.getTitle() + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String[] condition_value = {itemModel.getId()};
                int resultCount = getContentResolver().delete(StaticProviderConnect.CONTENT_URI, "_id = ?", condition_value);
                if (resultCount > 0) {
                    Toast.makeText(DeleteUpdateActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
                }
                if (resultCount < 0) {
                    Toast.makeText(DeleteUpdateActivity.this, "Update unsuccessful", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(DeleteUpdateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    private void loadItem() {
        itemModel = (ItemModel) getIntent().getExtras().get("itemModel");
        if (itemModel == null) {
            return;
        } else {
            title_output.setText(itemModel.getTitle());
            author_output.setText(itemModel.getAuthor());
            pages_output.setText(itemModel.getPages());
        }
        ;
    }
}