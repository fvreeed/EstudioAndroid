package com.example.falaq;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, description_input;
    Button update_button, delete_button;
    String id, title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        description_input = findViewById(R.id.description_input2);
        update_button = findViewById(R.id.updateButton);
        delete_button = findViewById(R.id.deleteButton);
        getIntentData();
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(title);
        update_button.setOnClickListener(view -> {
            MyDatabaseHelpers myDatabaseHelpers = new MyDatabaseHelpers(UpdateActivity.this);
            title = title_input.getText().toString().trim();
            description = description_input.getText().toString().trim();
            myDatabaseHelpers.updateData(id, title, description);
        });
        delete_button.setOnClickListener(view -> {
            confirmViaLog();
        });
    }

    void getIntentData() {
        if (getIntent().hasExtra("id") &&
                getIntent().hasExtra("title") &&
                getIntent().hasExtra("description")) {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");
            title_input.setText(title);
            description_input.setText(description);
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmViaLog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete note?");
        builder.setMessage("Are you sure you want to delete " + title + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelpers myDatabaseHelpers = new MyDatabaseHelpers(UpdateActivity.this);
                myDatabaseHelpers.deleteOneNote(id);
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
}