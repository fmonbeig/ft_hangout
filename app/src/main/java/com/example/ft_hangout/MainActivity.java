package com.example.ft_hangout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Button          btn_language;
    Button          btn_create;
    Button          btn_color;
    ListView        lv_contact;
    DbHelper        databaseHelper;
    ArrayAdapter    contactArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reference to all the button and Layout
        btn_language = findViewById(R.id.btn_language);
        btn_create = findViewById(R.id.btn_create);
        btn_color = findViewById(R.id.btn_color);
        lv_contact = findViewById(R.id.lv_contact);
        databaseHelper = new DbHelper(MainActivity.this);
        showContactList();

        //On click listener for every element

        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    public void showContactList(){
        contactArrayAdapter = new ArrayAdapter<Contact>(MainActivity.this,
                android.R.layout.simple_list_item_1, databaseHelper.getEveryOne());
        lv_contact.setAdapter(contactArrayAdapter);
    }

    //**************************//
    //     ACTIVITY LAUNCHER    //
    //**************************//

    public void launchColor(View v) {
        Intent i = new Intent(this, Color.class);
        startActivity(i);
    }

    public void launchDatabase(View v){
        Intent i = new Intent(this, Database.class );
        startActivity(i);
    }

    public void launchCreateContact(View v){
        Intent i = new Intent(this, CreateContact.class );
        startActivity(i);
    }
}

