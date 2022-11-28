package com.example.ft_hangout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;

public class Color extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_color);
    }

    public void ChangeInBlue(View v)
    {
        ActionBar aBar;
        aBar= getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(android.graphics.Color.parseColor("#0475A1"));
        aBar.setBackgroundDrawable(cd);
//        aBar.setTitle("Bonjour");
    }

    public void ChangeInGreen(View v)
    {
        ActionBar aBar;
        aBar= getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(android.graphics.Color.parseColor("#4CAF50"));
        aBar.setBackgroundDrawable(cd);
    }

    public void ChangeInOrange(View v)
    {
        ActionBar aBar;
        aBar= getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(android.graphics.Color.parseColor("#FF5722"));
        aBar.setBackgroundDrawable(cd);
    }

    public void ChangeInPink(View v)
    {
        ActionBar aBar;
        aBar= getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(android.graphics.Color.parseColor("#E91E63"));
        aBar.setBackgroundDrawable(cd);
    }

}