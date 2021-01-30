package com.example.app.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;
import com.example.app.room.view.TodoActivity;
import com.example.app.simple.view.ItemActivity;
import com.example.app.sqlite.view.NoteActivity;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);

        findViewById(R.id.action_room).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, TodoActivity.class)));
        findViewById(R.id.action_sqlite).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, NoteActivity.class)));
        findViewById(R.id.action_simple).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ItemActivity.class)));

    }
}