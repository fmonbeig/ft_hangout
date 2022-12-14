package com.example.ft_hangout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import HelperClass.DbHelper;
import HelperClass.PreferenceHelper;
import Pojo.Contact;

public class ModifyContact extends AppCompatActivity {

    EditText            ed_name_modify;
    TextInputEditText   ed_first_name_modify;
    TextInputEditText   ed_address_modify;
    TextInputEditText   ed_other_information_modify;
    TextInputEditText   ed_phone_modify;
    DbHelper            databaseHelper;

    PreferenceHelper    preferenceHelper;
    Contact             contact;
    Button              ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_contact);

        ed_name_modify = findViewById(R.id.ed_name_modify);
        ed_first_name_modify = findViewById(R.id.ed_first_name_modify);
        ed_address_modify = findViewById(R.id.ed_address_modify);
        ed_other_information_modify = findViewById(R.id.ed_other_information_modify);
        ed_phone_modify = findViewById(R.id.ed_phone_modify);

        ok = findViewById(R.id.btn_ok_modify);

        databaseHelper = new DbHelper(ModifyContact.this);
        preferenceHelper = new PreferenceHelper();
        Intent i = getIntent();
        contact = (Contact) i.getSerializableExtra("contactInfo");

        ed_name_modify.setText(contact.getName());
        ed_first_name_modify.setText(contact.getFirstName());
        ed_phone_modify.setText(contact.getPhone());
        ed_address_modify.setText(contact.getAddress());
        ed_other_information_modify.setText(contact.getOtherInformation());

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact newContact;
                try {
                    newContact = new Contact(contact.getId(), ed_first_name_modify.getText().toString(), ed_name_modify.getText().toString(),
                            ed_phone_modify.getText().toString(), ed_address_modify.getText().toString(),
                            ed_other_information_modify.getText().toString(),contact.getMessage());
                    boolean success = databaseHelper.modify(newContact);
                    if (!success) {
                        Toast.makeText(ModifyContact.this, "database error, try again", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent home = new Intent(ModifyContact.this, MainActivity.class);
                        startActivity(home);
                        Toast.makeText(ModifyContact.this, "Contact Modified", Toast.LENGTH_SHORT).show();
                    }
                } catch(Exception e)
                {
                    Toast.makeText(ModifyContact.this, "error creating contact", Toast.LENGTH_SHORT).show();
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