package com.example.ft_hangout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateContact extends AppCompatActivity {

    Button      btn_create;
    DbHelper    databaseHelper;
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

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Contact newContact;
                try {
                    newContact = new Contact(-1, et_first_name.getText().toString(), et_name.getText().toString(),
                            Integer.parseInt(et_phone.getText().toString()), et_address.getText().toString(),
                            et_other_information.getText().toString());
                } catch(Exception e)
                {
                    Toast.makeText(CreateContact.this, "error creating contact"  , Toast.LENGTH_SHORT).show();
                    newContact = new Contact(-1,"error","error", 0, "error", "error"  );
                }
                boolean success = databaseHelper.addOne(newContact);
//                Toast.makeText(CreateContact.this, "Success= " + success, Toast.LENGTH_SHORT).show();
                Toast.makeText(CreateContact.this, newContact.toString()  , Toast.LENGTH_SHORT).show();
            }
        });
    }



}