package com.example.falaq;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView rabbit;
    TextView motivationText;

    MyDatabaseHelpers myDB;
    ArrayList<String> note_id, note_title, note_description;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        rabbit = findViewById(R.id.rabbit);
        motivationText = findViewById(R.id.motivationText);
        add_button.setOnClickListener(view -> {
            Intent intent = new Intent(DatabaseActivity.this, AddActivity.class);
            startActivity(intent);
        });

        myDB = new MyDatabaseHelpers(DatabaseActivity.this);
        note_id = new ArrayList<>();
        note_title = new ArrayList<>();
        note_description = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(DatabaseActivity.this, this, note_id, note_title, note_description);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DatabaseActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {

        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            rabbit.setVisibility(View.VISIBLE);
            motivationText.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                note_id.add(cursor.getString(0));
                note_title.add(cursor.getString(1));
                note_description.add(cursor.getString(2));
            }
            rabbit.setVisibility(View.GONE);
            motivationText.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.delete_all) {
            confirmViaLog();
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmViaLog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete all notes?");
        builder.setMessage("Are you sure you want to delete all notes?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            MyDatabaseHelpers myDatabaseHelpers = new MyDatabaseHelpers(DatabaseActivity.this);
            myDatabaseHelpers.deleteAllData();
            Intent intent = new Intent(DatabaseActivity.this, DatabaseActivity.class);
            startActivity(intent);
            finish();
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {

        });
        builder.create().show();
    }
}