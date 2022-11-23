package com.example.ft_hangout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reference to all the button and Layout

    }

    public void LaunchColor(View v) {
        Intent i = new Intent(this, Color.class);
        startActivity(i);
    }

    public void launchDatabase(View v){
        Intent i = new Intent(this, Database.class );
        startActivity(i);
    }
}

