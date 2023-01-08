package com.example.ft_hangout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import HelperClass.DbHelper;
import HelperClass.PreferenceHelper;
import Pojo.Contact;

public class CreateContact extends AppCompatActivity {

    int SELECT_PICTURE = 200;

    Uri         selectedImageUri;
    Button      btn_create;
    DbHelper    databaseHelper;
    PreferenceHelper preferenceHelper;
    TextInputEditText et_first_name, et_name, et_phone, et_address, et_other_information;
    ImageView iv_picture_new;

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
        iv_picture_new = findViewById(R.id.iv_picture_new);
        databaseHelper = new DbHelper(CreateContact.this);
        preferenceHelper = new PreferenceHelper();

        iv_picture_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

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

                    String picture_path;
                    if (selectedImageUri != null && !selectedImageUri.equals(Uri.EMPTY)) {
                        picture_path = selectedImageUri.toString();
                    } else {
                        picture_path = "";
                    }

                    Log.d("PICTURE PATH", picture_path);

                    newContact = new Contact(-1, et_first_name.getText().toString(), et_name.getText().toString(),
                            et_phone.getText().toString(), et_address.getText().toString(),
                            et_other_information.getText().toString(), " ", picture_path);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                CreateContact.this.getContentResolver().takePersistableUriPermission(selectedImageUri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                //getPersistedUriPermissions()
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    iv_picture_new.setImageURI(selectedImageUri);
                }
            }
        }
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

}