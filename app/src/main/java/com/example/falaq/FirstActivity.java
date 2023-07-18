package com.example.falaq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void copyAndCheck(View view) {

        String text = ((TextView)findViewById(R.id.textForCopy)).getText().toString();
        text += "\n";
        text += ((EditText)findViewById(R.id.usersTextForCopy)).getText().toString();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("copy", text);
        startActivity(intent);
    }
}