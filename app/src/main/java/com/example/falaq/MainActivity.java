package com.example.falaq;

import androidx.appcompat.app.AppCompatActivity;

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

    public void masFunction(View view) {

        EditText editText = findViewById(R.id.latitudeInput);
        EditText editText2 = findViewById(R.id.longitudeInput);
        int latitude = Integer.parseInt(editText.getText().toString());
        int longitude = Integer.parseInt(editText2.getText().toString());
        int result = latitude + longitude;
        String res = Integer.toString(result);
        launchResult(res);
        ((TextView)findViewById(R.id.textView3)).setText(Integer.toString(result));
        Log.d("result", editText.getText().toString());
        PrayTime prayTime = new PrayTime();
        prayTime.setLat(latitude);
        prayTime.setLng(longitude);

        Log.d("queremos", Double.toString(prayTime.getJDate()));

    }

    public void menosFunction(View view) {

        EditText editText = findViewById(R.id.latitudeInput);
        EditText editText2 = findViewById(R.id.longitudeInput);
        int latitude = Integer.parseInt(editText.getText().toString());
        int longitude = Integer.parseInt(editText2.getText().toString());
        int result = latitude - longitude;
        String res = Integer.toString(result);
        launchResult(res);
        ((TextView)findViewById(R.id.textView3)).setText(Integer.toString(result));
    }

    public void launchResult(String view) {

        Intent i = new Intent(this, ResultActivity.class);
        i.putExtra("result", view);
        startActivity(i);
    }
}