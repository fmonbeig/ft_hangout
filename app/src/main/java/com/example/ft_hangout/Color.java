package com.example.ft_hangout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import HelperClass.PreferenceHelper;
import com.example.ft_hangout.R;

public class Color extends AppCompatActivity {
    PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_color);
        preferenceHelper = new PreferenceHelper();
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferenceHelper.setColor(this, getSupportActionBar());
    }

    public void ChangeInGreen(View v)
    {
        ActionBar aBar;
        aBar= getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(android.graphics.Color.parseColor("#D0F0C0"));
        aBar.setBackgroundDrawable(cd);
        preferenceHelper.newColor(this, "#D0F0C0");
    }

    public void ChangeInBrown(View v)
    {
        ActionBar aBar;
        aBar= getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(android.graphics.Color.parseColor("#755656"));
        aBar.setBackgroundDrawable(cd);
        preferenceHelper.newColor(this, "#755656");
    }

    public void ChangeInBackground(View v)
    {
        ActionBar aBar;
        aBar= getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(android.graphics.Color.parseColor("#FAEEEA"));
        aBar.setBackgroundDrawable(cd);
        preferenceHelper.newColor(this, "#FAEEEA");
    }

    public void ChangeInPink(View v)
    {
        ActionBar aBar;
        aBar= getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(android.graphics.Color.parseColor("#F89ABA"));
        aBar.setBackgroundDrawable(cd);
        preferenceHelper.newColor(this, "#F89ABA");
    }

}