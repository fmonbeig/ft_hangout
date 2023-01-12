package com.example.ft_hangout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import HelperClass.DbHelper;
import HelperClass.PreferenceHelper;
import Pojo.Contact;

public class ModifyContact extends AppCompatActivity {

    int SELECT_PICTURE = 200;

    Uri                 selectedImageUri;
    EditText            ed_name_modify;
    ImageView           iv_picture_mod;
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
        iv_picture_mod = findViewById(R.id.iv_picture_mod);

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

        pictureDisplay(contact.getPicture());

        iv_picture_mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact newContact;
                try {
                    String Picture_path;
                    if (selectedImageUri != null && !selectedImageUri.equals(Uri.EMPTY)) {
                        Picture_path = selectedImageUri.toString();
                    } else {
                        Picture_path = "";
                    }
                    newContact = new Contact(contact.getId(), ed_first_name_modify.getText().toString(), ed_name_modify.getText().toString(),
                            ed_phone_modify.getText().toString(), ed_address_modify.getText().toString(),
                            ed_other_information_modify.getText().toString(),contact.getMessage(), Picture_path);
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

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                ModifyContact.this.getContentResolver().takePersistableUriPermission(selectedImageUri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    iv_picture_mod.setImageURI(selectedImageUri);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferenceHelper.setColor(this, getSupportActionBar());
    }

    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_OPEN_DOCUMENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    void pictureDisplay(String Picture_path){
        Log.d("uri pict", Picture_path);
        Uri picture = Uri.parse(Picture_path);
        if (picture != null && !picture.equals(Uri.EMPTY)) {
            iv_picture_mod.setImageURI(picture);
        }
    }

}