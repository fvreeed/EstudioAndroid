package com.example.falaq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewTreeViewModelKt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchCalculator(View view) {

        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    public void launchFirstActivity(View view) {

        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }
}