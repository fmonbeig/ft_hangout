package ListenerClass;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import Pojo.Contact;

import HelperClass.DbHelper;

public class MyReceiver extends BroadcastReceiver {
    DbHelper databaseHelper;

    @SuppressLint("NewApi")
    public void onReceive(Context context, Intent intent) {
        databaseHelper = new DbHelper(context);
        Contact contact;

        // Get the SMS message.
        Log.d("SMS", "SMS RECEIVE ");
        Toast.makeText(context, "SMS RECEIVE: ", Toast.LENGTH_LONG).show();
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        String strMessage = "";
        String strPhone = "";
        String format = bundle.getString("format");
        // Retrieve the SMS message received.
        Object[] pdus = (Object[]) bundle.get("pdus");
        if (pdus != null) {
            // Check the Android version.
            boolean isVersionM =
                    (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
            // Fill the msgs array.
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                // Check Android version and use appropriate createFromPdu.
                if (isVersionM) {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                // Build the message to show.
                strPhone = msgs[i].getOriginatingAddress();
                strMessage += msgs[i].getMessageBody() + "\n";
                // Log and display the SMS message.
                Log.d("SMS", "onReceive: " + strMessage);
                Toast.makeText(context, strMessage, Toast.LENGTH_LONG).show();
            }
            contact = contactFinder(strPhone);
            appendMessage(strMessage, contact);
        }
    }

    public Contact contactFinder(String phone){
        Contact contact = databaseHelper.getOneContactByPhone(phone);
        if (contact == null) {
            Log.d("NOT EXIST CONTACT", "NOT");
            contactCreation(phone);
            contact = databaseHelper.getOneContactByPhone(phone);
        }
        else
        {
            Log.d("EXIST CONTACT", "EXIST");
        }
        return contact;
    }

    public void contactCreation(String phone){
        Contact contact = new Contact(-1, "", phone,
                phone, "", "", "", "");
        databaseHelper.addOne(contact);
    }

    public void appendMessage(String str, Contact contact) {
        String messageAppend;

        messageAppend = contact.getMessage();
        messageAppend += "RECEIVEBY\r" + str;
        contact.setMessage(messageAppend);
        databaseHelper.modifyMessageContent(contact);
    }
}