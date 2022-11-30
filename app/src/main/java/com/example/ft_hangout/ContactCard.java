package com.example.ft_hangout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactCard extends AppCompatActivity {

    TextView tv_name;
    TextView tv_address;
    TextView tv_phone;
    TextView tv_other_information;
    Button btn_delete;
    Button btn_modify;
    Button btn_text;
    Contact contact;
    DbHelper        databaseHelper;
    PreferenceHelper    preferenceHelper;
    PreferenceHelper dot;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_card);

        /* Setup Layout variables */
        tv_name = findViewById(R.id.tv_name);
        tv_address = findViewById(R.id.tv_address);
        tv_phone = findViewById(R.id.tv_phone);
        tv_other_information = findViewById(R.id.tv_other_information);
        btn_delete = findViewById(R.id.btn_delete);
        btn_modify = findViewById(R.id.btn_modify);
        btn_text = findViewById(R.id.btn_text);
        databaseHelper = new DbHelper(ContactCard.this);
        preferenceHelper = new PreferenceHelper();

        /* Retrieve and display contact information's*/
        Intent i = getIntent();
        contact = (Contact) i.getSerializableExtra("contactInfo");
        tv_name.setText(contact.getFirstName() + " " + contact.getName());
        tv_address.setText(contact.getAddress());
        tv_phone.setText(Integer.toString(contact.getPhone()));
        tv_other_information.setText(contact.getOtherInformation());

        /* On Click Listener */
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteOne(contact);
                Intent i = new Intent(ContactCard.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        preferenceHelper.setColor(this, getSupportActionBar());
    }
}