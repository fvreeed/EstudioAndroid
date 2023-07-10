package com.example.falaq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent i = getIntent();
        String result = i.getStringExtra("result");
        ((TextView)findViewById(R.id.resAct)).setText(result);
    }
}