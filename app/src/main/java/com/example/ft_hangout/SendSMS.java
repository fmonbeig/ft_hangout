package com.example.ft_hangout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SendSMS extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    Button              btn_send;
    TextView            tv_contact_sendsms;
    ListView            lv_sms_historical;
    EditText            et_message_content;
    DbHelper            databaseHelper;
    PreferenceHelper    preferenceHelper;
    Contact             contact;
    SmsManager          smsManager;
    ArrayAdapter        contactArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendsms);

        //**************************//
        //     LAYOUT AND VARIABLES //
        //**************************//
        btn_send = findViewById(R.id.btn_send);
        tv_contact_sendsms = findViewById(R.id.tv_contact_sendsms);
        lv_sms_historical = findViewById(R.id.lv_sms_historical);
        et_message_content = findViewById(R.id.et_message_content);
        databaseHelper = new DbHelper(SendSMS.this);
        preferenceHelper = new PreferenceHelper();
        smsManager = SmsManager.getDefault();
        Intent i = getIntent();
        contact = (Contact) i.getSerializableExtra("contactInfo");

        //**************************//
        //     DISPLAY INFORMATION  //
        //**************************//

        String name = contact.getFirstName() + " " + contact.getName();
        tv_contact_sendsms.setText(name);
        showMessage();
        checkForSmsPermission();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String smsMessage = et_message_content.getText().toString();
                String phoneNumber = String.format("smsto:%s", Integer.toString(contact.getPhone()));
                checkForSmsPermission();
                Toast.makeText(SendSMS.this, phoneNumber, Toast.LENGTH_SHORT).show();
                smsManager.sendTextMessage(phoneNumber,
                        null,
                        smsMessage,
                        null, null);
            }
        });
    }

    private void checkForSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            Log.d("permission","Permission not granted");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
            // After the response the user onRequestPermissionsResult are called
        } else {
            // Permission already granted. Enable the message button.
            btn_send.setEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (permissions[0].equalsIgnoreCase(Manifest.permission.SEND_SMS)
                        && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted.
                } else {
                    // Permission denied.
                    Log.d("fail", getString(R.string.failure_permission));
                    Toast.makeText(SendSMS.this,
                            getString(R.string.failure_permission),
                            Toast.LENGTH_SHORT).show();
                    // Disable the message button.
                    btn_send.setEnabled(false);
                }
            }
        }
    }

    public void showMessage(){
        contactArrayAdapter = new ArrayAdapter<String>(SendSMS.this,
                android.R.layout.select_dialog_item, contact.getEveryMessage());
        lv_sms_historical.setAdapter(contactArrayAdapter);
    }
}