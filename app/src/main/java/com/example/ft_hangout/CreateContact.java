package com.example.ft_hangout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import HelperClass.DbHelper;
import HelperClass.PreferenceHelper;
import Pojo.Contact;

public class CreateContact extends AppCompatActivity {

    Button      btn_create;
    DbHelper databaseHelper;
    PreferenceHelper preferenceHelper;
    EditText    et_first_name, et_name, et_phone, et_address, et_other_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        btn_create = findViewById(R.id.btn_create);
        et_first_name = findViewById(R.id.et_first_name);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_address = findViewById(R.id.et_address);
        et_other_information = findViewById(R.id.et_other_information);
        databaseHelper = new DbHelper(CreateContact.this);
        preferenceHelper = new PreferenceHelper();

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact newContact;
                try {
                    if (et_first_name.getText().toString().isEmpty() &&
                            et_name.getText().toString().isEmpty()){
                        Toast.makeText(CreateContact.this, "Please put a name"  , Toast.LENGTH_SHORT).show();
                        return;
                    }
                    newContact = new Contact(-1, et_first_name.getText().toString(), et_name.getText().toString(),
                            et_phone.getText().toString(), et_address.getText().toString(),
                            et_other_information.getText().toString(), " ");
                    boolean success = databaseHelper.addOne(newContact);
                    if (!success) {
                        Toast.makeText(CreateContact.this, "database error, try again"  , Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent home = new Intent(CreateContact.this, MainActivity.class);
                        startActivity(home);
                        Toast.makeText(CreateContact.this, "Contact created", Toast.LENGTH_SHORT).show();
                    }
                } catch(Exception e)
                {
                    Toast.makeText(CreateContact.this, "error creating contact"  , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferenceHelper.setColor(this, getSupportActionBar());
    }
}