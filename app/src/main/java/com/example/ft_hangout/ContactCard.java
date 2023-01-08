package com.example.ft_hangout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import HelperClass.DbHelper;
import HelperClass.PreferenceHelper;
import Pojo.Contact;
import Pojo.RowContactList;

public class ContactCard extends AppCompatActivity {

    TextView            tv_name;
    TextView            tv_address;
    TextView            tv_phone;
    TextView            tv_other_information;
    ImageView           btn_bin;
    ImageView           btn_modify_contact;
    ImageView           btn_text;
    ImageView           btn_call;
    ImageView           iv_picture_info;
    RowContactList      rowContactList;
    Contact             contact;
    DbHelper            databaseHelper;
    PreferenceHelper    preferenceHelper;

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
        btn_bin = findViewById(R.id.btn_bin);
        btn_modify_contact = findViewById(R.id.btn_modify_contact);
        btn_text = findViewById(R.id.btn_text);
        btn_call = findViewById(R.id.btn_call);
        iv_picture_info = findViewById(R.id.iv_picture_info);
        databaseHelper = new DbHelper(ContactCard.this);
        preferenceHelper = new PreferenceHelper();

        /* Retrieve and display contact information's*/
        Intent i = getIntent();
        rowContactList = (RowContactList) i.getSerializableExtra("contactInfo");
        contact = databaseHelper.getOneContactById(rowContactList.getId());
        tv_name.setText(contact.getFirstName() + " " + contact.getName());
        tv_address.setText(contact.getAddress());
        tv_phone.setText(contact.getPhone());
        tv_other_information.setText(contact.getOtherInformation());

        String pic_path = contact.getPicture();
        if (pic_path != null) {
            pictureDisplay(pic_path);
        }

        /* On Click Listener */

        btn_bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteOne(contact);
                Intent i = new Intent(ContactCard.this, MainActivity.class);
                startActivity(i);
            }
        });

        btn_modify_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modifyContact = new Intent(ContactCard.this, ModifyContact.class);
                modifyContact.putExtra("contactInfo", contact);
                startActivity(modifyContact);
            }
        });

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendSMS = new Intent(ContactCard.this, SendSMS.class);
                sendSMS.putExtra("contactInfo", contact);
                startActivity(sendSMS);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        preferenceHelper.setColor(this, getSupportActionBar());
    }

    void pictureDisplay(String Picture_path){
        Log.d("uri pict", Picture_path);
        Uri picture = Uri.parse(Picture_path);
        if (picture != null && !picture.equals(Uri.EMPTY)) {
            iv_picture_info.setImageURI(picture);
        }
    }

}