package com.example.falaq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, description_input;
    Button update_button;
    String id, title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        description_input = findViewById(R.id.description_input2);
        update_button = findViewById(R.id.updateButton);
        getIntentData();
        update_button.setOnClickListener(view -> {
            MyDatabaseHelpers myDatabaseHelpers = new MyDatabaseHelpers(UpdateActivity.this);
            title = title_input.getText().toString().trim();
            description = description_input.getText().toString().trim();
            myDatabaseHelpers.updateData(id, title, description);
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
}